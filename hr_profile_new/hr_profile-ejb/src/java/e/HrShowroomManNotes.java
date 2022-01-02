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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "HR_SHOWROOM_MAN_NOTES", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrShowroomManNotes.findAll", query = "SELECT h FROM HrShowroomManNotes h"),
    @NamedQuery(name = "HrShowroomManNotes.findById", query = "SELECT h FROM HrShowroomManNotes h WHERE h.id = :id"),
    @NamedQuery(name = "HrShowroomManNotes.findByManId", query = "SELECT h FROM HrShowroomManNotes h WHERE h.manId.empNo = :manId"),
    @NamedQuery(name = "HrShowroomManNotes.findByBasicComponents", query = "SELECT h FROM HrShowroomManNotes h WHERE h.basicComponents = :basicComponents"),
    @NamedQuery(name = "HrShowroomManNotes.findBySalesDept", query = "SELECT h FROM HrShowroomManNotes h WHERE h.salesDept = :salesDept"),
    @NamedQuery(name = "HrShowroomManNotes.findByCompDept", query = "SELECT h FROM HrShowroomManNotes h WHERE h.compDept = :compDept"),
    @NamedQuery(name = "HrShowroomManNotes.findByTransDept", query = "SELECT h FROM HrShowroomManNotes h WHERE h.transDept = :transDept"),
    @NamedQuery(name = "HrShowroomManNotes.findByGraphicDept", query = "SELECT h FROM HrShowroomManNotes h WHERE h.graphicDept = :graphicDept"),
    @NamedQuery(name = "HrShowroomManNotes.findByShowDept", query = "SELECT h FROM HrShowroomManNotes h WHERE h.showDept = :showDept"),
    @NamedQuery(name = "HrShowroomManNotes.findByCheckupDuty", query = "SELECT h FROM HrShowroomManNotes h WHERE h.checkupDuty = :checkupDuty"),
    @NamedQuery(name = "HrShowroomManNotes.findByComplaints", query = "SELECT h FROM HrShowroomManNotes h WHERE h.complaints = :complaints"),
    @NamedQuery(name = "HrShowroomManNotes.findBySuggestions", query = "SELECT h FROM HrShowroomManNotes h WHERE h.suggestions = :suggestions"),
    @NamedQuery(name = "HrShowroomManNotes.findByApproved", query = "SELECT h FROM HrShowroomManNotes h WHERE h.approved is null and h.done='Y' order by h.trnsDate"),
    @NamedQuery(name = "HrShowroomManNotes.findByBonus", query = "SELECT h FROM HrShowroomManNotes h WHERE h.bonus = :bonus"),
    @NamedQuery(name = "HrShowroomManNotes.findByDone", query = "SELECT h FROM HrShowroomManNotes h WHERE h.done = :done")})
