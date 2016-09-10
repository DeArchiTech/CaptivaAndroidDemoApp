package emc.captiva.mobile.sdksampleapp.ActivityHelper;

import android.util.Log;
import android.widget.EditText;

import java.util.List;

import emc.captiva.mobile.sdksampleapp.ListItem.FilterListItem;

/**
 * Created by Davix on 9/8/16.
 */

public class CreateProfileHelper {

    public boolean atLeastOneFilterSelected(List<FilterListItem> listItems) {

        return listItems.isEmpty() == false;
    }

    public boolean filterNameIsSet(EditText editText, String defaultString){

        if(editText != null){
            String text = editText.getText().toString();
            return (!text.isEmpty()) && (!text.equalsIgnoreCase(defaultString));
        }
        return false;

    }

}
