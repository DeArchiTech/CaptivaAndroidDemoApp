package emc.captiva.mobile.sdksampleapp.Service;
import okhttp3.ResponseBody;
import retrofit2.Call;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by david on 8/25/16.
 */
public interface FilestackImageUploadService {

    @POST("api/store/S3")
    Call<ResponseBody> updateImage(@Query("key") String key, @Query("filename") String filename, @Body RequestBody file);
}
