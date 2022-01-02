/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import sb.SessionBeanLocal;

/**
 *
 * @author Administrator
 */

public class CrmkOrdrAndRmnWithoutSrfDTO {

    /** Creates a new instance of CrmkOrdrAndRmnWithoutSrfDTO */
    public CrmkOrdrAndRmnWithoutSrfDTO() {
    }
    private String clntName;
    private String phone;
    private List<OrdrHdDTO> ordrHdDTOList;
    private List<RemainHdDTO> remainHdDTOList;

    public String getClntName() {
        return clntName;
    }

    public void setClntName(String clntName) {
        this.clntName = clntName;
    }

    public List<OrdrHdDTO> getOrdrHdDTOList() {
        return ordrHdDTOList;
    }

    public void setOrdrHdDTOList(List<OrdrHdDTO> ordrHdDTOList) {
        this.ordrHdDTOList = ordrHdDTOList;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<RemainHdDTO> getRemainHdDTOList() {
        return remainHdDTOList;
    }

    public void setRemainHdDTOList(List<RemainHdDTO> remainHdDTOList) {
        this.remainHdDTOList = remainHdDTOList;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CrmkOrdrAndRmnWithoutSrfDTO other = (CrmkOrdrAndRmnWithoutSrfDTO) obj;
        if ((this.clntName == null) ? (other.clntName != null) : !this.clntName.equals(other.clntName)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + (this.clntName != null ? this.clntName.hashCode() : 0);
        return hash;
    }
    
}
