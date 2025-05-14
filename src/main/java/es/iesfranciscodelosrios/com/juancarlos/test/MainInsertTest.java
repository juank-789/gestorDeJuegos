package es.iesfranciscodelosrios.com.juancarlos.test;


import es.iesfranciscodelosrios.com.juancarlos.controller.ComentarioController;
import es.iesfranciscodelosrios.com.juancarlos.controller.DesarrolladoraController;
import es.iesfranciscodelosrios.com.juancarlos.controller.JuegoController;
import es.iesfranciscodelosrios.com.juancarlos.model.Comentario;
import es.iesfranciscodelosrios.com.juancarlos.model.Desarrolladora;
import es.iesfranciscodelosrios.com.juancarlos.model.Juego;

import java.time.LocalDate;

public class MainInsertTest {
    public static void main(String[] args) {
        // Insertar desarrolladoras
        Desarrolladora d1 = new Desarrolladora(1, "Nintendo", null);
        Desarrolladora d2 = new Desarrolladora(2, "Ubisoft", null);

        if (DesarrolladoraController.addDesarrolladora(d1) != null) {
            System.out.println("Desarrolladora agregado");
        }
        if (DesarrolladoraController.addDesarrolladora(d2) != null) {
            System.out.println("Desarrolladora 2 agregado");
        }


        // Insertar juegos
        Juego j1 = new Juego(0, "Zelda", "Aventura", d1, null);
        Juego j2 = new Juego(0, "Assassin's Creed", "Acción", d2, null);
        j1 = JuegoController.addJuego(j1);
        j2 = JuegoController.addJuego(j2);
        if (j1.getId() != 0) {
            System.out.println("Juego insertado con id" + j1.getId());
        }
        if (j2.getId() != 0) {
            System.out.println("Juego 2 insertado con id" + j2.getId());
        }

        // Insertar comentarios
        Comentario c1 = new Comentario(0, "Gran juego", LocalDate.now(), j1);
        Comentario c2 = new Comentario(0, "Muy entretenido", LocalDate.now(), j2);
        ComentarioController.addComentario(c1);
        ComentarioController.addComentario(c2);

        System.out.println("Datos insertados correctamente.");
    }
}

