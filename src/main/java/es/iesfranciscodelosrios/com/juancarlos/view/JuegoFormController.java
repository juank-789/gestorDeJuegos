package es.iesfranciscodelosrios.com.juancarlos.view;

import es.iesfranciscodelosrios.com.juancarlos.controller.DesarrolladoraController;
import es.iesfranciscodelosrios.com.juancarlos.controller.JuegoController;
import es.iesfranciscodelosrios.com.juancarlos.model.Desarrolladora;
import es.iesfranciscodelosrios.com.juancarlos.model.Juego;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class JuegoFormController implements Initializable {

    @FXML
    private TextField txtTitulo;

    @FXML
    private TextField txtGenero;

    @FXML
    private ComboBox<Desarrolladora> desarrolladora_id;

    private Juego juego;

    private static Juego juegoActual;
    private static Stage ventana;

    public static void mostrarFormulario(Juego juego) {
        juegoActual = juego;
        ventana = FormularioUtil.mostrarVentana("JuegoForm.fxml", "Formulario de Juego");
    }

    public static void setJuego(Juego j) {
        juegoActual = j;

    }

    private void setJu(Juego j) {
        this.juego = j;
        if (j != null) {
            txtTitulo.setText(j.getTitulo());
            txtGenero.setText(j.getGenero());
            desarrolladora_id.setValue(j.getDesarrolladora());
        }
    }

    @FXML
    private void onGuardar() {
        String titulo = txtTitulo.getText();
        String genero = txtGenero.getText();
        Desarrolladora desarrolladora = desarrolladora_id.getValue();

        if (titulo.isEmpty() || genero.isEmpty() || desarrolladora == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Todos los campos son obligatorios.");
            alert.showAndWait();
            return;
        }

        if (juegoActual == null) {
            juegoActual = new Juego();
        }

        juegoActual.setTitulo(titulo);
        juegoActual.setGenero(genero);
        juegoActual.setDesarrolladora(desarrolladora);

        if (juegoActual.getId() == 0) {
            JuegoController.addJuego(juegoActual);
        } else {
            JuegoController.updateJuego(juegoActual);
        }

        cerrarVentana();
    }

    @FXML
    private void onCancelar() {
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) txtTitulo.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setJu(juegoActual);


        List<Desarrolladora> lista = DesarrolladoraController.getAllDesarrolladoras();
        System.out.println("Lista de desarrolladoras: " + lista);
        desarrolladora_id.setItems(FXCollections.observableArrayList(lista));

    }
}
