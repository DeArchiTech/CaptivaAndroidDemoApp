package emc.captiva.mobile.sdksampleapp.Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;

/**
 * Created by david on 8/31/16.
 */
public class UIUtils {

    public ProgressDialog createProgressDialog(Activity activity){

        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setTitle("Loading");
        dialog.setMessage("Connecting to server");
        return dialog;

    }

    public void createAlertDialog(Activity activity, String action, String result, String description) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(action + " " + result)
                .setPositiveButton(description, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
