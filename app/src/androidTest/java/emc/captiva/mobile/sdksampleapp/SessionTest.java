package emc.captiva.mobile.sdksampleapp;
import android.test.suitebuilder.annotation.SmallTest;
import junit.framework.TestCase;
import emc.captiva.mobile.sdksampleapp.RestClient.SessionClient;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by david on 8/24/16.
 */
public class SessionTest extends TestCase{

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @SmallTest
    public void testLogin(){

        SessionClient client = new SessionClient();
        Call<ResponseBody> call = client.login();
        assertNotNull(call);

    }

    @SmallTest
    public void testLogout(){

        SessionClient client = new SessionClient();
        Call<ResponseBody> responseCall = client.logout();
        assertNotNull(responseCall);

    }
}
