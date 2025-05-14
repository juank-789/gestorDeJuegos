package es.iesfranciscodelosrios.com.juancarlos.view;

import es.iesfranciscodelosrios.com.juancarlos.App;
import es.iesfranciscodelosrios.com.juancarlos.DAO.DesarrolladoraDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import es.iesfranciscodelosrios.com.juancarlos.model.*;
import es.iesfranciscodelosrios.com.juancarlos.controller.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MainViewController {

    // Juegos
    @FXML private TableView<Juego> tablaJuegos;
    @FXML private TableColumn<Juego, String> colTitulo;
    @FXML private TableColumn<Juego, String> colGenero;
    @FXML private TableColumn<Juego, String> colDesarrolladora;

    // Desarrolladoras
    @FXML private TableView<Desarrolladora> tablaDesarrolladoras;
    @FXML private TableColumn<Desarrolladora, String> colNombreDes;
    @FXML private TableColumn<Desarrolladora, String> colPais;

    // Comentarios
    @FXML private TableView<Comentario> tablaComentarios;
    @FXML private TableColumn<Comentario, String> colTexto;
    @FXML private TableColumn<Comentario, String> colFecha;
    @FXML private TableColumn<Comentario, String> colJuego;

    @FXML
    public void initialize() {
        loadJuegos();
        loadDesarrolladoras();
        loadComentarios();
    }

    private void loadJuegos() {
        List<Juego> juegos = JuegoController.getAllJuegos();
        ObservableList<Juego> lista = FXCollections.observableArrayList(juegos);
        tablaJuegos.setItems(lista);
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        colDesarrolladora.setCellValueFactory(
                cellData -> new SimpleStringProperty(
                        cellData.getValue().getDesarrolladora().getNombre()
                )
        );

    }

    private void loadDesarrolladoras() {
        List<Desarrolladora> des = DesarrolladoraController.getAllDesarrolladoras();
        ObservableList<Desarrolladora> lista = FXCollections.observableArrayList(des);
        tablaDesarrolladoras.setItems(lista);
        colNombreDes.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPais.setCellValueFactory(new PropertyValueFactory<>("pais"));
    }

    private void loadComentarios() {
        List<Comentario> comentarios = ComentarioController.getAllComentarios();
        ObservableList<Comentario> lista = FXCollections.observableArrayList(comentarios);
        tablaComentarios.setItems(lista);
        colTexto.setCellValueFactory(new PropertyValueFactory<>("texto"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colJuego.setCellValueFactory(
                cellData -> new SimpleStringProperty(
                        cellData.getValue().getJuego().getTitulo()
                )
        );
    }

    // =============================
    // MÉTODOS JUEGOS
    // =============================

    @FXML
    private void onInsertJuego() {
        JuegoFormController.setJuego(null);
        OpenModal("/es/iesfranciscodelosrios/com/juancarlos/view/JuegoForm.fxml");
        loadJuegos();
    }

    @FXML
    private void onUpdateJuego() {

        Juego j = tablaJuegos.getSelectionModel().getSelectedItem();
        if (j != null) {
            j.setTitulo(j.getTitulo() + " (actualizado)");
            JuegoFormController.setJuego(j);
            OpenModal("/es/iesfranciscodelosrios/com/juancarlos/view/JuegoForm.fxml");
            loadJuegos();
        }

    }


    @FXML
    private void onDeleteJuego() {
        Juego j = tablaJuegos.getSelectionModel().getSelectedItem();
        if (j != null) {
            JuegoController.deleteJuego(j.getId());
            loadJuegos();
        }
    }

    // =============================
    // MÉTODOS DESARROLLADORAS
    // =============================

    @FXML
    private void onInsertDesarrolladora() {


        DesarrolladoraFormController.setDesarrolladora(null);
        OpenModal("/es/iesfranciscodelosrios/com/juancarlos/view/DesarrolladoraForm.fxml");
        loadDesarrolladoras();
    }

    @FXML
    private void onUpdateDesarrolladora() {
        Desarrolladora d = tablaDesarrolladoras.getSelectionModel().getSelectedItem();
        if (d != null) {
            d.setNombre(d.getNombre() + " (actualizado)");
            DesarrolladoraFormController.setDesarrolladora(d);
            OpenModal("/es/iesfranciscodelosrios/com/juancarlos/view/DesarrolladoraForm.fxml");
            loadDesarrolladoras();
        }
    }

    @FXML
    private void onDeleteDesarrolladora() {
        Desarrolladora d = tablaDesarrolladoras.getSelectionModel().getSelectedItem();
        if (d != null) {
            DesarrolladoraController.deleteDesarrolladora(d.getId());
            loadDesarrolladoras();
        }
    }

    // =============================
    // MÉTODOS COMENTARIOS
    // =============================

    @FXML
    private void onInsertComentario() {
        // Insertar comentario dummy (asociado al primer juego de la tabla si hay)

        ComentarioFormController.setComentario(null);
        OpenModal("/es/iesfranciscodelosrios/com/juancarlos/view/ComentarioForm.fxml");
        loadComentarios();
    }

    @FXML
    private void onUpdateComentario() {


        Comentario c = tablaComentarios.getSelectionModel().getSelectedItem();
        if (c != null) {
            c.setTexto(c.getTexto() + " (actualizado)");
            ComentarioFormController.setComentario(c);
            OpenModal("/es/iesfranciscodelosrios/com/juancarlos/view/ComentarioForm.fxml");
            loadComentarios();
        }

    }

    @FXML
    private void onDeleteComentario() {
        Comentario c = tablaComentarios.getSelectionModel().getSelectedItem();
        if (c != null) {
            ComentarioController.deleteComentario(c.getId());
            loadComentarios();
        }
    }



    public void OpenModal(String url) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
            Parent root = loader.load();

            Stage modalStage = new Stage();
            modalStage.setTitle("Formulario");
            modalStage.setScene(new Scene(root));
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al abrir el formulario modal: " + url, e);
        }
    }

}
