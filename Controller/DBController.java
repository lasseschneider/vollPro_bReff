package Controller;
import java.sql.*;

/**
 * Created by Lasse on 18.05.2016.
 */
public class DBController {
    /**
     * DB Connection
     */
    protected Connection connection;
    /**
     * DB Statement
     */
    protected Statement statement;
    /**
     * DB result
     */
    protected ResultSet result;
    /**
     * DB prepared Statement
     */
    protected PreparedStatement preparedStatement;

    /**
     * standard Constructor
     */
    public DBController()
    {
        connection          = null;
        statement           = null;
        result              = null;
        preparedStatement   = null;
    }

    /**
     * connects the child class to the data base
     * @throws SQLException
     */
    public void connect() throws SQLException {
        if(this.connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vp_br", "root", "root");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * disconnects the child class from the data base
     */
    public void disconnect()
    {
        try
        {
            if(this.result != null)
            {
                result.close();
            }

            if(this.statement != null)
            {
                this.statement.close();
            }

            if(this.preparedStatement != null)
            {
                this.preparedStatement.close();
            }

            if(this.connection != null)
            {
                this.connection.close();
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
