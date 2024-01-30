package io.loopcamp.utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class HRApiTestBase {
    @BeforeAll
    public static void setUp() {
        //String url = ConfigurationReader.getProperty("minion.api.url");
        baseURI = ConfigurationReader.getProperty("hr.api.url");
    }
}