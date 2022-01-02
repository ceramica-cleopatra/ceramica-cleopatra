
import e.HrInquestDt;
import e.HrInquestHd;
import e.HrRegion;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.DragDropEvent;
import sb.SessionBeanLocal;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author data
 */
@ManagedBean
@ViewScoped
public class HrInquestHrApprove {

    @EJB
    private SessionBeanLocal sessionBean;
    List<HrInquestHd> inquestList = new ArrayList<HrInquestHd>();
    List<HrInquestDt> decisionList = new ArrayList<HrInquestDt>();
    HrInquestHd hrInquestHd = new HrInquestHd();

    @PostConstruct
    public void init() {
        inquestList = sessionBean.getAllMngApprovedInquests();
    }

    public List<HrInquestHd> getInquestList() {
        return inquestList;
    }

    public void setInquestList(List<HrInquestHd> inquestList) {
        this.inquestList = inquestList;
    }

    public void findInquestDetails() {
        Integer selected_row = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("indx").toString());
        decisionList = new ArrayList<HrInquestDt>();
        hrInquestHd = inquestList.get(selected_row);
        for (HrInquestDt hrInquestDt : hrInquestHd.getHrInquestDtList()) {
            if (hrInquestDt.getHrInquestEmpType().getId().equals(1L) && hrInquestDt.getInqCoApprove()!=null && !hrInquestDt.getInqCoApprove().isEmpty()) {
                    decisionList.add(hrInquestDt);
            } 
        }
    }

   public void handleClose(CloseEvent event) {
       hrInquestHd.setHrConf('Y');
       sessionBean.mergeHrInquestHd(hrInquestHd);
    }


    public String saveDescions() {
        for (HrInquestDt hrInquestDt : decisionList) {
            sessionBean.mergeHrInquestDt(hrInquestDt);
        }
        hrInquestHd.setMngConf('Y');
        sessionBean.mergeHrInquestHd(hrInquestHd);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ «· ’œÌﬁ ⁄·Ï «·ﬁ—«—«  »‰Ã«Õ"));
        return null;
    }

   

    public HrInquestHd getHrInquestHd() {
        return hrInquestHd;
    }

    public void setHrInquestHd(HrInquestHd hrInquestHd) {
        this.hrInquestHd = hrInquestHd;
    }

    public List<HrInquestDt> getDecisionList() {
        return decisionList;
    }

    public void setDecisionList(List<HrInquestDt> decisionList) {
        this.decisionList = decisionList;
    }

}
