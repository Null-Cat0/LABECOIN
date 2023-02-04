import java.util.Comparator;

public class ComparatorValorMoneda implements Comparator<Moneda> {

    @Override
    public int compare(Moneda o1, Moneda o2) {
        // TODO Auto-generated method stub
        if (o1.getValorMoneda() == o2.getValorMoneda())
            return 0;
        else if (o1.getFilaMoneda() > o2.getColumnaMoneda())
            return 1;
        else
            return -1;
    }

}
