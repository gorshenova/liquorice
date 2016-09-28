package com.liquoriceutils.core;

import android.app.Application;
import android.content.pm.PackageManager;

import com.liquoriceutils.LiquoriceHidingUtils;
import com.liquoriceutils.helpers.log.Logger;

/**
 * Created by eyablonskaya on 9/23/2016.
 */

public class LiquoriceApplication extends Application {

    private static LiquoriceApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance =  this;

        try {
            useBuildConfigHiding(getPackageName() + "/" + String.valueOf(getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0).versionCode));
        } catch (PackageManager.NameNotFoundException e) {
            Logger.error(LiquoriceApplication.class, e.getMessage(), e);
        }
    }

    public static Application getContext(){
        return instance;
    }

    public static LiquoriceApplication getApplication(){
        return instance;
    }

    /**
     * 'Encrypt' a message using the password we injected using {@link BuildConfig}.
     *
     * @param message The message we want to hide or unhide
     */
    public void useBuildConfigHiding(String message){
        byte[] msg = message.getBytes();
        byte[] pwd = BuildConfig.loggerEncryptionPasswordKey.getBytes();
        LiquoriceHidingUtils.doHiding(msg, pwd, false);
    }
}
