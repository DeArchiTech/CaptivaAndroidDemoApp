package emc.captiva.mobile.sdksampleapp.Presenter;
import android.os.Bundle;

import junit.framework.Assert;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import emc.captiva.mobile.sdksampleapp.BuildConfig;
import emc.captiva.mobile.sdksampleapp.Constant;
import emc.captiva.mobile.sdksampleapp.EnhanceImageActivity;
import emc.captiva.mobile.sdksampleapp.Model.FilterProfile;
import emc.captiva.mobile.sdksampleapp.R;
import emc.captiva.mobile.sdksampleapp.Repository.FilterProfileRepo;
import io.realm.Realm;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Davix on 9/14/16.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
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

    @Test
    public void testGetProfileIDPass(){

        Bundle bundle = Mockito.mock(Bundle.class);
        int id = 100 ;
        String key = this.activity.getString(R.string.intent_profile_key);
        when(bundle.containsKey(Matchers.anyString())).thenReturn(true);
        when(bundle.getInt(key)).thenReturn(id);
        Assert.assertTrue(bundle.containsKey(key));
        int result = this.presenter.getProfileId(bundle);
        Assert.assertEquals(id, result);
    }

    @Test
    public void testGetProfileIDFail(){

        Bundle bundle = Mockito.mock(Bundle.class);
        String key = this.activity.getString(R.string.intent_profile_key);
        when(bundle.containsKey(Matchers.anyString())).thenReturn(false);
        Assert.assertFalse(bundle.containsKey(key));
        int result = this.presenter.getProfileId(bundle);
        Assert.assertEquals(-1, result);
    }

}