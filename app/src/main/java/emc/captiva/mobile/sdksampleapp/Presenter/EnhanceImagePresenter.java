package emc.captiva.mobile.sdksampleapp.Presenter;
import android.view.MenuItem;
import emc.captiva.mobile.sdksampleapp.EnhanceImageActivity;
import emc.captiva.mobile.sdksampleapp.Model.FilterProfile;
import emc.captiva.mobile.sdksampleapp.R;
import emc.captiva.mobile.sdksampleapp.Repository.FilterProfileRepo;
import emc.captiva.mobile.sdksampleapp.Util.FilterToMenuUtil;
import io.realm.Realm;

/**
 * Created by Davix on 9/14/16.
 */

public class EnhanceImagePresenter implements Realm.Transaction.OnError, Realm.Transaction.OnSuccess{

    private FilterProfileRepo repo;
    private EnhanceImageActivity activity;

    public EnhanceImagePresenter(EnhanceImageActivity activity,FilterProfileRepo repo) {
        this.repo = repo;
        this.activity = activity;
    }

    public void loadFilterProfile(int profileId , Realm.Transaction.OnSuccess onSuccess
            , Realm.Transaction.OnError onError){
        this.repo.loadProfileAsync(profileId, onSuccess, onError);
    }

    @Override
    public void onError(Throwable error) {
        this.activity.displayCustomToast("Load Profile", "Failed", error.toString());
    }

    @Override
    public void onSuccess() {

        FilterProfile profileLoaded = FilterProfileRepo.lastProfileLoaded;
        if(profileLoaded != null){
            this.activity.applyFiltersFromProfileSettings(profileLoaded);
        }

    }

    public MenuItem createMenuOptionFromFilterString(String item){
        return new FilterToMenuUtil().getMenuFromFilterString(item);
    }

}
