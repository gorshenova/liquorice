package com.liquorice.app.android.core;

import android.app.Application;

/**
 * Created by eyablonskaya on 9/23/2016.
 */

public class LiquoriceStartApplication extends Application {

    private static LiquoriceStartApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance =  this;
    }

    public static LiquoriceStartApplication getApplication(){
        return instance;
    }

    public static Application getContext(){
        return instance;
    }
}
