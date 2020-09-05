package day11;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;


public class JsonSchemaValidation_3 {

    // please create a file called singleSpartanSchema.json
    // under src/test/resources
    // add the schema content I shared in code note

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan1.base_url");
        RestAssured.basePath = "/api";
    }

    @Test
    public void testSchema(){

        given()
                .log().uri().
        when()
                .get("/spartans/{id}", 55)
                .prettyPeek().
        then()
                .body( matchesJsonSchemaInClasspath("singleSpartanSchema.json") ) ;

    }

}
