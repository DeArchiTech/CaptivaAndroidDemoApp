package emc.captiva.mobile.sdksampleapp.Presenter;

import emc.captiva.mobile.sdksampleapp.Repository.CookieRepo;
import io.realm.Realm;

/**
 * Created by Davix on 9/12/16.
 */

public class SplashActivityPresenter {

    Realm realm;
    CookieRepo repo;

    public SplashActivityPresenter(Realm realm, CookieRepo repo) {
        this.realm = realm;
        this.repo = repo;
    }

    public void loadCookieFromDb(Realm.Transaction.OnSuccess onSuccess
            , Realm.Transaction.OnError onError){
        this.repo.readCookieAsync(getRealm(), onSuccess , onError);
    }

    public Realm getRealm() {
        return realm;
    }

}
