package org.jazzteam.converter.impl;

import org.jazzteam.converter.NumberConverter;

import static org.jazzteam.util.DictionaryLoader.*;
import static org.jazzteam.util.DictionaryType.*;

public class NumericalClassConverter extends NumberConverter {

    public NumericalClassConverter() {
        setDictionary(load(Classes));
    }

    @Override
    public String convert(long number) {
        if (number < 0 || number > 999) {
            throw new IllegalArgumentException("Number " + number + " has to be in the range from 0 to 999");
        }
        StringBuilder convertedNumericalClass = new StringBuilder();
        convertHundreds(convertedNumericalClass, number);
        convertTens(convertedNumericalClass, number);
        convertDigits(convertedNumericalClass, number);
        addClassname(convertedNumericalClass);
        addEnding(convertedNumericalClass, number);
        return convertedNumericalClass.toString();
    }

    private void convertHundreds(StringBuilder convertedNumericalClass, long number) {
        if (number > 99) {
            convertedNumericalClass.append(new HundredsConverter().convert((int) number / 100));
        }
    }

    private void convertTens(StringBuilder convertedNumericalClass, long number) {
        if (number > 19) {
            String convertedTens = new TensConverter().convert(((int) number - (int) (number / 100) * 100));
            if (!convertedTens.isEmpty()) {
                if (!convertedNumericalClass.toString().isEmpty()) {
                    convertedNumericalClass.append(SEPARATOR);
                }
                convertedNumericalClass.append(convertedTens);
            }
        }
    }

    private void convertDigits(StringBuilder convertedNumericalClass, long number) {
        String convertedDigits = new DigitsConverter(getClassNumber()).convert((int) number - (int) (number / 100) * 100);
        if (!convertedDigits.isEmpty()) {
            if (!convertedNumericalClass.toString().isEmpty()) {
                convertedNumericalClass.append(SEPARATOR);
            }
            convertedNumericalClass.append(convertedDigits);
        }
    }

    private void addClassname(StringBuilder convertedNumericalClass) {
        if (!convertedNumericalClass.toString().isEmpty()) {
            if (!getDictionary().getOrDefault(getClassNumber(), EMPTY_STRING).isEmpty()) {
                convertedNumericalClass.append(SEPARATOR).append(getDictionary().getOrDefault(getClassNumber(), EMPTY_STRING));
            }
        }
    }

    private void addEnding(StringBuilder convertedNumericalClass, long number) {
        new EndingConnector(number).connect(convertedNumericalClass);
    }

    private class EndingConnector {
        private static final String A_ENDING = "а";
        private static final String I_ENDING = "и";
        private static final String OV_ENDING = "ов";

        private long number;

        public EndingConnector(long number) {
            this.number = number;
        }

        public long getNumber() {
            return number;
        }

        public void setNumber(long number) {
            this.number = number;
        }

        public void connect(StringBuilder stringBuilder) {
            if (getClassNumber() == 2) {
                stringBuilder.append(getThousandsEnding());
            } else if (getClassNumber() != 1) {
                stringBuilder.append(getEnding());
            }
        }

        private String getThousandsEnding() {
            if (String.valueOf(getNumber()).length() == 2 && String.valueOf(getNumber()).charAt(0) == '1' ||
                    String.valueOf(getNumber()).length() == 3 && String.valueOf(getNumber()).charAt(1) == '1') {
                return EMPTY_STRING;
            }
            if (getNumber() % 10 == 1) {
                return A_ENDING;
            } else if (getNumber() % 10 > 1 && getNumber() % 10 < 5) {
                return I_ENDING;
            }
            return EMPTY_STRING;
        }

        private String getEnding() {
            if (getNumber() % 10 == 1) {
                if (String.valueOf(getNumber()).length() == 2 && String.valueOf(getNumber()).charAt(0) == '1') {
                   return OV_ENDING;
                } else if (String.valueOf(getNumber()).length() == 3 && String.valueOf(getNumber()).charAt(1) == '1') {
                   return OV_ENDING;
                }
            } else if (getNumber() % 10 > 1 && getNumber() % 10 < 5) {
                if (String.valueOf(getNumber()).length() == 1 && String.valueOf(getNumber()).charAt(0) == '1') {
                    return EMPTY_STRING;
                }
                return A_ENDING;
            }
            return OV_ENDING;
        }
    }
}
