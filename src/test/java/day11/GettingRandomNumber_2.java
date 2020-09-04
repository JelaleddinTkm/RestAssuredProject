package day11;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GettingRandomNumber_2 {

    @Test
    public void testRandom(){

        // Random class is coming from java.util package
        // and can provide some random numbers in different type
        // first we need to create an object

        Random r = new Random();

        // This will give us random number from 0-5

        int randomNumber = r.nextInt(5);

            System.out.println("Random number is = " + randomNumber);


        List<String> names = Arrays.asList("Kakilik", "Maral", "Jeren", "Keyik", "Towshan", "Tawus") ;

            System.out.println("Lucky name is = " + names.get(randomNumber) );
    }
}
