package emc.captiva.mobile.sdksampleapp.Network;
import okhttp3.ResponseBody;
import retrofit2.Call;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by david on 8/25/16.
 */
public interface FilestackImageUploadService {

    @POST("api/store/S3")
    Call<ResponseBody> updateImage(String filename, @Body RequestBody file);
}
