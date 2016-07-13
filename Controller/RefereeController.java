package Controller;

import java.sql.SQLException;

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

}
