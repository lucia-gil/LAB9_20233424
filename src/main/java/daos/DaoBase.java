package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DaoBase {

    private static final String URL      = "jdbc:mysql://localhost:3306/Veterinaria";
    private static final String USUARIO  = "root";
    private static final String PASSWORD = "12345678";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("No se encontro el driver MySQL pipipi", e);
        }
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }

    // metodos abstractos que toda subclase debe implementar
    public abstract void crear(Object entidad) throws SQLException;
    public abstract void borrar(int id) throws SQLException;
}