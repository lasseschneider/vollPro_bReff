package Model;

import java.util.Date;

/**
 * Created by Lasse on 28.05.2016.
 */
public class Referee {
    public Referee(){
        this.OID = 0;
        this.person = null;
        this.licenseNumber = "";
        this.licenseValidFrom = null;
        this.licenseValidTo = null;
        this.licenseType = null;
    }

    public Referee(int OID, Person person, String licenseNumber, Date licenseValidFrom, Date licenseValidTo, LicenseType licenseType) {
        this.OID = OID;
        this.person = person;
        this.licenseNumber = licenseNumber;
        this.licenseValidFrom = licenseValidFrom;
        this.licenseValidTo = licenseValidTo;
        this.licenseType = licenseType;
    }

    private int OID;
    private Person person;
    private String licenseNumber;
    private Date licenseValidFrom;
    private Date licenseValidTo;
    private LicenseType licenseType;
}
