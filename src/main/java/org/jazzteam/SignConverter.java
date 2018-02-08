package org.jazzteam;

public class SignConverter extends AbstractConverter {
    private static final String MINUS_STRING = "минус";
    private static final char MINUS_CHAR = '-';

    public SignConverter(Long number) {
        super(number);
    }

    @Override
    public void convert() {
        String numberString = String.valueOf(getNumber());
        if (numberString.charAt(0) == MINUS_CHAR) {
            setConvertedNumber(MINUS_STRING);
            setNumber(Long.valueOf(numberString.substring(1)));
        } else {
            setConvertedNumber(EMPTY_STRING);
        }
    }
}
