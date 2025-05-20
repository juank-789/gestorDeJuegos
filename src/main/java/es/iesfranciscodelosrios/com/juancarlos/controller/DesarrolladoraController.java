package es.iesfranciscodelosrios.com.juancarlos.controller;


import es.iesfranciscodelosrios.com.juancarlos.model.Desarrolladora;
import es.iesfranciscodelosrios.com.juancarlos.DAO.DesarrolladoraDAO;

import java.util.List;


public class DesarrolladoraController {

    /**
     * Método que devuelve una lista de todas las desarrolladoras
     * @return Lista de desarrolladoras
     */
    public static List<Desarrolladora> getAllDesarrolladoras() {
        return DesarrolladoraDAO.findAll();
    }

    /**
     * Método que devuelve una desarrolladora por su id
     * @param id
     * @return Desarrolladora
     */
    public static Desarrolladora getDesarrolladoraById(int id) {
        return DesarrolladoraDAO.findById(id);
    }

    /**
     * Método que devuelve una lista de desarrolladoras por su nombre
     * @param desarrolladora
     * @return Lista de desarrolladoras
     */
    public static Desarrolladora addDesarrolladora(Desarrolladora desarrolladora) {
        return DesarrolladoraDAO.insert(desarrolladora);
    }

    /**
     * Método que devuelve una lista de desarrolladoras por su nombre
     * @param desarrolladora
     * @return Lista de desarrolladoras
     */
    public static boolean updateDesarrolladora(Desarrolladora desarrolladora) {
        return DesarrolladoraDAO.update(desarrolladora);
    }

    /**
     * Método que devuelve una lista de desarrolladoras por su nombre
     * @param id
     * @return Lista de desarrolladoras
     */
    public static boolean deleteDesarrolladora(int id) {
        return DesarrolladoraDAO.delete(id);
    }
}
