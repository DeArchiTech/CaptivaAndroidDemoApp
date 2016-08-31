package emc.captiva.mobile.sdksampleapp;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest{

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void allButtonsViewAreThere(){

        onView(withId(R.id.takepicturebtn))
                .check(ViewAssertions.matches(ViewMatchers.withText(R.string.MainPage_TakePicture)));
        onView(withId(R.id.takecontinuouspicturesbtn))
                .check(ViewAssertions.matches(ViewMatchers.withText(R.string.MainPage_TakeContinuousPictures)));
        onView(withId(R.id.SettingsButton))
                .check(ViewAssertions.matches(ViewMatchers.withText(R.string.MainPage_Settings)));
        onView(withId(R.id.enhancepicturebtn))
                .check(ViewAssertions.matches(ViewMatchers.withText(R.string.MainPage_EnhanceImage)));


        //Self Added Buttons
        onView(withId(R.id.LoginButton))
                .check(ViewAssertions.matches(ViewMatchers.withText(R.string.MainPage_Login)));
        onView(withId(R.id.FileStackUpload))
                .check(ViewAssertions.matches(ViewMatchers.withText(R.string.MainPage_FileStackUpload)));
        onView(withId(R.id.CaptivaUpload))
                .check(ViewAssertions.matches(ViewMatchers.withText(R.string.MainPage_CaptivaUpload)));
        onView(withId(R.id.LogoutButton))
                .check(ViewAssertions.matches(ViewMatchers.withText(R.string.MainPage_Logout)));
    }

    @Test
    public void loginButtonClick(){

        onView(withId(R.id.LoginButton)).perform(click());

        //2)Click Ok on the alert dialog
        onView(withId(android.R.id.button1)).perform(click());
    }

    @Test
    public void fileStackUploadClick(){

        onView(withId(R.id.FileStackUpload)).perform(click());

    }

    @Test
    public void captivaImageUploadTest(){

        //1)First click login button
        onView(withId(R.id.LoginButton)).perform(click());

        //2)Click Ok on the alert dialog
        onView(withId(android.R.id.button1)).perform(click());

        //3)Second click on Captiva Upload button
        onView(withId(R.id.CaptivaUpload)).perform(click());

        //4)Click Ok On the alert dialog
        //onView(withId(android.R.id.button1)).perform(click());


    }

    @Test
    public void logoutButtonClick(){

        onView(withId(R.id.LogoutButton)).perform(click());

    }

    private static int getResourceId(String s) {
        Context targetContext = InstrumentationRegistry.getTargetContext();
        String packageName = targetContext.getPackageName();
        return targetContext.getResources().getIdentifier(s, "id", packageName);
    }
}