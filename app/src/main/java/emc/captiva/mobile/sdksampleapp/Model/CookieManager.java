package emc.captiva.mobile.sdksampleapp.Model;


import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by david on 9/1/16.
 */
public class CookieManager {

    public static Cookie sessionCookie;

    //Delete All The Cookies and persist one cookie
    public void createAndPersistOneCookie(final String cookieString, Realm realm,
                                          Realm.Transaction.OnSuccess onSuccess,
                                          Realm.Transaction.OnError onError){

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgrealm) {
                CookieManager.this.createAndPersistSync(bgrealm,cookieString);
            }
        },onSuccess,onError);

    }

    public void createAndPersistSync(Realm bgrealm,String cookieString){

        //Delete Cookies
        RealmResults<Cookie> cookies = bgrealm.where(Cookie.class).findAll();

        //Persist One Cookie
        Cookie cookie = bgrealm.createObject(Cookie.class);
        cookie.setCookie(cookieString);
        CookieManager.this.sessionCookie = cookie;

    }

    public void createCookieAync(final String cookieString, final Realm realm, Realm.Transaction.OnSuccess onSuccess
            , Realm.Transaction.OnError onError){

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgrealm) {
                CookieManager.this.createCookieSync(realm,cookieString);
            }
        },onSuccess,onError);

    }

    public void createCookieSync(Realm bgrealm,String cookieString){

        Cookie cookie = bgrealm.createObject(Cookie.class);
        cookie.setCookie(cookieString);
        CookieManager.this.sessionCookie = cookie;
    }

    public void readCookieAsync(Realm realm,Realm.Transaction.OnSuccess onSuccess
            , Realm.Transaction.OnError onError){

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgrealm) {

                CookieManager.this.readCookieSync(bgrealm);
            }
        },onSuccess,onError);
    }

    public void readCookieSync(Realm bgrealm){

        final RealmResults<Cookie> cookies = bgrealm.where(Cookie.class).findAll();
        if(cookies.size() > 0){
            CookieManager.sessionCookie=cookies.first();
        }else{
            CookieManager.sessionCookie=null;
        }
    }

    public void deleteCookie(Realm realm,Realm.Transaction.OnSuccess onSuccess
            , Realm.Transaction.OnError onError){

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgrealm) {

                CookieManager.this.deleteCookieSync(bgrealm);
            }
        },onSuccess,onError);
    }

    public void deleteCookieSync(Realm bgrealm){

        final RealmResults<Cookie> cookies = bgrealm.where(Cookie.class).findAll();
        cookies.deleteAllFromRealm();
        if(cookies.size() == 0){
            CookieManager.sessionCookie=null;
        }
    }

}
