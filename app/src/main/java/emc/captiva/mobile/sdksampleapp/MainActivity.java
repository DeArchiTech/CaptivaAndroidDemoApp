/** -------------------------------------------------------------------------
 * Copyright 2013-2015 EMC Corporation.  All rights reserved.
 ---------------------------------------------------------------------------- */

package emc.captiva.mobile.sdksampleapp;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import emc.captiva.mobile.sdk.CaptureException;
import emc.captiva.mobile.sdk.CaptureImage;
import emc.captiva.mobile.sdk.CaptureWindow;
import emc.captiva.mobile.sdk.PictureCallback;
import emc.captiva.mobile.sdk.ContinuousCaptureCallback;
import emc.captiva.mobile.sdksampleapp.Activity.BaseActivity;
import emc.captiva.mobile.sdksampleapp.Dagger.ApplicationComponent;
import emc.captiva.mobile.sdksampleapp.Dagger.Revaapplication;
import emc.captiva.mobile.sdksampleapp.Presenter.MainActivityPresenter;
import emc.captiva.mobile.sdksampleapp.CallBacks.ReadProfileErrorCB;
import emc.captiva.mobile.sdksampleapp.CallBacks.ReadProfileSuccessCB;
import emc.captiva.mobile.sdksampleapp.JsonPojo.LoginResponseObj;
import emc.captiva.mobile.sdksampleapp.ListAdapter.ProfileDropDownAdapter;
import emc.captiva.mobile.sdksampleapp.Model.FilterProfile;
import emc.captiva.mobile.sdksampleapp.Repository.CookieRepo;
import emc.captiva.mobile.sdksampleapp.Repository.FilterProfileRepo;
import emc.captiva.mobile.sdksampleapp.ServiceBuilder.SessionServiceBuilder;
import emc.captiva.mobile.sdksampleapp.Util.UIUtils;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class represents the main window offering the ability to take a picture,
 * enhance an image from the gallery, or adjust the settings.
 */
public class MainActivity extends BaseActivity implements PictureCallback, ContinuousCaptureCallback, Realm.Transaction.OnSuccess,Realm.Transaction.OnError {
	static boolean _newLoad = true;
    private static String TAG = MainActivity.class.getSimpleName();
    private final int CHOOSE_IMAGE = 1;
    private int _captureCount = 0;
    static String USE_QUADVIEW = "UseQuadView";
    static String USE_MOTION = "UseMotion";
    CustomWindow _customWindow;
    private boolean loggedIn = false;
    private ProgressDialog dialog;
    private MainActivityPresenter presenter;
    private int profile_id = Constant.invalidId;

    @Inject
    Realm realm;

