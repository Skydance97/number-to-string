package org.jazzteam;

import java.util.Map;

public abstract class AbstractExpandedConverter extends AbstractConverter {
    private Integer classNumber;
    private Map<Integer, String> dictionary;

    public AbstractExpandedConverter(Long number, Integer classNumber) {
        super(number);
        if (classNumber > 0 && classNumber < 8) {
            this.classNumber = classNumber;
        } else throw new IllegalArgumentException("Number: " + classNumber + "of a class has to be in the range from 1 to 7");
    }

    public Integer getClassNumber() {
        return classNumber;
    }

    public Map<Integer, String> getDictionary() {
        return dictionary;
    }

    public void setDictionary(Map<Integer, String> dictionary) {
        this.dictionary = dictionary;
    }
}
