import java.util.ArrayList;
import java.util.List;

public class MaximaPendiente {
	private List<String> movEscaladaSimple;
	private boolean esFin;
	private Tablero tablero;
	private List<String> posiblesMov;
	private boolean aux = true;
	
	private boolean enc = false;
	private double mejorHeuristica = 9999;
	private boolean noMoves = false; // Usado para no realizar el movimiento dos veces en el caso de cargar la heuristica

	public MaximaPendiente(Tablero tablero) {
		movEscaladaSimple = new ArrayList<>();
		posiblesMov =  new ArrayList<>();
		esFin = false;
		posiblesMov = null;
		this.tablero = tablero;
		
		tablero.encontrarRobot(); 
		tablero.encontrarMonedas();
		tablero.encontrarSalida();
		tablero.resetearHeuristica();
		tablero.encontrarMObjetivo();
	//	System.out.println(tablero.mObjetivo.getFila()+tablero.mObjetivo.getColumna());
	//	System.out.println(tablero.getHeuristicaTablero());
	}

	public void maximaPendiente() {
		
		//System.out.println("GETHEURISTICATABLERO "+tablero.getHeuristicaTablero(aux));

		enc = false;
		noMoves = false;

		if (tablero.fin()) esFin = true;

		else {
			String mov = " ";
			if (!esFin) {
				posiblesMov = tablero.getPosiblesMov();
				mejorHeuristica = 9999;
				for (int i = 0; i < posiblesMov.size(); i++) {
					// System.out.println("POSIBLES MOV:"+posiblesMov.get(i)+" HEURISTICA: "+ tablero.obtenerHeuristicaMovSinMatriz(posiblesMov.get(i)));
					// System.out.println(mejorHeuristica);
					if(tablero.getHeuristicaMovSinMatriz(posiblesMov.get(i)) < mejorHeuristica) {
						mov = posiblesMov.get(i);
						mejorHeuristica = tablero.getHeuristicaMovSinMatriz(mov);
						
					}
				}
				// System.out.println("OBTENERHEURISTICAMATRIZ "+tablero.obtenerHeuristicaMovSinMatriz(mov) );
				// System.out.println("GETHEURISTICATABLERO "+tablero.getHeuristicaTablero(aux));
				
				if (tablero.getHeuristicaMovSinMatriz(mov) <= tablero.getHeuristicaTablero() ) {
						enc = true;
						if(tablero.getHeuristicaMovSinMatriz(mov) == 0)
						{
							tablero.getListaMonedas().remove(tablero.mObjetivo);
							tablero.encontrarMObjetivo();
						
						}
						tablero.movimientoRobot(mov);
						
						noMoves = true;
						
						// System.out.println( "hucha "+ tablero.getHucha());
						
						if (tablero.getCartera() == tablero.getHucha()) {
							tablero.setmObjetivo(tablero.salida);
							// System.out.println("---------------------------------------------------------------------------------------------------");
							// System.out.println(tablero.mObjetivo.getFila()+ " "  + tablero.mObjetivo.getColumna());
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
	}
	public static void main(String[] args) {
        Datos_Iniciales d = new Datos_Iniciales("LABECOIN1.txt");
        MaximaPendiente m = new MaximaPendiente(d.getTablero());
        m.maximaPendiente();
        m.mostrarResultados();

    }
}




