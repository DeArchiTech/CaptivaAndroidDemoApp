package emc.captiva.mobile.sdksampleapp.ListItem;

import emc.captiva.mobile.sdksampleapp.Model.Filter;

/**
 * Created by david on 9/2/16.
 */
public class FilterListItem {

    public Filter filter;
    public boolean selected;

    public FilterListItem(Filter filter) {
        this.filter = filter;
        this.selected = false;
    }


}