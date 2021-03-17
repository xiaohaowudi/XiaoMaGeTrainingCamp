package org.geektimes.jmx;


// MXBean 接口定义
public interface QueueSamplerMXBean {
    public QueueSample getQueueSample();
    public void clearQueue();
}