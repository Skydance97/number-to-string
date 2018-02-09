package org.jazzteam;

public class UnitsConverter extends AbstractExpandedConverter {

    public UnitsConverter(Long number, Integer classNumber) {
        super(number, classNumber);
        if (!classNumber.equals(2)) {
            setDictionary(DictionaryLoader.loadDictionary(DictionaryType.Units));
        } else {
            setDictionary(DictionaryLoader.loadDictionary(DictionaryType.UnitsForThousands));
        }
    }

    @Override
    public void convert() {
        setConvertedNumber(getDictionary().getOrDefault(getNumber().intValue() - (getNumber().intValue() / 100) * 100, EMPTY_STRING));
        if (getConvertedNumber().isEmpty()) {
            setConvertedNumber(getDictionary().getOrDefault((getNumber().intValue() - (getNumber().intValue() / 100) * 100) % 10, EMPTY_STRING));
        }
    }
}
