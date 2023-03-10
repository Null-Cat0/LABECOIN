import java.util.ArrayList;
import java.util.List;

public class MaximaPendiente {
	private List<String> movEscaladaSimple;
	private boolean esFin;
	private Tablero tablero;
	private List<String> posiblesMov;
	private boolean enc = false;
	private double mejorHeuristica = 9999;
	private int contadorNodos;

	public MaximaPendiente(Tablero tablero) {
		movEscaladaSimple = new ArrayList<>();
		posiblesMov =  new ArrayList<>();
		esFin = false;
		posiblesMov = null;
		this.tablero = tablero;
		
		tablero.encontrarRobot(); 
		tablero.encontrarMonedas();
		tablero.encontrarSalida();
		tablero.encontrarMObjetivo();
	}

	public void maximaPendiente() {
		enc = false;
		if (tablero.fin()) esFin = true;

		else {
			String mov = " ";
			if (!esFin) {
				contadorNodos++;
				posiblesMov = tablero.getPosiblesMov();
				mejorHeuristica = 9999;
				for (int i = 0; i < posiblesMov.size(); i++) {
					if(tablero.getHeuristicaMovSinMatriz(posiblesMov.get(i)) < mejorHeuristica) {
						mov = posiblesMov.get(i);
						mejorHeuristica = tablero.getHeuristicaMovSinMatriz(mov);
						
					}
				}
				
				if (tablero.getHeuristicaMovSinMatriz(mov) <= tablero.getHeuristicaTablero() ) {
						enc = true;
						if(tablero.getHeuristicaMovSinMatriz(mov) == 0)
						{
							tablero.getListaMonedas().remove(tablero.mObjetivo);
							tablero.encontrarMObjetivo();
						
						}
						tablero.movimientoRobot(mov);
						
						
						if (tablero.getCartera() == tablero.getHucha()) {
							tablero.setmObjetivo(tablero.salida);
							movEscaladaSimple.add(mov);
							maximaPendiente();	
						}
						else {
							movEscaladaSimple.add(mov);
							maximaPendiente(); 
						}
				}

			} 		
		} 
	}
	
	public void mostrarResultados() {
		System.out.println("\nMostramos el camino recorrido: ");
		
		for(int i = 0; i < movEscaladaSimple.size()-1; i++) {
			System.out.print(movEscaladaSimple.get(i) + ", ");
		}
		System.out.println(movEscaladaSimple.get(movEscaladaSimple.size()-1));
		
		System.out.print("\nEstado final del tablero:\n" );
		tablero.impresionMatrizVisual();
		System.out.println("El numero de nodos generados es: " + contadorNodos);
	}
}




