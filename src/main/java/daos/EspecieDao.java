package daos;

import beans.Especie;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EspecieDao extends DaoBase {

    public List<Especie> listar() throws SQLException {
        List<Especie> lista = new ArrayList<>();
        String sql = "SELECT idespecie, nombre FROM especie";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Especie e = new Especie();
                e.setIdespecie(rs.getInt("idespecie"));
                e.setNombre(rs.getString("nombre"));
                lista.add(e);
            }
        }
        return lista;
    }

    @Override
    public void crear(Object entidad) throws SQLException {
    }

    @Override
    public void borrar(int id) throws SQLException {
    }
}