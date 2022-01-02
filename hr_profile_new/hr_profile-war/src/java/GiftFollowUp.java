/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrProfileAccessLog;
import e.HrUsers;
import e.HrZamalaGiftSrfDt;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import sb.SessionBeanLocal;

/**
 *
 * @author data
 */
@ManagedBean
@ViewScoped
public class GiftFollowUp {
    @EJB
    private SessionBeanLocal sessionBean;
    private List<HrZamalaGiftSrfDt> giftList;
    private HrEmpInfo hrEmpInfo;
    @PostConstruct
    public void init(){
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
        giftList=sessionBean.findFundEmpGifts(hrEmpInfo);
    }
    /** Creates a new instance of GiftFollowUp */
    public GiftFollowUp() {
    }

    public List<HrZamalaGiftSrfDt> getGiftList() {
        return giftList;
    }

    public void setGiftList(List<HrZamalaGiftSrfDt> giftList) {
        this.giftList = giftList;
    }

}
