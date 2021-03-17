package org.geektimes.configuration.microprofile.config.converter;

import org.eclipse.microprofile.config.spi.Converter;

public class String2LongConverter implements Converter<Long> {

    @Override
    public Long convert(String value) throws IllegalArgumentException, NullPointerException {
        return Long.parseLong(value);
    }
}
