package daos;

import beans.Transaccion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransaccionDao {
    private String url = "jdbc:mysql://localhost:3306/mydb";
    private String user = "root";
    private String pass = "12345678";

    public List<Transaccion> listarTransaccionesPorUsuario(int idUsuario) {
        List<Transaccion> lista = new ArrayList<>();
        String sql = "SELECT * FROM transacciones WHERE usuarios_idusuarios = ?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, user, pass);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idUsuario);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        Transaccion t = new Transaccion();
                        t.setIdtransacciones(rs.getInt("idtransacciones"));
                        t.setMonto(rs.getDouble("monto"));
                        t.setDescripcion(rs.getString("descripcion"));
                        t.setTitulo(rs.getString("titulo"));
                        t.setFecha(rs.getDate("fecha"));
                        t.setUsuariosIdusuarios(rs.getInt("usuarios_idusuarios"));
                        t.setTipo(rs.getString("tipo"));
                        lista.add(t);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void crearTransaccion(Transaccion t) {
        // Truco para evitar el error de falta de AUTO_INCREMENT en tu SQL
        String sqlMaxId = "SELECT MAX(idtransacciones) FROM transacciones";
        String sqlInsert = "INSERT INTO transacciones (idtransacciones, monto, descripcion, titulo, fecha, usuarios_idusuarios, tipo) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, user, pass)) {
                int nextId = 1;
                try (PreparedStatement pstMax = conn.prepareStatement(sqlMaxId);
                     ResultSet rs = pstMax.executeQuery()) {
                    if (rs.next()) {
                        nextId = rs.getInt(1) + 1;
                    }
                }

                try (PreparedStatement pstInsert = conn.prepareStatement(sqlInsert)) {
                    pstInsert.setInt(1, nextId);
                    pstInsert.setDouble(2, t.getMonto());
                    pstInsert.setString(3, t.getDescripcion());
                    pstInsert.setString(4, t.getTitulo());
                    pstInsert.setDate(5, t.getFecha());
                    pstInsert.setInt(6, t.getUsuariosIdusuarios());
                    pstInsert.setString(7, t.getTipo());
                    pstInsert.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void borrarTransaccion(int idTransaccion, int idUsuarioLogueado) {
        String sql = "DELETE FROM transacciones WHERE idtransacciones = ? AND usuarios_idusuarios = ?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, user, pass);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idTransaccion);
                pstmt.setInt(2, idUsuarioLogueado);
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}