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
public class DynamicAlertTemplateComparator implements Comparator<HrDynAlertTemplateDt> {

    @Override
    public int compare(HrDynAlertTemplateDt o1, HrDynAlertTemplateDt o2) {
            if (o1.getId() > o2.getId()) {
                return 1;
            } else if (o1.getId() < o2.getId()) {
                return -1;
            } else {
                return 0;
            }
       
    }
}
