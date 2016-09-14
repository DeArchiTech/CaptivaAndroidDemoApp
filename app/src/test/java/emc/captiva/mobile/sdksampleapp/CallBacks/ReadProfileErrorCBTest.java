package emc.captiva.mobile.sdksampleapp.CallBacks;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import emc.captiva.mobile.sdksampleapp.Presenter.MainActivityPresenter;
import emc.captiva.mobile.sdksampleapp.BuildConfig;
import static org.mockito.Mockito.verify;
import org.mockito.Matchers;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by Davix on 9/13/16.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ReadProfileErrorCBTest {

    @Mock
    MainActivityPresenter presenter;
    ReadProfileErrorCB cb ;

    @Before
    public void setUp() throws Exception {
        presenter = Mockito.mock(MainActivityPresenter.class);
        cb = new ReadProfileErrorCB(presenter);
    }

    @Test
    public void onError() throws Exception {

        cb.onError(null);
        verify(presenter).displayCustomToast(Matchers.anyString()
                ,Matchers.anyString()
                ,Matchers.anyString());
    }

}