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
                        x = "   ";
                        break;
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        x =" " + Matriz[i][j] + " ";
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
        System.out.println("Por favor seleccione el fichero: 1-LABECOIN1.txt");
        int opcion = Integer.valueOf(br.readLine()); // Se lee el nombre con readLine() que retorna un String con el

        String nombreFichero = " ";
        switch (opcion) {
            case 1:
                nombreFichero = "LABECOIN1.txt";
                break;
            default:
                return;
        }

        d = new Datos_Iniciales(nombreFichero);
        impresionMatrizVisual(d.getMatriz(),d.N);

    }
}
