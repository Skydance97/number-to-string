package org.jazzteam.converter.impl;

import org.jazzteam.converter.NumberConverter;

import static org.jazzteam.util.DictionaryLoader.*;
import static org.jazzteam.util.DictionaryType.*;

public class DigitsConverter extends NumberConverter {

    public DigitsConverter(int classNumber) {
        setClassNumber(classNumber);
        if (classNumber != 2) {
            setDictionary(load(Digits));
        } else {
            setDictionary(load(DigitsForThousands));
        }
    }

    @Override
    public String convert(long number) {
        String convertedDigit = getDictionary().getOrDefault((int) number, EMPTY_STRING);
        if (convertedDigit.isEmpty()) {
            convertedDigit = getDictionary().getOrDefault((int) number % 10, EMPTY_STRING);
        }
        return convertedDigit;
    }
}
