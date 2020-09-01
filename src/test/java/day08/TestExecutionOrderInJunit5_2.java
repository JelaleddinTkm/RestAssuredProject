package day08;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestExecutionOrderInJunit5_2 {

    @Order(4)
    @Test
    public void testB(){
    }

    @Order(2)
    @Test
    public void testA(){
    }

    @Order(3)
    @Test
    public void testZ(){
    }

    @Order(1)
    @Test
    public void testK(){
    }
}
