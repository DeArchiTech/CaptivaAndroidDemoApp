package emc.captiva.mobile.sdksampleapp.Model;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by david on 9/1/16.
 */
public class FilterProfile extends RealmObject {

    private int id;
    private RealmList<Filter> filters;
    private boolean autoMaticallyApplyFilter;
    private String profileName;

    public FilterProfile() {
    }

    public FilterProfile(String profileName) {
        this.profileName = profileName;
    }

    public boolean isAutoMaticallyApplyFilter() {
        return autoMaticallyApplyFilter;
    }

    public void setAutoMaticallyApplyFilter(boolean autoMaticallyApplyFilter) {
        this.autoMaticallyApplyFilter = autoMaticallyApplyFilter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList<Filter> getFilters() {
        return filters;
    }

    public void setFilters(RealmList<Filter> filters) {
        this.filters = filters;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }
}