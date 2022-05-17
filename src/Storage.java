import java.sql.*;
import java.util.ArrayList;

public class Storage {
    private Connection connect() throws SQLException {
        Connection conn;

        String url = "jdbc:sqlite:onlinestore.sqlite3";
        conn = DriverManager.getConnection(url);

        return conn;
    }

    public ArrayList<Articulo> GetArticulosPorTiempoEnvio(String code, int maxTiempoEnvio, String ciudad) throws SQLException {
        String query = "SELECT a.* FROM Articulos as a, Proveedores as p WHERE a.CODIGO = ? AND a.TIEMPO_ENVIO <= ? AND a.ID_PROVEEDOR = p.id AND p.ciudad = ? ORDER BY a.TIEMPO_ENVIO ASC;";

        Connection conn = this.connect();

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, code);
        stmt.setInt(2, maxTiempoEnvio);
        stmt.setString(3, ciudad);
        ResultSet rs = stmt.executeQuery();

        ArrayList<Articulo> articulos = new ArrayList<>();

        while (rs.next()) {
            Articulo articulo = new Articulo(rs);
            articulos.add(articulo);
        }

        rs.close();
        stmt.close();

        return articulos;
    }

    public Cliente FindCliente(String idCliente) throws SQLException {
        String query = "SELECT * FROM Clientes WHERE ID = ? LIMIT 1";

        Connection conn = this.connect();

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, idCliente);
        ResultSet rs = stmt.executeQuery();

        Cliente cliente = null;

        while (rs.next()) {
            cliente = new Cliente(rs);
        }

        rs.close();
        stmt.close();

        return cliente;
    }
}
