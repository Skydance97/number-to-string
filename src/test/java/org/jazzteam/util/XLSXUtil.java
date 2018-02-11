package org.jazzteam.util;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XLSXUtil {
    private static final String PATH = "src/test/resources/";
    private static final String FILE_NAME = "test-data.xlsx";
    private static final String SHEET_NAME = "DataDrivenTest";

    public static List<Object[]> loadTestData() {
        List<Object[]> list = new ArrayList<>();
        try (InputStream inputStream = new FileInputStream(PATH + FILE_NAME);
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            for (Row row : workbook.getSheet(SHEET_NAME)) {
                Object[] objects;
                if (row.getCell(0).getCellTypeEnum().equals(CellType.NUMERIC) &&
                        row.getCell(1).getCellTypeEnum().equals(CellType.STRING)) {
                    objects = new Object[] {
                            (long) row.getCell(0).getNumericCellValue(),
                            row.getCell(1).getStringCellValue()
                    };
                } else {
                    throw new InvalidTestDataException("Invalid test data");
                }
                list.add(objects);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
