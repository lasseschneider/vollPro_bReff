package Controller;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.*;

/**
 * Created by Lasse on 18.05.2016.
 */
public class EventController extends DBController{
    public EventController()
    {
        super();
        try {
            this.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param OID
     * @return
     */
    public Event getEventByID(int OID)
    {
        Event returningEvent = null;
        try
        {
            this.connect();
            this.statement = connection.createStatement();

            String sql = "Select * from v_event e where e.OID = " + OID;

            this.result = this.statement.executeQuery(sql);
            int Event_OID = 0;
            while(this.result.next())
            {
                Event_OID    = (int)this.result.getDouble("OID");
            }
            returningEvent = new Event(OID);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return returningEvent;
    }

    /**
     * @param _CurrentUser
     * @param _Event
     * @return
     */
    public boolean insertEvent(User _CurrentUser, Event _Event) {
        boolean result = false;
        String sql = "insert into TERMIN " +
                "(OID, TERMIN_VON, TERMIN_BIS, BETREFF, NOTIZ, ERSTELLT_VON_ID, ERSTELLT_AM, GUELTIG_VON) " +
                "values (?,?,?,?,?,?,curdate(), curdate())";
        try{
            this.preparedStatement = this.connection.prepareCall(sql);
            this.preparedStatement.setInt(1, _Event.getOID());
            this.preparedStatement.setTimestamp(2, _Event.getEvent_from());
            this.preparedStatement.setTimestamp(3, _Event.getEvent_to());
            this.preparedStatement.setString(4, _Event.getDescription());
            this.preparedStatement.setString(5, _Event.getSummury());
            this.preparedStatement.setInt(6, _CurrentUser.getOid());
            this.preparedStatement.execute();
            result = true;
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @return
     */
    public int getNewOID() {
        int newOID = -2147483648;
        String sql = "select max(OID) as MAX_OID from Termine where " +
                "gueltig_von <= curdate() " +
                "and ( gueltig_bis >= curdate() " +
                "or isnull(gueltig_bis) )";
        try{
            this.preparedStatement = this.connection.prepareCall(sql);
            this.preparedStatement.execute();
            this.result = this.preparedStatement.getResultSet();
            this.result.next();
            newOID = this.result.getInt(1) + 1;
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return newOID;
    }
   /* public List<Event> getAllEvents()
    {
        List<Event> resultList = new ArrayList<Event>();
        {
            String sql = "select at.ADRESS_TYP as adressType " +
                    "from adress_typ at " +
                    "inner join adress a on a.Adress_typ_id = at.OID " +

                    "where (a.gueltig_von <= curdate() and a.gueltig_bis > curdate())";
            try {
                this.preparedStatement = this.connection.prepareCall(sql);
                this.preparedStatement.execute();
                this.result = this.preparedStatement.getResultSet();
                while(this.result.next())
                {
                    resultList.add(new Adress(
                            (int) this.result.getDouble("OID")

                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }



        return resultList;
    }*/
}

