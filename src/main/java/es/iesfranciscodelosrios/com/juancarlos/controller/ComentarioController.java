package es.iesfranciscodelosrios.com.juancarlos.controller;

import es.iesfranciscodelosrios.com.juancarlos.model.Comentario;
import es.iesfranciscodelosrios.com.juancarlos.model.Juego;
import es.iesfranciscodelosrios.com.juancarlos.DAO.ComentarioDAO;

import java.util.List;


public class ComentarioController {

    public static List<Comentario> getAllComentarios() {
        return ComentarioDAO.findAll();
    }

    public static Comentario getComentarioById(int id) {
        return ComentarioDAO.findByIdEager(id);
    }

    public static List<Comentario> getComentariosByJuego(Juego juego) {
        return ComentarioDAO.findByJuegoEager(juego);
    }

    public static Comentario addComentario(Comentario comentario) {

        return ComentarioDAO.insert(comentario);
    }

    public static boolean updateComentario(Comentario comentario) {

        return ComentarioDAO.update(comentario);

    }

    public static boolean deleteComentario(int id) {

        return ComentarioDAO.delete(id);
    }
}
