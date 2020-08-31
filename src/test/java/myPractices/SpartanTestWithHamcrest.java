package myPractices;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class SpartanTestWithHamcrest {

    @BeforeAll
    public static void init(){
        baseURI = "http://3.85.141.124:8000";
    }

    /*
    Given accept type is Json
    And path param id is 15
    When user send a get request to spartans/{id}
    Then status code is 200
        {
            "id": 15,
            "name": "Meta",
            "gender": "Female",
            "phone": 1938695106
        }
     */

    @Test
    public void test1(){
        given().accept(ContentType.JSON)
        .pathParam("id", 15)
        .when().get("/api/spartans/{id}")
        .then().statusCode(200).and()
        .assertThat().contentType("application/json;charset=UTF-8");
    }


    @Test
    public void test2(){
        given().accept(ContentType.JSON)
        .pathParam("id", 15)
        .when().get("/api/spartans/{id}")           // untill here is send request
        .then().assertThat().statusCode(200)           // here starts assertions with hamcrest.MATCHERS
        .and().assertThat().contentType("application/json;charset=UTF-8")
        .and().assertThat().body( "id", equalTo(15),
                            "name", equalTo("Meta"),
                                    "gender", equalTo( "Female"),
                                    "phone", equalTo(1938695106)
        );

    }





}
