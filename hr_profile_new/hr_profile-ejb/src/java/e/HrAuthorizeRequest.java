/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ahmed abbas
 */

@Entity
@Table(name = "HR_AUTHORIZE_REQUEST", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrAuthorizeRequest.findAll", query = "SELECT h FROM HrAuthorizeRequest h where h.empNo=:emp and h.authorizeDate>=:authorize_date"),
    @NamedQuery(name = "HrAuthorizeRequest.findMax", query = "SELECT max(h.id) FROM HrAuthorizeRequest h"),
    @NamedQuery(name = "HrAuthorizeRequest.chkAuthorizeExist", query = "SELECT count(h.id) FROM HrAuthorizeRequest h where h.authorizeDate=:authorize_date and h.empNo=:emp and (h.id<>:id or :id is null)"),
    @NamedQuery(name = "HrAuthorizeRequest.minutesSum", query = "SELECT sum(h.minutesNo) FROM HrAuthorizeRequest h where h.empNo=:emp and h.flag is null and (h.cancel is null or h.cancel='N') and (h.id<>:id or :id is null) and h.authorizeDate between :from_date and :to_date"),
    @NamedQuery(name = "HrAuthorizeRequest.findById", query = "SELECT h FROM HrAuthorizeRequest h WHERE h.id = :id"),
    @NamedQuery(name = "HrAuthorizeRequest.findByEmpNo", query = "SELECT h FROM HrAuthorizeRequest h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrAuthorizeRequest.findByMinutesNo", query = "SELECT h FROM HrAuthorizeRequest h WHERE h.minutesNo = :minutesNo"),
    @NamedQuery(name = "HrAuthorizeRequest.findByTrnsDate", query = "SELECT h FROM HrAuthorizeRequest h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrAuthorizeRequest.findByMngNo", query = "SELECT h FROM HrAuthorizeRequest h WHERE h.mngNo = :mngNo"),
    @NamedQuery(name = "HrAuthorizeRequest.findByAccepted", query = "SELECT h FROM HrAuthorizeRequest h WHERE h.accepted = :accepted"),
    @NamedQuery(name = "HrAuthorizeRequest.findByFlag", query = "SELECT h FROM HrAuthorizeRequest h WHERE h.flag = :flag"),
    @NamedQuery(name = "HrAuthorizeRequest.findByType", query = "SELECT h FROM HrAuthorizeRequest h WHERE h.type = :type"),
    @NamedQuery(name = "HrAuthorizeRequest.findByAuthorizeDate", query = "SELECT h FROM HrAuthorizeRequest h WHERE h.authorizeDate = :authorizeDate"),
    @NamedQuery(name = "HrAuthorizeRequest.findByCancel", query = "SELECT h FROM HrAuthorizeRequest h WHERE h.cancel = :cancel")})
public class HrAuthorizeRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "EMP_NO")
    private Long empNo;
    @Column(name = "MINUTES_NO")
    private Long minutesNo;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date trnsDate;
    @ManyToOne
    @JoinColumn(name = "MNG_NO")
    private HrEmpInfo mngNo;
    @Column(name = "ACCEPTED")
    private String accepted;
    @Column(name = "FLAG")
    private String flag;
    @Column(name = "TYPE")
    private Long type;
    @Column(name = "AUTHORIZE_DATE")
    @Temporal(TemporalType.DATE)
    private Date authorizeDate;
    @Column(name = "CANCEL")
    private String cancel;
    @Column(name = "APPROVE_DATE")
    @Temporal(TemporalType.DATE)
    private Date approveDate;

    public HrAuthorizeRequest() {
    }

   

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

   
    public Date getAuthorizeDate() {
        return authorizeDate;
    }

    public void setAuthorizeDate(Date authorizeDate) {
        this.authorizeDate = authorizeDate;
    }

    public String getAccepted() {
        return accepted;
    }

    public void setAccepted(String accepted) {
        this.accepted = accepted;
    }

    public String getCancel() {
        return cancel;
    }

    public void setCancel(String cancel) {
        this.cancel = cancel;
    }

    public Long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Long empNo) {
        this.empNo = empNo;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMinutesNo() {
        return minutesNo;
    }

    public void setMinutesNo(Long minutesNo) {
        this.minutesNo = minutesNo;
    }

    public HrEmpInfo getMngNo() {
        return mngNo;
    }

    public void setMngNo(HrEmpInfo mngNo) {
        this.mngNo = mngNo;
    }

   

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
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
        if (!(object instanceof HrAuthorizeRequest)) {
            return false;
        }
        HrAuthorizeRequest other = (HrAuthorizeRequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrAuthorizeRequest[id=" + id + "]";
    }

}
