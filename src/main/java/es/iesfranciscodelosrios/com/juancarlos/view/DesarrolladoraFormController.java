package es.iesfranciscodelosrios.com.juancarlos.view;

import es.iesfranciscodelosrios.com.juancarlos.controller.DesarrolladoraController;
import es.iesfranciscodelosrios.com.juancarlos.model.Desarrolladora;
import es.iesfranciscodelosrios.com.juancarlos.model.Juego;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DesarrolladoraFormController implements Initializable {

    @FXML
    private TextField nombreField;

    @FXML
    private TextField paisField;

    private Desarrolladora desarrolladora;

    private static Desarrolladora desarrolladoraActual;
    private static Stage ventana;


    /**
     * Muestra el formulario de desarrolladora.
     * @param desarrolladora
     */
    public static void mostrarFormulario(Desarrolladora desarrolladora) {
        desarrolladoraActual = desarrolladora;
        ventana = FormularioUtil.mostrarVentana("DesarrolladoraForm.fxml", "Formulario de Desarrolladora");
    }

    /**
     * Muestra el formulario de desarrolladora.
     * @param d
     */
    public static void setDesarrolladora(Desarrolladora d) {
        desarrolladoraActual = d;

    }

    /**
     * Muestra el formulario de desarrolladora.
     * @param d
     */
    private void setDes(Desarrolladora d) {
        this.desarrolladora = d;
        if (d != null) {
            nombreField.setText(d.getNombre());
            paisField.setText(d.getPais());
        }
    }

    /**
     * Guarda la desarrolladora.
     */
    @FXML
    private void onGuardar() {
        if (desarrolladora == null) {
            desarrolladora = new Desarrolladora();
        }

        desarrolladora.setNombre(nombreField.getText());
        desarrolladora.setPais(paisField.getText());
        desarrolladora.setJuegos(new ArrayList<>());

        System.out.println("Guardando: " + desarrolladora);
        if (desarrolladora.getId() > 0) {
            DesarrolladoraController.updateDesarrolladora(desarrolladora);
        } else {
            DesarrolladoraController.addDesarrolladora(desarrolladora);
        }

        cerrarVentana();
    }


    /**
     * Cancela la acción y cierra la ventana.
     */
    @FXML
    private void onCancelar() {
        cerrarVentana();
    }

    /**
     * Cierra la ventana.
     */
    private void cerrarVentana() {
        Stage stage = (Stage) nombreField.getScene().getWindow();
        stage.close();
    }

    /**
     * Inicializa el controlador.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDes(desarrolladoraActual);
    }
}
