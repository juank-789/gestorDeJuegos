package es.iesfranciscodelosrios.com.juancarlos.DAO;

import es.iesfranciscodelosrios.com.juancarlos.connection.ConnectionDB;
import es.iesfranciscodelosrios.com.juancarlos.model.Comentario;
import es.iesfranciscodelosrios.com.juancarlos.model.Desarrolladora;
import es.iesfranciscodelosrios.com.juancarlos.model.Juego;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JuegoDAO {
        private static final String SQL_ALL = "SELECT * FROM juego";
        private static final String SQL_FIND_BY_ID = "SELECT * FROM juego WHERE id=?";
        private static final String SQL_FIND_BY_DESARROLLADORA = "SELECT * FROM juego WHERE id_desarrolladora=?";

        public static List<Juego> findByDesarrolladoraLazy(Desarrolladora d) {
            List<Juego> juegos = new ArrayList<>();
            try (Connection con = ConnectionDB.getConnection();
                 PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_DESARROLLADORA)) {
                ps.setInt(1, d.getId());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Juego j = new Juego();
                    j.setId(rs.getInt("id"));
                    j.setTitulo(rs.getString("titulo"));
                    j.setAnioLanzamiento(rs.getInt("anio_lanzamiento"));
                    j.setGenero(rs.getString("genero"));
                    j.setPlataforma(rs.getString("plataforma"));
                    j.setDesarrolladora(null); // Lazy
                    juegos.add(j);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return juegos;
        }

        public static Juego findByIdEager(int id) {
            Juego juego = null;
            try (java.sql.Connection con = ConnectionDB.getConnection();
                 PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    juego = new Juego();
                    juego.setId(rs.getInt("id"));
                    juego.setTitulo(rs.getString("titulo"));
                    juego.setAnioLanzamiento(rs.getInt("anio_lanzamiento"));
                    juego.setGenero(rs.getString("genero"));
                    juego.setPlataforma(rs.getString("plataforma"));
                    juego.setDesarrolladora(DesarrolladoraDAO.findByIdLazy(rs.getInt("id_desarrolladora")));
                    juego.setComentarios(ComentarioDAO.findByJuegoLazy(juego));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return juego;
        }

    public static List<Juego> findAll() {
        List<Juego> juegos = new ArrayList<>();
        try (Connection con = ConnectionDB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM juego")) {

            while (rs.next()) {
                Juego j = new Juego();
                j.setId(rs.getInt("id"));
                j.setNombre(rs.getString("nombre"));
                j.setGenero(rs.getString("genero"));
                int idDesarrolladora = rs.getInt("idDesarrolladora");
                j.setDesarrolladora(DesarrolladoraDAO.findById(idDesarrolladora));
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
             PreparedStatement ps = con.prepareStatement("SELECT * FROM juego WHERE id = ?")) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                j = new Juego();
                j.setId(rs.getInt("id"));
                j.setTitulo(rs.getString("titulo"));
                j.setGenero(rs.getString("genero"));
                int idDesarrolladora = rs.getInt("idDesarrolladora");
                j.setDesarrolladora(DesarrolladoraDAO.findById(idDesarrolladora));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return j;
    }

    public static void insert(Juego juego) {
        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement ps = con.prepareStatement("INSERT INTO juego (titulo, genero, idDesarrolladora) VALUES (?, ?, ?)")) {

            ps.setString(1, juego.getTitulo());
            ps.setString(2, juego.getGenero());
            ps.setInt(3, juego.getDesarrolladora().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update(Comentario comentario) {
        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement ps = con.prepareStatement("UPDATE comentario SET texto = ?, fecha = ?, idJuego = ? WHERE id = ?")) {

            ps.setString(1, comentario.getTexto());
            ps.setDate(2, Date.valueOf(comentario.getFecha()));
            ps.setInt(3, comentario.getJuego().getId());
            ps.setInt(4, comentario.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void delete(int id) {
        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM juego WHERE id = ?")) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
