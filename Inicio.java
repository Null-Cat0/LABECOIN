public class Inicio {
    
    Datos_Iniciales d;
    Robot robot;
    
    Inicio(String nombreFichero)
    {
        d = new Datos_Iniciales (nombreFichero);
        robot = new Robot();
        robot.setColumnaRobot(d.getColumnaRobot());
        robot.setFilaRobot(d.getFilaRobot());
    }
}
