package emc.captiva.mobile.sdksampleapp.UnitTest;

import android.test.suitebuilder.annotation.SmallTest;
import android.text.Editable;
import android.widget.EditText;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import emc.captiva.mobile.sdksampleapp.ActivityHelper.CreateProfileHelper;
import emc.captiva.mobile.sdksampleapp.ListItem.FilterListItem;
import emc.captiva.mobile.sdksampleapp.Model.Filter;

import static org.mockito.Mockito.when;

/**
 * Created by Davix on 9/8/16.
 */

public class CreateProfileHelperTest extends TestCase{

    CreateProfileHelper helper;
    @Mock
    EditText editText;
    @Mock
    Editable editable;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.helper = new CreateProfileHelper();
        this.editText = Mockito.mock(EditText.class);
        this.editable = Mockito.mock(Editable.class);
        when(this.editText.getText()).thenReturn(this.editable);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @SmallTest
    public void testFilterNameIsSetNeg(){

        //Fail Scenerio
        String defaultString = "Please input a name for your profile";
        when(this.editable.toString()).thenReturn(defaultString);
        boolean result = this.helper.filterNameIsSet(this.editText, defaultString);
        Assert.assertFalse(result);

    }

    @SmallTest
    public void testFilterNameIsSetPos(){

        //Pass Scenerio
        String defaultString = "Please input a name for your profile";
        when(this.editable.toString()).thenReturn("ABCDE");
        boolean result = this.helper.filterNameIsSet(this.editText, defaultString);
        Assert.assertTrue(result);

    }

    @SmallTest
    public void testAtLeastOneFilterSelected(){

        List<FilterListItem> listItems = new ArrayList<FilterListItem>();
        Filter filter = new Filter();
        FilterListItem item = new FilterListItem(filter);
        item.selected = true;
        listItems.add(item);

        Assert.assertTrue(helper.atLeastOneFilterSelected(listItems));

    }

}
