package es.iesfranciscodelosrios.com.juancarlos.model;



import java.time.LocalDate;

public class Comentario {
    private int id;
    private String texto;
    private LocalDate fecha;
    private Juego juego;

    /**
     * Constructor por defecto.
     */
    public Comentario() {

    }

    /**
     * Constructor con parámetros.
     * @param id
     */
    public Comentario(int id) {
        this.id = id;
    }

    /**
     * Constructor con parámetros.
     * @param id
     * @param texto
     * @param fecha
     * @param juego
     */
    public Comentario(int id, String texto, LocalDate fecha, Juego juego) {
        this.id = id;
        this.texto = texto;
        this.fecha = fecha;
        this.juego = juego;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }
}

