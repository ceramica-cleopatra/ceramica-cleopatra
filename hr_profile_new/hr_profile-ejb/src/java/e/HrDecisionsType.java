/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "HR_DECISIONS_TYPE", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrDecisionsType.findAll", query = "SELECT h FROM HrDecisionsType h"),
    @NamedQuery(name = "HrDecisionsType.findById", query = "SELECT h FROM HrDecisionsType h WHERE h.id = :id"),
    @NamedQuery(name = "HrDecisionsType.findByDecisionName", query = "SELECT h FROM HrDecisionsType h WHERE h.decisionName = :decisionName"),
    @NamedQuery(name = "HrDecisionsType.findByAffectOnEmpMovement", query = "SELECT h FROM HrDecisionsType h WHERE h.affectOnEmpMovement = :affectOnEmpMovement")})
public class HrDecisionsType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "DECISION_NAME")
    private String decisionName;
    @Column(name = "AFFECT_ON_EMP_MOVEMENT")
    private Character affectOnEmpMovement;
    @OneToMany(mappedBy = "hrDecisionsType")
    private List<HrMangaerialDecisions> hrMangaerialDecisionseList;
    public HrDecisionsType() {
    }

    public HrDecisionsType(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getDecisionName() {
        return decisionName;
    }

    public void setDecisionName(String decisionName) {
        this.decisionName = decisionName;
    }

    public Character getAffectOnEmpMovement() {
        return affectOnEmpMovement;
    }

    public void setAffectOnEmpMovement(Character affectOnEmpMovement) {
        this.affectOnEmpMovement = affectOnEmpMovement;
    }

    public List<HrMangaerialDecisions> getHrMangaerialDecisionseList() {
        return hrMangaerialDecisionseList;
    }

    public void setHrMangaerialDecisionseList(List<HrMangaerialDecisions> hrMangaerialDecisionseList) {
        this.hrMangaerialDecisionseList = hrMangaerialDecisionseList;
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
        if (!(object instanceof HrDecisionsType)) {
            return false;
        }
        HrDecisionsType other = (HrDecisionsType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrDecisionsType[id=" + id + "]";
    }

}
