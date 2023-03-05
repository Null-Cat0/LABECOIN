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
		posiblesMov =  new ArrayList<>();
		esFin = false;
		posiblesMov = null;
		this.tablero = tablero;
		
		tablero.encontrarRobot(); 
		tablero.encontrarMonedas();
		tablero.encontrarSalida();
		tablero.resetearHeuristica();
		tablero.cargarHeuristicaMonedasTodaMatriz();
		System.out.println(tablero.getHeuristicaTablero());
	}

	public void maximaPendiente() {

		enc = false;
		noMoves = false;

		if (tablero.fin()) esFin = true;

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
		
				System.out.println("------------------------------------------------------------------------------------");
				System.out.println(tablero.obtenerHeuristicaMov(mov) );
				System.out.println(tablero.getHeuristicaTablero());
				System.out.println( "hucha "+ tablero.getHucha());	
				if (tablero.obtenerHeuristicaMov(mov) <= tablero.getHeuristicaTablero() ) {
					enc = true;
					System.out.println(tablero.obtenerHeuristicaMov(mov)); 
					if (tablero.obtenerHeuristicaMov(mov) == 0) {
						noMoves = true;
						tablero.resetearHeuristica();
						System.out.println( "hucha "+ tablero.getHucha());
						if (tablero.getCartera() == tablero.getHucha()) {
							System.out.println("------------------------------------------------------------------------------------");
							//return;
							tablero.cargarHeuristicaSalida();
							// mejorHeuristica = tablero.calcularHeuristica(tablero.getRobot(), tablero.getSalida());
						}
						else {
							tablero.cargarHeuristicaMonedasTodaMatriz(); 
						}
					}
				}
				System.out.println(enc);
				System.out.println(noMoves);
				System.out.println(mov);
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
		
		for(int i = 0; i < movEscaladaSimple.size()-1; i++) {
			System.out.print(movEscaladaSimple.get(i) + ", ");
		}
		System.out.println(movEscaladaSimple.get(movEscaladaSimple.size()-1));
		
		System.out.print("\nEstado final del tablero:\n" );
		tablero.impresionMatrizVisual();
	}
	public static void main(String[] args) {
        Datos_Iniciales d = new Datos_Iniciales("LABECOIN2.txt");
        MaximaPendiente m = new MaximaPendiente(d.getTablero());
        m.maximaPendiente();
        m.mostrarResultados();

    }
}




