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

    public static void addDesarrolladora(Desarrolladora desarrolladora) {
        DesarrolladoraDAO.insert(desarrolladora);
    }

    public static void updateDesarrolladora(Desarrolladora desarrolladora) {
        DesarrolladoraDAO.update(desarrolladora);
    }

    public static void deleteDesarrolladora(int id) {
        DesarrolladoraDAO.delete(id);
    }
}
