package io.loopcamp.utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class MinionSecureApiTestBase {
    @BeforeAll
    public static void setUp() {
        //String url = ConfigurationReader.getProperty("minion.api.url");
        baseURI = ConfigurationReader.getProperty("minion.secure.api.url");
    }
}