package org.jazzteam;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ConverterTest {
    @Parameterized.Parameter()
    public long input;

    @Parameterized.Parameter(1)
    public String expected;

    @Parameterized.Parameters(name = "{index}: convert({0}) to \"{1}\"")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 0L, "ноль" },
                { -1L, "минус один" },
                { 11L, "одиннадцать" },
                { -222L, "минус двести двадцать два" },
                { 1000L, "одна тысяча" },
                { 4000333222111L, "четыре триллиона триста тридцать три миллиона двести двадцать две тысячи сто одиннадцать" },
                { -5000555L, "минус пять миллионов пятьсот пятьдесят пять" }
        });
    }

    @Test
    public void test() {
        AbstractConverter converter = new Converter(input);
        converter.convert();
        Assert.assertEquals("Mismatch of the expected and actual data", expected, converter.getConvertedNumber());
    }
}
