import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Datos_Iniciales {

	private String nombre; // Nombre del fichero
	int[][] tablero;// Tablero de juego
	int cartera;
	int N; // Número de filas x columnas del fichero

	public Datos_Iniciales(String _nombre) {

		nombre = "Ejemplos/"+ _nombre;
		N = ordenMatriz();
		tablero = new int[N][N];// Reservar espacio en la memoria para la matriz
		leerFichero();

	}

	private int ordenMatriz()
	{	
		int aux = 0;
		BufferedReader br = null;
		try {

			br = new BufferedReader(new FileReader(nombre));

			String texto = br.readLine(); //Primera línea, no necesaria.
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

	private void leerFichero() {

		BufferedReader br = null;
		int j = 0, i = 0;
		try {

			br = new BufferedReader(new FileReader(nombre));

			String texto = br.readLine();
			cartera = Integer.valueOf(texto); // Lectura de la primera línea

			texto = br.readLine();

			while (texto != null && i < N) {

				// Tratamiento de la línea leida
				String[] parts = texto.split(","); // Division de la fila

				for (j = 0; j < N; j++) {
					tablero[i][j] = Integer.valueOf(parts[j]);
				}

				texto = br.readLine(); // Siguiente línea
				i++;
			}
			System.out.println("Cartera: " + cartera);

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

	public void mostrarMatriz() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(tablero[i][j] + " ");
			}
			System.out.println();
		}
	}

	public int[][] getMatriz() {
		return this.tablero;
	}

	public int getCartera() {
		return this.cartera;
	}

	public int getFilaRobot() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (tablero[i][j] == 8)
					return i;
			}
		}
		return -1;
	}

	public int getColumnaRobot() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (tablero[i][j] == 8)
					return j;
			}
		}
		return -1;
	}

	public List<Moneda> getListaMonedas()
	{
		List <Moneda> listaMonedas = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if((tablero[i][j]<=6)&&(tablero[i][j] >= 1))
				{
					//Creamos Moneda
					Moneda aux = new Moneda();
					aux.setColumnaMoneda(j);
					aux.setFilaMoneda(i);
					//Insertamos Moneda
					listaMonedas.add(aux);
				}
					
			}
		}
		//Para ordenar las monedas en valor descendente (de mayor valor a menor valor)
		// Collections.sort(listaMonedas,Collections.reverseOrder (new ComparatorValorMoneda()));
		return listaMonedas;
	}

	// Prueba de lectura del fichero
	public static void main(String[] args) {
		Datos_Iniciales l = new Datos_Iniciales("LABECOIN1.txt");
		l.mostrarMatriz();
	}

}