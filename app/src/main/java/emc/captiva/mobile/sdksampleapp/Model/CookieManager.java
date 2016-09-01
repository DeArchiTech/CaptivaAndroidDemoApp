package emc.captiva.mobile.sdksampleapp.Model;

import android.util.Log;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by david on 9/1/16.
 */
public class CookieManager {

    public static Cookie sessionCookie;

    //Delete All The Cookies and persist one cookie
    public void createAndPersistOneCookie(final String cookieString){

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgrealm) {

                //Delete Cookies
                RealmResults<Cookie> cookies = bgrealm.where(Cookie.class).findAll();

                //Persist One Cookie
                Cookie cookie = bgrealm.createObject(Cookie.class);
                cookie.setCookie(cookieString);
                CookieManager.this.sessionCookie = cookie;

            }
        },new Realm.Transaction.OnSuccess(){
            @Override
            public void onSuccess() {
                Log.i("Realm" , "Cookie save succeed");
            }
        }, new Realm.Transaction.OnError(){
            @Override
            public void onError(Throwable error) {
                Log.i("Realm" , "Cookie save failed");
            }
        });

    }

    public void createCookie(final String cookieString){

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgrealm) {
                Cookie cookie = bgrealm.createObject(Cookie.class);
                cookie.setCookie(cookieString);
                CookieManager.this.sessionCookie = cookie;
            }
        },new Realm.Transaction.OnSuccess(){
            @Override
            public void onSuccess() {
                Log.i("Realm" , "Cookie save succeed");
            }
        }, new Realm.Transaction.OnError(){
            @Override
            public void onError(Throwable error) {
                Log.i("Realm" , "Cookie save failed");
            }
        });

    }

    public void readCookie(){

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgrealm) {

                final RealmResults<Cookie> cookies = bgrealm.where(Cookie.class).findAll();
                if(cookies.size() > 0){
                    CookieManager.sessionCookie=cookies.first();
                }
            }
        },new Realm.Transaction.OnSuccess(){
            @Override
            public void onSuccess() {
                Log.i("Realm" , "Cookie read succeed");
            }
        }, new Realm.Transaction.OnError(){
            @Override
            public void onError(Throwable error) {
                Log.i("Realm" , "Cookie read failed");
            }
        });
    }


    public void deleteCookie(){

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgrealm) {

                RealmResults<Cookie> cookies = bgrealm.where(Cookie.class).findAll();
                if (cookies.deleteAllFromRealm()){
                    CookieManager.sessionCookie = null;
                }
            }
        },new Realm.Transaction.OnSuccess(){
            @Override
            public void onSuccess() {
                Log.i("Realm" , "Cookie delete succeed");
            }
        }, new Realm.Transaction.OnError(){
            @Override
            public void onError(Throwable error) {
                Log.i("Realm" , "Cookie delete failed");
            }
        });
    }

}
