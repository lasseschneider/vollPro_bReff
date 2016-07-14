package Controller;

import Model.Person;
import Model.User;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public int getNewOID() {
        int res;
        String sql = "select max(OID) " +
                "from person";
        try{
            this.preparedStatement = this.connection.prepareCall(sql);
            this.preparedStatement.execute();
            this.result = this.preparedStatement.getResultSet();
            this.result.absolute(1);
            res = this.result.getInt(1) + 1;
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

    public ArrayList<Person> getPersonList(){
        ArrayList<Person> resultList = new ArrayList<Person>();
        String sql = "Select oid, Name, vorname, geburtsdatum " +
                "from person";
        try{
            this.preparedStatement = this.connection.prepareCall(sql);
            this.preparedStatement.execute();
            this.result = this.preparedStatement.getResultSet();
            while(this.result.next()){
                resultList.add(new Person(this.result.getString(3),this.result.getString(2),this.result.getDate(4),this.result.getInt(1)));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return resultList;
    }

    public boolean deletePerson(int _OID){
        boolean res;
        String sql = "delete from person " +
                "where OID = ?";
        try{
            this.preparedStatement = this.connection.prepareCall(sql);
            this.preparedStatement.setInt(1,_OID);
            this.preparedStatement.execute();
            res = true;
        }catch(SQLException e)
        {
            e.printStackTrace();
            res = false;
        }
        return res;
    }
    public boolean insertPersons(ArrayList<Person> PersonList){
        boolean res=true;
        int newOID = this.getNewOID();
        String sql = "INSERT INTO PERSON(OID, NAME, VORNAME, GEBURTSDATUM, ERSTELLT_AM, GUELTIG_VON) " +
                "VALUES(?,?,?,?,curdate(),curdate())";
        for (int i = 0; i < PersonList.size(); ++i){
            try{
                this.preparedStatement = this.connection.prepareCall(sql);
                this.preparedStatement.setInt(1,newOID);
                this.preparedStatement.setString(2,PersonList.get(i).getLastName());
                this.preparedStatement.setString(3,PersonList.get(i).getName());
                this.preparedStatement.setDate(4,PersonList.get(i).getBirthday());
                this.preparedStatement.execute();
                newOID++;
                res=true;

            }catch(SQLException e)
            {
                e.printStackTrace();
                res=false;
            }
        }
        return res;
    }
}
