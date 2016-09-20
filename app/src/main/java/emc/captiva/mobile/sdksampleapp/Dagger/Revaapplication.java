package emc.captiva.mobile.sdksampleapp.Dagger;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Davix on 9/15/16.
 */

public class Revaapplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        initializeInjector();
    }

    private void initializeInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
