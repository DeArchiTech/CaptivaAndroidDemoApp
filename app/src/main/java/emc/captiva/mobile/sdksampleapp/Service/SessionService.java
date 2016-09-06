package emc.captiva.mobile.sdksampleapp.Service;
import emc.captiva.mobile.sdksampleapp.JsonPojo.LoginRequestObj;
import emc.captiva.mobile.sdksampleapp.JsonPojo.LoginResponseObj;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;

/**
 * Created by david on 8/24/16.
 */
public interface SessionService {

    @POST("cp-rest/session")
    Call<LoginResponseObj> login(@Body LoginRequestObj object);

    @DELETE("cp-rest/session")
    Call<ResponseBody> logout();

}
