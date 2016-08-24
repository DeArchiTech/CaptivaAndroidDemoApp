package emc.captiva.mobile.sdksampleapp.Network;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by david on 8/24/16.
 */
public class LoginInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        request = request.newBuilder()
                .addHeader("Content-Type", "application/vnd.emc.captiva+json; charset=utf-8")
                .addHeader("Accept", "application/vnd.emc.captiva+json, application/json")
                .addHeader("Accept-Language", "en-US")
                .build();
        Response response = chain.proceed(request);
        return response;

    }

}
