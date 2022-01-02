/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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

/**
 *
 * @author data
 */
@Entity
@Table(name = "HR_DYN_ALERT_TEMPLATE_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrDynAlertTemplateDt.findAll", query = "SELECT h FROM HrDynAlertTemplateDt h"),
    @NamedQuery(name = "HrDynAlertTemplateDt.findById", query = "SELECT h FROM HrDynAlertTemplateDt h WHERE h.id = :id"),
    @NamedQuery(name = "HrDynAlertTemplateDt.findByCompId", query = "SELECT h FROM HrDynAlertTemplateDt h WHERE h.compId = :compId"),
    @NamedQuery(name = "HrDynAlertTemplateDt.findByRequired", query = "SELECT h FROM HrDynAlertTemplateDt h WHERE h.required = :required"),
    @NamedQuery(name = "HrDynAlertTemplateDt.findByTxt", query = "SELECT h FROM HrDynAlertTemplateDt h WHERE h.txt = :txt"),
    @NamedQuery(name = "HrDynAlertTemplateDt.findByValue", query = "SELECT h FROM HrDynAlertTemplateDt h WHERE h.value = :value")})
public class HrDynAlertTemplateDt  implements Serializable,Cloneable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="HR_DYN_ALERT_TEMPLATE_DT_SEQ", sequenceName="HR_DYN_ALERT_TEMPLATE_DT_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HR_DYN_ALERT_TEMPLATE_DT_SEQ")
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "COMP_ID")
    private Long compId;
    @Column(name = "REQUIRED")
    private Boolean required;
    @Column(name = "TXT")
    private String txt;
    @Column(name = "VALUE")
    private String value;
    @Column(name = "ADD_COMMENT")
    private Boolean addComment;
    @Column(name = "COMMENT_LABEL")
    private String commentLabel;
    @Column(name = "COMMENT_VALUE")
    private String commentValue;
    @Column(name = "BOOLEAN_VALUE")
    private Boolean booleanValue;
    @Column(name = "COMP_WIDTH")
    private Long compWidth;
    @Column(name = "COMP_HEIGHT")
    private Long compHeight;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrDynAlertTemplateHd hrDynAlertTemplateHd;
    @OneToMany(mappedBy = "hrDynAlertTemplateDt",cascade=CascadeType.ALL)
    private List<HrDynAlertTemplateCompDt> hrDynAlertTemplateCompDtList;

    public HrDynAlertTemplateDt() {
    }

    public HrDynAlertTemplateDt(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompId() {
        return compId;
    }

    public void setCompId(Long compId) {
        this.compId = compId;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public HrDynAlertTemplateHd getHrDynAlertTemplateHd() {
        return hrDynAlertTemplateHd;
    }

    public void setHrDynAlertTemplateHd(HrDynAlertTemplateHd hrDynAlertTemplateHd) {
        this.hrDynAlertTemplateHd = hrDynAlertTemplateHd;
    }

    public List<HrDynAlertTemplateCompDt> getHrDynAlertTemplateCompDtList() {
        return hrDynAlertTemplateCompDtList;
    }

    public void setHrDynAlertTemplateCompDtList(List<HrDynAlertTemplateCompDt> hrDynAlertTemplateCompDtList) {
        this.hrDynAlertTemplateCompDtList = hrDynAlertTemplateCompDtList;
    }

    public Boolean getAddComment() {
        return addComment;
    }

    public void setAddComment(Boolean addComment) {
        this.addComment = addComment;
    }

    public String getCommentLabel() {
        return commentLabel;
    }

    public void setCommentLabel(String commentLabel) {
        this.commentLabel = commentLabel;
    }

    public String getCommentValue() {
        return commentValue;
    }

    public void setCommentValue(String commentValue) {
        this.commentValue = commentValue;
    }

    public Boolean getBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public Long getCompHeight() {
        return compHeight;
    }

    public void setCompHeight(Long compHeight) {
        this.compHeight = compHeight;
    }

    public Long getCompWidth() {
        return compWidth;
    }

    public void setCompWidth(Long compWidth) {
        this.compWidth = compWidth;
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
        if (!(object instanceof HrDynAlertTemplateDt)) {
            return false;
        }
        HrDynAlertTemplateDt other = (HrDynAlertTemplateDt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrDynAlertTemplateDt[id=" + id + "]";
    }
     @Override
    public Object clone() throws CloneNotSupportedException{
      return super.clone();
    }

}
