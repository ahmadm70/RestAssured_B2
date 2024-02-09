package io.loopcamp.test.day08_b_jsonschema_validation;

import io.loopcamp.utilities.MinionApiTestBase;
import io.loopcamp.utilities.MinionRestUtilities;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class MinionPostJsonSchemaValidationTest extends MinionApiTestBase {
    @Test
    public void singleMinionSchemaValidationTest() {
        Map<String, Object> minionMap = new HashMap<>();
        minionMap.put("gender", "Male");
        minionMap.put("name", "TestPost1");
        minionMap.put("phone", 1234567425);
        int postedMinionId = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(minionMap)
                .when().post("/minions")
                .then().statusCode(HttpStatus.SC_CREATED)
                .and().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/jsonschemas/MinionPostSchema.json")))
                .and().extract().jsonPath().getInt("data.id");
        MinionRestUtilities.deleteMinionById(postedMinionId);
    }
}