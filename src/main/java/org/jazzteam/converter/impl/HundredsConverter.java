package org.jazzteam.converter.impl;

import org.jazzteam.converter.NumberConverter;

import static org.jazzteam.util.DictionaryLoader.*;
import static org.jazzteam.util.DictionaryType.*;

public class HundredsConverter extends NumberConverter {

    public HundredsConverter() {
        setDictionary(load(Hundreds));
    }

    @Override
    public String convert(long number) {
        return getDictionary().getOrDefault((int) number, EMPTY_STRING);
    }
}
