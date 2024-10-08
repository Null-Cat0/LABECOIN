
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PrimeroMejor {
	private List <Tablero> abiertos;
	private List <Tablero> cerrados;
	private List <String> movimientos;
	private Tablero actual;
	private int contadorNodos;
	static int maxNodos = 9999; 
	
	public PrimeroMejor(Tablero tab) {
		actual = tab;
		abiertos = new ArrayList<>();
		cerrados = new ArrayList<>();
		movimientos = new ArrayList<>();
		contadorNodos = 0;

		actual.encontrarRobot(); 
		actual.encontrarMonedas();
		actual.encontrarSalida();
		actual.encontrarMObjetivo();
	}
	public List <Tablero> generarSucesores(Tablero tab) {
		
		List <String> posiblesMov = tab.getPosiblesMov();
		List <Tablero> hijos = new ArrayList<>();
		boolean noMoves;
		Tablero hijo;
		
		for (int i = 0; i < posiblesMov.size(); i++) {
			hijo = tab.copiarTablero();
			contadorNodos++;
			String mov = posiblesMov.get(i);
			noMoves = false;
			if (hijo.getHeuristicaMovSinMatriz(mov) == 0) {
				noMoves = true;
				hijo.movimientoRobot(mov);
				hijo.getListaMonedas().remove(hijo.mObjetivo);
				hijo.encontrarMObjetivo();
				
				if (hijo.getHucha() >= hijo.getCartera()) {
					hijo.setmObjetivo(hijo.salida);
				}
			}
			if (!noMoves) hijo.movimientoRobot(mov);
			
			hijos.add(hijo);
		}
		return hijos;
	}

	public void tratarRepetidos(List <Tablero> hijos) {
		
		for (int i = 0; i < abiertos.size(); i++) {
			for (int j = 0; j < hijos.size(); j++) {
				if (hijos.get(j).equals(abiertos.get(i))) {
					hijos.remove(j);	
				}
			}
		}	
		
		for (int i = 0; i < cerrados.size(); i++) {
			for (int j = 0; j < hijos.size(); j++) {
				if (hijos.get(j).equals(cerrados.get(i))) {
					hijos.remove(j);	
				}
			}
		}
	}
	
	public void primeroMejor() {
		
		abiertos.add(actual);

		while (!actual.fin() && !abiertos.isEmpty() ) {
			abiertos.remove(0);
			cerrados.add(actual);

			//Generamos los sucesores del tablero actual
			List <Tablero> hijos = generarSucesores(actual);
			
			//Tratamos los repetidos
			tratarRepetidos(hijos);

			//Insertamos los hijos en la lista de nodos abiertos
			for (int i = 0; i < hijos.size(); i++) {
				abiertos.add(hijos.get(i));
			}

			// Ordenamos la lista de abiertos
			Collections.sort(abiertos, Collections.reverseOrder(new HuchaComparator()));
			if (!abiertos.isEmpty()) {
				actual = abiertos.get(0);
			}
		}
	}
	
	public void mostrarResultados() {
		if(contadorNodos >= maxNodos) System.out.println("La ejecucion se ha detenido porque se excede el numero de nodos generados");
		else {
			System.out.println("Mostramos el camino recorrido: ");

			Tablero aux = actual.getLastTablero();
			movimientos.add(actual.getLastMov());
			while (aux != null) {
				movimientos.add(aux.getLastMov());
				aux = aux.getLastTablero();
			}

			int auxCont = 0;
			for (int i = movimientos.size()-2; i > 0; i--) {
				System.out.print(movimientos.get(i) + ", ");
				auxCont++;
				if(auxCont == 20) {
					System.out.println();
					auxCont = 0;
				}
			}
			System.out.println(movimientos.get(0));

			System.out.println("\nEstado final del tablero:\n" );
			System.out.println("Valor total de las monedas recogidas: "+actual.getHucha());
			System.out.println("Valor total de la cartera: "+actual.getCartera());
			actual.impresionMatrizVisual();
		}
		System.out.println("El numero de nodos generados es: " + contadorNodos);
	}
}
