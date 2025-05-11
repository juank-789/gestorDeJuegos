package es.iesfranciscodelosrios.com.juancarlos.controller;



import es.iesfranciscodelosrios.com.juancarlos.model.Juego;
import es.iesfranciscodelosrios.com.juancarlos.DAO.JuegoDAO;

import java.util.List;


public class JuegoController {

    public static List<Juego> getAllJuegos() {
        return JuegoDAO.findAll();
    }

    public static Juego getJuegoById(int id) {
        return JuegoDAO.findById(id);
    }

    public static void addJuego(Juego juego) {
        JuegoDAO.insert(juego);
    }

    public static void updateJuego(Juego juego) {
        JuegoDAO.update(juego);
    }

    public static void deleteJuego(int id) {
        JuegoDAO.delete(id);
    }
}