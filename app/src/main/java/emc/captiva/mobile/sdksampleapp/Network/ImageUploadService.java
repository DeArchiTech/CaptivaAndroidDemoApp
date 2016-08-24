package emc.captiva.mobile.sdksampleapp.Network;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by david on 8/23/16.
 */
public interface ImageUploadService {

    @Multipart
    @POST("some/endpoint")
    Call<Response> uploadImage(@Part MultipartBody.Part image, @Part("name") RequestBody name);


}
