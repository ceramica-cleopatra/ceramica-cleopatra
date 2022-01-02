/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrCarRequestHd;
import e.HrLocation;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sb.SessionBeanLocal;

/**
 *
 * @author data
 */
@ManagedBean
@SessionScoped
public class CarRequest {
    @EJB
    private SessionBeanLocal sessionBean;
    private List<HrLocation> availableLocations;
    private HrCarRequestHd hrCarRequestHd;
    private List<HrLocation> selectedLocations;
    @PostConstruct
    public void init(){
        availableLocations=sessionBean.getDutyLocations();
        selectedLocations=new ArrayList<HrLocation>();
    }
    public CarRequest() {
    }

    public HrCarRequestHd getHrCarRequestHd() {
        return hrCarRequestHd;
    }

    public void setHrCarRequestHd(HrCarRequestHd hrCarRequestHd) {
        this.hrCarRequestHd = hrCarRequestHd;
    }

    public List<HrLocation> getAvailableLocations() {
        return availableLocations;
    }

    public void setAvailableLocations(List<HrLocation> availableLocations) {
        this.availableLocations = availableLocations;
    }

    public List<HrLocation> getSelectedLocations() {
        return selectedLocations;
    }

    public void setSelectedLocations(List<HrLocation> selectedLocations) {
        this.selectedLocations = selectedLocations;
    }
    
}
