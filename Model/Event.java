package Model;

import java.sql.Date;

/**
 * Created by Lasse on 18.05.2016.
 */
public class Event {
    public Event(int OID
            , String description
            , String summury
            , Adress adress
            , Date event_from
            , Date event_to) {
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
    private Date event_from;
    private Date event_to;
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

    public Date getEvent_from() {
        return event_from;
    }

    public void setEvent_from(Date event_from) {
        this.event_from = event_from;
    }

    public Date getEvent_to() {
        return event_to;
    }

    public void setEvent_to(Date event_to) {
        this.event_to = event_to;
    }
}
