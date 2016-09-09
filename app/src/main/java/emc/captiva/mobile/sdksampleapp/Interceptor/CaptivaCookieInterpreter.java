package emc.captiva.mobile.sdksampleapp.Interceptor;

import java.io.IOException;

import emc.captiva.mobile.sdksampleapp.Repository.CookieRepo;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by david on 8/30/16.
 */
public class CaptivaCookieInterpreter implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        request = request.newBuilder()
                .addHeader("Content-Type", "application/vnd.emc.captiva+json; charset=utf-8")
                .addHeader("Accept", "application/vnd.emc.captiva+json, application/json")
                .addHeader("Accept-Language", "en-US")
                .addHeader("Cookie", getCookieString())
                .build();
        Response response = chain.proceed(request);
        return response;

    }

    private String getCookieString(){
        return "CPTV-TICKET=" + CookieRepo.sessionCookie.getCookie();
    }

}
