package emc.captiva.mobile.sdksampleapp.Model;

import io.realm.RealmObject;

/**
 * Created by david on 9/1/16.
 */
public class Filter extends RealmObject {

    private String name;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
