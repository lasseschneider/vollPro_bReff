package Model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Lasse on 17.06.2016.
 */

public class Session {
    private String Session_ID;
    private User user;
    private boolean isLoggedIn;
    private Timestamp loggedInFrom;
    private Timestamp loggedInUntil;

    public Timestamp getLoggedInFrom() {
        return loggedInFrom;
    }

    public void setLoggedInFrom(Timestamp loggedInFrom) {
        this.loggedInFrom = loggedInFrom;
    }
    public void setLoggedInFrom() {
        this.loggedInFrom = new Timestamp(System.currentTimeMillis());
    }
    public void setLoggedInTimes(boolean loggedIn)
    {
        if(loggedIn)
        {
            this.loggedInFrom = new Timestamp(System.currentTimeMillis());
            this.loggedInUntil = new Timestamp(System.currentTimeMillis() + 600000);
        }
        else
        {
            this.loggedInFrom   =   null;
            this.loggedInUntil  =   null;
        }
    }

    public Timestamp getLoggedInUntil() {
        return loggedInUntil;
    }

    public void setLoggedInUntil(Timestamp loggedInUntil) {
        this.loggedInUntil = loggedInUntil;
    }



    /**
     * @param session_ID
     * @param user
     * @param isLoggedIn
     */
    public Session(String session_ID, User user, boolean isLoggedIn) {
        Session_ID = session_ID;
        this.user = user;
        this.isLoggedIn = isLoggedIn;
        this.setLoggedInTimes(isLoggedIn);

    }

    /**
     */
    public Session(String session_ID) {
        Session_ID = session_ID;
        this.user = null;
        this.isLoggedIn = false;
        this.setLoggedInTimes(isLoggedIn);
    }

    /**
     * @return
     */
    public String getSession_ID() {
        return Session_ID;
    }

    /**
     * @param session_ID
     */
    public void setSession_ID(String session_ID) {
        Session_ID = session_ID;
    }

    /**
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    /**
     * @param loggedIn
     */
    public void setLoggedIn(boolean loggedIn) {
        this.setLoggedInTimes(loggedIn);
        isLoggedIn = loggedIn;
    }
}
