package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
   
/**
 *
 * @Saipo
 */
public class DB {
    // Download the Java MySQL connector -> https://dev.mysql.com/downloads/connector/j/

    private static final String serverName = "localhost";
    private static final String userName = "root";
    private static final String dbName = "java_vehicle_rental";
    private static final int portNumber = 3306;
    private static final String pass = ""; 

    private static final Logger logger = Logger.getLogger(DB.class.getName());

    // Function to create and return the connection
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName;
            connection = DriverManager.getConnection(url, userName, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            logger.log(Level.SEVERE, "Database connection error", ex);
        }
        
        return connection;
    }
}
