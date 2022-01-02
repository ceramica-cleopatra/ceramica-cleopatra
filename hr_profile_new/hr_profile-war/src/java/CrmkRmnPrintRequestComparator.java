
import java.util.Comparator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class CrmkRmnPrintRequestComparator implements Comparator<RmnListRequestedToPrintDTO>{

    @Override
    public int compare(RmnListRequestedToPrintDTO o1, RmnListRequestedToPrintDTO o2) {
        if(o1.getStoreId()>o2.getStoreId())
            return 1;
        else if(o1.getStoreId()>o2.getStoreId())
            return -1;
        else
            return 0;
    }

}
