public class Moneda {
    int filaMoneda;
    int columnaMoneda;
    int valorMoneda;

    Moneda() {
        filaMoneda = 0;
        columnaMoneda = 0;
        valorMoneda = 0;
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

}