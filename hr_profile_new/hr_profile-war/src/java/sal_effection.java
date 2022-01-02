/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEgadaSetup;
import e.HrEmpInfo;
import e.HrProfileAccessLog;
import e.HrProfileMsg;
import e.HrUsers;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionListener;
import sb.SessionBeanLocal;

/**
 *
 * @author ahmed abbas
 */
@ManagedBean
@ViewScoped
public class sal_effection implements Serializable {

    @EJB
    private SessionBeanLocal sessionBean;

    /** Creates a new instance of sal_effection */
    public sal_effection() {
    }
    private Date date_from;
    private Date date_to;
    private String usercode;
    private List<Object[]> emp_eff = new ArrayList<Object[]>();
    private List<Object[]> filteredList = new ArrayList<Object[]>();
    private Long eff_min_sum = 0L;
    private Double eff_day_sum = 0D;
    private Double plus_min_sum = 0D;
    private Double plus_day_sum = 0D;
    private Long holiday_sum = 0L;
    private Long vacation_sum = 0L;
    private Long w_holiday_sum = 0L;
    private Long auth_sum = 0L;
    private Long auth_day_sum = 0L;
    private Long not_auth_sum = 0L;
    private Long ts_sum = 0L;
    private Long duty_sum = 0L;
    private Long tot_gza = 0L;
    private Long tot_min = 0L;
    private Double egadaValue = 0D;
    private boolean extraAsHours;
    private int y = 0;
    private HrEmpInfo hrEmpInfo;
    private List<Object[]> l = new ArrayList<Object[]>();
    private Object[] o = new Object[14];
    private Object[] selectedRow = new Object[29];
    private SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
    @PostConstruct
    public void init() {
        usercode = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usercode");
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
        date_from = CashHandler.hscp.getDateFrom();
        date_to = CashHandler.hscp.getDateTo();
        extraAsHours=(hrEmpInfo.getExtraAsHours()!=null && hrEmpInfo.getExtraAsHours().equals("Y") ? true : false);
        HrEgadaSetup hrEgadaSetup = sessionBean.findEgadaValue(hrEmpInfo.getJobGrpId());
        /*if(hrEgadaSetup!=null){
            egadaValue = hrEgadaSetup.getValue();
        }*/

        if (CashHandler.getMsgs().get(Long.parseLong(usercode)) != null && CashHandler.getMsgs().get(Long.parseLong(usercode)).size() > 0) {
            for (HrProfileMsg hrProfileMsg : CashHandler.getMsgs().get(Long.parseLong(usercode))) {
                if (hrProfileMsg.getEntityName().equals("HrOvertimeSubApprove")) {
                    hrProfileMsg.setReadDone('Y');
                }
            }
            sessionBean.updateReadDoneMsg("HrOvertimeSubApprove", null, 'Y', Long.parseLong(usercode));
        }
    }

    public String search() {
        emp_eff = sessionBean.findAllEff(Long.parseLong(usercode), date_from, date_to);
        tot_gza = 0L;
        tot_min = 0L;
        eff_min_sum = 0L;
        eff_day_sum = 0D;
        plus_min_sum = 0D;
        plus_day_sum = 0D;
        holiday_sum = 0L;
        vacation_sum = 0L;
        w_holiday_sum = 0L;
        auth_sum = 0L;
        auth_day_sum = 0L;
        not_auth_sum = 0L;
        ts_sum = 0L;
        duty_sum = 0L;
        y = 0;
        Date d = null;
        for (Object[] x : emp_eff) {
            d = (Date) x[0];
            if (d.before(new Date())) {
                if (x[3].toString() != null) {
                    eff_min_sum = eff_min_sum + Long.parseLong(x[3].toString());
                }
                if (x[4].toString() != null) {
                    eff_day_sum = eff_day_sum + Double.parseDouble(x[4].toString());
                }
                if (extraAsHours ||(x[5].toString() != null && x[29]!=null && x[29].equals("Y"))) {
                    plus_min_sum = plus_min_sum + Double.parseDouble(x[5].toString());
                }
                if (x[6].toString() != null) {
                    plus_day_sum = plus_day_sum + Double.parseDouble(x[6].toString());
                }
                if (x[7].toString() != null && x[7].toString().equals("Y")) {
                    holiday_sum = holiday_sum + 1L;
                }
                if (x[8].toString() != null && x[8].toString().equals("Y")) {
                    vacation_sum = vacation_sum + 1L;
                }
                if (x[9].toString() != null && x[9].toString().equals("Y")) {
                    w_holiday_sum = w_holiday_sum + 1L;
                }
                if (x[11].toString() != null && x[11].toString().equals("Y")) {
                    auth_sum = auth_sum + 1L;
                }
                if (x[14].toString() != null && x[14].toString().equals("Y")) {
                    auth_day_sum = auth_day_sum + 1L;
                }
                if (x[15].toString() != null && x[15].toString().equals("Y")) {
                    not_auth_sum = not_auth_sum + 1L;
                }
                if (x[12].toString() != null && x[12].toString().equals("Y")) {
                    ts_sum = ts_sum + 1L;
                }
                if (x[13].toString() != null && x[13].toString().equals("Y")) {
                    duty_sum = duty_sum + 1L;
                }
                if (x[1] == null && x[2] == null && x[7].toString().equals("N") && x[13].toString().equals("N") && x[14].toString().equals("N") && x[11].toString().equals("N") && x[15].toString().equals("N") && x[12].toString().equals("N") && x[8].toString().equals("N") && x[9].toString().equals("N")) {
                    y = y + 1;
                }
            }
        }

        auth_day_sum = auth_day_sum + y;
        tot_gza = Math.round(((Double.parseDouble(hrEmpInfo.getTotSal().toString()) + egadaValue) / 30) * ((eff_day_sum + auth_day_sum) + (2 * not_auth_sum)));
        tot_min = Math.round((Double.parseDouble(hrEmpInfo.getTotSal().toString()) + egadaValue) / (26 * 8 * 60) * eff_min_sum);
        o[0] = tot_gza;
        o[1] = tot_min;
        o[2] = eff_min_sum;
        o[3] = eff_day_sum;
        o[4] = plus_min_sum;
        o[5] = plus_day_sum;
        o[6] = holiday_sum;
        o[7] = vacation_sum;
        o[8] = w_holiday_sum;
        o[9] = auth_sum;
        o[10] = auth_day_sum;
        o[11] = not_auth_sum;
        o[12] = ts_sum;
        o[13] = duty_sum;
        l = new ArrayList<Object[]>();
        l.add(0, o);
        return null;
    }

