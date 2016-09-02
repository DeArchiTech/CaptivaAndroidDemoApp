package emc.captiva.mobile.sdksampleapp;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by david on 9/2/16.
 */

@RunWith(AndroidJUnit4.class)
public class CreateProfileActivityTest {

    @Rule
    public ActivityTestRule<CreateFilterProfileActivity> mActivityRule =
            new ActivityTestRule<>(CreateFilterProfileActivity.class);


    @Test
    public void allViewsAreThere(){

        onView(withId(R.id.CreateProfileTitle))
                .check(ViewAssertions.matches(ViewMatchers.withText(R.string.CreateProfilePage_Title)));
        onView(withId(R.id.CreateProfileButton))
                .check(ViewAssertions.matches(ViewMatchers.withText(R.string.CreateProfilePage_CreateBtn)));

    }

}
