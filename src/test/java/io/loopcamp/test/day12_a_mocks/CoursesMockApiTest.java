package io.loopcamp.test.day12_a_mocks;

import io.loopcamp.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CoursesMockApiTest {
    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("mock.api.url");
    }

    @DisplayName("GET /courses")
    @Test
    public void coursesTest() {
        given().accept(ContentType.JSON)
                .when().get("/courses")
                .then().statusCode(HttpStatus.SC_OK)
                .and().contentType(ContentType.JSON)
                .and().body("courseId", hasItems(1, 2, 3))
                .and().body("courseName", hasItems("Java SDET", "RPA Developer", "Salesforce Automation")).log().all();
    }
}