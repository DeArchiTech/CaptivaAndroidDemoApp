package emc.captiva.mobile.sdksampleapp.InstrumentationTest;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import emc.captiva.mobile.sdksampleapp.MainActivity;
import emc.captiva.mobile.sdksampleapp.R;

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

        onView(ViewMatchers.withId(R.id.takepicturebtn))
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
        onView(withId(R.id.CreateFilterProfile))
                .check(ViewAssertions.matches(ViewMatchers.withText(R.string.MainPage_CreateFilterProfile)));
    }

    @Test
    public void loginLogutTest(){

        try{

        //1)Click Log In
        onView(withId(R.id.LoginButton)).perform(click());

        Thread.sleep(1500);

        //2)Click Ok on the alert dialog
        onView(withId(android.R.id.button1)).perform(click());

        //3)Click Log Out
        onView(withId(R.id.LogoutButton)).perform(click());

        Thread.sleep(1500);

        //2)Click Ok on the alert dialog
        onView(withId(android.R.id.button1)).perform(click());

        Thread.sleep(1500);

        }catch (Exception e){

        }
    }

    @Test
    public void fileStackUploadClick(){

        //1)Click file stack upload
        onView(withId(R.id.FileStackUpload)).perform(click());

        try{
            Thread.sleep(1500);
        }catch(Exception e){

        }

        //2)Click Ok on the alert dialog
        onView(withId(android.R.id.button1)).perform(click());

    }

    @Test
    public void captivaImageUploadTest(){

        try{

            //1)Click Log In
            onView(withId(R.id.LoginButton)).perform(click());

            Thread.sleep(1500);

            //2)Click Ok on the alert dialog
            onView(withId(android.R.id.button1)).perform(click());

            //3)Second click on Captiva Upload button
            onView(withId(R.id.CaptivaUpload)).perform(click());

            Thread.sleep(1500);

            //4)Click Ok on the alert dialog
            onView(withId(android.R.id.button1)).perform(click());

            Thread.sleep(1500);

        }catch (Exception e){

        }

    }

    @Test
    public void createProfileButtonTest(){

        try{
            //1)Click Log In
            onView(withId(R.id.LoginButton)).perform(click());

            Thread.sleep(1500);

            //2)Click Ok on the alert dialog
            onView(withId(android.R.id.button1)).perform(click());

            //3)Second click on Captiva Upload button
            onView(withId(R.id.CaptivaUpload)).perform(click());

            //1)Click Create Profile Button
            onView(withId(R.id.CreateFilterProfile)).perform(click());

            Thread.sleep(1500);

        }catch (Exception e){

        }

    }

}