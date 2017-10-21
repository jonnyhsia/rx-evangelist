package com.citytogo.jonnyhsia.rxevangelist;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by JonnyHsia on 17/10/21.
 */

public class Preferences {

    private static final String USERNAME = "username";

    public static String getUsername(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(USERNAME, "");
    }

    public static void setUsername(Context context, String username) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(USERNAME, username)
                .apply();
    }
}
