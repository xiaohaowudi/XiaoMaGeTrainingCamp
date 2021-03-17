package org.geektimes.configuration.microprofile.config.converter;

import org.eclipse.microprofile.config.spi.Converter;

public class String2DoubleConverter implements Converter<Double> {
    @Override
    public Double convert(String value) throws IllegalArgumentException, NullPointerException {
        return Double.parseDouble(value);
    }
}
