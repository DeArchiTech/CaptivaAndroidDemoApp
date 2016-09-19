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
import emc.captiva.mobile.sdksampleapp.Model.Filter;
import emc.captiva.mobile.sdksampleapp.View.SelectedListClickedListener;

import static org.junit.Assert.*;

/**
 * Created by Davix on 9/13/16.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class SelectedFilterListAdapterTest {

    SelectedFilterListAdapter adapter;
    List<FilterListItem> items;

    @Before
    public void setup(){

        this.items = new ArrayList<>();
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

    @Test
    public void testAddItem(){

        int originalItemCount = this.items.size();
        adapter.addItemToListView(new FilterListItem(new Filter()));
        Assert.assertEquals(adapter.getCount() , originalItemCount +1);

    }

    @Test
    public void testRemoveItem(){

        int originalItemCount = this.items.size();
        adapter.removeItemFromListView(new FilterListItem(new Filter()));
        Assert.assertEquals(originalItemCount, adapter.getCount());

        adapter.addItemToListView(new FilterListItem(new Filter()));
        adapter.addItemToListView(new FilterListItem(new Filter()));

        FilterListItem itemToRemove = new FilterListItem(new Filter());
        adapter.addItemToListView(itemToRemove);

        int newCount = this.adapter.getCount();
        adapter.removeItemFromListView(itemToRemove);
        Assert.assertEquals(adapter.getCount() , newCount -1);

    }

    @Test
    public void testListContainsItem(){

        FilterListItem item = new FilterListItem(new Filter("ABCD"));
        FilterListItem itemAnother = new FilterListItem(new Filter("ABCD"));
        adapter.addItemToListView(item);
        boolean result = adapter.listContainsItem(itemAnother);
        Assert.assertTrue(result);

    }

}