    public String callHolidayRequest() {

       String url="holiday_request.xhtml";
        if (selectedRow[0] != null) {
            url+="?trnsDate=" + sdf.format(selectedRow[0]);
            if(((Date)selectedRow[0]).before(new Date()))
                url+="&holidayType=10";
            else
                url+="&holidayType=1";
        }
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(sal_effection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    public String callAuthorizeRequest() {

       String url="authorize_request.xhtml";
        if (selectedRow[0] != null) {
            url+="?trnsDate=" + sdf.format(selectedRow[0]);
            if(selectedRow[3] != null){
                url+="&minusMinuts="+selectedRow[3];
            }
        }
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(sal_effection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    public String callShiftChangeRequest() {

       String url="shift_request.xhtml";
        if (selectedRow[0] != null) {
            url+="?trnsDate=" + sdf.format(selectedRow[0]);
        }
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(sal_effection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    public void setDate_from(Date date_from) {

        this.date_from = date_from;

    }

    public Date getDate_from() {
        return date_from;

    }

    public void setDate_to(Date date_to) {
        this.date_to = date_to;
    }

    public Date getDate_to() {
        return date_to;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setEmp_eff(List emp_eff) {
        this.emp_eff = emp_eff;
    }

    public List getEmp_eff() {
        return emp_eff;
    }

    public void setEff_min_sum(Long eff_min_sum) {
        this.eff_min_sum = eff_min_sum;
    }

    public Long getEff_min_sum() {
        return eff_min_sum;
    }

    public void setEff_day_sum(Double eff_day_sum) {
        this.eff_day_sum = eff_day_sum;
    }

    public Double getEff_day_sum() {
        return eff_day_sum;
    }

    public void setPlus_min_sum(Double plus_min_sum) {
        this.plus_min_sum = plus_min_sum;
    }

    public Double getPlus_min_sum() {
        return plus_min_sum;
    }

    public void setPlus_day_sum(Double plus_day_sum) {
        this.plus_day_sum = plus_day_sum;
    }

    public Double getPlus_day_sum() {
        return plus_day_sum;
    }

    public void setHoliday_sum(Long holiday_sum) {
        this.holiday_sum = holiday_sum;
    }

    public Long getHoliday_sum() {
        return holiday_sum;
    }

    public void setVacation_sum(Long vacation_sum) {
        this.vacation_sum = vacation_sum;
    }

    public Long getVacation_sum() {
        return vacation_sum;
    }

    public void setW_holiday_sum(Long w_holiday_sum) {
        this.w_holiday_sum = w_holiday_sum;
    }

    public Long getW_holiday_sum() {
        return w_holiday_sum;
    }

    public void setAuth_sum(Long auth_sum) {
        this.auth_sum = auth_sum;
    }

    public Long getAuth_sum() {
        return auth_sum;
    }

    public void setAuth_day_sum(Long auth_day_sum) {
        this.auth_day_sum = auth_day_sum;
    }

    public Long getAuth_day_sum() {
        return auth_day_sum;
    }

    public void setNot_auth_sum(Long not_auth_sum) {
        this.not_auth_sum = not_auth_sum;
    }

    public Long getNot_auth_sum() {
        return not_auth_sum;
    }

    public void setTs_sum(Long ts_sum) {
        this.ts_sum = ts_sum;
    }

    public Long getTs_sum() {
        return ts_sum;
    }

    public void setDuty_sum(Long duty_sum) {
        this.duty_sum = duty_sum;
    }

    public Long getDuty_sum() {
        return duty_sum;
    }

    public void setTot_gza(Long tot_gza) {
        this.tot_gza = tot_gza;
    }

    public Long getTot_gza() {
        return tot_gza;
    }

    public void setTot_min(Long tot_min) {
        this.tot_min = tot_min;
    }

    public Long getTot_min() {
        return tot_min;
    }

    public void setL(List<Object[]> l) {
        this.l = l;
    }

    public List<Object[]> getL() {
        return l;
    }

    public void setO(Object[] o) {
        this.o = o;
    }

    public Object[] getO() {
        return o;
    }

    public List<Object[]> getFilteredList() {
        return filteredList;
    }

    public void setFilteredList(List<Object[]> filteredList) {
        this.filteredList = filteredList;
    }

    public Object[] getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(Object[] selectedRow) {
        this.selectedRow = selectedRow;
    }

    public boolean isExtraAsHours() {
        return extraAsHours;
    }

    public void setExtraAsHours(boolean extraAsHours) {
        this.extraAsHours = extraAsHours;
    }

    
    
}
