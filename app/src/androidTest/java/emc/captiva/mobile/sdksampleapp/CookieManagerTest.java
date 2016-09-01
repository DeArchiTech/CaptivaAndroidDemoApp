package emc.captiva.mobile.sdksampleapp;

import android.support.test.espresso.core.deps.guava.util.concurrent.ThreadFactoryBuilder;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import emc.captiva.mobile.sdksampleapp.Model.CookieManager;
import emc.captiva.mobile.sdksampleapp.Util.StringUtil;

/**
 * Created by david on 9/1/16.
 */

@RunWith(AndroidJUnit4.class)
public class CookieManagerTest{

    private String randomCookie = StringUtil.getSaltString();
    private String staticCookie = "I AM A COOKIE";

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void createCookieTest(){

        CookieManager manager = new CookieManager();
        manager.createAndPersistOneCookie(staticCookie);

        try{
            Thread.sleep(8000);
        }catch(Exception e){

        }

    }

    @Test
    public void readCookieTest(){

        CookieManager manager = new CookieManager();
        manager.readCookie();

    }

    @Test
    public void deleteCookieTest(){

        CookieManager manager = new CookieManager();
        manager.deleteCookie();

    }

    @Test
    public void createAndPersistCookieTest(){

        CookieManager manager = new CookieManager();
        manager.createAndPersistOneCookie(staticCookie);

    }

}