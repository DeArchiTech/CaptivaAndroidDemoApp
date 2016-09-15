package emc.captiva.mobile.sdksampleapp;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Davix on 9/15/16.
 */

public class Revaapplication extends Application {

    @Override public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }

}
