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

    private static String BASE_URL = "http://www.filestackapi.com";
    private static String key = "AaApUHHABQg2818PX5CLTz";

    public Call<ResponseBody> updateImage(String fileName, String imageUrl){

        FilestackImageUploadService service = this.createImageUploadService();
        MultipartBody.Part body = new ImageFileUtil().createPartFromFile(imageUrl);
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
        client.interceptors().add(new LoginInterceptor());
        return client;

    }
}
