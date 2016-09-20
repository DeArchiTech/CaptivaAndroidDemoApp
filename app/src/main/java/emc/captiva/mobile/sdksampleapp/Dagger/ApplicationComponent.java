package emc.captiva.mobile.sdksampleapp.Dagger;

import javax.inject.Singleton;

import dagger.Component;
import emc.captiva.mobile.sdksampleapp.MainActivity;

/**
 * Created by Davix on 9/19/16.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MainActivity activity);
}
