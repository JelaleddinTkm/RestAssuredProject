package day01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class Practice2_4 {

    @Test
    public void test1(){

        // assert 5+4 is 9
        int num1 = 5;
        int num2 = 4;

        // we need these two import for this to work
//        import static org.hamcrest.MatcherAssert.assertThat;
//        import static org.hamcrest.Matchers.*;
        // Hamcrest already come with RestAssured dependency


        // hamcrest library use the assertThat method for all assertions
        // hamcrest use built in matchers to do assertion
        // couple common ones are:
            // is( some value )
            // equalTo ( some value )
            // or optionally --> is( equalTo( some value ) )

        assertThat( num1 + num2 ,         is(  9)  );
        assertThat( num1 + num2 ,    equalTo(9)  );
        assertThat( num1 + num2 , is(equalTo(9) ));

        // not ( value )
        // is  ( not ( value ) )
        assertThat( num1 + num2 ,        not(  11)  );
        assertThat( num1 + num2 ,    is( not(  11) ));

        // save your first name and last name into 2 variables
        // test concatination result using hamcrest matcher

        String firstName = "Bayram";
        String lastName = "Pirjan";

        assertThat(firstName + lastName,          is (  " Bayram Pirjan") );
        assertThat(firstName + lastName,     equalTo (" Bayram Pirjan") );
        assertThat(firstName + lastName, is (equalTo (" Bayram Pirjan") ) );

        // String matchers
        // equalToIgnoringCase
        // equalToCompressingWhiteSpace
        //containsString, endsWith, startsWith - test string matching

        // how to check in caseInsensitive manner
        assertThat(firstName, equalToIgnoringCase("bayram") );

        // how to ignore all white spaces
        assertThat(firstName, equalToCompressingWhiteSpace("Bayram") );


    }




    @DisplayName("Support for collection")
    @Test
    public void test2() {

        List<Integer> numList = Arrays.asList(11, 44, 3, 55, 88, 5);

// checking the list size is 6
        assertThat(numList, hasSize(6)  );
// checking the list contains 11
        assertThat(numList, hasItem(11) );
// has items is used to check multiple items : 11, 3, 5
        assertThat(numList, hasItems(11,3,5) );
// checking the list contains all the items in exact order : 11,44,3,55,88,5
        assertThat(numList, contains(11,44,3,55,88,5 )   );
// checking the list contains all the items in any order  : 11, 44, 55,3,88
        assertThat(numList,  containsInAnyOrder(11, 44, 55,3,88,5 )   );


    }

}
