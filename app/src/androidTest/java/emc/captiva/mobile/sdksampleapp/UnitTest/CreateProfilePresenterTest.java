package emc.captiva.mobile.sdksampleapp.UnitTest;

import junit.framework.TestCase;

import android.content.Context;
import android.content.res.Resources;
import android.test.suitebuilder.annotation.SmallTest;
import org.mockito.Mock;
import org.mockito.Mockito;
import emc.captiva.mobile.sdksampleapp.Presenter.CreateProfilePresenter;
import emc.captiva.mobile.sdksampleapp.R;
import emc.captiva.mobile.sdksampleapp.Service.CreateProfileService;
import emc.captiva.mobile.sdksampleapp.View.CreateProfileView;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by giovanni on 05/09/16.
 */

public class CreateProfilePresenterTest extends TestCase{

    @Mock
    private Context context;
    @Mock
    private CreateProfileView view;
    @Mock
    private CreateProfileService service;

    String[] filters = new String[2];
    CreateProfilePresenter presenter;

    @Override
    protected void setUp() throws Exception {

        super.setUp();
        //1)Mock Objects
        view = Mockito.mock(CreateProfileView.class);
        context = Mockito.mock(Context.class);
        Resources resources = Mockito.mock(Resources.class);
        filters = new String[3];
        filters[0] = "filter A";
        filters[1] = "filter B";
        when(resources.getStringArray(R.array.Filter_List)).thenReturn(filters);
        when(context.getResources()).thenReturn(resources);
        presenter = new CreateProfilePresenter(view,service,context);
    }

    @SmallTest
    public void testGetFilterList() throws Exception {

        String result[] = presenter.getFilterList();

        assertEquals(result[0], filters[0]);
        assertEquals(result[1], filters[1]);

    }

    @SmallTest
    public void testOnProfileLoadResponse(){

        //Call Presents Http Result
        presenter.onProfileLoadResponse();

        //Assert View's displayView gets called
        verify(view).onProfilesLoadResponse();

    }

    @SmallTest
    public void onReadAllProfiles(){

        presenter.onReadAllProfiles();

        verify(service).readProfiles(null);

    }

    @SmallTest
    public void onCreateProfile(){

        presenter.onCreateProfile(null);

        verify(service).createAndPersistProfile(null,null,null,null);

    }

}
