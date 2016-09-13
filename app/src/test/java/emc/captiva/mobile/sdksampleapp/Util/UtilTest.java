package emc.captiva.mobile.sdksampleapp.Util;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import emc.captiva.mobile.sdksampleapp.BuildConfig;

import static org.junit.Assert.*;

/**
 * Created by Davix on 9/12/16.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class UtilTest {

    @Test
    public void enoughtTimeHasPassed() throws Exception {

        //Success Case

        Util util = new Util();
        long end = 9000;
        long start = 1000;
        long duration = 2000;
        boolean result =util.enoughtTimeHasPassed(end, start, duration);
        Assert.assertTrue(result);

        //Failure Case

        duration = 10000;
        result = util.enoughtTimeHasPassed(end, start, duration);
        Assert.assertFalse(result);
    }

}