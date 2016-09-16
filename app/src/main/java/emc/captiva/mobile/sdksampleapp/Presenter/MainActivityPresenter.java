package emc.captiva.mobile.sdksampleapp.Presenter;

import java.util.ArrayList;
import java.util.List;

import emc.captiva.mobile.sdksampleapp.Constant;
import emc.captiva.mobile.sdksampleapp.MainActivity;
import emc.captiva.mobile.sdksampleapp.Model.Cookie;
import emc.captiva.mobile.sdksampleapp.Model.FilterProfile;
import emc.captiva.mobile.sdksampleapp.R;
import emc.captiva.mobile.sdksampleapp.Repository.CookieRepo;
import emc.captiva.mobile.sdksampleapp.Repository.FilterProfileRepo;
import emc.captiva.mobile.sdksampleapp.Util.UIUtils;
import io.realm.Realm;

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

        List<FilterProfile> validList = getListWithValidName(list);
        validList.add(0,createPromptItem());
        this.activity.updateSpinnerList(validList);
    }

    public void displayCustomToast(String action , String result, String description) {

        new UIUtils().createAlertDialog(this.activity, action,result,description);

    }

    public int getIdFromAdapterObject(Object item) {

        if(item instanceof FilterProfile){
            FilterProfile profile = (FilterProfile)item;
            String promptString = this.activity.getString(R.string.mainPage_spinner_prompt);
            String profileName = profile.getProfileName();
            if(!profileName.equalsIgnoreCase(promptString))
                return profile.getId();
        }
        return Constant.invalidId;
    }

    public List<FilterProfile> getListWithValidName(List<FilterProfile> profiles) {

        List<FilterProfile> result = new ArrayList<FilterProfile>();
        for(FilterProfile item : profiles){
            if(item.getProfileName() != null)
                result.add(item);
        }
        return result;
    }

    private FilterProfile createPromptItem(){

        FilterProfile profile = new FilterProfile();
        profile.setProfileName(activity.getString(R.string.mainPage_spinner_prompt));
        profile.setId(Constant.invalidId);
        return profile;
    }
}
