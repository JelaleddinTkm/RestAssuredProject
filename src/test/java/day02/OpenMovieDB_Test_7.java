package day02;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class OpenMovieDB_Test_7 {

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = "http://www.omdbapi.com" ;
        // RestAssured.basePath = "/api" ;
    }


    @DisplayName("Test Movie API")
    @Test
    public void testMovies(){

        given()
                .log().all()
                .queryParam("apikey", "26aa5b74")
                .queryParam("t", "The Boss Baby").
        when()
                .get("").             //if URL is already completed, DO NOTHING
        then()
                .log().all()
                .statusCode(200)
                .body("Title", containsString("The Boss Baby") )
                .body("Ratings[0].Value", is("6.3/10") )
                .body("Ratings[-1].Value", is("50/100"))
        ;
    }


    /*

    // make a request
	      by adding few query parameters like
	      apikey =  your APIKEY
	      t =  the movie you want to search
	      plot =  full
	      then status code 200
	      		contentype is json
	      		body
	      			title is what you are searching for
	      			year is as you expected
	      			first rating value
	      			last rating value


     */



}