    @BindView(R.id.filterSpinner) Spinner spinner;
    @BindView(R.id.takecontinuouspicturesbtn) Button continousPicBtn;
    @BindView(R.id.enhancepicturebtn) Button enhancePicBtn;
    @BindView(R.id.takepicturebtn) Button takePicBtn;
    @BindView(R.id.LoginButton) Button buttonLogin;
    @BindView(R.id.CreateFilterProfile) Button createProfileButton;

    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // License the application
        CoreHelper.license(this);
        getApplicationComponent().inject(this);
        FilterProfileRepo repo = new FilterProfileRepo(getRealmInstance());
        this.presenter = new MainActivityPresenter(repo, this);
        this.loggedIn = presenter.userIsLoggedIn();
        this.loadFilterProfiles();
        this.profile_id = getProfileId(savedInstanceState);
        ButterKnife.bind(this);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(getString(R.string.intent_profile_key), this.profile_id);
        super.onSaveInstanceState(outState);
    }

    private void loadFilterProfiles(){

        ReadProfileSuccessCB successCB = new ReadProfileSuccessCB(this.presenter);
        ReadProfileErrorCB errorCB = new ReadProfileErrorCB(this.presenter);
        this.presenter.readProfileList(successCB, errorCB);

    }

    /**
     * Launches the camera window to take a picture, which saves to the image gallery.
     * @param view      The view for the control event.
     */

    @OnClick(R.id.takepicturebtn)
    public void onTakePicture(View view) {
        // Use a separate HashMap to hold non-TakePicture parameter values from preferences.
        if(!this.loggedIn){
            displayCustomToast("Take Picture" , "Failed" , "Please Log in before attempting Take Pictures");
            return;
        }
        HashMap<String, Object> appParams = new HashMap<>();
        // Obtain our picture parameters from the preferences. Only supported SDK keys should go into parameters.
        HashMap<String, Object> parameters = CoreHelper.getTakePictureParametersFromPrefs(this, appParams);

        // Get the preference for CaptureWindow
        SharedPreferences gprefs = PreferenceManager.getDefaultSharedPreferences(this);
        String capWndNone = CoreHelper.getStringResource(this, R.string.GPREF_CAPTURE_CUSTOM_OPTIONS_NONE);
        String capWndPref = gprefs.getString(CoreHelper.getStringResource(this, R.string.GPREF_CAPTURE_CUSTOM_OPTIONS), capWndNone);

        // Explicitly test for null to avoid linter warning.
        if (capWndPref == null) {
            capWndPref = capWndNone;
        }

        if (capWndPref.compareToIgnoreCase(capWndNone) != 0) {
            // Assign a custom CaptureWindow if specified by the prefs.
            CaptureWindow wnd = new CustomWindow(this, capWndPref, appParams);
            parameters.put(CaptureImage.PICTURE_CAPTUREWINDOW, wnd);
        }
        else if ((boolean) appParams.get(USE_QUADVIEW))
        {
            CaptureWindow wnd = new CustomWindow(this, capWndPref, appParams);
            parameters.put(CaptureImage.PICTURE_CAPTUREWINDOW, wnd);
        }

        // Launch the camera to take a picture.
    CaptureImage.takePicture(this, parameters);
    }

    /* (non-Javadoc)
     * @see emc.captiva.mobile.sdk.PictureCallback#onPictureTaken(byte[])
     */
    @Override
    public void onPictureTaken(byte[] imageData) {

        // Use our utility functions to obtain a unique filename to store into the image gallery.
        File fullpath = new File(CoreHelper.getImageGalleryPath(), CoreHelper.getUniqueFilename("Img", ".JPG"));
        try {
            // Use our utility function to save this JPG encoded byte array to storage.
            ByteArrayInputStream inputStream = new ByteArrayInputStream(imageData);       
            CoreHelper.saveFile(inputStream, fullpath);
            
            // Get a URI to broadcast and let Android know there is a new image in the gallery.
            Uri uri = Uri.fromFile(fullpath);
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            
            // Send the new picture taken to the enhancement screen so that users can modify it if necessary.
            gotoEnhanceImage(uri);
        } 
        catch (IOException e) {
            // Log a message and display an error using our utility function.
            Log.e(TAG, e.getMessage(), e);            
            CoreHelper.displayError(this, "Could not save the image to the gallery.");
        }
    }
    	
	/* (non-Javadoc)
     * @see emc.captiva.mobile.sdk.PictureCallback#onPictureCanceled(int)
     */
    @Override
    public void onPictureCanceled(int reason) {
        captureCanceled(reason);
    }

    // This captureCanceled() helper method is called by both onPictureCanceled() and onContinuousCaptureCanceled().
    private void captureCanceled(int reason) {
        // This callback will be called if the take picture operation was canceled.
        if (reason == PictureCallback.REASON_OPTIMAL_CONDITIONS) {
            CoreHelper.displayError(this, "The optimal conditions were not met and the picture was canceled.");
        } else if (reason == PictureCallback.REASON_CAMERA_ERROR) {
            StringBuilder errorReport = new StringBuilder();
            CaptureException ex = CaptureImage.getLastError();
            if (ex != null)
            {
                errorReport.append("\n\n************ Stack Trace ************\n\n");
                errorReport.append(Log.getStackTraceString(ex));
            }

            CoreHelper.displayError(this, "An error occurred while accessing the camera." + errorReport);
        }
    }

    /**
     * Launches the camera window to take a picture and then continually sends images to the ContinuousCaptureCallback.newImage() callback
     * until that callback returns false or the Activity is canceled by the back button.  It is the responsibility
     * of the caller to store any images (in that newImage() callback) that should be persisted.
     * @param view      The view for the control event.
     */
    @OnClick(R.id.takecontinuouspicturesbtn)
    public void onTakeContinuousPictures(View view) {
        if(!this.loggedIn){
            displayCustomToast("Taking Continuous picture" , "Failed" , "Please Log in before attempting to Take Continus Picture");
            return;
        }
        // Use a separate HashMap to hold non-TakePicture parameter values from preferences.
        HashMap<String, Object> appParams = new HashMap<>();
        // Obtain our picture parameters from the preferences. Only supported SDK keys should go into parameters.
        HashMap<String, Object> parameters = CoreHelper.getTakePictureParametersFromPrefs(this, appParams);

        // Get the preference for CaptureWindow
        SharedPreferences gprefs = PreferenceManager.getDefaultSharedPreferences(this);
        String capWndNone = CoreHelper.getStringResource(this, R.string.GPREF_CAPTURE_CUSTOM_OPTIONS_NONE);
        String capWndPref = gprefs.getString(CoreHelper.getStringResource(this, R.string.GPREF_CAPTURE_CUSTOM_OPTIONS), capWndNone);

        // Explicitly test for null to avoid the linter warning.
        if (capWndPref == null) {
            capWndPref = capWndNone;
        }

        if (capWndPref.compareToIgnoreCase(capWndNone) != 0) {
            // Assign a custom CaptureWindow if specified by the prefs.
            _customWindow = new CustomWindow(this, capWndPref, appParams);
            parameters.put(CaptureImage.PICTURE_CAPTUREWINDOW, _customWindow);
        }
        else if ((boolean) appParams.get(USE_QUADVIEW))
        {
            _customWindow = new CustomWindow(this, capWndPref, appParams);
            parameters.put(CaptureImage.PICTURE_CAPTUREWINDOW, _customWindow);
        }

        // Reset the capture counter.
        _captureCount = 0;

        // Launch the camera to take a picture.
        CaptureImage.continuousCapture(this, parameters);
    }

    /* (non-Javadoc)
     * @see emc.captiva.mobile.sdk.ContinuousCaptureCallback#newImage(byte[], Map)
     */
    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    @Override
    public ContinuousCaptureOperation newImage(byte[] imageData, Map<String, Object> qualityData) {
        CaptureException captureException;

        double newImageSimilarity = (double) qualityData.get(ContinuousCaptureCallback.CAPTURE_SIMILARITY);
        // Ensure that image data is retrieved.
        if (imageData == null) {
            CoreHelper.displayError(this, "Unable to retrieve image.");
            return ContinuousCaptureOperation.ContinuousCaptureStop;
        }

        // Get the similarity and count threshold values.
        SharedPreferences gprefs = PreferenceManager.getDefaultSharedPreferences(this);
        String temp = gprefs.getString(CoreHelper.getStringResource(this, R.string.GPREF_CAPTURESIMILARITY), "50");
        float similarityThresholdFromSettings = CoreHelper.getFloat(temp, 50f);
        temp = gprefs.getString(CoreHelper.getStringResource(this, R.string.GPREF_CAPTURECOUNT), "2");
        int captureCountLimit = CoreHelper.getInteger(temp, 2);

        // Only examine images that are different enough from the previous image that they could be
        // a new image (and not the same one that just happened to be caught by the capture interval).
        if (newImageSimilarity < similarityThresholdFromSettings ) {

            // First, load the image into the SDK for processing.
            CaptureImage.load(imageData);
            captureException = CaptureImage.getLastError();
            if (captureException != null) {
                CoreHelper.displayError(this, captureException.getMessage());
                return ContinuousCaptureOperation.ContinuousCaptureStop;
            }

            // Run autocrop on the captured image.
            Map<String, Object> imageProps = CaptureImage.getImageProperties();
            int shortSideFullSize = Math.min((int)imageProps.get(CaptureImage.IMAGE_PROPERTY_WIDTH), (int)imageProps.get(CaptureImage.IMAGE_PROPERTY_HEIGHT));
            CaptureImage.applyFilters(new String[]{CaptureImage.FILTER_CROP}, null);
            imageProps = CaptureImage.getImageProperties();
            // Only keep the cropped image if it is at least 10% the size of the short side of the full image to prevent over-cropping
            if (((int)imageProps.get(CaptureImage.IMAGE_PROPERTY_WIDTH) > shortSideFullSize / 10) && ((int)imageProps.get(CaptureImage.IMAGE_PROPERTY_HEIGHT) > shortSideFullSize / 10)) {
                // Save cropped image
                // Build the parameter map for saving the image.
                HashMap<String, Object> parameters = new HashMap<>();
                temp = gprefs.getString(CoreHelper.getStringResource(this, R.string.GPREF_DPIX), "72");
                int saveDpix = CoreHelper.getInteger(temp, 72);
                parameters.put(CaptureImage.SAVE_DPIX, saveDpix);
                temp = gprefs.getString(CoreHelper.getStringResource(this, R.string.GPREF_DPIY), "72");
                int saveDpiy = CoreHelper.getInteger(temp, 72);
                parameters.put(CaptureImage.SAVE_DPIY, saveDpiy);
                temp = gprefs.getString(CoreHelper.getStringResource(this, R.string.GPREF_JPGQUALITY), "95");
                int jpgQuality = CoreHelper.getInteger(temp, 95);
                parameters.put(CaptureImage.SAVE_JPG_QUALITY, jpgQuality);

                try {
                    // JPEG encode the cropped image.
                    imageData = CaptureImage.getEncodedBytes(CaptureImage.SAVE_JPG, parameters);
                } catch (CaptureException ex) {
                    CoreHelper.displayError(this, ex.getMessage());
                    return ContinuousCaptureOperation.ContinuousCaptureStop;
                }
            }

            // Use our utility functions to obtain a unique filename to store into the image gallery.
            File path = new File(CoreHelper.getImageGalleryPath(), CoreHelper.getUniqueFilename("Img", ".JPG"));
            ByteArrayInputStream inputStream = new ByteArrayInputStream(imageData);
            try {
                // Save the cropped and encoded image.
                CoreHelper.saveFile(inputStream, path);

                if (_customWindow != null) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            _customWindow.flashScreen();
                        }
                    });
                }
                Log.d(TAG, "Picture saved");

            } catch (IOException ex) {
                CoreHelper.displayError(this, ex.getMessage());
                return ContinuousCaptureOperation.ContinuousCaptureStop;
            }

            // Get a URI to broadcast and let Android know there is a new image in the gallery.
            Uri uri = Uri.fromFile(path);
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));

            // Once the number of captured images reaches the capture count limit, stop continuous
            // capture processing.
            if (++_captureCount >= captureCountLimit) {
                return ContinuousCaptureOperation.ContinuousCaptureStop;
            }
        } else {
            Log.d(TAG, "The new image was not different enough from the previous to initiate capture.");
            return ContinuousCaptureOperation.ContinuousCaptureContinueWithPrevious;
        }

        return ContinuousCaptureOperation.ContinuousCaptureContinue;
    }

    /**
     * This is the cancellation handler for Continuous Capture mode.  Perform any cleanup of the canceled
     * continuous capture session here.
     * @param reason    The reason code for the canceled continuous capture session.
    */
    @Override
    public void onContinuousCaptureCanceled(int reason) {
        captureCanceled(reason);
    }

    /**
     * This is the click handler for the Enhance Image button.
     * @param view    The view for the control event.
     */
    @OnClick(R.id.enhancepicturebtn)
    public void onEnhanceImage(View view) {
        // Launch the image gallery picker to allow the user to choose which image to enhance.
        // Only Android supported formats will display. TIFF images are not supported by the
        // image gallery viewer as of 4.2.2. Therefore, any TIFF images that are saved to the folder
        // will not display in the gallery picker. However, if you save a TIFF image to the gallery 
        // storage folder, it will still save and you can verify that it is there by using the 
        // Android "My Files" application if available on your device, or the Android Debug 
        // Bridge (adb). You can get the path to the file by debugging this application's save function.
        if(!this.loggedIn){
            displayCustomToast("Enhance Image" , "Failed" , "Please Log in before attempting to Enhance Image");
            return;
        }
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if(MainActivity.this.profile_id != Constant.invalidId)
            intent.putExtra(getString(R.string.intent_profile_key), MainActivity.this.profile_id);
        startActivityForResult(intent, CHOOSE_IMAGE);
    }
    
    /**
     * Displays the Settings panel.
     * @param view      The view for the control event.
     */
    @OnClick(R.id.SettingsButton)
    public void onSettings(View view) {
        // Launch the preference settings activity.
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.LoginButton)
    public void onLogin(View view) {

        SessionServiceBuilder client = new SessionServiceBuilder();
        Call<LoginResponseObj> call = client.login();
        call.enqueue(new Callback<LoginResponseObj>() {
            @Override
            public void onResponse(Call<LoginResponseObj> call, Response<LoginResponseObj> response) {

                MainActivity.this.stopProcessDialog();

                switch(response.code()){

                    case 200:
                        Log.d("Success", "login call succeed");
                        Log.d("Status Code", String.valueOf(response.code()));
                        Log.d("Response", response.message());
                        LoginResponseObj result = response.body();
                        new CookieRepo().createAndPersistOneCookie(result.ticket,getRealmInstance()
                                ,MainActivity.this, MainActivity.this);
                        MainActivity.this.loggedIn = true;
                        MainActivity.this.enableButtons(MainActivity.this.loggedIn);
                        displayCustomToast("Login", "Success" , response.message());
                        break;
                    default:
                        Log.e("Error", "login call has failed");
                        displayCustomToast("Login", "Failed" , response.message());
                        break;
                }

            }

            @Override
            public void onFailure(Call<LoginResponseObj> call, Throwable t) {
                Log.e("Error", "login call has failed");
                MainActivity.this.stopProcessDialog();
                displayCustomToast("Login", "Failed" , t.toString());
            }
        });
        this.startProcessDialog();

    }

    @OnClick(R.id.CreateFilterProfile)
    public void onCreateFilterProfile(View view) {

        if(!this.loggedIn){
            displayCustomToast("Create Profile" , "Failed" , "Please Log in before attempting to Create Profile");
            return;
        }
        Intent intent = new Intent(this, CreateFilterProfileActivity.class);
        startActivity(intent);
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
    	super.onActivityResult(requestCode, resultCode, data);
    	
    	// Handle results for activities launched by this activity.
        try {            
            if(requestCode == CHOOSE_IMAGE && data != null && data.getData() != null) {         
                // The user picked an image from the gallery.                
                // Send the new picture taken to the enhancement screen so that users can modify it if necessary.
                Uri uri = data.getData();
                gotoEnhanceImage(uri);
            }
            // Note: The broadcast of the image change is already done in this.onPictureTaken for original picture and
            // in EnhanceImageActivity for images that get enhanced/modified.
        }
        catch (Exception e) {
            // Log a message and display the error to the user using our utility function.
            Log.e(TAG, e.getMessage(), e);
            CoreHelper.displayError(this, e);
        }
    }

    private void gotoEnhanceImage(Uri uri) {
        
        // If we have a file, then send it to enhancement.
    	if (uri != null) {    
    	    // Send the file path to enhancement so that it can load in the screen.
            String filepath = CoreHelper.getFilePathFromContentUri(this, uri);
            Intent intent = new Intent(this, EnhanceImageActivity.class);
            intent.putExtra("Filename", filepath);
            if(MainActivity.this.profile_id != Constant.invalidId)
                intent.putExtra(getString(R.string.intent_profile_key), MainActivity.this.profile_id);
            _newLoad = true;
            startActivity(intent);
        }
    }

    private boolean startProcessDialog(){

        if(this.dialog == null)
            this.dialog = new UIUtils().createProgressDialog(this);
        if(!this.dialog.isShowing())
            this.dialog.show();
        return true;

    }

    private boolean stopProcessDialog(){

        if(this.dialog.isShowing())
            this.dialog.dismiss();
        if(this.dialog != null)
            this.dialog = null;
        return true;
    }

    public void displayCustomToast(String action , String result, String description) {

        new UIUtils().createAlertDialog(this, action,result,description);

    }

    private void enableButtons(boolean loggedIn){

        this.continousPicBtn.setEnabled(loggedIn);
        this.enhancePicBtn.setEnabled(loggedIn);
        this.takePicBtn.setEnabled(loggedIn);
        this.buttonLogin.setEnabled(!loggedIn);

    }

    @Override
    public void onError(Throwable error) {

    }

    @Override
    public void onSuccess() {

    }

    private Realm getRealmInstance(){

        return this.realm;

    }

    public void updateSpinnerList(List<FilterProfile> items){

        ProfileDropDownAdapter adapter = new ProfileDropDownAdapter(this, items);
        this.spinner.setAdapter(adapter);
        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getSelectedItem();
                MainActivity.this.profile_id = getIdFromAdapterObject(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private int getIdFromAdapterObject(Object item){
        return this.presenter.getIdFromAdapterObject(item);
    }

    private int getProfileId(Bundle bundle){

        if(bundle!=null){
            String key = getString(R.string.intent_profile_key);
            return bundle.getInt(key);
        }
        return Constant.invalidId;

    }

}

