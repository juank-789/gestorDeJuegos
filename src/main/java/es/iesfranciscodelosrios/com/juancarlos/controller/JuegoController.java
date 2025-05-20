package es.iesfranciscodelosrios.com.juancarlos.controller;



import es.iesfranciscodelosrios.com.juancarlos.model.Juego;
import es.iesfranciscodelosrios.com.juancarlos.DAO.JuegoDAO;

import java.util.List;


public class JuegoController {

    /**
     * Get all juegos
     * @return List of juegos
     */
    public static List<Juego> getAllJuegos() {
        return JuegoDAO.findAll();
    }

    /**
     * Get juego by id
     * @param id
     * @return Juego
     */
    public static Juego getJuegoById(int id) {
        return JuegoDAO.findById(id);
    }

    /**
     * Get juego by nombre
     * @param juego
     * @return Juego
     */
    public static Juego addJuego(Juego juego) {

        return JuegoDAO.insert(juego);
    }

    /**
     * Update juego
     * @param juego
     * @return boolean
     */
    public static boolean updateJuego(Juego juego) {

        return JuegoDAO.update(juego);
    }

    /**
     * Delete juego
     * @param id
     * @return boolean
     */
    public static boolean deleteJuego(int id) {
        return JuegoDAO.delete(id);
    }
}
