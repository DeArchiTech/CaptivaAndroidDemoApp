package emc.captiva.mobile.sdksampleapp.ServiceBuilder;
import emc.captiva.mobile.sdksampleapp.Service.FilestackImageUploadService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by david on 8/25/16.
 */
public class FilestackServiceBuilder {

    private static String BASE_URL = "https://www.filestackapi.com";
    private static String key = "AaApUHHABQg2818PX5CLTz";

    public FilestackImageUploadService buildFilestackService(){

        return this.createImageUploadService();

    }

    private FilestackImageUploadService createImageUploadService(){

        Retrofit adapter = this.createAdapter();
        return adapter.create(FilestackImageUploadService.class);
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
        return client;

    }
}
