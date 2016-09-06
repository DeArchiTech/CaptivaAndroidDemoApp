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
import emc.captiva.mobile.sdksampleapp.Model.FilterProfileService;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by david on 9/1/16.
 */
@RunWith(AndroidJUnit4.class)
public class FIlterProfileServiceTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void createFilterProfileSyncTest() throws Exception{

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
                FilterProfileService manager = new FilterProfileService();
                FilterProfile profile = new FilterProfile();
                profile.setId(143);
                Realm realm = Realm.getInstance(realmConfig);

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

                Context context = InstrumentationRegistry.getTargetContext();
                RealmConfiguration realmConfig = new RealmConfiguration.Builder(context)
                        .name("myrealm.realm")
                        .inMemory()
                        .build();

                //2)Create manager and Realm Instance
                FilterProfileService manager = new FilterProfileService();
                FilterProfile profile = new FilterProfile();
                profile.setId(143);
                Realm realm = Realm.getInstance(realmConfig);

                //3)Test sync call
                Assert.assertNull(FilterProfileService.profilesCache);

                realm.beginTransaction();
                manager.readProfiles(realm);
                realm.commitTransaction();

                Assert.assertNotNull(FilterProfileService.profilesCache);
            }

        });
    }

    @Test
    public void readMaxIDSyncTest() throws Exception{

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
                FilterProfileService manager = new FilterProfileService();
                Realm realm = Realm.getInstance(realmConfig);

                //3)Test sync call
                Assert.assertEquals(FilterProfileService.maxIdCache ,0);

                int maxID = 999;
                int midID = 455;
                int minID = 123;

                FilterProfile profile = new FilterProfile();

                realm.beginTransaction();
                profile.setId(minID);
                manager.createFilterProfileSync(realm , profile);
                realm.commitTransaction();

                realm.beginTransaction();
                profile.setId(midID);
                manager.createFilterProfileSync(realm , profile);
                realm.commitTransaction();

                realm.beginTransaction();
                profile.setId(maxID);
                manager.createFilterProfileSync(realm , profile);
                realm.commitTransaction();

                manager.readMaxIdSync(realm);

                Assert.assertEquals(FilterProfileService.maxIdCache.intValue(), maxID);

            }

        });
    }
}
