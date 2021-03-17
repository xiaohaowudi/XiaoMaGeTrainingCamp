package org.geektimes.configuration.microprofile.config.converter;

import org.eclipse.microprofile.config.spi.Converter;
import sun.jvm.hotspot.jdi.IntegerTypeImpl;

public class String2IntConverter implements Converter<Integer> {

    @Override
    public Integer convert(String value) throws IllegalArgumentException, NullPointerException {
        return Integer.parseInt(value);
    }
}
