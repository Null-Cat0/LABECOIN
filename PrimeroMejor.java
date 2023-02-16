import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrimeroMejor {
    private List<Tablero> abiertos;
    private List<Tablero> cerrados;
    private List<String> movimientos;
    private Tablero actual;
    private int contadorNodos;
    static int maxNodos = 100000;

    // CUANDO SE COGE UNA MONEDA HAY QUE PONER LA POSICION LA MONEDA A 0

    public PrimeroMejor(Tablero tab) {
        actual = tab;
        abiertos = new ArrayList<>();
        cerrados = new ArrayList<>();
        movimientos = new ArrayList<>();
        contadorNodos = 0;
        actual.cargarHeuristicaMonedasv2();

        // System.out.println("Posicion robot : " + actual.r.getFila() + " " +
        // actual.r.getColumna());
        // System.out.println("MONEDA OBJETIVO: " + actual.mObjetivo.getFila() + " " +
        // actual.mObjetivo.getColumna() + " "
        // + actual.mObjetivo.getHeuristica());
        // for (int i = 0; i < actual.listaMonedas.size(); i++) {
        // System.out.println("VALOR: " + actual.listaMonedas.get(i).getValor() + "
        // FILA: "
        // + actual.listaMonedas.get(i).getFila() + " COLUMNA: " +
        // actual.listaMonedas.get(i).getColumna()
        // + " HEURISTICA: " + actual.listaMonedas.get(i).heuristica);
        // }
        // System.out.println("MOVEMOS ROBOT HACIA LA IZQ");
        // actual.movimientoRobot("I");
        // System.out.println("Posicion robot : " + actual.r.getFila() + " " +
        // actual.r.getColumna());
        // actual.cargarHeuristicaMonedasv2();
        // System.out.println("MONEDA OBJETIVO: " + actual.mObjetivo.getFila() + " " +
        // actual.mObjetivo.getColumna() + " "
        // + actual.mObjetivo.getHeuristica());
        // for (int i = 0; i < actual.listaMonedas.size(); i++) {
        // System.out.println("VALOR: " + actual.listaMonedas.get(i).getValor() + "
        // FILA: "
        // + actual.listaMonedas.get(i).getFila() + " COLUMNA: " +
        // actual.listaMonedas.get(i).getColumna()
        // + " HEURISTICA: " + actual.listaMonedas.get(i).heuristica);
        // }

    }

    public List<Tablero> generarSucesores(Tablero tab) {
        List<String> posiblesMov = tab.obtenerPosiblesMov();
        List<Tablero> hijos = new ArrayList<>();
        boolean noMoves; // Usado para no realizar el movimiento dos veces en el caso de cargar la
                         // heuristica
        Tablero hijo;
        for (int i = 0; i < posiblesMov.size(); i++) {
            hijo = tab.copiarTablero();
            // System.out.println("FILA: " + hijo.r.getFila() + " COLUMNA " +
            // hijo.r.getColumna());
            contadorNodos++;
            String mov = posiblesMov.get(i);

            System.out.println();
            noMoves = false;
            

            if (hijo.obtenerHeuristicaMov(mov) < hijo.r.getHeuristica() && hijo.sepuedeMov(mov)) {
                
                System.out.println("--- "+hijo.r.getHeuristica());
                System.out.println("---> "+hijo.obtenerHeuristicaMov(mov));
                
                hijo.r.setHeuristica(hijo.obtenerHeuristicaMov(mov));
                noMoves = true;
                hijo.movimientoRobot(mov);
                
                System.out.println(mov);
                hijo.impresionMatrizVisual(hijo.getMatriz(), 10);
                hijo.mostrarListaMonedas();
                System.out.println();
                
                if (hijo.obtenerHeuristicaMov(mov) == 0) {
                    hijo.resetearHeuristica();
                    hijo.cargarHeuristicaMonedasv2();

                    if (hijo.getCartera() == 0) {
                        hijo.cargarHeuristicaSalida();
                    } else {
                        // hijo.cargarHeuristicaMonedasv2();
                    }
                    // System.out.println("CARTERA ACTUAL: "+hijo.getCartera()+" Fila siguiente
                    // moneda: "+hijo.mObjetivo.getFila()+" Columna siguiente moneda:
                    // "+hijo.mObjetivo.getColumna());
                    // break;
                }

            }

            hijos.add(hijo);
        }
        return hijos;
    }

    public void primeroMejor() {

        abiertos.add(actual);
        int cont = 0;
        while (!abiertos.isEmpty() && contadorNodos < maxNodos) { // Mientras que no hayamos visto todos los nodos no
                                                                  // explorados y mientras no hayamos explorado mas de x
                                                                     // nodos.
            abiertos.remove(0);
            cerrados.add(actual);

            // Generamos los sucesores del tablero actual
            List<Tablero> hijos = generarSucesores(actual);

            // Tratamos los repetidos
            tratarRepetidos(hijos);

            // Insertamos los hijos en la lista de nodos abiertos
            for (int i = 0; i < hijos.size(); i++) {
                abiertos.add(hijos.get(i));
            }

            // Ordenamos la lista de abiertos
            Collections.sort(abiertos, new ComparatorCartera());

            if (!abiertos.isEmpty()) {
                actual = abiertos.get(0);
            }
        }
    }

    public void tratarRepetidos(List<Tablero> hijos) {

        for (int i = 0; i < abiertos.size(); i++) {
            for (int j = 0; j < hijos.size(); j++) {
                if (hijos.get(j).equals(abiertos.get(i))) {
                    hijos.remove(j);
                }
            }
        }

        for (int i = 0; i < cerrados.size(); i++) {
            for (int j = 0; j < hijos.size(); j++) {
                if (hijos.get(j).equals(cerrados.get(i))) {
                    hijos.remove(j);
                }
            }
        }
    }

    public static void main(String[] args) {
        Datos_Iniciales d = new Datos_Iniciales("LABECOIN1.txt");
        PrimeroMejor m = new PrimeroMejor(d.getTablero());
        m.primeroMejor();
        // m.generarSucesores(d.getTablero());

    }
}
