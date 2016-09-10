package emc.captiva.mobile.sdksampleapp.ListAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import emc.captiva.mobile.sdksampleapp.ListItem.FilterListItem;
import emc.captiva.mobile.sdksampleapp.R;
import emc.captiva.mobile.sdksampleapp.View.AvailableFilterListListener;

/**
 * Created by david on 9/2/16.
 */
public class AvailableFilterListAdapter extends ArrayAdapter<FilterListItem> {

    private LayoutInflater inflater;
    private AvailableFilterListListener listener;

    public AvailableFilterListAdapter(Context context, List<FilterListItem> items, AvailableFilterListListener listener) {
        super(context,0, items);
        this.listener = listener;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            v = inflater.inflate(R.layout.list_item_filter, parent, false);
        }
        final FilterListItem item = getItem(position);
        modifyRow(v,item);
        return v;
    }

    private void modifyRow(final View row, final FilterListItem item){

        TextView filterField = (TextView) row.findViewById(R.id.filterField);
        filterField.setText(item.filter.getFilterName());
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AvailableFilterListAdapter.this.listener.filterListOnClick(item);
            }
        });

    }

}