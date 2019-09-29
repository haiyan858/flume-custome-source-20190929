官方文档，开发手册地址：

自定义source
http://flume.apache.org/releases/content/1.9.0/FlumeDeveloperGuide.html#source


`bin/flume-ng agent -c conf/ -n a1 -f job/flume-mysource-logger.conf -Dflume.root.logger=INFO,console`

log

`2019-09-29 19:08:24,183 (SinkRunner-PollingRunner-DefaultSinkProcessor) [INFO - org.apache.flume.sink.LoggerSink.process(LoggerSink.java:95)] Event: { headers:{} body: 58 58 58 58 30                                  XXXX0 }
 2019-09-29 19:08:29,179 (SinkRunner-PollingRunner-DefaultSinkProcessor) [INFO - org.apache.flume.sink.LoggerSink.process(LoggerSink.java:95)] Event: { headers:{} body: 58 58 58 58 31                                  XXXX1 }
 2019-09-29 19:08:34,183 (SinkRunner-PollingRunner-DefaultSinkProcessor) [INFO - org.apache.flume.sink.LoggerSink.process(LoggerSink.java:95)] Event: { headers:{} body: 58 58 58 58 32                                  XXXX2 }
 2019-09-29 19:08:39,186 (SinkRunner-PollingRunner-DefaultSinkProcessor) [INFO - org.apache.flume.sink.LoggerSink.process(LoggerSink.java:95)] Event: { headers:{} body: 58 58 58 58 33                                  XXXX3 }
 2019-09-29 19:08:44,190 (SinkRunner-PollingRunner-DefaultSinkProcessor) [INFO - org.apache.flume.sink.LoggerSink.process(LoggerSink.java:95)] Event: { headers:{} body: 58 58 58 58 34                                  XXXX4 }
 2019-09-29 19:08:49,192 (SinkRunner-PollingRunner-DefaultSinkProcessor) [INFO - org.apache.flume.sink.LoggerSink.process(LoggerSink.java:95)] Event: { headers:{} body: 58 58 58 58 30                                  XXXX0 }
 2019-09-29 19:08:54,198 (SinkRunner-PollingRunner-DefaultSinkProcessor) [INFO - org.apache.flume.sink.LoggerSink.process(LoggerSink.java:95)] Event: { headers:{} body: 58 58 58 58 31                                  XXXX1 }`
 
 
自定义Sink
http://flume.apache.org/releases/content/1.9.0/FlumeDeveloperGuide.html#sink
 