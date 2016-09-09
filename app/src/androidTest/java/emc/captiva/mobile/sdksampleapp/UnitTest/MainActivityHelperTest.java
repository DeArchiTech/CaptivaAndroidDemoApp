package emc.captiva.mobile.sdksampleapp.UnitTest;

import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.mockito.Mock;
import org.mockito.MockingDetails;
import org.mockito.Mockito;

import emc.captiva.mobile.sdksampleapp.ActivityHelper.MainActivityHelper;
import emc.captiva.mobile.sdksampleapp.MainActivity;
import emc.captiva.mobile.sdksampleapp.Model.Cookie;
import emc.captiva.mobile.sdksampleapp.Repository.CookieRepo;

/**
 * Created by david on 9/9/16.
 */
public class MainActivityHelperTest extends TestCase{

    @SmallTest
    public void testCheckLogInStatus() throws Exception {

        Cookie cookie = new Cookie();
        cookie.setCookie("A COOKIE");
        CookieRepo.sessionCookie = cookie;

        MainActivityHelper helper = new MainActivityHelper();
        boolean result = helper.userIsLoggedIn();
        Assert.assertTrue(result);

        CookieRepo.sessionCookie = null;
        result = helper.userIsLoggedIn();
        Assert.assertFalse(result);

    }
}
