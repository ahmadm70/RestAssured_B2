package io.loopcamp.test.day02_b_parameters;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class MinionApiWithParametersTest {
    String endPoint = "http://54.92.243.31:8000/api/minions";

    @DisplayName("GET user by id")
    @Test
    public void getSingleMinionTest() {
        //Option 1
        int id = 5;
        Response response = given().accept(ContentType.JSON).when().get(endPoint + "/" + id);
        //Option 2
        given().accept(ContentType.JSON).and().pathParam("id", 5).when().get(endPoint + "/{id}");
        //Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(HttpStatus.SC_OK, response.statusCode());
        //System.out.println(response.contentType());
        System.out.println(response.getHeader("content-type"));
        Assertions.assertEquals(ContentType.JSON.toString(), response.getContentType());
    }
}