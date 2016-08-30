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
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest{

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void allButtonsViewAreThere(){

        //TODO: Figure out how to write to check a label exists on an espresso testa
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
    }

    @Test
    public void loginButtonClick(){

        onView(withId(R.id.LoginButton)).perform(click());
    }

    @Test
    public void fileStackUploadClick(){

        onView(withId(R.id.FileStackUpload)).perform(click());

    }

    @Test
    public void captivaUploadClick(){

        onView(withId(R.id.id_quadcropbackbutton)).perform(click());

    }


//    @Test
//    public void buttonShouldUpdateText(){
//
//        onView(withId(getResourceId("Click"))).check(matches(withText("Done")));
//    }

    private static int getResourceId(String s) {
        Context targetContext = InstrumentationRegistry.getTargetContext();
        String packageName = targetContext.getPackageName();
        return targetContext.getResources().getIdentifier(s, "id", packageName);
    }
}