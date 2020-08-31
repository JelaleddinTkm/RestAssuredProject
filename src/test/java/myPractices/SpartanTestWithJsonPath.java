package myPractices;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SpartanTestWithJsonPath {

    @BeforeAll
    public static void init() {
        baseURI = "http://3.85.141.124:8000";
    }

    /*
    Given accept type is json
    And path param spartan id is 11
    when user sends a get request to /spartan,{id}
    Then status code is 200
    And content type is json
    And "id" is 11
            {
                "id": 11,
                "name": "Nona",
                "gender": "Female",
                "phone": 7959094216
             }
     */

    @Test
    public void test1(){

    Response response = given()
            .accept(ContentType.JSON)
            .pathParam("id", 11)
    .when()
            .get("/api/spartans/{id}")
    //        .prettyPeek()
            ;

    assertEquals(response.statusCode(), 200);

    // how to read value with path() method?
    int id = response.path("id");
        System.out.println("id = " + id);

    // how to read value with JsonPath?    
        JsonPath jsonPath = response.jsonPath();

        int id1 = jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phone = jsonPath.getLong("phone");

        System.out.println("id = " + id1);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        assertEquals(id1, 11);
        assertEquals(name, "Nona");
        assertEquals(gender, "Female");
        assertEquals(phone, 7959094216l);



    }
}
