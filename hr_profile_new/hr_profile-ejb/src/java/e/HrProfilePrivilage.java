/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ahmed abbas
 */

@Entity
@Cacheable(true)
@Table(name = "HR_PROFILE_PRIVILAGE", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrProfilePrivilage.findAll", query = "SELECT h FROM HrProfilePrivilage h order by h.menuId.menuOrder"),
    @NamedQuery(name = "HrProfilePrivilage.findById", query = "SELECT h FROM HrProfilePrivilage h WHERE h.id = :id"),
    @NamedQuery(name = "HrProfilePrivilage.findByEmpNo", query = "SELECT distinct h FROM HrProfilePrivilage h WHERE h.empNo = :empNo order by h.menuId.menuOrder"),
    @NamedQuery(name = "HrProfilePrivilage.findByMenuId", query = "SELECT h FROM HrProfilePrivilage h WHERE h.menuId = :menuId"),
    @NamedQuery(name = "HrProfilePrivilage.findByArabicName", query = "SELECT h FROM HrProfilePrivilage h WHERE h.arabicName = :arabicName")})
public class HrProfilePrivilage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private BigInteger id;
    @Basic(optional = false)
    @Column(name = "EMP_NO")
    private long empNo;
    @JoinColumn(name = "MENU_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrMenuTable menuId;
    @Column(name = "ARABIC_NAME")
    private String arabicName;

    public HrProfilePrivilage() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(long empNo) {
        this.empNo = empNo;
    }

    public HrMenuTable getMenuId() {
        return menuId;
    }

    public void setMenuId(HrMenuTable menuId) {
        this.menuId = menuId;
    }

    

    public String getArabicName() {
        return arabicName;
    }

    public void setArabicName(String arabicName) {
        this.arabicName = arabicName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0) ;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HrProfilePrivilage)) {
            return false;
        }
        HrProfilePrivilage other = (HrProfilePrivilage) object;
        if ((this.empNo == 0 && other.empNo != 0) || (this.empNo != 0 && other.empNo == 0) || (this.menuId == null && other.menuId != null) || (this.menuId != null && other.menuId == null) || (this.empNo != 0 && this.menuId!=null && (this.empNo!=other.empNo || !this.menuId.equals(other.menuId)))) {
            return false;
        }
        return true;
    }

}
