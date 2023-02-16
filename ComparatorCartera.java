import java.util.Comparator;

public class ComparatorCartera implements Comparator<Tablero> {

    @Override
    public int compare(Tablero o1, Tablero o2) {
        // TODO Auto-generated method stub
        if (o1.getCartera() == o2.getCartera())
            return new ComparatorHeuristica().compare(o1,o2);
        else if (o1.getCartera() > o2.getCartera())
            return 1;
        else
            return -1;
    }

}
