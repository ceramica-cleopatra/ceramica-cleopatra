/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_INVESTIGATE_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrInvestigateDt.findAll", query = "SELECT distinct h FROM HrInvestigateDt h where h.hrInvestigateHd.id=:hd_id"),
    @NamedQuery(name = "HrInvestigateDt.findTotalRadioResult", query = "SELECT count(dt.id),h FROM HrInvestigateDt h Left Join h.hrInvestigateEmpList dt where h.hrInvestigateHd=:hd_id group by h"),
    @NamedQuery(name = "HrInvestigateDt.findTotalSlidaerResult", query = "SELECT avg(dt.answer),h FROM HrInvestigateDt h Left Join h.hrInvestigateEmpList dt where h.hrInvestigateHd=:hd_id group by h"),
    @NamedQuery(name = "HrInvestigateDt.findById", query = "SELECT h FROM HrInvestigateDt h WHERE h.id = :id"),
    @NamedQuery(name = "HrInvestigateDt.findByQuestionText", query = "SELECT h FROM HrInvestigateDt h WHERE h.questionText = :questionText"),
    @NamedQuery(name = "HrInvestigateDt.findByQuestionVal", query = "SELECT h FROM HrInvestigateDt h WHERE h.questionVal = :questionVal")})
public class HrInvestigateDt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "QUESTION_TEXT")
    private String questionText;
    @Column(name = "QUESTION_VAL")
    private Long questionVal;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrInvestigateHd hrInvestigateHd;
    @OneToMany(mappedBy = "hrInvestigateDt")
    private List<HrInvestigateEmp> hrInvestigateEmpList;

    public HrInvestigateDt() {
    }

    public HrInvestigateDt(Long id,String questionText,Long questionVal)
    {
    this.id=id;
    this.questionText=questionText;
    this.questionVal=questionVal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionVal() {
        return questionVal;
    }

    public void setQuestionVal(Long questionVal) {
        this.questionVal = questionVal;
    }

   
    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }


    public HrInvestigateHd getHrInvestigateHd() {
        return hrInvestigateHd;
    }

    public void setHrInvestigateHd(HrInvestigateHd hrInvestigateHd) {
        this.hrInvestigateHd = hrInvestigateHd;
    }

    public List<HrInvestigateEmp> getHrInvestigateEmpList() {
        return hrInvestigateEmpList;
    }

    public void setHrInvestigateEmpList(List<HrInvestigateEmp> hrInvestigateEmpList) {
        this.hrInvestigateEmpList = hrInvestigateEmpList;
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
        if (!(object instanceof HrInvestigateDt)) {
            return false;
        }
        HrInvestigateDt other = (HrInvestigateDt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrInvestigateDt[id=" + id + "]";
    }

}
