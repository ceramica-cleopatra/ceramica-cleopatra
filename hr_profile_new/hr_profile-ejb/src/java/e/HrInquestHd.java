/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author data
 */
@Entity
@Table(name = "HR_INQUEST_HD", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrInquestHd.findAllForMng", query = "SELECT h FROM HrInquestHd h where h.hrConf is null and exists (select x from HrInquestDt x where x.hrInquestHd=h and x.inqRec is not null) order by h.no desc"),
    @NamedQuery(name = "HrInquestHd.findAllForHr", query = "SELECT h FROM HrInquestHd h where h.mngConf='Y' order by h.no desc"),
    @NamedQuery(name = "HrInquestHd.findById", query = "SELECT h FROM HrInquestHd h WHERE h.id = :id"),
    @NamedQuery(name = "HrInquestHd.findByNo", query = "SELECT h FROM HrInquestHd h WHERE h.no = :no"),
    @NamedQuery(name = "HrInquestHd.findByInqSum", query = "SELECT h FROM HrInquestHd h WHERE h.inqSum = :inqSum"),
    @NamedQuery(name = "HrInquestHd.findByTrnsDate", query = "SELECT h FROM HrInquestHd h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrInquestHd.findByHrConf", query = "SELECT h FROM HrInquestHd h WHERE h.hrConf = :hrConf"),
    @NamedQuery(name = "HrInquestHd.findByNotes", query = "SELECT h FROM HrInquestHd h WHERE h.notes = :notes")})
public class HrInquestHd implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "NO")
    private BigInteger no;
    @Column(name = "INQ_SUM")
    private String inqSum;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @Column(name = "HR_CONF")
    private Character hrConf;
     @Column(name = "MNG_CONF")
    private Character mngConf;
    @Column(name = "NOTES")
    private String notes;
    @OneToMany(mappedBy = "hrInquestHd")
    private List<HrInquestDt> hrInquestDtList;

    public HrInquestHd() {
    }

    public HrInquestHd(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigInteger getNo() {
        return no;
    }

    public void setNo(BigInteger no) {
        this.no = no;
    }

    public String getInqSum() {
        return inqSum;
    }

    public void setInqSum(String inqSum) {
        this.inqSum = inqSum;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public Character getHrConf() {
        return hrConf;
    }

    public void setHrConf(Character hrConf) {
        this.hrConf = hrConf;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<HrInquestDt> getHrInquestDtList() {
        return hrInquestDtList;
    }

    public void setHrInquestDtList(List<HrInquestDt> hrInquestDtList) {
        this.hrInquestDtList = hrInquestDtList;
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
        if (!(object instanceof HrInquestHd)) {
            return false;
        }
        HrInquestHd other = (HrInquestHd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public Character getMngConf() {
        return mngConf;
    }

    public void setMngConf(Character mngConf) {
        this.mngConf = mngConf;
    }


    

    @Override
    public String toString() {
        return "e.HrInquestHd[id=" + id + "]";
    }

}
