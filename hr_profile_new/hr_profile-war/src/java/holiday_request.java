/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrEmpMangers;
import e.HrHolidayRequest;
import e.HrHolidayType;
import e.HrMenuTable;
import e.HrMontlySalaryCalcPeriod;
import e.HrProfileAccessLog;
import e.HrProfileMsg;
import e.HrUsers;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.model.SelectItem;
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
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import sb.SessionBeanLocal;

/**
 *
 * @author ahmed abbas
 */
@ManagedBean
@ViewScoped
public class holiday_request implements Serializable {

    @EJB
    private SessionBeanLocal sessionBean;
    /** Creates a new instance of holiday_request */
    private String usercode;
    private HrHolidayRequest hrHolidayRequest = new HrHolidayRequest();
    private List<Object[]> hol_req_list;
    private List<Object[]> hol_type;
    private HrEmpInfo info = new HrEmpInfo();
    private HrHolidayType hol_typ = new HrHolidayType();
    private List<SelectItem> hol_type_list;
    private List<SelectItem> alternativeEmp;
    private List<HrEmpInfo> alternatives_list;
    private Date min_date;
    private Boolean x = true;
    private Boolean y = false;
    private Date to_date;
    private Date from_date;
    private Long empNo;
    private HrHolidayRequest d;
    private HrHolidayRequest hhr;
    private List<HrHolidayRequest> hrHolidayRequestList;
    private FacesMessage fm = new FacesMessage();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    private SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
    private SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
    private HrEmpInfo hrEmpInfo;
    private boolean hasTransaction = true;
    Calendar c;

    @PostConstruct
    public void init() {
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
        HrMontlySalaryCalcPeriod hscp = CashHandler.hscp;
        usercode = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usercode");
        min_date = hscp.getDateFrom();
        try {
            hrHolidayRequestList = sessionBean.find_holiday_request(Long.parseLong(usercode), min_date);
        } catch (NullPointerException e) {
            hrHolidayRequestList = new ArrayList<HrHolidayRequest>();
        }
        try {
            alternatives_list = sessionBean.findAlternativeEmp(Long.parseLong(usercode), hrEmpInfo.getDeptId(), hrEmpInfo.getLocId());
        } catch (NullPointerException e) {
            alternatives_list = new ArrayList<HrEmpInfo>();
        }
        alternativeEmp = new ArrayList<SelectItem>();
        for (HrEmpInfo hrEmpInfo : alternatives_list) {
            alternativeEmp.add(new SelectItem(String.valueOf(hrEmpInfo.getEmpNo()), hrEmpInfo.getEmpName()));
        }


        hol_type = sessionBean.find_holiday_types();
        hol_type_list = new ArrayList<SelectItem>();
        for (Object[] object : hol_type) {
            hol_type_list.add(new SelectItem(object[0], object[1].toString()));
        }

        if (CashHandler.getMsgs().get(Long.parseLong(usercode)) != null && CashHandler.getMsgs().get(Long.parseLong(usercode)).size() > 0) {
            for (HrProfileMsg hrProfileMsg : CashHandler.getMsgs().get(Long.parseLong(usercode))) {
                if (hrProfileMsg.getEntityName().equals("HrHolidayRequest")) {
                    hrProfileMsg.setReadDone('Y');
                }
            }
            sessionBean.updateReadDoneMsg("HrHolidayRequest", null, 'Y', Long.parseLong(usercode));
        }
    }

