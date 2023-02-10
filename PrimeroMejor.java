import java.util.ArrayList;
import java.util.List;

public class PrimeroMejor {
    private List<Tablero> abiertos;
    private List<Tablero> cerrados;
    private List<String> movimientos;
    private Tablero actual;
    private int contadorNodos;
    static int maxNodos = 100000; 

    public PrimeroMejor(Tablero tab) {
        actual = tab;
        abiertos = new ArrayList<>();
        cerrados = new ArrayList<>();
        movimientos = new ArrayList<>();
        contadorNodos = 0;
    }

    public List<Tablero> generarSucesores(Tablero tab) {
        List<String> posiblesMov = tab.obtenerPosiblesMov();
        List<Tablero> hijos = new ArrayList<>();
        for (String posiblesm : posiblesMov) {
            System.out.println(posiblesm);
        }
        return null;
    }

    public static void main(String[] args) {
        Datos_Iniciales d = new Datos_Iniciales("LABECOIN1.txt");
        PrimeroMejor m = new PrimeroMejor(d.getTablero());
        m.generarSucesores(d.getTablero());
    }
}
