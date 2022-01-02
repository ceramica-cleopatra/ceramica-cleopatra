
import e.HrAllShowroomTrgt;
import java.util.Comparator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class CustomDateComparator implements Comparator<HrAllShowroomTrgt>{
    @Override
    public int compare(HrAllShowroomTrgt o1, HrAllShowroomTrgt o2) {
         String[] arr1=o1.getMonths().split("/");
         String[] arr2=o2.getMonths().split("/");
         if(o1.getMonths().equals(o2.getMonths()))
            return 0;
         if(Long.parseLong(arr1[1])==Long.parseLong(arr2[1])){
            if(Long.parseLong(arr1[0])<Long.parseLong(arr2[0]))
                return -1;
            else
                return 1;
         }
        else if(Long.parseLong(arr1[1])<Long.parseLong(arr2[1]))
                return -1;
         else return 1;

    }
}
