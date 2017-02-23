package com.dcanna.ivedixfibdemo.Activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import com.dcanna.ivedixfibdemo.Utilities.CompletionCloser;
import com.dcanna.ivedixfibdemo.Utilities.Constants;
import com.dcanna.ivedixfibdemo.Utilities.FibonacciNumberCruncher;
import com.dcanna.ivedixfibdemo.Utilities.PersistantPrefs;
import com.dcanna.ivedixfibdemo.R;

/**
 * Created by dcanna on 2/22/17.
 *
 *  Ivedix Demonstration Application
 *
 *      *This app was designed based off the following requirments.
 *
 *
 *	1.	Design a program to generate the nth Fibonacci number for a given index.  Include the following:
 *
 *              // I created the FibonacciNumberCruncher and the CompletionCloser interfaces for this
 *      a.	An interface / protocol defining a method that takes an index as a parameter and returns the Fibonacci number for that index.
 *
 *              // These are located in the CalculationsHelper
 *      b.	Two different implementations of that method.
 *
 *              // These are located the Test package
 *      c.	Test cases that cover the interface and implementation.
 *
 * // These requirements are completed, Code should be well documented below. Have fun...
 *
 *  2.	Build an Android app to display the Fibonacci numbers.  It has the following requirements:
 *      a.	The app should launch and display the Fibonacci number at index 0.
 *      b.	Every second that elapses, the app should increment the index and show the next corresponding Fibonacci number.
 *      c.	After reaching F(10) decrement the index each second until reaching F(0).
 *      d.	The app should then begin counting up again, and so on.
 *      e.	If the app goes into the background it should remember the last number displayed and should resume where it left off upon entering the foreground.
 *      f.	Use the implementation in #1 to generate the numbers. 
 *       
 *      The Fibonacci sequence from [0,10] is (0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55).
 *       
 *      Use best design, development, conventions, and methodology per your knowledge, and document the thought process / decisions. 
 *
 */

public class MainActivity extends Activity implements CompletionCloser {

//region Variable Declarations

    /*
     * Instantiate all member variables, and precede the name with 'm' for member
     * followed by camel case for best practices
     *
     * non defined variables come first for readability
     *  followed defined variables
     */
    private TextView mFibNum,mIndexLabel;
    private Handler mUIHandler;
    private int mFibIndex;


    private boolean mCountUp = true;

    /*
     * used only when logging during development
     */
    //private final String TAG = this.getClass().getSimpleName();

    /*
     * using application context instead of activity helps to make sure
     * your not keeping any references to activities or fragments when they
     * are not needed, we are not using fragments or multiple activities
     * in this app, but it is a habit I like to maintain
     */
    private Context mAppContext;
//endregion
//region Lifecycle Callbacks

    /*
     * I tend to do my set up in OnCreate()
     * and the actual heavy lifting in onResume()
     * depending on the application's needs
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
         * There are 2 activity_main.xml files
         * for both portrait and landscape by way of
         * configuration qualifiers in the /res directory
         *
         * Most apps I have written have a specified orientation
         * so I would normally use below line of code to avoid
         * creating multiple layout files
         *
         *          setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
         *
         */
        setContentView(R.layout.activity_main);

        /*
         * define UI elements first
         */
        mFibNum = (TextView)findViewById(R.id.txt_fibNum);
        mIndexLabel = (TextView)findViewById(R.id.txt_fibIndex);

        /*
         * define class member variables
         */
        mAppContext = getApplicationContext();
        mFibIndex = 0;

        /*
         * define worker member variables
         */
        mUIHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*
         * heavy lifting done in onResume
         * the Handler is kicked off immediately
         * to run the FibonacciNumberCruncher task
         *
         * The 1 second delay will come on next iteration.
         */
        mUIHandler.post(new FibonacciNumberCruncher(mFibIndex,this));
        /*
         * if we were here before, the last index will be saved
         * in Shared Preferences, so we will make that the current
         * index, if not set already, 0 will be passed back
         *
         * **See /Utilities/Persitants.java for more info
         */
        mFibIndex = PersistantPrefs.getCurrentCount(mAppContext);
        //Log.d(TAG,String.format("starting at index :%s",mFibIndex));
    }

    @Override
    protected void onPause() {
        super.onPause();

        /*
         * remove Handler callbacks and Messages
         * during on pause to avoid memory leaks
         */
        mUIHandler.removeCallbacksAndMessages(null);

         /*
         * Save the current index now so it is remembered
         * whenever the user comes back to the app by using
         * Shared Preferences since they persist until uninstall
         */
        PersistantPrefs.setCurrentCount(mAppContext, mFibIndex);
    }

//endregion
//region Helper Methods
    /*
     * Using the mCountUp boolean member variable we will
     * see if we should count up or down, and then once the
     * Index is changed, we will check again and update for
     * next time
     */
    private void incrementOrDecrementFibIndex() {

        //Log.d(TAG,String.format("index before changed %s ",mFibIndex));

        /*
         * changed based on current mCountUp boolean
         */
        if(mCountUp){
            mFibIndex++;
        }else {
            mFibIndex--;
        }

        //Log.d(TAG,String.format("index after changed %s ",mFibIndex));

        /*
         * after changed index, define if we should
         * increase or decrease next time
         */
        if(mFibIndex >= Constants.MAX_INDEX){
            mCountUp = false;
        } else if(mFibIndex <= Constants.MIN_INDEX){
            mCountUp = true;
        }

        //Log.d(TAG,String.format("will count up next iteration: %b ",mCountUp));
    }
//endregion
//region Interface Callbacks

    /*
     * this is an override of the interface 'CompletionCloser'
     * that this Activity implements, in this implementation
     * it will update the textViews, increment the mFibIndex
     * interval, and then waits off the UI thread for 1 second
     * when it will create a new annonymous Fibonacci number
     * cruncher
     */
    @Override
    public void onNumberCalculated(String strVal) {

        /*
         * update the UI text fields
         */
        mFibNum.setText(strVal);
        mIndexLabel.setText(String.format("Index : %s", mFibIndex));
        //Log.d(TAG,String.format("index %s returned %s",mFibIndex,strVal));
        /*
         * deal with keeping index within 0-10
         */
        incrementOrDecrementFibIndex();

        /*
         * Repeat counting every second (1000 ms)
         *****************************************
         * Handler.postDelayed() will not block the ui thread
         */
        mUIHandler.postDelayed(
                new FibonacciNumberCruncher(mFibIndex,MainActivity.this),
                Constants.DELAY_MILLIS);
    }

//endregion
}
