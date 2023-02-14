import java.util.Comparator;

public class ComparatorValorMoneda implements Comparator<Casilla> {

    @Override
    public int compare(Casilla o1, Casilla o2) {
        // TODO Auto-generated method stub
        if (o1.getValor() == o2.getValor())
            return 0;
        else if (o1.getValor() > o2.getValor())
            return 1;
        else
            return -1;
    }

}
