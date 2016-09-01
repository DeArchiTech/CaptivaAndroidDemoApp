package emc.captiva.mobile.sdksampleapp.Model;

import android.util.Log;
import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by david on 9/1/16.
 */
public class UserSettingsManager {

    public static int defaultId = 0;
    public static UserSettingsManager build(){
        return new UserSettingsManager();
    }

    private UserSettings settings;

    private UserSettingsManager() {

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgrealm) {
                UserSettings settings = bgrealm.createObject(UserSettings.class);
                settings.setId(UserSettingsManager.defaultId);
                settings.setAutoMaticallyApplyFilter(false);
                UserSettingsManager.this.settings = settings;
            }
        },new Realm.Transaction.OnSuccess(){
            @Override
            public void onSuccess() {
                Log.i("Realm" , "UserSettings save succeed");
            }
        }, new Realm.Transaction.OnError(){
            @Override
            public void onError(Throwable error) {
                Log.i("Realm" , "UserSettings save failed");
            }
        });

    }

    public void updateFilters(final RealmList<Filter> filters){

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgrealm) {
                UserSettingsManager.this.settings.setFilters(filters);
            }
        },new Realm.Transaction.OnSuccess(){
            @Override
            public void onSuccess() {
                Log.i("Realm" , "UserSettings save succeed");
            }
        }, new Realm.Transaction.OnError(){
            @Override
            public void onError(Throwable error) {
                Log.i("Realm" , "UserSettings save failed");
            }
        });
    }

    public void updateAutoMaticallyApplyFilter(final boolean enable){

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgrealm) {
                UserSettingsManager.this.settings.setAutoMaticallyApplyFilter(enable);
            }
        },new Realm.Transaction.OnSuccess(){
            @Override
            public void onSuccess() {
                Log.i("Realm" , "UserSettings save succeed");
            }
        }, new Realm.Transaction.OnError(){
            @Override
            public void onError(Throwable error) {
                Log.i("Realm" , "UserSettings save failed");
            }
        });

    }

}
