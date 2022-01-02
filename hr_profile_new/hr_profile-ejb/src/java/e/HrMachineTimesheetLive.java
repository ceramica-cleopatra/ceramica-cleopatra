/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author data
 */
@Entity
@Table(name = "HR_MACHINE_TIMESHEET_LIVE", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrMachineTimesheetLive.findAll", query = "SELECT h FROM HrMachineTimesheetLive h where h.locId in (SELECT x.hrLocationIpMappingPK.locId FROM HrLocationIpMapping x WHERE x.hrLocationIpMappingPK.ip = :ip) order by h.devDt desc"),
    @NamedQuery(name = "HrMachineTimesheetLive.findById", query = "SELECT h FROM HrMachineTimesheetLive h WHERE h.id = :id"),
    @NamedQuery(name = "HrMachineTimesheetLive.findByEmpNo", query = "SELECT h FROM HrMachineTimesheetLive h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrMachineTimesheetLive.findByEmpName", query = "SELECT h FROM HrMachineTimesheetLive h WHERE h.empName = :empName"),
    @NamedQuery(name = "HrMachineTimesheetLive.findByDeptName", query = "SELECT h FROM HrMachineTimesheetLive h WHERE h.deptName = :deptName"),
    @NamedQuery(name = "HrMachineTimesheetLive.findByJobName", query = "SELECT h FROM HrMachineTimesheetLive h WHERE h.jobName = :jobName"),
    @NamedQuery(name = "HrMachineTimesheetLive.findByJobGrpName", query = "SELECT h FROM HrMachineTimesheetLive h WHERE h.jobGrpName = :jobGrpName"),
    @NamedQuery(name = "HrMachineTimesheetLive.findByTrnsType", query = "SELECT h FROM HrMachineTimesheetLive h WHERE h.trnsType = :trnsType"),
    @NamedQuery(name = "HrMachineTimesheetLive.findByTrnsTime", query = "SELECT h FROM HrMachineTimesheetLive h WHERE h.trnsTime = :trnsTime")})
public class HrMachineTimesheetLive implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "ID")
    @Id
    private Long id;
    @Basic(optional = false)
    @Column(name = "EMP_NO")
    private Long empNo;
    @Column(name = "EMP_NAME")
    private String empName;
    @Column(name = "DEPT_NAME")
    private String deptName;
    @Column(name = "JOB_NAME")
    private String jobName;
    @Column(name = "JOB_GRP_NAME")
    private String jobGrpName;
    @Column(name = "TRNS_TYPE")
    private String trnsType;
    @Column(name = "TRNS_TIME")
    private String trnsTime;
    @Column(name = "IP_ADDRESS")
    private Long ipAddress;
    @Column(name = "LOC_ID")
    private Long locId;
    @Column(name = "DEVDT")
    private Long devDt;


    public HrMachineTimesheetLive() {
    }

    public Long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Long empNo) {
        this.empNo = empNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGrpName() {
        return jobGrpName;
    }

    public void setJobGrpName(String jobGrpName) {
        this.jobGrpName = jobGrpName;
    }

    public String getTrnsType() {
        return trnsType;
    }

    public void setTrnsType(String trnsType) {
        this.trnsType = trnsType;
    }

    public String getTrnsTime() {
        return trnsTime;
    }

    public void setTrnsTime(String trnsTime) {
        this.trnsTime = trnsTime;
    }

    public Long getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(Long ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Long getDevDt() {
        return devDt;
    }

    public void setDevDt(Long devDt) {
        this.devDt = devDt;
    }

    public Long getLocId() {
        return locId;
    }

    public void setLocId(Long locId) {
        this.locId = locId;
    }

   
}
