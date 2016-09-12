package emc.captiva.mobile.sdksampleapp.Util;

/**
 * Created by Davix on 9/12/16.
 */

public class Util {

    public boolean enoughtTimeHasPassed(long end, long start, long duration){

        return (end - start) > duration;

    }

}
