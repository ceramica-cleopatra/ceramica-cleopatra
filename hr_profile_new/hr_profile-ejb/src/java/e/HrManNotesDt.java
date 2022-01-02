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
@Table(name = "HR_MAN_NOTES_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrManNotesDt.findAll", query = "SELECT h FROM HrManNotesDt h"),
    @NamedQuery(name = "HrManNotesDt.findById", query = "SELECT h FROM HrManNotesDt h WHERE h.id = :id"),
    @NamedQuery(name = "HrManNotesDt.findByTitle", query = "SELECT h FROM HrManNotesDt h WHERE h.title = :title"),
    @NamedQuery(name = "HrManNotesDt.findBySummaryFlag", query = "SELECT h FROM HrManNotesDt h WHERE h.summaryFlag = :summaryFlag"),
    @NamedQuery(name = "HrManNotesDt.findByRedirectFlag", query = "SELECT h FROM HrManNotesDt h WHERE h.redirectFlag = :redirectFlag"),
    @NamedQuery(name = "HrManNotesDt.findByRedirectMail", query = "SELECT h FROM HrManNotesDt h WHERE h.redirectMail = :redirectMail"),
    @NamedQuery(name = "HrManNotesDt.findByReply", query = "SELECT h FROM HrManNotesDt h WHERE h.reply = :reply"),
    @NamedQuery(name = "HrManNotesDt.findByDetails", query = "SELECT h FROM HrManNotesDt h WHERE h.details = :details"),
    @NamedQuery(name = "HrManNotesDt.findByDisplayOrder", query = "SELECT h FROM HrManNotesDt h WHERE h.displayOrder = :displayOrder")})
public class HrManNotesDt implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="HR_MAN_NOTES_DT_SEQ", sequenceName="HR_MAN_NOTES_DT_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HR_MAN_NOTES_DT_SEQ")
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
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
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrManNotesHd hrManNotesHd;

    public HrManNotesDt() {
    }

    public HrManNotesDt(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
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

    public Long getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Long displayOrder) {
        this.displayOrder = displayOrder;
    }

    

    public HrManNotesHd getHrManNotesHd() {
        return hrManNotesHd;
    }

    public void setHrManNotesHd(HrManNotesHd hrManNotesHd) {
        this.hrManNotesHd = hrManNotesHd;
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
        if (!(object instanceof HrManNotesDt)) {
            return false;
        }
        HrManNotesDt other = (HrManNotesDt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrManNotesDt[id=" + id + "]";
    }

}
