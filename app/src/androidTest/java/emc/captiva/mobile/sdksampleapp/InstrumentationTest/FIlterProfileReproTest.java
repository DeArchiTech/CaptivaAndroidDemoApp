package emc.captiva.mobile.sdksampleapp.InstrumentationTest;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import emc.captiva.mobile.sdksampleapp.MainActivity;
import emc.captiva.mobile.sdksampleapp.Model.FilterProfile;
import emc.captiva.mobile.sdksampleapp.Repository.FilterProfileRepo;
import emc.captiva.mobile.sdksampleapp.Util.RealmUtil;
import io.realm.Realm;

/**
 * Created by david on 9/1/16.
 */
@RunWith(AndroidJUnit4.class)
public class FIlterProfileReproTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void createFilterProfileSyncTest() throws Exception{

        //Run on the same thread that has looper
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {

                //1)Create Test Realm
                Context context = InstrumentationRegistry.getTargetContext();
                Realm realm = new RealmUtil().createRealm(context);


                //2)Create manager and Realm Instance
                FilterProfileRepo manager = new FilterProfileRepo();
                FilterProfile profile = new FilterProfile();
                profile.setId(143);

                //3)Test sync call
                realm.beginTransaction();
                manager.createFilterProfileSync(realm, profile);
                realm.commitTransaction();

            }

        });
    }

    @Test
    public void readFilterProfileSyncTest() throws Exception{

        //Run on the same thread that has looper
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {

                //1)Create Test Realm
                Context context = InstrumentationRegistry.getTargetContext();
                Realm realm = new RealmUtil().createRealm(context);

                //2)Create manager and Realm Instance
                FilterProfileRepo repro = new FilterProfileRepo();
                FilterProfile profile = new FilterProfile();
                profile.setId(143);

                //3)Create

                int initSize = FilterProfileRepo.profilesCache.size();

                realm.beginTransaction();
                repro.createFilterProfileSync(realm, profile);
                realm.commitTransaction();

                //4)Test sync call

                realm.beginTransaction();
                repro.readListOfProfileSync(realm);
                realm.commitTransaction();
                Assert.assertEquals(initSize + 1,FilterProfileRepo.profilesCache.size());
            }

        });
    }

    @Test
    public void nextIDTest() throws Exception{

        //Run on the same thread that has looper
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {

                //1)Create Test Realm
                Context context = InstrumentationRegistry.getTargetContext();
                Realm realm = new RealmUtil().createRealm(context);

                //2)Create manager and Realm Instance
                FilterProfileRepo manager = new FilterProfileRepo();
                //3)Test sync call


                FilterProfile profile = new FilterProfile();

                realm.beginTransaction();
                manager.createFilterProfileSync(realm , profile);
                realm.commitTransaction();
                int id = manager.getNextId(realm);
                realm.beginTransaction();
                manager.createFilterProfileSync(realm , profile);
                realm.commitTransaction();

                realm.beginTransaction();
                manager.createFilterProfileSync(realm , profile);
                realm.commitTransaction();

                int currentId = manager.getNextId(realm);
                Assert.assertEquals(id + 2, currentId);

            }

        });
    }

    @Test
    public void readIDSyncTest() throws Exception{

        //Run on the same thread that has looper
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {

                //1)Create Test Realm
                Context context = InstrumentationRegistry.getTargetContext();
                Realm realm = new RealmUtil().createRealm(context);

                //2)Create manager and Realm Instance
                FilterProfileRepo manager = new FilterProfileRepo();
                //3)Test sync call
                Assert.assertEquals(FilterProfileRepo.maxIdCache ,0);
                String profileName = "Profile Name";

                FilterProfile profile = new FilterProfile();
                profile.setProfileName(profileName);
                realm.beginTransaction();
                manager.createFilterProfileSync(realm , profile);
                realm.commitTransaction();

                int id = manager.getNextId(realm) -1 ;

                Assert.assertNull(FilterProfileRepo.lastProfileLoaded);
                manager.loadProfileFromIdSync(realm,id);
                Assert.assertNotNull(FilterProfileRepo.lastProfileLoaded);
                Assert.assertEquals(FilterProfileRepo.lastProfileLoaded.getId(), id);
                Assert.assertEquals(FilterProfileRepo.lastProfileLoaded.getProfileName(), profileName);

            }

        });
    }
}
