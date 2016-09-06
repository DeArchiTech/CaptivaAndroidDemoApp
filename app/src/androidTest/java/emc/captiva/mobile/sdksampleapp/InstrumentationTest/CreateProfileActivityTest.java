package emc.captiva.mobile.sdksampleapp.InstrumentationTest;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import emc.captiva.mobile.sdksampleapp.CreateFilterProfileActivity;
import emc.captiva.mobile.sdksampleapp.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.action.ViewActions.click;
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

        onView(ViewMatchers.withId(R.id.CreateProfileTitle))
                .check(ViewAssertions.matches(ViewMatchers.withText(R.string.CreateProfilePage_Title)));
        onView(withId(R.id.CreateProfileButton))
                .check(ViewAssertions.matches(ViewMatchers.withText(R.string.CreateProfilePage_CreateBtn)));

    }

    @Test
    public void listViewHasString(){
        onView(withText("Image Label")).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void buttonTest(){

        try{
            onView(withId(R.string.CreateProfilePage_CreateBtn)).perform(click());

            Thread.sleep(1500);

            onView(withId(android.R.id.button1)).perform(click());

        }catch(Exception e){

        }
    }

}
