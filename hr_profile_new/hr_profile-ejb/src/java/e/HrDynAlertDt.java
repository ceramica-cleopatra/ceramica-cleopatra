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
@Table(name = "HR_DYN_ALERT_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrDynAlertDt.findAll", query = "SELECT h FROM HrDynAlertDt h"),
    @NamedQuery(name = "HrDynAlertDt.findById", query = "SELECT h FROM HrDynAlertDt h WHERE h.id = :id"),
    @NamedQuery(name = "HrDynAlertDt.findByCompId", query = "SELECT h FROM HrDynAlertDt h WHERE h.compId = :compId"),
    @NamedQuery(name = "HrDynAlertDt.findByTxt", query = "SELECT h FROM HrDynAlertDt h WHERE h.txt = :txt"),
    @NamedQuery(name = "HrDynAlertDt.findByValue", query = "SELECT h FROM HrDynAlertDt h WHERE h.value = :value"),
    @NamedQuery(name = "HrDynAlertDt.findByCommentLabel", query = "SELECT h FROM HrDynAlertDt h WHERE h.commentLabel = :commentLabel"),
    @NamedQuery(name = "HrDynAlertDt.findByCommentValue", query = "SELECT h FROM HrDynAlertDt h WHERE h.commentValue = :commentValue"),
    @NamedQuery(name = "HrDynAlertDt.findByBooleanValue", query = "SELECT h FROM HrDynAlertDt h WHERE h.booleanValue = :booleanValue")})
public class HrDynAlertDt implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="HR_DYN_ALERT_DT_SEQ", sequenceName="HR_DYN_ALERT_DT_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HR_DYN_ALERT_DT_SEQ")
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "COMP_ID")
    private Long compId;
    @Column(name = "TXT")
    private String txt;
    @Column(name = "VALUE")
    private String value;
    @Column(name = "COMMENT_LABEL")
    private String commentLabel;
    @Column(name = "COMMENT_VALUE")
    private String commentValue;
    @Column(name = "BOOLEAN_VALUE")
    private Long booleanValue;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrDynAlertHd hrDynAlertHd;

    public HrDynAlertDt() {
    }

    public Long getBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(Long booleanValue) {
        this.booleanValue = booleanValue;
    }

    public Long getCompId() {
        return compId;
    }

    public void setCompId(Long compId) {
        this.compId = compId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public HrDynAlertHd getHrDynAlertHd() {
        return hrDynAlertHd;
    }

    public void setHrDynAlertHd(HrDynAlertHd hrDynAlertHd) {
        this.hrDynAlertHd = hrDynAlertHd;
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
        if (!(object instanceof HrDynAlertDt)) {
            return false;
        }
        HrDynAlertDt other = (HrDynAlertDt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrDynAlertDt[id=" + id + "]";
    }

}
