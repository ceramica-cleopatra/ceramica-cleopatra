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
@Table(name = "HR_LOCATION", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrLocation.findAll", query = "SELECT h FROM HrLocation h where h.sectorId is not null and h.sectorId<>0"),
    @NamedQuery(name = "HrLocation.findLocationsForDuties", query = "SELECT h FROM HrLocation h where h.sectorId is not null and h.sectorId<>0"),
    @NamedQuery(name = "HrLocation.findAllShowRooms", query = "SELECT h FROM HrLocation h where h.hrLocationType.id =1 order by h.id"),
    @NamedQuery(name = "HrLocation.findCrmkShowRoomIdByLocId", query = "SELECT h.crmkId FROM HrLocation h where h.hrLocationType.id =1 and h.id=:loc_id"),
    @NamedQuery(name = "HrLocation.findById", query = "SELECT h FROM HrLocation h WHERE h.id = :id"),
    @NamedQuery(name = "HrLocation.findLocationNames", query = "SELECT h FROM HrLocation h where h.ipAddress is not null or h.crmkId is not null"),
    @NamedQuery(name = "HrLocation.findSpecificLocationNames", query = "SELECT h FROM HrLocation h where (h.ipAddress is not null or h.crmkId is not null) and h.id<>:current_loc and h.hrLocationType.id=1 and h.id<>124"),
    @NamedQuery(name = "HrLocation.findByName", query = "SELECT h FROM HrLocation h WHERE h.name = :name"),
    @NamedQuery(name = "HrLocation.findByNotes", query = "SELECT h FROM HrLocation h WHERE h.notes = :notes"),
    @NamedQuery(name = "HrLocation.findByIpAddress", query = "SELECT h FROM HrLocation h where h.id in (SELECT x.hrLocationIpMappingPK.locId FROM HrLocationIpMapping x WHERE x.hrLocationIpMappingPK.ip = :ipAddress)"),
    @NamedQuery(name = "HrLocation.findByCrmkId", query = "SELECT h FROM HrLocation h WHERE h.crmkId = :crmkId"),
    @NamedQuery(name = "HrLocation.dutyLocations", query = "SELECT h FROM HrLocation h where (h.sectorId=(select l.sectorId from HrLocation l where l.id=:loc_id) and h.ipAddress is not null and h.dutyName is not null) or h.id=-1")})
public class HrLocation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "IP_ADDRESS")
    private String ipAddress;
    @Column(name = "CRMK_ID")
    private Long crmkId;
    @Column(name="SECTOR_ID")
    private Long sectorId;
    @Column(name="DUTY_NAME")
    private String dutyName;
    @Column(name="DUTY")
    private String duty;
    @JoinColumn(name = "REG_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrRegion hrRegion;
    @JoinColumn(name = "TYPE_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrLocationType hrLocationType;
    @JoinColumn(name = "AREA_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrArea hrArea;
    @OneToMany(mappedBy = "hrLocation")
    private List<HrEmpJob> hrEmpJobList;
    @OneToMany(mappedBy = "locId")
    private List<HrCheckupDutyHd> hrCheckupDutyHdList;
    @OneToMany(mappedBy="hrLocation")
    private List<HrOpenDutyExpectedLocDt> hrOpenDutyExpectedLocDtList;
    public HrLocation() {
    }

    public List<HrEmpJob> getHrEmpJobList() {
        return hrEmpJobList;
    }

    public void setHrEmpJobList(List<HrEmpJob> hrEmpJobList) {
        this.hrEmpJobList = hrEmpJobList;
    }

    public HrLocation(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Long getCrmkId() {
        return crmkId;
    }

    public void setCrmkId(Long crmkId) {
        this.crmkId = crmkId;
    }

    public HrRegion getHrRegion() {
        return hrRegion;
    }

    public void setHrRegion(HrRegion hrRegion) {
        this.hrRegion = hrRegion;
    }

    public HrLocationType getHrLocationType() {
        return hrLocationType;
    }

    public void setHrLocationType(HrLocationType hrLocationType) {
        this.hrLocationType = hrLocationType;
    }

    public HrArea getHrArea() {
        return hrArea;
    }

    public void setHrArea(HrArea hrArea) {
        this.hrArea = hrArea;
    }

    public List<HrOpenDutyExpectedLocDt> getHrOpenDutyExpectedLocDtList() {
        return hrOpenDutyExpectedLocDtList;
    }

    public void setHrOpenDutyExpectedLocDtList(List<HrOpenDutyExpectedLocDt> hrOpenDutyExpectedLocDtList) {
        this.hrOpenDutyExpectedLocDtList = hrOpenDutyExpectedLocDtList;
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
        if (!(object instanceof HrLocation)) {
            return false;
        }
        HrLocation other = (HrLocation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    public Long getSectorId() {
        return sectorId;
    }

    public void setSectorId(Long sectorId) {
        this.sectorId = sectorId;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    @Override
    public String toString() {
        return "e.HrLocation[id=" + id + "]";
    }

    public List<HrCheckupDutyHd> getHrCheckupDutyHdList() {
        return hrCheckupDutyHdList;
    }

    public void setHrCheckupDutyHdList(List<HrCheckupDutyHd> hrCheckupDutyHdList) {
        this.hrCheckupDutyHdList = hrCheckupDutyHdList;
    }

}
