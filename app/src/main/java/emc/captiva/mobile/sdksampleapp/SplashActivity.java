package emc.captiva.mobile.sdksampleapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import emc.captiva.mobile.sdksampleapp.Repository.CookieRepo;
import emc.captiva.mobile.sdksampleapp.Util.RealmUtil;
import io.realm.Realm;

/**
 * Created by david on 9/9/16.
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        loadCookieFromDB();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
    
    private void loadCookieFromDB(){

        CookieRepo repo = new CookieRepo();
        repo.readCookieAsync(getRealmInstance(), createSuccessCallBack(), createErrorCallBack());
       
    }

    private Realm.Transaction.OnSuccess createSuccessCallBack() {
        return new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
            }
        };
    }

    private Realm.Transaction.OnError createErrorCallBack() {
        return new Realm.Transaction.OnError(){
            @Override
            public void onError(Throwable error) {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
            }
        };
    }

    private Realm getRealmInstance() {

        return new RealmUtil().createRealm(this);
    }

}