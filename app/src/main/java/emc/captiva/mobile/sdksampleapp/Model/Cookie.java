package emc.captiva.mobile.sdksampleapp.Model;
import io.realm.RealmObject;

/**
 * Created by david on 8/30/16.
 */
public class Cookie extends RealmObject{

    private String cookie;

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

}
