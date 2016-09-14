package emc.captiva.mobile.sdksampleapp.CallBacks;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import emc.captiva.mobile.sdksampleapp.ActivityHelper.MainActivityPresenter;
import emc.captiva.mobile.sdksampleapp.BuildConfig;


import static org.mockito.Mockito.verify;
/**
 * Created by Davix on 9/13/16.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ReadProfileSuccessCBTest {

    @Mock
    MainActivityPresenter presenter;
    ReadProfileSuccessCB cb;

    @Before
    public void setUp() throws Exception {

        presenter = Mockito.mock(MainActivityPresenter.class);
        cb = new ReadProfileSuccessCB(presenter);
    }

    @Test
    public void onSuccess() throws Exception {

        cb.onSuccess();
        verify(presenter).loadProfileIntoSpinner(Matchers.any(ArrayList.class));
    }

}