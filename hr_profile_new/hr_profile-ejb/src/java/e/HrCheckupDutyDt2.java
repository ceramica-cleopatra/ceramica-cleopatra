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
@Table(name = "HR_CHECKUP_DUTY_DT2", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrCheckupDutyDt2.findAll", query = "SELECT h FROM HrCheckupDutyDt2 h"),
    @NamedQuery(name = "HrCheckupDutyDt2.findById", query = "SELECT h FROM HrCheckupDutyDt2 h WHERE h.id = :id"),
    @NamedQuery(name = "HrCheckupDutyDt2.findByTitle", query = "SELECT h FROM HrCheckupDutyDt2 h WHERE h.title = :title"),
    @NamedQuery(name = "HrCheckupDutyDt2.findBySummaryFlag", query = "SELECT h FROM HrCheckupDutyDt2 h WHERE h.summaryFlag = :summaryFlag"),
    @NamedQuery(name = "HrCheckupDutyDt2.findByRedirectFlag", query = "SELECT h FROM HrCheckupDutyDt2 h WHERE h.redirectFlag = :redirectFlag"),
    @NamedQuery(name = "HrCheckupDutyDt2.findByRedirectMail", query = "SELECT h FROM HrCheckupDutyDt2 h WHERE h.redirectMail = :redirectMail"),
    @NamedQuery(name = "HrCheckupDutyDt2.findByReply", query = "SELECT h FROM HrCheckupDutyDt2 h WHERE h.reply = :reply"),
    @NamedQuery(name = "HrCheckupDutyDt2.findByDetails", query = "SELECT h FROM HrCheckupDutyDt2 h WHERE h.details = :details"),
    @NamedQuery(name = "HrCheckupDutyDt2.findByDisplayOrder", query = "SELECT h FROM HrCheckupDutyDt2 h WHERE h.displayOrder = :displayOrder"),
    @NamedQuery(name = "HrCheckupDutyDt2.findByAction", query = "SELECT h FROM HrCheckupDutyDt2 h WHERE h.action = :action")})
public class HrCheckupDutyDt2 implements Serializable {
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
    @Column(name = "DETAILS")
    private String details;
    @Column(name = "DISPLAY_ORDER")
    private Long displayOrder;
    @Column(name = "ACTION")
    private String action;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrCheckupDutyHd2 hrCheckupDutyHd2;

    public HrCheckupDutyDt2() {
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

    
    public String getAction() {
        return action;
    }

    public Long getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Long displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public HrCheckupDutyHd2 getHrCheckupDutyHd2() {
        return hrCheckupDutyHd2;
    }

    public void setHrCheckupDutyHd2(HrCheckupDutyHd2 hrCheckupDutyHd2) {
        this.hrCheckupDutyHd2 = hrCheckupDutyHd2;
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
        if (!(object instanceof HrCheckupDutyDt2)) {
            return false;
        }
        HrCheckupDutyDt2 other = (HrCheckupDutyDt2) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrCheckupDutyDt2[id=" + id + "]";
    }

}
