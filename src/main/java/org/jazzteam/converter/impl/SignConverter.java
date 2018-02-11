package org.jazzteam.converter.impl;

import org.jazzteam.converter.Convertible;

public class SignConverter implements Convertible {
    private static final String MINUS_STRING = "минус";
    private static final char MINUS_CHAR = '-';

    @Override
    public String convert(long number) {
        String numberString = String.valueOf(number);
        if (numberString.charAt(0) == MINUS_CHAR) {
            return MINUS_STRING;
        } else {
            return EMPTY_STRING;
        }
    }
}
