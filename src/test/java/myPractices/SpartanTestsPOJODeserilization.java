package myPractices;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import pojo.Spartan;


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
                                    .pathParam("id", 15).
                            when()
                                    .get("/api/spartans/{id}");

    /*
    when we convert the data from JSON RESPONSE --> JAVA OBJECT (POJO) it is called DE-SERIALIZATION
    when we convert the data from JAVA OBJECT --> JSON BODY by PUT & POST request it is called SERIALIZATION
     */

        // how to convert json response to our spartan class
        Spartan spartan = response.body().as(Spartan.class);

        // verify each key with spartan object
        assertEquals(spartan.getName(), "Meta");
        assertEquals(spartan.getId(), 15);
        assertEquals(spartan.getGender(), "Female");
        assertEquals(spartan.getPhone(), 1938695106);

    }


    @Test
    public void gsonExample(){

        Gson gson = new Gson();

        String myJsonBody = "{\n" +
                "            \"id\": 15,\n" +
                "            \"name\": \"Meta\",\n" +
                "            \"gender\": \"Female\",\n" +
                "            \"phone\": 1938695106\n" +
                "            }" ;

        // using gson method do de-serialize our json body
        Spartan spartanMeta = gson.fromJson(myJsonBody, Spartan.class) ;

        System.out.println(spartanMeta.toString() );

        // serialization Java object --> JSON BODY

        Spartan spartan = new Spartan("Mike", "Male", 1231231231l);

        // converting custom class to json (serialization)
        String jsonbody = gson.toJson(spartan) ;

        System.out.println(jsonbody);
    }

}
