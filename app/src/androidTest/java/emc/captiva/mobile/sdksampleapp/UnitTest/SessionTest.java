package emc.captiva.mobile.sdksampleapp.UnitTest;
import android.test.suitebuilder.annotation.SmallTest;
import junit.framework.TestCase;

import emc.captiva.mobile.sdksampleapp.JsonPojo.LoginResponseObj;
import emc.captiva.mobile.sdksampleapp.ServiceBuilder.SessionServiceBuilder;
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

        SessionServiceBuilder client = new SessionServiceBuilder();
        Call<LoginResponseObj> call = client.login();
        assertNotNull(call);

    }

    @SmallTest
    public void testLogout(){

        SessionServiceBuilder client = new SessionServiceBuilder();
        Call<ResponseBody> responseCall = client.logout();
        assertNotNull(responseCall);

    }
}
