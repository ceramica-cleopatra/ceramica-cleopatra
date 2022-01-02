/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrBorrowDt;
import e.HrBorrowHd;
import e.HrBorrowSummary;
import e.HrProfileAccessLog;
import e.HrUsers;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
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
public class borrow implements Serializable  {
    @EJB
    private SessionBeanLocal sessionBean;
    private List<HrBorrowHd> hrBorrowHdList;
    private HrBorrowHd hrBorrowHd;
    private List<HrBorrowDt> hrBorrowDt;
    private List<HrBorrowSummary> hrBorrowSummary;
    private String usercode;
    public borrow(){}
    @PostConstruct
    public void init(){
       
        usercode=(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usercode");
        HrUsers hrUsers = (HrUsers) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrUsers");
        if (hrUsers != null) {
            HrProfileAccessLog hrProfileAccessLog = new HrProfileAccessLog();
            hrProfileAccessLog.setEmpNo(Long.parseLong(usercode));
            hrProfileAccessLog.setPageName(FacesContext.getCurrentInstance().getViewRoot().getViewId());
            hrProfileAccessLog.setTrnsDate(new Date());
            hrProfileAccessLog.setUserName(hrUsers.getUserName());
            sessionBean.persistProfileAccessLog(hrProfileAccessLog);
        }
        hrBorrowHdList=sessionBean.findAllBorrow(Long.parseLong(usercode));
    }
    
    public List<HrBorrowDt> getHrBorrowDt() {
        return hrBorrowDt;
    }

    public void setHrBorrowDt(List<HrBorrowDt> hrBorrowDt) {
        this.hrBorrowDt = hrBorrowDt;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public HrBorrowHd getHrBorrowHd() {
        return hrBorrowHd;
    }

    public void setHrBorrowHd(HrBorrowHd hrBorrowHd) {
        
        this.hrBorrowHd = hrBorrowHd;
    }

    public List<HrBorrowHd> getHrBorrowHdList() {
        return hrBorrowHdList;
    }

    public void setHrBorrowHdList(List<HrBorrowHd> hrBorrowHdList) {
        this.hrBorrowHdList = hrBorrowHdList;
    }

    public List<HrBorrowSummary> getHrBorrowSummary() {
        return hrBorrowSummary;
    }

    public void setHrBorrowSummary(List<HrBorrowSummary> hrBorrowSummary) {
        this.hrBorrowSummary = hrBorrowSummary;
    }

    

}
