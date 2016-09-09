package emc.captiva.mobile.sdksampleapp.ListAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.List;
import emc.captiva.mobile.sdksampleapp.ListItem.FilterListItem;
import emc.captiva.mobile.sdksampleapp.R;

/**
 * Created by david on 9/9/16.
 */
public class SelectedFilterListAdapter extends ArrayAdapter<FilterListItem> {

    private LayoutInflater inflater;

    public SelectedFilterListAdapter(Context context, List<FilterListItem> items) {
        super(context,0, items);
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
                removeItemAtPosition(item);
            }
        });
        return v;
    }

    private void removeItemAtPosition(FilterListItem item){
        this.remove(item);
        notifyDataSetChanged();
    }

    private int getColor(int id){
        return getContext().getResources().getColor(id);
    }

}
