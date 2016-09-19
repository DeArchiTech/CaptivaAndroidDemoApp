package emc.captiva.mobile.sdksampleapp.Presenter;

import android.content.Context;
import android.text.Editable;
import android.widget.EditText;

import java.util.List;

import emc.captiva.mobile.sdksampleapp.ListItem.FilterListItem;
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

    public boolean atLeastOneFilterSelected(List<FilterListItem> listItems) {

        return listItems.isEmpty() == false;
    }

    public boolean filterNameIsSet(EditText editText, String defaultString){

        if(editText != null){
            String text = editText.getText().toString();
            return (!text.isEmpty()) && (!text.equalsIgnoreCase(defaultString));
        }
        return false;

    }

    public boolean endsWithNewLine(Editable s){

        String string = s.toString();
        if(string.length() > 1){
            String subString = string.subSequence(string.length()-1, string.length()).toString();
            if(subString.equalsIgnoreCase("\n")){
                return true;
            }
        }
        return false;

    }

    public Editable takeOutNewLineAtEnd(Editable s){

        String string = s.toString();
        if(string.length() > 1){
            s.delete(string.length()-1, string.length());
            return s;
        }
        return s;
    }

}
