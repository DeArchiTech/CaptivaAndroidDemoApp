package emc.captiva.mobile.sdksampleapp.Presenter;

import android.content.Context;

import emc.captiva.mobile.sdksampleapp.Model.FilterProfile;
import emc.captiva.mobile.sdksampleapp.R;
import emc.captiva.mobile.sdksampleapp.Service.CreateProfileService;
import emc.captiva.mobile.sdksampleapp.View.CreateProfileView;

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

    public void onProfileLoadResponse() {
        view.onProfilesLoadResponse();
    }

    public void onReadAllProfiles() {


    }

    public void onCreateProfile(FilterProfile profile) {
    }
}
