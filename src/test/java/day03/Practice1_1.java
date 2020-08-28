package day03;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;


public class Practice1_1 {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://3.85.141.124" ;
        RestAssured.port = 8000 ;
        RestAssured.basePath = "/api" ;
    }



    @DisplayName("simle test")
    @Test
    public void test1() {

        given()
                .log().all()
                .queryParam("gender", "Female").
        when()
                .get("/spartans/search").
        then()
                .statusCode(200) ;
    }




}
