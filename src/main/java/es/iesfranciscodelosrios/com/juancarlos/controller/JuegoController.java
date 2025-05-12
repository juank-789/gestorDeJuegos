package es.iesfranciscodelosrios.com.juancarlos.controller;


import es.iesfranciscodelosrios.com.juancarlos.model.Juego;


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

    public static Juego addJuego(Juego juego) {

        return JuegoDAO.insert(juego);
    }

    public static boolean updateJuego(Juego juego) {

        return JuegoDAO.update(juego);
    }

    public static boolean deleteJuego(int id) {
        return JuegoDAO.delete(id);
    }
}