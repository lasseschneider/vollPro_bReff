package Model;

import java.sql.Date;

/**
 * Created by Lasse on 28.05.2016.
 */
public class Person {

    public Person(String name, String lastName, Date birthday, int OID, RefereeGroup refereeGroup) {
        this.name = name;
        this.lastName = lastName;
        this.birthday = birthday;
        this.OID = OID;
        this.refereeGroup = refereeGroup;
    }

    public Person(String name, String lastName, Date birthday, int OID) {
        this.name = name;
        this.lastName = lastName;
        this.birthday = birthday;
        this.OID = OID;
        this.refereeGroup = null;
    }

    private String name;
    private String lastName;
    private Date birthday;
    private int OID;
    private RefereeGroup refereeGroup;

    public Person() {
        this.name = null;
        this.lastName = null;
        this.birthday = null;
        this.OID = 0;
        this.refereeGroup = null;
    }

    public RefereeGroup getRefereeGroup() {
        return refereeGroup;
    }

    public void setRefereeGroup(RefereeGroup refereeGroup) {
        this.refereeGroup = refereeGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getOID() {
        return OID;
    }

    public void setOID(int OID) {
        this.OID = OID;
    }


}
