package es.iesfranciscodelosrios.com.juancarlos.controller;


import es.iesfranciscodelosrios.com.juancarlos.model.Desarrolladora;
import es.iesfranciscodelosrios.com.juancarlos.DAO.DesarrolladoraDAO;

import java.util.List;

public class DesarrolladoraController {

    public List<Desarrolladora> obtenerTodas() {
        return DesarrolladoraDAO.findAll();
    }

    public Desarrolladora buscarPorId(int id) {
        return DesarrolladoraDAO.findById(id);
    }

    public Desarrolladora insertar(Desarrolladora d) {
        return DesarrolladoraDAO.insert(d);
    }

    public void actualizar(Desarrolladora d) {
        DesarrolladoraDAO.update(d);
    }

    public void eliminar(int id) {
        DesarrolladoraDAO.delete(id);
    }
}
