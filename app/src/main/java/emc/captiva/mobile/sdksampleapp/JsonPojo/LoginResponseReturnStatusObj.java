package emc.captiva.mobile.sdksampleapp.JsonPojo;

/**
 * Created by david on 8/30/16.
 */
public class LoginResponseReturnStatusObj {

    final String status;
    final String code;
    final String message;
    final String server;

    public LoginResponseReturnStatusObj(String status, String code, String message, String server) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.server = server;
    }
}
