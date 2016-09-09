package emc.captiva.mobile.sdksampleapp.ActivityHelper;

import emc.captiva.mobile.sdksampleapp.Model.Cookie;
import emc.captiva.mobile.sdksampleapp.Repository.CookieRepo;

/**
 * Created by david on 9/9/16.
 */
public class MainActivityHelper {

    public boolean userIsLoggedIn() {
        return cookieIsValid(CookieRepo.sessionCookie);
    }

    private boolean cookieIsValid(Cookie cookie){

        return cookie!=null && cookie.getCookie()!=null;

    }
}
