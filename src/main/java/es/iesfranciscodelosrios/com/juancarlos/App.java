package es.iesfranciscodelosrios.com.juancarlos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /*public static void main(String[] args) {
        launch();

    }*/




        public static void main(String[] args) {
            String url = "jdbc:mysql://localhost:3306/games_db"; // Cambia por el nombre de tu base de datos
            String usuario = "root"; // Cambia por tu usuario
            String contraseña = ""; // Cambia por tu contraseña

            String insertSQL = "INSERT INTO desarrolladora (nombreDesarrolladora) VALUES (?)";

            try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
                 PreparedStatement stmt = conn.prepareStatement(insertSQL)) {

                stmt.setString(1, "Super Mario Bros"); // Valor de prueba
                int filasAfectadas = stmt.executeUpdate();

                if (filasAfectadas > 0) {
                    System.out.println("Conexión correcta. Inserción exitosa.");
                } else {
                    System.out.println("Conexión establecida, pero no se insertó nada.");
                }

            } catch (SQLException e) {
                System.out.println("Error al conectar o insertar: " + e.getMessage());
            }

        }


}