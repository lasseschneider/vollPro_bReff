package Controller;

import Model.User;

import java.sql.Date;
import java.sql.SQLException;

/**
 * Created by Lasse on 05.07.2016.
 */
public class PersonController extends DBController{
    public PersonController(){
        super();
        try {
            this.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static boolean isNameValid(String Name){
        boolean res;
        try{
            if(Name.equals("") || Name.equals("null"))
                res=false;
            else
                res=true;
        }catch(NullPointerException npe)
        {
            npe.printStackTrace();
            res=false;
        }
        return res;
    }
    private int getNewOID() {
        int res;
        String sql = "select max(OID) + 1 as newOid " +
                "from person";
        try{
            this.preparedStatement = this.connection.prepareCall(sql);
            this.preparedStatement.execute();
            this.result = this.preparedStatement.getResultSet();
            this.result.next();
            res = this.result.getInt(1);
        }catch(SQLException e){
            e.printStackTrace();
            res = -2147483648;
        }
        return res;
    }
    public boolean insertNewPerson(String name, String firstname, Date birthday, User currentUser)
    {
        boolean res = false;
        int newOid = this.getNewOID();
        String sql = "insert into person (OID, VORNAME, NAME, GEBURTSDATUM, ERSTELLT_VON_ID, ERSTELLT_AM, GUELTIG_VON) " +
                "values (?,?,?,?,?,curdate(),curdate() )";
        try{
            this.preparedStatement = this.connection.prepareCall(sql);
            this.preparedStatement.setInt(1,newOid);
            this.preparedStatement.setString(2,firstname);
            this.preparedStatement.setString(3,name);
            this.preparedStatement.setDate(4,birthday);
            this.preparedStatement.setInt(5,currentUser.getOid());
            this.preparedStatement.execute();
            res=true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return res;
    }
    public boolean insertNewPerson(String name, String firstname, Date birthday)
    {
        boolean res = false;
        int newOid = this.getNewOID();
        String sql = "insert into person (OID, VORNAME, NAME, GEBURTSDATUM, ERSTELLT_VON_ID, ERSTELLT_AM, GUELTIG_VON) " +
                "values (?,?,?,?,NULL ,curdate(),curdate() )";
        try{
            this.preparedStatement = this.connection.prepareCall(sql);
            this.preparedStatement.setInt(1,newOid);
            this.preparedStatement.setString(2,firstname);
            this.preparedStatement.setString(3,name);
            this.preparedStatement.setDate(4,birthday);
            this.preparedStatement.execute();
            res=true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return res;
    }
}
