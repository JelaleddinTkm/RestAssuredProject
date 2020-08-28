package day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class PutRequestExample_6 {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://3.85.141.124" ;
        RestAssured.port = 8000 ;
        RestAssured.basePath = "/api" ;
    }


    @DisplayName("Updating Existing Data")
    @Test
    public void updateSpartan(){

        String updatedBody = "{\n" +
                "        \"id\": 112,\n" +
                "        \"name\": \"Beautiful Mary\",\n" +
                "        \"gender\": \"Female\",\n" +
                "        \"phone\": 1234567890\n" +
                "    }" ;

        given()
                .contentType(ContentType.JSON)
                .body(updatedBody)
                .log().all().
        when()
                .put("/spartans/{id}", 112).

         then()
                .log().all()
                .statusCode(204)
         ;
    }



    @Test
    public void testDelete(){

        when()
                .delete("/spartans/{id}",112).
                then()
                .statusCode(204)

        ;

    }

// Please create another test
// make a post request , store the response Object
// save the id into int variable
// save the name into String
// print them out.

    @DisplayName("Practice extracting data")
    @Test
    public void postRequestExtractingData(){
    }


}
