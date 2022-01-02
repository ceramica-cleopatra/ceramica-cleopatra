
import e.HrEmpInfo;
import e.HrProfileAccessLog;
import e.HrProfileMsg;
import e.HrTamyozDt;
import e.HrTamyozHd;
import e.HrUsers;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.primefaces.context.RequestContext;
import sb.SessionBeanLocal;

/**
 *
 * @author ahmed abbas
 */
@ManagedBean
@ViewScoped
public class daily_tamyoz_entry implements Serializable {

    @EJB
    private SessionBeanLocal sessionBean;
    private Long emp_no;
    private Long amount;
    private HrEmpInfo empData;
    private HrTamyozHd hrTamyozHd;
    private List<HrTamyozDt> tamyoz_emp_list = new ArrayList<HrTamyozDt>();
    private String location;
    private Long usercode;
    private Date trnsDate;
    private List<Long> chkEmpExistList = new ArrayList<Long>();
    private Long emp_sum = 0L;
    private int emp_cnt = 0;
    private HrEmpInfo hrEmpInfo;
    private List<HrEmpInfo> hrEmpInfoList;
    private Map<Long, HrEmpInfo> empMap;
    private List<HrTamyozHd> dailyTamyozEntry = new ArrayList<HrTamyozHd>();

    @PostConstruct
    public void init() {
        usercode = Long.parseLong((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usercode"));
        hrEmpInfo = (HrEmpInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrEmpInfo");
        HrUsers hrUsers = (HrUsers) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrUsers");
        if (hrUsers != null) {
            HrProfileAccessLog hrProfileAccessLog = new HrProfileAccessLog();
            hrProfileAccessLog.setEmpNo(hrEmpInfo.getEmpNo());
            hrProfileAccessLog.setPageName(FacesContext.getCurrentInstance().getViewRoot().getViewId());
            hrProfileAccessLog.setTrnsDate(new Date());
            hrProfileAccessLog.setUserName(hrUsers.getUserName());
            sessionBean.persistProfileAccessLog(hrProfileAccessLog);
        }
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -3);
        hrEmpInfoList = sessionBean.allHrEmpInfo();
        empMap = new HashMap<Long, HrEmpInfo>();
        for (HrEmpInfo emp : hrEmpInfoList) {
            empMap.put(emp.getEmpNo(), emp);
        }
        hrTamyozHd = new HrTamyozHd();
        try {
            dailyTamyozEntry = sessionBean.getEmpDailyTamyozEntry(c.getTime(), hrEmpInfo.getLocId());
        } catch (NullPointerException e) {
        }
    }

    public void delete_row(ActionEvent ae) {
        int row = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("indx").toString());
        HrTamyozDt htd = tamyoz_emp_list.remove(row);
        Boolean x = chkEmpExistList.remove(htd.getEmpId().getEmpNo());
        emp_cnt = tamyoz_emp_list.size();
        emp_sum = emp_sum - htd.getAmount();
    }



    public void delete_row1(ActionEvent ae)
    {
    Long row=Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("del_row").toString());
    HrTamyozDt htd=sessionBean.findHrTamyozDtById(row);
    sessionBean.removeHrTamyozDt(sessionBean.mergeHrTamyozDt(htd));
    Calendar c=Calendar.getInstance();
    c.add(Calendar.DATE,-3);
    dailyTamyozEntry=sessionBean.getEmpDailyTamyozEntry(c.getTime(), hrEmpInfo.getLocId());
    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ Õ–› «·„ÊŸ› »‰Ã«Õ");
    FacesContext.getCurrentInstance().addMessage(null, msg);
    }



