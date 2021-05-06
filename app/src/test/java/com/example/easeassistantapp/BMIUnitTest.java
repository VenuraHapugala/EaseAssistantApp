package com.example.easeassistantapp;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class BMIUnitTest {
    private BMIhealthPage bmIhealthPage;

    @Before
    public void setup(){
        bmIhealthPage=new BMIhealthPage();
    }

    @Test
    public void testMultiplication(){
        float result = bmIhealthPage.multiply(2,2);
        assertEquals(4.0,result,0.000001);
    }

    @Test
    public void testDivision(){
        float result = bmIhealthPage.divide(12,2);
        assertEquals(6.0,result,0.000001);
    }
}
