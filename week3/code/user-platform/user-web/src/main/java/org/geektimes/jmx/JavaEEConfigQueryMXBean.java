package org.geektimes.jmx;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import java.util.Map;

public interface JavaEEConfigQueryMXBean {
    public String getApplicationName();
    public boolean getBooleanVal();
    public byte getByteVal();
    public short getShortVal();
    public int getIntVal();
    public long getLongVal();
    public float getFloatVal();
    public double getDoubleVal();
    public Map<String, String> getAllConfig();
}
