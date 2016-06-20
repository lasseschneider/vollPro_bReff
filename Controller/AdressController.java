package Controller;

import Model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lasse on 28.05.2016.
 */
public class AdressController extends DBController {
    /**
     *
     */
    public AdressController() {
        super();
        try {
            this.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   /* public List<Adress> getAllAdreeses()
    {
        String sql = "select at.ADRESS_TYP as adressType" +
                ",  a.STRASSE as street" +
                ",  a.Hausnummer as homeNumber" +
                ",  a.OID as oid" +
                ",  po.PLZ as ZIP_CODE" +
                ",  po.ORT as city " +
                "from adresse a " +
                "left join adress_typ at on a.adress_type_id = at.oid " +
                "left join plz_ort po on a.PLZ_ORT_ID = po.OID " +
                "where (a.gueltig_von <= curdate() and a.gueltig_bis > curdate())";
        List<Adress> resultList = new ArrayList<Adress>();
        try {
            this.preparedStatement = this.connection.prepareCall(sql);
            this.preparedStatement.execute();
            this.result = this.preparedStatement.getResultSet();
            while(this.result.next())
            {
                resultList.add(new Adress(
                        (int) this.result.getDouble("OID")
                        , this.result.getString("ZIP_CODE")
                        , this.result.getString("street")
                        , (int)this.result.getDouble("homeNumber")
                        , this.result.getString("adressType")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return resultList;
    }
    public List<Adress> getAllAdreesesByPersonID(int personID)
    {
        String sql = "select at.ADRESS_TYP as adressType" +
                ",  a.STRASSE as street" +
                ",  a.Hausnummer as homeNumber" +
                ",  a.OID as oid" +
                ",  po.PLZ as ZIP_CODE" +
                ",  po.ORT as city " +
                "from adresse a " +
                "left join adress_typ at on a.adress_type_id = at.oid " +
                "left join plz_ort po on a.PLZ_ORT_ID = po.OID " +
                "inner join adress_person ap on a.OID = ap.ADRESS_ID" +
                "where (a.gueltig_von <= curdate() " +
                "and a.gueltig_bis > curdate() " +
                "and ap.PERSON_ID = ?)";
        List<Adress> resultList = new ArrayList<Adress>();
        try {
            this.preparedStatement.setInt(1,personID);
            this.preparedStatement = this.connection.prepareCall(sql);
            this.preparedStatement.execute();
            this.result = this.preparedStatement.getResultSet();
            while(this.result.next())
            {
                resultList.add(new Adress(
                        (int) this.result.getDouble("OID")
                        , this.result.getString("ZIP_CODE")
                        , this.result.getString("street")
                        , (int)this.result.getDouble("homeNumber")
                        , this.result.getString("adressType")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }*/
}
