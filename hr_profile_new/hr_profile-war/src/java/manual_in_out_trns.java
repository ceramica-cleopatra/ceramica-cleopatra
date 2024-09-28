/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpHd;
import e.HrEmpInfo;
import e.HrLocation;
import e.HrManualInOutTrns;
import e.HrProfileAccessLog;
import e.HrUsers;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import sb.SessionBeanLocal;

/**
 *
 * @author ahmed abbas
 */
@ManagedBean
@SessionScoped
public class manual_in_out_trns {

    @EJB
    private SessionBeanLocal sessionBean;
    private List<HrManualInOutTrns> hrManualInTrnsList = new ArrayList<HrManualInOutTrns>();
    private List<HrManualInOutTrns> hrManualOutTrnsList = new ArrayList<HrManualInOutTrns>();
    private String usercode;
    private FacesContext fc;
    private Long ip;
    private Long in_emp_no;
    private Long out_emp_no;
    private String in_emp_name;
    private String out_emp_name;
    private Map<Long,String> lastTrans;
    private Long actualLocation;
    @PostConstruct
    public void init() {
        fc = FacesContext.getCurrentInstance();
        usercode = (String) fc.getExternalContext().getSessionMap().get("usercode");
        HrUsers hrUsers = (HrUsers) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrUsers");
        if (hrUsers != null) {
            HrProfileAccessLog hrProfileAccessLog = new HrProfileAccessLog();
            hrProfileAccessLog.setEmpNo(Long.parseLong(usercode));
            hrProfileAccessLog.setPageName(FacesContext.getCurrentInstance().getViewRoot().getViewId());
            hrProfileAccessLog.setTrnsDate(new Date());
            hrProfileAccessLog.setUserName(hrUsers.getUserName());
            sessionBean.persistProfileAccessLog(hrProfileAccessLog);
        }
        Date d1 = new Date();
        d1.setHours(0);
        d1.setMinutes(0);
        d1.setSeconds(0);
        d1.setTime(d1.getTime()-(1000*60*60*24));
        Date d2 = new Date();
        d2.setHours(23);
        d2.setMinutes(59);
        d2.setSeconds(59);
        Date d3 = new Date();
        d3.setHours(0);
        d3.setMinutes(0);
        d3.setSeconds(0);
        FacesContext fc = FacesContext.getCurrentInstance();
        lastTrans=new HashMap<Long,String>();
        ip=(getIpAddress()==null || getIpAddress().isEmpty() ? 0L : Long.parseLong(getIpAddress()));
        List<HrLocation> hrLocations=sessionBean.getLoactionByIp(ip);
        if(hrLocations!=null && !hrLocations.isEmpty())
            actualLocation=hrLocations.get(0).getId();
        List<HrManualInOutTrns> list=sessionBean.findAllInOutTrns(d1, d2, ip);
        hrManualInTrnsList = sessionBean.getManualInTrns(d3, d2, Long.parseLong(usercode));
        hrManualOutTrnsList = sessionBean.getManualOutTrns(d3, d2, Long.parseLong(usercode));
        for(HrManualInOutTrns hrManualInOutTrns : list){
            if(!lastTrans.containsKey(hrManualInOutTrns.getHrEmpHd().getId()))
                lastTrans.put(hrManualInOutTrns.getHrEmpHd().getId(), hrManualInOutTrns.getTrnsType());
        }
        
    }

    public void findInEmpName() {
//        if (CashHandler.getInOutManualTrnsMap().get(Long.parseLong(usercode)).containsKey(in_emp_no)) {
//            in_emp_name = CashHandler.getInOutManualTrnsMap().get(Long.parseLong(usercode)).get(in_emp_no);
//        } else {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·« ÌÊÃœ „ÊŸ› œ«Œ· «·„Êﬁ⁄ »Â–« «·ﬂÊœ"));
//            in_emp_name=null;
//        }
    }


