package Model;

/**
 * Created by Lasse on 17.06.2016.
 */

//ToDo: Implemetieren von Session Timern
public class Session {
    private String Session_ID;
    private User user;
    private boolean isLoggedIn;

    public Session(String session_ID, User user, boolean isLoggedIn) {
        Session_ID = session_ID;
        this.user = user;
        this.isLoggedIn = isLoggedIn;
    }

    public Session(String session_ID) {
        Session_ID = session_ID;
        this.user = null;
        this.isLoggedIn = false;
    }

    public String getSession_ID() {
        return Session_ID;
    }

    public void setSession_ID(String session_ID) {
        Session_ID = session_ID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}
