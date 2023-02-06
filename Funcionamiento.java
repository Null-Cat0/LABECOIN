import java.util.ArrayList;
import java.util.List;

public class Funcionamiento {

    int[][] matriz;
    Robot robot;
    List<Moneda> listaMonedas;

    Funcionamiento(String nombreFichero) {
        Datos_Iniciales d;
        d = new Datos_Iniciales(nombreFichero);
        robot = new Robot();
        robot.setColumnaRobot(d.getColumnaRobot());
        robot.setFilaRobot(d.getFilaRobot());
        listaMonedas = new ArrayList<>();
        listaMonedas = d.getListaMonedas();
        matriz = d.getMatriz();
    }

    void movimientoRobot(String direccion) {

        switch (direccion) {
            case "A":
                if (matriz[robot.getFilaRobot() - 1][robot.getColumnaRobot()] != 9)
                    robot.setFilaRobot(robot.getFilaRobot() - 1);
                break;

            case "B":
                if (matriz[robot.getFilaRobot() + 1][robot.getColumnaRobot()] != 9)
                    robot.setFilaRobot(robot.getFilaRobot() + 1);
                break;

            case "D":
                if (matriz[robot.getFilaRobot()][robot.getColumnaRobot() + 1] != 9)
                    robot.setColumnaRobot(robot.getColumnaRobot() + 1);
                break;

            case "I":
                if (matriz[robot.getFilaRobot()][robot.getColumnaRobot() - 1] != 9)
                    robot.setColumnaRobot(robot.getColumnaRobot() - 1);
                break;

            // Diagonales
            case "AI":
                if (matriz[robot.getFilaRobot() - 1][robot.getColumnaRobot() - 1] != 9)

                    robot.setFilaRobot(robot.getFilaRobot() - 1);
                robot.setColumnaRobot(robot.getColumnaRobot() - 1);
                break;

            case "AD":
                if (matriz[robot.getFilaRobot() - 1][robot.getColumnaRobot() + 1] != 9)
                    robot.setFilaRobot(robot.getFilaRobot() - 1);
                robot.setColumnaRobot(robot.getColumnaRobot() + 1);
                break;

            case "BD":
                if (matriz[robot.getFilaRobot() + 1][robot.getColumnaRobot() + 1] != 9)
                    robot.setFilaRobot(robot.getFilaRobot() + 1);
                robot.setColumnaRobot(robot.getColumnaRobot() + 1);
                break;

            case "BI":
                if (matriz[robot.getFilaRobot() + 1][robot.getColumnaRobot() - 1] != 9)
                    robot.setFilaRobot(robot.getFilaRobot() + 1);
                robot.setColumnaRobot(robot.getColumnaRobot() - 1);
                break;

            default:
                return;
        }
    }
    public static void main (String []args)
    {
        Funcionamiento n = new Funcionamiento("LABECOIN1.txt");
        System.out.println("Posición actual robot "+n.robot.getFilaRobot()+" "+n.robot.getColumnaRobot());
        n.movimientoRobot("A");
        System.out.println("Posición actual robot "+n.robot.getFilaRobot()+" "+n.robot.getColumnaRobot());
    }
}
