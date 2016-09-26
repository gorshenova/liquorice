package com.eg.utils;

import android.app.Application;

/**
 * Created by eyablonskaya on 9/23/2016.
 */

public class UtilsApplication extends Application {

    private static UtilsApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance =  this;
    }

    public static Application getContext(){
        return instance;
    }

    public static UtilsApplication getApplication(){
        return instance;
    }
}
