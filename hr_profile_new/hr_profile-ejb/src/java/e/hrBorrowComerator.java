/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package e;

import java.util.Calendar;
import java.util.Comparator;

/**
 *
 * @author ahmed abbas
 */
public class hrBorrowComerator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        if (o1 instanceof HrBorrowDt && o2 instanceof HrBorrowDt) {
            HrBorrowDt hrBorrowDt1 = (HrBorrowDt) o1;
            HrBorrowDt hrBorrowDt2 = (HrBorrowDt) o2;
            c1.set(Calendar.MONTH, Integer.parseInt(hrBorrowDt1.getPayMonth().toString()));
            c1.set(Calendar.YEAR, Integer.parseInt(hrBorrowDt1.getPayYear().toString()));
            c2.set(Calendar.MONTH, Integer.parseInt(hrBorrowDt2.getPayMonth().toString()));
            c2.set(Calendar.YEAR, Integer.parseInt(hrBorrowDt2.getPayYear().toString()));
        } else if (o1 instanceof HrBorrowZamalaDt && o2 instanceof HrBorrowZamalaDt) {
            HrBorrowZamalaDt hrBorrowZamalaDt1 = (HrBorrowZamalaDt) o1;
            HrBorrowZamalaDt hrBorrowZamalaDt2 = (HrBorrowZamalaDt) o2;
            c1.set(Calendar.MONTH, Integer.parseInt(hrBorrowZamalaDt1.getPayMonth().toString()));
            c1.set(Calendar.YEAR, Integer.parseInt(hrBorrowZamalaDt1.getPayYear().toString()));
            c2.set(Calendar.MONTH, Integer.parseInt(hrBorrowZamalaDt2.getPayMonth().toString()));
            c2.set(Calendar.YEAR, Integer.parseInt(hrBorrowZamalaDt2.getPayYear().toString()));
        }
         if (c1.before(c2)) {
            return -1;
        } else if (c2.before(c1)){
            return 1;
        }
        return 0;
    }
}
