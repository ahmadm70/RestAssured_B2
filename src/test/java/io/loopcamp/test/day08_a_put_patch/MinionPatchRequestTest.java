package io.loopcamp.test.day08_a_put_patch;

import io.loopcamp.pojo.Minion;
import io.loopcamp.utilities.MinionApiTestBase;
import io.loopcamp.utilities.MinionRestUtilities;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class MinionPatchRequestTest extends MinionApiTestBase {
    @Test
    public void minionPatchRequestTest() {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("gender", "Male");
        requestMap.put("name", "PutRequest");
        requestMap.put("phone", "2024442233");
        given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().pathParam("id", 101)
                .and().body(requestMap)
                .when().put("/minions/{id}")
                .then().statusCode(HttpStatus.SC_NO_CONTENT);
        Minion minion = MinionRestUtilities.getMinionById(101);
        assertThat(minion.getPhone(), is(requestMap.get("phone")));
    }
}