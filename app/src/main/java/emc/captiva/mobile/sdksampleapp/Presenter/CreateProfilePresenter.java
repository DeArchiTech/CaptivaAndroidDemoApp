package emc.captiva.mobile.sdksampleapp.Presenter;

import android.content.Context;

import emc.captiva.mobile.sdksampleapp.Model.FilterProfile;
import emc.captiva.mobile.sdksampleapp.R;
import emc.captiva.mobile.sdksampleapp.Service.CreateProfileService;
import emc.captiva.mobile.sdksampleapp.View.CreateProfileView;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by giovanni on 05/09/16.
 */
public class CreateProfilePresenter {

    CreateProfileView view;
    CreateProfileService service;
    Context context;

    public CreateProfilePresenter(CreateProfileView view, CreateProfileService service, Context context) {
        this.view = view;
        this.service = service;
        this.context = context;
    }

    public String[] getFilterList() {
        if(this.context!=null)
            return this.context.getResources().getStringArray(R.array.Filter_List);
        return null;
    }

    public void onReadAllProfiles(Realm.Transaction.OnSuccess onSuccess,
                                  Realm.Transaction.OnError onError,
                                  Realm realm) {
        //Call services to read all profile
        service.readProfiles(realm, onSuccess,onError);

    }


    public void onCreateProfile(FilterProfile profile, Realm.Transaction.OnSuccess onSuccess,
                                Realm.Transaction.OnError onError,
                                Realm realm) {
        service.createAndPersistProfile(profile,realm,onSuccess,onError);

    }
}
