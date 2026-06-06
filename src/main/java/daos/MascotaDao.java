package daos;

import beans.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MascotaDao extends DaoBase {

    // listado de  todas las mascotitas c:
    public List<Mascota> listar() throws SQLException {
        return listarPorEspecie(0);
    }

    public List<Mascota> listarPorEspecie(int especieId) throws SQLException {
        List<Mascota> lista = new ArrayList<>();
        String sql = "SELECT m.idmascota, m.nombre, m.edad, m.peso, " +
                "e.idespecie, e.nombre AS esp_nombre, " +
                "v.idveterinario, v.nombre AS vet_nombre, v.especialidad, " +
                "d.iddueno, d.nombre AS due_nombre, d.telefono " +
                "FROM mascota m " +
                "INNER JOIN especie e ON m.especie_id = e.idespecie " +
                "INNER JOIN veterinario v ON m.veterinario_id = v.idveterinario " +
                "INNER JOIN dueno d ON m.dueno_id = d.iddueno";
        if (especieId > 0) {
            sql += " WHERE m.especie_id = ?";
        }

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            if (especieId > 0) {
                ps.setInt(1, especieId);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Mascota m = new Mascota();
                    m.setIdmascota(rs.getInt("idmascota"));
                    m.setNombre(rs.getString("nombre"));
                    m.setEdad(rs.getInt("edad"));
                    m.setPeso(rs.getDouble("peso"));

                    Especie e = new Especie(rs.getInt("idespecie"), rs.getString("esp_nombre"));
                    Veterinario v = new Veterinario(rs.getInt("idveterinario"), rs.getString("vet_nombre"), rs.getString("especialidad"));
                    Dueno d = new Dueno(rs.getInt("iddueno"), rs.getString("due_nombre"), rs.getString("telefono"));

                    m.setEspecie(e);
                    m.setVeterinario(v);
                    m.setDueno(d);
                    lista.add(m);
                }
            }
        }
        return lista;
    }

    @Override
    public void crear(Object entidad) throws SQLException {
        Mascota m = (Mascota) entidad;
        String sql = "INSERT INTO mascota(nombre, edad, peso, especie_id, veterinario_id, dueno_id) VALUES (?,?,?,?,?,?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getEdad());
            ps.setDouble(3, m.getPeso());
            ps.setInt(4, m.getEspecie().getIdespecie());
            ps.setInt(5, m.getVeterinario().getIdveterinario());
            ps.setInt(6, m.getDueno().getIddueno());
            ps.executeUpdate();
        }
    }

    @Override
    public void borrar(int id) throws SQLException {
        String sql = "DELETE FROM mascota WHERE idmascota = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}