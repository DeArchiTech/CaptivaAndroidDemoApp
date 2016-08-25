package emc.captiva.mobile.sdksampleapp;
import android.provider.Settings;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;

import junit.framework.TestCase;
import emc.captiva.mobile.sdksampleapp.Util.ImageFileUtil;
import okhttp3.MultipartBody;

/**
 * Created by david on 8/25/16.
 */
public class ImageFileUtilTest extends TestCase{

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @SmallTest
    public void testCreatePartFromFile(){

        ImageFileUtil util = new ImageFileUtil();
        String path = "/storage/emulated/0/DCIM/Camera/IMG_20160825_153535";
        MultipartBody.Part part = util.createPartFromFile(path);
        Log.i("Succeed", String.valueOf(part));
        assertNotNull(util.createPartFromFile(path));

    }

}
