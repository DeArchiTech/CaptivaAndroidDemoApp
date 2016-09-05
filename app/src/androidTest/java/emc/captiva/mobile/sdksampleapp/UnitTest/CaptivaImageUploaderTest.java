package emc.captiva.mobile.sdksampleapp.UnitTest;
import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.TestCase;

import java.io.InputStream;

import emc.captiva.mobile.sdksampleapp.JsonPojo.ImageUploadObj;
import emc.captiva.mobile.sdksampleapp.Model.Cookie;
import emc.captiva.mobile.sdksampleapp.Model.CookieRepo;
import emc.captiva.mobile.sdksampleapp.Network.CaptivaImageUploadService;
import emc.captiva.mobile.sdksampleapp.RestClient.CaptivaImageServiceBuilder;
import emc.captiva.mobile.sdksampleapp.Util.StringUtil;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by david on 8/23/16.
 */
public class CaptivaImageUploaderTest extends TestCase{

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

        //1)Mock Up Login token
        String token = "CPTV-TICKET"+ "SK22>>>>*!91114904796168454341/2Otngwy2Yw7TVPt+oLguhB/TRED9DpEctfJhhR7Kcx75JaQaZ+pnqj6V/iGCRdQ1acNZuQyMx0reNZ5BIqU0lx9s4lmwPdOmf95riSEqkz6MDJfrUYV/6XBbw0XvSqiiWoRjqbJ6mXPvFjL4H/tuwLINgnvUHiOdDelU0fYZ2GfTtiPOer0KNLg9zB+A7X/AL6QXeMcF7VbKoRCRYtBTJ5tg51o4rRn+a8alEbgdrRF46VLxQJvvVNs0ixV8ijPeOmXkQasrne113jXhKVvDOcAlmPpLyiGKF3XISKFV3LYnyg==*>>>>";
        Cookie cookie = new Cookie();
        cookie.setCookie(token);
        CookieRepo.sessionCookie = cookie;

        //2)Load in BASE 64 Image Data
        String jsonFileName = "base64Image.txt";
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(jsonFileName);
        String imageData = StringUtil.getStringFromInputStream(in);

        //3)Mock Up services and Object
        ImageUploadObj obj = new ImageUploadObj(imageData);
        CaptivaImageUploadService service = new CaptivaImageServiceBuilder().createImageUploadServer();

        //4)Execute function
        Call<ResponseBody> call = service.uploadImage(obj);

        //5)Assert Results
        assertNotNull(call);
    }
}
