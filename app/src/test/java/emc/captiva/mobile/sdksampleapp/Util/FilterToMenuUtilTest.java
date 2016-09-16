package emc.captiva.mobile.sdksampleapp.Util;

import android.view.MenuItem;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import emc.captiva.mobile.sdk.CaptureImage;
import emc.captiva.mobile.sdksampleapp.R;

import static org.junit.Assert.*;

/**
 * Created by Davix on 9/14/16.
 */
public class FilterToMenuUtilTest {

    FilterToMenuUtil util;
    String gray = "Gray";
    String blackWhite = "Black-White";
    String rotate = "Rotate 180";

    @Before
    public void setUp(){

        util = new FilterToMenuUtil();
    }

    @Test
    public void getMenuFromFilterString() throws Exception {

        //First Test
        MenuItem menuItem = util.getMenuFromFilterString(this.gray);
        int result = menuItem.getItemId();
        Assert.assertEquals(result, R.id.ABGray);

        //Second Test
        menuItem = util.getMenuFromFilterString(this.blackWhite);
        result = menuItem.getItemId();
        Assert.assertEquals(result, R.id.ABBlackWhite);

        //Third Test
        menuItem = util.getMenuFromFilterString(this.rotate);
        result = menuItem.getItemId();
        Assert.assertEquals(result, R.id.ABRotateRight);
    }


}