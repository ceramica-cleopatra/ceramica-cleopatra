/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sb;

import e.HrMachineTimesheetLive;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author data
 */
@Stateless
public class InOutSessionBean implements InOutSessionBeanLocal, Serializable {

  //  @PersistenceContext(unitName = "hr_profile-in-out-PU")
    @PersistenceContext(unitName = "hr_profile-ejbPU")
    private EntityManager em;


    public List<HrMachineTimesheetLive> findHrMachineTimesheetLiveList(Long ip) {
        return em.createNamedQuery("HrMachineTimesheetLive.findAll").setParameter("ip", ip).getResultList();
    }
}
