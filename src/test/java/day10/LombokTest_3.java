package day10;

import org.junit.jupiter.api.Test;
import pojo.Catagory;
import pojo.Countries;

public class LombokTest_3 {

    @Test
    public void test(){

        Catagory c1 = new Catagory("12", "abc");
            System.out.println("c1 = " + c1);

        Countries ar = new Countries("AR", "ARGENTINA",2);
            System.out.println("ar = " + ar);
    }
}
