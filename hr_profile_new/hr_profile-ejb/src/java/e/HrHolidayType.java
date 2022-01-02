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
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_HOLIDAY_TYPE", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrHolidayType.findAll", query = "select o.id,o.name from HrHolidayType o where o.holidayRequest='Y' order by o.id"),
    @NamedQuery(name = "HrHolidayType.findById", query = "SELECT h FROM HrHolidayType h WHERE h.id =:id"),
    @NamedQuery(name = "HrHolidayType.findByName", query = "SELECT h FROM HrHolidayType h WHERE h.name = :name"),
    @NamedQuery(name = "HrHolidayType.findByNotes", query = "SELECT h FROM HrHolidayType h WHERE h.notes = :notes"),
    @NamedQuery(name = "HrHolidayType.findByNumberOfDays", query = "SELECT h FROM HrHolidayType h WHERE h.numberOfDays = :numberOfDays"),
    @NamedQuery(name = "HrHolidayType.findBySalaryRatio", query = "SELECT h FROM HrHolidayType h WHERE h.salaryRatio = :salaryRatio"),
    @NamedQuery(name = "HrHolidayType.findByHafezRatio", query = "SELECT h FROM HrHolidayType h WHERE h.hafezRatio = :hafezRatio"),
    @NamedQuery(name = "HrHolidayType.findByBadlRatio", query = "SELECT h FROM HrHolidayType h WHERE h.badlRatio = :badlRatio"),
    @NamedQuery(name = "HrHolidayType.findByEffectOfEmpHolidays", query = "SELECT h FROM HrHolidayType h WHERE h.effectOfEmpHolidays = :effectOfEmpHolidays"),
    @NamedQuery(name = "HrHolidayType.findByEffectOfBadlRahat", query = "SELECT h FROM HrHolidayType h WHERE h.effectOfBadlRahat = :effectOfBadlRahat"),
    @NamedQuery(name = "HrHolidayType.findByBasic", query = "SELECT h FROM HrHolidayType h WHERE h.basic = :basic"),
    @NamedQuery(name = "HrHolidayType.findByHolidayRequest", query = "SELECT h FROM HrHolidayType h WHERE h.holidayRequest = :holidayRequest")})
public class HrHolidayType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "NUMBER_OF_DAYS")
    private Long numberOfDays;
    @Column(name = "SALARY_RATIO")
    private Long salaryRatio;
    @Column(name = "HAFEZ_RATIO")
    private Long hafezRatio;
    @Column(name = "BADL_RATIO")
    private Long badlRatio;
    @Column(name = "EFFECT_OF_EMP_HOLIDAYS")
    private String effectOfEmpHolidays;
    @Column(name = "EFFECT_OF_BADL_RAHAT")
    private String effectOfBadlRahat;
    @Column(name = "BASIC")
    private String basic;
    @Column(name = "HOLIDAY_REQUEST")
    private String holidayRequest;
    @OneToMany(mappedBy = "holidayType")
    private List<HrHolidayRequest> hrHolidayTypeList;

    public String getHolidayRequest() {
        return holidayRequest;
    }

    public void setHolidayRequest(String holidayRequest) {
        this.holidayRequest = holidayRequest;
    }

    public HrHolidayType() {
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

    public Long getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Long numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public Long getBadlRatio() {
        return badlRatio;
    }

    public void setBadlRatio(Long badlRatio) {
        this.badlRatio = badlRatio;
    }

    public Long getHafezRatio() {
        return hafezRatio;
    }

    public void setHafezRatio(Long hafezRatio) {
        this.hafezRatio = hafezRatio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSalaryRatio() {
        return salaryRatio;
    }

    public void setSalaryRatio(Long salaryRatio) {
        this.salaryRatio = salaryRatio;
    }

    public String getEffectOfEmpHolidays() {
        return effectOfEmpHolidays;
    }

    public void setEffectOfEmpHolidays(String effectOfEmpHolidays) {
        this.effectOfEmpHolidays = effectOfEmpHolidays;
    }

    public String getEffectOfBadlRahat() {
        return effectOfBadlRahat;
    }

    public void setEffectOfBadlRahat(String effectOfBadlRahat) {
        this.effectOfBadlRahat = effectOfBadlRahat;
    }

    public String getBasic() {
        return basic;
    }

    public void setBasic(String basic) {
        this.basic = basic;
    }

    public List<HrHolidayRequest> getHrHolidayTypeList() {
        return hrHolidayTypeList;
    }

    public void setHrHolidayTypeList(List<HrHolidayRequest> hrHolidayTypeList) {
        this.hrHolidayTypeList = hrHolidayTypeList;
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
        if (!(object instanceof HrHolidayType)) {
            return false;
        }
        HrHolidayType other = (HrHolidayType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrHolidayType[id=" + id + "]";
    }

}
