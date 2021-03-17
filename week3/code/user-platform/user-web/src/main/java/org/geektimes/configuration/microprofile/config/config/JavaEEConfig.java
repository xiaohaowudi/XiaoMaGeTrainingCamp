package org.geektimes.configuration.microprofile.config.config;

import com.sun.xml.bind.v2.model.annotation.ClassLocatable;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigValue;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.eclipse.microprofile.config.spi.Converter;
import org.geektimes.configuration.microprofile.config.converter.*;

import java.util.*;
import java.util.logging.Logger;

public class JavaEEConfig implements Config {

    public final static Map<Class, Converter> simpleTypeConverterMapping = new HashMap<>();
    Logger logger = Logger.getLogger(JavaEEConfig.class.getName());

    static {
        simpleTypeConverterMapping.put(boolean.class, new String2BooleanConverter());
        simpleTypeConverterMapping.put(byte.class, new String2ByteConverter());
        simpleTypeConverterMapping.put(short.class, new String2ShortConverter());
        simpleTypeConverterMapping.put(int.class, new String2IntConverter());
        simpleTypeConverterMapping.put(long.class, new String2LongConverter());
        simpleTypeConverterMapping.put(float.class, new String2FloatConverter());
        simpleTypeConverterMapping.put(double.class, new String2DoubleConverter());
    }

    private final List<ConfigSource> configSourceList = new ArrayList<>();

    private static final Comparator<ConfigSource> comp = new Comparator<ConfigSource>() {
        @Override
        public int compare(ConfigSource o1, ConfigSource o2) {
            return o1.getOrdinal() - o2.getOrdinal();
        }
    };


    public JavaEEConfig() {
        ClassLoader cl = getClass().getClassLoader();
        ServiceLoader<ConfigSource> serviceLoader = ServiceLoader.load(ConfigSource.class, cl);
        serviceLoader.forEach(configSourceList::add);

        configSourceList.sort(comp);
        for (ConfigSource configSource : configSourceList) {
            logger.info("config source name = " + configSource.getName() + " config source ordinal = " + configSource.getOrdinal());
        }
    }

    @Override
    public <T> T getValue(String propertyName, Class<T> propertyType) {

        String valueStr = getPropertyValue(propertyName);
        if (valueStr == null) {
            return null;
        }

        Optional<Converter<T>> converter = getConverter(propertyType);
        if (converter.isPresent()) {
            logger.info("converting value using custom converter, type = " + propertyType.getName());
            return converter.get().convert(valueStr);
        }

        return (T)(valueStr);
    }

    @Override
    public ConfigValue getConfigValue(String propertyName) {
        return null;
    }

    protected String getPropertyValue(String propertyName) {
        String value = null;
        for (ConfigSource configSource : configSourceList) {
            value = configSource.getValue(propertyName);
            if (value != null) {
                break;
            }
        }

        return value;
    }

    @Override
    public <T> Optional<T> getOptionalValue(String propertyName, Class<T> propertyType) {
        return Optional.empty();
    }

    @Override
    public Iterable<String> getPropertyNames() {
        Set<String> prop_names = new HashSet<>();

        for (ConfigSource configSource : configSourceList) {
            prop_names.addAll(configSource.getPropertyNames());
        }

        return Collections.unmodifiableSet(prop_names);
    }

    @Override
    public Iterable<ConfigSource> getConfigSources() {
        return Collections.unmodifiableList(configSourceList);
    }

    @Override
    public <T> Optional<Converter<T>> getConverter(Class<T> forType) {
        if (simpleTypeConverterMapping.containsKey(forType)) {
            return Optional.of(simpleTypeConverterMapping.get(forType));
        }

        return Optional.empty();
    }

    @Override
    public <T> T unwrap(Class<T> type) {
        return null;
    }
}
