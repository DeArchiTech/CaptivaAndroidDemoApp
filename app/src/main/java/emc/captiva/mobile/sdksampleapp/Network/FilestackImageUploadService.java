package emc.captiva.mobile.sdksampleapp.Network;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by david on 8/25/16.
 */
public interface FilestackImageUploadService {

    @Multipart
    @POST("api/store/S3")
    Call<ResponseBody> updateImage(@Query("key") String key, @Query("filename") String filename, @Part MultipartBody.Part file);
}
