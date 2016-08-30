package emc.captiva.mobile.sdksampleapp.RestClient;
import emc.captiva.mobile.sdksampleapp.Network.CaptivaImageUploadService;
import emc.captiva.mobile.sdksampleapp.Network.LoginInterceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by david on 8/23/16.
 */
//A class that is used to upload images
public class CaptivaImageUploaderClient {

    private static String BASE_URL = "http://104.209.39.82:8090";

    public CaptivaImageUploadService createImageUploadServer(){

        Retrofit adapter = this.createAdapter();
        return adapter.create(CaptivaImageUploadService.class);
    }

    private Retrofit createAdapter(){

        return new Retrofit.Builder()
                .client(createClient())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    private OkHttpClient createClient(){

        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(new LoginInterceptor());
        return client;

    }

}
