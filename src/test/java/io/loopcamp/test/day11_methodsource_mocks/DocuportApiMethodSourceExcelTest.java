package io.loopcamp.test.day11_methodsource_mocks;

import io.loopcamp.utilities.DocuportApiTestBase;
import io.loopcamp.utilities.ExcelUtilities;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;

public class DocuportApiMethodSourceExcelTest extends DocuportApiTestBase {
    public static List<Map<String, String>> getUserCredentials() {
        String filePath = "src/test/resources/DocBeta.xlsx";
        ExcelUtilities excelUtilities = new ExcelUtilities(filePath, "BETA2");
        return excelUtilities.getDataList();
    }

    @ParameterizedTest
    @MethodSource("getUserCredentials")
    public void docuportUserTokenTest(Map<String, String> eachUserMap) {
        getAccessToken(eachUserMap.get("email"));
    }
}