    public void update(RowEditEvent event) {
        Date d1;
        Date d2;
        Long chk_result;
        java.sql.Timestamp timestamp;
        hhr = (HrHolidayRequest) event.getObject();
        chk_result = sessionBean.chk_holiday_request(Long.parseLong(usercode), hhr.getFromDate(), hhr.getToDate(), hhr.getHolidayType().getId(), hhr.getId());
        d1 = (Date) hhr.getFromDate();
        d2 = (Date) hhr.getToDate();
        if (hhr.getMngConfirm() != null) {
            fm.setSummary("Œÿ√");
            fm.setDetail(" „ ≈⁄ „«œ «·√Ã«“… .. ·« Ìﬂ‰ﬂ «· ⁄œÌ·");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
            reset_req_list();
            return;
        }
        if (d2 != null && d2 != null && d1.after(d2)) {
            fm.setSummary("Œÿ√");
            fm.setDetail("ÌÃ» √‰ ÌﬂÊ‰  «—ÌŒ »œ¡ «·√Ã«“… ﬁ»·  «—ÌŒ «·≈‰ Â«¡");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
            reset_req_list();
            return;
        }
        if (sdf.format(d1).equals(sdf.format(d2)) == false) {
            fm.setSummary("Œÿ√");
            fm.setDetail("ÌÃ» √‰ ÌﬂÊ‰  «—ÌŒ »œ¡ «·√Ã«“… Ê «—ÌŒ «·≈‰ Â«¡ Œ·«· ‰›” «·”‰…");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
            reset_req_list();
            return;
        }
        if (chk_result.equals(1L)) {
            hrHolidayRequestList = sessionBean.find_holiday_request(Long.parseLong(usercode), min_date);
            fm.setSummary("Œÿ√");
            fm.setDetail("—’Ìœﬂ «·Õ«·Ï ·« Ìﬂ›Ï ··Õ’Ê· ⁄·Ï √Ã«“… ·Â–Â «·„œÂ");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
            reset_req_list();
            return;
        }
        if ((d1.before(new Date()) || d1.equals(new Date())) && hhr.getHolidayType().getId() != 10L) {
            hrHolidayRequestList = sessionBean.find_holiday_request(Long.parseLong(usercode), min_date);
            fm.setSummary("Œÿ√");
            fm.setDetail("ÿ·» «·√Ã«“… ÌÃ» √‰ ÌﬂÊ‰ ﬁ»·  «—ÌŒ »œ¡ «·√Ã«“…");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
            reset_req_list();
            return;
        }
        Date d = new Date();
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH");
        SimpleDateFormat sdf3 = new SimpleDateFormat("MM");
        if (sdf1.format(d.getTime() + (1000 * 60 * 60 * 24)).equals(sdf1.format(d1)) && hhr.getHolidayType().getId() != 10L && Long.parseLong(sdf2.format(d)) > 23L) {
            hrHolidayRequestList = sessionBean.find_holiday_request(Long.parseLong(usercode), min_date);
            fm.setSummary("Œÿ√");
            fm.setDetail("√Œ— „Ì⁄œ · ﬁœÌ„ «·√Ã«“… «·”«⁄… «·Õ«œÌ… ⁄‘—");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
            reset_req_list();
            return;
        }
        if (hhr.getHolidayType().getId() == 10L) {
            if (chk_result.equals(2L)) {
                hrHolidayRequestList = sessionBean.find_holiday_request(Long.parseLong(usercode), min_date);
                fm.setSummary("Œÿ√");
                fm.setDetail("·« Ì„ﬂ‰ﬂ «·Õ’Ê· ⁄·Ï «ﬂÀ— „‰ ÌÊ„Ì‰ √Ã«“… ⁄«—÷… Œ·«· ‰›” «·‘Â—");
                fm.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, fm);
                reset_req_list();
                return;
            }
            if ((((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24)) + 1) > 2) {
                hrHolidayRequestList = sessionBean.find_holiday_request(Long.parseLong(usercode), min_date);
                fm.setSummary("Œÿ√");
                fm.setDetail("·« Ì„ﬂ‰ﬂ ÿ·» √ﬂÀ— „‰ ÌÊ„Ì‰ √Ã«“… ⁄«—÷… ›Ï ‰›” «·ÿ·»");
                fm.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, fm);
                reset_req_list();
                return;
            }

        }
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 25);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        Calendar ccc = Calendar.getInstance();
        ccc.set(Calendar.DAY_OF_MONTH, 15);
        ccc.set(Calendar.HOUR_OF_DAY, 23);
        ccc.set(Calendar.MINUTE, 59);
        ccc.set(Calendar.SECOND, 59);


        if (c.before(Calendar.getInstance()) && hhr.getFromDate().before(ccc.getTime())) {
            hrHolidayRequestList = sessionBean.find_holiday_request(Long.parseLong(usercode), min_date);
            fm.setSummary("Œÿ√");
            fm.setDetail("·« Ì„ﬂ‰ ≈œŒ«· ÿ·» √Ã«“… Œ·«· Â–« «·‘Â— »”»» œŒÊ· «·„— »«  ··„—«Ã⁄…");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
            hrHolidayRequestList = sessionBean.find_holiday_request(Long.parseLong(usercode), min_date);

            return;
        }


        if (chk_result.equals(3L)) {
            hrHolidayRequestList = sessionBean.find_holiday_request(Long.parseLong(usercode), min_date);
            fm.setSummary("Œÿ√");
            fm.setDetail("·« Ì„ﬂ‰ ≈œŒ«· √Ã«“… ›Ï ‘Â— ﬁœÌ„");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
            reset_req_list();
            return;
        }

        c.setTime(d1);
        Calendar cc = Calendar.getInstance();
        cc.add(Calendar.MONTH, -2);
        if (c.before(cc)) {
            FacesMessage fm = new FacesMessage("Œÿ√", "·« Ì„ﬂ‰ ≈œŒ«· ≈–‰ ›Ï ‘Â— ﬁœÌ„");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
            return;
        }
        if (chk_result.equals(4L)) {
            hrHolidayRequestList = sessionBean.find_holiday_request(Long.parseLong(usercode), min_date);
            fm.setSummary("Œÿ√");
            fm.setDetail("› —… «·√Ã«“…  Õ ÊÏ ⁄·Ï €Ì«» »√–‰ √Ê »œÊ‰ √–‰ .. ·« Ì„ﬂ‰  ÿ»Ìﬁ «·√Ã«“…");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
            reset_req_list();
            return;
        }
        if (chk_result.equals(5L)) {
            hrHolidayRequestList = sessionBean.find_holiday_request(Long.parseLong(usercode), min_date);
            fm.setSummary("Œÿ√");
            fm.setDetail(" „  ”ÃÌ· Â–Â «·√Ã«“… „‰ ﬁ»· .. »—Ã«¡ ≈·€«¡ «·”«»ﬁ… √Ê·«");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
            reset_req_list();
            return;
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.addCallbackParam("show", false);
        timestamp = new java.sql.Timestamp(d1.getTime());
        hhr.setFromDate(timestamp);
        timestamp = new java.sql.Timestamp(d2.getTime());
        hhr.setToDate(timestamp);
        HrHolidayType x = new HrHolidayType();
        x.setName(sessionBean.find_holiday_By_Id(hhr.getHolidayType().getId()).getName());
        x.setId(hhr.getHolidayType().getId());
        hhr.setHolidayType(x);
        hhr.setDaysNo(((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24)) + 1);
        sessionBean.mergeHrHolidayRequest(hhr);
        fm.setSummary(" „ »‰Ã«Õ");
        fm.setDetail(" „ Õ›Ÿ «· ⁄œÌ· »‰Ã«Õ");
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, fm);
        hasTransaction = false;
        for (HrEmpMangers hrEmpMangers : CashHandler.getEmpManagers().get(Long.parseLong(usercode))) {
            if (CashHandler.getAlerts().get(hrEmpMangers.getMngNo()) != null && CashHandler.getAlerts().get(hrEmpMangers.getMngNo()).size() > 0) {
                for (HrProfileMsg hrProfileMsg : CashHandler.getAlerts().get(hrEmpMangers.getMngNo())) {
                    if (hrProfileMsg.getEntityName().equals("HrHolidayRequest") && hrProfileMsg.getMsgId().equals(hhr.getId()) && hhr.getCanceled().equals(new Character('Y'))) {
                        hrProfileMsg.setReadDone('Y');
                    } else if (hrProfileMsg.getEntityName().equals("HrHolidayRequest") && hrProfileMsg.getMsgId().equals(hhr.getId()) && (hhr.getCanceled().equals(new Character('N')) || hhr.getCanceled() == null)) {
                        hrProfileMsg.setReadDone('N');
                    }
                }

            }
        }
        if (hhr.getCanceled().equals(new Character('Y'))) {
            sessionBean.updateReadDoneMsg("HrHolidayRequest", hhr.getId(), 'Y', null);
        } else {
            sessionBean.updateReadDoneMsg("HrHolidayRequest", hhr.getId(), 'N', null);
        }
        hrHolidayRequestList = sessionBean.find_holiday_request(Long.parseLong(usercode), min_date);
    }

    public void test() {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaa");
    }

    public void add_new_request() {
        if (from_date == null || to_date == null || String.valueOf(hol_typ.getId()).length() == 0 || hrHolidayRequest.getRequestDesc() == null || String.valueOf(empNo).length() == 0) {
            fm.setSummary("Œÿ√");
            fm.setDetail("ÌÃ» ≈œŒ«· Ã„Ì⁄ «·»Ì«‰« ");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
            return;
        }

        try {
            hrHolidayRequest.setHrHoldayType(hol_typ);
            info.setEmpNo(empNo);
            hrHolidayRequest.setAlternativeEmp(info);
            hrHolidayRequest.setDaysNo((Long) ((to_date.getTime() - from_date.getTime()) / (1000 * 60 * 60 * 24)) + 1);
            if (sessionBean.findRequestId() != null) {
                hrHolidayRequest.setId(sessionBean.findRequestId() + 1L);
            } else {
                hrHolidayRequest.setId(1L);
            }
            hrHolidayRequest.setFromDate(new java.sql.Timestamp(from_date.getTime()));
            hrHolidayRequest.setToDate(new java.sql.Timestamp(to_date.getTime()));
            hrHolidayRequest.setEmpId(Long.parseLong(usercode));
            hrHolidayRequest.setTrnsDate(new java.sql.Timestamp(new Date().getTime()));
        } catch (NullPointerException e) {
            fm.setSummary("Œÿ√");
            fm.setDetail("ÌÃ» ≈œŒ«· Ã„Ì⁄ «·»Ì«‰« ");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
            return;
        }
        Long chk_result = sessionBean.chk_holiday_request(Long.parseLong(usercode), hrHolidayRequest.getFromDate(), hrHolidayRequest.getToDate(), hrHolidayRequest.getHrHoldayType().getId(), hrHolidayRequest.getId());
        System.out.println(Long.parseLong(usercode));
        System.out.println(hrHolidayRequest.getFromDate());
        System.out.println(hrHolidayRequest.getToDate());
        System.out.println(hrHolidayRequest.getHrHoldayType().getId());
        System.out.println(hrHolidayRequest.getId());
        if (from_date != null && to_date != null && from_date.after(to_date)) {
            fm.setSummary("Œÿ√");
            fm.setDetail("ÌÃ» √‰ ÌﬂÊ‰  «—ÌŒ »œ¡ «·√Ã«“… ﬁ»·  «—ÌŒ «·≈‰ Â«¡");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
            return;
        }
        if (sdf.format(from_date).equals(sdf.format(to_date)) == false) {
            fm.setSummary("Œÿ√");
            fm.setDetail("ÌÃ» √‰ ÌﬂÊ‰  «—ÌŒ »œ¡ «·√Ã«“… Ê «—ÌŒ «·≈‰ Â«¡ Œ·«· ‰›” «·”‰…");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
            return;
        }
        if (chk_result.equals(1L)) {
            fm.setSummary("Œÿ√");
            fm.setDetail("—’Ìœﬂ «·Õ«·Ï ·« Ìﬂ›Ï ··Õ’Ê· ⁄·Ï √Ã«“… ·Â–Â «·„œÂ");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
            return;
        }
        if ((from_date.before(new Date()) || from_date.equals(new Date())) && hol_typ.getId() != 10L) {
            fm.setSummary("Œÿ√");
            fm.setDetail("ÿ·» «·√Ã«“… ÌÃ» √‰ ÌﬂÊ‰ ﬁ»·  «—ÌŒ »œ¡ «·√Ã«“…");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
            return;
        }
        Date d = new Date();
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH");
        SimpleDateFormat sdf3 = new SimpleDateFormat("MM");
        if (sdf1.format(d.getTime() + (1000 * 60 * 60 * 24)).equals(sdf1.format(from_date)) && hol_typ.getId() != 10L && Long.parseLong(sdf2.format(d)) > 23L) {
            fm.setSummary("Œÿ√");
            fm.setDetail("√Œ— „Ì⁄«œ · ﬁœÌ„ «·√Ã«“… «·”«⁄… «·Õ«œÌ… ⁄‘—");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
            return;
        }
        if (hol_typ.getId() == 10L) {
            if (chk_result.equals(2L)) {
                fm.setSummary("Œÿ√");
                fm.setDetail("·« Ì„ﬂ‰ﬂ «·Õ’Ê· ⁄·Ï «ﬂÀ— „‰ ÌÊ„Ì‰ √Ã«“… ⁄«—÷… Œ·«· ‰›” «·‘Â—");
                fm.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, fm);
                return;
            }
            if ((((to_date.getTime() - from_date.getTime()) / (1000 * 60 * 60 * 24)) + 1) > 2) {
                fm.setSummary("Œÿ√");
                fm.setDetail("·« Ì„ﬂ‰ﬂ ÿ·» √ﬂÀ— „‰ ÌÊ„Ì‰ √Ã«“… ⁄«—÷… ›Ï ‰›” «·ÿ·»");
                fm.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, fm);
                return;
            }
            if (chk_result.equals(2L)) {
                fm.setSummary("Œÿ√");
                fm.setDetail("·« Ì„ﬂ‰ﬂ «·Õ’Ê· ⁄·Ï «ﬂÀ— „‰ ÌÊ„Ì‰ √Ã«“… ⁄«—÷… Œ·«· ‰›” «·‘Â—");
                fm.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, fm);
                return;
            }
        }
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 25);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        Calendar ccc = Calendar.getInstance();
        ccc.set(Calendar.DAY_OF_MONTH, 15);
        ccc.set(Calendar.HOUR_OF_DAY, 23);
        ccc.set(Calendar.MINUTE, 59);
        ccc.set(Calendar.SECOND, 59);

        if (c.before(Calendar.getInstance()) && from_date.before(ccc.getTime())) {
            fm.setSummary("Œÿ√");
            fm.setDetail("·« Ì„ﬂ‰ ≈œŒ«· ÿ·» √Ã«“… Œ·«· Â–« «·‘Â— »”»» œŒÊ· «·„— »«  ··„—«Ã⁄…");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
            return;
        }
        if (chk_result.equals(3L)) {
            fm.setSummary("Œÿ√");
            fm.setDetail("·« Ì„ﬂ‰ ≈œŒ«· √Ã«“… ›Ï ‘Â— ﬁœÌ„");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
            return;
        }
        if (chk_result.equals(4L)) {
            fm.setSummary("Œÿ√");
            fm.setDetail("› —… «·√Ã«“…  Õ ÊÏ ⁄·Ï €Ì«» »√–‰ √Ê »œÊ‰ √–‰ .. ·« Ì„ﬂ‰  ÿ»Ìﬁ «·√Ã«“…");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
            return;
        }
        if (chk_result.equals(5L)) {
            fm.setSummary("Œÿ√");
            fm.setDetail(" „  ”ÃÌ· Â–Â «·√Ã«“… „‰ ﬁ»· .. »—Ã«¡ ≈·€«¡ «·”«»ﬁ… √Ê·«");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
            return;
        }

        if (chk_result.equals(7L)) {
            fm.setSummary("Œÿ√");
            fm.setDetail("„Ã„Ê⁄ «·√Ã«“«  «·„⁄ „œ… Ê «·€Ì— „⁄ „œ…  Ã«Ê“ «·„”„ÊÕ »Â ··√Ã«“… «·”‰ÊÌ…");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
            return;
        }

        if (chk_result.equals(6L)) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('confirmation').show();");
        } else {
            RequestContext context = RequestContext.getCurrentInstance();
            context.addCallbackParam("show", false);
            fm.setSummary(" „ »‰Ã«Õ");
            fm.setDetail(" „ Õ›Ÿ ÿ·» «·√Ã«“… »‰Ã«Õ");
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
            hasTransaction = true;
            sessionBean.persistHrHolidayRequest(hrHolidayRequest);
            Connection connection = null;
            Session session = null;
            MessageProducer messageProducer = null;
            try {
                Context ctx = new InitialContext();
                ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("jms/profileMsgQueueFactory");
                Queue queue = (Queue) ctx.lookup("jms/profileMsgQueue");
                connection = connectionFactory.createConnection();
                session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                messageProducer = session.createProducer(queue);
                for (HrEmpMangers hrEmpMangers : CashHandler.getEmpManagers().get(hrHolidayRequest.getEmpId())) {
                    ObjectMessage objectMessage = session.createObjectMessage();
                    HrProfileMsg hrProfileMsg = new HrProfileMsg();
                    hrProfileMsg.setEntityName("HrHolidayRequest");
                    hrProfileMsg.setSendDate(new Date());
                    hrProfileMsg.setEmpNo(hrEmpMangers.getMngNo());
                    hrProfileMsg.setSenderNo(hrHolidayRequest.getEmpId());
                    hrProfileMsg.setMsgId(hrHolidayRequest.getId());
                    hrProfileMsg.setMsgType(1L);
                    objectMessage.setObject(hrProfileMsg);
                    if (CashHandler.getAlerts().containsKey(hrEmpMangers.getMngNo())) {
                        CashHandler.getAlerts().get(hrEmpMangers.getMngNo()).add(hrProfileMsg);
                    } else {
                        List<HrProfileMsg> hrProfileMsgs = new ArrayList<HrProfileMsg>();
                        hrProfileMsgs.add(hrProfileMsg);
                        CashHandler.getAlerts().put(hrEmpMangers.getMngNo(), hrProfileMsgs);
                    }
                    messageProducer.send(objectMessage);

                }
                System.out.println("message sent");
            } catch (NamingException ex) {
                ex.printStackTrace();
            } catch (JMSException e) {
                e.printStackTrace();
            } finally {
                try {
                    messageProducer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    session.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            from_date = null;
            to_date = null;
            hol_typ = new HrHolidayType();
            hrHolidayRequest = new HrHolidayRequest();
            empNo = null;
        }

        hrHolidayRequestList = sessionBean.find_holiday_request(Long.parseLong(usercode), min_date);

    }

    public void addNewRequestForExistTransactions() {

        try {
            hrHolidayRequest.setHrHoldayType(hol_typ);
            info.setEmpNo(empNo);
            hrHolidayRequest.setAlternativeEmp(info);
            hrHolidayRequest.setDaysNo((Long) ((to_date.getTime() - from_date.getTime()) / (1000 * 60 * 60 * 24)) + 1);
            if (sessionBean.findRequestId() != null) {
                hrHolidayRequest.setId(sessionBean.findRequestId() + 1L);
            } else {
                hrHolidayRequest.setId(1L);
            }
            hrHolidayRequest.setFromDate(new java.sql.Timestamp(from_date.getTime()));
            hrHolidayRequest.setToDate(new java.sql.Timestamp(to_date.getTime()));
            hrHolidayRequest.setEmpId(Long.parseLong(usercode));
            hrHolidayRequest.setTrnsDate(new java.sql.Timestamp(new Date().getTime()));
        } catch (NullPointerException e) {
            fm.setSummary("Œÿ√");
            fm.setDetail("ÌÃ» ≈œŒ«· Ã„Ì⁄ «·»Ì«‰« ");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
            return;
        }
        fm.setSummary(" „ »‰Ã«Õ");
        fm.setDetail(" „ Õ›Ÿ ÿ·» «·√Ã«“… »‰Ã«Õ");
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, fm);
        hasTransaction = true;
        sessionBean.persistHrHolidayRequest(hrHolidayRequest);
        Connection connection = null;
        Session session = null;
        MessageProducer messageProducer = null;
        try {
            Context ctx = new InitialContext();
            ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("jms/profileMsgQueueFactory");
            Queue queue = (Queue) ctx.lookup("jms/profileMsgQueue");
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            messageProducer = session.createProducer(queue);
            for (HrEmpMangers hrEmpMangers : CashHandler.getEmpManagers().get(hrHolidayRequest.getEmpId())) {
                ObjectMessage objectMessage = session.createObjectMessage();
                HrProfileMsg hrProfileMsg = new HrProfileMsg();
                hrProfileMsg.setEntityName("HrHolidayRequest");
                hrProfileMsg.setSendDate(new Date());
                hrProfileMsg.setEmpNo(hrEmpMangers.getMngNo());
                hrProfileMsg.setSenderNo(hrHolidayRequest.getEmpId());
                hrProfileMsg.setMsgId(hrHolidayRequest.getId());
                hrProfileMsg.setMsgType(1L);
                objectMessage.setObject(hrProfileMsg);
                if (CashHandler.getAlerts().containsKey(hrEmpMangers.getMngNo())) {
                    CashHandler.getAlerts().get(hrEmpMangers.getMngNo()).add(hrProfileMsg);
                } else {
                    List<HrProfileMsg> hrProfileMsgs = new ArrayList<HrProfileMsg>();
                    hrProfileMsgs.add(hrProfileMsg);
                    CashHandler.getAlerts().put(hrEmpMangers.getMngNo(), hrProfileMsgs);
                }
                messageProducer.send(objectMessage);

            }
            System.out.println("message sent");
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                messageProducer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        from_date = null;
        to_date = null;
        hol_typ = new HrHolidayType();
        hrHolidayRequest = new HrHolidayRequest();
        empNo = null;

        hrHolidayRequestList = sessionBean.find_holiday_request(Long.parseLong(usercode), min_date);
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('confirmation').hide();");
    }

    public void confirm_after_insert() {
        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("confirm").toString().equals("1")) {
            fm.setSummary(" „ »‰Ã«Õ");
            fm.setDetail(" „ Õ›Ÿ «·ÿ·» »‰Ã«Õ");
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
            sessionBean.persistHrHolidayRequest(hrHolidayRequest);
            Connection connection = null;
            Session session = null;
            MessageProducer messageProducer = null;
            try {
                Context ctx = new InitialContext();
                ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("jms/profileMsgQueueFactory");
                Queue queue = (Queue) ctx.lookup("jms/profileMsgQueue");
                connection = connectionFactory.createConnection();
                session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                messageProducer = session.createProducer(queue);
                for (HrEmpMangers hrEmpMangers : CashHandler.getEmpManagers().get(hrHolidayRequest.getEmpId())) {
                    ObjectMessage objectMessage = session.createObjectMessage();
                    HrProfileMsg hrProfileMsg = new HrProfileMsg();
                    hrProfileMsg.setEntityName("HrHolidayRequest");
                    hrProfileMsg.setSendDate(new Date());
                    hrProfileMsg.setEmpNo(hrEmpMangers.getMngNo());
                    hrProfileMsg.setSenderNo(hrHolidayRequest.getEmpId());
                    hrProfileMsg.setMsgId(hrHolidayRequest.getId());
                    hrProfileMsg.setMsgType(1L);
                    if (CashHandler.getAlerts().containsKey(hrEmpMangers.getMngNo())) {
                        CashHandler.getAlerts().get(hrEmpMangers.getMngNo()).add(hrProfileMsg);
                    } else {
                        List<HrProfileMsg> hrProfileMsgs = new ArrayList<HrProfileMsg>();
                        CashHandler.getAlerts().put(hrEmpMangers.getMngNo(), hrProfileMsgs);
                    }
                    objectMessage.setObject(hrProfileMsg);
                    messageProducer.send(objectMessage);
                }
                System.out.println("message sent");
            } catch (NamingException ex) {
                ex.printStackTrace();
            } catch (JMSException e) {
                e.printStackTrace();
            } finally {
                try {
                    messageProducer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    session.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        hrHolidayRequestList = sessionBean.find_holiday_request(Long.parseLong(usercode), min_date);

        from_date = null;
        to_date = null;
        hol_typ = new HrHolidayType();
        hrHolidayRequest = new HrHolidayRequest();
        empNo = null;

    }

    public void confirm_after_update() {
        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("confirm").toString().equals("1")) {
            sessionBean.mergeHrHolidayRequest(hhr);
            fm.setSummary(" „ »‰Ã«Õ");
            fm.setDetail(" „ Õ›Ÿ «· ⁄œÌ· »‰Ã«Õ");
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }
        hrHolidayRequestList = sessionBean.find_holiday_request(Long.parseLong(usercode), min_date);

    }

    public void reset_req_list() {
        HrMontlySalaryCalcPeriod hscp = CashHandler.hscp;
        min_date = hscp.getDateFrom();
        hrHolidayRequestList = sessionBean.find_holiday_request(Long.parseLong(usercode), min_date);
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public void setHol_req_list(List<Object[]> hol_req_list) {
        this.hol_req_list = hol_req_list;
    }

    public List<Object[]> getHol_req_list() {
        return hol_req_list;
    }

    public void setHol_type(List<Object[]> hol_type) {
        this.hol_type = hol_type;
    }

    public List<Object[]> getHol_type() {
        return hol_type;
    }

    public void setHol_type_list(List<SelectItem> hol_type_list) {
        this.hol_type_list = hol_type_list;
    }

    public List<SelectItem> getHol_type_list() {
        return hol_type_list;
    }

    public void setX(Boolean x) {
        this.x = x;
    }

    public Boolean getX() {
        return x;
    }

    public void setY(Boolean y) {
        this.y = y;
    }

    public Boolean getY() {
        return y;
    }

    public void setMin_date(Date min_date) {
        this.min_date = min_date;
    }

    public Date getMin_date() {
        return min_date;
    }

    public void setHrHolidayRequest(HrHolidayRequest hrHolidayRequest) {
        this.hrHolidayRequest = hrHolidayRequest;
    }

    public HrHolidayRequest getHrHolidayRequest() {
        return hrHolidayRequest;
    }

    public void setHol_typ(HrHolidayType hol_typ) {
        this.hol_typ = hol_typ;
    }

    public HrHolidayType getHol_typ() {
        System.out.println("hol_type" + FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("holidayType"));
        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("holidayType") != null) {
            hol_typ.setId(Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("holidayType")));
        }
        return hol_typ;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }

    public Date getTo_date() {
        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("trnsDate") != null) {
            String s = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("trnsDate");
            String dd = s.substring(0, s.indexOf('-'));
            String mm = s.substring(s.indexOf('-') + 1, s.lastIndexOf('-'));
            String yyyy = s.substring(s.lastIndexOf('-') + 1);
            System.out.println("");
            Calendar c = Calendar.getInstance(new Locale("ar"));
            c.set(Integer.parseInt(yyyy), Integer.parseInt(mm) - 1, Integer.parseInt(dd));
            to_date = c.getTime();
            hrHolidayRequest.setDaysNo(1L);
        }
        return to_date;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public Date getFrom_date() {
        System.out.println("trnsDate" + FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("trnsDate"));
        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("trnsDate") != null) {
            String s = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("trnsDate");
            String dd = s.substring(0, s.indexOf('-'));
            String mm = s.substring(s.indexOf('-') + 1, s.lastIndexOf('-'));
            String yyyy = s.substring(s.lastIndexOf('-') + 1);
            System.out.println("");
            Calendar c = Calendar.getInstance(new Locale("ar"));
            c.set(Integer.parseInt(yyyy), Integer.parseInt(mm) - 1, Integer.parseInt(dd));
            from_date = c.getTime();
            hrHolidayRequest.setDaysNo(1L);
        }
        return from_date;
    }

    public void setAlternativeEmp(List<SelectItem> alternativeEmp) {
        this.alternativeEmp = alternativeEmp;
    }

    public List<SelectItem> getAlternativeEmp() {
        return alternativeEmp;
    }

    public Long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Long empNo) {
        this.empNo = empNo;
    }

    public List<HrHolidayRequest> getHrHolidayRequestList() {
        return hrHolidayRequestList;
    }

    public void setHrHolidayRequestList(List<HrHolidayRequest> hrHolidayRequestList) {
        this.hrHolidayRequestList = hrHolidayRequestList;
    }

    public boolean isHasTransaction() {
        return hasTransaction;
    }

    public void setHasTransaction(boolean hasTransaction) {
        this.hasTransaction = hasTransaction;
    }
}
