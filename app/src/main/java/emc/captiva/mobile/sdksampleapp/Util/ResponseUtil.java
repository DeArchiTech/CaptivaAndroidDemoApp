package emc.captiva.mobile.sdksampleapp.Util;

import com.google.gson.Gson;

import emc.captiva.mobile.sdksampleapp.JsonPojo.LoginResponseObj;

/**
 * Created by david on 8/30/16.
 */
public class ResponseUtil {

    public String getCookieFromResponse(String json){

        LoginResponseObj body = new Gson().fromJson(json, LoginResponseObj.class);
        //Todo Implement
        return body.ticket;

    }
}
