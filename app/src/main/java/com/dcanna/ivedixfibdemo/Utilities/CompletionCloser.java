package com.dcanna.ivedixfibdemo.Utilities;

/**
 * Created by dcanna on 2/22/17.
 *
 * This interface is used to communicate between
 * the MainActivity and the Runnable that handles the
 * Fibonacci compilation
 *
 * Using this interface between those 2 classes help to
 * separate the concerns of the given classes
 *
 */

public interface CompletionCloser {
    void onNumberCalculated(String strVal);
}
