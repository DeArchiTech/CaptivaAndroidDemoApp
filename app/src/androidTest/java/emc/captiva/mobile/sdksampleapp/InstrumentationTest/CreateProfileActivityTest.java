package emc.captiva.mobile.sdksampleapp.InstrumentationTest;

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

import java.util.ArrayList;
import java.util.List;
import emc.captiva.mobile.sdksampleapp.CreateFilterProfileActivity;
import emc.captiva.mobile.sdksampleapp.ListItem.FilterListItem;
import emc.captiva.mobile.sdksampleapp.Model.Filter;
import emc.captiva.mobile.sdksampleapp.Model.FilterProfile;
import emc.captiva.mobile.sdksampleapp.Presenter.CreateProfilePresenter;
import emc.captiva.mobile.sdksampleapp.R;
import io.realm.Realm;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.clearText;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

/**
 * Created by david on 9/2/16.
 */

@RunWith(AndroidJUnit4.class)
public class CreateProfileActivityTest {

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
        filterListItem = new ArrayList<>();

        filterListItem.add(new FilterListItem(new Filter("aaa")));
        filterListItem.add(new FilterListItem(new Filter("bbb")));
        filterListItem.add(new FilterListItem(new Filter("ccc")));

        FilterListItem itemSelected = new FilterListItem(new Filter("selected"));
        itemSelected.selected = true;

        filterListItem.add(itemSelected);
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
    public void testInputProfileName(){

        String profileName = "profileName";

        onView(withId(R.id.createProfileNameInput)).perform(click()).perform(clearText(), typeText(profileName));

        onView(withId(R.id.createProfileNameInput)).check(matches(withText(profileName)));

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
    public void testGetProfileName() {

        String result = mActivityRule.getActivity().getProfileName(textView);
        Assert.assertEquals(testString, result);

    }

    @Test
    public void testGetSelectedFilters() {

        List<FilterListItem> result = mActivityRule.getActivity().getSelectedFilters(filterListItem);
        Assert.assertEquals(result.size(), 1);

    }

    @Test
    public void testCreateProfileObject() {

        String profileName = mActivityRule.getActivity().getProfileName(textView);
        List<FilterListItem> filterList = mActivityRule.getActivity().getSelectedFilters(filterListItem);
        boolean applyFilterAuto = true;
        FilterProfile profile = mActivityRule.getActivity().createFilterProfile(profileName, filterList, applyFilterAuto);

        Assert.assertEquals(profile.isAutoMaticallyApplyFilter(), applyFilterAuto);
        Assert.assertEquals(profile.getProfileName(), profileName);
        Assert.assertEquals(profile.getFilters().size(), filterList.size());

    }

    @Test
    public void testonSaveButtonClicked(){

        //Stub Methods
        doNothing().when(presenter).onCreateProfile(Matchers.any(FilterProfile.class)
                ,Matchers.any(Realm.Transaction.OnSuccess.class)
                ,Matchers.any(Realm.Transaction.OnError.class)
                ,Matchers.any(Realm.class));

        mActivityRule.getActivity().onSaveButtonClicked();

        //Verify createProfiles gets called
        verify(presenter).onCreateProfile(Matchers.any(FilterProfile.class)
                ,Matchers.any(Realm.Transaction.OnSuccess.class)
                ,Matchers.any(Realm.Transaction.OnError.class)
                ,Matchers.any(Realm.class));

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

    @Test
    public void testInputFilterAlert() {

        Assert.assertEquals(true,false);

    }

    @Test
    public void testNotEnoughFiltersAlert(){

        Assert.assertEquals(true,false);

    }


}