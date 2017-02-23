package com.dcanna.ivedixfibdemo.Utilities;

import static com.dcanna.ivedixfibdemo.Utilities.CalculationsHelper.getFibonacciLoop;
import static com.dcanna.ivedixfibdemo.Utilities.CalculationsHelper.getFibonacciRecursive;

/**
 * Created by dcanna on 2/22/17.
 *
 * This class does gets and sends the correct Fibonacci number
 * back to the given CompletionCloser, in this application
 * the MainActivity implements Completion closer so we notify the activity
 * through that interface
 *
 *
 *          ***This class has an associated Unit test***
 */

public class FibonacciNumberCruncher implements Runnable {

    private CompletionCloser mCloser;
    private int mIndex;

    /*
     * mFibNumberToReturn was only moved to a member variable for testing purposes...
     *
     * normally I would not create a managed object when it could easily be
     * an anonymous object passed into the completion closer interface to avoid
     * keeping a reference to any unneeded objects
     */
    String mFibNumberToReturn = "";

    /*
     * constructor to set member variables
     */
    public FibonacciNumberCruncher(int index, CompletionCloser closer) {
        mIndex = index;
        mCloser = closer;
    }

    @Override
    public void run() {
        /*
         * using both the methods that were requested
         */
        mFibNumberToReturn = String.valueOf(mIndex % 2 == 0 ?
                getFibonacciRecursive(mIndex) :
                getFibonacciLoop(mIndex));

        /*
         * this will notify the MainActivity
         * who will update ui elements on UI thread
         */
        mCloser.onNumberCalculated(mFibNumberToReturn);
    }
}
