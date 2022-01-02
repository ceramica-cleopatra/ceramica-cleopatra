/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrDutyTrnsHd;
import e.HrDutyTrnsHdVu;
import e.HrEmpInfo;
import e.HrProfileMsg;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import sb.SessionBeanLocal;

/**
 *
 * @author data
 */
@ManagedBean
@RequestScoped
public class DutyFollowUp {

    /** Creates a new instance of DutyFollowUp */
    public DutyFollowUp() {
    }
    @EJB
    private SessionBeanLocal sessionBean;
    private HrEmpInfo hrEmpInfo;
    private List<HrDutyTrnsHd> hrDutyTrnsHdList;

    @PostConstruct
    public void init(){
        hrEmpInfo=(HrEmpInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrEmpInfo");
        Calendar c=Calendar.getInstance();
        c.set(Calendar.MONTH, 0);
        c.add(Calendar.MONTH, -1);
        System.out.println(c);
        hrDutyTrnsHdList=sessionBean.findDutyToFollowUp(hrEmpInfo,c.getTime());
        if (CashHandler.getMsgs().get(hrEmpInfo.getEmpNo()) != null && CashHandler.getMsgs().get(hrEmpInfo.getEmpNo()).size() > 0) {
            for (HrProfileMsg hrProfileMsg : CashHandler.getMsgs().get(hrEmpInfo.getEmpNo())) {
                if (hrProfileMsg.getEntityName().equals("HrDutyTrnsDtApprove")) {
                    hrProfileMsg.setReadDone('Y');
                }
            }
            sessionBean.updateReadDoneMsg("HrDutyTrnsDtApprove", null, 'Y', hrEmpInfo.getEmpNo());
        }
    }

    public List<HrDutyTrnsHd> getHrDutyTrnsHdList() {
        return hrDutyTrnsHdList;
    }
}
