package es.iesfranciscodelosrios.com.juancarlos.controller;

import es.iesfranciscodelosrios.com.juancarlos.model.Comentario;
import es.iesfranciscodelosrios.com.juancarlos.model.Juego;
import es.iesfranciscodelosrios.com.juancarlos.DAO.ComentarioDAO;

import java.util.List;

public class ComentarioController {

    public List<Comentario> obtenerTodos() {
        return ComentarioDAO.findAll();
    }

    public List<Comentario> obtenerPorJuego(Juego juego) {
        return ComentarioDAO.findByJuegoEager(juego);  // o Lazy según el uso
    }

    public Comentario buscarPorId(int id) {
        return ComentarioDAO.findById(id);
    }

    public Comentario insertar(Comentario c) {
        return ComentarioDAO.insert(c);
    }

    public void actualizar(Comentario c) {
        ComentarioDAO.update(c);
    }

    public void eliminar(int id) {
        ComentarioDAO.delete(id);
    }
}
