import java.util.ArrayList;
import java.util.List;

public class Tablero {
    Robot r;
    List<Moneda> listaMonedas;
    int[][] matriz;
    int cartera;
    String lastMov;
    int N;

    Tablero(List<Moneda> _listaMonedas, int filaRobot, int columnaRobot, int[][] _matriz, int _cartera, int _N) {
        r = new Robot();
        r.setColumnaRobot(filaRobot);
        r.setFilaRobot(columnaRobot);
        listaMonedas = new ArrayList<>();
        listaMonedas = _listaMonedas;
        matriz = _matriz;
        cartera = _cartera;
        this.N = _N;
    }

    Tablero(int _N) {
        r = new Robot();
        listaMonedas = new ArrayList<>();
        matriz = new int[_N][_N];
        cartera = 0;
        this.N = _N;
    }

    public boolean posicionLibre(int i, int j) {
        return matriz[i][j] != 9;
    }

    public List<String> obtenerPosiblesMov() {
        List<String> posiblesMov = new ArrayList<>();

        int fila = this.r.getFilaRobot();
        int columna = this.r.getColumnaRobot();

        if (posicionLibre(fila - 1, columna) /* && !this.lastMov.equals("B") */)
            posiblesMov.add("A");

        if (posicionLibre(fila + 1, columna) /* && !this.lastMov.equals("A") */)
            posiblesMov.add("B");

        if (posicionLibre(fila, columna - 1) /* && !this.lastMov.equals("D") */)
            posiblesMov.add("I");

        if (posicionLibre(fila, columna + 1)/* && !this.lastMov.equals("I") */)
            posiblesMov.add("D");

        if (posicionLibre(fila - 1, columna - 1) /* && !this.lastMov.equals("BD") */)
            posiblesMov.add("AI");

        if (posicionLibre(fila - 1, columna + 1)/* && !this.lastMov.equals("BI") */)
            posiblesMov.add("AD");

        if (posicionLibre(fila + 1, columna + 1)/* && !this.lastMov.equals("AD") */)
            posiblesMov.add("BD");

        if (posicionLibre(fila + 1, columna - 1)/* && !this.lastMov.equals("AI") */)
            posiblesMov.add("BI");

        return posiblesMov;
    }

    void movimientoRobot(String direccion) {

        switch (direccion) {
            case "A":
                if (matriz[r.getFilaRobot() - 1][r.getColumnaRobot()] != 9)
                    r.setFilaRobot(r.getFilaRobot() - 1);
                break;

            case "B":
                if (matriz[r.getFilaRobot() + 1][r.getColumnaRobot()] != 9)
                    r.setFilaRobot(r.getFilaRobot() + 1);
                break;

            case "D":
                if (matriz[r.getFilaRobot()][r.getColumnaRobot() + 1] != 9)
                    r.setColumnaRobot(r.getColumnaRobot() + 1);
                break;

            case "I":
                if (matriz[r.getFilaRobot()][r.getColumnaRobot() - 1] != 9)
                    r.setColumnaRobot(r.getColumnaRobot() - 1);
                break;

            // Diagonales
            case "AI":
                if (matriz[r.getFilaRobot() - 1][r.getColumnaRobot() - 1] != 9)

                    r.setFilaRobot(r.getFilaRobot() - 1);
                r.setColumnaRobot(r.getColumnaRobot() - 1);
                break;

            case "AD":
                if (matriz[r.getFilaRobot() - 1][r.getColumnaRobot() + 1] != 9)
                    r.setFilaRobot(r.getFilaRobot() - 1);
                r.setColumnaRobot(r.getColumnaRobot() + 1);
                break;

            case "BD":
                if (matriz[r.getFilaRobot() + 1][r.getColumnaRobot() + 1] != 9)
                    r.setFilaRobot(r.getFilaRobot() + 1);
                r.setColumnaRobot(r.getColumnaRobot() + 1);
                break;

            case "BI":
                if (matriz[r.getFilaRobot() + 1][r.getColumnaRobot() - 1] != 9)
                    r.setFilaRobot(r.getFilaRobot() + 1);
                r.setColumnaRobot(r.getColumnaRobot() - 1);
                break;

            default:
                return;
        }
    }

    private double calcularDistancia(Robot r, Moneda m) {
        int filaRobot = r.getFilaRobot();
        int columnaRobot = r.getColumnaRobot();
        int filaMoneda = m.getFilaMoneda();
        int columnaMoneda = m.getColumnaMoneda();
        return (Math.sqrt(Math.pow(columnaMoneda - columnaRobot, 2) + Math.pow(filaMoneda - filaRobot, 2))) / 100;
    }

    public double calcularHeuristica(Robot r, Moneda m) {
        return (calcularDistancia(r, m) * 40) + (m.valorMoneda / 100) * 60;
    }

    public int[][] getMatriz() {
        return this.matriz;
    }

    public int getCartera() {
        return this.cartera;
    }

    public Tablero copiarTablero() {
        Tablero tab = new Tablero(N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tab.matriz[i][j] = this.matriz[i][j];
            }
        }
        tab.r = this.r;
        tab.cartera = this.cartera;
        tab.listaMonedas = new ArrayList<>(this.listaMonedas);
        tab.lastMov = this.lastMov;
        return tab;
    }

    public void mostrarMatriz() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(this.matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void mostrarListaMonedas() {

        for (int j = 0; j < N; j++) {
            System.out.print("Fila moneda: " + listaMonedas.get(j).filaMoneda + " Columna moneda: "
                    + listaMonedas.get(j).getColumnaMoneda() + " Valor moneda: "
                    + listaMonedas.get(j).getValorMoneda());
            System.out.println();
        }

    }

    // public static void main(String[] args) {
    // Datos_Iniciales d = new Datos_Iniciales("LABECOIN1.txt");
    // Tablero n = d.getTablero();
    // System.out.println("Posición actual robot " + n.r.getFilaRobot() + " " +
    // n.r.getColumnaRobot());
    // n.movimientoRobot("A");
    // System.out.println("Posición actual robot " + n.r.getFilaRobot() + " " +
    // n.r.getColumnaRobot());
    // n.mostrarListaMonedas();
    // }
}
