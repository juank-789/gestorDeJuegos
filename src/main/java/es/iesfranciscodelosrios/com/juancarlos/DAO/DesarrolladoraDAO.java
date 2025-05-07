package es.iesfranciscodelosrios.com.juancarlos.DAO;

import es.iesfranciscodelosrios.com.juancarlos.connection.ConnectionDB;
import es.iesfranciscodelosrios.com.juancarlos.model.Desarrolladora;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DesarrolladoraDAO {

        private static final String SQL_ALL = "SELECT * FROM desarrolladora";
        private static final String SQL_FIND_BY_ID = "SELECT * FROM desarrolladora WHERE id=?";

        public static List<Desarrolladora> findAllLazy() {
            List<Desarrolladora> lista = new ArrayList<>();
            try (Connection con = ConnectionDB.getConnection();
                 Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery(SQL_ALL)) {

                while (rs.next()) {
                    Desarrolladora d = new Desarrolladora();
                    d.setId(rs.getInt("id"));
                    d.setNombre(rs.getString("nombre"));
                    d.setPais(rs.getString("pais"));
                    d.setJuegos(new ArrayList<>()); // Lazy
                    lista.add(d);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return lista;
        }

        public static Desarrolladora findByIdLazy(int id) {
            Desarrolladora d = null;
            try (Connection con = ConnectionDB.getConnection();
                 PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    d = new Desarrolladora();
                    d.setId(rs.getInt("id"));
                    d.setNombre(rs.getString("nombre"));
                    d.setPais(rs.getString("pais"));
                    d.setJuegos(new ArrayList<>());
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return d;
        }

        public static Desarrolladora findByIdEager(int id) {
            Desarrolladora d = findByIdLazy(id);
            if (d != null) {
                d.setJuegos(JuegoDAO.findByDesarrolladoraLazy(d));
            }
            return d;
        }


}
