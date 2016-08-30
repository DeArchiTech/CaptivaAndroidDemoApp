package emc.captiva.mobile.sdksampleapp.Network;
import emc.captiva.mobile.sdksampleapp.JsonPojo.LoginRequestObj;
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
    Call<ResponseBody> login(@Body LoginRequestObj object);

    @DELETE("cp-rest/session")
    Call<ResponseBody> logout(@Body LoginRequestObj object);

}
