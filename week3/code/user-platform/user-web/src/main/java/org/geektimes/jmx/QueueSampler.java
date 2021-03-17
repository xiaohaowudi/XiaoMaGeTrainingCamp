package org.geektimes.jmx;


import java.util.Date;
import java.util.Queue;

// MXBean 实现类
public class QueueSampler implements QueueSamplerMXBean {

    private Queue<String> queue;

    public QueueSampler(Queue<String> queue) {
        this.queue = queue;
    }

    @Override
    public QueueSample getQueueSample() {
        synchronized (queue) {
            return new QueueSample(new Date(), queue.size(), queue.peek());
        }
    }

    @Override
    public void clearQueue() {
        synchronized (queue) {
            queue.clear();
        }
    }
}
