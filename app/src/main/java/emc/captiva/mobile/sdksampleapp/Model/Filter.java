package emc.captiva.mobile.sdksampleapp.Model;

import io.realm.RealmObject;

/**
 * Created by david on 9/2/16.
 */
public class Filter extends RealmObject{

    String filter;

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
