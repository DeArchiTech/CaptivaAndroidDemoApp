package emc.captiva.mobile.sdksampleapp.View;

import android.widget.TextView;

import java.util.List;

import emc.captiva.mobile.sdksampleapp.Model.Filter;

/**
 * Created by giovanni on 05/09/16.
 */
public interface CreateProfileView{

    String getProfileName(TextView textView);
    String getSelectedFilters(List<Filter> filterList);

}
