package Model;

/**
 * Created by Lasse on 28.05.2016.
 */
public class RefereeGroup {
    public RefereeGroup(int OID, User groupChef, User groupAdmin, String groupName) {
        this.OID = OID;
        this.groupChef = groupChef;
        this.groupAdmin = groupAdmin;
        this.groupName = groupName;
    }

    private int OID;
    private User groupChef;
    private User groupAdmin;
    private String groupName;



    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getOID() {
        return OID;
    }

    public void setOID(int OID) {
        this.OID = OID;
    }

    public User getGroupChef() {
        return groupChef;
    }

    public void setGroupChef(User groupChef) {
        this.groupChef = groupChef;
    }

    public User getGroupAdmin() {
        return groupAdmin;
    }

    public void setGroupAdmin(User groupAdmin) {
        this.groupAdmin = groupAdmin;
    }


}
