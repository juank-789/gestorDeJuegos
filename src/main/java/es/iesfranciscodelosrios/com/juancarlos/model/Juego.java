package es.iesfranciscodelosrios.com.juancarlos.model;

import java.util.List;

public class Juego {
    private int id;
    private String titulo;
    private String genero;
    private Desarrolladora desarrolladora;
    private List<Comentario> comentarios;

    // Constructor por defecto
    public Juego() {

    }

    // Constructor con id
    public Juego (int id){
        this.id = id;
    }

    // Constructor con id, titulo y genero
    public Juego(int id, String titulo, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
    }

    // Constructor con id, titulo, genero y desarrolladora
    public Juego(int id, String titulo, String genero, Desarrolladora desarrolladora) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.desarrolladora = desarrolladora;
    }

    // Constructor full equip
    public Juego(int id, String titulo, String genero, Desarrolladora desarrolladora, List<Comentario> comentarios) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.desarrolladora = desarrolladora;
        this.comentarios = comentarios;
    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String nombre) {
        this.titulo = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Desarrolladora getDesarrolladora() {
        return desarrolladora;
    }

    public void setDesarrolladora(Desarrolladora desarrolladora) {
        this.desarrolladora = desarrolladora;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        return titulo;
    }
}
