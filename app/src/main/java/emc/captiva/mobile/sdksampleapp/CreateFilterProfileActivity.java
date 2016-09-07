package emc.captiva.mobile.sdksampleapp;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import emc.captiva.mobile.sdksampleapp.ListAdapter.FilterListAdapter;
import emc.captiva.mobile.sdksampleapp.ListItem.FilterListItem;
import emc.captiva.mobile.sdksampleapp.Model.Filter;
import emc.captiva.mobile.sdksampleapp.Model.FilterProfile;
import emc.captiva.mobile.sdksampleapp.Model.FilterProfileRepo;
import emc.captiva.mobile.sdksampleapp.Presenter.CreateProfilePresenter;
import emc.captiva.mobile.sdksampleapp.Util.RealmUtil;
import emc.captiva.mobile.sdksampleapp.Util.UIUtils;
import emc.captiva.mobile.sdksampleapp.View.CreateProfileView;
import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by david on 9/2/16.
 */
public class CreateFilterProfileActivity extends Activity implements CreateProfileView{

    private String action = "Create Profile";
    private CreateProfilePresenter presenter;
    private List<FilterListItem> listItems;
    private boolean autoApplyFilter = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_filter_profile);
        ListView listView = (ListView)findViewById(R.id.filterListView);
        this.listItems = initializeFilterList(getResources().getStringArray(R.array.Filter_List), listView);
        this.presenter = new CreateProfilePresenter(this, new FilterProfileRepo(),this);

    }

    private Realm.Transaction.OnSuccess createReadSuccessCallBack() {
        return new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                CreateFilterProfileActivity.this
                        .displayCustomToast(CreateFilterProfileActivity.this.action,
                                "Succeed", "Profile Saved");
            }
        };
    }

    private Realm.Transaction.OnError createReadErrorCallBack() {
        return new Realm.Transaction.OnError(){
            @Override
            public void onError(Throwable error) {
                CreateFilterProfileActivity.this
                        .displayCustomToast(CreateFilterProfileActivity.this.action,
                                "Failed", error.getMessage());
            }
        };
    }

    private ArrayList<FilterListItem> initializeFilterList(String[] array, ListView listView){

        List<String> filters = Arrays.asList(array);
        ArrayList<FilterListItem> listItems = new ArrayList<FilterListItem>();
        for(String item: filters){
            listItems.add(new FilterListItem(new Filter(item)));
        }
        FilterListAdapter adapter = new FilterListAdapter(this,  listItems);
        listView.setAdapter(adapter);
        return listItems;

    }

    @Override
    public String getProfileName(TextView textView) {
        return textView.getText().toString();
    }

    @Override
    public List<FilterListItem> getSelectedFilters(List<FilterListItem> filterList) {
        List<FilterListItem> result = new ArrayList<FilterListItem>();
        for(FilterListItem item : filterList){
            if(item.selected)
                result.add(item);
        }
        return result;
    }

    @Override
    public FilterProfile createFilterProfile(String profileName, List<FilterListItem> items, boolean applyFilterAuto) {
        FilterProfile profile = new FilterProfile();
        profile.setProfileName(profileName);
        profile.setAutoMaticallyApplyFilter(applyFilterAuto);
        RealmList<Filter> filters = new RealmList<Filter>();
        for(FilterListItem item: items){
            filters.add(item.filter);
        }
        profile.setFilters(filters);
        return profile;
    }

    public void onSaveButtonClicked(){

        boolean autoApply = this.autoApplyFilter;
        TextView textView = (TextView) findViewById(R.id.createProfileTitle);
        FilterProfile profile = createFilterProfile(getProfileName(textView),
                getSelectedFilters(this.listItems),
                autoApply);

        this.presenter.onCreateProfile(profile,createReadSuccessCallBack(),createReadErrorCallBack(),getRealmInstance());

    }

    private void displayCustomToast(String action , String result, String description) {

        new UIUtils().createAlertDialog(this, action,result,description);

    }

    private Realm getRealmInstance(){

        return new RealmUtil().createRealm(this);

    }

    public void setPresenter(CreateProfilePresenter presenter) {
        this.presenter = presenter;
    }

    public boolean isAutoApplyFilter() {
        return autoApplyFilter;
    }

    public void onToggleClicked(View view) {

        this.autoApplyFilter = !this.autoApplyFilter;

    }
}
