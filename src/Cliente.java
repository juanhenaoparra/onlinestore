import java.sql.ResultSet;
import java.sql.SQLException;

public class Cliente {
    private String id;
    private String nombre;
    private String ciudad;

    public Cliente(ResultSet rs) {
        try {
            this.id = rs.getString("ID");
            this.nombre = rs.getString("NOMBRE");
            this.ciudad = rs.getString("CIUDAD");
        } catch (SQLException e) {
            System.out.println("Error getting the required data: " + e.getMessage());
        }
    }

    public String GetCiudad() {
        return this.ciudad;
    }
}
