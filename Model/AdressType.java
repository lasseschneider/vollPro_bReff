package Model;

/**
 * Created by Lasse on 28.05.2016.
 */
public class AdressType {
    private int OID;

    public AdressType(int OID, String name) {
        this.OID = OID;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOID() {
        return OID;
    }

    public void setOID(int OID) {
        this.OID = OID;
    }

    private String name;
}
