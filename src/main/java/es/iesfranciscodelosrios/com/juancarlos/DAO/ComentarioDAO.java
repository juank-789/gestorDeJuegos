package es.iesfranciscodelosrios.com.juancarlos.DAO;

import es.iesfranciscodelosrios.com.juancarlos.connection.ConnectionDB;
import es.iesfranciscodelosrios.com.juancarlos.connection.MySQLConnection;
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
    private static final String SQL_FIND_BY_JUEGO = "SELECT * FROM comentario WHERE juego_id=?";
    private static final String SQL_INSERT = "INSERT INTO comentario (texto, fecha, juego_id) VALUES (?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM comentario WHERE id=?";
    private static final String SQL_UPDATE = "UPDATE comentario SET texto=?, fecha=?, juego_id=? WHERE id=?";

    /**
     * * Método para obtener todos los comentarios de la base de datos.
     * @return Lista de comentarios.
     */
    public static List<Comentario> findAll() {
        List<Comentario> comentarios = new ArrayList<>();
        Connection con = MySQLConnection.getConnection();
        try (
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL_ALL)) {

            while (rs.next()) {
                Comentario c = new Comentario();
                c.setId(rs.getInt("id"));
                c.setTexto(rs.getString("texto"));
                c.setFecha(rs.getDate("fecha").toLocalDate());

                int idJuego = rs.getInt("juego_id");
                Juego j = JuegoDAO.findById(idJuego); // ← Esto soluciona el problema
                c.setJuego(j);
                comentarios.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return comentarios;
    }

    /**
     * Método para obtener un comentario por su ID.
     * @param id
     * @return Comentario
     */
    public static Comentario findById(int id) {
        Comentario c = null;
        Connection con = MySQLConnection.build().getConnection();
        try (
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

    /**
     * Método para obtener un comentario por su ID de forma "eager" (carga ansiosa).
     * @param id
     * @return Comentario
     */
    public static Comentario findByIdEager(int id) {
        Comentario c = null;
        Connection con = MySQLConnection.build().getConnection();
        try (
                PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = new Comentario();
                c.setId(rs.getInt("id"));
                c.setTexto(rs.getString("texto"));
                c.setFecha(rs.getDate("fecha").toLocalDate());

                int idJuego = rs.getInt("juego_id");
                Juego j = JuegoDAO.findById(idJuego); // ← Esto soluciona el problema
                c.setJuego(j);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return c;
    }


    /**
     * Método para obtener todos los comentarios de un juego específico.
     * @param juego
     * @return Lista de comentarios
     */
    public static List<Comentario> findByJuegoEager(Juego juego) {
        List<Comentario> comentarios = new ArrayList<>();
        Connection con = MySQLConnection.build().getConnection();
        try (
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


    /**
     * Método para insertar un nuevo comentario en la base de datos.
     * @param c
     * @return Comentario
     */
    public static Comentario insert(Comentario c) {
        Connection con = MySQLConnection.build().getConnection();
        try (
                PreparedStatement ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, c.getTexto());
            ps.setDate(2, Date.valueOf(c.getFecha()));
            ps.setInt(3, c.getJuego().getId());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        c.setId(rs.getInt(1));  // Establecer el ID generado
                    }
                }
                return c;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar el comentario: " + e.getMessage(), e);
        }
    }


    /**
     * Método para actualizar un comentario en la base de datos.
     * @param c
     * @return boolean
     */
    public static boolean update(Comentario c) {
        Connection con = MySQLConnection.build().getConnection();
        try (
                PreparedStatement ps = con.prepareStatement(SQL_UPDATE)) {

            ps.setString(1, c.getTexto());
            ps.setDate(2, Date.valueOf(c.getFecha()));
            ps.setInt(3, c.getJuego().getId());
            ps.setInt(4, c.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el comentario: " + e.getMessage(), e);
        }
    }

    /**
     * Método para eliminar un comentario de la base de datos.
     * @param id
     * @return boolean
     */
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
