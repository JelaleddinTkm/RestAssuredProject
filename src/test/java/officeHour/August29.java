package officeHour;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static io.restassured.RestAssured.*;

public class August29 {

    public static void main(String[] args) {

        String URL = "http://api.zippopotam.us/us/90210";

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter country code: ");
        String countryCode = scanner.next();

        System.out.print("\nEnter postal code: ");
        String postalCode = scanner.next();

        //pattern checker, to check if it matches only following format
        // \d+ - string can contain only digits of any length
        if (!postalCode.matches("\\d+")) {
            throw new RuntimeException("Wrong postal code");
        }

        baseURI = "http://api.zippopotam.us";

        Response response =

        given().
                pathParam("country", countryCode).
                pathParam("zip", postalCode).
        when().
                get("/{country}/{zip}");

        response.then().log().ifError();

        String countryValue = response.jsonPath().getString("country");

        System.out.println("Here is what I found:");
        System.out.println("Country: " + countryValue);

        List<Map<String, Object>> places = response.jsonPath().getList("places");
        System.out.println("List of places:\n");

        for (Map<String, Object> place : places) {
            place.forEach((key, value) -> {
                System.out.println(key + ": " + value);
            });

            System.out.println();
        }
    }

}
