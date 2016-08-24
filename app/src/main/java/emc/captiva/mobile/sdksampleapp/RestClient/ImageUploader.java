package emc.captiva.mobile.sdksampleapp.RestClient;
import emc.captiva.mobile.sdksampleapp.Network.ImageUploadService;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by david on 8/23/16.
 */
//A class that is used to upload images
public class ImageUploader {

    private static String BASE_URL = "http://104.209.39.82:8090";

    public Call<Response> uploadImage(MultipartBody.Part image, RequestBody name){

        ImageUploadService service = this.createImageUploadServer();
        service.uploadImage(image, name);
        return null;

    }

    private ImageUploadService createImageUploadServer(){

        Retrofit adapter = this.createAdapter();
        return adapter.create(ImageUploadService.class);
    }

    private Retrofit createAdapter(){

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

}
