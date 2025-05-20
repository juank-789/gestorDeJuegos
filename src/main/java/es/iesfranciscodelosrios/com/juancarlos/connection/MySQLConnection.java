package es.iesfranciscodelosrios.com.juancarlos.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    private static Connection con = null;
    private static final String file = "connection.xml";
    private static MySQLConnection _instance;
    private static MySQLproperties properties;

    /**
     * Constructor de MySQLConnection
     */
    private MySQLConnection() {
        properties = (MySQLproperties) XMLManager.readXML(new MySQLproperties(), file);

        try {
            con = DriverManager.getConnection(properties.getURL(), properties.getUser(), properties.getPass());
        } catch (SQLException e) {
            e.printStackTrace();
            con = null;
        }
    }

    /**
     * Método para cerrar la instancia de MySQLConnection
     */
    public static void closeConnection() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Método para obtener la instancia de MySQLConnection
     * @return un objeto Connection
     */
    public static Connection getConnection() {
        if (con == null) {
            try {
                con = DriverManager.getConnection(properties.getURL(), properties.getUser(), properties.getPass());
            } catch (SQLException SQLe) {
                throw new RuntimeException(SQLe);
            }
        }
        return con;
    }

    /**
     * Método que devuelve el propio MySQLConnection
     * @return un objeto MySQLConnection
     */
    public static MySQLConnection build() {
        return new MySQLConnection();
    }
}