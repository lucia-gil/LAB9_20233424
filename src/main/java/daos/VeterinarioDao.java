package daos;

import beans.Veterinario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioDao extends DaoBase {

    public List<Veterinario> listar() throws SQLException {
        List<Veterinario> lista = new ArrayList<>();
        String sql = "SELECT idveterinario, nombre, especialidad FROM veterinario";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Veterinario v = new Veterinario();
                v.setIdveterinario(rs.getInt("idveterinario"));
                v.setNombre(rs.getString("nombre"));
                v.setEspecialidad(rs.getString("especialidad"));
                lista.add(v);
            }
        }
        return lista;
    }

    @Override
    public void crear(Object entidad) throws SQLException {}

    @Override
    public void borrar(int id) throws SQLException {}
}