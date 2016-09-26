package com.liquoriceutils.utils;

import android.app.Application;

/**
 * Created by eyablonskaya on 9/23/2016.
 */

public class LiquoriceApplication extends Application {

    private static LiquoriceApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance =  this;
    }

    public static Application getContext(){
        return instance;
    }

    public static LiquoriceApplication getApplication(){
        return instance;
    }
}
