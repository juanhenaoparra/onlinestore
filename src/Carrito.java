import java.sql.Connection;
import java.util.ArrayList;

public class Carrito {
    private String id;
    private String idCliente;
    private int tiempoMaxEspera;
    private String estado;

    public ArrayList<Articulo> GetArticulosPorTiempo(ArrayList<String> idArticulos) {
    return new ArrayList<>();
    }

    public void SetEstado(String estado) {
        this.estado = estado;
    }
}
