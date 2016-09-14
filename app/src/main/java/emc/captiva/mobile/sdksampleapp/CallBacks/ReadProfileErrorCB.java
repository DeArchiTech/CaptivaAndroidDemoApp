package emc.captiva.mobile.sdksampleapp.CallBacks;

import emc.captiva.mobile.sdksampleapp.Presenter.MainActivityPresenter;
import io.realm.Realm;
/**
 * Created by Davix on 9/13/16.
 */

public class ReadProfileErrorCB implements Realm.Transaction.OnError {

    MainActivityPresenter presenter;

    public ReadProfileErrorCB(MainActivityPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onError(Throwable error) {
        this.presenter.displayCustomToast("Read Profile" , "Failed" , "Failed in trying to read profiles");
    }
}
