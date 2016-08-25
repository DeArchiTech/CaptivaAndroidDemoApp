package emc.captiva.mobile.sdksampleapp;
import android.test.suitebuilder.annotation.SmallTest;
import junit.framework.TestCase;
import emc.captiva.mobile.sdksampleapp.RestClient.FilestackClient;
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

        FilestackClient client = new FilestackClient();
        String filePath = "";
        String fileName = "A cool file mang";
        Call<ResponseBody> call = client.updateImage(fileName,filePath);
        assertNotNull(call);

    }
}
