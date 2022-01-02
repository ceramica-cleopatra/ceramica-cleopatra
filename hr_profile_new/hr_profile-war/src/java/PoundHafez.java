/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrPoundHafezDt;
import e.HrProfileAccessLog;
import e.HrUsers;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import sb.SessionBeanLocal;

/**
 *
 * @author Administrator
 */
@ManagedBean
@ViewScoped
public class PoundHafez {

    @EJB
    private SessionBeanLocal sessionBean;
    private List<HrPoundHafezDt> hrPoundHafezDtList = new ArrayList<HrPoundHafezDt>();
    private HrPoundHafezDt hrPoundHafezDt=new HrPoundHafezDt();
    private int rowspanCount=10;
    private HrEmpInfo hrEmpInfo;
    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        String usercode = (String) fc.getExternalContext().getSessionMap().get("usercode");
        hrEmpInfo=(HrEmpInfo) fc.getExternalContext().getSessionMap().get("hrEmpInfo");
        HrUsers hrUsers = (HrUsers) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrUsers");
        if (hrUsers != null) {
            HrProfileAccessLog hrProfileAccessLog = new HrProfileAccessLog();
            hrProfileAccessLog.setEmpNo(Long.parseLong(usercode));
            hrProfileAccessLog.setPageName(FacesContext.getCurrentInstance().getViewRoot().getViewId());
            hrProfileAccessLog.setTrnsDate(new Date());
            hrProfileAccessLog.setUserName(hrUsers.getUserName());
            sessionBean.persistProfileAccessLog(hrProfileAccessLog);
        }
        hrPoundHafezDtList = sessionBean.findPoundHafezDts(Long.parseLong(usercode));
        if(hrPoundHafezDtList.size()>0)
            hrPoundHafezDt=hrPoundHafezDtList.get(0);
        if(hrPoundHafezDt.getCheckupDuty()==null || hrPoundHafezDt.getCheckupDuty().equals(new BigInteger("0")))
            rowspanCount=rowspanCount-1;
        if(hrPoundHafezDt.getStoreIssues()==null || hrPoundHafezDt.getStoreIssues().equals(new BigInteger("0")))
            rowspanCount=rowspanCount-1;
        if(hrPoundHafezDt.getRaiseBasic2018()==null || hrPoundHafezDt.getRaiseBasic2018().equals(new BigInteger("0")))
            rowspanCount=rowspanCount-1;
        if(hrPoundHafezDt.getRaiseVar2018()==null || hrPoundHafezDt.getRaiseVar2018().equals(new BigInteger("0")))
            rowspanCount=rowspanCount-1;
        if(hrPoundHafezDt.getLoadBonus()==null || hrPoundHafezDt.getLoadBonus().equals(new BigInteger("0")))
            rowspanCount=rowspanCount-1;
        if(hrPoundHafezDt.getDriversBonus()==null || hrPoundHafezDt.getDriversBonus().equals(new BigInteger("0")))
            rowspanCount=rowspanCount-1;
        if(hrPoundHafezDt.getTrnsBonus()==null || hrPoundHafezDt.getTrnsBonus().equals(new BigInteger("0")))
            rowspanCount=rowspanCount-1;
        if(hrPoundHafezDt.getOther()==null || hrPoundHafezDt.getOther().equals(new BigInteger("0")))
            rowspanCount=rowspanCount-1;
    }

    public HrPoundHafezDt getHrPoundHafezDt() {
        return hrPoundHafezDt;
    }

    public void setHrPoundHafezDt(HrPoundHafezDt hrPoundHafezDt) {
        this.hrPoundHafezDt = hrPoundHafezDt;
    }

    /** Creates a new instance of PoundHafez */
    public PoundHafez() {
    }

    public int getRowspanCount() {
        return rowspanCount;
    }

    public void setRowspanCount(int rowspanCount) {
        this.rowspanCount = rowspanCount;
    }

    public HrEmpInfo getHrEmpInfo() {
        return hrEmpInfo;
    }

    public void setHrEmpInfo(HrEmpInfo hrEmpInfo) {
        this.hrEmpInfo = hrEmpInfo;
    }

   
}
