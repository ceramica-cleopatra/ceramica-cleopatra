 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_MANGAERIAL_DECISIONS", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrMangaerialDecisions.findAll", query = "SELECT h FROM HrMangaerialDecisions h where h.profile='Y' order by h.no desc"),
    @NamedQuery(name = "HrMangaerialDecisions.findById", query = "SELECT h FROM HrMangaerialDecisions h WHERE h.id = :id"),
    @NamedQuery(name = "HrMangaerialDecisions.findByNo", query = "SELECT h FROM HrMangaerialDecisions h WHERE h.no = :no"),
    @NamedQuery(name = "HrMangaerialDecisions.findByName", query = "SELECT h FROM HrMangaerialDecisions h WHERE h.name = :name"),
    @NamedQuery(name = "HrMangaerialDecisions.findByTrnsDate", query = "SELECT h FROM HrMangaerialDecisions h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrMangaerialDecisions.findByContainer", query = "SELECT h FROM HrMangaerialDecisions h WHERE h.container = :container"),
    @NamedQuery(name = "HrMangaerialDecisions.findByNotes", query = "SELECT h FROM HrMangaerialDecisions h WHERE h.notes = :notes"),
    @NamedQuery(name = "HrMangaerialDecisions.findByProfile", query = "SELECT h FROM HrMangaerialDecisions h WHERE h.profile = :profile")})
public class HrMangaerialDecisions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NO")
    private Long no;
    @Column(name = "NAME")
    private String name;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @Column(name = "CONTAINER")
    private String container;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "PROFILE")
    private Character profile;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrDecisionsType hrDecisionsType;
    @OneToMany(mappedBy = "hrMangaerialDecisions")
    private List<HrDecisionsMovement> hrDecisionsMovementList;
    public HrMangaerialDecisions() {
    }

    public HrMangaerialDecisions(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Character getProfile() {
        return profile;
    }

    public void setProfile(Character profile) {
        this.profile = profile;
    }

    public HrDecisionsType getHrDecisionsType() {
        return hrDecisionsType;
    }

    public void setHrDecisionsType(HrDecisionsType hrDecisionsType) {
        this.hrDecisionsType = hrDecisionsType;
    }

    public List<HrDecisionsMovement> getHrDecisionsMovementList() {
        return hrDecisionsMovementList;
    }

    public void setHrDecisionsMovementList(List<HrDecisionsMovement> hrDecisionsMovementList) {
        this.hrDecisionsMovementList = hrDecisionsMovementList;
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
        if (!(object instanceof HrMangaerialDecisions)) {
            return false;
        }
        HrMangaerialDecisions other = (HrMangaerialDecisions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrMangaerialDecisions[id=" + id + "]";
    }

}