    public void add_emp(ActionEvent ae) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -3);
        if (hrTamyozHd.getTrnsDate() == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· «· «—ÌŒ");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        /*if (hrTamyozHd.getTrnsDate().before(c.getTime())) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·« Ì„ﬂ‰ﬂ ≈œŒ«· «· „Ì“ » «—ÌŒ ﬁ»· ÌÊ„Ì‰ „«÷ÌÌ‰");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }*/
        if (emp_no == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· ﬂÊœ «·„ÊŸ›");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        if (emp_no != null && !empMap.containsKey(emp_no)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ﬂÊœ «·„ÊŸ› €Ì— ’ÕÌÕ");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        
        if(!sessionBean.chkEmpAttendance(hrTamyozHd.getTrnsDate(), emp_no)){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "«·„ÊŸ› ·„ ÌÕ÷— «·Ï «·„Êﬁ⁄ ›Ï Â–« «·ÌÊ„");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }

        if (sessionBean.chkEmpDailyTamyoz(emp_no, hrTamyozHd.getTrnsDate()) > 0L) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "Â–« «·„ÊŸ› Õ’· ⁄·Ï «· „Ì“ ·Â–« «·ÌÊ„ „‰ „Êﬁ⁄ «Œ—");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        if (amount == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· ﬁÌ„… «· „Ì“");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        } else if (empMap.get(emp_no).getJobGrpId() == 4L && amount != null && amount > 10) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "«·Õœ «·√ﬁ’Ï ·ﬁÌ„… «· „Ì“ »«·‰”»… ··⁄«„· 10 Ã‰ÌÂ« ");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        } else if ((empMap.get(emp_no).getJobGrpId() == 5L || empMap.get(emp_no).getJobGrpId() == 3L) && amount != null && amount > 15) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "«·Õœ «·√ﬁ’Ï ·ﬁÌ„… «· „Ì“ »«·‰”»… ··›‰Ï Ê «·”«∆ﬁ 15 Ã‰ÌÂ«");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        } else if (amount != null && amount > 20) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "«·Õœ «·√ﬁ’Ï ·ﬁÌ„… «· „Ì“ »«·‰”»… ··„ÊŸ›Ì‰ 20 Ã‰ÌÂ«");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        if (chkEmpExistList.contains(emp_no)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", " „ ≈÷«›… Â–« «·„ÊŸ› „‰ ﬁ»·");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        HrTamyozDt hrTamyozDt = new HrTamyozDt();
        chkEmpExistList.add(emp_no);
        hrTamyozDt.setEmpId(empData);
        hrTamyozDt.setAmount(amount);
        tamyoz_emp_list.add(hrTamyozDt);
        emp_cnt = tamyoz_emp_list.size();
        emp_sum = emp_sum + amount;
        empData = null;
        emp_no = null;
        amount = null;

    }

    public void add_emp1(ActionEvent ae) {
        Long row = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("indx1").toString());
        HrTamyozHd hth = sessionBean.getHrTamyozHdById(row);
        List<Long> l = sessionBean.findTamyozEmpsByHdId(row);
        if (emp_no == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· ﬂÊœ «·„ÊŸ›");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        if (emp_no == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· ﬂÊœ «·„ÊŸ›");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        if (emp_no != null && !empMap.containsKey(emp_no)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ﬂÊœ «·„ÊŸ› €Ì— ’ÕÌÕ");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        if(!sessionBean.chkEmpAttendance(hth.getTrnsDate(), emp_no)){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "«·„ÊŸ› ·„ ÌÕ÷— «·Ï «·„Êﬁ⁄ ›Ï Â–« «·ÌÊ„");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        if (sessionBean.chkEmpDailyTamyoz(emp_no, hth.getTrnsDate()) > 0L) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "Â–« «·„ÊŸ› Õ’· ⁄·Ï «· „Ì“ ·Â–« «·ÌÊ„ „‰ „Êﬁ⁄ «Œ—");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        if (amount == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· ﬁÌ„… «· „Ì“");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        } else if (empMap.get(emp_no).getJobGrpId() == 4L && amount != null && amount > 10) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "«·Õœ «·√ﬁ’Ï ·ﬁÌ„… «· „Ì“ »«·‰”»… ··⁄«„· 10 Ã‰ÌÂ« ");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        } else if ((empMap.get(emp_no).getJobGrpId() == 5L || empMap.get(emp_no).getJobGrpId() == 3L) && amount != null && amount > 15) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "«·Õœ «·√ﬁ’Ï ·ﬁÌ„… «· „Ì“ »«·‰”»… ··›‰Ï Ê «·”«∆ﬁ 15 Ã‰ÌÂ«");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        } else if (amount != null && amount > 20) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "«·Õœ «·√ﬁ’Ï ·ﬁÌ„… «· „Ì“ »«·‰”»… ··„ÊŸ›Ì‰ 20 Ã‰ÌÂ«");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        if (l.contains(emp_no)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", " „ ≈÷«›… Â–« «·„ÊŸ› „‰ ﬁ»·");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        HrTamyozDt hrTamyozDt = new HrTamyozDt();
        hrTamyozDt.setEmpId(empData);
        hrTamyozDt.setAmount(amount);
        hrTamyozDt.setHrTamyozHd(hth);
        sessionBean.persistHrTamyozDt(hrTamyozDt);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -3);
        dailyTamyozEntry = sessionBean.getEmpDailyTamyozEntry(c.getTime(), hrEmpInfo.getLocId());
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ ≈÷«›… «·„ÊŸ› »‰Ã«Õ");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        empData = null;
        emp_no = null;
        amount = null;

    }

    public void empChangeListner(AjaxBehaviorEvent a) {
        if (emp_no != null) {
            empData = empMap.get(emp_no);
        } else {
            empData = new HrEmpInfo();
            amount = null;
        }
    }

    public void save(ActionEvent ae) throws JMSException {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -3);
        if (hrTamyozHd.getTrnsDate() == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· «· «—ÌŒ");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        /*if (hrTamyozHd.getTrnsDate().before(c.getTime())) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·« Ì„ﬂ‰ﬂ ≈œŒ«· «· „Ì“ » «—ÌŒ ﬁ»· ÌÊ„Ì‰ „«÷ÌÌ‰");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }*/
        if (tamyoz_emp_list.isEmpty()) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· «·„ÊŸ›Ì‰ √Ê·«");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
        hrTamyozHd.setMonth(Long.parseLong(sdf1.format(hrTamyozHd.getTrnsDate())));
        hrTamyozHd.setTrnsYear(Long.parseLong(sdf2.format(hrTamyozHd.getTrnsDate())));
        hrTamyozHd.setLocationId(hrEmpInfo.getLocId());
        hrTamyozHd.setTamyozEntry(usercode);
        hrTamyozHd.setManageId(1L);
        hrTamyozHd.setRewardType(7L);
        sessionBean.persistHrTamyozHd(hrTamyozHd);
        hrTamyozHd = sessionBean.getPersistHrTamyozHd(hrTamyozHd.getTrnsDate(), hrTamyozHd.getLocationId(), hrTamyozHd.getTamyozEntry());
        for (HrTamyozDt hrTamyozDt : tamyoz_emp_list) {
            hrTamyozDt.setHrTamyozHd(hrTamyozHd);
            sessionBean.persistHrTamyozDt(hrTamyozDt);
        }



        if (CashHandler.getTamyozApprove() != null
                    && CashHandler.getTamyozApprove().get(hrTamyozHd.getLocationId()) != null && CashHandler.getTamyozApprove().get(hrTamyozHd.getLocationId()).size()>0
                    && CashHandler.getTamyozApprove().get(hrTamyozHd.getLocationId()).get(1L)!=null
                    && CashHandler.getTamyozApprove().get(hrTamyozHd.getLocationId()).get(1L).size()>0
                    && (hrTamyozHd.getApproved()==null || hrTamyozHd.getApproved().isEmpty())) {
                System.out.println("inside first if");
                Connection connection=null;
                Session session=null;
                MessageProducer messageProducer=null;
                try {
                    Context ctx = new InitialContext();
                    ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("jms/profileMsgQueueFactory");
                    Queue queue = (Queue) ctx.lookup("jms/profileMsgQueue");
                    connection = connectionFactory.createConnection();
                    session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                    List<Long> empApprove = CashHandler.getTamyozApprove().get(hrTamyozHd.getLocationId()).get(1L);
                    System.out.println("empApprove" + empApprove);
                    messageProducer = session.createProducer(queue);
                    for (Long emp : empApprove) {
                        ObjectMessage objectMessage = session.createObjectMessage();
                        HrProfileMsg hrProfileMsg = new HrProfileMsg();
                        hrProfileMsg.setEntityName("HrTamyozHd");
                        hrProfileMsg.setSendDate(new Date());
                        hrProfileMsg.setEmpNo(emp);
                        hrProfileMsg.setSenderNo(usercode);
                        hrProfileMsg.setMsgId(hrTamyozHd.getId());
                        hrProfileMsg.setMsgType(1L);
                        objectMessage.setObject(hrProfileMsg);
                        messageProducer.send(objectMessage);
                        if (CashHandler.getAlerts().containsKey(emp)) {
                            CashHandler.getAlerts().get(emp).add(hrProfileMsg);
                        } else {
                            List<HrProfileMsg> hrProfileMsgs = new ArrayList<HrProfileMsg>();
                            hrProfileMsgs.add(hrProfileMsg);
                            CashHandler.getAlerts().put(emp, hrProfileMsgs);
                        }
                        System.out.println("message sent");
                    }
                } catch (NamingException ex) {
                    ex.printStackTrace();
                } catch (JMSException x) {
                    x.printStackTrace();
                }finally{
                    try{
                    messageProducer.close();
                    }catch(Exception e){
                        e.printStackTrace();
                    }try{
                    session.close();
                    }catch(Exception e){
                        e.printStackTrace();
                    }try{
                    connection.close();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }

            }


        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ Õ›Ÿ «· „Ì“ »‰Ã«Õ");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        tamyoz_emp_list.clear();
        chkEmpExistList.clear();
        amount = null;
        emp_cnt = 0;
        emp_sum = 0L;
        emp_no = 0L;
        empData = new HrEmpInfo();
        hrTamyozHd = new HrTamyozHd();
        emp_no = null;
    }

    public Long getEmp_no() {
        return emp_no;
    }

    public HrEmpInfo getEmpData() {
        return empData;
    }

    public void setEmpData(HrEmpInfo empData) {
        this.empData = empData;
    }

    public void setEmp_no(Long emp_no) {
        this.emp_no = emp_no;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public List<HrTamyozHd> getDailyTamyozEntry() {
        return dailyTamyozEntry;
    }

    public void setDailyTamyozEntry(List<HrTamyozHd> dailyTamyozEntry) {
        this.dailyTamyozEntry = dailyTamyozEntry;
    }

    public int getEmp_cnt() {
        return emp_cnt;
    }

    public void setEmp_cnt(int emp_cnt) {
        this.emp_cnt = emp_cnt;
    }

    public Long getEmp_sum() {
        return emp_sum;
    }

    public void setEmp_sum(Long emp_sum) {
        this.emp_sum = emp_sum;
    }

    public void setUsercode(Long usercode) {
        this.usercode = usercode;
    }

    public String getLocation() {
        location = hrEmpInfo.getLocation();
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public daily_tamyoz_entry() {
    }

    public HrTamyozHd getHrTamyozHd() {
        return hrTamyozHd;
    }

    public void setHrTamyozHd(HrTamyozHd hrTamyozHd) {
        this.hrTamyozHd = hrTamyozHd;
    }

    public List<HrTamyozDt> getTamyoz_emp_list() {
        return tamyoz_emp_list;
    }

    public void setTamyoz_emp_list(List<HrTamyozDt> tamyoz_emp_list) {
        this.tamyoz_emp_list = tamyoz_emp_list;
    }
}
