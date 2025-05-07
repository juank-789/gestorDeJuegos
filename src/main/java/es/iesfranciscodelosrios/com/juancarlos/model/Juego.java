package es.iesfranciscodelosrios.com.juancarlos.model;

import java.util.List;

public class Juego {
    private int id;
    private String titulo;
    private int anioLanzamiento;
    private String genero;
    private String plataforma;

    private Desarrolladora desarrolladora; // relación N:1
    private List<Comentario> comentarios;  // relación 1:N

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public int getAnioLanzamiento() { return anioLanzamiento; }
    public void setAnioLanzamiento(int anioLanzamiento) { this.anioLanzamiento = anioLanzamiento; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public String getPlataforma() { return plataforma; }
    public void setPlataforma(String plataforma) { this.plataforma = plataforma; }

    public Desarrolladora getDesarrolladora() { return desarrolladora; }
    public void setDesarrolladora(Desarrolladora desarrolladora) { this.desarrolladora = desarrolladora; }

    public List<Comentario> getComentarios() { return comentarios; }
    public void setComentarios(List<Comentario> comentarios) { this.comentarios = comentarios; }
}
