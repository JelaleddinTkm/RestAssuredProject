package day03;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class SpartansSearchExtractData_4 {

    /**
     * Please add new class called SpartanSearchExtractData
     * 		Add all the imports
     * 		add @BeforeAll to initilize the baseURI, basePath
     * 		Add a test
     * 		send get request to /spartan/search
     * 		query paramters gender = Female
     * 		save the response Object into response variable
     * 		call jsonPath method to get JsonPath object from respone
     * 		JsonPath jp = response.jsonPath() ;
     * 		// get the list of all IDs store it into List
     * 		// get the list of all names store it into List of String
     * 		// get the mumberOfElements field value
     */

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://3.85.141.124" ;
        RestAssured.port = 8000 ;
        RestAssured.basePath = "/api" ;
    }


    @DisplayName("simle test")
    @Test
    public void test1() {

        Response response =

        given()
                .log().all()
                .queryParam("gender","Female").
                        when()
                .get("/spartans/search")
                .prettyPeek() ;

        JsonPath jp = response.jsonPath();

        // get the value of the numberOfElements from the response body
        int numOfFemaleSpartans = jp.getInt("numberOfElements");
        System.out.println("numOfFemaleSpartans = " + numOfFemaleSpartans);

        // if you wanted to get single Spartan , for Example the first one id
        // you would use jsonPath of the content[0].id

        int firstID = jp.getInt("content[0].id");
        System.out.println("firstID = " + firstID);
        // if you want to get al the ids , You can use getList method and remove the index
        //  content.id  for the id , content.name

        // storing all ids into list of integer
        List<Integer> numList = jp.getList("content.id");
        System.out.println("numList = " + numList);
        List<String> nameList = jp.getList("content.name");
        System.out.println("nameList = " + nameList);


    }

}
