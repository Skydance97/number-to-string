package org.jazzteam;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.loader.LoaderType;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = { "test-data.csv" }, loaderType = LoaderType.CSV)
public class NumberToStringTest {

    @Test
    public void dataDrivenTest(@Param(name = "number") Long input, @Param(name = "string") String expected) {
        AbstractConverter converter = new Converter(input);
        converter.convert();
        assertEquals("Mismatch of the expected and actual data", expected, converter.getConvertedNumber());
    }
}
