
import e.HrDynAlertTemplateCompDt;
import e.HrDynAlertTemplateDt;
import java.util.Comparator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author data
 */
public class DynamicAlertTemplateComparator implements Comparator<HrDynAlertTemplateDt>{
    @Override
    public int compare(HrDynAlertTemplateDt o1, HrDynAlertTemplateDt o2) {
        if(o1.getId()>o2.getId())
            return 1;
        else if(o1.getId()<o2.getId())
            return -1;
        else return 0;
    }
}
