package emc.captiva.mobile.sdksampleapp.Util;

import android.view.MenuItem;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import emc.captiva.mobile.sdksampleapp.R;

import static org.junit.Assert.*;

/**
 * Created by Davix on 9/14/16.
 */
public class FilterToMenuUtilTest {

    @Test
    public void getMenuFromFilterString() throws Exception {

        FilterToMenuUtil util = new FilterToMenuUtil();

        //First Test
        MenuItem menuItem = util.getMenuFromFilterString("Gray");
        int result = menuItem.getItemId();
        Assert.assertEquals(result, R.id.ABGray);

        //Second Test
        menuItem = util.getMenuFromFilterString("Black-White");
        result = menuItem.getItemId();
        Assert.assertEquals(result, R.id.ABBlackWhite);

        //Third Test
        menuItem = util.getMenuFromFilterString("Rotate 180");
        result = menuItem.getItemId();
        Assert.assertEquals(result, R.id.ABRotate180);
    }

}