package com.dcanna.ivedixfibdemo.Utilities;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dcanna on 2/23/17.
 *
 *      * This test can be used to ensure that the FibonacciNumberCruncher
 * returns proper values
 */
public class FibonacciNumberCruncherTest {

    @Test
    public void run() throws Exception {

        /*
         * loop through all possible values defined in Constants.Java
         * and ensure they return valid values
         */
        for(int i = Constants.MIN_INDEX; i <= Constants.MAX_INDEX; i++){

            FibonacciNumberCruncher fibonacciNumberCruncher = new FibonacciNumberCruncher(i,
                    new CompletionCloser() {
                        @Override
                        public void onNumberCalculated(String strVal) {
                            //empty callback
                        }
                    });

            fibonacciNumberCruncher.run();
            /*
             * assert it at least has a value
             */
            assertTrue(!fibonacciNumberCruncher.mFibNumberToReturn.isEmpty());
            /*
             * assert that value is non negative
             */
            assertTrue(Integer.parseInt(fibonacciNumberCruncher.mFibNumberToReturn) >= 0);
            /*
             * assert it at least has a value
             */
            assertTrue(Integer.parseInt(fibonacciNumberCruncher.mFibNumberToReturn) <= 55);
        }

    }

}