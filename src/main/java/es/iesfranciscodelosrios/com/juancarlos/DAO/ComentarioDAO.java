package es.iesfranciscodelosrios.com.juancarlos.DAO;

import es.iesfranciscodelosrios.com.juancarlos.connection.ConnectionDB;
import es.iesfranciscodelosrios.com.juancarlos.model.Comentario;
import es.iesfranciscodelosrios.com.juancarlos.model.Juego;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComentarioDAO {
    private static final String SQL_FIND_BY_JUEGO = "SELECT * FROM comentario WHERE id_juego=?";

    public static List<Comentario> findByJuegoLazy(Juego juego) {
        List<Comentario> comentarios = new ArrayList<>();
        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_JUEGO)) {
            ps.setInt(1, juego.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Comentario c = new Comentario();
                c.setId(rs.getInt("id"));
                c.setTexto(rs.getString("texto"));
                c.setFecha(rs.getDate("fecha").toLocalDate());
                c.setJuego(null); // Lazy
                comentarios.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return comentarios;
    }
}

