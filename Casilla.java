
public class Casilla {
	private int fila;
	private int columna;
	private int valor;
	private double heuristica;

	Casilla(int fila, int columna, int valor, double heuristica) {
		this.fila = fila;
		this.columna = columna;
		this.valor = valor;
		this.heuristica = heuristica;
	}

	public Casilla() {
		fila = 0;
		columna = 0;
		valor = 0;
		heuristica = 10;
	}

	public int getFila() {
		return this.fila;
	}

	public int getColumna() {
		return this.columna;
	}

	public int getValor() {
		return this.valor;
	}

	public double getHeuristica() {
		return this.heuristica;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public void setColumna(int _columna) {
		this.columna = _columna;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public void setHeuristica(double heuristica) {
		this.heuristica = heuristica;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Casilla other = (Casilla) obj;
		return fila == other.fila && columna == other.columna 
				&& valor == other.valor && Double.doubleToLongBits(heuristica) == Double.doubleToLongBits(other.heuristica);
	}
}
