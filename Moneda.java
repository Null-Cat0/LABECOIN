public class Moneda {
    int filaMoneda;
    int columnaMoneda;
    int valorMoneda;
    double heuristica;
    Moneda() {
        filaMoneda = 0;
        columnaMoneda = 0;
        valorMoneda = 0;
    }
    Moneda(int _fila, int _columna, int _valorMoneda, double heuristica) {
        filaMoneda = _fila;
        columnaMoneda =_columna ;
        valorMoneda = _valorMoneda;
        this.heuristica=heuristica;
    }
    public int getFilaMoneda() {
        return this.filaMoneda;
    }

    public int getColumnaMoneda() {
        return this.columnaMoneda;
    }

    public int getValorMoneda() {
        return this.valorMoneda;
    }

    public void setValorMoneda(int _valorMoneda) {
        this.valorMoneda = _valorMoneda;
    }

    public void setFilaMoneda(int _filaMoneda) {
        this.filaMoneda = _filaMoneda;
    }

    public void setColumnaMoneda(int _columnaMoneda) {
        this.columnaMoneda = _columnaMoneda;
    }
    public void setHeuristica(double  h) {
        this.heuristica = h;
    }
    
    public double getHeuristica() {
        return this.heuristica ;
    }

}