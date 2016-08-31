package emc.captiva.mobile.sdksampleapp.Util;

import com.google.gson.Gson;

import emc.captiva.mobile.sdksampleapp.JsonPojo.LoginResponseObj;

/**
 * Created by david on 8/30/16.
 */
public class SerializeUtil {

    public LoginResponseObj getCookieFromResponse(String json){

        return new Gson().fromJson(json, LoginResponseObj.class);

    }
}
