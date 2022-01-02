package e;


import e.HrCheckupDutyDt2;
import java.util.Comparator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author data
 */
public class HrCheckupDutyDt2Comparator implements Comparator<HrCheckupDutyDt2>{

    @Override
    public int compare(HrCheckupDutyDt2 o1, HrCheckupDutyDt2 o2) {
        if(o1.getDisplayOrder()==null || o2.getDisplayOrder()==null)
            return 0;
        if(o1.getDisplayOrder()>o2.getDisplayOrder())
            return 1;
        else if(o1.getDisplayOrder()<o2.getDisplayOrder())
            return -1;
        else
            return 0;
    }

}
