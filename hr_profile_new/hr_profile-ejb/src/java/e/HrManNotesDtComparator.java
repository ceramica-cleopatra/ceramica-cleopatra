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
public class HrManNotesDtComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        HrManNotesDt hrManNotesDt1=(HrManNotesDt) o1;
        HrManNotesDt hrManNotesDt2=(HrManNotesDt) o2;
        return hrManNotesDt1.getDisplayOrder().compareTo(hrManNotesDt2.getDisplayOrder());
    }

}
