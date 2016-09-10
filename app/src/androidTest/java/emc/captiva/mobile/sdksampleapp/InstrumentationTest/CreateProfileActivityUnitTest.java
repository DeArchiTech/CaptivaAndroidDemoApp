package emc.captiva.mobile.sdksampleapp.InstrumentationTest;

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
import emc.captiva.mobile.sdksampleapp.ListAdapter.AvailableFilterListAdapter;
import emc.captiva.mobile.sdksampleapp.ListAdapter.SelectedFilterListAdapter;
import emc.captiva.mobile.sdksampleapp.ListItem.FilterListItem;
import emc.captiva.mobile.sdksampleapp.Model.Filter;
import emc.captiva.mobile.sdksampleapp.Model.FilterProfile;
import emc.captiva.mobile.sdksampleapp.Presenter.CreateProfilePresenter;
import io.realm.Realm;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.action.ViewActions.click;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

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
    @Mock
    SelectedFilterListAdapter selectedFilterListAdapter;
    @Mock
    AvailableFilterListAdapter availableFilterListAdapter;

    List<FilterListItem> filterListItem;
    String testString = "ABCD";

    @Before
    public void setUp() throws Exception {

        textView = Mockito.mock(TextView.class);
        presenter = Mockito.mock(CreateProfilePresenter.class);
        profile = Mockito.mock(FilterProfile.class);
        selectedFilterListAdapter = Mockito.mock(SelectedFilterListAdapter.class);
        availableFilterListAdapter = Mockito.mock(AvailableFilterListAdapter.class);

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

    @Test
    public void testAvailableFilterListOnClick(){

        //Adds an item to selected list
        FilterListItem item = new FilterListItem(new Filter());
        CreateFilterProfileActivity activity = mActivityRule.getActivity();

        //Swap out the selected filter adapter
        activity.setSelectedFilterListAdapter(this.selectedFilterListAdapter);
        activity.filterListOnClick(item);
        verify(this.selectedFilterListAdapter).addItemToListView(Matchers.any(FilterListItem.class));

    }

    @Test
    public void testSelectedListOnClick(){

        //Adds an item to selected list
        FilterListItem item = new FilterListItem(new Filter());
        CreateFilterProfileActivity activity = mActivityRule.getActivity();

        //Swap out the selected filter adapter
        activity.setSelectedFilterListAdapter(this.selectedFilterListAdapter);
        activity.selectedListOnClick(item);
        verify(this.selectedFilterListAdapter).removeItemFromListView(Matchers.any(FilterListItem.class));

    }



}