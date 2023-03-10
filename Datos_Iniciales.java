import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Datos_Iniciales {
	private String nombre; // Nombre del fichero
	private int N_Aux;
	 int cartera_Aux;
	 Tablero tablero;

	public Datos_Iniciales(String _nombre) {

		nombre = "Ejemplos/" + _nombre;
		N_Aux = ordenMatriz();
		tablero = new Tablero(N_Aux);
		leerFichero();
		tablero.setCartera(cartera_Aux);

	}
	public Tablero getTablero() 
	{
		return this.tablero;
	}
	private int ordenMatriz() {
		int aux = 0;
		BufferedReader br = null;
		try {

			br = new BufferedReader(new FileReader(nombre));

			String texto = br.readLine(); // Primera línea, no necesaria.
			texto = br.readLine(); // Primera fila de la matriz.
			String[] parts = texto.split(","); // Division de la fila
			aux = parts.length;

		} catch (FileNotFoundException e1) {
			System.out.println("Fichero no encontrado");
		} catch (IOException e) {
			System.out.println("Error en la lectura del fichero");
		} finally {
			try {
				// Cerrar el fichero si se ha podido abrir
				if (br != null) {
					br.close();
				}
			} catch (Exception ex) {
				System.out.println("Error al cerrar el fichero");
				ex.printStackTrace();
			}
		}
		return aux;

	}
	
	//Lee el fichero
	private void leerFichero() {

		BufferedReader br = null;
		int N = N_Aux;
		int j = 0, i = 0;
		try {

			br = new BufferedReader(new FileReader(nombre));

			String texto = br.readLine();
			cartera_Aux = Integer.valueOf(texto); // Lectura de la primera línea

			texto = br.readLine();

			while (texto != null && i < N) {

				// Tratamiento de la línea leida
				String[] parts = texto.split(","); // Division de la fila

				for (j = 0; j < N; j++) {
					tablero.matriz[i][j] = new Casilla (i,j,Integer.valueOf(parts[j]),100);
				}

				texto = br.readLine(); // Siguiente línea
				
				i++;
			}
		} catch (FileNotFoundException e1) {
			System.out.println("Fichero no encontrado");
		} catch (IOException e) {
			System.out.println("Error en la lectura del fichero");
		} finally {
			try {
				// Cerrar el fichero si se ha podido abrir
				if (br != null) {
					br.close();
				}
			} catch (Exception ex) {
				System.out.println("Error al cerrar el fichero");
				ex.printStackTrace();
			}
		}
	}
}
