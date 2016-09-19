package emc.captiva.mobile.sdksampleapp.Presenter;

import android.text.Editable;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import emc.captiva.mobile.sdksampleapp.BuildConfig;
import emc.captiva.mobile.sdksampleapp.CreateFilterProfileActivity;
import emc.captiva.mobile.sdksampleapp.Service.CreateProfileService;
import emc.captiva.mobile.sdksampleapp.View.CreateProfileView;

/**
 * Created by Davix on 9/19/16.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class CreateProfilePresenterTest {

    @Mock
    CreateProfilePresenter presenter;
    @Mock
    CreateProfileView view;
    @Mock
    CreateProfileService service;
    @Mock
    CreateFilterProfileActivity activity;
    @Mock
    Editable editable;
    @Before
    public void setUp() throws Exception {

        this.view = Mockito.mock(CreateProfileView.class);
        this.service = Mockito.mock(CreateProfileService.class);
        this.activity = Mockito.mock(CreateFilterProfileActivity.class);
        this.editable = Mockito.mock(Editable.class);
        this.editable = new Editable.Factory().newEditable("abcd\n");
        this.presenter = new CreateProfilePresenter(this.view, this.service, this.activity);
    }

    @Test
    public void testEndsWithNewLine() throws Exception {

        boolean result = this.presenter.endsWithNewLine(this.editable);
        Assert.assertTrue(result);

    }

    @Test
    public void takeOutNewLineAtEnd() throws Exception {

        Editable result = this.presenter.takeOutNewLineAtEnd(this.editable);
        Assert.assertEquals("abcd" , result.toString());

    }

}