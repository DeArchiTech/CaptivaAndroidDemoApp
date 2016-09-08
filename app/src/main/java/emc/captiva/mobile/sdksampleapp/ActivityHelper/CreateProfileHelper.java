package emc.captiva.mobile.sdksampleapp.ActivityHelper;

import android.widget.EditText;

import java.util.List;

import emc.captiva.mobile.sdksampleapp.ListItem.FilterListItem;

/**
 * Created by Davix on 9/8/16.
 */

public class CreateProfileHelper {

    public boolean atLeastOneFilterSelected(List<FilterListItem> listItems) {

        for(FilterListItem item: listItems){

            if(item.selected == true)
                return true;
        }
        return false;
    }

    public boolean filterNameIsSet(EditText editText){

        if(editText != null){
            String textInEditableText = editText.getText().toString();
            return textInEditableText.isEmpty() == false;
        }
        return false;

    }

}
