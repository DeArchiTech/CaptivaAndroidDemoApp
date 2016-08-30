package emc.captiva.mobile.sdksampleapp.JsonPojo;

import java.io.File;

/**
 * Created by david on 8/30/16.
 */
public class ImageUploadObject {

    final String data;

    public ImageUploadObject(String data) {
        this.data = data;
    }

    public ImageUploadObject(File file){
        this.data = "";
        //Todo Implement
    }
}
