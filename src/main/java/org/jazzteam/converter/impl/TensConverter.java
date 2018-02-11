package org.jazzteam.converter.impl;

import org.jazzteam.converter.NumberConverter;

import static org.jazzteam.util.DictionaryLoader.*;
import static org.jazzteam.util.DictionaryType.*;

public class TensConverter extends NumberConverter {

    public TensConverter() {
        setDictionary(load(Tens));
    }

    @Override
    public String convert(long number) {
        return getDictionary().getOrDefault((int) number / 10, EMPTY_STRING);
    }
}
