public class Robot {
    private int filaRobot;
    private int columnaRobot;

    Robot() {
        filaRobot = 0;
        columnaRobot = 0;
    }

    public int getFilaRobot() {
        return this.filaRobot;
    }

    public int getColumnaRobot() {
        return this.columnaRobot;
    }

    public void setFilaRobot(int fila) {
        this.filaRobot = fila;
    }

    public void setColumnaRobot(int columna) {
        this.columnaRobot = columna;
    }
}
