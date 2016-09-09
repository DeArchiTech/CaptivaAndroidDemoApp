package emc.captiva.mobile.sdksampleapp.Util;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.exceptions.RealmMigrationNeededException;

/**
 * Created by david on 9/6/16.
 */
public class RealmUtil {

    public Realm createRealm(Context context){
        RealmConfiguration realmConfig;
        Realm realm;

        try{
            realmConfig = new RealmConfiguration.Builder(context).build();
            Realm.setDefaultConfiguration(realmConfig);
            realm = Realm.getDefaultInstance();
        }catch (RealmMigrationNeededException r){
            RealmConfiguration migrationConfig = new RealmConfiguration.Builder(context)
                    .deleteRealmIfMigrationNeeded()
                    .build();
            Realm.setDefaultConfiguration(migrationConfig);
            realm = Realm.getDefaultInstance();
        }
        return realm;
    }

    public Realm createTestRealm(Context context){

        RealmConfiguration realmConfig = new RealmConfiguration.Builder(context)
                .name("myrealm.realm")
                .inMemory()
                .build();
        Realm realm = Realm.getInstance(realmConfig);
        return realm;
    }

}
