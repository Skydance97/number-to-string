package org.jazzteam;

public abstract class AbstractConverter {
    public static final String EMPTY_STRING = "";
    public static final String SEPARATOR = " ";

    private Long number;
    private String convertedNumber;

    public AbstractConverter(Long number) {
        this.number = number;
        this.convertedNumber = EMPTY_STRING;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getConvertedNumber() {
        return convertedNumber;
    }

    public void setConvertedNumber(String convertedNumber) {
        this.convertedNumber = convertedNumber;
    }

    public abstract void convert();
}
