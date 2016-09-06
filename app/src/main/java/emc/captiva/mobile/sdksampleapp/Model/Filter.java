package emc.captiva.mobile.sdksampleapp.Model;

import io.realm.RealmObject;

/**
 * Created by david on 9/6/16.
 */
public class Filter extends RealmObject {

    public String filterName;

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public Filter(String filterName) {
        this.filterName = filterName;
    }

    public Filter() {
    }
}
