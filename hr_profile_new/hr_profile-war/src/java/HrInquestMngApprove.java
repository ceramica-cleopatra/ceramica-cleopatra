
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
public class HrInquestMngApprove {

    @EJB
    private SessionBeanLocal sessionBean;
    List<HrInquestHd> inquestList = new ArrayList<HrInquestHd>();
    List<HrInquestDt> recommendationList = new ArrayList<HrInquestDt>();
    List<HrInquestDt> decisionList = new ArrayList<HrInquestDt>();
    List<HrInquestDt> witnessList = new ArrayList<HrInquestDt>();
    HrInquestHd hrInquestHd = new HrInquestHd();

    @PostConstruct
    public void init() {
        inquestList = sessionBean.getAllInquests();
    }

    public List<HrInquestHd> getInquestList() {
        return inquestList;
    }

    public void setInquestList(List<HrInquestHd> inquestList) {
        this.inquestList = inquestList;
    }

    public void findInquestDetails() {
        Integer selected_row = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("indx").toString());
        witnessList = new ArrayList<HrInquestDt>();
        recommendationList = new ArrayList<HrInquestDt>();
        decisionList = new ArrayList<HrInquestDt>();
        hrInquestHd = inquestList.get(selected_row);
        for (HrInquestDt hrInquestDt : hrInquestHd.getHrInquestDtList()) {
            if (hrInquestDt.getHrInquestEmpType().getId().equals(1L)) {
                if (hrInquestDt.getInqCoApprove() != null && !hrInquestDt.getInqCoApprove().isEmpty()) {
                    decisionList.add(hrInquestDt);
                } else {
                    recommendationList.add(hrInquestDt);
                }
            } else if (hrInquestDt.getHrInquestEmpType().getId().equals(2L)) {
                witnessList.add(hrInquestDt);
            }
        }
    }

    public void onRecommendationDrop(DragDropEvent ddEvent) {
        HrInquestDt hrInquestDt = ((HrInquestDt) ddEvent.getData());
        hrInquestDt.setInqCoApprove(hrInquestDt.getInqRec());
        hrInquestDt.setInqConMng(hrInquestDt.getInqCon());
        decisionList.add(hrInquestDt);
        recommendationList.remove(hrInquestDt);
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

    public String removeDecision() {
        Integer selected_row = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("indx").toString());
        HrInquestDt hrInquestDt = decisionList.get(selected_row);
        decisionList.remove(hrInquestDt);
        hrInquestDt.setInqCoApprove(null);
        hrInquestDt.setInqCon(null);
        recommendationList.add(hrInquestDt);
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

    public List<HrInquestDt> getRecommendationList() {
        return recommendationList;
    }

    public void setRecommendationList(List<HrInquestDt> recommendationList) {
        this.recommendationList = recommendationList;
    }

    public List<HrInquestDt> getWitnessList() {
        return witnessList;
    }

    public void setWitnessList(List<HrInquestDt> witnessList) {
        this.witnessList = witnessList;
    }
}
