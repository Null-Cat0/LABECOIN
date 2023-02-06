import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Principal {

    public static void impresionMatrizVisual(int[][] Matriz, int N) {
        String x;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                switch (Matriz[i][j]) {
                    case 0:
                        x = " . ";
                        break;
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        x = " " + Matriz[i][j] + " ";
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

    public static void main(String[] args) throws IOException {

        Datos_Iniciales d;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // Ya tenemos el "lector"
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
        int opcion = Integer.valueOf(br.readLine()); // Se lee el nombre con readLine() que retorna un String con el

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
        impresionMatrizVisual(d.getMatriz(), d.N);

    }
}
