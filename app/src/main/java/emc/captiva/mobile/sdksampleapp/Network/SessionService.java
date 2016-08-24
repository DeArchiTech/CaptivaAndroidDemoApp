package emc.captiva.mobile.sdksampleapp.Network;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.POST;

/**
 * Created by david on 8/24/16.
 */
public interface SessionService {

    @POST("cp-rest/session")
    Call<Response> login(JSONObject object);

    @DELETE("cp-rest/session")
    Call<Response> logout(JSONObject object);

}
