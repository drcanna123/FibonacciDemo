package com.dcanna.ivedixfibdemo.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by dcanna on 2/22/17.
 *
 * This is a helper class similar to one I like to use to move the logic of setting and getting
 * Shared preferences for when I need to persist data across sessions
 */

public class PersistantPrefs {

    /*
     * Keys for Shared Preferences values
     */
    private static final String KEY_CURRENT_INDEX = "persist_current_index";
    private static final String KEY_SP_FILENAME = "shared_pref_filename";

    public static void setCurrentCount(Context app, int value){
        SharedPreferences prefs = app.getSharedPreferences(KEY_SP_FILENAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_CURRENT_INDEX, value);
        editor.apply(); //.apply() saves in background instead of .commit() on UIThread

    }

    public static int getCurrentCount(Context app){
        return app.getSharedPreferences(KEY_SP_FILENAME,Context.MODE_PRIVATE).getInt(KEY_CURRENT_INDEX,0);
    }

}
