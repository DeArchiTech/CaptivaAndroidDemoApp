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
import emc.captiva.mobile.sdksampleapp.View.SelectedListClickedListener;

/**
 * Created by david on 9/9/16.
 */
public class SelectedFilterListAdapter extends ArrayAdapter<FilterListItem> {

    private LayoutInflater inflater;
    private SelectedListClickedListener listener;
    private List<FilterListItem> listItems;

    public SelectedFilterListAdapter(Context context, List<FilterListItem> items, SelectedListClickedListener listener) {
        super(context,0, items);
        this.listener = listener;
        this.listItems = items;
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
                SelectedFilterListAdapter.this.listener.selectedListOnClick(item);
            }
        });

    }

    public List<FilterListItem> getItems(){
        return this.listItems;
    }

    public void addItemToListView(FilterListItem item){
        this.listItems.add(item);
        this.add(item);
        this.notifyDataSetChanged();

    }

    public void removeItemFromListView(FilterListItem item){
        this.remove(item);
        this.notifyDataSetChanged();
    }

}