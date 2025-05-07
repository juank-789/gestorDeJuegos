package es.iesfranciscodelosrios.com.juancarlos.DAO;

import es.iesfranciscodelosrios.com.juancarlos.connection.ConnectionDB;
import es.iesfranciscodelosrios.com.juancarlos.model.Desarrolladora;
import es.iesfranciscodelosrios.com.juancarlos.model.Juego;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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


}
