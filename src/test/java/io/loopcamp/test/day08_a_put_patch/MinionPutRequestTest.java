package io.loopcamp.test.day08_a_put_patch;

import io.loopcamp.utilities.MinionApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class MinionPutRequestTest extends MinionApiTestBase {
    @Test
    public void updateMinionTest() {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("gender", "Male");
        requestMap.put("name", "PutRequest");
        requestMap.put("phone", 1234567425);
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().pathParam("id", 101)
                .and().body(requestMap)
                .when().patch("/minions/{id}");
        assertThat(response.statusCode(), is(HttpStatus.SC_NO_CONTENT));
    }
}