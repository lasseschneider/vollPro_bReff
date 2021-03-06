package Controller;
import java.sql.*;

import Model.*;
import com.sun.org.apache.xalan.internal.lib.ExsltDatetime;
import jdk.nashorn.internal.ir.annotations.Ignore;
import sun.util.calendar.LocalGregorianCalendar;

import java.sql.Date;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import java.time.*;

import static java.sql.Types.NULL;

/**
 * Created by Lasse on 19.05.2016.
 */
public class UserController extends DBController {
    /**
     *
     */
    public UserController(){
        super();
        try {
            this.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param _OID
     * @return
     * @throws SQLException
     */
    public User getUserByID(int _OID) throws SQLException {
        User resultUser = null;
        try {
            PreparedStatement ps;
            String sql = "select * from user where OID = ?";
            ps = this.connection.prepareCall(sql);
            ps.setInt(1,_OID);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            rs.absolute(1);
            resultUser = new User((int) rs.getDouble("OID"),rs.getString("LOGIN_NAME"),rs.getString("LOGIN_EMAIL"),rs.getString("PASSWORT"));
            }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return resultUser;
    }

    /**
     * @param _loginemail
     * @return
     */
    public User getUserByLoginEmail(String _loginemail){
        User resultUser = null;
        try {
            PreparedStatement ps;
            String sql = "select * from user where LOGIN_EMAIL = ?";
            ps = this.connection.prepareCall(sql);
            ps.setString(1,_loginemail);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            rs.absolute(1);
            resultUser = new User((int) rs.getDouble("OID"),rs.getString("LOGIN_NAME"),rs.getString("LOGIN_EMAIL"),rs.getString("PASSWORT"));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return resultUser;
    }

    /**
     * @param _LoginName
     * @return
     */
    public String getPasswordByLoginname(String _LoginName)
    {
        try {
            this.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String Password = "";
        try {
            PreparedStatement ps;
            String sql = "select * from user where LOGIN_NAME = ?";
            ps = this.connection.prepareCall(sql);
            ps.setString(1,_LoginName);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            rs.absolute(1);
            Password = rs.getString("PASSWORT");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return Password;
    }

    /**
     * @param _LoginName
     * @return
     */
    public User getUserByLoginName(String _LoginName){
        User resultUser = null;
        try {
            PreparedStatement ps;
            String sql = "select * from user where LOGIN_NAME = ?";
            ps = this.connection.prepareCall(sql);
            ps.setString(1,_LoginName);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            rs.absolute(1);
            resultUser = new User((int) rs.getDouble("OID"),rs.getString("LOGIN_NAME"),rs.getString("LOGIN_EMAIL"),rs.getString("PASSWORT"));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return resultUser;
    }

    /**
     * @param _LoginName
     * @return
     */
    public boolean isLoginNameValid(String _LoginName)
    {
        User resultUser = this.getUserByLoginName(_LoginName);
        if(resultUser != null)
            return true;
        else
            return false;
    }

    /**
     * @param _LoginEmail
     * @return
     */
    public boolean isLoginEmailValid(String _LoginEmail)
    {
        User resultUser = this.getUserByLoginEmail(_LoginEmail);
        if(resultUser != null)
            return true;
        else
            return false;
    }

    /**
     * @param _LoginName
     * @param _Password
     * @return
     */
    public User isUserValidByLoginName(String _LoginName, String _Password)
    {
        User resultUser = this.getUserByLoginName(_LoginName);
        String md5Password = MD5.getMD5(_Password);

        if(resultUser != null)
        {
            boolean passwordEquals = resultUser.getPasswort().equals(md5Password);
            if (!passwordEquals)
                resultUser = null;
        }
        return resultUser;
    }

    /**
     * @param _LoginEmail
     * @param _Password
     * @return
     */
    public User isUserValidByLoginEmail(String _LoginEmail, String _Password)
    {
        User resultUser = this.getUserByLoginEmail(_LoginEmail);
        if(resultUser == null)
        {
            return resultUser;
        }
       else {
        if(resultUser.getPasswort().equals(_Password)) {
            return resultUser;
        }
            else
        {
            return null;
        }
        }
    }

    /**
     * @param _LoginNameOrLoginEmail
     * @param _Password
     * @return
     */
    public User doesUserExists(String _LoginNameOrLoginEmail, String _Password){
        User resultUser = isUserValidByLoginEmail(_LoginNameOrLoginEmail, _Password);
        if(resultUser != null)
            return resultUser;
        else
        {
            resultUser = isUserValidByLoginName(_LoginNameOrLoginEmail, _Password);
            return  resultUser;
        }
    }

    /**
     * @param _unHashedPW
     * @param _HashedPW
     * @return
     */
    public boolean isPasswordRight(String _unHashedPW, String _HashedPW)
    {
        MD5 md5 = new MD5();
        String hashedPW = md5.getMD5(_unHashedPW);
        if(hashedPW.equals(_HashedPW))
            return true;
        else
            return false;
    }

    /**
     * @return
     */
    public List<User> getAllUser()
    {
        List<User> resultList = new ArrayList<User>();
        try {
            PreparedStatement ps;
            String sql = "select * from user";
            ps = this.connection.prepareCall(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while(rs.next())
            {
                resultList.add(new User((int) rs.getDouble("OID"),rs.getString("LOGIN_NAME"),rs.getString("LOGIN_EMAIL"),rs.getString("PASSWORT")));
            }
                  }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    /**
     * @return
     */
    public int getNumberOfUser()
    {
        int result = 0;
        try {
            String sql = "select count(*) as numberOfUser from user";
            this.preparedStatement = this.connection.prepareCall(sql);
            this.preparedStatement.execute();
            this.result = preparedStatement.getResultSet();
            this.result.absolute(1);
            result = this.result.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param _username
     * @return
     */
    public int getNumberOfUser(String _username)
    {
        int result = 0;
        try {
            String sql = "select count(*) as numberOfUser from user where LOGIN_NAME = ?";
            this.preparedStatement = this.connection.prepareCall(sql);
            this.preparedStatement.setString(1,_username);
            this.preparedStatement.execute();
            this.result = preparedStatement.getResultSet();
            this.result.absolute(1);
            result = this.result.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param _LoginName
     * @param _LoginEmail
     * @param _Password
     * @param currentUser
     * @return
     */

    public boolean insertNewUser(String _LoginName, String _LoginEmail, String _Password, User currentUser)
    {
        boolean result = false;
        double currentUserOid = currentUser.getOid();
        String MD5Password;
        double newOid;
        MD5 md5Class = new MD5();
        MD5Password = md5Class.getMD5(_Password);
        int numberOfUser = this.getNumberOfUser();
        if(numberOfUser == 0) {
            newOid = -2147483648;
        }
        else{
            if(this.isLoginEmailValid(_LoginEmail) || this.isLoginNameValid(_LoginName))
            {
                return false;
            }
            else {
                newOid = -2147483648 + numberOfUser;
            }
        }
        Date curdate  = new Date( System.currentTimeMillis() );

        String sql = "insert into user (OID, ERSTELLT_VON_ID, LOGIN_NAME, LOGIN_EMAIL, PASSWORT, GUELTIG_VON, ERSTELLT_AM) " +
                "values(?,?,?,?,?,?,?)" +
                "ON DUPLICATE KEY UPDATE LOGIN_NAME = ?";
        try{
            this.preparedStatement = this.connection.prepareCall(sql);
            this.preparedStatement.setInt(1,(int)newOid);
            this.preparedStatement.setInt(2,(int)currentUserOid);
            this.preparedStatement.setString(3,_LoginName);
            this.preparedStatement.setString(4,_LoginEmail);
            this.preparedStatement.setString(5,MD5Password);
            this.preparedStatement.setDate(6,curdate);
            this.preparedStatement.setDate(7,curdate);
            this.preparedStatement.setString(8,_LoginName);
            this.preparedStatement.execute();
            result = true;
        }
        catch (SQLException e) {
        e.printStackTrace();
    }
        return result;
    }
    public boolean insertNewUser(String _LoginName, String _LoginEmail, String _Password)
    {
        boolean result = false;
        String MD5Password;
        double newOid;
        MD5 md5Class = new MD5();
        MD5Password = md5Class.getMD5(_Password);
        int numberOfUser = this.getNumberOfUser();
        if(numberOfUser == 0) {
            newOid = -2147483648;
        }
        else{
            if(this.isLoginEmailValid(_LoginEmail) || this.isLoginNameValid(_LoginName))
            {
                return false;
            }
            else {
                newOid = -2147483648 + numberOfUser;
            }
        }
        Date curdate  = new Date( System.currentTimeMillis() );

        String sql = "insert into user (OID, ERSTELLT_VON_ID, LOGIN_NAME, LOGIN_EMAIL, PASSWORT, GUELTIG_VON, ERSTELLT_AM) " +
                "values(?,? ,?,?,?,?,?)" +
                "ON DUPLICATE KEY UPDATE LOGIN_NAME = ?";
        try{
            this.preparedStatement = this.connection.prepareCall(sql);
            this.preparedStatement.setInt(1,(int)newOid);
            this.preparedStatement.setInt(2,NULL);
            this.preparedStatement.setString(3,_LoginName);
            this.preparedStatement.setString(4,_LoginEmail);
            this.preparedStatement.setString(5,MD5Password);
            this.preparedStatement.setDate(6,curdate);
            this.preparedStatement.setDate(7,curdate);
            this.preparedStatement.setString(8,_LoginName);
            this.preparedStatement.execute();
            result = true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /*public boolean insertNewUser(String _LoginName, String _Password)
    {
        boolean result = false;
        String MD5Password;
        int newOid = this.getMaxOID() + 1;
        MD5Password = MD5.getMD5(_Password);

        String sql = "insert into user (OID, LOGIN_NAME, PASSWORT, GUELTIG_VON, ERSTELLT_AM) " +
                "values(?,?,?,curdate(),curdate())";
        try{
            this.preparedStatement = this.connection.prepareCall(sql);
            this.preparedStatement.setInt(1,newOid);
            this.preparedStatement.setString(2,_LoginName);
            this.preparedStatement.setString(3,MD5Password);
            this.preparedStatement.execute();
            result = true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }*/

    public User insertNewUser(String _LoginName, String _Password)
    {
        User result = null;
        String MD5Password;
        int newOid = this.getMaxOID() + 1;
        MD5Password = MD5.getMD5(_Password);

        String sql = "insert into user (OID, LOGIN_NAME, PASSWORT, GUELTIG_VON, ERSTELLT_AM) " +
                "values(?,?,?,curdate(),curdate())";
        try{
            this.preparedStatement = this.connection.prepareCall(sql);
            this.preparedStatement.setInt(1,newOid);
            this.preparedStatement.setString(2,_LoginName);
            this.preparedStatement.setString(3,MD5Password);
            this.preparedStatement.execute();
            result = this.getUserByID(newOid);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getMaxOID() {
        int maxOID = -2147483648;
        String sql = "select max(OID) as anzahl from user";
        try{
            this.preparedStatement = this.connection.prepareCall(sql);
            this.preparedStatement.execute();
            this.result = this.preparedStatement.getResultSet();
            this.result.next();
            maxOID = this.result.getInt(1);
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return maxOID;
    }
}
