package org.jazzteam;

public class NumberSplitter {
    private Long number;
    private String[] classes;

    public NumberSplitter(Long number) {
        this.number = number;
        if (String.valueOf(number).length() % 3 == 0) {
            classes = new String[String.valueOf(number).length() / 3];
        } else {
            classes = new String[String.valueOf(number).length() / 3 + 1];
        }
    }

    public String[] getClasses() {
        return classes;
    }

    public void split() {
        for (int i = String.valueOf(number).length() - 1, j = 0; j < classes.length; i -= 3, j++) {
            if (j == classes.length - 1) {
                classes[j] = String.valueOf(number).substring(0, i + 1);
            } else {
                classes[j] = String.valueOf(number).substring(i - 2, i + 1);
            }
        }
    }
}
