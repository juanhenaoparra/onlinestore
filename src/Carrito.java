import java.sql.SQLException;
import java.util.ArrayList;

public class Carrito {
    private String id;
    private String idCliente;
    private int tiempoMaxEspera;
    private String estado;
    private Cliente cliente;

    public static final String STATE_CREATED = "created";
    public static final String STATE_PENDING = "pending";
    public static final String STATE_PURCHASED = "purchased";

    public Carrito() {
        setID();
    }

    public Carrito(String idCliente, int tiempoMaxEspera) {
        this.idCliente = idCliente;
        this.tiempoMaxEspera = tiempoMaxEspera;

        setID();
        this.SetEstado(STATE_CREATED);
        this.setCliente();
    }

    public Carrito(String id, String idCliente, int tiempoMaxEspera, String estado) {
        this.id = id;
        this.idCliente = idCliente;
        this.tiempoMaxEspera = tiempoMaxEspera;

        this.SetEstado(estado);
        this.setCliente();
    }

    private void setID() {
        this.id = Main.GenerateID("CRT");
    }

    private void setCliente() {
        Storage storage = new Storage();

        try {
            this.cliente = storage.FindCliente(idCliente);

        } catch (SQLException e) {
            System.out.println("Error getting clietn data: " + e.getMessage());
        }
    }

    public String GetIDCliente() {
        return this.idCliente;
    }

    public String GetID() {
        return this.id;
    }

    public void SetEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList<ArrayList<Articulo>> GetArticulosPorTiempo(ArrayList<String> idArticulos) {
        Storage storage = new Storage();
        ArrayList<ArrayList<Articulo>> articulosMatrix = new ArrayList<>();

        idArticulos.forEach((codeArticulo) -> {
            try {
                articulosMatrix.add(storage.GetArticulosPorTiempoEnvio(codeArticulo, this.tiempoMaxEspera, this.cliente.GetCiudad()));
            } catch (SQLException e) {
                articulosMatrix.add(new ArrayList<>());
                System.out.println("ERROR: " + e.getMessage());
            }
        });

        return articulosMatrix;
    }

    public ArrayList<Articulo> SelectArticulos(ArrayList<String> idArticulos) {
        ArrayList<ArrayList<Articulo>> matrix = this.GetArticulosPorTiempo(idArticulos);
        ArrayList<Articulo> articulos = new ArrayList<>(matrix.size());

        matrix.forEach((groupArticles) -> {
            Articulo articulo = null;

            if (groupArticles.size() > 0) {
                articulo = groupArticles.get(0);
            }

            articulos.add(articulo);
        });

        return articulos;
    }
}
