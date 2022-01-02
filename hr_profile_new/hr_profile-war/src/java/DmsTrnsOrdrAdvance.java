/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.DmsTransportOrdrHd;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
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
public class DmsTrnsOrdrAdvance {

    @EJB
    private SessionBeanLocal sessionBean;
    private List<DmsTransportOrdrHd> trnsOrdrAdvanceList = new ArrayList<DmsTransportOrdrHd>();
    private boolean readDone = false;
    private String usercode;
    @PostConstruct
    public void init() {
        usercode = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usercode");
        trnsOrdrAdvanceList = sessionBean.findTrnsOrdrAdvanceList(Long.parseLong(usercode));
    }

    public void onSelectCheckBox() {
        readDone = false;
        String ordrId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("readDone");
        DmsTransportOrdrHd dmsTransportOrdrHd = sessionBean.findDmsOrdrHdById(Long.parseLong(ordrId));
        dmsTransportOrdrHd.setProfileConfirm("Y");
        sessionBean.mergeDmsTranOrdrHd(dmsTransportOrdrHd);
        trnsOrdrAdvanceList = sessionBean.findTrnsOrdrAdvanceList(Long.parseLong(usercode));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ «·≈ÿ·«⁄"));
    }

    /** Creates a new instance of DmsTrnsOrdrAdvance */
    public DmsTrnsOrdrAdvance() {
    }

    public List<DmsTransportOrdrHd> getTrnsOrdrAdvanceList() {
        return trnsOrdrAdvanceList;
    }

    public void setTrnsOrdrAdvanceList(List<DmsTransportOrdrHd> trnsOrdrAdvanceList) {
        this.trnsOrdrAdvanceList = trnsOrdrAdvanceList;
    }

    public boolean isReadDone() {
        return readDone;
    }

    public void setReadDone(boolean readDone) {
        this.readDone = readDone;
    }
}
