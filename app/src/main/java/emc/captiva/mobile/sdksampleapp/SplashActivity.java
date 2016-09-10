package emc.captiva.mobile.sdksampleapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import emc.captiva.mobile.sdksampleapp.Repository.CookieRepo;
import emc.captiva.mobile.sdksampleapp.Util.RealmUtil;
import emc.captiva.mobile.sdksampleapp.Util.UIUtils;
import io.realm.Realm;

/**
 * Created by david on 9/9/16.
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        loadCookieFromDB();
    }

    @Override
    protected void onPause() {

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
                displayCustomToast("Login" , "Successful" , "Cookie Read Successfully");
            }
        };
    }

    private Realm.Transaction.OnError createErrorCallBack() {
        return new Realm.Transaction.OnError(){
            @Override
            public void onError(Throwable error) {
                displayCustomToast("Login" , "Unsuccessful" , "Cookie Expired, please try to login again");
            }
        };
    }

    private Realm getRealmInstance() {

        return new RealmUtil().createRealm(this);
    }

    private void displayCustomToast(String action , String result, String description) {

        new UIUtils().createAlertDialog(this, action,result,description, createListener());

    }

    private DialogInterface.OnClickListener createListener() {

        return new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // FIRE ZE MISSILES!
                SplashActivity.this.startMainActivity();
            }
        };
    }

    private void startMainActivity(){

        Intent intent = new Intent(SplashActivity.this,MainActivity.class);
        startActivity(intent);

    }

}