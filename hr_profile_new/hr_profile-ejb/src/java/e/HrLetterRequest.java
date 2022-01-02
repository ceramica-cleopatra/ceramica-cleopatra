/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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

/**
 *
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_LETTER_REQUEST", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrLetterRequest.findAll", query = "SELECT h FROM HrLetterRequest h where h.empNo=:emp"),
    @NamedQuery(name = "HrLetterRequest.findNotRead", query = "SELECT h FROM HrLetterRequest h where h.empNo=:emp and h.readDone is null and h.accepted is not null"),
    @NamedQuery(name = "HrLetterRequest.findMaxId", query = "SELECT max(h.id) FROM HrLetterRequest h"),
    @NamedQuery(name = "HrLetterRequest.findById", query = "SELECT h FROM HrLetterRequest h WHERE h.id = :id"),
    @NamedQuery(name = "HrLetterRequest.findByEmpNo", query = "SELECT h FROM HrLetterRequest h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrLetterRequest.findByDest", query = "SELECT h FROM HrLetterRequest h WHERE h.dest = :dest"),
    @NamedQuery(name = "HrLetterRequest.findByReason", query = "SELECT h FROM HrLetterRequest h WHERE h.reason = :reason"),
    @NamedQuery(name = "HrLetterRequest.findByAccepted", query = "SELECT h FROM HrLetterRequest h WHERE h.accepted = :accepted"),
    @NamedQuery(name = "HrLetterRequest.findByCanceled", query = "SELECT h FROM HrLetterRequest h WHERE h.canceled = :canceled")})
public class HrLetterRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "EMP_NO")
    private Long empNo;
    @Column(name = "DEST")
    private String dest;
    @ManyToOne
    @JoinColumn(name = "REASON")
    private HrLetterType reason;
    @Column(name = "ACCEPTED")
    private Character accepted;
    @Column(name = "CANCELED")
    private Character canceled;
    @Column(name = "READ_DONE")
    private Character readDone;
    @Column(name = "TRNS_DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date trnsDate;
    @Column(name="RESPONSE_DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date respnseDate;

    public Character getReadDone() {
        return readDone;
    }

    public void setReadDone(Character readDone) {
        this.readDone = readDone;
    }

    
    public Date getRespnseDate() {
        return respnseDate;
    }

    public void setRespnseDate(Date respnseDate) {
        this.respnseDate = respnseDate;
    }

    
    public HrLetterRequest() {
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
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

    public HrLetterType getReason() {
        return reason;
    }

    public void setReason(HrLetterType reason) {
        this.reason = reason;
    }

  

    public Character getAccepted() {
        return accepted;
    }

    public void setAccepted(Character accepted) {
        this.accepted = accepted;
    }

    public Character getCanceled() {
        return canceled;
    }

    public void setCanceled(Character canceled) {
        this.canceled = canceled;
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
        if (!(object instanceof HrLetterRequest)) {
            return false;
        }
        HrLetterRequest other = (HrLetterRequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrLetterRequest[id=" + id + "]";
    }

}
