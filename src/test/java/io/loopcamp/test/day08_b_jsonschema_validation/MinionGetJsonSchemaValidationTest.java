package io.loopcamp.test.day08_b_jsonschema_validation;

import io.loopcamp.utilities.MinionApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.*;

public class MinionGetJsonSchemaValidationTest extends MinionApiTestBase {
    @Test
    public void singleMinionSchemaValidationTest() {
        given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("/minions/{id}")
                .then().statusCode(HttpStatus.SC_OK)
                .and().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/jsonschemas/SingleMinionSchema.json")));
    }

    @Test
    public void allMinionsSchemaValidationTest() {
        given().accept(ContentType.JSON)
                .when().get("/minions")
                .then().statusCode(HttpStatus.SC_OK)
                .and().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/jsonschemas/AllMinionsSchema.json")));
    }

    @Test
    public void searchMinionsSchemaValidationTest() {
        given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "e", "gender", "Female")
                .when().get("/minions/search")
                .then().statusCode(HttpStatus.SC_OK)
                .and().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/jsonschemas/SearchMinionsSchema.json")));
    }
}