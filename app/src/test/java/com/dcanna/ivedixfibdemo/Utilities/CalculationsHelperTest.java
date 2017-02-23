package com.dcanna.ivedixfibdemo.Utilities;

import org.junit.Test;

import static com.dcanna.ivedixfibdemo.Utilities.Constants.realFibonacciNums;
import static junit.framework.Assert.assertTrue;


/**
 * Created by dcanna on 2/23/17.
 *
 * this class is used to test that the Fibonacci number methods both return the proper values
 */
public class CalculationsHelperTest {



    @Test
    public void getFibonacciLoop() throws Exception {

        int index = 0;
        for(int i : realFibonacciNums){
            assertTrue(CalculationsHelper.getFibonacciLoop(index++) == i);
        }
    }

    @Test
    public void getFibonacciRecursive() throws Exception {

        int index = 0;
        for(int i : realFibonacciNums){
            assertTrue(CalculationsHelper.getFibonacciLoop(index++) == i);
        }
    }

}