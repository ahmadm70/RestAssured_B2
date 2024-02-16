package io.loopcamp.test.day11_methodsource_mocks;

import io.loopcamp.utilities.ExcelUtilities;
import org.junit.jupiter.api.Test;

public class ReadExcelFileDataTest {
    @Test
    public void readDocBetaUsersTest() {
        String filePath = "src/test/resources/DocBeta.xlsx";
        ExcelUtilities excelUtilities = new ExcelUtilities(filePath, "BETA2");
        System.out.println(excelUtilities.getColumnNames());
        System.out.println(excelUtilities.rowCount());
        System.out.println(excelUtilities.getDataList());
    }
}