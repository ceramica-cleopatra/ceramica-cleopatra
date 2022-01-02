/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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
 * @author Administrator
 */
@Entity
@Table(name = "CRMK_C_ME2ORDR_HD", catalog = "", schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "CrmkCMe2ordrHd.findAll", query = "SELECT c FROM CrmkCMe2ordrHd c"),
    @NamedQuery(name = "CrmkCMe2ordrHd.findById", query = "SELECT c FROM CrmkCMe2ordrHd c WHERE c.id = :id"),
    @NamedQuery(name = "CrmkCMe2ordrHd.findByNo", query = "SELECT c FROM CrmkCMe2ordrHd c WHERE c.no = :no"),
    @NamedQuery(name = "CrmkCMe2ordrHd.findByTrnsDate", query = "SELECT c FROM CrmkCMe2ordrHd c WHERE c.trnsDate = :trnsDate"),
    @NamedQuery(name = "CrmkCMe2ordrHd.findByClosed", query = "SELECT c FROM CrmkCMe2ordrHd c WHERE c.closed = :closed"),
    @NamedQuery(name = "CrmkCMe2ordrHd.findByCanceled", query = "SELECT c FROM CrmkCMe2ordrHd c WHERE c.canceled = :canceled"),
    @NamedQuery(name = "CrmkCMe2ordrHd.findByCrtDate", query = "SELECT c FROM CrmkCMe2ordrHd c WHERE c.crtDate = :crtDate"),
    @NamedQuery(name = "CrmkCMe2ordrHd.findByEdtDate", query = "SELECT c FROM CrmkCMe2ordrHd c WHERE c.edtDate = :edtDate"),
    @NamedQuery(name = "CrmkCMe2ordrHd.findByClsDate", query = "SELECT c FROM CrmkCMe2ordrHd c WHERE c.clsDate = :clsDate"),
    @NamedQuery(name = "CrmkCMe2ordrHd.findByOpnDate", query = "SELECT c FROM CrmkCMe2ordrHd c WHERE c.opnDate = :opnDate"),
    @NamedQuery(name = "CrmkCMe2ordrHd.findByDelDate", query = "SELECT c FROM CrmkCMe2ordrHd c WHERE c.delDate = :delDate"),
    @NamedQuery(name = "CrmkCMe2ordrHd.findByUndelDate", query = "SELECT c FROM CrmkCMe2ordrHd c WHERE c.undelDate = :undelDate"),
    @NamedQuery(name = "CrmkCMe2ordrHd.findByNotes", query = "SELECT c FROM CrmkCMe2ordrHd c WHERE c.notes = :notes"),
    @NamedQuery(name = "CrmkCMe2ordrHd.findByHandNo", query = "SELECT c FROM CrmkCMe2ordrHd c WHERE c.handNo = :handNo"),
    @NamedQuery(name = "CrmkCMe2ordrHd.findByOrdrHandNo", query = "SELECT c FROM CrmkCMe2ordrHd c WHERE c.ordrHandNo = :ordrHandNo"),
    @NamedQuery(name = "CrmkCMe2ordrHd.findBySrfOrdrNo", query = "SELECT c FROM CrmkCMe2ordrHd c WHERE c.srfOrdrNo = :srfOrdrNo")})
public class CrmkCMe2ordrHd implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name="BRN_ID",referencedColumnName="ID")
    private CrmkBranch crmkBranch;
    @Column(name="PRD_ID")
    private Long prdId;
    @Basic(optional = false)
    @Column(name = "NO")
    private String no;
    @Basic(optional = false)
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @ManyToOne
    @JoinColumn(name="ORDR_ID",referencedColumnName="ID")
    private CrmkOrdrHd crmkOrdrHd;
    @Column(name = "CLOSED")
    private Character closed;
    @Column(name = "CANCELED")
    private Character canceled;
    @Basic(optional = false)
    @Column(name = "CRT_DATE")
    @Temporal(TemporalType.DATE)
    private Date crtDate;
    @Column(name = "EDT_DATE")
    @Temporal(TemporalType.DATE)
    private Date edtDate;
    @Column(name = "CLS_DATE")
    @Temporal(TemporalType.DATE)
    private Date clsDate;
    @Column(name = "OPN_DATE")
    @Temporal(TemporalType.DATE)
    private Date opnDate;
    @Column(name = "DEL_DATE")
    @Temporal(TemporalType.DATE)
    private Date delDate;
    @Column(name = "UNDEL_DATE")
    @Temporal(TemporalType.DATE)
    private Date undelDate;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "HAND_NO")
    private String handNo;
    @Column(name = "ORDR_HAND_NO")
    private String ordrHandNo;
    @Basic(optional = false)
    @Column(name = "SRF_ORDR_NO")
    private long srfOrdrNo;
    @OneToMany(mappedBy="crmkCMe2ordrHd")
    private List<CrmkCMe2ordrDt> crmkCMe2ordrDtList;

    public CrmkCMe2ordrHd() {
    }

    public CrmkCMe2ordrHd(Long id) {
        this.id = id;
    }

    public CrmkCMe2ordrHd(Long id, String no, Date trnsDate, Date crtDate, long srfOrdrNo) {
        this.id = id;
        this.no = no;
        this.trnsDate = trnsDate;
        this.crtDate = crtDate;
        this.srfOrdrNo = srfOrdrNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public Character getClosed() {
        return closed;
    }

    public void setClosed(Character closed) {
        this.closed = closed;
    }

    public Character getCanceled() {
        return canceled;
    }

    public void setCanceled(Character canceled) {
        this.canceled = canceled;
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    public Date getEdtDate() {
        return edtDate;
    }

    public void setEdtDate(Date edtDate) {
        this.edtDate = edtDate;
    }

    public Date getClsDate() {
        return clsDate;
    }

    public void setClsDate(Date clsDate) {
        this.clsDate = clsDate;
    }

    public Date getOpnDate() {
        return opnDate;
    }

    public void setOpnDate(Date opnDate) {
        this.opnDate = opnDate;
    }

    public Date getDelDate() {
        return delDate;
    }

    public void setDelDate(Date delDate) {
        this.delDate = delDate;
    }

    public Date getUndelDate() {
        return undelDate;
    }

    public void setUndelDate(Date undelDate) {
        this.undelDate = undelDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getHandNo() {
        return handNo;
    }

    public void setHandNo(String handNo) {
        this.handNo = handNo;
    }

    public String getOrdrHandNo() {
        return ordrHandNo;
    }

    public void setOrdrHandNo(String ordrHandNo) {
        this.ordrHandNo = ordrHandNo;
    }

    public long getSrfOrdrNo() {
        return srfOrdrNo;
    }

    public void setSrfOrdrNo(long srfOrdrNo) {
        this.srfOrdrNo = srfOrdrNo;
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
        if (!(object instanceof CrmkCMe2ordrHd)) {
            return false;
        }
        CrmkCMe2ordrHd other = (CrmkCMe2ordrHd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.CrmkCMe2ordrHd[id=" + id + "]";
    }

}
