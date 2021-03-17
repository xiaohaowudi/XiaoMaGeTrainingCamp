package org.geektimes.configuration.microprofile.config.config_source;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.*;

public class JavaEESystemEnvConfigSource implements ConfigSource {


    @Override
    public Set<String> getPropertyNames() {
        return Collections.unmodifiableSet(System.getenv().keySet());
    }

    @Override
    public String getValue(String propertyName) {
        // 优先级配置
        if (propertyName.equals(ConfigSource.CONFIG_ORDINAL)) {
            return "11";
        }

        return (String)System.getenv().get(propertyName);
    }

    @Override
    public String getName() {
        return "System Environment Configuration Source";
    }

    public static void main(String[] args) {
        // 测试程序
        ConfigSource configSource = new JavaEESystemEnvConfigSource();
        for (String name : configSource.getPropertyNames()) {
            System.out.println(name + " = " + configSource.getValue(name));
        }
    }

}
