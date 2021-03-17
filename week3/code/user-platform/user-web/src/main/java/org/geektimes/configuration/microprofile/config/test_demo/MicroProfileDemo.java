package org.geektimes.configuration.microprofile.config.test_demo;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.config.spi.Converter;
import org.geektimes.configuration.microprofile.config.converter.*;

import javax.swing.text.html.Option;
import java.util.Optional;

public class MicroProfileDemo {

    public static void main(String[] args) {
        Config config = ConfigProvider.getConfig();

        for (String name : config.getPropertyNames()) {
            System.out.println("name = " + name + config.getValue(name, Object.class));
        }

        String appName = config.getValue("application.name", String.class);
        System.out.println("appName = " + appName);

        boolean boolean_val = config.getValue("test.boolean.value", boolean.class);
        System.out.println("test.boolean.value = " + boolean_val);

        byte byte_val = config.getValue("test.byte.value", byte.class);
        System.out.println("test.byte.value = " + byte_val);

        short short_val = config.getValue("test.short.value", short.class);
        System.out.println("test.short.value = " + short_val);

        int int_val = config.getValue("test.int.value", int.class);
        System.out.println("test.int.value = " + int_val);

        long long_val = config.getValue("test.long.value", long.class);
        System.out.println("test.long.value = " + long_val);

        float float_val = config.getValue("test.float.value", float.class);
        System.out.println("test.float.value = " + float_val);

        double double_val = config.getValue("test.double.value", double.class);
        System.out.println("test.double.value = " + double_val);
    }
}
