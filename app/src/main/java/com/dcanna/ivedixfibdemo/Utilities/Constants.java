package com.dcanna.ivedixfibdemo.Utilities;

/**
 * Created by dcanna on 2/23/17.
 *
 * This class only holds final constants that we know will not change during development
 * and keeps them in one place to make it easier and self explanatory to change later
 */

public class Constants {
    /*
     * defines the time delay for recurring task
     */
    public static final int DELAY_MILLIS = 1000;

    /*
     * defines the max index to reach before counting down
     */
    public static final int MAX_INDEX = 10;

    /*
     * defines the min index to start
     * or
     * to reach before counting up
     */
    public static final int MIN_INDEX = 0;

    /*
     * this is the set of known fibonacci numbers to be
     * used for testing
     */
    public static final int[] realFibonacciNums = new int[]{
            0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55
    };
}
