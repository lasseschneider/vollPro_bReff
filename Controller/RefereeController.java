package Controller;

import Model.LicenseType;
import Model.Person;
import Model.Referee;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Lasse on 13.07.2016.
 */
public class RefereeController extends DBController {
    public RefereeController(){
        super();
        try {
            this.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Referee> getAllReferees(){
        ArrayList<Referee> resultList = new ArrayList<Referee>();
        String sql = "select p.NAME " +
                ",  p.VORNAME " +
                ",  p.GEBURTSDATUM " +
                ",  sr.PERSON_ID as P_ID " +
                ",  sr.OID as SR_ID " +
                ",  sr.LIZENZNUMMER " +
                ",  l.LIZENZSTUFE_NAME as LIZENZSTUFE " +
                ",  sr.LIZENZ_GUELTIG_VON " +
                ",  sr.LIZENZ_GUELTIG_BIS " +
                ",  sr.LIZENZSTUFE_ID as L_ID " +
                "from schiedsrichter sr " +
                "inner join person p on sr.PERSON_ID = p.OID " +
                "inner join lizenzstufe l on sr.lizenzstufe_id = l.OID";
        try{
            this.preparedStatement = this.connection.prepareCall(sql);
            this.preparedStatement.execute();
            this.result = this.preparedStatement.getResultSet();
            while(this.result.next()){
                resultList.add(new Referee(result.getInt(5)
                        ,new Person(    result.getString(2)
                                        ,result.getString(1)
                                        ,result.getDate(3)
                                        ,result.getInt(4)
                                    )
                        ,result.getString(6)
                        , result.getDate(8)
                        , result.getDate(9)
                        , new LicenseType(  result.getInt(10)
                                            , result.getString(7)
                                        )
                                        )
                            );
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return resultList;
    }
}
