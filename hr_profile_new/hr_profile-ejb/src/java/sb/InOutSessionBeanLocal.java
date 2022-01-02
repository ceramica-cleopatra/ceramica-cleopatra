/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sb;

import e.HrMachineTimesheetLive;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author data
 */
@Local
public interface InOutSessionBeanLocal {
    public List<HrMachineTimesheetLive> findHrMachineTimesheetLiveList(Long ip);
}
