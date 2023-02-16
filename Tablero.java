import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Tablero {
	Casilla r;
	List<Casilla> listaMonedas;
	Casilla mObjetivo = null;
	Casilla[][] matriz;
	int cartera;
	String lastMov;
	int N;

	Tablero(List<Casilla> _listaMonedas, int filaRobot, int columnaRobot, Casilla[][] _matriz, int _cartera, int _N) {
		r = new Casilla();
		r.setColumna(filaRobot);
		r.setFila(columnaRobot);
		listaMonedas = new ArrayList<>();
		listaMonedas = _listaMonedas;
		matriz = _matriz;
		cartera = _cartera;
		this.N = _N;
	}

	Tablero(int _N) {
		r = new Casilla();
		listaMonedas = new ArrayList<>();
		matriz = new Casilla[_N][_N];
		cartera = 0;
		this.N = _N;
	}

	public Double obtenerHeuristicaMov(String direccion) {

		int fila = this.r.getFila();
		int columna = this.r.getColumna();

		double valor;
		switch (direccion) {
			case "A":
				valor = calcularHeuristicaPosiciones(fila - 1, columna, mObjetivo);
				break;

			case "B":
				valor = calcularHeuristicaPosiciones(fila + 1, columna, mObjetivo);
				break;

			case "D":
				valor = calcularHeuristicaPosiciones(fila, columna + 1, mObjetivo);
				break;

			case "I":
				valor = calcularHeuristicaPosiciones(fila, columna - 1, mObjetivo);
				break;

			// Diagonales
			case "AI":
				valor = calcularHeuristicaPosiciones(fila - 1, columna - 1, mObjetivo);
				break;

			case "AD":
				valor = calcularHeuristicaPosiciones(fila - 1, columna + 1, mObjetivo);
				break;

			case "BD":
				valor = calcularHeuristicaPosiciones(fila + 1, columna + 1, mObjetivo);
				break;

			case "BI":
				valor = calcularHeuristicaPosiciones(fila + 1, columna - 1, mObjetivo);
				break;

			default:
				valor = 0;
		}

		// System.out.println("FILA robot : "+fila+" COLUMNA robot :"+ columna +" FILA
		// Moneda : "+mObjetivo.getFila()+" COLUMNA Moneda :"+ mObjetivo.getColumna() );
		if ((fila == mObjetivo.getFila()) && (columna == mObjetivo.getColumna()))
			valor = 0;
		return valor;
	}

	public boolean posicionLibre(int i, int j) {
		return matriz[i][j].getValor() != 9;
	}

	public List<String> obtenerPosiblesMov() {
		List<String> posiblesMov = new ArrayList<>();

		int fila = this.r.getFila();
		int columna = this.r.getColumna();

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

		int fila = this.r.getFila();
		int columna = this.r.getColumna();

		switch (direccion) {
			case "A":
				if (matriz[fila - 1][columna].getValor() != 9) {
					this.r.setFila(fila - 1);
					this.matriz[fila][columna].setValor(0);
					this.matriz[fila - 1][columna].setValor(8);
				}
				break;

			case "B":
				if (matriz[fila + 1][columna].getValor() != 9) {
					this.r.setFila(fila + 1);

					this.matriz[fila][columna].setValor(0);
					this.matriz[fila + 1][columna].setValor(8);
				}
				break;

			case "D":
				if (matriz[fila][columna + 1].getValor() != 9) {
					this.r.setColumna(columna + 1);

					this.matriz[fila][columna].setValor(0);
					this.matriz[fila][columna + 1].setValor(8);
				}
				break;

			case "I":
				if (matriz[fila][columna - 1].getValor() != 9) {
					this.r.setColumna(columna - 1);

					this.matriz[fila][columna].setValor(0);
					this.matriz[fila][columna - 1].setValor(8);
				}
				break;

			// Diagonales
			case "AI":
				if (matriz[fila - 1][columna - 1].getValor() != 9) {

					this.r.setFila(fila - 1);
					this.r.setColumna(columna - 1);

					this.matriz[fila][columna].setValor(0);
					this.matriz[fila - 1][columna - 1].setValor(8);
				}
				break;

			case "AD":
				if (matriz[fila - 1][columna + 1].getValor() != 9) {
					this.r.setFila(fila - 1);
					this.r.setColumna(columna + 1);

					this.matriz[fila][columna].setValor(0);
					this.matriz[fila - 1][columna + 1].setValor(8);
				}
				break;

			case "BD":
				if (matriz[fila + 1][columna + 1].getValor() != 9) {
					this.r.setFila(fila + 1);
					this.r.setColumna(columna + 1);

					this.matriz[fila][columna].setValor(0);
					this.matriz[fila + 1][columna + 1].setValor(8);
				}
				break;

			case "BI":
				if (matriz[fila + 1][columna - 1].getValor() != 9) {
					this.r.setFila(fila + 1);
					this.r.setColumna(columna - 1);

					this.matriz[fila][columna].setValor(0);
					this.matriz[fila + 1][columna - 1].setValor(8);
				}
				break;

			default:
				return;
		}
	}

	private double calcularDistancia(Casilla r, Casilla m) {
		int filaRobot = r.getFila();
		int columnaRobot = r.getColumna();
		int filaMoneda = m.getFila();
		int columnaMoneda = m.getColumna();
		return (Math.sqrt(Math.pow(columnaMoneda - columnaRobot, 2) + Math.pow(filaMoneda - filaRobot, 2)));
	}

	public double calcularHeuristica(Casilla r, Casilla m) {
		return (calcularDistancia(r, m) * 1) + (m.getValor()) * 0.25;
	}

	private double calcularDistanciaPosiciones(int f, int c, Casilla m) {
		int filaMoneda = m.getFila();
		int columnaMoneda = m.getColumna();
		return (Math.sqrt(Math.pow(columnaMoneda - c, 2) + Math.pow(filaMoneda - f, 2)));
	}

	public double calcularHeuristicaPosiciones(int f, int c, Casilla m) {
		return (calcularDistanciaPosiciones(f, c, m) * 1) + (m.getValor()) * 0.25;
	}

	public Casilla[][] getMatriz() {
		return this.matriz;
	}

	public int getCartera() {
		return this.cartera;
	}

	public void reducirCartera(int valor) {
		this.cartera -= valor;
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
		tab.mObjetivo = this.mObjetivo;
		return tab;
	}

	public void mostrarMatriz() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(this.matriz[i][j].getValor() + " ");
			}
			System.out.println();
		}
	}

	public void mostrarListaMonedas() {

		for (int j = 0; j < N; j++) {
			System.out.print("Fila moneda: " + listaMonedas.get(j).getFila() + " Columna moneda: "
					+ listaMonedas.get(j).getColumna() + " Valor moneda: " + listaMonedas.get(j).getValor()
					+ " Heuristica: " + listaMonedas.get(j).getHeuristica());
			System.out.println();
		}

	}

	public void cargarHeuristicaMonedas() {
		double haux;
		for (int h = 0; h < listaMonedas.size(); h++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (this.matriz[i][j].getValor() != 9) {
						Casilla aux = listaMonedas.get(h);
						haux = this.calcularHeuristica(r, aux);
						if (haux < this.matriz[i][j].getHeuristica()) {
							this.matriz[i][j].setHeuristica(haux);
						}
					}
				}
			}
		}
	}

	public void cargarHeuristicaMonedasv2() {
		double mejor = calcularHeuristica(r, listaMonedas.get(0));
		Casilla mejorM = new Casilla(listaMonedas.get(0).getFila(), listaMonedas.get(0).getColumna(),
				listaMonedas.get(0).getValor(), listaMonedas.get(0).heuristica);
		listaMonedas.get(0).setHeuristica(mejor);
		// System.out.print("MEJOR: "+mejor);

		for (int i = 1; i < listaMonedas.size(); i++) {
			double aux = calcularHeuristica(r, listaMonedas.get(i));
			listaMonedas.get(i).setHeuristica(aux);
			// System.out.println(" AUX: "+aux);
			if (aux < mejor) {
				mejorM = listaMonedas.get(i);
				mejor = aux;
			}
		}
		if (mObjetivo == null) {
			mObjetivo = mejorM;
			listaMonedas.remove(mObjetivo);
			this.r.setHeuristica(mObjetivo.getHeuristica());
			Collections.sort(listaMonedas, new ComparatorHeuristica());
		}
		// System.out.print("MEJOR: "+mejor+ mejorM.getFilaMoneda()+"
		// "+mejorM.getColumnaMoneda());
	}

	public void resetearHeuristica() {
		this.r.setHeuristica(0);
		this.mObjetivo = null;
	}
    public void impresionMatrizVisual(Casilla[][] Matriz, int N) {
        String x;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                switch (Matriz[i][j].getValor()) {
                    case 0:
                        x = " . ";
                        break;
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        x = " " + Matriz[i][j].getValor() + " ";
                        break;
                    case 7:
                        x = " S ";
                        break;
                    case 8:
                        x = " @ ";
                        break;
                    case 9:
                        x = " # ";
                        break;
                    default:
                        return;
                }
                System.out.print(x);
            }
            System.out.println();
        }
    }
	public void cargarHeuristicaSalida() {

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tablero other = (Tablero) obj;
		return N == other.N && Objects.equals(listaMonedas, other.listaMonedas)
				&& Objects.equals(lastMov, other.lastMov) && Arrays.deepEquals(matriz, other.matriz)
				&& cartera == other.cartera && Objects.equals(r, other.r);
	}
	// public static void main(String[] args) {
	// Datos_Iniciales d = new Datos_Iniciales("LABECOIN1.txt");
	// Tablero n = d.getTablero();
	// System.out.println("PosiciÃ³n actual robot " + n.r.getFilaRobot() + " " +
	// n.r.getColumnaRobot());
	// n.movimientoRobot("A");
	// System.out.println("PosiciÃ³n actual robot " + n.r.getFilaRobot() + " " +
	// n.r.getColumnaRobot());
	// n.mostrarListaMonedas();
	// }
}
