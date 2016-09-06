package emc.captiva.mobile.sdksampleapp.UnitTest;

import junit.framework.TestCase;
import android.test.suitebuilder.annotation.SmallTest;
import org.mockito.Mock;
import org.mockito.Mockito;
import emc.captiva.mobile.sdksampleapp.Presenter.CreateProfilePresenter;
import emc.captiva.mobile.sdksampleapp.Service.CreateProfileService;
import emc.captiva.mobile.sdksampleapp.View.CreateProfileView;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by giovanni on 05/09/16.
 */

public class CreateProfilePresenterTest extends TestCase{

    @Mock
    private CreateProfileView view;
    @Mock
    private CreateProfileService service;

    @SmallTest
    public void testGetName() throws Exception {

        view = Mockito.mock(CreateProfileView.class);
        when(view.getProfileName()).thenReturn("momotest");
        CreateProfilePresenter presenter = new CreateProfilePresenter(view,service);

        String result = view.getProfileName();
        verify(view).getProfileName();
        assertEquals(result, "momotest");
    }
}
