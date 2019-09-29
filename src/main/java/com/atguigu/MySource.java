package com.atguigu;

import org.apache.flume.Context;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.PollableSource;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.SimpleEvent;
import org.apache.flume.source.AbstractSource;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义source
 *
 * @Author cuihaiyan
 * @Create_Time 2019-09-29 17:15
 */
public class MySource extends AbstractSource implements Configurable, PollableSource {
    //定义配置文件将来要读取的字段
    private Long delay; //两条数据之间的间隔
    private String field;


    /**
     * 初始化配置信息
     *
     * @param context
     */
    @Override
    public void configure(Context context) {
        delay = context.getLong("delay", 2000l);
        field = context.getString("field", "atguigu");
    }

    /**
     * 处理数据
     * event 组成：header+Byte payload
     *
     * @return
     * @throws EventDeliveryException
     */
    @Override
    public Status process() throws EventDeliveryException {
        try {
            //创建事件头信息
            Map<String, String> header = new HashMap<>();
            //创建事件
            SimpleEvent event = new SimpleEvent();

            //把数据存到event
            for (int i = 0; i < 5; i++) {
                event.setHeaders(header);
                event.setBody((field + i).getBytes());

                //将event写入channel
                getChannelProcessor().processEvent(event);
                //间隔
                Thread.sleep(delay);
            }

        } catch (Exception e) {
            return Status.BACKOFF;
        }
        return Status.READY;
    }

    /**
     * 失败重试，重试的间隔，递增的时长
     *
     * @return
     */
    @Override
    public long getBackOffSleepIncrement() {
        return 0;
    }

    /**
     * 失败重试，重试的间隔，递增的时长，最大值
     *
     * @return
     */
    @Override
    public long getMaxBackOffSleepInterval() {
        return 0;
    }
}
