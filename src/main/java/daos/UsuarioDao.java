package daos;

import beans.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {
    private String url = "jdbc:mysql://localhost:3306/mydb";
    private String user = "root";
    private String pass = "12345678";

    public Usuario validarLogin(String correo, String passwordCifrada) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND pass = ?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, user, pass);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, correo);
                pstmt.setString(2, passwordCifrada);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        usuario = new Usuario();
                        usuario.setIdusuarios(rs.getInt("idusuarios"));
                        usuario.setNombre(rs.getString("nombre"));
                        usuario.setApellido(rs.getString("apellido"));
                        usuario.setDni(rs.getString("dni"));
                        usuario.setCorreo(rs.getString("correo"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, user, pass);
                 PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Usuario u = new Usuario();
                    u.setIdusuarios(rs.getInt("idusuarios"));
                    u.setNombre(rs.getString("nombre"));
                    u.setApellido(rs.getString("apellido"));
                    u.setDni(rs.getString("dni"));
                    u.setCorreo(rs.getString("correo"));
                    lista.add(u);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean existeDniOCorreo(String dni, String correo) {
        boolean existe = false;
        String sql = "SELECT idusuarios FROM usuarios WHERE dni = ? OR correo = ?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, user, pass);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, dni);
                pstmt.setString(2, correo);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) existe = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return existe;
    }

    public void crearUsuario(Usuario u) {
        String sql = "INSERT INTO usuarios (nombre, apellido, pass, dni, correo) VALUES (?, ?, ?, ?, ?)";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, user, pass);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, u.getNombre());
                pstmt.setString(2, u.getApellido());
                pstmt.setString(3, u.getPass());
                pstmt.setString(4, u.getDni());
                pstmt.setString(5, u.getCorreo());
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
