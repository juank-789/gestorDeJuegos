package es.iesfranciscodelosrios.com.juancarlos.DAO;

import es.iesfranciscodelosrios.com.juancarlos.connection.ConnectionDB;
import es.iesfranciscodelosrios.com.juancarlos.model.Comentario;
import es.iesfranciscodelosrios.com.juancarlos.model.Desarrolladora;
import es.iesfranciscodelosrios.com.juancarlos.model.Juego;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JuegoDAO {
    private static final String SQL_ALL = "SELECT * FROM juego";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM juego WHERE id=?";
    private static final String SQL_FIND_BY_DESARROLLADORA = "SELECT * FROM juego WHERE idDesarrolladora=?";
    private static final String SQL_INSERT = "INSERT INTO juego (titulo, genero, idDesarrolladora) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE juego SET titulo=?, genero=?, idDesarrolladora=? WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM juego WHERE id=?";

    public static List<Juego> findAll() {
        List<Juego> juegos = new ArrayList<>();
        try (Connection con = ConnectionDB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_ALL)) {

            while (rs.next()) {
                Juego j = new Juego();
                j.setId(rs.getInt("id"));
                j.setTitulo(rs.getString("titulo"));
                j.setGenero(rs.getString("genero"));
                j.setDesarrolladora(null); // Lazy
                j.setComentarios(new ArrayList<>()); // Lazy
                juegos.add(j);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return juegos;
    }

    public static Juego findById(int id) {
        Juego j = null;
        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                j = new Juego();
                j.setId(rs.getInt("id"));
                j.setTitulo(rs.getString("titulo"));
                j.setGenero(rs.getString("genero"));
                int idDes = rs.getInt("idDesarrolladora");
                j.setDesarrolladora(DesarrolladoraDAO.findById(idDes));
                j.setComentarios(ComentarioDAO.findByJuegoEager(j));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return j;
    }

    public static List<Juego> findByDesarrolladora(Desarrolladora d) {
        List<Juego> juegos = new ArrayList<>();
        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_DESARROLLADORA)) {

            ps.setInt(1, d.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Juego j = new Juego();
                j.setId(rs.getInt("id"));
                j.setTitulo(rs.getString("titulo"));
                j.setGenero(rs.getString("genero"));
                j.setDesarrolladora(d);
                j.setComentarios(new ArrayList<>()); // Lazy
                juegos.add(j);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return juegos;
    }

    public static void insert(Juego juego) {
        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {

            ps.setString(1, juego.getTitulo());
            ps.setString(2, juego.getGenero());
            ps.setInt(3, juego.getDesarrolladora().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update(Juego juego) {
        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_UPDATE)) {

            ps.setString(1, juego.getTitulo());
            ps.setString(2, juego.getGenero());
            ps.setInt(3, juego.getDesarrolladora().getId());
            ps.setInt(4, juego.getId());
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
