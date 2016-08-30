package emc.captiva.mobile.sdksampleapp.RestClient;
import emc.captiva.mobile.sdksampleapp.JsonPojo.LoginObject;
import emc.captiva.mobile.sdksampleapp.Network.LoginInterceptor;
import emc.captiva.mobile.sdksampleapp.Network.SessionService;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by david on 8/24/16.
 */
public class SessionClient {

    private static String BASE_URL = "http://104.209.39.82:8090";


    public Call<ResponseBody> login(){

        Retrofit retrofit = createAdapter();
        SessionService service = retrofit.create(SessionService.class);
        return service.login(createLoginJsonObject());

    }

    public Call<ResponseBody> logout(){

        Retrofit retrofit = createAdapter();
        SessionService service = retrofit.create(SessionService.class);
        return service.logout(createLoginJsonObject());

    }

    private Retrofit createAdapter(){

        return new Retrofit.Builder()
                .client(createClient())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    private OkHttpClient createClient(){

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        return builder.addInterceptor(new LoginInterceptor()).build();

    }

    private LoginObject createLoginJsonObject(){

        String licenseKey = "LICE075-D09A-64E3";
        String applicationId = "APP3075-D09A-59C8";
        String username = "capadmin";
        String password = "Reva12#$";
        return new LoginObject("en-US",licenseKey,"",applicationId,username,password,"");
    }


}
