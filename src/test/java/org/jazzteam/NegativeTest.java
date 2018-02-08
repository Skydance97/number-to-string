package org.jazzteam;

import org.junit.Test;

public class NegativeTest {

    @Test
    public void negativeTest() {
        AbstractConverter converter = new Converter(null);
        converter.convert();
        System.out.println(converter.getConvertedNumber());
    }
}
