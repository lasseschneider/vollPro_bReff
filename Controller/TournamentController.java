package Controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Controller.*;
import Model.*;

/**
 * Created by Lasse on 28.05.2016.
 */
public class TournamentController extends DBController{
    public TournamentController() {
        super();
        try {
            this.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*public void insertCSV(String url, User _CurrentUser)
    {

        BufferedReader br = null;
        TournamentTypeController ttc = new TournamentTypeController();
        EventController ec = new EventController();
        AdressController ac = new AdressController();

        Event event = null;
        try {
            br = new BufferedReader(new FileReader(url));
            try {
                while( br.ready()){
                    String zeile = br.readLine();
                    String[] zeilenInhalte = zeile.split(";");
                    int newEvent_ID = ec.getNewOID();
                    event = new Event(newEvent_ID,zeilenInhalte[1],zeilenInhalte[2],zeilenInhalte[3],)
                    //ToDo: Insert eines Tournaments impementieren mit dem.
                    //ToDo: Insert von Adresse
                    this.insertTournament(_CurrentUser, ttc.getTournamentTypeByName(zeilenInhalte[0]), event);
                }
            } catch (IOException ioe){
                ioe.printStackTrace();
            }
        }catch (FileNotFoundException fne)
        {
            fne.printStackTrace();
        }
        }*/

    /*public List<Tournament> getAllTournaments()
    {
        String sql = "select * from v_turnier";
        List<Tournament> resultList = new ArrayList<Tournament>();
        try {
            this.preparedStatement = this.connection.prepareCall(sql);
            this.preparedStatement.execute();
            this.result = this.preparedStatement.getResultSet();
            while(this.result.next())
            {
                resultList.add(new Tournament(
                        (int) this.result.getDouble("OID")
                        , this.result.getString("ZIP_CODE")
                        , this.result.getString("street")
                        , (int)this.result.getDouble("homeNumber")
                        , this.result.getString("adressType")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }*/
    public boolean insertTournament(User _CurrentUser,  TournamentType _TournamentType, Event _Event)
    {
        boolean result = false;
        String sql = "insert into turnier (OID, TERMIN_ID, TURNIER_TYP_ID, ERSTELLT_VON_ID, ERSTELLT_AM, GUELTIG_VON) " +
                "values(?,?,?,?,curdate(),curdate())";
        try {
            PreparedStatement ps;
            ps = this.connection.prepareCall(sql);
            ps.setInt(1,this.getNewOID());
            ps.setInt(2,_Event.getOID());
            ps.setInt(3,_TournamentType.getOID());
            ps.setInt(4,_CurrentUser.getOid());
            ps.execute();
            result = true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getNewOID() {
        int newOID = -2147483648;
        String sql = "select max(OID) as newOID from turnier";
        try{
            this.preparedStatement = this.connection.prepareCall(sql);
            this.preparedStatement.execute();
            this.result = this.preparedStatement.getResultSet();
            this.result.next();
            newOID = (int)this.result.getDouble("newOID") + 1;
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return newOID;
    }


}
