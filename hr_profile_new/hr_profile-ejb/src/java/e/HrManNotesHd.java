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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author data
 */
@Entity
@Table(name = "HR_MAN_NOTES_HD", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrManNotesHd.findAll", query = "SELECT h FROM HrManNotesHd h"),
    @NamedQuery(name = "HrManNotesHd.findById", query = "SELECT h FROM HrManNotesHd h WHERE h.id = :id"),
    @NamedQuery(name = "HrManNotesHd.findByEmpNo", query = "SELECT h FROM HrManNotesHd h WHERE h.empNo.empNo = :empNo order by h.trnsDate desc"),
    @NamedQuery(name = "HrManNotesHd.findByTrnsDate", query = "SELECT h FROM HrManNotesHd h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrManNotesHd.findByApproved", query = "SELECT h FROM HrManNotesHd h WHERE h.approved = :approved"),
    @NamedQuery(name = "HrManNotesHd.findByApproveDate", query = "SELECT h FROM HrManNotesHd h WHERE h.approveDate = :approveDate"),
    @NamedQuery(name = "HrManNotesHd.findByDone", query = "SELECT h FROM HrManNotesHd h WHERE h.done = :done"),
    @NamedQuery(name = "HrManNotesHd.findByMngNo", query = "SELECT h FROM HrManNotesHd h WHERE h.mngNo = :mngNo"),
    @NamedQuery(name = "HrManNotesHd.findByBonus", query = "SELECT h FROM HrManNotesHd h WHERE h.bonus = :bonus"),
    @NamedQuery(name = "HrManNotesHd.findNotApproved", query = "SELECT h FROM HrManNotesHd h WHERE h.approved is null and h.done='Y' order by h.trnsDate")}
)

public class HrManNotesHd implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="HR_MAN_NOTES_HD_SEQ", sequenceName="HR_MAN_NOTES_HD_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HR_MAN_NOTES_HD_SEQ")
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "EMP_NO",referencedColumnName="EMP_NO")
    private HrEmpInfo empNo;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @Column(name = "APPROVED")
    private Character approved;
    @Column(name = "APPROVE_DATE")
    @Temporal(TemporalType.DATE)
    private Date approveDate;
    @Column(name = "DONE")
    private Character done;
    @ManyToOne
    @JoinColumn(referencedColumnName="EMP_NO",name = "MNG_NO")
    private HrEmpInfo mngNo;
    @Column(name = "BONUS")
    private BigInteger bonus;
    @OneToMany(mappedBy = "hrManNotesHd",cascade=CascadeType.ALL)
    private List<HrManNotesDt> hrManNotesDtList;

    public HrManNotesHd() {
    }

    public HrManNotesHd(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HrEmpInfo getEmpNo() {
        return empNo;
    }

    public void setEmpNo(HrEmpInfo empNo) {
        this.empNo = empNo;
    }

    

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public Character getApproved() {
        return approved;
    }

    public void setApproved(Character approved) {
        this.approved = approved;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public Character getDone() {
        return done;
    }

    public void setDone(Character done) {
        this.done = done;
    }

    public HrEmpInfo getMngNo() {
        return mngNo;
    }

    public void setMngNo(HrEmpInfo mngNo) {
        this.mngNo = mngNo;
    }

   
    public BigInteger getBonus() {
        return bonus;
    }

    public void setBonus(BigInteger bonus) {
        this.bonus = bonus;
    }

    public List<HrManNotesDt> getHrManNotesDtList() {
        return hrManNotesDtList;
    }

    public void setHrManNotesDtList(List<HrManNotesDt> hrManNotesDtList) {
        this.hrManNotesDtList = hrManNotesDtList;
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
        if (!(object instanceof HrManNotesHd)) {
            return false;
        }
        HrManNotesHd other = (HrManNotesHd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrManNotesHd[id=" + id + "]";
    }

}
