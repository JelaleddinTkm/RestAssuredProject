package day10;

import org.junit.jupiter.api.Test;
import pojo.Catagory;

public class LombokTest {

    @Test
    public void test(){

        Catagory c1 = new Catagory("12", "abc");

            System.out.println("c1 = " + c1);

    }
}
