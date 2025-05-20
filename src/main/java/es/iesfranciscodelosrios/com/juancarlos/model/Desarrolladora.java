package es.iesfranciscodelosrios.com.juancarlos.model;

import java.util.List;

public class Desarrolladora {
    private int id;
    private String nombre;
    private String pais;
    private List<Juego> juegos;

    // Constructor por defecto
    public Desarrolladora() {

    }

    // Constructor con parámetros
    public Desarrolladora(int id) {
        this.id = id;
    }

    // Constructor con parámetros
    public Desarrolladora(int id, String nombre, String pais) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
    }

    // Constructor full equip
    public Desarrolladora(int id, String nombre, String pais, List<Juego> juegos) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.juegos = juegos;
    }

    // Getters y Setters

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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public List<Juego> getJuegos() {
        return juegos;
    }

    public void setJuegos(List<Juego> juegos) {
        this.juegos = juegos;
    }

    @Override
    public String toString() {
        return  nombre;
    }
}
