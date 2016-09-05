package emc.captiva.mobile.sdksampleapp.UnitTest;
import android.test.suitebuilder.annotation.SmallTest;
import junit.framework.TestCase;

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
        /*
        String sdCardPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String filePath = "/DCIM/Camera/IMG_20160825_153535.jpg";
        String path = sdCardPath + filePath;
        File file = new File(path);
        ImageFileUtil util = new ImageFileUtil();
        MultipartBody.Part part = util.createPartFromFile(file);
        String fileName = "a good file";
        String key = "";
        Res
        Call<ResponseBody> call = client.buildFilestackService().updateImage(fileName,key, part);
        assertNotNull(call);
*/
    }
}
