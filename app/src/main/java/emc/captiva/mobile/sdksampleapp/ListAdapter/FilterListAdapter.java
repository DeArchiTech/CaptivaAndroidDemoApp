package emc.captiva.mobile.sdksampleapp.ListAdapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import emc.captiva.mobile.sdksampleapp.ListItem.FilterListItem;
import emc.captiva.mobile.sdksampleapp.R;

/**
 * Created by david on 9/2/16.
 */
public class FilterListAdapter extends ArrayAdapter<FilterListItem> {

    private LayoutInflater inflater;

    public FilterListAdapter(Context context, List<FilterListItem> items) {
        super(context,0, items);
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
                item.selected = !item.selected;
                updateRowColor(row,item.selected);
            }
        });
        updateRowColor(row,item.selected);
    }

    private void updateRowColor(View row, boolean on){
        if(on){
            row.setBackgroundColor(Color.BLUE);
        }else{
            row.setBackgroundColor(Color.MAGENTA);
        }
    }

}