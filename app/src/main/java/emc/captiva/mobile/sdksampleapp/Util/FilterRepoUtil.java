package emc.captiva.mobile.sdksampleapp.Util;

import emc.captiva.mobile.sdksampleapp.Model.Filter;
import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by Davix on 9/8/16.
 */
public class FilterRepoUtil {
    public RealmList<Filter> createReproFilters(RealmList<Filter> originalFilters, Realm realm){

        RealmList<Filter> result = new RealmList<>();
        if(originalFilters!=null){
            for(Filter item: originalFilters){

                Filter newItem = realm.createObject(Filter.class);
                newItem.setFilterName(item.filterName);
                result.add(newItem);
            }
        }
        return result;
    }
}
