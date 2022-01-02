/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author data
 */
@Entity
@Table(name = "HR_MAN_NOTES_TITLES_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrManNotesTitlesDt.findAll", query = "SELECT h FROM HrManNotesTitlesDt h"),
    @NamedQuery(name = "HrManNotesTitlesDt.findById", query = "SELECT h FROM HrManNotesTitlesDt h WHERE h.id = :id"),
    @NamedQuery(name = "HrManNotesTitlesDt.findByTitle", query = "SELECT h FROM HrManNotesTitlesDt h WHERE h.title = :title"),
    @NamedQuery(name = "HrManNotesTitlesDt.findByDisplayOrder", query = "SELECT h FROM HrManNotesTitlesDt h WHERE h.displayOrder = :displayOrder")})
public class HrManNotesTitlesDt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "DISPLAY_ORDER")
    private BigInteger displayOrder;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrManNotesTitlesHd hrManNotesTitlesHd;

    public HrManNotesTitlesDt() {
    }

    public HrManNotesTitlesDt(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigInteger getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(BigInteger displayOrder) {
        this.displayOrder = displayOrder;
    }

    public HrManNotesTitlesHd getHrManNotesTitlesHd() {
        return hrManNotesTitlesHd;
    }

    public void setHrManNotesTitlesHd(HrManNotesTitlesHd hrManNotesTitlesHd) {
        this.hrManNotesTitlesHd = hrManNotesTitlesHd;
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
        if (!(object instanceof HrManNotesTitlesDt)) {
            return false;
        }
        HrManNotesTitlesDt other = (HrManNotesTitlesDt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrManNotesTitlesDt[id=" + id + "]";
    }

}
