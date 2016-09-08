package emc.captiva.mobile.sdksampleapp.InstrumentationTest;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
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
import emc.captiva.mobile.sdksampleapp.Model.FilterProfileRepo;
import emc.captiva.mobile.sdksampleapp.Presenter.CreateProfilePresenter;
import emc.captiva.mobile.sdksampleapp.R;
import io.realm.Realm;
import io.realm.RealmConfiguration;

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
public class CreateProfileActivityUnitTest {

    @Mock
    TextView textView;
    @Mock
    CreateProfilePresenter presenter;
    @Mock
    FilterProfile profile;

    List<FilterListItem> filterListItem;
    String testString = "ABCD";

    @Before
    public void setUp() throws Exception {

        textView = Mockito.mock(TextView.class);
        presenter = Mockito.mock(CreateProfilePresenter.class);
        profile = Mockito.mock(FilterProfile.class);

        filterListItem = new ArrayList<>();

        filterListItem.add(new FilterListItem(new Filter("aaa")));
        filterListItem.add(new FilterListItem(new Filter("bbb")));
        filterListItem.add(new FilterListItem(new Filter("ccc")));

        this.testString = "ABCD";
        mActivityRule.getActivity().setPresenter(this.presenter);
        when(textView.getText()).thenReturn(testString);
    }

    @Rule
    public ActivityTestRule<CreateFilterProfileActivity> mActivityRule =
            new ActivityTestRule<>(CreateFilterProfileActivity.class);

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

        FilterListItem selected = new FilterListItem(new Filter("ccc"));
        selected.selected = true;
        filterListItem.add(selected);
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
    public void testonSaveButtonClicked() {

        //I am just testing that the presenter gets called, UI Assertions tests are in
        //The UI Test Class
        doNothing().when(presenter).onCreateProfile(Matchers.any(FilterProfile.class)
                , Matchers.any(Realm.Transaction.OnSuccess.class)
                , Matchers.any(Realm.Transaction.OnError.class)
                , Matchers.any(Realm.class));

        mActivityRule.getActivity().callPresenterToCreateProfile(profile,null);
        //Verify createProfiles gets called
        verify(presenter).onCreateProfile(Matchers.any(FilterProfile.class)
                , Matchers.any(Realm.Transaction.OnSuccess.class)
                , Matchers.any(Realm.Transaction.OnError.class)
                , Matchers.any(Realm.class));

    }


}