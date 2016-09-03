package emc.captiva.mobile.sdksampleapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import emc.captiva.mobile.sdksampleapp.Model.CookieRepo;
import emc.captiva.mobile.sdksampleapp.Util.StringUtil;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by david on 9/1/16.
 */

@RunWith(AndroidJUnit4.class)
public class CookieRepoTest {

    private String randomCookie = StringUtil.getSaltString();
    private String staticCookie = "I AM A COOKIE";

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void createCookieSyncTest() throws Exception{

        //Run on the same thread that has looper
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {

                Context context = InstrumentationRegistry.getTargetContext();
                RealmConfiguration realmConfig = new RealmConfiguration.Builder(context)
                        .name("myrealm.realm")
                        .inMemory()
                        .build();

                //2)Create manager and Realm Instance
                CookieRepo manager = new CookieRepo();
                Realm realm = Realm.getInstance(realmConfig);
                realm.beginTransaction();

                //3)Test sync call
                manager.createAndPersistSync(Realm.getInstance(realmConfig), staticCookie);
                realm.commitTransaction();

            }

        });
    }

    @Test
    public void crudCookieTest(){

        //Run on the same thread that has looper
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {

                Context context = InstrumentationRegistry.getTargetContext();
                RealmConfiguration realmConfig = new RealmConfiguration.Builder(context)
                        .name("myrealm.realm")
                        .inMemory()
                        .build();

                //2)Create manager and Realm Instance
                CookieRepo manager = new CookieRepo();
                Realm realm = Realm.getInstance(realmConfig);

                //3)CREATE TEST
                Assert.assertNull(CookieRepo.sessionCookie);

                realm.beginTransaction();
                manager.createAndPersistSync(Realm.getInstance(realmConfig), randomCookie);
                realm.commitTransaction();

                Assert.assertNotNull(CookieRepo.sessionCookie);

                //4)READ TEST
                CookieRepo.sessionCookie=null;
                Assert.assertNull(CookieRepo.sessionCookie);

                realm.beginTransaction();
                manager.createAndPersistSync(Realm.getInstance(realmConfig), randomCookie);
                realm.commitTransaction();

                Assert.assertNotNull(CookieRepo.sessionCookie);
                Assert.assertEquals(CookieRepo.sessionCookie.getCookie(), randomCookie);

                //5)DELETE & ATTEMPT TO READ TEST
                Assert.assertNotNull(CookieRepo.sessionCookie);

                realm.beginTransaction();
                manager.deleteCookieSync(Realm.getInstance(realmConfig));
                realm.commitTransaction();

                Assert.assertNull(CookieRepo.sessionCookie);

            }

        });
    }

}