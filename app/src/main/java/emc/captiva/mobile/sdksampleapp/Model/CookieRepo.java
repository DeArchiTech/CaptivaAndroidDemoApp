package emc.captiva.mobile.sdksampleapp.Model;


import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by david on 9/1/16.
 */
public class CookieRepo {

    public static Cookie sessionCookie;

    //Delete All The Cookies and persist one cookie
    public void createAndPersistOneCookie(final String cookieString, Realm realm,
                                          Realm.Transaction.OnSuccess onSuccess,
                                          Realm.Transaction.OnError onError){

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgrealm) {
                CookieRepo.this.createAndPersistSync(bgrealm,cookieString);
            }
        },onSuccess,onError);

    }

    public void createAndPersistSync(Realm bgrealm,String cookieString){

        //Delete Cookies
        RealmResults<Cookie> cookies = bgrealm.where(Cookie.class).findAll();

        //Persist One Cookie
        Cookie cookie = bgrealm.createObject(Cookie.class);
        cookie.setCookie(cookieString);
        CookieRepo.this.sessionCookie = cookie;

    }

    public void createCookieAync(final String cookieString, final Realm realm, Realm.Transaction.OnSuccess onSuccess
            , Realm.Transaction.OnError onError){

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgrealm) {
                CookieRepo.this.createCookieSync(realm,cookieString);
            }
        },onSuccess,onError);

    }

    public void createCookieSync(Realm bgrealm,String cookieString){

        Cookie cookie = bgrealm.createObject(Cookie.class);
        cookie.setCookie(cookieString);
        CookieRepo.this.sessionCookie = cookie;
    }

    public void readCookieAsync(Realm realm,Realm.Transaction.OnSuccess onSuccess
            , Realm.Transaction.OnError onError){

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgrealm) {

                CookieRepo.this.readCookieSync(bgrealm);
            }
        },onSuccess,onError);
    }

    public void readCookieSync(Realm bgrealm){

        final RealmResults<Cookie> cookies = bgrealm.where(Cookie.class).findAll();
        if(cookies.size() > 0){
            CookieRepo.sessionCookie=cookies.first();
        }else{
            CookieRepo.sessionCookie=null;
        }
    }

    public void deleteCookie(Realm realm,Realm.Transaction.OnSuccess onSuccess
            , Realm.Transaction.OnError onError){

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgrealm) {

                CookieRepo.this.deleteCookieSync(bgrealm);
            }
        },onSuccess,onError);
    }

    public void deleteCookieSync(Realm bgrealm){

        final RealmResults<Cookie> cookies = bgrealm.where(Cookie.class).findAll();
        cookies.deleteAllFromRealm();
        if(cookies.size() == 0){
            CookieRepo.sessionCookie=null;
        }
    }

}