    public void findOutEmpName() {
//        if (CashHandler.getInOutManualTrnsMap().get(Long.parseLong(usercode)).containsKey(out_emp_no)) {
//            out_emp_name = CashHandler.getInOutManualTrnsMap().get(Long.parseLong(usercode)).get(out_emp_no);
//        } else {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·« ÌÊÃœ „ÊŸ› œ«Œ· «·„Êﬁ⁄ »Â–« «·ﬂÊœ"));
//            out_emp_name=null;
//        }
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


    public void add_in_emp(ActionEvent ae) {

        if (in_emp_no==null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Œÿ√", "ÌÃ» ≈œŒ«· ﬂÊœ «·„ÊŸ›"));
            return;
        }

        if (in_emp_name==null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Œÿ√", "·« ÌÊÃœ „ÊŸ› ›Ï Â–« «·„Êﬁ⁄ »Â–« «·ﬂÊœ"));
            return;
        }

        if (lastTrans.containsKey(in_emp_no) && lastTrans.get(in_emp_no).equals("A1")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Œÿ√", "√Œ— Õ—ﬂ… „”Ã·… ··„ÊŸ› Õ÷Ê— ·« Ì„ﬂ‰ﬂ ≈ „«„ «·⁄„·Ì…"));
            return;
        }
        HrManualInOutTrns hrManualInOutTrns = new HrManualInOutTrns();
        hrManualInOutTrns.setTrnsDate(new Date());
        HrEmpHd hrEmpHd = new HrEmpHd();
        hrEmpHd.setId(in_emp_no);
        hrEmpHd.setName(in_emp_name);
        hrManualInOutTrns.setHrEmpHd(hrEmpHd);
        hrManualInOutTrns.setTrnsType("A1");
        hrManualInOutTrns.setUserId(Long.parseLong(usercode));
        hrManualInOutTrns.setLocId(actualLocation);
        sessionBean.persistHrManualInOutTrns(hrManualInOutTrns);
        hrManualInTrnsList.add(0,hrManualInOutTrns);
        lastTrans.put(in_emp_no,"A1");
        in_emp_name=null;
        in_emp_no=null;
    }

    public void add_out_emp(ActionEvent ae) {
        if (out_emp_no == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Œÿ√", "ÌÃ» ≈œŒ«· ﬂÊœ «·„ÊŸ›"));
            return;
        }
        if (out_emp_name==null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Œÿ√", "·« ÌÊÃœ „ÊŸ› ›Ï Â–« «·„Êﬁ⁄ »Â–« «·ﬂÊœ"));
            return;
        }

        if (lastTrans.containsKey(out_emp_no) && lastTrans.get(out_emp_no).equals("A2")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Œÿ√", "√Œ— Õ—ﬂ… „”Ã·… ·Â–« «·„ÊŸ› ≈‰’—«›. ·« Ì„ﬂ‰ﬂ ≈ „«„ «·⁄„·Ì…"));
            return;
        }
        
        HrManualInOutTrns hrManualInOutTrns = new HrManualInOutTrns();
        hrManualInOutTrns.setTrnsDate(new Date());
        HrEmpHd hrEmpHd = new HrEmpHd();
        hrEmpHd.setId(out_emp_no);
        hrEmpHd.setName(out_emp_name);
        hrManualInOutTrns.setHrEmpHd(hrEmpHd);
        hrManualInOutTrns.setTrnsType("A2");
        hrManualInOutTrns.setUserId(Long.parseLong(usercode));
        hrManualInOutTrns.setLocId(actualLocation);
        sessionBean.persistHrManualInOutTrns(hrManualInOutTrns);
        hrManualOutTrnsList.add(0,hrManualInOutTrns);
        lastTrans.put(out_emp_no,"A2");
        out_emp_name=null;
        out_emp_no=null;
    }


    /** Creates a new instance of manual_in_out_trns */
    public manual_in_out_trns() {
    }
    

    public List<HrManualInOutTrns> getHrManualInTrnsList() {
        return hrManualInTrnsList;
    }

    public void setHrManualInTrnsList(List<HrManualInOutTrns> hrManualInTrnsList) {
        this.hrManualInTrnsList = hrManualInTrnsList;
    }

    public List<HrManualInOutTrns> getHrManualOutTrnsList() {
        return hrManualOutTrnsList;
    }

    public void setHrManualOutTrnsList(List<HrManualInOutTrns> hrManualOutTrnsList) {
        this.hrManualOutTrnsList = hrManualOutTrnsList;
    }

    public String getIn_emp_name() {
        return in_emp_name;
    }

    public void setIn_emp_name(String in_emp_name) {
        this.in_emp_name = in_emp_name;
    }

    public Long getIn_emp_no() {
        return in_emp_no;
    }

    public void setIn_emp_no(Long in_emp_no) {
        this.in_emp_no = in_emp_no;
    }

    public String getOut_emp_name() {
        return out_emp_name;
    }

    public void setOut_emp_name(String out_emp_name) {

        this.out_emp_name = out_emp_name;
    }

    public Long getOut_emp_no() {
        return out_emp_no;
    }

    public void setOut_emp_no(Long out_emp_no) {
        this.out_emp_no = out_emp_no;
    }

    
}
