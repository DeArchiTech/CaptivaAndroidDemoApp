package emc.captiva.mobile.sdksampleapp.ActivityHelper;

import java.util.ArrayList;
import java.util.List;

import emc.captiva.mobile.sdksampleapp.MainActivity;
import emc.captiva.mobile.sdksampleapp.Model.Cookie;
import emc.captiva.mobile.sdksampleapp.Model.Filter;
import emc.captiva.mobile.sdksampleapp.Model.FilterProfile;
import emc.captiva.mobile.sdksampleapp.Repository.CookieRepo;
import emc.captiva.mobile.sdksampleapp.Repository.FilterProfileRepo;
import emc.captiva.mobile.sdksampleapp.Util.UIUtils;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by david on 9/9/16.
 */
public class MainActivityPresenter {

    FilterProfileRepo repo;
    MainActivity activity;
    public boolean userIsLoggedIn() {
        return cookieIsValid(CookieRepo.sessionCookie);
    }

    private boolean cookieIsValid(Cookie cookie){

        return cookie!=null && cookie.getCookie()!=null;

    }

    public MainActivityPresenter(FilterProfileRepo repo, MainActivity activity) {
        this.repo = repo;
        this.activity = activity;
    }

    public void readProfileList(Realm.Transaction.OnSuccess onSuccess,
                                Realm.Transaction.OnError onError){
        this.repo.readProfiles(onSuccess,onError);
    }

    public ArrayList<String> getStringFromProfileList(List<FilterProfile> profileList){
        ArrayList<String> list = new ArrayList<>();
        for(FilterProfile item : profileList){
            list.add(item.getProfileName());
        }
        return list;
    }

    public void loadProfileIntoSpinner(List<FilterProfile> list){

        List<String> stringList = new ArrayList<>();
        for(FilterProfile profile: list){
            String name = profile.getProfileName();
            if(name != null){
                stringList.add(profile.getProfileName());
            }
        }
        String[] spinnerStrings = stringList.toArray(new String[0]);
        this.activity.updateSpinnerList(spinnerStrings);
    }

    public void displayCustomToast(String action , String result, String description) {

        new UIUtils().createAlertDialog(this.activity, action,result,description);

    }

}
