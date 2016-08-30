package emc.captiva.mobile.sdksampleapp;
import android.os.Environment;
import android.test.suitebuilder.annotation.SmallTest;
import junit.framework.TestCase;

import java.io.File;

import emc.captiva.mobile.sdksampleapp.RestClient.FilestackClient;
import emc.captiva.mobile.sdksampleapp.Util.ImageFileUtil;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by david on 8/25/16.
 */
public class FilestackImageUploadTest extends TestCase{

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @SmallTest
    public void testUploadImage(){
        //TODO Reweite uploadtImageTest Code, where should your tests be??
        /*FilestackClient client = new FilestackClient();
        String sdCardPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String filePath = "/DCIM/Camera/IMG_20160825_153535.jpg";
        String path = sdCardPath + filePath;
        File file = new File(path);
        ImageFileUtil util = new ImageFileUtil();
        MultipartBody.Part part = util.createPartFromFile(file);
        String fileName = "a good file";
        String key = "";
        Res
        Call<ResponseBody> call = client.getService().updateImage(fileName,key, part);
        assertNotNull(call);
*/
    }
}
