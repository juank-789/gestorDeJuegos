package es.iesfranciscodelosrios.com.juancarlos.test;


import es.iesfranciscodelosrios.com.juancarlos.controller.ComentarioController;
import es.iesfranciscodelosrios.com.juancarlos.controller.DesarrolladoraController;
import es.iesfranciscodelosrios.com.juancarlos.controller.JuegoController;
import es.iesfranciscodelosrios.com.juancarlos.model.Comentario;
import es.iesfranciscodelosrios.com.juancarlos.model.Desarrolladora;
import es.iesfranciscodelosrios.com.juancarlos.model.Juego;

import java.time.LocalDate;

public class MainUpdateTest {
    public static void main(String[] args) {
        // Actualizar Desarrolladora con ID 1
        Desarrolladora d = DesarrolladoraController.getDesarrolladoraById(7);
        if (d != null) {
            d.setNombre("Nintendo Actualizado");
            DesarrolladoraController.updateDesarrolladora(d);
        }

        // Actualizar Juego con ID 1
        Juego j = JuegoController.getJuegoById(13);
        if (j != null) {
            j.setTitulo("Zelda Breath of the Wild");
            j.setGenero("Aventura Épica");
            JuegoController.updateJuego(j);
        }

        // Actualizar Comentario con ID 1
        Comentario c = ComentarioController.getComentarioById(9);
        if (c != null) {
            c.setTexto("Juego impresionante");
            c.setFecha(LocalDate.now());
            ComentarioController.updateComentario(c);
        }

        System.out.println("Datos actualizados correctamente.");
    }
}

