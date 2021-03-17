package org.geektimes.jmx;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import java.util.HashMap;
import java.util.Map;

public class JavaEEConfigQuery implements JavaEEConfigQueryMXBean {
    private Config config = ConfigProvider.getConfig();

    @Override
    public String getApplicationName() {
        return config.getValue("application.name", String.class);
    }

    @Override
    public boolean getBooleanVal() {
        return config.getValue("test.boolean.value", boolean.class);
    }

    @Override
    public byte getByteVal() {
        return config.getValue("test.byte.value", byte.class);
    }

    @Override
    public short getShortVal() {
        return config.getValue("test.short.value", short.class);
    }

    @Override
    public int getIntVal() {
        return config.getValue("test.int.value", int.class);
    }

    @Override
    public long getLongVal() {
        return config.getValue("test.long.value", long.class);
    }

    @Override
    public float getFloatVal() {
        return config.getValue("test.float.value", float.class);
    }

    @Override
    public double getDoubleVal() {
        return config.getValue("test.double.value", double.class);
    }

    @Override
    public Map<String, String> getAllConfig() {
        Map<String, String> m = new HashMap<>();
        for (String name : config.getPropertyNames()) {
            m.put(name, config.getValue(name, Object.class).toString());
        }

        return m;
    }
}
