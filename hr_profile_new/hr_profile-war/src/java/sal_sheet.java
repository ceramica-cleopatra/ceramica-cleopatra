/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrBadlHistory;
import e.HrCutoffVu;
import e.HrEgadaBonus;
import e.HrEmpInfo;
import e.HrEmpSal;
import e.HrEmpSalary;
import e.HrProfileAccessLog;
import e.HrSalDetail;
import e.HrSalHistory;
import e.HrTotalSalComponents;
import e.HrUsers;
import e.TaxDesc;
import e.Uni;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import sb.SessionBeanLocal;

/**
 *
 * @author ahmed abbas
 */
@ManagedBean
@ViewScoped
public class sal_sheet {

    @EJB
    private SessionBeanLocal sessionBean;
    private Double tot_outcome;
    private Double tot_income;
    private Double net_value;
    private List<HrCutoffVu> getHrCutoffVus = new ArrayList<HrCutoffVu>();
    private String current_month;
    private String usercode;
    private HrSalDetail hrSalDetail;
    private HrEgadaBonus hrEgadaBonus;

    @PostConstruct
    public void init() {
        usercode = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usercode");
        HrUsers hrUsers = (HrUsers) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrUsers");
        if (hrUsers != null) {
            HrProfileAccessLog hrProfileAccessLog = new HrProfileAccessLog();
            hrProfileAccessLog.setEmpNo(Long.parseLong(usercode));
            hrProfileAccessLog.setPageName(FacesContext.getCurrentInstance().getViewRoot().getViewId());
            hrProfileAccessLog.setTrnsDate(new Date());
            hrProfileAccessLog.setUserName(hrUsers.getUserName());
            sessionBean.persistProfileAccessLog(hrProfileAccessLog);
        }
        
        hrSalDetail = sessionBean.findSalDetail(Long.parseLong(usercode));
        current_month = hrSalDetail.getTrnsMonth() + "-" + hrSalDetail.getTrnsYear();
        getHrCutoffVus = sessionBean.getHrCutoffVus(hrSalDetail.getTrnsMonth(), hrSalDetail.getTrnsYear(), Long.parseLong(usercode));
        tot_income = hrSalDetail.getTotSal() + hrSalDetail.getPlusValue() + hrSalDetail.getHafezTot() + hrSalDetail.getTamyozTot() + hrSalDetail.getSalesTamyozTot();
        tot_outcome = hrSalDetail.getAuthVal() + hrSalDetail.getNotAuthVal() + hrSalDetail.getMinusMinutsVal() + hrSalDetail.getMinusDaysVal() + hrSalDetail.getMaradyVal() + hrSalDetail.getEsabaVal() + hrSalDetail.getCutoffTot() + hrSalDetail.getTax() + hrSalDetail.getWorkerPay();
        net_value = tot_income - tot_outcome;
       // hrEgadaBonus = sessionBean.findEmpEgadaBonus(Long.parseLong(usercode),hrSalDetail.getTrnsMonth(),hrSalDetail.getTrnsYear());
    }

    public void setCurrent_month(String current_month) {
        this.current_month = current_month;
    }

    /** Creates a new instance of sal_sheet */
    public sal_sheet() {
    }

    public String getCurrent_month() {
        return current_month;
    }

    public Double getNet_value() {
        return net_value;
    }

    public void setNet_value(Double net_value) {
        this.net_value = net_value;
    }

    public Double getTot_income() {
        return tot_income;
    }

    public void setTot_income(Double tot_income) {
        this.tot_income = tot_income;
    }

    public Double getTot_outcome() {
        return tot_outcome;
    }

    public void setTot_outcome(Double tot_outcome) {
        this.tot_outcome = tot_outcome;
    }

    public List<HrCutoffVu> getGetHrCutoffVus() {
        return getHrCutoffVus;
    }

    public void setGetHrCutoffVus(List<HrCutoffVu> getHrCutoffVus) {
        this.getHrCutoffVus = getHrCutoffVus;
    }

    public HrSalDetail getHrSalDetail() {
        return hrSalDetail;
    }

    public void setHrSalDetail(HrSalDetail hrSalDetail) {
        this.hrSalDetail = hrSalDetail;
    }

    public HrEgadaBonus getHrEgadaBonus() {
        return hrEgadaBonus;
    }

    public void setHrEgadaBonus(HrEgadaBonus hrEgadaBonus) {
        this.hrEgadaBonus = hrEgadaBonus;
    }

}
