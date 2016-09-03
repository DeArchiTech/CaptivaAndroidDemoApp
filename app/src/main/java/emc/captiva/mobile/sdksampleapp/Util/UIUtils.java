package emc.captiva.mobile.sdksampleapp.Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import emc.captiva.mobile.sdksampleapp.CreateFilterProfileActivity;
import emc.captiva.mobile.sdksampleapp.ListAdapter.FilterListAdapter;
import emc.captiva.mobile.sdksampleapp.ListItem.FilterListItem;

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

    public void initializeListView(String[] array, ListView listView, Context context){

        List<String> filters = Arrays.asList(array);
        ArrayList<FilterListItem> listItems = new ArrayList<FilterListItem>();
        for(String item: filters){
            listItems.add(new FilterListItem(item));
        }
        FilterListAdapter adapter = new FilterListAdapter(context, listItems);
        listView.setAdapter(adapter);

    }

}
