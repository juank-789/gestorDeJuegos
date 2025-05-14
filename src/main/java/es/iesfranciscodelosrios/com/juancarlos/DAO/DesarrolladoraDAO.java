package es.iesfranciscodelosrios.com.juancarlos.DAO;

import es.iesfranciscodelosrios.com.juancarlos.connection.ConnectionDB;
import es.iesfranciscodelosrios.com.juancarlos.connection.MySQLConnection;
import es.iesfranciscodelosrios.com.juancarlos.model.Desarrolladora;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DesarrolladoraDAO {
    private static final String SQL_ALL = "SELECT * FROM desarrolladora";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM desarrolladora WHERE id=?";
    private static final String SQL_INSERT = "INSERT INTO desarrolladora (nombre, pais) VALUES (?, ?)";
    private static final String SQL_UPDATE = "UPDATE desarrolladora SET nombre=?, pais=? WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM desarrolladora WHERE id=?";



    public static List<Desarrolladora> findAll() {
        List<Desarrolladora> desarrolladoras = new ArrayList<>();
        Connection con = MySQLConnection.build().getConnection();
        try (
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL_ALL)) {

            while (rs.next()) {
                Desarrolladora d = new Desarrolladora();
                d.setId(rs.getInt("id"));
                d.setNombre(rs.getString("nombre"));
                d.setPais(rs.getString("pais"));
                d.setJuegos(new ArrayList<>()); // Lazy
                desarrolladoras.add(d);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return desarrolladoras;
    }

    public static Desarrolladora findById(int id) {
        Desarrolladora d = null;
        Connection con = MySQLConnection.build().getConnection();
        try (
                PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                d = new Desarrolladora();
                d.setId(rs.getInt("id"));
                d.setNombre(rs.getString("nombre"));
                d.setPais(rs.getString("pais"));
                d.setJuegos(JuegoDAO.findByDesarrolladora(d));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return d;
    }

    /*public static void insert(Desarrolladora d) {
        try (Connection con = MySQLConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {

            ps.setString(1, d.getNombre());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
*/

    public static Desarrolladora insert(Desarrolladora d) {
        Connection con = MySQLConnection.build().getConnection();
        try (
                PreparedStatement ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, d.getNombre());
            ps.setString(2, d.getPais());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        d.setId(rs.getInt(1));  // Guardar el ID generado
                    }
                }
                return d;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar la desarrolladora: " + e.getMessage(), e);
        }
    }


    /*public static void update(Desarrolladora d) {
        try (Connection con = MySQLConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_UPDATE)) {

            ps.setString(1, d.getNombre());
            ps.setInt(2, d.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

    public static boolean update(Desarrolladora d) {
        Connection con = MySQLConnection.build().getConnection();
        try (
                PreparedStatement ps = con.prepareStatement(SQL_UPDATE)) {

            ps.setString(1, d.getNombre());
            ps.setString(2, d.getPais());
            ps.setInt(3, d.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la desarrolladora: " + e.getMessage(), e);
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