package emc.captiva.mobile.sdksampleapp.ListAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
    private List<FilterListItem> items;
    public SelectedFilterListAdapter(Context context, List<FilterListItem> items, SelectedListClickedListener listener) {
        super(context,0, items);
        this.listener = listener;
        this.items = items;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            v = inflater.inflate(R.layout.list_item_filter, parent, false);
        }
        final FilterListItem item = getItem(position);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.selectedListOnClick(item);
            }
        });
        return v;
    }

    private int getColor(int id){
        return getContext().getResources().getColor(id);
    }

    public List<FilterListItem> getItems() {
        return items;
    }

    public void addItemToListView(FilterListItem item){
        this.items.add(item);
        this.add(item);
        this.notifyDataSetChanged();

    }

    public void removeItemFromListView(FilterListItem item){

        this.items.remove(item);
        this.remove(item);
        this.notifyDataSetChanged();
    }

}
