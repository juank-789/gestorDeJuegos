package es.iesfranciscodelosrios.com.juancarlos;


import es.iesfranciscodelosrios.com.juancarlos.controller.ComentarioController;
import es.iesfranciscodelosrios.com.juancarlos.controller.JuegoController;
import es.iesfranciscodelosrios.com.juancarlos.controller.DesarrolladoraController;

public class MainDeleteTest {
    public static void main(String[] args) {
        // Eliminar comentarios
        ComentarioController.deleteComentario(7);
        ComentarioController.deleteComentario(8);

        // Eliminar juegos
        JuegoController.deleteJuego(11);
        JuegoController.deleteJuego(12);

        // Eliminar desarrolladoras
        DesarrolladoraController.deleteDesarrolladora(5);
        DesarrolladoraController.deleteDesarrolladora(7);

        System.out.println("Datos eliminados correctamente.");
    }
}
