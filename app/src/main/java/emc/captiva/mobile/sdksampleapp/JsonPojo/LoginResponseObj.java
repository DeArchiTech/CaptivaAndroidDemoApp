package emc.captiva.mobile.sdksampleapp.JsonPojo;

/**
 * Created by david on 8/30/16.
 */
public class LoginResponseObj {

    final LoginResponseReturnStatusObj returnStatus;

    final String ticket;

    public LoginResponseObj(LoginResponseReturnStatusObj returnStatus, String ticket) {
        this.returnStatus = returnStatus;
        this.ticket = ticket;
    }

}
