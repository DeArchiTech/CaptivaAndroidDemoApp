package emc.captiva.mobile.sdksampleapp.ServiceBuilder;
import emc.captiva.mobile.sdksampleapp.Interceptor.CaptivaCookieInterpreter;
import emc.captiva.mobile.sdksampleapp.Service.CaptivaImageUploadService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by david on 8/23/16.
 */
//A class that is used to upload images
public class CaptivaImageServiceBuilder {

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

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        return builder.addInterceptor(new CaptivaCookieInterpreter()).build();

    }

}
