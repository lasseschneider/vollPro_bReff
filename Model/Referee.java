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

    public int getOID() {
        return OID;
    }

    public void setOID(int OID) {
        this.OID = OID;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Date getLicenseValidFrom() {
        return licenseValidFrom;
    }

    public void setLicenseValidFrom(Date licenseValidFrom) {
        this.licenseValidFrom = licenseValidFrom;
    }

    public Date getLicenseValidTo() {
        return licenseValidTo;
    }

    public void setLicenseValidTo(Date licenseValidTo) {
        this.licenseValidTo = licenseValidTo;
    }

    public LicenseType getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(LicenseType licenseType) {
        this.licenseType = licenseType;
    }
}
