import java.util.ArrayList;
import java.util.List;

public class EscaladaSimple {
    private List<String> movEscaladaSimple;
	private boolean esFin;
	private Tablero tablero;
	private List<String> posiblesMov;
	private boolean aux = true;
	
	private boolean enc = false;
	private double mejorHeuristica = 9999;
	private boolean noMoves = false; // Usado para no realizar el movimiento dos veces en el caso de cargar la heuristica
	private int contadorNodos;
	public EscaladaSimple(Tablero tablero) {
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

	public void escaladaSimple() {
		
	
		enc = false;
		noMoves = false;

		if (tablero.fin()) esFin = true;

		else {
			String mov = " ";
			if (!esFin) {
				posiblesMov = tablero.getPosiblesMov();
				for (int i = 0; i < posiblesMov.size(); i++) {
					if(tablero.getHeuristicaMovSinMatriz(posiblesMov.get(i)) < tablero.getHeuristicaTablero()) {
						mov = posiblesMov.get(i);
						mejorHeuristica = tablero.getHeuristicaMovSinMatriz(mov);
                        break;	
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
						
						noMoves = true;
						
						if (tablero.getCartera() == tablero.getHucha()) {
							tablero.setmObjetivo(tablero.salida);
							movEscaladaSimple.add(mov);
							escaladaSimple();	
						}
						else {
							movEscaladaSimple.add(mov);
							escaladaSimple(); 
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
