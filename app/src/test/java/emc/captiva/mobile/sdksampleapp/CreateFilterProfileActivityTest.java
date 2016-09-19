package emc.captiva.mobile.sdksampleapp;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import emc.captiva.mobile.sdksampleapp.ListAdapter.SelectedFilterListAdapter;
import emc.captiva.mobile.sdksampleapp.ListItem.FilterListItem;
import emc.captiva.mobile.sdksampleapp.Model.Filter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Davix on 9/19/16.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class CreateFilterProfileActivityTest {

    CreateFilterProfileActivity activity;
    @Mock
    SelectedFilterListAdapter adapter;

    @Before
    public void setUp() throws Exception {

        adapter = Mockito.mock(SelectedFilterListAdapter.class);
        when(adapter.listContainsItem(Mockito.any(FilterListItem.class))).thenReturn(false);
        activity = Robolectric.setupActivity(CreateFilterProfileActivity.class);
        activity.setSelectedFilterListAdapter(adapter);

    }

    @Test
    public void availableFilterListOnClick() throws Exception {

        activity.availableFilterListOnClick(new FilterListItem(new Filter()));
        verify(adapter).addItemToListView(Mockito.any(FilterListItem.class));

    }

}