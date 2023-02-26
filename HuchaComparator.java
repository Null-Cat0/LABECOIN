import java.util.Comparator;

public class HuchaComparator implements Comparator<Tablero>{
    public int compare(Tablero t1, Tablero t2) {
		if (t1.getHucha() == t2.getHucha()) {
			return new MonedasEnTableroComparator().compare(t1, t2);
		} else if (t1.getHucha() < t2.getHucha()) {
			return -1;
		} else {
			return 1;
		}
	}
}
