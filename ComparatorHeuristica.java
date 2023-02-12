import java.util.Comparator;

public class ComparatorHeuristica implements Comparator<Double> {

    @Override
    public int compare(Double o1, Double o2) {
        // TODO Auto-generated method stub
        if (o1 == o2)
            return 0;
        else if (o1 > o2)
            return 1;
        else
            return -1;
    }

}
