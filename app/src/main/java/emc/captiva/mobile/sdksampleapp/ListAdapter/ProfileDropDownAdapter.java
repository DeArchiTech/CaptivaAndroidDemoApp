package emc.captiva.mobile.sdksampleapp.ListAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import emc.captiva.mobile.sdksampleapp.ListItem.FilterListItem;
import emc.captiva.mobile.sdksampleapp.Model.FilterProfile;
import emc.captiva.mobile.sdksampleapp.R;
import emc.captiva.mobile.sdksampleapp.View.AvailableFilterListListener;

/**
 * Created by Davix on 9/14/16.
 */

public class ProfileDropDownAdapter extends ArrayAdapter<FilterProfile> {

    private LayoutInflater inflater;

    public ProfileDropDownAdapter(Context context, List<FilterProfile> items) {
        super(context,0, items);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            v = inflater.inflate(R.layout.list_item_filter, parent, false);
        }
        final FilterProfile item = getItem(position);
        TextView filterField = (TextView) v.findViewById(R.id.filterField);
        filterField.setText(item.getProfileName());
        return v;
    }

}
