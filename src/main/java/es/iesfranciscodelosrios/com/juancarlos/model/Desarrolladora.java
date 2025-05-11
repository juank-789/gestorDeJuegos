package es.iesfranciscodelosrios.com.juancarlos.model;

import java.util.List;

public class Desarrolladora {
    private int id;
    private String nombre;
    private List<Juego> juegos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Juego> getJuegos() {
        return juegos;
    }

    public void setJuegos(List<Juego> juegos) {
        this.juegos = juegos;
    }
}
