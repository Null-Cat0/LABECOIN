
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class LABECOIN {

    public static void main(String[] args) throws IOException {

        Datos_Iniciales d;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Por favor seleccione el fichero:");
        System.out.println(" 1-LABECOIN1.txt");
        System.out.println(" 2-LABECOIN2.txt");
        System.out.println(" 3-LABECOIN3.txt");
        System.out.println(" 4-LABECOIN4.txt");
        System.out.println(" 5-LABECOIN5.txt");
        System.out.println(" 6-LABECOIN6.txt");
        System.out.println(" 7-LABECOIN7.txt");
        System.out.println(" 8-LABECOIN8.txt");
        System.out.println(" 9-LABECOIN9.txt");
        System.out.println(" 10-LABECOIN10.txt");
        
        int opcion = Integer.valueOf(br.readLine()); // Se lee el nombre con readLine() que retorna un String
		
	
        String nombreFichero = " ";
        switch (opcion) {
            case 1:
                nombreFichero = "LABECOIN1.txt";
                break;
            case 2:
                nombreFichero = "LABECOIN2.txt";
                break;
            case 3:
                nombreFichero = "LABECOIN3.txt";
                break;
            case 4:
                nombreFichero = "LABECOIN4.txt";
                break;
            case 5:
                nombreFichero = "LABECOIN5.txt";
                break;
            case 6:
                nombreFichero = "LABECOIN6.txt";
                break;
            case 7:
                nombreFichero = "LABECOIN7.txt";
                break;
            case 8:
                nombreFichero = "LABECOIN8.txt";
                break;
            case 9:
                nombreFichero = "LABECOIN9.txt";
                break;
            case 10:
                nombreFichero = "LABECOIN10.txt";
                break;
            default:
                return;
        }
        
		d = new Datos_Iniciales(nombreFichero);

        System.out.println("Por favor seleccione el algoritmo: ");
        System.out.println(" 1-Primero Mejor");
        System.out.println(" 2-Escalada Simple");
        System.out.println(" 3-Maxima Pendiente");
        int algoritmoElegido = Integer.valueOf(br.readLine()); // Se lee el nombre con readLine() que retorna un String 
        
        long initialTime;
        initialTime = new Date().getTime();
        switch (algoritmoElegido) {
            case 1:
            PrimeroMejor m = new PrimeroMejor(d.getTablero());
            m.primeroMejor();
            m.mostrarResultados();
            System.out.println ("Tiempo total empleado: " + (new Date().getTime() - initialTime) + "ms");
                break;
            case 2:
                EscaladaSimple es = new EscaladaSimple(d.getTablero());
                es.escaladaSimple();
                es.mostrarResultados();
                System.out.println ("Tiempo total empleado: " + (new Date().getTime() - initialTime) + "ms");
                break;
            case 3:
            MaximaPendiente max = new MaximaPendiente(d.getTablero());
            max.maximaPendiente();
            max.mostrarResultados();
            System.out.println ("Tiempo total empleado: " + (new Date().getTime() - initialTime) + "ms");
                break;
            default:
                return;
        }
    }
}