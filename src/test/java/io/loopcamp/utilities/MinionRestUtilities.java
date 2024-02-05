package io.loopcamp.utilities;

import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class MinionRestUtilities {
    private static final String baseUrl = ConfigurationReader.getProperty("minion.api.url");
    public static void deleteMinionById(int id) {
        given().accept(ContentType.JSON)
                .and().pathParam("id", id)
                .when().delete(baseUrl + "/minions/{id}");
    }
}