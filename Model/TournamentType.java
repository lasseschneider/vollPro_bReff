package Model;

/**
 * Created by Lasse on 29.05.2016.
 */
public class TournamentType {
    public TournamentType(int OID, String name) {
        this.OID = OID;
        Name = name;
    }
    private int OID;
    private String Name;

    public int getOID() {
        return OID;
    }

    public void setOID(int OID) {
        this.OID = OID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
