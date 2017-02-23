package com.dcanna.ivedixfibdemo.Utilities;

/**
 * Created by dcanna on 2/22/17.
 *
 * This class has the sole purpose of containing the calls and logic
 * needed to determine any math problem we encounter during the application
 *
 *          ***This class has an associated Unit test***
 */

class CalculationsHelper {

    /*********************************************************
     * This is a loop implementation of the fibonacci sequence
     *********************************************************/
    public static int getFibonacciLoop(int index){
        if (index <= 1) {
            return index;
        }
        int fib1 = 1, fib2 = 1, fibNow = 1;
        for (int i = 3; i <= index; i++) {
            fibNow = fib1 + fib2; // Fibonacci number is sum of previous two Fibonacci number
            fib1 = fib2;
            fib2 = fibNow;

        }
        return fibNow;
    }

    /*********************************************************
     * This is a loop implementation of the fibonacci sequence
     *********************************************************/
    public static int getFibonacciRecursive(int index){

        if(index <= 1){
            return index;
        } else {
            return getFibonacciRecursive(index - 1) + getFibonacciRecursive(index -2);
        }
    }

}
