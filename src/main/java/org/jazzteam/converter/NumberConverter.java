package org.jazzteam.converter;

import java.util.Map;

public abstract class NumberConverter implements Convertible {
    private int classNumber;
    private Map<Integer, String> dictionary;

    public int getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(int classNumber) {
        if (classNumber > 0) {
            this.classNumber = classNumber;
        } else {
            throw new IllegalArgumentException("Number of a class " + classNumber + " has to be more than 0");
        }
    }

    public Map<Integer, String> getDictionary() {
        return dictionary;
    }

    public void setDictionary(Map<Integer, String> dictionary) {
        this.dictionary = dictionary;
    }
}
