public class Casilla {
    int fila;
    int columna;
    int valor;
    double heuristica;

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
		heuristica = -51;
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

    public void setValor( int valor) {
         this.valor = valor;
    }

    public void setHeuristica(double heuristica) {
         this.heuristica = heuristica;
    }
   
}
