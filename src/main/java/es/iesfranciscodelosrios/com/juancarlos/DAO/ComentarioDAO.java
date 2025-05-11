package es.iesfranciscodelosrios.com.juancarlos.DAO;

import es.iesfranciscodelosrios.com.juancarlos.connection.ConnectionDB;
import es.iesfranciscodelosrios.com.juancarlos.model.Comentario;
import es.iesfranciscodelosrios.com.juancarlos.model.Juego;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ComentarioDAO {
    private static final String SQL_ALL = "SELECT * FROM comentario";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM comentario WHERE id=?";
    private static final String SQL_FIND_BY_JUEGO = "SELECT * FROM comentario WHERE idJuego=?";
    private static final String SQL_INSERT = "INSERT INTO comentario (texto, fecha, idJuego) VALUES (?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM comentario WHERE id=?";
    private static final String SQL_UPDATE = "UPDATE comentario SET texto=?, fecha=?, idJuego=? WHERE id=?";

    public static List<Comentario> findAll() {
        List<Comentario> comentarios = new ArrayList<>();
        try (Connection con = ConnectionDB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_ALL)) {

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

    public static Comentario findById(int id) {
        Comentario c = null;
        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = new Comentario();
                c.setId(rs.getInt("id"));
                c.setTexto(rs.getString("texto"));
                c.setFecha(rs.getDate("fecha").toLocalDate());
                c.setJuego(null); // Lazy
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return c;
    }

    public static List<Comentario> findByJuegoEager(Juego juego) {
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
                c.setJuego(juego); // Eager
                comentarios.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return comentarios;
    }

    public static void insert(Comentario c) {
        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {

            ps.setString(1, c.getTexto());
            ps.setDate(2, Date.valueOf(c.getFecha()));
            ps.setInt(3, c.getJuego().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update(Comentario c) {
        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_UPDATE)) {

            ps.setString(1, c.getTexto());
            ps.setDate(2, Date.valueOf(c.getFecha()));
            ps.setInt(3, c.getJuego().getId());
            ps.setInt(4, c.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void delete(int id) {
        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_DELETE)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
