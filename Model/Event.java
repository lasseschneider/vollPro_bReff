package Model;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Lasse on 18.05.2016.
 */
public class Event {
    public Event(int OID
            , String description
            , String summury
            , Adress adress
            , Timestamp event_from
            , Timestamp event_to) {
        this.OID = OID;
        this.description = description;
        this.summury = summury;
        this.adress = adress;
        this.event_from = event_from;
        this.event_to = event_to;
    }

    private int OID;
    private String description;
    private String summury;
    private Adress adress;
    private Timestamp event_from;
    private Timestamp event_to;
    public Event(int _OID)
    {
        this.OID = _OID;
    }

    public int getOID() {
        return OID;
    }

    public void setOID(int OID) {
        this.OID = OID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSummury() {
        return summury;
    }

    public void setSummury(String summury) {
        this.summury = summury;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public Timestamp getEvent_from() {
        return event_from;
    }

    public void setEvent_from(Timestamp event_from) {
        this.event_from = event_from;
    }
    public void setEvent_from(String event_from){
         try{
            SimpleDateFormat sdf = new SimpleDateFormat();
            sdf.applyPattern("YYYY-MM-DD");
            this.event_from = (Timestamp)sdf.parse(event_from);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public Timestamp getEvent_to() {
        return event_to;
    }
    public void setEvent_to(Timestamp event_to) {
        this.event_to = event_to;
    }
    public void setEvent_to(String event_to){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat();
            sdf.applyPattern("YYYY-MM-DD");
            this.event_to = (Timestamp)sdf.parse(event_to);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
