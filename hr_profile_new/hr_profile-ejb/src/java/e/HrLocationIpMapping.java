/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author data
 */
@Entity
@Table(name = "HR_LOCATION_IP_MAPPING", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrLocationIpMapping.findAll", query = "SELECT h FROM HrLocationIpMapping h"),
    @NamedQuery(name = "HrLocationIpMapping.findByIp", query = "SELECT h FROM HrLocationIpMapping h WHERE h.hrLocationIpMappingPK.ip = :ip"),
    @NamedQuery(name = "HrLocationIpMapping.findByLocId", query = "SELECT h FROM HrLocationIpMapping h WHERE h.hrLocationIpMappingPK.locId = :locId")})
public class HrLocationIpMapping implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HrLocationIpMappingPK hrLocationIpMappingPK;

    public HrLocationIpMapping() {
    }

    public HrLocationIpMapping(HrLocationIpMappingPK hrLocationIpMappingPK) {
        this.hrLocationIpMappingPK = hrLocationIpMappingPK;
    }

    public HrLocationIpMapping(Long ip, Long locId) {
        this.hrLocationIpMappingPK = new HrLocationIpMappingPK(ip, locId);
    }

    public HrLocationIpMappingPK getHrLocationIpMappingPK() {
        return hrLocationIpMappingPK;
    }

    public void setHrLocationIpMappingPK(HrLocationIpMappingPK hrLocationIpMappingPK) {
        this.hrLocationIpMappingPK = hrLocationIpMappingPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hrLocationIpMappingPK != null ? hrLocationIpMappingPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HrLocationIpMapping)) {
            return false;
        }
        HrLocationIpMapping other = (HrLocationIpMapping) object;
        if ((this.hrLocationIpMappingPK == null && other.hrLocationIpMappingPK != null) || (this.hrLocationIpMappingPK != null && !this.hrLocationIpMappingPK.equals(other.hrLocationIpMappingPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrLocationIpMapping[hrLocationIpMappingPK=" + hrLocationIpMappingPK + "]";
    }

}
