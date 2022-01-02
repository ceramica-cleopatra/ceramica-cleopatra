/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "HR_CHECKUP_DUTY_DT1", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrCheckupDutyDt1.findAll", query = "SELECT h FROM HrCheckupDutyDt1 h"),
    @NamedQuery(name = "HrCheckupDutyDt1.findById", query = "SELECT h FROM HrCheckupDutyDt1 h WHERE h.id = :id"),
    @NamedQuery(name = "HrCheckupDutyDt1.findByTitle", query = "SELECT h FROM HrCheckupDutyDt1 h WHERE h.title = :title"),
    @NamedQuery(name = "HrCheckupDutyDt1.findBySummaryFlag", query = "SELECT h FROM HrCheckupDutyDt1 h WHERE h.summaryFlag = :summaryFlag"),
    @NamedQuery(name = "HrCheckupDutyDt1.findByRedirectFlag", query = "SELECT h FROM HrCheckupDutyDt1 h WHERE h.redirectFlag = :redirectFlag"),
    @NamedQuery(name = "HrCheckupDutyDt1.findByRedirectMail", query = "SELECT h FROM HrCheckupDutyDt1 h WHERE h.redirectMail = :redirectMail"),
    @NamedQuery(name = "HrCheckupDutyDt1.findByReply", query = "SELECT h FROM HrCheckupDutyDt1 h WHERE h.reply = :reply"),
    @NamedQuery(name = "HrCheckupDutyDt1.findByDetails", query = "SELECT h FROM HrCheckupDutyDt1 h WHERE h.details = :details")})
public class HrCheckupDutyDt1 implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="HR_CHECKUP_DUTY_DT_SEQ", sequenceName="HR_CHECKUP_DUTY_DT_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HR_CHECKUP_DUTY_DT_SEQ")
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "SUMMARY_FLAG")
    private Character summaryFlag;
    @Column(name = "REDIRECT_FLAG")
    private Character redirectFlag;
    @Column(name = "REDIRECT_MAIL")
    private String redirectMail;
    @Column(name = "REPLY")
    private String reply;
    @Column(name = "ACTION")
    private String action;
    @Column(name = "DETAILS")
    private String details;
    @Column(name = "DISPLAY_ORDER")
    private Long displayOrder;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrCheckupDutyHd1 hrCheckupDutyHd1;

    public HrCheckupDutyDt1() {
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

   

    public Character getSummaryFlag() {
        return summaryFlag;
    }

    public void setSummaryFlag(Character summaryFlag) {
        this.summaryFlag = summaryFlag;
    }

    public Character getRedirectFlag() {
        return redirectFlag;
    }

    public void setRedirectFlag(Character redirectFlag) {
        this.redirectFlag = redirectFlag;
    }

    public String getRedirectMail() {
        return redirectMail;
    }

    public void setRedirectMail(String redirectMail) {
        this.redirectMail = redirectMail;
    }

   

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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
        if (!(object instanceof HrCheckupDutyDt1)) {
            return false;
        }
        HrCheckupDutyDt1 other = (HrCheckupDutyDt1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrCheckupDutyDt1[id=" + id + "]";
    }

    public HrCheckupDutyHd1 getHrCheckupDutyHd1() {
        return hrCheckupDutyHd1;
    }

    public void setHrCheckupDutyHd1(HrCheckupDutyHd1 hrCheckupDutyHd1) {
        this.hrCheckupDutyHd1 = hrCheckupDutyHd1;
    }

    public Long getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Long displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

   
    

}
