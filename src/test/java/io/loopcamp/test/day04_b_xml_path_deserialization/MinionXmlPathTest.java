package io.loopcamp.test.day04_b_xml_path_deserialization;

import io.loopcamp.utilities.MinionApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class MinionXmlPathTest extends MinionApiTestBase {
    @DisplayName("GET /minions with XML")
    @Test
    public void getMinions() {
        Response response = given().accept(ContentType.XML)
                .when().get("/minions");
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(ContentType.XML.toString(), response.contentType());
        XmlPath xmlPath = response.xmlPath();
        assertEquals(1, xmlPath.getInt("List.item[0].id"), "IDs aren't matching!");
        assertEquals("Meade", xmlPath.getString("List.item[0].name"), "Names aren't matching!");
        assertEquals("Male", xmlPath.getString("List.item[0].gender"), "Genders aren't matching!");
        assertEquals(9994128233L, xmlPath.getLong("List.item[0].phone"), "Phones aren't matching!");
        System.out.println(xmlPath.getList("List.item.name"));
    }
}