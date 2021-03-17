package org.geektimes.configuration.microprofile.config.config_source;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.*;

public class JavaEESystemPropertiesConfigSource implements ConfigSource {

    private final Map<String, Object> properties;
    private final Set<String> prop_names;

    public JavaEESystemPropertiesConfigSource() {
        properties = new HashMap<String, Object>();
        prop_names = new HashSet<>();

        Properties props = System.getProperties();
        for (String name : props.stringPropertyNames()) {
            properties.put(name, props.getProperty(name));
            prop_names.add(name);
        }

        // 数据源的优先级配置
        properties.put(ConfigSource.CONFIG_ORDINAL, "12");
    }

    @Override
    public Set<String> getPropertyNames() {
        return prop_names;
    }

    @Override
    public String getValue(String propertyName) {
        return (String)properties.get(propertyName);
    }

    @Override
    public String getName() {
        return "System Properties Configuration Source";
    }

    public static void main(String[] args) {
        // 测试程序
        ConfigSource configSource = new JavaEESystemPropertiesConfigSource();
        for (String name : configSource.getPropertyNames()) {
            System.out.println(name + " = " + configSource.getValue(name));
        }
    }
}
