package org.jazzteam;

public class HundredsConverter extends AbstractExpandedConverter {

    public HundredsConverter(Long number, Integer classNumber) {
        super(number, classNumber);
        setDictionary(DictionaryLoader.loadDictionary(DictionaryType.Hundreds));
    }

    @Override
    public void convert() {
        setConvertedNumber(getDictionary().getOrDefault(getNumber().intValue() / 100, EMPTY_STRING));
    }
}
