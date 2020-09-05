package day11;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import pojo.Spartan;
import pojo.Spartan2;
import utility.ConfigurationReader;
import utility.DB_Utility;

import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GettingDataFromOtherRequests_1 {

    /*

    How do you handle data issue in your automation ?

    The challenges :
    Dynamic data -- Type of data that changes often and can not be reused for next test.

    2) If you do not have access to DB but have access to API
    Send the request to the endpoint that return your data and
                                             -  get the fields that you want using jsonPath.

       */

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan1.base_url");
        RestAssured.basePath = "/api";
        DB_Utility.createConnection("spartan1");
    }


    /**
     * We want to test GET /Spartans/{id}
     * and ID is dynamic, and also different in different environments
     * we want to always work with available data
     * without dealing with 404 issue because data does not exists
     *
     *  Action Items
     *  1. Send a GET /spartans endpoint
     *  2. store the result data and use it for GET /spartans/{id} request
     *  3. initially just the first data and use it for GET /Spartans/{id} request
     *     and use the name , gender , phone for body validation
     *  4. eventuallly randomize the way you get the ID from List of POJO
     */

    /*
    @Test
    public void testTheDynamicID(){

        Response response = get("/spartans") ;

        List<Spartan2> spartan2List = response.jsonPath().getList("", Spartan2.class);

            System.out.println("spartan2List = " + spartan2List);

        // get the first spartan id and name so we can send below request
        // we are calling list method get(0) to get first Spartan2 Object
        // then we called getter method getId() from Spartan2

        int    firstSpartanIDFromTheList = spartan2List.get(0).getId();
        String firstSpartanNameFromTheList = spartan2List.get(0).getName();

            System.out.println("firstSpartanIDFromTheList = " + firstSpartanIDFromTheList);
            System.out.println("firstSpartanNameFromTheList = " + firstSpartanNameFromTheList);

        // GET /spartans/{id}
        given()
                .pathParam("id", firstSpartanIDFromTheList ).
        when()
                .get("/spartans/{id}").
    //            .prettyPeek();
        then()
                .statusCode(200)
                .body("name", is (firstSpartanNameFromTheList) ) ;
    }

     */

    // own practice
    @Test
    public void testDynamicID(){

        Response response = get("/spartans");
        List<Spartan> spartanList = response.jsonPath().getList("", Spartan.class);

            int firstSpartanIdFromList = spartanList.get(0).getId();
            String firstSpartanNameFromList = spartanList.get(0).getName();

        given()
                .pathParam("id", firstSpartanIdFromList ).
        when()
                .get("/spartans/{id}").
        //        .prettyPeek() ;
        then()
                .statusCode(200)
                .body("name", is( firstSpartanNameFromList) ) ;

    }



    // Can I repeat certain test n number of times in Junit 5

    @RepeatedTest(10)
    public void gettingRandomID_and_NameForEachTest() {

        Response response = get("/spartans");

        List<Spartan> spartanList = response.jsonPath().getList("", Spartan.class );

        // get random spartan object from the list each time
        // our range for the index will be 0--->spartan2List.size()

        /*

        Random r = new Random();


        int randomIndex  =  r.nextInt( spartan2List.size()  ) ;

            System.out.println("randomIndex = " + randomIndex);


        Spartan2 randomSpartanObject =  spartan2List.get(  randomIndex  ) ;

            System.out.println("randomSpartanObject = " + randomSpartanObject);


        given()
                .pathParam("id" , randomSpartanObject.getId() ).
        when()
                .get("/spartans/{id}").

        then()
                .log().body()
                .statusCode(is(200))
                .body("name" , is(  randomSpartanObject.getName()   )  ) ;

         */

        // own pratice

        Random random = new Random() ;

        int randomIndex = random.nextInt( spartanList.size() ) ;

        Spartan randomSpartanObject = spartanList.get( randomIndex ) ;

        given()
                .pathParam("id", randomSpartanObject.getId() ).
        when()
                .get("/spartans/{id}").
        then()
                .statusCode(200)
                .body("name", is (randomSpartanObject.getName() )) ;





    }

}
