package com.atguigu;

import org.apache.flume.*;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义sink
 *
 * @Author cuihaiyan
 * @Create_Time 2019-09-29 19:18
 */
public class MySink extends AbstractSink implements Configurable {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractSink.class);
    private String prefix;
    private String suffix;

    /**
     * 初始化配置信息
     *
     * @param context
     */
    @Override
    public void configure(Context context) {
        prefix = context.getString("prefix", "PRE");
        suffix = context.getString("suffix");
    }

    /**
     * process 从Channel 读取获取数据（event）
     * 使用场景：读取channel 数据写入 MySQL 或者其他文件系统
     *
     * @return
     * @throws EventDeliveryException
     */
    @Override
    public Status process() throws EventDeliveryException {
        Status status = null;

        // Start Transaction
        Channel channel = getChannel();

        Transaction transaction = channel.getTransaction();

        transaction.begin();

        try {
            // Send the Event to the external respository
            Event take;
            while ((take = channel.take()) == null) {
                Thread.sleep(200);
            }
            //到这里就拿到数据了,输出数据
            LOG.info(prefix + new String(take.getBody()) + suffix);

            transaction.commit();
            status = Status.READY;
        } catch (Throwable t) {
            //回滚
            transaction.rollback();

            status = Status.BACKOFF;

            // re-throw all Errors
            if (t instanceof Error) {
                throw (Error) t;
            }
        }finally {
            transaction.close();
        }
        return status;
    }
}
