package emc.captiva.mobile.sdksampleapp;
import android.os.Environment;
import android.provider.Settings;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;

import junit.framework.TestCase;

import java.io.File;

import emc.captiva.mobile.sdksampleapp.Util.ImageFileUtil;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

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
        String sdCardPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String filePath = "/DCIM/Camera/IMG_20160825_153535.jpg";
        String path = sdCardPath + filePath;
        File file = new File(path);
        assertNotNull(util.createPartFromFile(file));
        Log.i("Succeed", String.valueOf(file));

    }

}
