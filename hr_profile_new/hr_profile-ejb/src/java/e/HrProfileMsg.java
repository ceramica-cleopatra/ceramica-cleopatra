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
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Administrator
 */
@Cacheable(true)
@Entity
@Table(name = "HR_PROFILE_MSG", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrProfileMsg.findAll", query = "SELECT h FROM HrProfileMsg h where h.empNo = :empNo and (h.readDone is null or h.readDone='N')"),
    @NamedQuery(name = "HrProfileMsg.findById", query = "SELECT h FROM HrProfileMsg h WHERE h.id = :id"),
    @NamedQuery(name = "HrProfileMsg.findByEmpNo", query = "SELECT h FROM HrProfileMsg h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrProfileMsg.findByMsgType", query = "SELECT h FROM HrProfileMsg h WHERE h.msgType = :msgType"),
    @NamedQuery(name = "HrProfileMsg.findBySenderNo", query = "SELECT h FROM HrProfileMsg h WHERE h.senderNo = :senderNo"),
    @NamedQuery(name = "HrProfileMsg.findBySendDate", query = "SELECT h FROM HrProfileMsg h WHERE h.sendDate = :sendDate"),
    @NamedQuery(name = "HrProfileMsg.findByMsgId", query = "SELECT h FROM HrProfileMsg h WHERE h.msgId = :msgId"),
    @NamedQuery(name = "HrProfileMsg.findByReadDone", query = "SELECT h FROM HrProfileMsg h WHERE h.readDone = :readDone"),
    @NamedQuery(name = "HrProfileMsg.findByEntityName", query = "SELECT h FROM HrProfileMsg h WHERE h.entityName = :entityName")})
public class HrProfileMsg implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="HR_PROFILE_MSG_SEQ", sequenceName="HR_PROFILE_MSG_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HR_PROFILE_MSG_SEQ")
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "EMP_NO")
    private Long empNo;
    @Column(name = "MSG_TYPE")
    private Long msgType;
    @Column(name = "SENDER_NO")
    private Long senderNo;
    @Column(name = "SEND_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendDate;
    @Column(name = "MSG_ID")
    private Long msgId;
    @Column(name = "READ_DONE")
    private Character readDone;
    @Column(name = "ENTITY_NAME")
    private String entityName;
    @Column(name = "MSG_APPROVE")
    private Character msgApprove;
    @Column(name = "MSG_TEXT")
    private String msgText;

    public HrProfileMsg() {
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

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public Long getMsgType() {
        return msgType;
    }

    public void setMsgType(Long msgType) {
        this.msgType = msgType;
    }

    public Long getSenderNo() {
        return senderNo;
    }

    public void setSenderNo(Long senderNo) {
        this.senderNo = senderNo;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

   
    public Character getReadDone() {
        return readDone;
    }

    public void setReadDone(Character readDone) {
        this.readDone = readDone;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
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
        if (!(object instanceof HrProfileMsg)) {
            return false;
        }
        HrProfileMsg other = (HrProfileMsg) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public Character getMsgApprove() {
        return msgApprove;
    }

    public void setMsgApprove(Character msgApprove) {
        this.msgApprove = msgApprove;
    }

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    

    @Override
    public String toString() {
        return "e.HrProfileMsg[id=" + id + "]";
    }

}