public class HrShowroomManNotes implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="HR_SHOWROOM_MAN_NOTES_SEQ", sequenceName="HR_SHOWROOM_MAN_NOTES_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HR_SHOWROOM_MAN_NOTES_SEQ")
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "MAN_ID",referencedColumnName="EMP_NO")
    private HrEmpInfo manId;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date trnsDate;
    @Column(name = "BASIC_COMPONENTS")
    private String basicComponents;
    @Column(name = "SALES_DEPT")
    private String salesDept;
    @Column(name = "COMP_DEPT")
    private String compDept;
    @Column(name = "TRANS_DEPT")
    private String transDept;
    @Column(name = "GRAPHIC_DEPT")
    private String graphicDept;
    @Column(name = "SHOW_DEPT")
    private String showDept;
    @Column(name = "CHECKUP_DUTY")
    private String checkupDuty;
    @Column(name = "COMPLAINTS")
    private String complaints;
    @Column(name = "SUGGESTIONS")
    private String suggestions;
    @Column(name = "APPROVED")
    private Character approved;
    @Column(name = "BONUS")
    private Long bonus;
    @Column(name = "DONE")
    private Character done;
    @Column(name = "SUMM_BASIC_COMPONENTS")
    private String summBasicComponents;
    @Column(name = "SUMM_SALES_DEPT")
    private String summSalesDept;
    @Column(name = "SUMM_COMP_DEPT")
    private String summCompDept;
    @Column(name = "SUMM_TRNS_DEPT")
    private String summTrnsDept;
    @Column(name = "SUMM_GRAPHIC_DEPT")
    private String summGraphicDept;
    @Column(name = "SUMM_SHOW_DEPT")
    private String summShowDept;
    @Column(name = "SUMM_CHECKUP_DUTY")
    private String summCheckupDuty;
    @Column(name = "SUMM_COMPLAINTS")
    private String summComplaints;
    @Column(name = "SUMM_SUGGESTIONS")
    private String summSuggestions;
    @Column(name = "NOTES_BASIC_COMPONENTS")
    private String notesBasicComponents;
    @Column(name = "NOTES_SALES_DEPT")
    private String notesSalesDept;
    @Column(name = "NOTES_COMP_DEPT")
    private String notesCompDept;
    @Column(name = "NOTES_TRANS_DEPT")
    private String notesTransDept;
    @Column(name = "NOTES_GRAPHIC_DEPT")
    private String notesGraphicDept;
    @Column(name = "NOTES_SHOW_DEPT")
    private String notesShowDept;
    @Column(name = "NOTES_CHECKUP_DUTY")
    private String notesCheckupDuty;
    @Column(name = "NOTES_COMPLAINTS")
    private String notesComplaints;
    @Column(name = "NOTES_SUGGESTIONS")
    private String notesSuggestions;
    @Column(name = "REPLY_BASIC_COMPONENTS")
    private String replyBasicComponents;
    @Column(name = "REPLY_SALES_DEPT")
    private String replySalesDept;
    @Column(name = "REPLY_COMP_DEPT")
    private String replyCompDept;
    @Column(name = "REPLY_TRANS_DEPT")
    private String replyTransDept;
    @Column(name = "REPLY_GRAPHIC_DEPT")
    private String replyGraphicDept;
    @Column(name = "REPLY_SHOW_DEPT")
    private String replyShowDept;
    @Column(name = "REPLY_CHECKUP_DUTY")
    private String replyCheckupDuty;
    @Column(name = "REPLY_COMPLAINTS")
    private String replyComplaints;
    @Column(name = "REPLY_SUGGESTIONS")
    private String replySuggestions;
    public HrShowroomManNotes() {
    }

    

    public String getBasicComponents() {
        return basicComponents;
    }

    public void setBasicComponents(String basicComponents) {
        this.basicComponents = basicComponents;
    }

    public String getSalesDept() {
        return salesDept;
    }

    public void setSalesDept(String salesDept) {
        this.salesDept = salesDept;
    }

    public String getCompDept() {
        return compDept;
    }

    public void setCompDept(String compDept) {
        this.compDept = compDept;
    }

    public String getTransDept() {
        return transDept;
    }

    public void setTransDept(String transDept) {
        this.transDept = transDept;
    }

    public String getGraphicDept() {
        return graphicDept;
    }

    public void setGraphicDept(String graphicDept) {
        this.graphicDept = graphicDept;
    }

    public String getShowDept() {
        return showDept;
    }

    public void setShowDept(String showDept) {
        this.showDept = showDept;
    }

    public String getCheckupDuty() {
        return checkupDuty;
    }

    public void setCheckupDuty(String checkupDuty) {
        this.checkupDuty = checkupDuty;
    }

    public String getComplaints() {
        return complaints;
    }

    public void setComplaints(String complaints) {
        this.complaints = complaints;
    }

    public String getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(String suggestions) {
        this.suggestions = suggestions;
    }

    public Character getApproved() {
        return approved;
    }

    public void setApproved(Character approved) {
        this.approved = approved;
    }

    public Long getBonus() {
        return bonus;
    }

    public void setBonus(Long bonus) {
        this.bonus = bonus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HrEmpInfo getManId() {
        return manId;
    }

    public void setManId(HrEmpInfo manId) {
        this.manId = manId;
    }

    

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

   

    public Character getDone() {
        return done;
    }

    public void setDone(Character done) {
        this.done = done;
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
        if (!(object instanceof HrShowroomManNotes)) {
            return false;
        }
        HrShowroomManNotes other = (HrShowroomManNotes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrShowroomManNotes[id=" + id + "]";
    }

    public String getSummBasicComponents() {
        return summBasicComponents;
    }

    public void setSummBasicComponents(String summBasicComponents) {
        this.summBasicComponents = summBasicComponents;
    }

    public String getSummCheckupDuty() {
        return summCheckupDuty;
    }

    public void setSummCheckupDuty(String summCheckupDuty) {
        this.summCheckupDuty = summCheckupDuty;
    }

    public String getSummCompDept() {
        return summCompDept;
    }

    public void setSummCompDept(String summCompDept) {
        this.summCompDept = summCompDept;
    }

    public String getSummComplaints() {
        return summComplaints;
    }

    public void setSummComplaints(String summComplaints) {
        this.summComplaints = summComplaints;
    }

    public String getSummGraphicDept() {
        return summGraphicDept;
    }

    public void setSummGraphicDept(String summGraphicDept) {
        this.summGraphicDept = summGraphicDept;
    }

    public String getSummSalesDept() {
        return summSalesDept;
    }

    public void setSummSalesDept(String summSalesDept) {
        this.summSalesDept = summSalesDept;
    }

    public String getSummShowDept() {
        return summShowDept;
    }

    public void setSummShowDept(String summShowDept) {
        this.summShowDept = summShowDept;
    }

    public String getSummSuggestions() {
        return summSuggestions;
    }

    public void setSummSuggestions(String summSuggestions) {
        this.summSuggestions = summSuggestions;
    }

    public String getSummTrnsDept() {
        return summTrnsDept;
    }

    public void setSummTrnsDept(String summTrnsDept) {
        this.summTrnsDept = summTrnsDept;
    }

    public String getNotesBasicComponents() {
        return notesBasicComponents;
    }

    public void setNotesBasicComponents(String notesBasicComponents) {
        this.notesBasicComponents = notesBasicComponents;
    }

    public String getNotesCheckupDuty() {
        return notesCheckupDuty;
    }

    public void setNotesCheckupDuty(String notesCheckupDuty) {
        this.notesCheckupDuty = notesCheckupDuty;
    }

    public String getNotesCompDept() {
        return notesCompDept;
    }

    public void setNotesCompDept(String notesCompDept) {
        this.notesCompDept = notesCompDept;
    }

    public String getNotesComplaints() {
        return notesComplaints;
    }

    public void setNotesComplaints(String notesComplaints) {
        this.notesComplaints = notesComplaints;
    }

    public String getNotesGraphicDept() {
        return notesGraphicDept;
    }

    public void setNotesGraphicDept(String notesGraphicDept) {
        this.notesGraphicDept = notesGraphicDept;
    }

    public String getNotesSalesDept() {
        return notesSalesDept;
    }

    public void setNotesSalesDept(String notesSalesDept) {
        this.notesSalesDept = notesSalesDept;
    }

    public String getNotesShowDept() {
        return notesShowDept;
    }

    public void setNotesShowDept(String notesShowDept) {
        this.notesShowDept = notesShowDept;
    }

    public String getNotesSuggestions() {
        return notesSuggestions;
    }

    public void setNotesSuggestions(String notesSuggestions) {
        this.notesSuggestions = notesSuggestions;
    }

    public String getReplyBasicComponents() {
        return replyBasicComponents;
    }

    public void setReplyBasicComponents(String replyBasicComponents) {
        this.replyBasicComponents = replyBasicComponents;
    }

    public String getReplyCheckupDuty() {
        return replyCheckupDuty;
    }

    public void setReplyCheckupDuty(String replyCheckupDuty) {
        this.replyCheckupDuty = replyCheckupDuty;
    }

    public String getReplyCompDept() {
        return replyCompDept;
    }

    public void setReplyCompDept(String replyCompDept) {
        this.replyCompDept = replyCompDept;
    }

    public String getReplyComplaints() {
        return replyComplaints;
    }

    public void setReplyComplaints(String replyComplaints) {
        this.replyComplaints = replyComplaints;
    }

    public String getReplyGraphicDept() {
        return replyGraphicDept;
    }

    public void setReplyGraphicDept(String replyGraphicDept) {
        this.replyGraphicDept = replyGraphicDept;
    }

    public String getReplySalesDept() {
        return replySalesDept;
    }

    public void setReplySalesDept(String replySalesDept) {
        this.replySalesDept = replySalesDept;
    }

    public String getReplyShowDept() {
        return replyShowDept;
    }

    public void setReplyShowDept(String replyShowDept) {
        this.replyShowDept = replyShowDept;
    }

    public String getReplySuggestions() {
        return replySuggestions;
    }

    public void setReplySuggestions(String replySuggestions) {
        this.replySuggestions = replySuggestions;
    }

    public String getNotesTransDept() {
        return notesTransDept;
    }

    public void setNotesTransDept(String notesTransDept) {
        this.notesTransDept = notesTransDept;
    }

    public String getReplyTransDept() {
        return replyTransDept;
    }

    public void setReplyTransDept(String replyTransDept) {
        this.replyTransDept = replyTransDept;
    }

   

    

}
