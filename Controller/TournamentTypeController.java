package Controller;

import Model.TournamentType;

import java.sql.SQLException;

/**
 * Created by Lasse on 05.06.2016.
 */
public class TournamentTypeController extends DBController{
    public TournamentTypeController() {
        super();
        try {
            this.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public TournamentType getTournamentTypeByName(String _TournamentName)
    {
        TournamentType result = null;
        String sql = "select OID, TURNIER_TYP from TURNIER_TYP " +
                "where gueltig_von <= curdate() " +
                "and " +
                "(gueltig_bis > curdate() " +
                "or isnull(gueltig_bis) )" +
                "and turnier_typ.TURNIER_TYP = ?";
        try{
            this.preparedStatement = this.connection.prepareCall(sql);
            this.preparedStatement.setString(1,_TournamentName);
            this.preparedStatement.execute();
            this.result = this.preparedStatement.getResultSet();
            this.result.next();
            result = new TournamentType((int)this.result.getDouble(1), this.result.getString(2) );

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }
}
