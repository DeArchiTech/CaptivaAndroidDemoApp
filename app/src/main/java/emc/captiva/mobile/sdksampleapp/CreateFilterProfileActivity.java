package emc.captiva.mobile.sdksampleapp;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import emc.captiva.mobile.sdksampleapp.ListAdapter.FilterListAdapter;
import emc.captiva.mobile.sdksampleapp.ListItem.FilterListItem;
import emc.captiva.mobile.sdksampleapp.Model.Filter;
import emc.captiva.mobile.sdksampleapp.Model.FilterProfile;
import emc.captiva.mobile.sdksampleapp.View.CreateProfileView;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by david on 9/2/16.
 */
public class CreateFilterProfileActivity extends Activity implements CreateProfileView, Realm.Transaction.OnError,Realm.Transaction.OnSuccess{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_filter_profile);
        ListView listView = (ListView)findViewById(R.id.filterListView);
        initializeFilterList(getResources().getStringArray(R.array.Filter_List), listView);

    }

    private void initializeFilterList(String[] array, ListView listView){

        List<String> filters = Arrays.asList(array);
        ArrayList<FilterListItem> listItems = new ArrayList<FilterListItem>();
        for(String item: filters){
            listItems.add(new FilterListItem(item));
        }
        FilterListAdapter adapter = new FilterListAdapter(this,  listItems);
        listView.setAdapter(adapter);

    }
    
    @Override
    public String getProfileName(TextView textView) {
        return textView.getText().toString();
    }

    @Override
    public String getSelectedFilters(List<Filter> filterList) {
        return null;
    }

    @Override
    public void onError(Throwable error) {

    }

    @Override
    public void onSuccess() {

    }

    public void onSaveButtonClicked(){

    }

}
