package com.citytogo.jonnyhsia.rxevangelist;

import android.app.Application;

/**
 * Created by JonnyHsia on 17/10/21.
 * 全局获取 Context
 */
public class App extends Application {

    public static Application sApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();

        sApplication = this;
    }

    public static Application getInstance() {
        return sApplication;
    }
}
