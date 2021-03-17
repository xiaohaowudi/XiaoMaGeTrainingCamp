package org.geektimes.jmx;

import java.beans.ConstructorProperties;
import java.util.Date;

// MXBean 返回的复杂类型最后会被转换为CompositeDataSupport类型
public class QueueSample {

    private final Date date;
    private final int size;
    private final String head;

    @ConstructorProperties({"date", "size", "head"})
    public QueueSample(Date date, int size,
                       String head) {
        this.date = date;
        this.size = size;
        this.head = head;
    }

    public Date getDate() {
        System.out.println("getDate is called");
        return date;
    }

    public int getSize() {
        System.out.println("getSize is called");
        return size;
    }

    public String getHead() {
        System.out.println("getHead is called");
        return head;
    }
}
