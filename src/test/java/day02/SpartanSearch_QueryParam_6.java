package day02;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class SpartanSearch_QueryParam_6 {

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = "http://3.85.141.124:8000" ;
        RestAssured.basePath = "/api" ;
    }


    // http://3.85.141.124:8000/api/spartans/search?gender=male&nameContains=ea

    @DisplayName("Testing /spartans/search Endpoint")
    @Test
    public void testSearch() {

        given()
                .log().all()
                .queryParam("gender", "Male")
                .queryParam("nameContains", "ea").
        when()
//                .get("/spartans/search?gender=male&nameContains=ea").
                  .get("/spartans/search"). // if we use queryParam above, then we should write like this
        then()
                .log().all()
                .statusCode(200)
                .body("numberOfElements", is(3))
        ;

    }



}
