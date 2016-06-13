package Model;

/**
 * Created by Lasse on 28.05.2016.
 */
public class LicenseType {
    public LicenseType(int OID, String name) {
        this.OID = OID;
        this.name = name;
    }

    private int OID;
    private String name;

    public int getOID() {
        return OID;
    }

    public void setOID(int OID) {
        this.OID = OID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
