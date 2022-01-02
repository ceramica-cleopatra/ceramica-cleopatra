/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;
import java.util.Comparator;
/**
 *
 * @author ahmed abbas
 */
public class hrEmpTimeComparator implements Comparator {

//    public int compare(HrEmpTime x1, HrEmpTime x2){
//
//
//    }


    public hrEmpTimeComparator()
    {
    super();
    }
    @Override
    public int compare(Object o1, Object o2) {
        NextHrEmpTime x1=(NextHrEmpTime) o1;
        NextHrEmpTime x2=(NextHrEmpTime) o2;
        if(x1.getTrnsDate().before(x2.getTrnsDate())){
        return 0;
        }else if(x1.getTrnsDate().after(x2.getTrnsDate()))
        {
        return 1;
        }
        else
        {
        throw new UnsupportedOperationException("Not supported yet.");
        }
    }

  
}


