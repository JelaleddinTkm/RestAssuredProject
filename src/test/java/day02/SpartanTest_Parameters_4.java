package day02;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.*;


public class SpartanTest_Parameters_4 {

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = "http://3.85.141.124:8000";
        RestAssured.basePath = "/api";
    }


    @DisplayName("Testing /spartans/{id}")
    @Test
    public void testSingleSpartan(){

        given()
                .log().all()
                .pathParam("id", 102).
        when()
                .get("/spartans/{id}").
        then()
                .statusCode( is(200) );
    }



    @DisplayName("Testing2 /spartans/{id}")
    @Test
    public void testSingleSpartan2(){

        given()
                .log().all().
        when()
                .get("/spartans/{id}" , 102).
        then()
                .statusCode( is(200) );
    }



    @DisplayName("Testing2 /spartans/{id} Body")
    @Test
    public void testSingleSpartanBody(){

        given()
                .log().all()
                .pathParam("id", 102).

        when()
                .get("/spartans/{id}" ).

        then()
                .log().all()
                .statusCode( is(200) )
//                .body("JSON PATH", is("THE VALUE"));
                .body("id",     is(102))
                .body("name",   is("Dowran Updated"))
                .body("gender", is("Male"))
                .body("phone", is(7777777777L))
        ;
    }

}
