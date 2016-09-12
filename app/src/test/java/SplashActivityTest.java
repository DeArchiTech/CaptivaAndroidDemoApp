import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import emc.captiva.mobile.sdksampleapp.BuildConfig;
import emc.captiva.mobile.sdksampleapp.SplashActivity;

/**
 * Created by Davix on 9/12/16.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)

public class SplashActivityTest {

    private SplashActivity activity;
    @Before
    public void setUp(){
        this.activity = Robolectric.setupActivity(SplashActivity.class);
    }

    @Test
    public void shouldReturnTrue(){
        System.out.println("jooma");
    }


}
