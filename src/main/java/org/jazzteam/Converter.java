package org.jazzteam;

public class Converter extends AbstractConverter {

    public Converter(Long number) {
        super(number);
    }

    @Override
    public void convert() {
        if (getNumber().equals(0L)) {
            setConvertedNumber("ноль");
        } else {
            SignConverter signConverter = new SignConverter(getNumber());
            signConverter.convert();
            setConvertedNumber(signConverter.getConvertedNumber());

            NumberSplitter numberSplitter = new NumberSplitter(signConverter.getNumber());
            numberSplitter.split();

            NumberClassConverter numberClassConverter;
            for (int i = numberSplitter.getClasses().length - 1; i > -1; i--) {
                numberClassConverter = new NumberClassConverter(Long.parseLong(numberSplitter.getClasses()[i]), i + 1);
                numberClassConverter.convert();
                if (!numberClassConverter.getConvertedNumber().isEmpty()) {
                    if (!getConvertedNumber().isEmpty()) {
                        setConvertedNumber(getConvertedNumber() + SEPARATOR);
                    }
                    setConvertedNumber(getConvertedNumber() + numberClassConverter.getConvertedNumber());
                }
            }
        }
    }
}
