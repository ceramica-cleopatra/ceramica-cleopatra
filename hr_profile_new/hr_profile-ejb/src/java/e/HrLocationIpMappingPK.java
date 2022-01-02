/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author data
 */
@Embeddable
public class HrLocationIpMappingPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "IP")
    private Long ip;
    @Basic(optional = false)
    @Column(name = "LOC_ID")
    private Long locId;

    public HrLocationIpMappingPK() {
    }

    public HrLocationIpMappingPK(Long ip, Long locId) {
        this.ip = ip;
        this.locId = locId;
    }

    public Long getIp() {
        return ip;
    }

    public void setIp(Long ip) {
        this.ip = ip;
    }

    public Long getLocId() {
        return locId;
    }

    public void setLocId(Long locId) {
        this.locId = locId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ip != null ? ip.hashCode() : 0);
        hash += (locId != null ? locId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HrLocationIpMappingPK)) {
            return false;
        }
        HrLocationIpMappingPK other = (HrLocationIpMappingPK) object;
        if ((this.ip == null && other.ip != null) || (this.ip != null && !this.ip.equals(other.ip))) {
            return false;
        }
        if ((this.locId == null && other.locId != null) || (this.locId != null && !this.locId.equals(other.locId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrLocationIpMappingPK[ip=" + ip + ", locId=" + locId + "]";
    }

}
