import java.util.Comparator;

public class ComparatorHeuristica implements Comparator<Tablero> {

    @Override
    public int compare(Tablero o1, Tablero o2) {
        // TODO Auto-generated method stub
        if (o1.r.getHeuristica() == o2.r.getHeuristica())
            return 0;
        else if (o1.r.getHeuristica() > o2.r.getHeuristica())
            return 1;
        else
            return -1;
    }

}
