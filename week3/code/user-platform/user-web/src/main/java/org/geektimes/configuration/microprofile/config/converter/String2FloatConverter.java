package org.geektimes.configuration.microprofile.config.converter;

import org.eclipse.microprofile.config.spi.Converter;

public class String2FloatConverter implements Converter<Float> {
    @Override
    public Float convert(String value) throws IllegalArgumentException, NullPointerException {
        return Float.parseFloat(value);
    }
}
