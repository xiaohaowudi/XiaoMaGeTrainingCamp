package org.geektimes.configuration.microprofile.config.converter;

import org.eclipse.microprofile.config.spi.Converter;

public class String2ByteConverter implements Converter<Byte> {
    @Override
    public Byte convert(String value) throws IllegalArgumentException, NullPointerException {
        return Byte.parseByte(value);
    }
}
