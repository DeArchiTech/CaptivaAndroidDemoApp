package emc.captiva.mobile.sdksampleapp.RestClient;
import emc.captiva.mobile.sdksampleapp.Network.FilestackImageUploadService;
import emc.captiva.mobile.sdksampleapp.Network.LoginInterceptor;
import emc.captiva.mobile.sdksampleapp.Util.ImageFileUtil;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by david on 8/25/16.
 */
public class FilestackClient {

    private static String BASE_URL = "https://www.filestackapi.com";
    private static String key = "AaApUHHABQg2818PX5CLTz";

    public Call<ResponseBody> updateImage(String fileName, MultipartBody.Part body){

        FilestackImageUploadService service = this.createImageUploadService();
        return service.updateImage(key, fileName, body);

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
