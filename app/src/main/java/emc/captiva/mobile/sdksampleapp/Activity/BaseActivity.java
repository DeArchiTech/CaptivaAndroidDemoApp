package emc.captiva.mobile.sdksampleapp.Activity;

import android.app.Activity;
import android.os.Bundle;

import emc.captiva.mobile.sdksampleapp.Dagger.ApplicationComponent;
import emc.captiva.mobile.sdksampleapp.Dagger.Revaapplication;
import icepick.Icepick;

/**
 * Created by Davix on 9/20/16.
 */

public class BaseActivity extends Activity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    public ApplicationComponent getApplicationComponent() {
        return ((Revaapplication) getApplication()).getApplicationComponent();
    }

}
