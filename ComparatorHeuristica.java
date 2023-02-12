import java.util.Comparator;

public class ComparatorHeuristica implements Comparator<Moneda> {

    @Override
    public int compare(Moneda o1, Moneda o2) {
        // TODO Auto-generated method stub
        if (o1.getHeuristica() == o2.getHeuristica())
            return 0;
        else if (o1.getHeuristica() > o2.getHeuristica())
            return 1;
        else
            return -1;
    }

}
