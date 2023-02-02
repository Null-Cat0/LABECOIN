import java.io.*;

public class lecturaFichero {

	private String nombre; // Nombre del fichero
	int[][] tablero;// Tablero de juego
	int cartera;

	public lecturaFichero(String _nombre) {

		nombre = _nombre;
		tablero = new int[10][10];// Inicializar la matriz
		leerFichero();

	}

	private void leerFichero() {

		BufferedReader br = null;
		int j = 0, i = 0;

		try {

			br = new BufferedReader(new FileReader(nombre));

			String texto = br.readLine();
			cartera = Integer.valueOf(texto); // Lectura de la primera línea

			texto = br.readLine();

			while (texto != null && i < 10) {

				// Tratamiento de la línea leida
				String[] parts = texto.split(","); // Division de la fila
				for (j = 0; j < 10; j++) {
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
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(tablero[i][j] + " ");
			}
			System.out.println();
		}
	}

	public int[][] getMatriz() {
		return this.tablero;
	}

	public int getCatera() {
		return this.cartera;
	}
	/*
		//Prueba de lectura del fichero
		public static void main(String[] args) {
			lecturaFichero l = new lecturaFichero("LABECOIN1.txt");
			l.mostrarMatriz();
		}
	*/
}