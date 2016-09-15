package emc.captiva.mobile.sdksampleapp.ListAdapter;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import emc.captiva.mobile.sdksampleapp.BuildConfig;
import emc.captiva.mobile.sdksampleapp.ListItem.FilterListItem;
import emc.captiva.mobile.sdksampleapp.View.SelectedListClickedListener;

import static org.junit.Assert.*;

/**
 * Created by Davix on 9/13/16.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class SelectedFilterListAdapterTest {

    SelectedFilterListAdapter adapter;

    @Before
    public void setup(){

        List<FilterListItem> items = new ArrayList<>();
        SelectedListClickedListener listener = new SelectedListClickedListener() {
            @Override
            public void selectedListOnClick(FilterListItem item) {

            }
        };
        adapter = new SelectedFilterListAdapter(RuntimeEnvironment.application, items, listener);
    }


    @Test
    public void getItems() throws Exception {

        List<FilterListItem> result = adapter.getItems();
        Assert.assertNotNull(result);
    }

}