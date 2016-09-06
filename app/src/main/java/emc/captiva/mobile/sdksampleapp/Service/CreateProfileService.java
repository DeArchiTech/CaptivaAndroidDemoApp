package emc.captiva.mobile.sdksampleapp.Service;

import emc.captiva.mobile.sdksampleapp.Model.FilterProfile;
import io.realm.Realm;

/**
 * Created by giovanni on 05/09/16.
 */
public interface CreateProfileService {
    void createAndPersistProfile(final FilterProfile profile, final Realm realm,
                                 Realm.Transaction.OnSuccess onSuccess,
                                 Realm.Transaction.OnError onError);

    void readProfiles(Realm bgrealm,Realm.Transaction.OnSuccess onSuccess,
                      Realm.Transaction.OnError onError);
}
