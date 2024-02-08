package io.loopcamp.utilities;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class HRRestUtilities {
    private static final String baseUrl = ConfigurationReader.getProperty("hr.api.url");

    public static void deleteRegionById(int id) {
        given().accept(ContentType.JSON)
                .and().pathParam("id", id)
                .when().delete(baseUrl + "/regions/{id}");
    }
}