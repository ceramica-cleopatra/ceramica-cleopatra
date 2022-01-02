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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ahmed abbas
 */

@Entity
@Table(name = "HR_SCHEDULE", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrSchedule.findAll", query = "SELECT h FROM HrSchedule h where h.empNo=:emp"),
    @NamedQuery(name = "HrSchedule.findAllNotRead", query = "SELECT h FROM HrSchedule h where h.empNo=:emp and h.readDone is null"),
    @NamedQuery(name = "HrSchedule.chkReadDone", query = "SELECT count(h.id) FROM HrSchedule h where h.empNo=:emp and h.readDone is null"),
    @NamedQuery(name = "HrSchedule.findAllForMng", query = "SELECT h FROM HrSchedule h where h.mngNo.empNo=:mng"),
    @NamedQuery(name = "HrSchedule.getseq", query = "select max(o.id) from HrSchedule o"),
    @NamedQuery(name = "HrSchedule.getSchedule", query = "select o from HrSchedule o where o.id=:x"),
    @NamedQuery(name = "HrSchedule.findById", query = "SELECT h FROM HrSchedule h WHERE h.id = :id"),
    @NamedQuery(name = "HrSchedule.findByEmpNo", query = "SELECT h FROM HrSchedule h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrSchedule.findByTitle", query = "SELECT h FROM HrSchedule h WHERE h.title = :title"),
    @NamedQuery(name = "HrSchedule.findByFromDate", query = "SELECT h FROM HrSchedule h WHERE h.fromDate = :fromDate"),
    @NamedQuery(name = "HrSchedule.findByToDate", query = "SELECT h FROM HrSchedule h WHERE h.toDate = :toDate")})
public class HrSchedule implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "EMP_NO")
    private Long empNo;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "FROM_DATE")
    @Temporal(TemporalType.DATE)
    private Date fromDate;
    @Column(name = "TO_DATE")
    @Temporal(TemporalType.DATE)
    private Date toDate;
    @Column(name="READ_DONE")
    private String readDone;
    @ManyToOne
    @JoinColumn(name = "MNG_NO")
    private HrEmpInfo mngNo;
    @OneToMany(mappedBy="hrSchedule")
    private List<HrScheduleDt> hrScheduleDtList;

    public String getReadDone() {
        return readDone;
    }

    public void setReadDone(String readDone) {
        this.readDone = readDone;
    }

    
    public List<HrScheduleDt> getHrScheduleDtList() {
        return hrScheduleDtList;
    }

    public void setHrScheduleDtList(List<HrScheduleDt> hrScheduleDtList) {
        this.hrScheduleDtList = hrScheduleDtList;
    }

    public HrEmpInfo getMngNo() {
        return mngNo;
    }

    public void setMngNo(HrEmpInfo mngNo) {
        this.mngNo = mngNo;
    }
    
 
    
    public HrSchedule() {
    }

    public Long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Long empNo) {
        this.empNo = empNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
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
        if (!(object instanceof HrSchedule)) {
            return false;
        }
        HrSchedule other = (HrSchedule) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrSchedule[id=" + id + "]";
    }

}
