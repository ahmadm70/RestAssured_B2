package io.loopcamp.test.day10_b_data_driven_testing;

import io.loopcamp.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class JUnitValueSourceTest {
    @ParameterizedTest
    @ValueSource(ints = {15, 23, 45, 123, 7, 234, 675, 34})
    public void numberTest(int num) {
        System.out.println(num);
        assertThat(num, greaterThan(5));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Java", "Selenium", "JUnit", "TestNG", "Cucumber"})
    public void wordTest(String word) {
        System.out.println(word);
        assertThat(word, not(emptyOrNullString()));
    }

    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("zippopotamus.api.url");
    }

    @ParameterizedTest
    @ValueSource(ints = {22192, 90033, 77493, 37650, 37863})
    public void zipcodeTest(int zipCode) {
        given().accept(ContentType.JSON)
                .and().pathParam("postal_code", zipCode)
                .when().get("/US/{postal_code}")
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .log().all();
    }
}