import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Tablero {
	private Casilla r;
	public Casilla mObjetivo;
	Casilla salida;
	public Casilla[][] matriz;
	private List<Casilla> listaMonedas;
	private int cartera;
	private int N;
	private int numeroMonedas;
	private int hucha;
	private String lastMov;
	private Tablero ultTablero;

	Tablero(List<Casilla> _listaMonedas, int filaRobot, int columnaRobot, Casilla[][] _matriz, int _cartera, int _N,
			Tablero ultTablero) {
		this.r = new Casilla();
		this.r.setColumna(filaRobot);
		this.r.setFila(columnaRobot);
		this.listaMonedas = new ArrayList<>();
		this.listaMonedas = _listaMonedas;
		this.matriz = _matriz;
		this.cartera = _cartera;
		this.N = _N;
		this.lastMov = " ";
		this.ultTablero = ultTablero;
		this.mObjetivo = null;
		this.salida = null;
		this.hucha = 0;
	}

	Tablero(int _N) {
		this.r = new Casilla();
		this.listaMonedas = new ArrayList<>();
		this.matriz = new Casilla[_N][_N];
		this.cartera = 0;
		this.N = _N;
		this.lastMov = " ";
		this.mObjetivo = null;
		this.ultTablero = null;
		this.salida = null;
		this.hucha = 0;
	}

	/*
	 * 
	 * SET Y GET
	 * 
	 */

	public Casilla getRobot() {
		return this.r;
	}

	public Casilla getSalida() {
		return this.salida;
	}

	public Casilla getmObjetivo() {
		return this.mObjetivo;
	}

	public Casilla[][] getMatriz() {
		return this.matriz;
	}

	public List<Casilla> getListaMonedas() {
		return this.listaMonedas;
	}

	public int getCartera() {
		return this.cartera;
	}

	public int getN() {
		return this.N;
	}

	public int getHucha() {
		return this.hucha;
	}

	public String getLastMov() {
		return this.lastMov;
	}

	public Tablero getLastTablero() {
		return this.ultTablero;
	}

	public void setRobot(Casilla r) {
		this.r = r;
	}

	public void setmObjetivo(Casilla mObjetivo) {
		this.mObjetivo = mObjetivo;
	}

	public void setMatriz(Casilla[][] matriz) {
		this.matriz = matriz;
	}

	public void setListaMonedas(List<Casilla> l) {
		this.listaMonedas = l;
	}

	public void setCartera(int x) {
		this.cartera = x;
	}

	public void setN(int N) {
		this.N = N;
	}

	public void setLastMov(String x) {
		this.lastMov = x;
	}

	public void setLastTablero(Tablero x) {
		this.ultTablero = x;
	}

	public int getNumeroMonedas() {
		return numeroMonedas;
	}

	public void setNumeroMonedas(int numeroMonedas) {
		this.numeroMonedas = numeroMonedas;
	}

	public double getHeuristicaTablero() {

		 return calcularHeuristica(this.r, this.mObjetivo);
	}

	public boolean esMoneda(int fila, int columna) {
		return this.matriz[fila][columna].getValor() >= 1 && this.matriz[fila][columna].getValor() <= 6;
	}

	/*
	 * 
	 * 
	 * BUSQUEDA DE ROBOT, MONEDAS....
	 * 
	 * 
	 */
	public void encontrarMonedas() {
		for (int i = 0; i < this.N; i++) {
			for (int j = 0; j < this.N; j++) {
				if ((this.matriz[i][j].getValor() <= 6) && (this.matriz[i][j].getValor() >= 1)) {
					// Creamos Moneda
					Casilla aux = new Casilla(i, j, this.matriz[i][j].getValor(), 100);
					// Insertamos Moneda
					listaMonedas.add(aux);
					this.setNumeroMonedas(this.getNumeroMonedas() + 1);
				}

			}
		}
	}

	public void encontrarRobot() {
		for (int i = 0; i < this.N; i++) {
			for (int j = 0; j < this.N; j++) {
				if (this.matriz[i][j].getValor() == 8)
					this.r = new Casilla(i, j, 8, 0);
			}
		}

	}

	public void encontrarSalida() {
		for (int i = 0; i < this.N; i++) {
			for (int j = 0; j < this.N; j++) {
				if (this.matriz[i][j].getValor() == 7)
					this.salida = new Casilla(i, j, 7, 100);
			}
		}

	}

	public void encontrarMObjetivo() {
		if (listaMonedas != null) {
			double mejorHueristica = this.calcularHeuristica(this.listaMonedas.get(0),
					this.matriz[this.r.getFila()][this.r.getColumna()]);
					this.mObjetivo = this.listaMonedas.get(0);
			for (int k = 1; k < listaMonedas.size(); k++) {
				double distanciaPonderada = this.calcularHeuristica(this.listaMonedas.get(k),this.matriz[this.r.getFila()][this.r.getColumna()]);
				if (distanciaPonderada < mejorHueristica) {
					this.mObjetivo = this.listaMonedas.get(k);
					mejorHueristica = distanciaPonderada;
				}
			}
			this.r.setHeuristica(mejorHueristica);
		}
	}
	/*
	 * 
	 * METODOS DE LA CLASE
	 * 
	 */

	// Devuelve si la posicion i , j de la matriz es un muro
	public boolean posicionLibre(int i, int j) {

		return this.matriz[i][j].getValor() != 9;
	}

	public Double getHeuristicaMovSinMatriz(String direccion) {

		int fila = this.r.getFila();
		int columna = this.r.getColumna();

		double valor;
		switch (direccion) {
			case "A":
				valor = calcularHeuristica(this.matriz[fila - 1][columna],this.mObjetivo
						);
				break;

			case "B":
				valor = calcularHeuristica(
						this.matriz[fila + 1][columna],this.mObjetivo);
				break;

			case "D":
				valor = calcularHeuristica(
						this.matriz[fila][columna + 1],this.mObjetivo);
				break;

			case "I":
				valor = calcularHeuristica(
						this.matriz[fila][columna - 1],this.mObjetivo);
				break;

			// Diagonales
			case "AI":
				valor = calcularHeuristica(
						this.matriz[fila - 1][columna - 1],this.mObjetivo);
				break;

			case "AD":
				valor = calcularHeuristica(
						this.matriz[fila - 1][columna + 1],this.mObjetivo);
				break;

			case "BD":
				valor = calcularHeuristica(
						this.matriz[fila + 1][columna + 1],this.mObjetivo);
				break;

			case "BI":
				valor = calcularHeuristica(
						this.matriz[fila + 1][columna - 1],this.mObjetivo);
				break;

			default:
				valor = this.r.getHeuristica();
		}

		return valor;
	}

	// Devuelve la lista de movimientos que puede realizar el robot.
	public List<String> getPosiblesMov() {
		List<String> posiblesMov = new ArrayList<>();

		int fila = this.r.getFila();
		int columna = this.r.getColumna();
		if (fila < 9 && columna < 9 && fila > 0 && columna > 0) {
			if (posicionLibre(fila - 1, columna))
				posiblesMov.add("A");

			if (posicionLibre(fila + 1, columna))
				posiblesMov.add("B");

			if (posicionLibre(fila, columna - 1))
				posiblesMov.add("I");

			if (posicionLibre(fila, columna + 1))
				posiblesMov.add("D");

			if (posicionLibre(fila - 1, columna - 1))
				posiblesMov.add("AI");

			if (posicionLibre(fila - 1, columna + 1))
				posiblesMov.add("AD");

			if (posicionLibre(fila + 1, columna + 1))
				posiblesMov.add("BD");

			if (posicionLibre(fila + 1, columna - 1))
				posiblesMov.add("BI");
		}
		return posiblesMov;
	}

	// Mueve el robot en la direcci�n indicada en "direccion" (Si es posible)
	void movimientoRobot(String direccion) {

		int fila = this.r.getFila();
		int columna = this.r.getColumna();

		switch (direccion) {
			case "A":
				if (posicionLibre(fila - 1, columna)) {

					if (esMoneda(fila - 1, columna)) {
						hucha = hucha + this.matriz[fila - 1][columna].getValor();
						this.numeroMonedas--;
					}
					this.r.setFila(fila - 1);
					this.matriz[fila][columna].setValor(0);
					this.matriz[fila - 1][columna].setValor(8);
					this.lastMov = "A";
				}
				break;

			case "B":

				if (posicionLibre(fila + 1, columna)) {
					if (esMoneda(fila + 1, columna)) {
						hucha = hucha + this.matriz[fila + 1][columna].getValor();
						this.numeroMonedas--;

					}
					this.r.setFila(fila + 1);

					this.matriz[fila][columna].setValor(0);
					this.matriz[fila + 1][columna].setValor(8);
					this.lastMov = "B";
				}
				break;

			case "D":
				if (posicionLibre(fila, columna + 1)) {
					if (esMoneda(fila, columna + 1)) {
						hucha = hucha + this.matriz[fila][columna + 1].getValor();
						this.numeroMonedas--;

					}
					this.r.setColumna(columna + 1);

					this.matriz[fila][columna].setValor(0);
					this.matriz[fila][columna + 1].setValor(8);
					this.lastMov = "D";
				}
				break;

			case "I":
				if (posicionLibre(fila, columna - 1)) {
					if (esMoneda(fila, columna - 1)) {
						hucha = hucha + this.matriz[fila][columna - 1].getValor();
						this.numeroMonedas--;

					}
					this.r.setColumna(columna - 1);

					this.matriz[fila][columna].setValor(0);
					this.matriz[fila][columna - 1].setValor(8);
					this.lastMov = "I";
				}
				break;

			// Diagonales
			case "AI":
				if (posicionLibre(fila - 1, columna - 1)) {
					if (esMoneda(fila - 1, columna - 1)) {
						hucha = hucha + this.matriz[fila - 1][columna - 1].getValor();
						this.numeroMonedas--;

					}
					this.r.setFila(fila - 1);
					this.r.setColumna(columna - 1);

					this.matriz[fila][columna].setValor(0);
					this.matriz[fila - 1][columna - 1].setValor(8);
					this.lastMov = "AI";
				}
				break;

			case "AD":
				if (posicionLibre(fila - 1, columna + 1)) {
					if (esMoneda(fila - 1, columna + 1)) {
						hucha = hucha + this.matriz[fila - 1][columna + 1].getValor();
						this.numeroMonedas--;
					}
					this.r.setFila(fila - 1);
					this.r.setColumna(columna + 1);

					this.matriz[fila][columna].setValor(0);
					this.matriz[fila - 1][columna + 1].setValor(8);
					this.lastMov = "AD";
				}
				break;

			case "BD":
				if (posicionLibre(fila + 1, columna + 1)) {
					if (esMoneda(fila + 1, columna + 1)) {
						hucha = hucha + this.matriz[fila + 1][columna + 1].getValor();
						this.numeroMonedas--;
					}
					this.r.setFila(fila + 1);
					this.r.setColumna(columna + 1);

					this.matriz[fila][columna].setValor(0);
					this.matriz[fila + 1][columna + 1].setValor(8);
					this.lastMov = "BD";
				}
				break;

			case "BI":
				if (posicionLibre(fila + 1, columna - 1)) {
					if (esMoneda(fila + 1, columna - 1)) {
						hucha = hucha + this.matriz[fila + 1][columna - 1].getValor();
						this.numeroMonedas--;
					}
					this.r.setFila(fila + 1);
					this.r.setColumna(columna - 1);

					this.matriz[fila][columna].setValor(0);
					this.matriz[fila + 1][columna - 1].setValor(8);
					this.lastMov = "BI";
				}
				break;
		}
	}



	// Calcula la distancia entre dos casillas
	private double calcularDistancia(Casilla r, Casilla m) {
		int filaRobot = r.getFila();
		int columnaRobot = r.getColumna();
		int filaMoneda = m.getFila();
		int columnaMoneda = m.getColumna();
		return Math.round(
				Math.sqrt(Math.pow(columnaMoneda - columnaRobot, 2) + Math.pow(filaMoneda - filaRobot, 2)) * 100.0)
				/ 100.0;
	}

	// Obtiene la heuristica entre dos casillas
	public double calcularHeuristica(Casilla r, Casilla m) {
		return (calcularDistancia(r, m) * 1) + (m.getValor()) * 0.25;
	}


	// Copia un tablero en otro.
	public Tablero copiarTablero() {
		Tablero tab = new Tablero(N);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				tab.matriz[i][j] = new Casilla(this.matriz[i][j].getFila(), this.matriz[i][j].getColumna(),
						this.matriz[i][j].getValor(), this.matriz[i][j].getHeuristica());
			}
		}
		tab.hucha = this.hucha;
		tab.salida = new Casilla(this.salida.getFila(), this.salida.getColumna(), this.salida.getValor(),
				this.salida.getHeuristica());
		tab.r = new Casilla(this.r.getFila(), this.r.getColumna(),
				this.r.getValor(), this.r.getHeuristica());
		tab.cartera = this.cartera;
		tab.listaMonedas = new ArrayList<>(this.listaMonedas);
		tab.lastMov = this.lastMov;
		if (this.mObjetivo != null)
			tab.mObjetivo = new Casilla(this.mObjetivo.getFila(), this.mObjetivo.getColumna(),
					this.mObjetivo.getValor(), this.mObjetivo.getHeuristica());
		tab.numeroMonedas = this.numeroMonedas;
		tab.setLastTablero(this);
		return tab;
	}

	// Imprime de una forma mas visual la matriz
	public void impresionMatrizVisual() {
		String x;
		for (int i = 0; i < this.N; i++) {
			for (int j = 0; j < this.N; j++) {
				switch (this.matriz[i][j].getValor()) {
					case 0:
						x = " . ";
						break;
					case 1:
					case 2:
					case 3:
					case 4:
					case 5:
					case 6:
						x = " " + this.matriz[i][j].getValor() + " ";
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

	public boolean fin() {
		return this.r.getFila() == this.salida.getFila() && this.r.getColumna() == this.salida.getColumna()
				&& this.hucha >= this.cartera;
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
		return N == other.N && Objects.deepEquals(listaMonedas, other.listaMonedas)
				&& Objects.equals(lastMov, other.lastMov) && Arrays.deepEquals(matriz, other.matriz)
				&& cartera == other.cartera && Objects.equals(r, other.r) && Objects.equals(mObjetivo, other.mObjetivo)
				&& Objects.equals(salida, other.salida) && hucha == other.hucha;
	}
}
