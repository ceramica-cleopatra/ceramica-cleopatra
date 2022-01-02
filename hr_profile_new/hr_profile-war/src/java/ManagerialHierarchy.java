/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import sb.SessionBeanLocal;

/**
 *
 * @author Administrator
 */
@ManagedBean
@RequestScoped
public class ManagerialHierarchy {

    @EJB
    private SessionBeanLocal sessionBean;
    private String headHierarchy;
    private String salesHierarchy;
    private String storeHierarchy;

    /** Creates a new instance of ManagerialHierarchy */
    public ManagerialHierarchy() {
    }

    @PostConstruct
    public void init() {
        if (CashHandler.getHeadHierachy() != null && !CashHandler.getHeadHierachy().isEmpty()) {
            headHierarchy = CashHandler.getHeadHierachy();
            salesHierarchy = CashHandler.getSalesHierachy();
            storeHierarchy = CashHandler.getStoreHierarchy();
        } else {
            headHierarchy = sessionBean.findManagerialHierarchy("1");
            storeHierarchy = sessionBean.findManagerialHierarchy("2");
            salesHierarchy = sessionBean.findManagerialHierarchy("3");
        }

    }

    public String getHeadHierarchy() {
        return headHierarchy;
    }

    public void setHeadHierarchy(String headHierarchy) {
        this.headHierarchy = headHierarchy;
    }

    public String getSalesHierarchy() {
        return salesHierarchy;
    }

    public void setSalesHierarchy(String salesHierarchy) {
        this.salesHierarchy = salesHierarchy;
    }

    public String getStoreHierarchy() {
        return storeHierarchy;
    }

    public void setStoreHierarchy(String storeHierarchy) {
        this.storeHierarchy = storeHierarchy;
    }

    

}
