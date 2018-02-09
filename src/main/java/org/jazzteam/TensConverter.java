package org.jazzteam;

public class TensConverter extends AbstractExpandedConverter {

    public TensConverter(Long number, Integer classNumber) {
        super(number, classNumber);
        setDictionary(DictionaryLoader.loadDictionary(DictionaryType.Tens));
    }

    @Override
    public void convert() {
        setConvertedNumber(getDictionary().getOrDefault((getNumber().intValue() - (getNumber().intValue() / 100) * 100) / 10, EMPTY_STRING));
    }
}
