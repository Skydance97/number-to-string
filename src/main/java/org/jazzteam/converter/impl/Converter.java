package org.jazzteam.converter.impl;

import org.jazzteam.converter.Convertible;
import org.jazzteam.util.NumberUtils;

public class Converter implements Convertible {
    private static final String ZERO = "ноль";

    private SignConverter signConverter;
    private NumericalClassConverter numericalClassConverter;

    public Converter() {
        this.signConverter = new SignConverter();
        this.numericalClassConverter = new NumericalClassConverter();
    }

    @Override
    public String convert(long number) {
        StringBuilder convertedNumber;
        if (number == 0L) {
            convertedNumber = new StringBuilder(ZERO);
        } else {
            convertedNumber = new StringBuilder(signConverter.convert(number));
            final long[] splittedNumber = NumberUtils.split(number);
            for (int i = splittedNumber.length - 1; i > -1; i--) {
                numericalClassConverter.setClassNumber(i + 1);
                String convertedNumericalClass = numericalClassConverter.convert(splittedNumber[i]);
                if (!convertedNumericalClass.isEmpty()) {
                    if (convertedNumber.length() > 0) {
                        convertedNumber.append(SEPARATOR);
                    }
                    convertedNumber.append(convertedNumericalClass);
                }
            }
        }
        return convertedNumber.toString();
    }
}
