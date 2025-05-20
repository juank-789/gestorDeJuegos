package es.iesfranciscodelosrios.com.juancarlos.controller;

import es.iesfranciscodelosrios.com.juancarlos.model.Comentario;
import es.iesfranciscodelosrios.com.juancarlos.model.Juego;
import es.iesfranciscodelosrios.com.juancarlos.DAO.ComentarioDAO;

import java.util.List;


public class ComentarioController {

    /**
     * Get all comentarios
     * @return List of Comentario
     */
    public static List<Comentario> getAllComentarios() {
        return ComentarioDAO.findAll();
    }

    /**
     * Get all comentarios by juego
     * @param id
     * @return List of Comentario
     */
    public static Comentario getComentarioById(int id) {
        return ComentarioDAO.findByIdEager(id);
    }

    /**
     * Get all comentarios by juego
     * @param juego
     * @return List of Comentario
     */
    public static List<Comentario> getComentariosByJuego(Juego juego) {
        return ComentarioDAO.findByJuegoEager(juego);
    }

    /**
     * Get all comentarios by juego
     * @param comentario
     * @return Comentario
     */
    public static Comentario addComentario(Comentario comentario) {

        return ComentarioDAO.insert(comentario);
    }

    /**
     * Update comentario
     * @param comentario
     * @return boolean
     */
    public static boolean updateComentario(Comentario comentario) {

        return ComentarioDAO.update(comentario);

    }

    /**
     * Delete comentario
     * @param id
     * @return boolean
     */
    public static boolean deleteComentario(int id) {

        return ComentarioDAO.delete(id);
    }
}
