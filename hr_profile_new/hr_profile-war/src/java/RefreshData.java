/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrProfileAccessLog;
import e.HrUsers;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import sb.SessionBeanLocal;

/**
 *
 * @author Administrator
 */
@ManagedBean
@RequestScoped
public class RefreshData {

    @EJB
    private SessionBeanLocal sessionBean;

    /** Creates a new instance of RefreshData */
    public RefreshData() {
    }

    @PostConstruct
    public void init() {
        HrEmpInfo hrEmpInfo = (HrEmpInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrEmpInfo");
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

    public void refreshMenu(ActionEvent e) {
        CashHandler.setMenuCashMap(null);
        addMessage();
//        CashHandler.fillCash();
    }

    public void refreshMsg(ActionEvent e) {
        CashHandler.setAlerts(null);
        addMessage();
        //       CashHandler.fillCash();
    }

    public void refreshTamyozApprove(ActionEvent e) {
        CashHandler.setTamyozApprove(null);
        addMessage();
        //       CashHandler.fillCash();
    }

    public void refreshHierarchy(ActionEvent e) {
        CashHandler.setHeadHierachy(null);
        addMessage();
        //      CashHandler.fillCash();
    }

    public void refreshApprove(ActionEvent e) {
        CashHandler.setEmpManagers(null);
        addMessage();
        //       CashHandler.fillCash();
    }

    public void refreshAll(ActionEvent e) {
        CashHandler.setMenuCashMap(null);
        CashHandler.setAlerts(null);
        CashHandler.setTamyozApprove(null);
        CashHandler.setHeadHierachy(null);
        CashHandler.setEmpManagers(null);
        CashHandler.setHscp(null);
        addMessage();
        //      CashHandler.fillCash();
    }

    public void addMessage() {
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „  ÕœÌÀ «·»Ì«‰«  »‰Ã«Õ"));
    }
}
