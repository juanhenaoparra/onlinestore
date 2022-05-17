import java.sql.ResultSet;
import java.sql.SQLException;

public class Articulo {
    private int id;
    private String codigo;
    private String nombre;
    private double precio;
    private String idProveedor;
    private int cantidadRestante;
    private int tiempoEnvio;

    public Articulo() {}

    public Articulo(String codigo, String nombre, double precio, String idProveedor, int cantidadRestante, int tiempoEnvio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.idProveedor = idProveedor;
        this.cantidadRestante = cantidadRestante;
        this.tiempoEnvio = tiempoEnvio;
    }

    public Articulo(ResultSet rs) {
        try {
            this.id = rs.getInt("ID");
            this.codigo = rs.getString("CODIGO");
            this.nombre = rs.getString("NOMBRE");
            this.precio = rs.getDouble("PRECIO");
            this.idProveedor = rs.getString("ID_PROVEEDOR");
            this.cantidadRestante = rs.getInt("CANTIDAD_RESTANTE");
            this.tiempoEnvio = rs.getInt("TIEMPO_ENVIO");
        } catch (SQLException e) {
            System.out.println("Error getting the required data: " + e.getMessage());
        }
    }

    public String GetCodigo() {
        return this.codigo;
    }

    public String GetNombre() {
        return this.nombre;
    }

    public double GetPrecio() {
        return this.precio;
    }

    @Override
    public String toString() {
        return String.format("ID: %d\nCODIGO: %s\nNOMBRE: %s\nPRECIO: $%.2f\nID_PROVEEDOR: %s\nCANTIDAD_RESTANTE: %d\nTIEMPO_ENVIO: %d días", this.id, this.codigo, this.nombre, this.precio, this.idProveedor, this.cantidadRestante, this.tiempoEnvio);
    }
}
