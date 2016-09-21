package emc.captiva.mobile.sdksampleapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import emc.captiva.mobile.sdksampleapp.Presenter.SplashActivityPresenter;
import emc.captiva.mobile.sdksampleapp.Repository.CookieRepo;
import emc.captiva.mobile.sdksampleapp.Util.RealmUtil;
import emc.captiva.mobile.sdksampleapp.Util.UIUtils;
import emc.captiva.mobile.sdksampleapp.Util.Util;
import io.realm.Realm;


/**
 * Created by david on 9/9/16.
 */
public class SplashActivity extends Activity {

    long tStart ;
    long desiredWaitTIme = 3000;
    SplashActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        tStart = System.currentTimeMillis();

    }

    @Override
    protected void onResume() {

        super.onResume();
        this.presenter = new SplashActivityPresenter(getRealmInstance(), new CookieRepo());
        this.presenter.loadCookieFromDb(createSuccessCallBack(), createErrorCallBack());

    }

    @Override
    protected void onPause() {

        super.onPause();

    }

    private Realm.Transaction.OnSuccess createSuccessCallBack() {
        return new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                while(!enoughTimeHasPassed()){
                    try{
                        Thread.sleep(desiredWaitTIme);
                    }catch(Exception e){

                    }
                    SplashActivity.this.startMainActivity();
                }
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

    public Realm getRealmInstance(){

        return new RealmUtil().createRealm(this);
    }

    private void displayCustomToast(String action , String result, String description) {

        new UIUtils().createAlertDialog(this, action,result,description, createListener());

    }

    private boolean enoughTimeHasPassed(){
        long current = System.currentTimeMillis();
        return new Util().enoughtTimeHasPassed(current, tStart, this.desiredWaitTIme);
    }

    private DialogInterface.OnClickListener createListener() {

        return new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                SplashActivity.this.startMainActivity();
            }
        };
    }

    private void startMainActivity(){

        Intent intent = new Intent(SplashActivity.this,MainActivity.class);
        startActivity(intent);

    }

}