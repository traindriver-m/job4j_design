package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());


    public static void main(String[] args) {
        byte byteValue = 127;
        short shortValue = 61;
        int intValue = 10;
        long longValue = 1023466955;
        double doubleValue = 354.56;
        float floatValue = 3.14f;
        boolean booleanValue = false;
        char charValue = 'a';
        LOG.debug("Values: {}, {}, {}, {}, {}, {}, {}, {}", byteValue, shortValue,
                intValue, longValue, doubleValue, floatValue, booleanValue, charValue);

    }
}
