package myPractices;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pojo.Spartan;
import pojo.Spartan2;

public class SpartanTestsPOJODeserilization {

    @BeforeAll
    public static void init(){
        baseURI="http://3.85.141.124:8000";
    }

    /*
    Given accept type is Json
    And path param id is 15
    When user send a get request to spartans/{id}
    Then status code is 200
        {
            "id": 15,
            "name": "Meta",
            "gender": "Female",
            "phone": 1938695106
        }
     */

    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
            .pathParam("id", 15)
            .when().get("/api/spartans/{id}");

        // how to convert json response to our spartan class
        Spartan2 spartan2 = response.body().as(Spartan2.class);

        // verify each key with spartan object
        assertEquals(spartan2.getName(), "Meta");
        assertEquals(spartan2.getId(), 15);
        assertEquals(spartan2.getGender(), "Female");
        assertEquals(spartan2.getPhone(), 1938695106);





    }

}
