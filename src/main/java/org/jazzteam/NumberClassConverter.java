package org.jazzteam;

public class NumberClassConverter extends AbstractExpandedConverter {
    private HundredsConverter hundredsConverter;
    private TensConverter tensConverter;
    private UnitsConverter unitsConverter;
    private EndingConnector endingConnector;

    public NumberClassConverter(Long number, Integer classNumber) {
        super(number, classNumber);
        setDictionary(DictionaryLoader.loadDictionary(NumbersEnum.Classes));
        int numberLength = String.valueOf(number).length();
        if (numberLength > 3) {
            throw new IllegalArgumentException("Number " + number + " has to be in the range from 0 to 999");
        }
        if (numberLength > 2) {
            hundredsConverter = new HundredsConverter(number, classNumber);
        }
        if (numberLength > 1) {
            tensConverter = new TensConverter(number, classNumber);
        }
        unitsConverter = new UnitsConverter(number, classNumber);
        if (!number.equals(0L)) {
            endingConnector = new EndingConnector();
        }
    }

    @Override
    public void convert() {
        StringBuilder convertedNumberClass = new StringBuilder();
        if (hundredsConverter != null) {
            hundredsConverter.convert();
            convertedNumberClass.append(hundredsConverter.getConvertedNumber());
        }
        if (tensConverter != null) {
            tensConverter.convert();
            if (!tensConverter.getConvertedNumber().isEmpty()) {
                if (!getConvertedNumber().isEmpty() || !convertedNumberClass.toString().isEmpty()) {
                    convertedNumberClass.append(SEPARATOR);
                }
                convertedNumberClass.append(tensConverter.getConvertedNumber());
            }
        }
        if (unitsConverter != null) {
            unitsConverter.convert();
            if (!unitsConverter.getConvertedNumber().isEmpty()) {
                if (!convertedNumberClass.toString().isEmpty()) {
                    convertedNumberClass.append(SEPARATOR);
                }
                convertedNumberClass.append(unitsConverter.getConvertedNumber());
            }
        }
        if (!convertedNumberClass.toString().isEmpty()) {
            if (!getDictionary().getOrDefault(getClassNumber(), EMPTY_STRING).isEmpty()) {
                convertedNumberClass.append(SEPARATOR).append(getDictionary().getOrDefault(getClassNumber(), EMPTY_STRING));
            }
        }
        setConvertedNumber(convertedNumberClass.toString());
        if (endingConnector != null) {
            endingConnector.connect();
        }
    }

    private class EndingConnector {
        private static final String A_ENDING = "а";
        private static final String I_ENDING = "и";
        private static final String OV_ENDING = "ов";

        private boolean endingConnected;

        public EndingConnector() {
            endingConnected = false;
        }

        public boolean isEndingConnected() {
            return endingConnected;
        }

        public void setEndingConnected(boolean EndingConnected) {
            this.endingConnected = EndingConnected;
        }

        public void connect() {
            if (isEndingConnected()) return;
            if (getClassNumber().equals(2)) {
                connectToThousand();
            } else if (!getClassNumber().equals(1)) {
                connectToBigger();
            }
            setEndingConnected(true);
        }

        private void connectToThousand() {
            if (String.valueOf(getNumber()).length() == 2 && String.valueOf(getNumber()).charAt(0) == '1' ||
                    String.valueOf(getNumber()).length() == 3 && String.valueOf(getNumber()).charAt(1) == '1') {
                return;
            }
            if (getNumber() % 10 == 1) {
                setConvertedNumber(getConvertedNumber() + A_ENDING);
            } else if (getNumber() % 10 > 1 && getNumber() % 10 < 5) {
                setConvertedNumber(getConvertedNumber() + I_ENDING);
            }
        }

        private void connectToBigger() {
            if (getNumber() % 10 == 1) {
                if (String.valueOf(getNumber()).length() == 2 && String.valueOf(getNumber()).charAt(0) == '1') {
                    setConvertedNumber(getConvertedNumber() + OV_ENDING);
                } else if (String.valueOf(getNumber()).length() == 3 && String.valueOf(getNumber()).charAt(1) == '1') {
                    setConvertedNumber(getConvertedNumber() + OV_ENDING);
                }
            } else if (getNumber() % 10 > 1 && getNumber() % 10 < 5) {
                if (String.valueOf(getNumber()).length() == 1 && String.valueOf(getNumber()).charAt(0) == '1') {
                    return;
                }
                setConvertedNumber(getConvertedNumber() + A_ENDING);
            } else {
                setConvertedNumber(getConvertedNumber() + OV_ENDING);
            }
        }
    }
}
