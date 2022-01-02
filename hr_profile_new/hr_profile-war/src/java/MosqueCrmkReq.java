/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrMosqueCrmkReq;
import e.HrProfileAccessLog;
import e.HrUsers;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import sb.SessionBeanLocal;

/**
 *
 * @author data
 */
@ManagedBean
@ViewScoped
public class MosqueCrmkReq {
    @EJB
    private SessionBeanLocal sessionBean;
    private HrEmpInfo hrEmpInfo;
    private HrMosqueCrmkReq hrMosqueCrmkReq;
    /** Creates a new instance of MosqueCrmkReq */
    public MosqueCrmkReq() {
    }

    private boolean showEmp2;
    private boolean showEmp3;
    private boolean showEmp4;
    private boolean showEmp5;
    
     @PostConstruct
    public void init() {
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
        hrMosqueCrmkReq = new HrMosqueCrmkReq();
    }

    public String addNewViewer(){
        if(hrMosqueCrmkReq!=null){
            if(hrMosqueCrmkReq.getEmpName1()!=null &&
                    !hrMosqueCrmkReq.getEmpName1().isEmpty())
                showEmp2 = true;
            else
                showEmp2=false;

            if(hrMosqueCrmkReq.getEmpName2()!=null &&
                    !hrMosqueCrmkReq.getEmpName2().isEmpty())
                showEmp3 = true;
            else
                showEmp3=false;

            if(hrMosqueCrmkReq.getEmpName3()!=null &&
                    !hrMosqueCrmkReq.getEmpName3().isEmpty())
                showEmp4 = true;
            else
                showEmp4=false;

            if(hrMosqueCrmkReq.getEmpName4()!=null &&
                    !hrMosqueCrmkReq.getEmpName4().isEmpty())
                showEmp5 = true;
            else
                showEmp5=false;
        }
        return null;
    }

     public void saveRequest(){
         hrMosqueCrmkReq.setSerialNo(sessionBean.getMosqueReqSerialNo()==null || sessionBean.getMosqueReqSerialNo().equals(0L)?1L:sessionBean.getMosqueReqSerialNo());
         hrMosqueCrmkReq.setEmpNo(hrEmpInfo);
         hrMosqueCrmkReq.setTrnsDate(new Date());
         sessionBean.saveMosqueCrmkRequest(hrMosqueCrmkReq);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ «·Õ›Ÿ »‰Ã«Õ"));
        hrMosqueCrmkReq = new HrMosqueCrmkReq();
     }
    public HrMosqueCrmkReq getHrMosqueCrmkReq() {
        return hrMosqueCrmkReq;
    }

    public void setHrMosqueCrmkReq(HrMosqueCrmkReq hrMosqueCrmkReq) {
        this.hrMosqueCrmkReq = hrMosqueCrmkReq;
    }

    public HrEmpInfo getHrEmpInfo() {
        return hrEmpInfo;
    }

    public void setHrEmpInfo(HrEmpInfo hrEmpInfo) {
        this.hrEmpInfo = hrEmpInfo;
    }

    public boolean isShowEmp2() {
        return showEmp2;
    }

    public void setShowEmp2(boolean showEmp2) {
        this.showEmp2 = showEmp2;
    }

    public boolean isShowEmp3() {
        return showEmp3;
    }

    public void setShowEmp3(boolean showEmp3) {
        this.showEmp3 = showEmp3;
    }

    public boolean isShowEmp4() {
        return showEmp4;
    }

    public void setShowEmp4(boolean showEmp4) {
        this.showEmp4 = showEmp4;
    }

    public boolean isShowEmp5() {
        return showEmp5;
    }

    public void setShowEmp5(boolean showEmp5) {
        this.showEmp5 = showEmp5;
    }

    

}
