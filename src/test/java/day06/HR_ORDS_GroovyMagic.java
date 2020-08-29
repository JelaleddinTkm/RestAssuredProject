package day06;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class HR_ORDS_GroovyMagic {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://3.85.141.124";
        RestAssured.port = 1000;
        RestAssured.basePath = "/ords/hr";
    }


    @DisplayName("Testing the /Employees endpoint")
    @Test
    public void testEmployees(){

        Response response = get("/employees") ;

        JsonPath jsonPath = response.jsonPath();

        // print all id by getting a list
        System.out.println("All IDs" + jsonPath.getList("items.employee_id") );

        // print the first and last id
        System.out.println("First ID" + jsonPath.getInt("items[0].employee_id") );
        System.out.println("Last ID" + jsonPath.getInt("items[-1].employee_id") );

        // get all the id's from first to fifth
        System.out.println("First 5 ID's are: " + jsonPath.getList("items[0..4].employee_id") );

        // get all last names from index 10-15
        System.out.println("last names from index 10-15 are: " + jsonPath.getList("items[10..15].last_name") );

        // get the employee first_name with employee id of 105
        // find and find all where you can specify the criteria to restrict the result
        // find method will return single value that fall into the criteria compared to findAll will return a list
        // find { it.employee_id == 105}
        String result = jsonPath.getString(" items.find{it.employee_id == 105}.last_name ");
            System.out.println("result = " + result);

        int salary = jsonPath.getInt("items.find{it.email == 'LDEHAAN'}.salary");
            System.out.println("salary = " + salary);

        // findAll will get all the result that match the criteria and return it as a list
        // save all the first_names of the employees with salary more than 15000
        List<String> richPeople = jsonPath.getList("items.findAll { it.salary > 15000 }.first_name ");
            System.out.println("richPeople = " + richPeople);

        // find out all the phone numbers in the department_id 90
        List<String> phoneNumList = jsonPath.getList("items.findAll {it.department_id == 90 }.phone_number" );
            System.out.println("phoneNumList = " + phoneNumList);

        // max, min
        // find out max salary, and min salary
        int maxSalary = jsonPath.getInt("items.max {it.salary}.salary");
        System.out.println("maxSalary = " + maxSalary);

        // find out the name of the guy who makes max salary
        String richestGuy = jsonPath.getString("items.max {it.salary}.first_name");
        System.out.println("richestGuy = " + richestGuy);

    }


}
