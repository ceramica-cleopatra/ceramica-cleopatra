/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigInteger;
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

/**
 *
 * @author data
 */
@Entity
@Table(name = "CRMK_ACTIVATE_PREVIOUS_DOC_VU", catalog = "", schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "CrmkActivatePreviousDocVu.findAll", query = "SELECT c FROM CrmkActivatePreviousDocVu c where (c.docType=:doc_type or :doc_type is null) and (c.docBrnId.id=:doc_brn_id or :doc_brn_id is null)"
    + " and (c.crmkSehy=:crmk_sehy or :crmk_sehy is null) and (c.docNo=:doc_no or :doc_no is null) and (c.docHandNo=:doc_hand_no or :doc_hand_no is null) and (c.docPrdId=:doc_prd_id or :doc_prd_id is null)"),
    @NamedQuery(name = "CrmkActivatePreviousDocVu.findByRsrvId", query = "SELECT c FROM CrmkActivatePreviousDocVu c WHERE c.rsrvId = :rsrvId"),
    @NamedQuery(name = "CrmkActivatePreviousDocVu.findByDocBrnId", query = "SELECT c FROM CrmkActivatePreviousDocVu c WHERE c.docBrnId = :docBrnId"),
    @NamedQuery(name = "CrmkActivatePreviousDocVu.findByCrmkSehy", query = "SELECT c FROM CrmkActivatePreviousDocVu c WHERE c.crmkSehy = :crmkSehy"),
    @NamedQuery(name = "CrmkActivatePreviousDocVu.findByDocType", query = "SELECT c FROM CrmkActivatePreviousDocVu c WHERE c.docType = :docType"),
    @NamedQuery(name = "CrmkActivatePreviousDocVu.findByDocId", query = "SELECT c FROM CrmkActivatePreviousDocVu c WHERE c.docId = :docId"),
    @NamedQuery(name = "CrmkActivatePreviousDocVu.findByDocNo", query = "SELECT c FROM CrmkActivatePreviousDocVu c WHERE c.docNo = :docNo"),
    @NamedQuery(name = "CrmkActivatePreviousDocVu.findByDocHandNo", query = "SELECT c FROM CrmkActivatePreviousDocVu c WHERE c.docHandNo = :docHandNo"),
    @NamedQuery(name = "CrmkActivatePreviousDocVu.findByDocPrdId", query = "SELECT c FROM CrmkActivatePreviousDocVu c WHERE c.docPrdId = :docPrdId")})
public class CrmkActivatePreviousDocVu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "RSRV_ID")
    private long rsrvId;
    @JoinColumn(name = "DOC_BRN_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CrmkBranch docBrnId;
    @Column(name = "CRMK_SEHY")
    private String crmkSehy;
    @Column(name = "DOC_TYPE")
    private Long docType;
    @Column(name = "DOC_ID")
    private Long docId;
    @Column(name = "DOC_NO")
    private Long docNo;
    @Column(name = "DOC_HAND_NO")
    private String docHandNo;
    @Column(name = "DOC_PRD_ID")
    private Long docPrdId;

    public CrmkActivatePreviousDocVu() {
    }

    public long getRsrvId() {
        return rsrvId;
    }

    public void setRsrvId(long rsrvId) {
        this.rsrvId = rsrvId;
    }

    public CrmkBranch getDocBrnId() {
        return docBrnId;
    }

    public void setDocBrnId(CrmkBranch docBrnId) {
        this.docBrnId = docBrnId;
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

    public Long getDocNo() {
        return docNo;
    }

    public void setDocNo(Long docNo) {
        this.docNo = docNo;
    }

    

    public String getDocHandNo() {
        return docHandNo;
    }

    public void setDocHandNo(String docHandNo) {
        this.docHandNo = docHandNo;
    }

    public Long getDocPrdId() {
        return docPrdId;
    }

    public void setDocPrdId(Long docPrdId) {
        this.docPrdId = docPrdId;
    }

    public Long getDocType() {
        return docType;
    }

    public void setDocType(Long docType) {
        this.docType = docType;
    }

}
