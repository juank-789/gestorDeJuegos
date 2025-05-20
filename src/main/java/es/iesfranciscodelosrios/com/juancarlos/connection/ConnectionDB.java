package es.iesfranciscodelosrios.com.juancarlos.connection;




import es.iesfranciscodelosrios.com.juancarlos.model.ConfigDB;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {
    /**
     * Clase que se encarga de gestionar la conexión a la base de datos
     */
    private static ConfigDB config;

    static {
        try {
            JAXBContext context = JAXBContext.newInstance(ConfigDB.class);
            Unmarshaller um = context.createUnmarshaller();
            config = (ConfigDB) um.unmarshal(new File("connection.xml"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("No se pudo cargar la configuración de la BBDD");
        }
    }

    /**
     * Método que devuelve una conexión a la base de datos
     * @return un objeto Connection
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al conectar a la base de datos");
        }
    }

}
