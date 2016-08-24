package emc.captiva.mobile.sdksampleapp.RestClient;

import org.json.JSONObject;

import emc.captiva.mobile.sdksampleapp.Network.SessionService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by david on 8/24/16.
 */
public class SessionClient {

    private static String BASE_URL = "http://104.209.39.82:8090";

    
    public Call<Response> login(){

        SessionService service = this.createSessionService();
        JSONObject parameter = new JSONObject();
        return service.login(parameter);

    }

    public Call<Response> logout(){

        SessionService service = this.createSessionService();
        JSONObject parameter = new JSONObject();
        return service.logout(parameter);

    }

    private SessionService createSessionService(){

        Retrofit adapter = this.createAdapter();
        return adapter.create(SessionService.class);
    }

    private Retrofit createAdapter(){

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

}
