package com.citytogo.jonnyhsia.rxevangelist.helper;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;

/**
 * Created by JonnyHsia on 17/10/22.
 */
public class Kits {


    public static class UI {

        public static boolean isScreenOrientationPortrait(@NonNull Context context) {
            return context.getResources().getConfiguration().orientation
                    == Configuration.ORIENTATION_PORTRAIT;
        }
    }
}
