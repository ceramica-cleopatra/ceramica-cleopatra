/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrOpenDutyDt;
import e.HrOpenDutyExpectedLocDt;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.DragDropEvent;
import sb.SessionBeanRemote;

/**
 *
 * @author Administrator
 */
@ManagedBean
@ViewScoped
public class duty_trns {
    @EJB
    private SessionBeanRemote sessionBean;

    /** Creates a new instance of duty_trns */
    public duty_trns() {
    }
    private List<HrOpenDutyExpectedLocDt> expectedDutyEmp=new ArrayList<HrOpenDutyExpectedLocDt>();
    private List<HrOpenDutyDt> hrOpenDutyDts=new ArrayList<HrOpenDutyDt>();
    private HrEmpInfo hrEmpInfo=new HrEmpInfo();
    private String usercode;
    private boolean external=false;

    public List<HrOpenDutyExpectedLocDt> getExpectedDutyEmp() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ValueBinding vb = fc.getApplication().createValueBinding("#{loginBean.username}");
        usercode = vb.getValue(fc).toString();
        hrEmpInfo=sessionBean.finduserbyid(Long.parseLong(usercode));
        System.out.println("location"+sessionBean.getLoactionByIp(getIpAddress()).getId());
        expectedDutyEmp=sessionBean.findExpectedDutyLocations(sessionBean.getLoactionByIp(getIpAddress()).getId(),new Date(),getIpAddress());
        return expectedDutyEmp;
    }

    public String getIpAddress() {
        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String reqIp = httpServletRequest.getHeader("x-forwarded-for");
        if (reqIp != null) {
            String[] ipArray = reqIp.split(",");
            for (int i = 0; i < ipArray.length; i++) {
                if (ipArray[i] != null && !ipArray[i].isEmpty() && !ipArray[i].trim().contains("20.1.1.46")) {
                    reqIp = ipArray[i];
                    break;
                }
            }
        }
        if (reqIp == null || "".equals(reqIp)) {
            reqIp = httpServletRequest.getRemoteAddr();
        }
        String ipSec = reqIp.substring(5, reqIp.length()).toString();
        int counter = ipSec.indexOf(".");
        ipSec = ipSec.substring(0, counter).toString();
        return ipSec;
    }
    
    public void onEmpDrop(DragDropEvent ddEvent) {
        FacesContext fc = FacesContext.getCurrentInstance();
        ValueBinding vb = fc.getApplication().createValueBinding("#{loginBean.username}");
        usercode = vb.getValue(fc).toString();
        HrOpenDutyExpectedLocDt hrOpenDutyExpectedLocDt = ((HrOpenDutyExpectedLocDt) ddEvent.getData());
        HrOpenDutyDt hrOpenDutyDt=new HrOpenDutyDt();
        hrOpenDutyDt.setTrnsTime(new Date());
        hrOpenDutyDt.setHrOpenDutyHd(hrOpenDutyExpectedLocDt.getHrOpenDutyHd());
        hrOpenDutyDt.setSecId(Long.parseLong(usercode));
        hrOpenDutyDt.setLocId(sessionBean.getLoactionByIp(getIpAddress()));
        Calendar c1=Calendar.getInstance();
        Calendar c2=Calendar.getInstance();
        c1.set(Calendar.HOUR, 0);
        c1.set(Calendar.MINUTE,0);
        c1.set(Calendar.SECOND,0);
        c2.set(Calendar.HOUR, 23);
        c2.set(Calendar.MINUTE,59);
        c2.set(Calendar.SECOND,59);
        HrOpenDutyDt last_trans=sessionBean.getLastEmpTransaction(hrOpenDutyExpectedLocDt.getHrOpenDutyHd().getEmpId().getEmpNo(), c1.getTime(),c2.getTime(),sessionBean.getLoactionByIp(getIpAddress()).getId());
        if(last_trans==null)
            hrOpenDutyDt.setTrnsType("I");
        else if(last_trans.getTrnsType().equals("I"))
            hrOpenDutyDt.setTrnsType("O");
        else if(last_trans.getTrnsType().equals("O"))
            hrOpenDutyDt.setTrnsType("I");
        if(external)
            hrOpenDutyDt.setExternal(new Character('Y'));
        external=false;
        sessionBean.persist_Hr_Open_Duty_Dt(hrOpenDutyDt);
        hrOpenDutyDts.add(hrOpenDutyDt);
    }
    public List<HrOpenDutyDt> getHrOpenDutyDts() {
        Calendar c1=Calendar.getInstance();
        Calendar c2=Calendar.getInstance();
        c1.set(Calendar.HOUR, 0);
        c1.set(Calendar.MINUTE,0);
        c1.set(Calendar.SECOND,0);
        c2.set(Calendar.HOUR, 23);
        c2.set(Calendar.MINUTE,59);
        c2.set(Calendar.SECOND,59);
        hrOpenDutyDts=sessionBean.getTodayTransactions(c1.getTime(),c2.getTime());
        return hrOpenDutyDts;
    }

    public void setHrOpenDutyDts(List<HrOpenDutyDt> hrOpenDutyDts) {
        this.hrOpenDutyDts = hrOpenDutyDts;
    }

    public void setExpectedDutyEmp(List<HrOpenDutyExpectedLocDt> expectedDutyEmp) {
        this.expectedDutyEmp = expectedDutyEmp;
    }

    public boolean isExternal() {
        System.out.println("external"+external);
        return external;
    }

    public void setExternal(boolean external) {
        this.external = external;
    }

    public void vcl(ValueChangeEvent e)
    {
//        System.out.println("ex"+e.getNewValue());
//        if(e.getNewValue().equals("true"))
//        external=true;
//        else
//        external=false;
    }

}
