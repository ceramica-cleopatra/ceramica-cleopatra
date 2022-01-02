/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrGzaDt;
import e.HrProfileAccessLog;
import e.HrProfileMsg;
import e.HrUsers;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import sb.SessionBeanLocal;
import sb.SessionBeanRemote;

/**
 *
 * @author ahmed abbas
 */
@ManagedBean
@ViewScoped
public class gza_emp_audit {
    @EJB
    private SessionBeanLocal sessionBean;
    private List<Object[]> HrGzaDtForEmp=new ArrayList<Object[]>();
    private String usercode;
    private Object[] selectedGza;
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
    HrGzaDtForEmp=sessionBean.getHrGzaDtForEmp(Long.parseLong(usercode));
    List<HrGzaDt> hrGzaDts=sessionBean.getEmpNotReadGza(Long.parseLong(usercode));
    for(HrGzaDt hrGzaDt:hrGzaDts)
      {
        hrGzaDt.setReadDone("Y");
        sessionBean.mergeHrGzaDt(hrGzaDt);
      }

        if(CashHandler.getAlerts().get(Long.parseLong(usercode))!=null && CashHandler.getAlerts().get(Long.parseLong(usercode)).size()>0)
        {
            for (HrProfileMsg hrProfileMsg : CashHandler.getAlerts().get(Long.parseLong(usercode)))
            {
                if(hrProfileMsg.getEntityName().equals("HrGzaHd"))
                {
                    hrProfileMsg.setReadDone('Y');
                    sessionBean.updateReadDoneMsg("HrGzaHd", hrProfileMsg.getMsgId(), 'Y',null);
                }
            }
        }
    }
    public List<Object[]> getHrGzaDtForEmp() {
      return HrGzaDtForEmp;
    }

    public void setHrGzaDtForEmp(List<Object[]> HrGzaDtForEmp) {
        this.HrGzaDtForEmp = HrGzaDtForEmp;
    }

    public Object[] getSelectedGza() {
        return selectedGza;
    }

    public void setSelectedGza(Object[] selectedGza) {
        this.selectedGza = selectedGza;
    }

    

    /** Creates a new instance of gza_emp_audit */
    public gza_emp_audit() {
    }

}
