
import e.HrBorrowZamalaDt;
import java.util.Calendar;
import java.util.Comparator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author data
 */
public class FundBorrowDetailsComparator implements Comparator<HrBorrowZamalaDt> {

    @Override
    public int compare(HrBorrowZamalaDt o1, HrBorrowZamalaDt o2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.set(Calendar.MONTH, Integer.parseInt(o1.getPayMonth().toString()));
        c1.set(Calendar.YEAR, Integer.parseInt(o1.getPayYear().toString()));
        c2.set(Calendar.MONTH, Integer.parseInt(o2.getPayMonth().toString()));
        c2.set(Calendar.YEAR, Integer.parseInt(o2.getPayYear().toString()));

        if (c1.before(c2)) {
            return -1;
        } else if (c2.before(c1)){
            return 1;
        }
        return 0;
    }
}
