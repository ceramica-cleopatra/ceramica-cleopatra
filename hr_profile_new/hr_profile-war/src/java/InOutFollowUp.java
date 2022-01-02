/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrMachineTimesheetLive;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;
import sb.InOutSessionBean;
import sb.InOutSessionBeanLocal;
import sb.SessionBeanLocal;

/**
 *
 * @author data
 */
@ManagedBean
@ViewScoped
public class InOutFollowUp {
    @EJB
    private InOutSessionBeanLocal inOutSessionBean;

    
    private List<HrMachineTimesheetLive> hrMachineTimesheetLiveList = new ArrayList<HrMachineTimesheetLive>();
    private boolean stopFlag;
    private Long ip;

    @PostConstruct
    public void init() {
        stopFlag = false;
        if (ip == null) {
            String s = getIpAddress();
            if (s != null && !s.trim().equals("")) {
                ip = Long.parseLong(s);
            }
        }
        hrMachineTimesheetLiveList = inOutSessionBean.findHrMachineTimesheetLiveList(ip);
    }

    public void start(ActionEvent ae) {
        stopFlag = true;
    }

    public void stop(ActionEvent ae) {
        stopFlag = false;
    }

    public String getIpAddress() {
        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String reqIp = httpServletRequest.getRemoteAddr();
        String ipSec = reqIp.substring(5, reqIp.length()).toString();
        int counter = ipSec.indexOf(".");
        ipSec = ipSec.substring(0, counter).toString();
        return ipSec;
    }

    /** Creates a new instance of InOutFollowUp */
    public InOutFollowUp() {
    }

    public List<HrMachineTimesheetLive> getHrMachineTimesheetLiveList() {
        return hrMachineTimesheetLiveList;
    }

    public void setHrMachineTimesheetLiveList(List<HrMachineTimesheetLive> hrMachineTimesheetLiveList) {
        this.hrMachineTimesheetLiveList = hrMachineTimesheetLiveList;
    }

    public boolean isStopFlag() {
        return stopFlag;
    }

    public void setStopFlag(boolean stopFlag) {
        this.stopFlag = stopFlag;
    }

    public Long getIp() {
        return ip;
    }

    public void setIp(Long ip) {
        this.ip = ip;
    }

    
}
