package io.loopcamp.test.day12_a_mocks;

import io.loopcamp.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class StudentsMockApiTest {
    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("mock.api.url");
    }

    @DisplayName("GET /students/1")
    @Test
    public void studentsTest() {
        given().accept(ContentType.JSON)
                .when().get("/students/1")
                .then().statusCode(HttpStatus.SC_OK)
                .and().contentType(ContentType.JSON)
                .and().body("studentId", is(1)).log().all();
    }
}