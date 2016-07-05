package Controller;

import Model.Session;
import Model.User;

import java.sql.SQLException;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Lasse on 17.06.2016.
 */
public class SessionController extends DBController{
    public SessionController(){
        super();
        try{
            this.connect();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @param Session_ID
     * @param _user
     */
    public void setNewSession(String Session_ID, User _user)
    {
        String sql = "insert into session(SESSION_ID, USER_ID, IS_LOGGED_IN)" +
                "values(?,?,?)";
        try{
            this.preparedStatement = this.connection.prepareCall(sql);
            this.preparedStatement.setString(1,Session_ID);
            this.preparedStatement.setInt(2,_user.getOid());
            this.preparedStatement.setInt(3,1);
            this.preparedStatement.execute();
        }catch(SQLException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * @param Session_ID
     */
    public void setNewSession(String Session_ID)
    {
        String sql = "insert into session(SESSION_ID, IS_LOGGED_IN)" +
                "values(?,?)";
        try{
            this.preparedStatement = this.connection.prepareCall(sql);
            this.preparedStatement.setString(1,Session_ID);
            this.preparedStatement.setInt(2,0);
            this.preparedStatement.execute();
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @param Session_ID
     * @return
     */
    public boolean isLoggedIn(String Session_ID){
        boolean result = false;
        Timestamp now = new Timestamp( System.currentTimeMillis() );
        String sql = "select IS_LOGGED_IN from session where SESSION_ID = ? " +
                "and ? BETWEEN LOGGED_IN_FROM and LOGGED_IN_TO";
        try{
            this.preparedStatement = this.connection.prepareCall(sql);
            this.preparedStatement.setString(1,Session_ID);
            this.preparedStatement.setTimestamp(2, now);
            this.preparedStatement.execute();
            this.result = this.preparedStatement.getResultSet();
            this.result.next();
            if(this.result.getBoolean(1))
                result = true;
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param Session_ID
     * @return
     */
    public boolean doesSessionExists(String Session_ID)
    {
        boolean result = false;
        String sql = "select count(*) as anzahl from session where SESSION_ID = ? ";
        Timestamp now = new Timestamp(System.currentTimeMillis());
        try{
            this.preparedStatement = this.connection.prepareCall(sql);
            this.preparedStatement.setString(1,Session_ID);
            //this.preparedStatement.setTimestamp(2,now);
            this.preparedStatement.execute();
            this.result = this.preparedStatement.getResultSet();
            this.result.next();
            if(this.result.getInt(1) == 1)
                result = true;
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }
    public boolean isSessionValid(String Session_ID)
    {
        boolean result = false;
        String sql = "select count(*) as anzahl from session where SESSION_ID = ? " +
                "and LOGGED_IN_TO >= ?";
        Timestamp now = new Timestamp(System.currentTimeMillis());
        try{
            this.preparedStatement = this.connection.prepareCall(sql);
            this.preparedStatement.setString(1,Session_ID);
            this.preparedStatement.setTimestamp(2,now);
            this.preparedStatement.execute();
            this.result = this.preparedStatement.getResultSet();
            this.result.next();
            if(this.result.getInt(1) == 1)
                result = true;
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param Session_ID
     * @return
     */
    public Session getSessionBySessionID(String Session_ID) {
        Session resultSession = new Session(Session_ID);
        String sql = "Select IS_LOGGED_IN, USER_ID, LOGGED_IN_FROM, LOGGED_IN_TO from session " +
                "where SESSION_ID = ?";
        try{
            this.preparedStatement = this.connection.prepareCall(sql);
            this.preparedStatement.setString(1,Session_ID);
            this.preparedStatement.execute();
            this.result = this.preparedStatement.getResultSet();
            this.result.next();
            resultSession.setLoggedIn(this.result.getBoolean(1));
            resultSession.setLoggedInFrom(this.result.getTimestamp(3));
            resultSession.setLoggedInUntil(this.result.getTimestamp(4));
            User sessionUser;
            UserController uc = new UserController();
            try{
                sessionUser = uc.getUserByID(this.result.getInt(2));
            }catch(SQLException e)            {
                e.printStackTrace();
                sessionUser = null;
            }
            resultSession.setUser(sessionUser);
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return resultSession;
    }

    /**
     * @param session
     */
    public void updateSession(Session session){
        String sql = "update session " +
                "set IS_LOGGED_IN = ? " +
                ", USER_ID = ? " +
                ", LOGGED_IN_FROM = ? " +
                ", LOGGED_IN_TO = ? " +
                "WHERE " +
                "SESSION_ID = ?";
        try{
            this.preparedStatement = this.connection.prepareCall(sql);
            this.preparedStatement.setBoolean(1,session.isLoggedIn());
            this.preparedStatement.setInt(2,session.getUser().getOid());
            this.preparedStatement.setTimestamp(3, session.getLoggedInFrom());
            this.preparedStatement.setTimestamp(4, session.getLoggedInUntil());
            this.preparedStatement.setString(5,session.getSession_ID());
            this.preparedStatement.execute();
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }


    public void testUtil(String sessionID, java.util.Date date) {
        String sql = "UPDATE session " +
                "SET LOGGED_IN_TO = ? " +
                "WHERE SESSION_ID = ?";
        try {
            this.preparedStatement = this.connection.prepareCall(sql);
            this.preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean logoutFromSession(String sessionID) {
        boolean res = false;
        String sql = "update session " +
                "set logged_in_to = ? " +
                ", IS_LOGGED_IN = ?" +
                "where SESSION_ID = ?";
        Timestamp now = new Timestamp(System.currentTimeMillis());
        try{
            this.preparedStatement = this.connection.prepareCall(sql);
            this.preparedStatement.setTimestamp(1,now);
            this.preparedStatement.setBoolean(2,false);
            this.preparedStatement.setString(3,sessionID);
            this.preparedStatement.execute();
            res = true;
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return res;
    }
}
