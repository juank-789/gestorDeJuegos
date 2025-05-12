package es.iesfranciscodelosrios.com.juancarlos.controller;


import es.iesfranciscodelosrios.com.juancarlos.model.Desarrolladora;
import es.iesfranciscodelosrios.com.juancarlos.DAO.DesarrolladoraDAO;

import java.util.List;


public class DesarrolladoraController {

    public static List<Desarrolladora> getAllDesarrolladoras() {
        return DesarrolladoraDAO.findAll();
    }

    public static Desarrolladora getDesarrolladoraById(int id) {
        return DesarrolladoraDAO.findById(id);
    }

    public static Desarrolladora addDesarrolladora(Desarrolladora desarrolladora) {
        return DesarrolladoraDAO.insert(desarrolladora);
    }

    public static boolean updateDesarrolladora(Desarrolladora desarrolladora) {
        return DesarrolladoraDAO.update(desarrolladora);
    }

    public static boolean deleteDesarrolladora(int id) {
        return DesarrolladoraDAO.delete(id);
    }
}
