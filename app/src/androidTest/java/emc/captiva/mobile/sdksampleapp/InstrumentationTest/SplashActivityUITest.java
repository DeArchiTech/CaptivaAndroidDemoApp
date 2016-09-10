package emc.captiva.mobile.sdksampleapp.InstrumentationTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import emc.captiva.mobile.sdksampleapp.CreateFilterProfileActivity;


/**
 * Created by david on 9/9/16.
 */
@RunWith(AndroidJUnit4.class)
public class SplashActivityUITest {

    @Rule
    public ActivityTestRule<CreateFilterProfileActivity> mActivityRule =
            new ActivityTestRule<>(CreateFilterProfileActivity.class);

    @Test
    public void loadingCookies() {

        //Todo Implement

    }
}
