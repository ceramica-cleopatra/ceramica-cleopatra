
import e.HrMenuTable;
import java.util.Comparator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class MenuComparator implements Comparator<HrMenuTable>{

    @Override
    public int compare(HrMenuTable o1, HrMenuTable o2) {
        if(o1.getMenuOrder() > o2.getMenuOrder())
            return 1;
        else if(o1.getMenuOrder() < o2.getMenuOrder())
            return -1;
        else
            return 0;
    }

}
