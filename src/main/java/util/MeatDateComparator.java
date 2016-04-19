package util;

import model.Meat;
import org.joda.time.DateTime;

import java.util.Comparator;

/**
 * Created by Jakub on 19.04.2016.
 */
public class MeatDateComparator implements Comparator<Meat> {
    @Override
    public int compare(Meat o1, Meat o2) {
        DateTime o1Value = DateConverter.toDateTimeDots(((Meat) o1).getExpiryDate());
        DateTime o2Value = DateConverter.toDateTimeDots(((Meat) o2).getExpiryDate());

        return o1Value.compareTo(o2Value);
    }
}
