import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Datos_Iniciales {

	private String nombre; // Nombre del fichero
	int[][] matrizAuxiliar;
	private Tablero tab;
	int N_Aux;
	int cartera_Aux;

	public Datos_Iniciales(String _nombre) {

		nombre = "Ejemplos/" + _nombre;
		N_Aux = ordenMatriz();
		matrizAuxiliar = new int[N_Aux][N_Aux];// Reservar espacio en la memoria para la matriz
		leerFichero();
		tab = new Tablero(this.getListaMonedas(), this.getFilaRobot(), this.getColumnaRobot(), matrizAuxiliar,
				cartera_Aux, N_Aux);

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
					matrizAuxiliar[i][j] = Integer.valueOf(parts[j]);
				}

				texto = br.readLine(); // Siguiente línea
				i++;
			}
			System.out.println("Cartera: " + cartera_Aux);

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

	private List<Moneda> getListaMonedas() {
		int N = N_Aux;
		List<Moneda> listaMonedas = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((this.matrizAuxiliar[i][j] <= 6) && (this.matrizAuxiliar[i][j] >= 1)) {
					// Creamos Moneda
					Moneda aux = new Moneda();
					aux.setColumnaMoneda(j);
					aux.setFilaMoneda(i);
					aux.setValorMoneda(this.matrizAuxiliar[i][j]);
					// Insertamos Moneda
					listaMonedas.add(aux);
				}

			}
		}
		// Para ordenar las monedas en valor descendente (de mayor valor a menor valor)
		// Collections.sort(listaMonedas,Collections.reverseOrder (new
		// ComparatorValorMoneda()));
		return listaMonedas;
	}

	private int getFilaRobot() {
		int N = N_Aux;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (matrizAuxiliar[i][j] == 8)
					return i;
			}
		}
		return -1;
	}

	private int getColumnaRobot() {
		int N = N_Aux;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (matrizAuxiliar[i][j] == 8)
					return j;
			}
		}
		return -1;
	}

	public Tablero getTablero() {
		return tab;
	}

	// public static void main(String[] args) {
	// 		Datos_Iniciales n = new Datos_Iniciales("LABECOIN1.txt");
			
	// }
}