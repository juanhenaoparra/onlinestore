import java.sql.*;
import java.util.ArrayList;

public class StorageArticulos {
    private Connection connect() throws SQLException {
        Connection conn;

        String url = "jdbc:sqlite:/home/parjua/Documents/u/taller13-onlinestore/onlinestore.sqlite3";
        conn = DriverManager.getConnection(url);

        return conn;
    }

    public ArrayList<Articulo> GetArticulosPorTiempoEnvio(String code, int maxTiempoEnvio) throws SQLException {
        String query = "SELECT * FROM Articulos WHERE CODIGO = ? AND TIEMPO_ENVIO <= ?";

        Connection conn = this.connect();

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, code);
        stmt.setInt(2, maxTiempoEnvio);
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
}
