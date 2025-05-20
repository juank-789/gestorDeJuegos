package es.iesfranciscodelosrios.com.juancarlos.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class FormularioUtil {

    /**
     * Muestra una ventana modal con el formulario especificado.
     * @param fxmlNombre
     * @param tituloVentana
     * @return la ventana mostrada
     */
    public static Stage mostrarVentana(String fxmlNombre, String tituloVentana) {
        try {
            FXMLLoader loader = new FXMLLoader(FormularioUtil.class.getResource(fxmlNombre));
            Parent root = loader.load();

            Stage ventana = new Stage();
            ventana.setTitle(tituloVentana);
            ventana.setScene(new Scene(root));
            ventana.initModality(Modality.APPLICATION_MODAL);
            ventana.show();

            return ventana;
        } catch (IOException e) {
            throw new RuntimeException("No se pudo cargar el formulario: " + fxmlNombre, e);
        }
    }
}
