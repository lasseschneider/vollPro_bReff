package Model;

import sun.java2d.pipe.hw.AccelDeviceEventNotifier;

/**
 * Created by Lasse on 29.05.2016.
 */
public class Tournament {
    public Tournament(int OID, TournamentType tournamentType, Event event) {
        this.OID = OID;
        this.tournamentType = tournamentType;
        this.event = event;
    }

    private int OID;
    private TournamentType tournamentType;
    private Event event;

    public int getOID() {
        return OID;
    }

    public void setOID(int OID) {
        this.OID = OID;
    }

    public TournamentType getTournamentType() {
        return tournamentType;
    }

    public void setTournamentType(TournamentType tournamentType) {
        this.tournamentType = tournamentType;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
