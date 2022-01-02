/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_HOLIDAY_LEVEL_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrHolidayLevelDt.findAll", query = "SELECT h FROM HrHolidayLevelDt h"),
    @NamedQuery(name = "HrHolidayLevelDt.findById", query = "SELECT h FROM HrHolidayLevelDt h WHERE h.id = :id"),
    @NamedQuery(name = "HrHolidayLevelDt.findByGrpId", query = "SELECT h FROM HrHolidayLevelDt h WHERE h.grpId = :grpId"),
    @NamedQuery(name = "HrHolidayLevelDt.findByJobId", query = "SELECT h FROM HrHolidayLevelDt h WHERE h.jobId = :jobId"),
    @NamedQuery(name = "HrHolidayLevelDt.findByDeptId", query = "SELECT h FROM HrHolidayLevelDt h WHERE h.deptId = :deptId"),
    @NamedQuery(name = "HrHolidayLevelDt.findByLocId", query = "SELECT h FROM HrHolidayLevelDt h WHERE h.locId = :locId")})
public class HrHolidayLevelDt implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "GRP_ID")
    private Long grpId;
    @Column(name = "JOB_ID")
    private Long jobId;
    @Column(name = "DEPT_ID")
    private Long deptId;
    @Column(name = "LOC_ID")
    private Long locId;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrHolidayLevelHd hrHolidayLevelHd;

    public HrHolidayLevelDt() {
    }

   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGrpId() {
        return grpId;
    }

    public void setGrpId(Long grpId) {
        this.grpId = grpId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getLocId() {
        return locId;
    }

    public void setLocId(Long locId) {
        this.locId = locId;
    }

    public HrHolidayLevelHd getHrHolidayLevelHd() {
        return hrHolidayLevelHd;
    }

    public void setHrHolidayLevelHd(HrHolidayLevelHd hrHolidayLevelHd) {
        this.hrHolidayLevelHd = hrHolidayLevelHd;
    }

}
