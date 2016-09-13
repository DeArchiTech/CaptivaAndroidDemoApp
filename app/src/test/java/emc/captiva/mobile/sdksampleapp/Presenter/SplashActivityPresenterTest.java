package emc.captiva.mobile.sdksampleapp.Presenter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import emc.captiva.mobile.sdksampleapp.BuildConfig;
import emc.captiva.mobile.sdksampleapp.Model.FilterProfile;
import emc.captiva.mobile.sdksampleapp.Repository.CookieRepo;
import io.realm.Realm;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

/**
 * Created by Davix on 9/12/16.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class SplashActivityPresenterTest {

    @Mock
    CookieRepo repo;
    @Test
    public void shouldloadCookieFromDb(){

        repo = Mockito.mock(CookieRepo.class);
        SplashActivityPresenter presenter = new SplashActivityPresenter(null,repo);
        doNothing().when(repo).readCookieAsync(Matchers.any(Realm.class)
                , Matchers.any(Realm.Transaction.OnSuccess.class)
                , Matchers.any(Realm.Transaction.OnError.class));
        presenter.loadCookieFromDb(null,null);
        verify(repo).readCookieAsync(Matchers.any(Realm.class)
                , Matchers.any(Realm.Transaction.OnSuccess.class)
                , Matchers.any(Realm.Transaction.OnError.class));
    }

}

