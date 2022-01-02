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
import javax.annotation.Generated;
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
 * @author a.abbas
 */

@Entity
@Table(name = "HR_ADVANCE_ZAMALA_HD", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrAdvanceZamalaHd.findAll", query = "SELECT h FROM HrAdvanceZamalaHd h"),
    @NamedQuery(name = "HrAdvanceZamalaHd.findCurrentSerial", query = "SELECT max(h.serial) FROM HrAdvanceZamalaHd h"),
    @NamedQuery(name = "HrAdvanceZamalaHd.findById", query = "SELECT h FROM HrAdvanceZamalaHd h WHERE h.id = :id"),
    @NamedQuery(name = "HrAdvanceZamalaHd.findByEmpNo", query = "SELECT h FROM HrAdvanceZamalaHd h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrAdvanceZamalaHd.findByTrnsDate", query = "SELECT h FROM HrAdvanceZamalaHd h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrAdvanceZamalaHd.findByNote", query = "SELECT h FROM HrAdvanceZamalaHd h WHERE h.note = :note"),
    @NamedQuery(name = "HrAdvanceZamalaHd.findBySerial", query = "SELECT h FROM HrAdvanceZamalaHd h WHERE h.serial = :serial"),
    @NamedQuery(name = "HrAdvanceZamalaHd.findByReqId", query = "SELECT h FROM HrAdvanceZamalaHd h WHERE h.reqId = :reqId"),
    @NamedQuery(name = "HrAdvanceZamalaHd.findByCanceled", query = "SELECT h FROM HrAdvanceZamalaHd h WHERE h.canceled = :canceled"),
    @NamedQuery(name = "HrAdvanceZamalaHd.findBySrfOrdrNo", query = "SELECT h FROM HrAdvanceZamalaHd h WHERE h.srfOrdrNo = :srfOrdrNo")})
public class HrAdvanceZamalaHd implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(schema="HR",name="HR_ADVANCE_ZAMALA_HD_SEQ",sequenceName="HR_ADVANCE_ZAMALA_HD_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(generator="HR_ADVANCE_ZAMALA_HD_SEQ",strategy=GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @JoinColumn(name = "EMP_NO",referencedColumnName="EMP_NO")
    @ManyToOne
    private HrEmpInfo empNo;
    @Column(name = "GUARANTEE_NO")
    private Long guaranteeNo;
    @Column(name = "MNG_NO")
    private Long mngNo;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date trnsDate;
    @Column(name = "NOTE")
    private String note;
    @Column(name = "SERIAL")
    private Long serial;
    @Column(name = "REQ_ID")
    private Long reqId;
    @Column(name = "CANCELED")
    private Character canceled;
    @Column(name = "SRF_ORDR_NO")
    private String srfOrdrNo;
    @OneToMany(mappedBy = "hrAdvanceZamalaHd",cascade=CascadeType.PERSIST)
    private List<HrAdvanceZamalaDt> hrAdvanceZamalaDtList;

    public HrAdvanceZamalaHd() {
    }

    public HrAdvanceZamalaHd(Long id) {
        this.id = id;
    }

    public HrEmpInfo getEmpNo() {
        return empNo;
    }

    public void setEmpNo(HrEmpInfo empNo) {
        this.empNo = empNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReqId() {
        return reqId;
    }

    public void setReqId(Long reqId) {
        this.reqId = reqId;
    }

    public Long getSerial() {
        return serial;
    }

    public void setSerial(Long serial) {
        this.serial = serial;
    }

   

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

   
    public Character getCanceled() {
        return canceled;
    }

    public void setCanceled(Character canceled) {
        this.canceled = canceled;
    }

    public String getSrfOrdrNo() {
        return srfOrdrNo;
    }

    public void setSrfOrdrNo(String srfOrdrNo) {
        this.srfOrdrNo = srfOrdrNo;
    }

    public List<HrAdvanceZamalaDt> getHrAdvanceZamalaDtList() {
        return hrAdvanceZamalaDtList;
    }

    public void setHrAdvanceZamalaDtList(List<HrAdvanceZamalaDt> hrAdvanceZamalaDtList) {
        this.hrAdvanceZamalaDtList = hrAdvanceZamalaDtList;
    }

    public Long getGuaranteeNo() {
        return guaranteeNo;
    }

    public void setGuaranteeNo(Long guaranteeNo) {
        this.guaranteeNo = guaranteeNo;
    }

    public Long getMngNo() {
        return mngNo;
    }

    public void setMngNo(Long mngNo) {
        this.mngNo = mngNo;
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
        if (!(object instanceof HrAdvanceZamalaHd)) {
            return false;
        }
        HrAdvanceZamalaHd other = (HrAdvanceZamalaHd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrAdvanceZamalaHd[id=" + id + "]";
    }

}
