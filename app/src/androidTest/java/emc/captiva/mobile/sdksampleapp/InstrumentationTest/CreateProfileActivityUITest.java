package emc.captiva.mobile.sdksampleapp.InstrumentationTest;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import com.google.dexmaker.dx.io.Code;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.stubbing.answers.ThrowsException;

import java.util.ArrayList;
import java.util.List;
import emc.captiva.mobile.sdksampleapp.CreateFilterProfileActivity;
import emc.captiva.mobile.sdksampleapp.ListItem.FilterListItem;
import emc.captiva.mobile.sdksampleapp.Model.Filter;
import emc.captiva.mobile.sdksampleapp.Model.FilterProfile;
import emc.captiva.mobile.sdksampleapp.Presenter.CreateProfilePresenter;
import emc.captiva.mobile.sdksampleapp.R;
import io.realm.Realm;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.clearText;
import static org.hamcrest.Matchers.anything;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

/**
 * Created by david on 9/2/16.
 */

@RunWith(AndroidJUnit4.class)
public class CreateProfileActivityUITest {

    @Mock
    TextView textView;

    @Mock
    CreateProfilePresenter presenter;

    List<FilterListItem> filterListItem;
    String testString = "ABCD";

    @Before
    public void setUp() throws Exception {

        textView = Mockito.mock(TextView.class);
        presenter = Mockito.mock(CreateProfilePresenter.class);
        this.testString = "ABCD";
        mActivityRule.getActivity().setPresenter(this.presenter);
        when(textView.getText()).thenReturn(testString);
    }

    @Rule
    public ActivityTestRule<CreateFilterProfileActivity> mActivityRule =
            new ActivityTestRule<>(CreateFilterProfileActivity.class);


    @Test
    public void allViewsAreThere() {

        onView(ViewMatchers.withId(R.id.createProfileTitle))
                .check(ViewAssertions.matches(ViewMatchers.withText(R.string.CreateProfilePage_Title)));
        onView(withId(R.id.CreateProfileButton))
                .check(ViewAssertions.matches(ViewMatchers.withText(R.string.CreateProfilePage_CreateBtn)));

        onView(withId(R.id.createProfileNameInput)).check(ViewAssertions.matches(isDisplayed()));

        onView(withId(R.id.autoApplyFilter)).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void listViewHasString() {
        onView(withText("Image Label")).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void testCreateProfileButtonClicked(){

        //No Input
        try{
            Espresso.closeSoftKeyboard();

            onView(withId(R.id.CreateProfileButton)).perform(click());

            Thread.sleep(500);

            onView(withId(android.R.id.button1)).perform(click());
        }catch(Exception e){

        }

    }

    @Test
    public void testInputProfileNameNotSet(){

        try{
            Espresso.closeSoftKeyboard();

            onData(anything()).inAdapterView(withId(R.id.filterListView)).atPosition(0).perform(click());

            Thread.sleep(500);

            onView(withId(R.id.CreateProfileButton)).perform(click());

            Thread.sleep(3000);

            onView(withId(android.R.id.button1)).perform(click());

        }catch(Exception e){

            System.out.println(e.getMessage());

        }

    }

    @Test

    public void testNoFilterItemsSelected(){

        try{
            Espresso.closeSoftKeyboard();

            String profileName = "Hakuna Matata";

            onView(withId(R.id.createProfileNameInput)).perform(click()).perform(clearText(), typeText(profileName));

            Espresso.closeSoftKeyboard();

            onView(withId(R.id.CreateProfileButton)).perform(click());

            Thread.sleep(500);

            //Alert Dialog Shows Up saying no filter selected
            onView(withId(android.R.id.button1)).perform(click());


        }catch(Exception e){


        }

    }


    @Test
    public void createProfileUITest() {

        try {
            //1)Read db and determine the size of entries in db
            //2)Input Filter Name
            //3)Click on toggle button
            //4)Select 3 filters
            //5)Click on filter profile button
            //6)Validate db has +1 entry
            onView(withId(R.string.CreateProfilePage_CreateBtn)).perform(click());

            Thread.sleep(1500);

            onView(withId(android.R.id.button1)).perform(click());

        } catch (Exception e) {

        }
    }

}