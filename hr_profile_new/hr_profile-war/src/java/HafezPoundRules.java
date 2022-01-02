/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import sb.SessionBeanLocal;

/**
 *
 * @author data
 */
@ManagedBean
@ViewScoped
public class HafezPoundRules {

    /** Creates a new instance of HafezPoundRules */
   @EJB
    private SessionBeanLocal sessionBean;
    private List<Object[]> hafezPoundRuleList = new ArrayList<Object[]>();
    private HrEmpInfo hrEmpInfo;

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        hrEmpInfo = (HrEmpInfo) fc.getExternalContext().getSessionMap().get("hrEmpInfo");
        hafezPoundRuleList = sessionBean.getPoundHafezRules();
    }
    public HafezPoundRules() {
    }

    public List<Object[]> getHafezPoundRuleList() {
        return hafezPoundRuleList;
    }

    public void setHafezPoundRuleList(List<Object[]> hafezPoundRuleList) {
        this.hafezPoundRuleList = hafezPoundRuleList;
    }
    
}
