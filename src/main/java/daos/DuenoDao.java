package daos;

import beans.Dueno;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DuenoDao extends DaoBase {

    public List<Dueno> listar() throws SQLException {
        List<Dueno> lista = new ArrayList<>();
        String sql = "SELECT iddueno, nombre, telefono FROM dueno";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Dueno d = new Dueno();
                d.setIddueno(rs.getInt("iddueno"));
                d.setNombre(rs.getString("nombre"));
                d.setTelefono(rs.getString("telefono"));
                lista.add(d);
            }
        }
        return lista;
    }

    @Override
    public void crear(Object entidad) throws SQLException {}

    @Override
    public void borrar(int id) throws SQLException {}
}