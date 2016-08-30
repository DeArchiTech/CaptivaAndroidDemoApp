package emc.captiva.mobile.sdksampleapp;

import android.content.Context;
import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.TestCase;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import emc.captiva.mobile.sdksampleapp.Util.ResponseUtil;
import emc.captiva.mobile.sdksampleapp.Util.StringUtil;
import retrofit2.Response;

/**
 * Created by david on 8/30/16.
 */
public class ResponseUtilTest extends TestCase{
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @SmallTest
    public void testGetCookieFromResponse(){

        //Todo Implement
        //1)Mock Up Objects
        String jsonFileName = "loginObject.json";

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(jsonFileName);
        String responseString = StringUtil.getStringFromInputStream(in);

        //2)Call Method
        String result = new ResponseUtil().getCookieFromResponse(responseString);

        //3)Assert Result is correct
        assertNotNull(result);
        assertEquals(result , "SK22>>>>*!9381189715979665605/jbRgxRa7Yw7TVPt+oLguhB/TRET9C5EJoPVN0THKcx75JaQaZ+pnqj6V/iGCRdQ1acNZuQyMx0reNZ5BIqU0lx9s4lmwPdOmf95riSEqkz6MDJfrUYV/6XBbw0XvSpvWG5gd2LYqliXjEmf6H/tuxr4Ngi+EGyOdXLNe0adM3GXSuSKfI75bYLg9zB+A7X/AL6QXeMcF7VbKoRCRYtBTJ5pg5F04qx7+aMGlEbgdrRF46VLxQJvvVNs0ixV8ijPeOmXkQasrne113jXhKVvDOcAlmPpLyiGKF3XISKFV3LYnyg==*>>>>");
    }


}
