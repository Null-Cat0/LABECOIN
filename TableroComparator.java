import java.util.Comparator;

public class TableroComparator implements Comparator <Tablero>
{
	public int compare(Tablero t1, Tablero t2) {
		if (t1.getHeuristicaTablero() == t2.getHeuristicaTablero()) {
			return 0;
		}
		else if (t1.getHeuristicaTablero() < t2.getHeuristicaTablero()) {
			return -1;
		}
		else {
			return 1;
		}
	}
}
