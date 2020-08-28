package myPractices;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SpartanTestWithPathMethod {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://3.85.141.124" ;
        RestAssured.port = 8000 ;

    }


    @Test
    public void test1(){
    Response response =
        given()
                .accept(ContentType.JSON)
                .pathParam("id", 10).
        when()
                .get("api/spartans/{id}");

        response.body().prettyPrint();

        assertEquals(response.statusCode(), 200);

        assertEquals(response.contentType(), "application/json;charset=UTF-8");

        System.out.println("id : " + response.body().path("id").toString());
        System.out.println("name : " + response.body().path("name").toString());
        System.out.println("gender : " + response.body().path("gender").toString());
        System.out.println("phone : " + response.body().path("phone").toString());

        int id = response.body().path("id");
        String name = response.body().path("name");
        String gender = response.path("gender");
        int phone = response.path("phone");

        assertEquals(id, 10);
        assertEquals(name, "Dowran");
        assertEquals(gender, "Female");
        assertEquals(phone, 1234567890);

    }

}
