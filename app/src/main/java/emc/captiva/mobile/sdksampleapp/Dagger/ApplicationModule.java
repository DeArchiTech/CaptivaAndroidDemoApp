package emc.captiva.mobile.sdksampleapp.Dagger;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import emc.captiva.mobile.sdksampleapp.Util.RealmUtil;
import io.realm.Realm;

/**
 * Created by Davix on 9/19/16.
 */
@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application.getApplicationContext();
    }

    @Provides
    Realm getRealm(){
        return new RealmUtil().createRealm(this.application.getApplicationContext());
    }

}
