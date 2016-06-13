package Model;

/**
 * Created by Lasse on 28.05.2016.
 * handels all Adresses
 */
public class Adress {
    public Adress(int OID
            , String ZIP_CODE
            , String street
            , int homenumber
            , AdressType adressType) {
        this.OID = OID;
        this.ZIP_CODE = ZIP_CODE;
        this.street = street;
        this.homenumber = homenumber;
        this.adressType = adressType;
    }

    public int getOID() {
        return OID;
    }

    public void setOID(int OID) {
        this.OID = OID;
    }

    public String getZIP_CODE() {
        return ZIP_CODE;
    }

    public void setZIP_CODE(String ZIP_CODE) {
        this.ZIP_CODE = ZIP_CODE;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHome_number() {
        return homenumber;
    }

    public void setHomenumber(int home_number) {
        this.homenumber = home_number;
    }

    public AdressType getAdressType() {
        return adressType;
    }

    public void setAdressType(AdressType adressType) {
        this.adressType = adressType;
    }

    private int OID;
    private String ZIP_CODE;
    private String street;
    private int homenumber;
    private AdressType adressType;
}
