package es.iesfranciscodelosrios.com.juancarlos.controller;



import es.iesfranciscodelosrios.com.juancarlos.model.Juego;
import es.iesfranciscodelosrios.com.juancarlos.DAO.JuegoDAO;

import java.util.List;

public class JuegoController {

    public List<Juego> obtenerTodosLosJuegos() {
        return JuegoDAO.findAll();
    }

    public Juego buscarJuegoPorId(int id) {
        return JuegoDAO.findById(id);
    }

    public Juego insertarJuego(Juego juego) {
        return JuegoDAO.insert(juego);
    }

    public void actualizarJuego(Juego juego) {
        JuegoDAO.update(juego);
    }

    public void eliminarJuego(int id) {
        JuegoDAO.delete(id);
    }
}
