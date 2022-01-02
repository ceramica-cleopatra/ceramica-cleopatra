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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author data
 */
@Entity
@Table(name = "CRMK_ACTIVATE_PREVIOUS_DOC_REQ", catalog = "", schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "CrmkActivatePreviousDocReq.findAll", query = "SELECT c FROM CrmkActivatePreviousDocReq c where c.empReqNo=:emp_no and ((c.activate is not null and c.activateDate>:trns_date) or c.activate is null) order by c.id desc"),
    @NamedQuery(name = "CrmkActivatePreviousDocReq.findReqNeedActivate", query = "SELECT c FROM CrmkActivatePreviousDocReq c where c.hrLocId=:locId and c.activate is null order by c.id desc"),
    @NamedQuery(name = "CrmkActivatePreviousDocReq.findById", query = "SELECT c FROM CrmkActivatePreviousDocReq c WHERE c.id = :id"),
    @NamedQuery(name = "CrmkActivatePreviousDocReq.findByEmpReqNo", query = "SELECT c FROM CrmkActivatePreviousDocReq c WHERE c.empReqNo = :empReqNo"),
    @NamedQuery(name = "CrmkActivatePreviousDocReq.findByCrmkSehy", query = "SELECT c FROM CrmkActivatePreviousDocReq c WHERE c.crmkSehy = :crmkSehy"),
    @NamedQuery(name = "CrmkActivatePreviousDocReq.findByDocType", query = "SELECT c FROM CrmkActivatePreviousDocReq c WHERE c.docType = :docType"),
    @NamedQuery(name = "CrmkActivatePreviousDocReq.findByDocId", query = "SELECT c FROM CrmkActivatePreviousDocReq c WHERE c.docId = :docId"),
    @NamedQuery(name = "CrmkActivatePreviousDocReq.findByTrnsDate", query = "SELECT c FROM CrmkActivatePreviousDocReq c WHERE c.trnsDate = :trnsDate"),
    @NamedQuery(name = "CrmkActivatePreviousDocReq.findByEmpActivateNo", query = "SELECT c FROM CrmkActivatePreviousDocReq c WHERE c.empActivateNo = :empActivateNo"),
    @NamedQuery(name = "CrmkActivatePreviousDocReq.findByActivate", query = "SELECT c FROM CrmkActivatePreviousDocReq c WHERE c.activate = :activate"),
    @NamedQuery(name = "CrmkActivatePreviousDocReq.findByActivateDate", query = "SELECT c FROM CrmkActivatePreviousDocReq c WHERE c.activateDate = :activateDate"),
    @NamedQuery(name = "CrmkActivatePreviousDocReq.findByRsrvId", query = "SELECT c FROM CrmkActivatePreviousDocReq c WHERE c.rsrvId = :rsrvId")})
public class CrmkActivatePreviousDocReq implements Serializable {
   private static final long serialVersionUID = 1L;
    @SequenceGenerator(schema="CRMK",name="CRMK_ACTIVATE_PREVIOUS_DOC_SEQ",sequenceName="CRMK_ACTIVATE_PREVIOUS_DOC_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(generator="CRMK_ACTIVATE_PREVIOUS_DOC_SEQ",strategy=GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @JoinColumn(name = "EMP_REQ_NO", referencedColumnName = "EMP_NO")
    @ManyToOne(optional = false)
    private HrEmpInfo empReqNo;
    @Column(name = "CRMK_SEHY")
    private String crmkSehy;
    @Column(name = "DOC_TYPE")
    private Long docType;
    @Column(name = "DOC_ID")
    private Long docId;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date trnsDate;
    @Column(name = "EMP_ACTIVATE_NO")
    private Long empActivateNo;
    @Column(name = "ACTIVATE")
    private Character activate;
    @Column(name = "ACTIVATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activateDate;
    @Column(name = "RSRV_ID")
    private Long rsrvId;
    @JoinColumn(name = "BRN_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CrmkBranch brnId;
    @Column(name = "DOC_NO")
    private Long docNo;
    @Column(name = "DOC_HAND_NO")
    private String docHandNo;
    @Column(name = "DOC_PRD_ID")
    private Long docPrdId;
    @Column(name = "HR_LOC_ID")
    private Long hrLocId;
    public CrmkActivatePreviousDocReq() {
    }

    public Character getActivate() {
        return activate;
    }

    public void setActivate(Character activate) {
        this.activate = activate;
    }

    public Date getActivateDate() {
        return activateDate;
    }

    public void setActivateDate(Date activateDate) {
        this.activateDate = activateDate;
    }

    public String getCrmkSehy() {
        return crmkSehy;
    }

    public void setCrmkSehy(String crmkSehy) {
        this.crmkSehy = crmkSehy;
    }

    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public Long getDocType() {
        return docType;
    }

    public void setDocType(Long docType) {
        this.docType = docType;
    }

    public Long getEmpActivateNo() {
        return empActivateNo;
    }

    public void setEmpActivateNo(Long empActivateNo) {
        this.empActivateNo = empActivateNo;
    }

    public HrEmpInfo getEmpReqNo() {
        return empReqNo;
    }

    public void setEmpReqNo(HrEmpInfo empReqNo) {
        this.empReqNo = empReqNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRsrvId() {
        return rsrvId;
    }

    public void setRsrvId(Long rsrvId) {
        this.rsrvId = rsrvId;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public CrmkBranch getBrnId() {
        return brnId;
    }

    public void setBrnId(CrmkBranch brnId) {
        this.brnId = brnId;
    }

    public String getDocHandNo() {
        return docHandNo;
    }

    public void setDocHandNo(String docHandNo) {
        this.docHandNo = docHandNo;
    }

    public Long getDocNo() {
        return docNo;
    }

    public void setDocNo(Long docNo) {
        this.docNo = docNo;
    }

    public Long getDocPrdId() {
        return docPrdId;
    }

    public void setDocPrdId(Long docPrdId) {
        this.docPrdId = docPrdId;
    }

    public Long getHrLocId() {
        return hrLocId;
    }

    public void setHrLocId(Long hrLocId) {
        this.hrLocId = hrLocId;
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
        if (!(object instanceof CrmkActivatePreviousDocReq)) {
            return false;
        }
        CrmkActivatePreviousDocReq other = (CrmkActivatePreviousDocReq) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.CrmkActivatePreviousDocReq[id=" + id + "]";
    }

}
