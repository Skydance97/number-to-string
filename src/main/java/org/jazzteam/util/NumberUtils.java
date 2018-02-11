package org.jazzteam.util;

import static java.lang.Long.*;
import static java.lang.Math.*;

public class NumberUtils {

    public static int getCountOfDigits(long number) {
        return (number == 0) ? 1 : (int) ceil(log10(abs(number) + 0.5));
    }

    public static int getCountOfNumericalClasses(long number) {
        return (getCountOfDigits(number) % 3 == 0) ? getCountOfDigits(number) / 3 : getCountOfDigits(number) / 3 + 1;
    }

    public static long[] split(long number) {
        long[] numericalClasses = new long[getCountOfNumericalClasses(number)];
        String numberString = String.valueOf(abs(number));
        for (int i = 0, position = getCountOfDigits(number) - 1; i < numericalClasses.length; i++, position -= 3) {
            if (i == numericalClasses.length - 1) {
                numericalClasses[i] = parseLong(numberString.substring(0, position + 1));
            } else {
                numericalClasses[i] = parseLong(numberString.substring(position - 2, position + 1));
            }
        }
        return numericalClasses;
    }
}
