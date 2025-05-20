package es.iesfranciscodelosrios.com.juancarlos.view;

import es.iesfranciscodelosrios.com.juancarlos.controller.ComentarioController;
import es.iesfranciscodelosrios.com.juancarlos.controller.DesarrolladoraController;
import es.iesfranciscodelosrios.com.juancarlos.controller.JuegoController;
import es.iesfranciscodelosrios.com.juancarlos.model.Comentario;
import es.iesfranciscodelosrios.com.juancarlos.model.Desarrolladora;
import es.iesfranciscodelosrios.com.juancarlos.model.Juego;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ComentarioFormController implements Initializable {

    @FXML
    private TextField textoField;

    @FXML
    private DatePicker fechaPicker;

    @FXML
    private ComboBox<Juego> txtTitulo;

    private Comentario comentario;

    private static Comentario comentarioActual;
    private static Juego juegoRelacionado;


    public void setComentario(Comentario c, Juego juego) {
        this.comentario = c;
        this.juegoRelacionado = juego;

        if (c != null) {
            textoField.setText(c.getTexto());
            fechaPicker.setValue(c.getFecha());
        }
    }

    /**
     * Método para establecer el comentario actual
     * @param c
     */
    public static void setComentario(Comentario c) {
        comentarioActual = c;

    }

    /**
     * Método para establecer el comentario actual
     * @param c
     */
    private void setCo(Comentario c) {
        this.comentario = c;
        if (c != null) {
            textoField.setText(c.getTexto());
            fechaPicker.setValue(c.getFecha());
            txtTitulo.setValue(c.getJuego());
        }
    }

    /**
     * Método para guardar el comentario
     */
    @FXML
    private void onGuardar() {
        if (comentario == null) {
            comentario = new Comentario();
        }

        comentario.setTexto(textoField.getText());
        comentario.setFecha(fechaPicker.getValue() != null ? fechaPicker.getValue() : LocalDate.now());
        comentario.setJuego(txtTitulo.getValue());

        if (comentario.getId() > 0) {
            ComentarioController.updateComentario(comentario);
        } else {
            ComentarioController.addComentario(comentario);
        }

        cerrarVentana();
    }

    /**
     * Método para cancelar la acción
     */
    @FXML
    private void onCancelar() {
        cerrarVentana();
    }

    /**
     * Método para cerrar la ventana
     */
    private void cerrarVentana() {
        Stage stage = (Stage) textoField.getScene().getWindow();
        stage.close();
    }

    /**
     * Método para inicializar el controlador
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCo(comentarioActual);

        List<Juego> lista = JuegoController.getAllJuegos();
        txtTitulo.setItems(FXCollections.observableArrayList(lista));
    }
}
