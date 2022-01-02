/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.util.Comparator;

/**
 *
 * @author data
 */
public class HrCheckupDutyDtComparator implements Comparator{

    @Override
    public int compare(Object o1, Object o2) {
        HrCheckupDutyDt1 hrCheckupDutyDt1=(HrCheckupDutyDt1) o1;
        HrCheckupDutyDt1 hrCheckupDutyDt2=(HrCheckupDutyDt1) o2;
        return hrCheckupDutyDt1.getDisplayOrder().compareTo(hrCheckupDutyDt2.getDisplayOrder());
         
    }

}
