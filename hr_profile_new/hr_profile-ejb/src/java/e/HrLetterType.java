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
@Table(name = "HR_LETTER_TYPE", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrLetterType.findAll", query = "SELECT h FROM HrLetterType h"),
    @NamedQuery(name = "HrLetterType.findById", query = "SELECT h FROM HrLetterType h WHERE h.id = :id"),
    @NamedQuery(name = "HrLetterType.findByName", query = "SELECT h FROM HrLetterType h WHERE h.name = :name")})
public class HrLetterType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @OneToMany(mappedBy="reason")
    private List<HrLetterRequest> hrLetterRequestList;
    public HrLetterType() {
    }

    public List<HrLetterRequest> getHrLetterRequestList() {
        return hrLetterRequestList;
    }

    public void setHrLetterRequestList(List<HrLetterRequest> hrLetterRequestList) {
        this.hrLetterRequestList = hrLetterRequestList;
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
        if (!(object instanceof HrLetterType)) {
            return false;
        }
        HrLetterType other = (HrLetterType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrLetterType[id=" + id + "]";
    }

}
