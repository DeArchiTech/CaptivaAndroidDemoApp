package emc.captiva.mobile.sdksampleapp.InstrumentationTest;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import emc.captiva.mobile.sdksampleapp.CreateFilterProfileActivity;
import emc.captiva.mobile.sdksampleapp.ListItem.FilterListItem;
import emc.captiva.mobile.sdksampleapp.Model.FilterProfile;
import emc.captiva.mobile.sdksampleapp.Repository.FilterProfileRepo;
import emc.captiva.mobile.sdksampleapp.Presenter.CreateProfilePresenter;
import emc.captiva.mobile.sdksampleapp.R;
import io.realm.Realm;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.clearText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

/**
 * Created by david on 9/2/16.
 */

@RunWith(AndroidJUnit4.class)
public class CreateProfileActivityUITest {

    @Mock
    TextView textView;

    @Mock
    CreateProfilePresenter presenter;

    @Mock
    FilterProfileRepo repo;

    String testString = "ABCD";

    @Before
    public void setUp() throws Exception {

        textView = Mockito.mock(TextView.class);
        presenter = Mockito.mock(CreateProfilePresenter.class);
        repo = Mockito.mock(FilterProfileRepo.class);
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
                .check(matches(ViewMatchers.withText(R.string.CreateProfilePage_Title)));
        onView(withId(R.id.CreateProfileButton))
                .check(matches(ViewMatchers.withText(R.string.CreateProfilePage_CreateBtn)));

        onView(withId(R.id.createProfileNameInput)).check(matches(isDisplayed()));

        onView(withId(R.id.autoApplyFilter)).check(matches(isDisplayed()));
    }

    @Test
    public void listViewHasString() {
        onView(withText("Image Label")).check(matches(isDisplayed()));
    }

    @Test
    public void testToggle() {

        boolean state = mActivityRule.getActivity().isAutoApplyFilter();

        onView(withId(R.id.autoApplyFilter)).perform(click());

        Assert.assertEquals(mActivityRule.getActivity().isAutoApplyFilter(), !state);

        onView(withId(R.id.autoApplyFilter)).perform(click());

        Assert.assertEquals(mActivityRule.getActivity().isAutoApplyFilter(), state);

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
    public void onClickChangeColorTest(){

        //Assert View with so e color
        //Todo

        onData(anything()).inAdapterView(withId(R.id.filterListView)).atPosition(0).perform(click());

        //Assert View with different color

    }
    @Test
    public void createProfileUITest() {

        doNothing().when(presenter).onCreateProfile(Matchers.any(FilterProfile.class)
                , Matchers.any(Realm.Transaction.OnSuccess.class)
                , Matchers.any(Realm.Transaction.OnError.class)
                , Matchers.any(Realm.class));

        //2)Input Filter Name
        Espresso.closeSoftKeyboard();

        String profileName = "Hakuna Matata";

        onView(withId(R.id.createProfileNameInput)).perform(click()).perform(clearText(), typeText(profileName));

        Espresso.closeSoftKeyboard();

        //3)Click on toggle button
        onView(withId(R.id.autoApplyFilter)).perform(click());

        //4)Select 3 filters
        onData(anything()).inAdapterView(withId(R.id.filterListView)).atPosition(0).perform(click());

        onData(anything()).inAdapterView(withId(R.id.filterListView)).atPosition(1).perform(click());

        onData(anything()).inAdapterView(withId(R.id.filterListView)).atPosition(2).perform(click());

        //5)Click on filter profile create button
        onView(withId(R.id.CreateProfileButton)).perform(click());

        //6)Verify Presenter gets called
        verify(presenter).onCreateProfile(Matchers.any(FilterProfile.class)
                , Matchers.any(Realm.Transaction.OnSuccess.class)
                , Matchers.any(Realm.Transaction.OnError.class)
                , Matchers.any(Realm.class));
    }

    @Test
    public void testAddingItemToSelectedFilters(){

        Espresso.closeSoftKeyboard();

        onData(anything()).inAdapterView(withId(R.id.filterListView)).atPosition(0).perform(click());

        onData(instanceOf(FilterListItem.class))
                .inAdapterView(allOf(withId(R.id.selectedFilterListView), isDisplayed()))
                .atPosition(0)
                .check(matches(isDisplayed()));
    }

    @Test
    public void testRemovingItemsFromSelectedFilters(){

        //Mock For Add
        Espresso.closeSoftKeyboard();

        onData(anything()).inAdapterView(withId(R.id.filterListView)).atPosition(0).perform(click());

        onData(instanceOf(FilterListItem.class))
                .inAdapterView(allOf(withId(R.id.selectedFilterListView), isDisplayed()))
                .atPosition(0)
                .check(matches(isDisplayed()));

        //Attempt To Remove
        onData(anything()).inAdapterView(withId(R.id.selectedFilterListView)).atPosition(0).perform(click());

        //Todo Refactor: Check if position has no content
    }

}