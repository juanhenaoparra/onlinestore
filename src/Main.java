import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Carrito carrito = new Carrito("CLT123", 4);
        ArrayList<String> idArticulos = new ArrayList<>();

        idArticulos.add("ART0001");
        idArticulos.add("ART0002");

        ArrayList<Articulo> articulos = carrito.SelectArticulos(idArticulos);
        System.out.println(articulos.toString());
    }

    public static String GetRandomHexString(int numchars){
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while(sb.length() < numchars){
            sb.append(Integer.toHexString(r.nextInt()));
        }

        return sb.toString().substring(0, numchars).toUpperCase();
    }

    public static String GenerateID(String prefix){
        return prefix + GetRandomHexString(10).toUpperCase();
    }
}
