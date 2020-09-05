package day10;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;
import utility.DB_Utility;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetTestDataFromSpartanDatabase_4 {

    /*

    How do you handle data issue in your automation ?

    The challenges :

    Dynamic data -- Type of data that changes often and can not be reused for next test.
    Data is not sync between environment.
    Data that exists in one environment does not exists in another and casue test failure.

    What's your approach to handle them ?

    1) If the data change often and you have access to DB
    Query the database to get up to date data and feed the data to your test.

     */

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan1.base_url");
        RestAssured.basePath = "/api";
        DB_Utility.createConnection("spartan1");
    }

    @DisplayName("Testing GET /Spartans/{id} by getting the id from DB")
    @Test
    public void testDataFromDB() {

        // I want to write a query to get the newest data created from DB
        String myQuery = "SELECT * FROM SPARTANS ORDER BY SPARTAN_ID DESC";
        // AND JUST GET THE FIRST ROW USING OUR UTILITY METHOD

        DB_Utility.runQuery(myQuery);    // now we have the result

        Map<String, String> firstRowMap = DB_Utility.getRowMap(1);

        // get the id, name, gender, phone out of this map
        System.out.println("firstRowMap = " + firstRowMap);
        System.out.println("spartan_id key's value  = " + firstRowMap.get("spartan_id"));


        // we want to save id, name, gender, phone
        // so we can use the ID to make GET request
        // and verify the response body match the data from database

        //TODO Use the Map data to get the ID as Int
        int idFromDB = Integer.parseInt(firstRowMap.get("SPARTAN_ID"));
        String nameFromDB = firstRowMap.get("NAME");
        String genderFromDB = firstRowMap.get("GENDER");
        long phoneFromDB = Long.parseLong(firstRowMap.get("PHONE"));

        when()
                .get("/spartans/{id}", idFromDB).

                then()
                .log().all()
                .statusCode(200)
                .body("id", is(idFromDB))
                .body("name", is(nameFromDB))
                .body("gender", is(genderFromDB))
                .body("phone.toLong()", is(phoneFromDB));

        // the test is failing if the phone number fall within the range of int
        // because body method is just getting it as int
        // and we can not compare int with long since they are not same data type
        // can I just convert
        // my phone value from response to long directly inside jsonPath
        // YES WE CAN ! Because jsonPath is groovy , we can call groovy function
        // WAIT !! I DO NOT KNOW THE METHOD!!! --->> GOOGLE!!
        // I found If I add toLong() to the int value , it's turning it into long value
        // now we will try it out.

    }


    @DisplayName("Testing GET /Spartans/{id} by getting the RANDOM id from DB")
    @Test
    public void testDataFromDB_Randomly() {

        String myQuery = "SELECT * FROM SPARTANS ORDER BY SPARTAN_ID DESC";

        DB_Utility.runQuery(myQuery);

        // we want to get the rowNum for below method randomly from 1 till the last row count
        // so I can always get different data for my test
        // so first I need to get the row count so I can set the max of my random number

        int rowCount = DB_Utility.getRowCount();

        // get a random number from 1 to rowCount value
        int randomRowNum = new Faker().number().numberBetween(1, rowCount);

        Map<String, String> randomRowMap = DB_Utility.getRowMap(randomRowNum);

        System.out.println("CURRENT ROW IS = " + randomRowNum);
        System.out.println("CURRENT ROW data IS = " + randomRowMap);

    }

}