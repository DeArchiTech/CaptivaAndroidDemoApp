package emc.captiva.mobile.sdksampleapp.Presenter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import emc.captiva.mobile.sdksampleapp.Constant;
import emc.captiva.mobile.sdksampleapp.EnhanceImageActivity;
import emc.captiva.mobile.sdksampleapp.Model.FilterProfile;
import emc.captiva.mobile.sdksampleapp.Repository.FilterProfileRepo;
import io.realm.Realm;
import static org.mockito.Mockito.verify;

/**
 * Created by Davix on 9/14/16.
 */
public class EnhanceImagePresenterTest {

    @Mock
    FilterProfileRepo repo;
    EnhanceImagePresenter presenter;
    EnhanceImageActivity activity;

    @Before
    public void setUp(){
        this.repo = Mockito.mock(FilterProfileRepo.class);
        this.activity = Mockito.mock(EnhanceImageActivity.class);
        this.presenter = new EnhanceImagePresenter(this.activity, this.repo);
    }

    @Test
    public void loadFilterProfileSuccess() throws Exception {

        int id = 11;
        this.presenter.loadFilterProfile(id,this.presenter,this.presenter);
        verify(this.repo).loadProfileAsync(Matchers.anyInt()
                ,Matchers.any(Realm.Transaction.OnSuccess.class)
                ,Matchers.any(Realm.Transaction.OnError.class));

    }

    @Test
    public void loadFilterProfilefFail() throws Exception {

        int id = Constant.invalidId;
        this.presenter.loadFilterProfile(id,this.presenter,this.presenter);
        verify(this.repo, Mockito.times(0)).loadProfileAsync(Matchers.anyInt()
                ,Matchers.any(Realm.Transaction.OnSuccess.class)
                ,Matchers.any(Realm.Transaction.OnError.class));


    }

    @Test
    public void onError() throws Exception {

        Throwable throwable = Mockito.mock(Throwable.class);
        this.presenter.onError(throwable);
    }

    @Test
    public void onSuccess() throws Exception {

        FilterProfile profile = Mockito.mock(FilterProfile.class);
        FilterProfileRepo.lastProfileLoaded = profile;
        this.presenter.onSuccess();
        verify(this.activity).attemptToApplyFilters(Matchers.anyList());

    }

}