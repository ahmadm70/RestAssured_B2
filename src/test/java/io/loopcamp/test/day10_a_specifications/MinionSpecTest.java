package io.loopcamp.test.day10_a_specifications;

import io.loopcamp.utilities.MinionSecureApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class MinionSpecTest extends MinionSecureApiTestBase {
    RequestSpecification requestSpecification = given().accept(ContentType.JSON)
            .and().auth().basic("loopacademy", "loopacademy");
    ResponseSpecification responseSpecification = expect().statusCode(HttpStatus.SC_OK)
            .and().contentType(ContentType.JSON);

    @Test
    public void singleMinionTest() {
        given().spec(requestSpecification)
                .and().pathParam("id", 10)
                .when().get("/minions/{id}")
                .then().spec(responseSpecification)
                .and().body("name", is("Lorenza"));
    }

    @Test
    public void allMinionTest() {
        given().spec(requestSpecification)
                .when().get("/minions")
                .then().spec(responseSpecification);
    }
}