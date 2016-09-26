package com.rocketstart.app.android;

import android.app.Application;

/**
 * Created by eyablonskaya on 9/23/2016.
 */

public class RocketStartApplication extends Application {

    private static RocketStartApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance =  this;
    }

    public static RocketStartApplication getApplication(){
        return instance;
    }

    public static Application getContext(){
        return instance;
    }
}
