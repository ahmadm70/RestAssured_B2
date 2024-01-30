package io.loopcamp.test.day04_a_json_path;

import io.loopcamp.utilities.HRApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HREmployeesJsonPathTest extends HRApiTestBase {
    @DisplayName("GET /employees?limit=200")
    @Test
    public void getEmployeesJsonPath() {
        /*
        We can store our queryParams as a KEY and VALUE by using map
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("limit", 2);
        Response response = given().accept(ContentType.JSON).and().queryParams(queryMap).when().get("/employees");
         */
        Response response = given().accept(ContentType.JSON).and().queryParam("limit", 20).when().get("/employees");
        JsonPath jsonPath = response.jsonPath();
        System.out.println("1st employee first_name: " + jsonPath.getString("items[0].first_name"));
        System.out.println("1st employee job_id: " + jsonPath.getString("items[0].job_id"));
        List<String> emails = jsonPath.getList("items.email");
        assertTrue(emails.contains("NYANG"));
        List<String> firstNames = jsonPath.getList("items.first_name");
        System.out.println(firstNames);
        System.out.println(firstNames.size());
        //findAll{} --> returns all values for the given parameter
        List<String> firstNamesWithDepartmentID = jsonPath.getList("items.findAll{it.department_id==90}.first_name");
        System.out.println(firstNamesWithDepartmentID);
        System.out.println(firstNamesWithDepartmentID.size());
        List<String> firstNamesWithJobID = jsonPath.getList("items.findAll{it.job_id=='IT_PROG'}.first_name");
        //2nd way of doing same thing with the line above
        //List<String> firstNamesWithJobID = jsonPath.getList("items.findAll{it.job_id.equals(\"IT_PROG\")}.first_name");
        System.out.println(firstNamesWithJobID);
        System.out.println(firstNamesWithJobID.size());
        List<String> firstnamesWithSalary = jsonPath.getList("items.findAll{it.salary>5000}.first_name");
        System.out.println(firstnamesWithSalary);
        System.out.println(firstnamesWithSalary.size());
        //max{} --> returns the maximum value for given parameter
        String maxSalaryFirstName = (jsonPath.getString("items.max{it.salary}.first_name"));
        System.out.println(maxSalaryFirstName);
        //min{} --> returns the minimum value for given parameter
        String minSalaryFirstName = (jsonPath.getString("items.min{it.salary}.first_name"));
        System.out.println(minSalaryFirstName);
    }
}