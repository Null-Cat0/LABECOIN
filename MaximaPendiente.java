import java.util.ArrayList;
import java.util.List;

public class MaximaPendiente {
	private List<String> movEscaladaSimple;
	private boolean esFin;
	private Tablero tablero;
	private List<String> posiblesMov;
	
	private boolean enc = false;
	private double mejorHeuristica = 9999;
	private boolean noMoves = false; // Usado para no realizar el movimiento dos veces en el caso de cargar la heuristica

	public MaximaPendiente(Tablero tablero) {
		movEscaladaSimple = new ArrayList<>();
		esFin = false;
		posiblesMov = null;
		this.tablero = tablero;
		
		tablero.encontrarRobot(); 
		tablero.encontrarMonedas();
		tablero.cargarHeuristicaMonedasTodaMatriz();;
	}

	public void maximaPendiente() {

		enc = false;
		noMoves = false;

		if (tablero.getCartera() ==0) esFin = true;

		else {
			String mov = " ";
			if (!esFin) {
				posiblesMov = tablero.obtenerPosiblesMov();
				mejorHeuristica = 9999;
				for (int i = 0; i < posiblesMov.size(); i++) {
					if(tablero.obtenerHeuristicaMov(posiblesMov.get(i)) < mejorHeuristica) {
						mov = posiblesMov.get(i);
						mejorHeuristica = tablero.obtenerHeuristicaMov(mov);
					}
				}
				if (tablero.obtenerHeuristicaMov(mov) <= tablero.getHeuristicaTablero()) {
					enc = true;
					if (tablero.obtenerHeuristicaMov(mov) == 0) {
						noMoves = true;
						tablero.movimientoRobot(mov);
						tablero.resetearHeuristica();
						if (tablero.getCartera()==0) {
							System.out.println(0);
							//tablero.cargarHeuristicaSalida();
						}
						else {
							tablero.cargarHeuristicaMonedasTodaMatriz(); 
						}
					}
				}
				if(!enc) esFin = true;
				
				else {
					if(!noMoves) tablero.movimientoRobot(mov);					
					movEscaladaSimple.add(mov);
					maximaPendiente();
				}
			}
		}
	}
	
	public void mostrarResultados() {
		System.out.println("\nMostramos el camino recorrido: ");
		
		int auxCont = 0;
		for(int i = 0; i < movEscaladaSimple.size()-1; i++) {
			System.out.print(movEscaladaSimple.get(i) + ", ");
			auxCont++;
			if(auxCont == 20) {
				System.out.println();
				auxCont = 0;
			}
		}
		System.out.println(movEscaladaSimple.get(movEscaladaSimple.size()-1));
		
		System.out.print("\nEstado final del tablero:\n" + tablero);
	}
	// public static void main(String[] args) {
    //     Datos_Iniciales d = new Datos_Iniciales("LABECOIN1.txt");
    //     MaximaPendiente m = new MaximaPendiente(d.getTablero());
    //     m.maximaPendiente();
    //     m.mostrarResultados();
    //     // m.generarSucesores(d.getTablero());

    // }
}




