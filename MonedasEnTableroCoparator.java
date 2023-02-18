import java.util.Comparator;

public class MonedasEnTableroCoparator implements Comparator<Tablero> {
	public int compare(Tablero t1, Tablero t2) {
		if (t1.getNumeroMonedas() == t2.getNumeroMonedas()) {
			return new TableroComparator().compare(t1, t2);
		} else if (t1.getNumeroMonedas() < t2.getNumeroMonedas()) {
			return -1;
		} else {
			return 1;
		}
	}
}
