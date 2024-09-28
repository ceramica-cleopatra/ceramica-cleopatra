
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
public class MenuModelComparator implements Comparator<MenuModelDTO>{

    @Override
    public int compare(MenuModelDTO o1, MenuModelDTO o2) {
        if(o1.getParent().getMenuOrder() > o2.getParent().getMenuOrder())
            return 1;
        else if(o1.getParent().getMenuOrder() < o2.getParent().getMenuOrder())
            return -1;
        else
            return 0;
    }

}
