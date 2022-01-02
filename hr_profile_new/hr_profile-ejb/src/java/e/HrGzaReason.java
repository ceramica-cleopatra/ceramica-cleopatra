/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_GZA_REASON", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrGzaReason.findAll", query = "SELECT h FROM HrGzaReason h"),
    @NamedQuery(name = "HrGzaReason.findById", query = "SELECT h FROM HrGzaReason h WHERE h.id = :id"),
    @NamedQuery(name = "HrGzaReason.findByName", query = "SELECT h FROM HrGzaReason h WHERE h.name = :name")})
public class HrGzaReason implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @OneToMany(mappedBy="")
    private List<HrGzaHd> hrGzaHdList;

    public List<HrGzaHd> getHrGzaHdList() {
        return hrGzaHdList;
    }

    public void setHrGzaHdList(List<HrGzaHd> hrGzaHdList) {
        this.hrGzaHdList = hrGzaHdList;
    }

   

    public HrGzaReason() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof HrGzaReason)) {
            return false;
        }
        HrGzaReason other = (HrGzaReason) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrGzaReason[id=" + id + "]";
    }

}
