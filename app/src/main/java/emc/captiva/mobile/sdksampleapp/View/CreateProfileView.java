package emc.captiva.mobile.sdksampleapp.View;

import android.widget.TextView;

import java.util.List;

import emc.captiva.mobile.sdksampleapp.ListItem.FilterListItem;
import emc.captiva.mobile.sdksampleapp.Model.FilterProfile;

/**
 * Created by giovanni on 05/09/16.
 */
public interface CreateProfileView{

    String getProfileName(TextView textView);
    List<FilterListItem> getSelectedFilters(List<FilterListItem> filterList);
    FilterProfile createFilterProfile(String profileName, List<FilterListItem> items, boolean applyFilterAuto);
}
