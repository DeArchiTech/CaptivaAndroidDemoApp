package emc.captiva.mobile.sdksampleapp.UnitTest;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.Assert;
import junit.framework.TestCase;
import emc.captiva.mobile.sdksampleapp.Model.Filter;
import emc.captiva.mobile.sdksampleapp.Util.FilterRepoUtil;
import emc.captiva.mobile.sdksampleapp.Util.RealmUtil;
import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by Davix on 9/8/16.
 */

public class FilterReproUtilTest extends TestCase{

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @SmallTest
    public void testCreateFilterList(){

        //Run on the same thread that has looper
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {

                //1)Create Test Realm
                Context context = InstrumentationRegistry.getTargetContext();
                Realm realm = new RealmUtil().createRealm(context);

                //2)Create and test util
                FilterRepoUtil util = new FilterRepoUtil();
                RealmList<Filter> originalFilters = new RealmList<>();
                String testString = "test String";
                originalFilters.add(new Filter(testString));

                realm.beginTransaction();
                RealmList<Filter> filters = util.createReproFilters(originalFilters, realm);
                realm.commitTransaction();
                //3)Assert
                Assert.assertNotNull(filters);
                Assert.assertEquals(filters.get(0).getFilterName(), testString);

            }

        });

    }
}
