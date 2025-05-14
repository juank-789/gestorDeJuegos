package es.iesfranciscodelosrios.com.juancarlos.DAO;

import es.iesfranciscodelosrios.com.juancarlos.connection.ConnectionDB;
import es.iesfranciscodelosrios.com.juancarlos.connection.MySQLConnection;
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
    private static final String SQL_FIND_BY_DESARROLLADORA = "SELECT * FROM juego WHERE desarrolladora_id=?";
    private static final String SQL_INSERT = "INSERT INTO juego (titulo, genero, desarrolladora_id) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE juego SET titulo=?, genero=?, desarrolladora_id=? WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM juego WHERE id=?";

    /*public static List<Juego> findAll() {
        List<Juego> juegos = new ArrayList<>();
        Connection con = MySQLConnection.build().getConnection();
        try (
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
    }*/

    public static List<Juego> findAll() {
        List<Juego> juegos = new ArrayList<>();
        Connection con = MySQLConnection.build().getConnection();

        try (
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL_ALL)) {

            while (rs.next()) {
                Juego j = new Juego();
                j.setId(rs.getInt("id"));
                j.setTitulo(rs.getString("titulo"));
                j.setGenero(rs.getString("genero"));

                // EAGER: cargar la desarrolladora
                int idDesarrolladora = rs.getInt("desarrolladora_id");
                Desarrolladora d = DesarrolladoraDAO.findById(idDesarrolladora);
                j.setDesarrolladora(d);

                // LAZY
                j.setComentarios(new ArrayList<>());

                juegos.add(j);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return juegos;
    }


    public static Juego findById(int id) {
        Juego j = null;
        Connection con = MySQLConnection.build().getConnection();
        try (
                PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                j = new Juego();
                j.setId(rs.getInt("id"));
                j.setTitulo(rs.getString("titulo"));
                j.setGenero(rs.getString("genero"));
                int idDes = rs.getInt("desarrolladora_id");
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
        Connection con = MySQLConnection.build().getConnection();
        try (
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

    /*public static void insert(Juego juego) {
        try (Connection con = MySQLConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {

            ps.setString(1, juego.getTitulo());
            ps.setString(2, juego.getGenero());
            ps.setInt(3, juego.getDesarrolladora().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

    public static Juego insert(Juego juego) {
        Connection con = MySQLConnection.build().getConnection();
        try (
                PreparedStatement ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, juego.getTitulo());
            ps.setString(2, juego.getGenero());
            ps.setInt(3, juego.getDesarrolladora().getId());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        juego.setId(rs.getInt(1));  // Establecer el ID generado
                    }
                }
                return juego;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar el juego: " + e.getMessage(), e);
        }
    }

    /*public static void update(Juego juego) {
        try (Connection con = MySQLConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_UPDATE)) {

            ps.setString(1, juego.getTitulo());
            ps.setString(2, juego.getGenero());
            ps.setInt(3, juego.getDesarrolladora().getId());
            ps.setInt(4, juego.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

    public static boolean update(Juego juego) {
        Connection con = MySQLConnection.build().getConnection();
        try (
                PreparedStatement ps = con.prepareStatement(SQL_UPDATE)) {

            ps.setString(1, juego.getTitulo());
            ps.setString(2, juego.getGenero());
            ps.setInt(3, juego.getDesarrolladora().getId());
            ps.setInt(4, juego.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el juego: " + e.getMessage(), e);
        }
    }


    public static boolean delete(int id) {
        Connection con = MySQLConnection.build().getConnection();
        try (
                PreparedStatement ps = con.prepareStatement(SQL_DELETE)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
