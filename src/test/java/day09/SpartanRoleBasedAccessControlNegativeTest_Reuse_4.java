package day09;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;



public class SpartanRoleBasedAccessControlNegativeTest_Reuse_4 {


    @BeforeAll
    public static void init(){

//we are using Akbar's ip address because it is with basic auth,
// and my ip is with no auth, so i will not be able to complete the test with my own

        RestAssured.baseURI = "http://54.160.106.84";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";
    }


    @DisplayName("User should not be able to delete data")
    @Test
    public void testUserCanNotDeleteData(){

        // building reusable request specification
        // using RequestSpecBuilder class

        RequestSpecification requestSpec = given()
                .auth().basic("user", "user")
                .accept(ContentType.JSON)
                .log().all() ;

        // Extracting ResponseSpecification for all assertions so we can reuse
        // We will be using a class called ResponseSpecBuilder
        ResponseSpecBuilder resSpecBuilder = new ResponseSpecBuilder() ;

        // Getting the reusable ResponseSpecification object using the builder methods chaining
        ResponseSpecification responseSpec = resSpecBuilder.expectStatusCode(403)
                                              .expectContentType(ContentType.JSON)
                                              .expectHeader("Date", notNullValue(String.class) )
                                              .build() ;

        // expectHeader second argument expect a Matcher<String>
        // but notNullValue() return a Matcher<Object> so it did not compile
        // so we used the second version of notNullValue( The Matcher type inside <>)
        // to specify what kind of matcher we wanted



        given()
                .spec(requestSpec).
        when()
                .delete("/spartans/{id}", 10).
        then()
                .spec(responseSpec) ;
    }



}
