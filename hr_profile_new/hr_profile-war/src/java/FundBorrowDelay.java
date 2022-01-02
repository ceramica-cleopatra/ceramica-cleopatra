/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrFundBorrowDelayReq;
import e.HrProfileAccessLog;
import e.HrUsers;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import sb.SessionBeanLocal;

/**
 *
 * @author ahmed
 */
@ManagedBean
@ViewScoped
public class FundBorrowDelay {
    @EJB
    private SessionBeanLocal sessionBean;
    private HrFundBorrowDelayReq hrFundBorrowDelayReq;
    private HrEmpInfo hrEmpInfo;
    @PostConstruct
    public void init(){
        hrFundBorrowDelayReq=new HrFundBorrowDelayReq();
        hrEmpInfo=(HrEmpInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrEmpInfo");
        HrUsers hrUsers = (HrUsers) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrUsers");
        if (hrUsers != null) {
            HrProfileAccessLog hrProfileAccessLog = new HrProfileAccessLog();
            hrProfileAccessLog.setEmpNo(hrEmpInfo.getEmpNo());
            hrProfileAccessLog.setPageName(FacesContext.getCurrentInstance().getViewRoot().getViewId());
            hrProfileAccessLog.setTrnsDate(new Date());
            hrProfileAccessLog.setUserName(hrUsers.getUserName());
            sessionBean.persistProfileAccessLog(hrProfileAccessLog);
        }
    }


    public void delay(ActionEvent ae){
        if(hrFundBorrowDelayReq.getDelayMonth()==null || hrFundBorrowDelayReq.getDelayYear()==null || hrFundBorrowDelayReq.getBorrowNo()==null){
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Œÿ√","ÌÃ» ≈œŒ«· Ã„Ì⁄ «·»Ì«‰« "));
            return;
        }
        Long chk=sessionBean.chkFundBorrowDelay(hrEmpInfo.getEmpNo(),hrFundBorrowDelayReq.getBorrowNo(),hrFundBorrowDelayReq.getDelayMonth(),hrFundBorrowDelayReq.getDelayYear());
        if(chk==1){
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Œÿ√"," √ﬂœ „‰ —ﬁ„ «·”·›… Ê «·‘Â— «·„—«œ  √ÃÌ·Â"));
            return;
        }else if(chk==2){
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Œÿ√"," „  Ã«Ê“ ⁄œœ „—«  «· √ÃÌ· «·„”„ÊÕ »Â«"));
            return;
        }else if(chk==3){
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Œÿ√","Â–« «·‘Â— „ƒÃ· ”œ«œÂ »«·›⁄·"));
            return;
        }
        sessionBean.applyFundBorrowDelay(hrEmpInfo.getEmpNo(),hrFundBorrowDelayReq.getBorrowNo(),hrFundBorrowDelayReq.getDelayMonth(),hrFundBorrowDelayReq.getDelayYear());
        hrFundBorrowDelayReq.setEmpNo(hrEmpInfo.getEmpNo());
        hrFundBorrowDelayReq.setTrnsDate(new Date());
        sessionBean.persistHrFundBorrowDelayReq(hrFundBorrowDelayReq);
        hrFundBorrowDelayReq=new HrFundBorrowDelayReq();
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR," „ »‰Ã«Õ"," „  √ÃÌ· «·”œ«œ »‰Ã«Õ"));
    }
    /** Creates a new instance of FundBorrowDelay */
    public FundBorrowDelay() {
    }

    public HrFundBorrowDelayReq getHrFundBorrowDelayReq() {
        return hrFundBorrowDelayReq;
    }

    public void setHrFundBorrowDelayReq(HrFundBorrowDelayReq hrFundBorrowDelayReq) {
        this.hrFundBorrowDelayReq = hrFundBorrowDelayReq;
    }

    
}
