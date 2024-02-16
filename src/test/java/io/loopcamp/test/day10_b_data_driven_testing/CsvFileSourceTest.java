package io.loopcamp.test.day10_b_data_driven_testing;

import io.loopcamp.utilities.ZippopotamusApiTestBase;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

public class CsvFileSourceTest extends ZippopotamusApiTestBase {
    @ParameterizedTest
    @CsvFileSource(resources = "/ZipCodes.csv", numLinesToSkip = 1)
    public void zipCodeTest(String state, String city) {
        Map<String, String> data = new HashMap<>();
        data.put("state", state);
        data.put("city", city);
        given().accept(ContentType.JSON)
                //.and().pathParams("state", state, "city", city)
                .and().pathParams(data)
                .when().get("/us/{state}/{city}")
                .then().statusCode(HttpStatus.SC_OK)
                .and().contentType(ContentType.JSON)
                .and().body("places.'place name'", hasItem(city)).log().all();
    }
}