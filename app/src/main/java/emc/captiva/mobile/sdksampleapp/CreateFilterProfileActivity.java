package emc.captiva.mobile.sdksampleapp;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import emc.captiva.mobile.sdksampleapp.ActivityHelper.CreateProfileHelper;
import emc.captiva.mobile.sdksampleapp.ListAdapter.AvailableFilterListAdapter;
import emc.captiva.mobile.sdksampleapp.ListAdapter.SelectedFilterListAdapter;
import emc.captiva.mobile.sdksampleapp.ListItem.FilterListItem;
import emc.captiva.mobile.sdksampleapp.Model.Filter;
import emc.captiva.mobile.sdksampleapp.Model.FilterProfile;
import emc.captiva.mobile.sdksampleapp.Repository.FilterProfileRepo;
import emc.captiva.mobile.sdksampleapp.Presenter.CreateProfilePresenter;
import emc.captiva.mobile.sdksampleapp.Util.RealmUtil;
import emc.captiva.mobile.sdksampleapp.Util.UIUtils;
import emc.captiva.mobile.sdksampleapp.View.CreateProfileView;
import emc.captiva.mobile.sdksampleapp.View.AvailableFilterListListener;
import emc.captiva.mobile.sdksampleapp.View.SelectedListClickedListener;
import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by david on 9/2/16.
 */
public class CreateFilterProfileActivity extends Activity implements CreateProfileView, AvailableFilterListListener, SelectedListClickedListener{

    private String action = "Create Profile";
    private CreateProfilePresenter presenter;
    private AvailableFilterListAdapter availableFilterListAdapter;
    private SelectedFilterListAdapter selectedFilterListAdapter;
    private boolean autoApplyFilter = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_filter_profile);
        ListView availableListView = (ListView)findViewById(R.id.filterListView);
        ListView selectedListView = (ListView)findViewById(R.id.selectedFilterListView);
        this.availableFilterListAdapter = createAvailableFilterList(getResources().getStringArray(R.array.Filter_List), availableListView);
        this.selectedFilterListAdapter = createSelectedFilterList( selectedListView);
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

    private AvailableFilterListAdapter createAvailableFilterList(String[] array, ListView listView){

        List<FilterListItem> listItems = new UIUtils().initializeFilterListView(array);
        AvailableFilterListAdapter adapter = new AvailableFilterListAdapter(this,  listItems, this);
        listView.setAdapter(adapter);
        return adapter;

    }

    private SelectedFilterListAdapter createSelectedFilterList(ListView listView){

        List<FilterListItem> listItems = new ArrayList<>();
        SelectedFilterListAdapter adapter = new SelectedFilterListAdapter(this, listItems, this);
        listView.setAdapter(adapter);
        return adapter;

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

    public void onSaveButtonClicked(View view){

        if(filterNameIsSet() == false){
            this.displayCustomToast("Create Profile" , "Failed" , "Filter Name No Set");
            return;
        }
        if(atLeastOneFilterSelected() == false){
            this.displayCustomToast("Create Profile" , "Failed" , "Filter Item Not Selected");
            return;
        }
        TextView textView = (TextView) findViewById(R.id.createProfileTitle);
        List<FilterListItem> items = this.selectedFilterListAdapter.getItems();
        FilterProfile profile = createFilterProfile(getProfileName(textView),
                getSelectedFilters(items), this.autoApplyFilter);

        this.callPresenterToCreateProfile(profile,getRealmInstance());
    }

    public void callPresenterToCreateProfile(FilterProfile profile, Realm realm){

        //Todo Refactor
        if (this.presenter != null) {
            this.presenter.onCreateProfile(profile,createReadSuccessCallBack(),createReadErrorCallBack(), realm);
        }

    }

    private void displayCustomToast(String action , String result, String description) {

        new UIUtils().createAlertDialog(this, action,result,description);

    }

    private Realm getRealmInstance(){

        return new RealmUtil().createRealm(this);

    }

    private boolean filterNameIsSet(){

        View view = findViewById(R.id.createProfileNameInput);
        String defaultString = getString(R.string.CreateProfilePage_ProfileNameInput);
        if(view !=null){
            EditText editText = (EditText) view;
            return new CreateProfileHelper().filterNameIsSet(editText , defaultString);
        }
        return false;
    }

    private boolean atLeastOneFilterSelected(){

        List<FilterListItem> items = this.selectedFilterListAdapter.getItems();
        return new CreateProfileHelper().atLeastOneFilterSelected(items);

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

    @Override
    public void selectedListOnClick(FilterListItem item) {

        this.selectedFilterListAdapter.removeItemFromListView(item);

    }

    @Override
    public void filterListOnClick(FilterListItem item) {

        this.selectedFilterListAdapter.addItemToListView(item);

    }

    //Getter and Setters

    public SelectedFilterListAdapter getSelectedFilterListAdapter() {
        return selectedFilterListAdapter;
    }

    public AvailableFilterListAdapter getAvailableFilterListAdapter() {
        return availableFilterListAdapter;
    }

    public void setAvailableFilterListAdapter(AvailableFilterListAdapter availableFilterListAdapter) {
        this.availableFilterListAdapter = availableFilterListAdapter;
    }

    public void setSelectedFilterListAdapter(SelectedFilterListAdapter selectedFilterListAdapter) {
        this.selectedFilterListAdapter = selectedFilterListAdapter;
    }
}
