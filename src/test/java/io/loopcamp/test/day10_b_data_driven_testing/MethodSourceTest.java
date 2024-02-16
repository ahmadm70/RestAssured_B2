package io.loopcamp.test.day10_b_data_driven_testing;

import io.loopcamp.utilities.ZippopotamusApiTestBase;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class MethodSourceTest extends ZippopotamusApiTestBase {
    public static List<String> getCountries() {
        return Arrays.asList("Canada", "USA", "France", "Azerbaijan", "Ukraine");
    }

    @ParameterizedTest
    @MethodSource("getCountries")
    public void countriesTest(String country) {
        System.out.println("Country: " + country);
    }
}