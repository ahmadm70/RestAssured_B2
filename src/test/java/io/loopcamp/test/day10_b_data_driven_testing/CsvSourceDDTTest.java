package io.loopcamp.test.day10_b_data_driven_testing;

import io.loopcamp.utilities.ZippopotamusApiTestBase;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CsvSourceDDTTest extends ZippopotamusApiTestBase {
    @ParameterizedTest
    @CsvSource({
            "7, 34, 102",
            "3, 56, 34",
            "4, 76, 77",
            "8, 87, 87"
    })
    public void basicAddTest(int num1, int num2) {
        System.out.print(num1);
        System.out.println("\t" + num2);
    }

    @ParameterizedTest
    @CsvSource({
            "AK, Kodiak",
            "TN, Pigeon Forge",
            "TN, Morristown",
            "NC, Asheville"
    })
    public void basicAddTest(String state, String city) {
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