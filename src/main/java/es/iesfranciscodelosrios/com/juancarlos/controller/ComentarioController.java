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
        return ComentarioDAO.findById(id);
    }

    public static List<Comentario> getComentariosByJuego(Juego juego) {
        return ComentarioDAO.findByJuegoEager(juego);
    }

    public static void addComentario(Comentario comentario) {
        ComentarioDAO.insert(comentario);
    }

    public static void updateComentario(Comentario comentario) {
        ComentarioDAO.update(comentario);
    }

    public static void deleteComentario(int id) {
        ComentarioDAO.delete(id);
    }
}
