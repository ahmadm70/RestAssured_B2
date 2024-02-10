package io.loopcamp.test.day09_a_authorization;

import io.loopcamp.utilities.MinionSecureApiTestBase;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class MinionBasicAuthTest extends MinionSecureApiTestBase {
    @Test
    public void getAllMinionsBasicAuthTest() {
        given().accept(ContentType.JSON)
                .and().auth().basic("loopacademy", "loopacademy")
                .when().get("/minions")
                .then().statusCode(HttpStatus.SC_OK)
                .and().contentType(ContentType.JSON)
                .log().all();
    }

    @Test
    public void getAllMinionsUnauthorizedTest() {
        given().accept(ContentType.JSON)
                .and().auth().basic("loopacademy2", "loopacademy2")
                .when().get("/minions")
                .then().statusCode(HttpStatus.SC_UNAUTHORIZED)
                .and().contentType(ContentType.JSON)
                .log().all();
    }
}