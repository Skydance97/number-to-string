package org.jazzteam;

import org.jazzteam.converter.impl.Converter;
import org.jazzteam.util.XLSXUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class NumberToStringTest {
    @Parameter()
    public long input;
    @Parameter(1)
    public String expected;

    @Parameters(name = "{index}: convert({0}) to \"{1}\"")
    public static Collection<Object[]> data() {
        return new ArrayList<>(XLSXUtil.loadTestData());
    }

    @Test
    public void test() {
        Assert.assertEquals("Mismatch of the expected and actual data", expected, new Converter().convert(input));
    }
}
