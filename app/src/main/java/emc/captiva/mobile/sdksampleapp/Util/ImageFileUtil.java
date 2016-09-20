package emc.captiva.mobile.sdksampleapp.Util;

import android.net.Uri;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by david on 8/25/16.
 */
public class ImageFileUtil {

    public MultipartBody.Part createPartFromFile(File file){

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("picture", file.getName(), requestFile);

        return body;

    }

    public String encodeImageBase64(InputStream inputStream){

        byte[] byteArray = null;
        try{
            byteArray = inputStreamToByteArray(inputStream);
        }catch (Exception e){

        }
        if(byteArray != null){
            return Base64.encodeToString(byteArray, Base64.NO_WRAP);
        }
        return null;
    }

    public byte[] inputStreamToByteArray(InputStream inputStream) throws IOException {
        if(inputStream==null) {
            return null;
        }
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

}
