package myPractices;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class SpartanJsonToCollection {

    @BeforeAll
    public static void init(){
        baseURI="http://3.85.141.124:8000";
    }

    /*
    Given accept type is json
    And path param spartan id is 11
    when user sends a get request to /spartan,{id}
    Then status code is 200
    And content type is json
    And
                "id": 11,
                "name": "Nona",
                "gender": "Female",
                "phone": 7959094216

     */

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
        .pathParam("id", 11).when().get("/api/spartans/{id}");

        // how to convert Json response to Java Collection(Map)
       Map<String, Object> spartanMap = response.body().as(Map.class);

        System.out.println(spartanMap.get("name"));
        System.out.println(spartanMap.get("id"));

        // one example verification, one side map/ expected value
        assertEquals(spartanMap.get("name"), "Nona");
        assertEquals(spartanMap.get("id"), 11);

    }


    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans") ;

    //    response.prettyPrint();

        // convert full json body to list of Maps
        // response.body().as(Map.class); // we will convert this to a List

        List<Map<String, Object>> listOfSpartans = response.body().as(List.class);

        // print all data of first spartan
        System.out.println( listOfSpartans.get(0) );
        // {id=102, name=Dowran Updated, gender=Male, phone=7777777777}

        System.out.println("===========================");

        // if I want to see the result as key and value format, then I can store it to Map
        Map<String, Object> firstSpartan = listOfSpartans.get(0) ;
        System.out.println(firstSpartan.get("name") );  // Dowran Updated

        System.out.println("===========================");

        // if I want to see all spartans one by one as key : value format, then I can loop it
        // even I can add a counter...
        int counter = 1;
        for( Map<String, Object> eachMap : listOfSpartans ) {

            System.out.println(counter + " - spartan " + eachMap);
            counter++;
        }

    }

}
