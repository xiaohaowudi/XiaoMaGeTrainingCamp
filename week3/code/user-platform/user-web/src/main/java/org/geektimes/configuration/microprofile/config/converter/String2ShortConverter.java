package org.geektimes.configuration.microprofile.config.converter;

import org.eclipse.microprofile.config.spi.Converter;

public class String2ShortConverter implements Converter<Short> {
    @Override
    public Short convert(String value) throws IllegalArgumentException, NullPointerException {
        return Short.parseShort(value);
    }
}
