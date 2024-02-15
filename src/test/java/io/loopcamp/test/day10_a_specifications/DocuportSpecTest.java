package io.loopcamp.test.day10_a_specifications;

import io.loopcamp.utilities.DocuportApiTestBase;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class DocuportSpecTest extends DocuportApiTestBase {

    @Test
    public void employeeInfoTest() {
        given().spec(requestSpecification)
                .when().get("/api/v1/company/organizer-titles/all")
                .then().spec(responseSpecification)
                .and().body("value[0]", is("1"))
                .and().body("displayText[0]", is("President"))
                .and().body("displayText", hasItems("President", "Vice President", "Treasurer", "Secretary"))
                .and().body("displayText", hasSize(4));
    }
}