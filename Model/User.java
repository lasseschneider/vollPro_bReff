package Model;

import Controller.UserController;

/**
 * Created by Lasse on 19.05.2016.
 */
public class User {
    private int oid;
    private String login_name;
    private String login_email;
    private String passwort;
    private Person person;

    public User(int _OID, String _login_name, String _login_email, String _password)
    {
        this.oid            = _OID;
        this.login_name     = _login_name;
        this.login_email    = _login_email;
        this.passwort       = _password;
    }
    public User(int _oid)
    {
        this.oid = _oid;
        this.login_name     = null;
        this.login_email    = null;
        this.passwort       = null;
    }
    public static boolean isUserNameValid(String _Username)
    {
        UserController uc = new UserController();
        boolean result = false;
        if(uc.getNumberOfUser(_Username) == 0)
        {
            result = true;
        }
        if(_Username.length() == 0 )
        {
        result = false;
        }

        return result;
    }
    public int getOid()
    {
        return this.oid;
    }
    public String getLogin_name() {
        return login_name;
    }
    public String getLogin_email() {
        return login_email;
    }
    public String getPasswort() {
        return passwort;
    }

}
