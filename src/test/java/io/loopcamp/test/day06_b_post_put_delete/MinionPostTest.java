package io.loopcamp.test.day06_b_post_put_delete;

import io.loopcamp.utilities.MinionApiTestBase;
import io.loopcamp.utilities.MinionRestUtilities;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinionPostTest extends MinionApiTestBase {
    @DisplayName("POST /minions")
    @Test
    public void addNewMinionAsJsonTest() {
        String jsonBody = "{\n" +
                "\"gender\": \"Male\",\n" +
                "\"name\": \"TestPost1\",\n" +
                "\"phone\": 1234567425\n" +
                "}";
//        given().accept(ContentType.JSON)
//                .and().contentType(ContentType.JSON)
//                .and().body(jsonBody)
//                .when().post("/minions")
//                .then().statusCode(HttpStatus.SC_CREATED)
//                .and().contentType(containsString("" + ContentType.JSON))
//                .and().body("success", is("A Minion is Born!"),
//                        "data.gender", is("Male"),
//                        "data.name", is("TestPost1"),
//                        "data.phone", is("1234567425"));
        //--------------------------------------------------------------------------------------------------------------
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(jsonBody)
                .when().post("/minions");
        //Verify status code
        response.prettyPrint();
        assertEquals(HttpStatus.SC_CREATED, response.statusCode()); // JUnit
        assertThat(response.statusCode(), is(201)); // Hamcrest
        //Verify Header
        assertThat(response.contentType(), is("application/json;charset=UTF-8"));
        //Converted response to JsonPath
        JsonPath jsonPath = response.jsonPath();
        //Verify body
        assertThat(jsonPath.getString("success"), equalTo("A Minion is Born!"));
        assertThat(jsonPath.getString("data.name"), equalTo("TestPost"));
        assertThat(jsonPath.getString("data.gender"), equalTo("Male"));
        assertThat(jsonPath.getString("data.phone"), equalTo("1234567425"));
        int id = jsonPath.getInt("data.id");
        System.out.println("minion id: " + id);
        MinionRestUtilities.deleteMinionById(id);
    }
}