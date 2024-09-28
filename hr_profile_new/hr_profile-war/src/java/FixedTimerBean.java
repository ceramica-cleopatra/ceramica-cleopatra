
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DEV
 */
@Singleton
public class FixedTimerBean {

    @EJB
    private WorkerBean workerBean;

    @Lock(LockType.READ)
    @Schedule(second = "*", minute = "*/59", hour = "*", persistent = false)
    public void atSchedule() throws InterruptedException {
        workerBean.doTimerWork();
    }
}