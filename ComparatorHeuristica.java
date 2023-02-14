import java.util.Comparator;

public class ComparatorHeuristica implements Comparator<Casilla> {

    @Override
    public int compare(Casilla o1, Casilla o2) {
        // TODO Auto-generated method stub
        if (o1.getHeuristica() == o2.getHeuristica())
            return 0;
        else if (o1.getHeuristica() > o2.getHeuristica())
            return 1;
        else
            return -1;
    }

}
