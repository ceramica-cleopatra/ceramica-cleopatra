/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ahmed abbas
 */

@Entity
@Cacheable(true)
@Table(name = "HR_MENU_TABLE", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrMenuTable.findAll", query = "SELECT h FROM HrMenuTable h"),
    @NamedQuery(name = "HrMenuTable.findMaxId", query = "SELECT max(h.id) FROM HrMenuTable h"),
    @NamedQuery(name = "HrMenuTable.findMenuNames", query = "SELECT h FROM HrMenuTable h where h.parentId is not null"),
    @NamedQuery(name = "HrMenuTable.findById", query = "SELECT h FROM HrMenuTable h WHERE h.id = :id"),
    @NamedQuery(name = "HrMenuTable.findByMenuName", query = "SELECT h FROM HrMenuTable h WHERE h.menuName = :menuName"),
    @NamedQuery(name = "HrMenuTable.findByArabicName", query = "SELECT h FROM HrMenuTable h WHERE h.arabicName = :arabicName"),
    @NamedQuery(name = "HrMenuTable.findByParentId", query = "SELECT h FROM HrMenuTable h WHERE h.parentId = :parentId"),
    @NamedQuery(name = "HrMenuTable.findByMenuOrder", query = "SELECT h FROM HrMenuTable h WHERE h.menuOrder = :menuOrder"),
    @NamedQuery(name = "HrMenuTable.findAllRoot", query = "select o from HrMenuTable o join o.hrProfilePrivilages p  where o.parentId is null and p.empNo=:emp order by o.menuOrder desc"),
    @NamedQuery(name = "HrMenuTable.findAllChild", query = "select o from HrMenuTable o join o.hrProfilePrivilages p where o.parentId is not null and p.empNo=:emp order by o.menuOrder desc"),
    @NamedQuery(name = "HrMenuTable.thereIsChild", query = "select count(o.id) from HrMenuTable o join o.hrProfilePrivilages p where o.parentId=:pid and p.empNo=:emp")})
public class HrMenuTable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "MENU_NAME")
    private String menuName;
    @Column(name = "ARABIC_NAME")
    private String arabicName;
    @Column(name = "ICON")
    private String icon;
    @Column(name = "PARENT_ID")
    private Long parentId;
    @Column(name = "MENU_ORDER")
    private Long menuOrder;
    
    @OneToMany(mappedBy = "menuId")
    private List<HrProfilePrivilage> hrProfilePrivilages;
    public HrMenuTable() {
    }

    public HrMenuTable(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getArabicName() {
        return arabicName;
    }

    public void setArabicName(String arabicName) {
        this.arabicName = arabicName;
    }

    
   

    public Long getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Long menuOrder) {
        this.menuOrder = menuOrder;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HrMenuTable)) {
            return false;
        }
        HrMenuTable other = (HrMenuTable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    

    @Override
    public String toString() {
        return "e.HrMenuTable[id=" + id + "]";
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<HrProfilePrivilage> getHrProfilePrivilages() {
        return hrProfilePrivilages;
    }

    public void setHrProfilePrivilages(List<HrProfilePrivilage> hrProfilePrivilages) {
        this.hrProfilePrivilages = hrProfilePrivilages;
    }

}
