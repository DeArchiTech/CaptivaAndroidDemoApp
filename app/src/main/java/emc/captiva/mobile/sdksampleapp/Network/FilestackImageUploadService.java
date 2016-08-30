package emc.captiva.mobile.sdksampleapp.Network;
import java.util.concurrent.locks.ReadWriteLock;

import okhttp3.ResponseBody;
import okhttp3.MultipartBody;
import retrofit2.Call;
import okhttp3.RequestBody;

import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by david on 8/25/16.
 */
public interface FilestackImageUploadService {

    @POST("api/store/S3")
    Call<ResponseBody> updateImage(@Query("key") String key, @Query("filename") String filename, @Body RequestBody file);


    @Multipart
    @POST("api/store/S3")
    Call<ResponseBody> updateImage(@Query("key") String key, @Query("filename") String filename, @Part MultipartBody.Part file);
}
