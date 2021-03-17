package org.geektimes.configuration.microprofile.config.config_source;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.io.*;
import java.net.URL;
import java.util.*;

public class JavaEEPropertiesFileConfigSource implements ConfigSource {

    private final Map<String, Object> properties;
    private final Set<String> prop_names;
    private static final String PROPERTIES_FILE_NAME = "/META-INF/javaee.properties";

    public JavaEEPropertiesFileConfigSource() {
        properties = new HashMap<String, Object>();
        prop_names = new HashSet<>();

        Properties props = new Properties();
        URL fileURL = this.getClass().getResource(PROPERTIES_FILE_NAME);
        if (fileURL != null) {
            try {
                props.load(this.getClass().getResourceAsStream(PROPERTIES_FILE_NAME));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (String name : props.stringPropertyNames()) {
            properties.put(name, props.getProperty(name));
            prop_names.add(name);
        }

        // 数据源的优先级配置
        properties.put(ConfigSource.CONFIG_ORDINAL, "10");
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
        return "Properties File Configuration Source";
    }

    public static void main(String[] args) {
        // 测试程序
        ConfigSource configSource = new JavaEEPropertiesFileConfigSource();
        for (String name : configSource.getPropertyNames()) {
            System.out.println(name + " = " + configSource.getValue(name));
        }
    }
}
