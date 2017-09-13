package com.cc.solartest;

import android.app.Application;

/**
 * Created by silvercc on 17/9/12.
 */

public class SolarApplication extends Application {
    private static SolarApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static SolarApplication getInstance() {
        return mInstance;
    }
}
