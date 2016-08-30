package emc.captiva.mobile.sdksampleapp.JsonPojo;

import java.io.File;

/**
 * Created by david on 8/30/16.
 */
public class ImageUploadObj {

    final String data;

    public ImageUploadObj(String data) {
        this.data = data;
    }

    public ImageUploadObj(File file){
        this.data = "";
        //Todo Implement
    }
}
