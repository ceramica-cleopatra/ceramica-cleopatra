/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "HR_EMP_INFO_FULL", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrEmpInfo.findAllEmpInfo", query = "SELECT h FROM HrEmpInfo h where h.empStatus='R'"),
    @NamedQuery(name = "HrEmpInfo.findAll", query = "SELECT h FROM HrEmpInfo h where h.empNo in(select o.empNo from HrEmpMangers o where o.mngDept=:dept and o.mngLoc=:loc and o.mngGrp=:grp and o.mngJob=:job) and h.empStatus='R'"),
    @NamedQuery(name = "HrEmpInfo.findByEmpNo", query = "SELECT h FROM HrEmpInfo h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrEmpInfo.findEmpNames", query = "SELECT h FROM HrEmpInfo h order by h.empName"),
    @NamedQuery(name = "HrEmpInfo.findByEmpName", query = "SELECT h FROM HrEmpInfo h WHERE h.empName like :emp_name"),
    @NamedQuery(name = "HrEmpInfo.findByComId", query = "SELECT h FROM HrEmpInfo h WHERE h.comId = :comId"),
    @NamedQuery(name = "HrEmpInfo.findByComName", query = "SELECT h FROM HrEmpInfo h WHERE h.comName = :comName"),
    @NamedQuery(name = "HrEmpInfo.findByDeptId", query = "SELECT h FROM HrEmpInfo h WHERE h.deptId = :deptId"),
    @NamedQuery(name = "HrEmpInfo.findByDeptName", query = "SELECT h FROM HrEmpInfo h WHERE h.deptName = :deptName"),
    @NamedQuery(name = "HrEmpInfo.findByJobGrpId", query = "SELECT h FROM HrEmpInfo h WHERE h.jobGrpId = :jobGrpId"),
    @NamedQuery(name = "HrEmpInfo.findByJobGrpName", query = "SELECT h FROM HrEmpInfo h WHERE h.jobGrpName = :jobGrpName"),
    @NamedQuery(name = "HrEmpInfo.findByJobId", query = "SELECT h FROM HrEmpInfo h WHERE h.jobId = :jobId"),
    @NamedQuery(name = "HrEmpInfo.findByJobName", query = "SELECT h FROM HrEmpInfo h WHERE h.jobName = :jobName"),
    @NamedQuery(name = "HrEmpInfo.findByLocId", query = "SELECT h FROM HrEmpInfo h WHERE h.locId = :locId"),
    @NamedQuery(name = "HrEmpInfo.findByLocation", query = "SELECT h FROM HrEmpInfo h WHERE h.location = :location"),
    @NamedQuery(name = "HrEmpInfo.findByHireDate", query = "SELECT h FROM HrEmpInfo h WHERE h.hireDate = :hireDate"),
    @NamedQuery(name = "HrEmpInfo.findByWorkingYears", query = "SELECT h FROM HrEmpInfo h WHERE h.workingYears = :workingYears"),
    @NamedQuery(name = "HrEmpInfo.findByBirthDate", query = "SELECT h FROM HrEmpInfo h WHERE h.birthDate = :birthDate"),
    @NamedQuery(name = "HrEmpInfo.findByTotSal", query = "SELECT h FROM HrEmpInfo h WHERE h.totSal = :totSal"),
    @NamedQuery(name = "HrEmpInfo.findByBasicSal", query = "SELECT h FROM HrEmpInfo h WHERE h.basicSal = :basicSal"),
    @NamedQuery(name = "HrEmpInfo.findByVarSal", query = "SELECT h FROM HrEmpInfo h WHERE h.varSal = :varSal"),
    @NamedQuery(name = "HrEmpInfo.findByOthers", query = "SELECT h FROM HrEmpInfo h WHERE h.others = :others"),
    @NamedQuery(name = "HrEmpInfo.findByTax", query = "SELECT h FROM HrEmpInfo h WHERE h.tax = :tax"),
    @NamedQuery(name = "HrEmpInfo.findByDayOff", query = "SELECT h FROM HrEmpInfo h WHERE h.dayOff = :dayOff"),
    @NamedQuery(name = "HrEmpInfo.findByInsNo", query = "SELECT h FROM HrEmpInfo h WHERE h.insNo = :insNo"),
    @NamedQuery(name = "HrEmpInfo.findByAddress", query = "SELECT h FROM HrEmpInfo h WHERE h.address = :address"),
    @NamedQuery(name = "HrEmpInfo.findByIdent", query = "SELECT h FROM HrEmpInfo h WHERE h.ident = :ident"),
    @NamedQuery(name = "HrEmpInfo.findByPass", query = "SELECT h FROM HrEmpInfo h WHERE h.pass = :pass"),
    @NamedQuery(name = "HrEmpInfo.finduser", query = "select count(o.empNo) from HrEmpInfo o where  o.empNo=:emp and o.empStatus='R' and o.pass=:ps"),
    @NamedQuery(name = "HrEmpInfo.finduserbyid", query = "select o from HrEmpInfo o where o.empNo=:emp"),
    @NamedQuery(name = "HrEmpInfo.findchatemp", query = "select o from HrEmpInfo o where (o.locId=:loc or :loc is null) and o.empStatus='R' and (o.deptId=:dept or :dept is null) and (o.jobId=:job or :job is null) and o.empNo<>:emp"),
    @NamedQuery(name = "HrEmpInfo.findAlternativeEmp1", query = "select o from HrEmpInfo o where o.locId=:loc and o.deptId=:dept and o.empNo<>:emp"),
    @NamedQuery(name = "HrEmpInfo.findDeptManagersMail", query = "select o.eMail from HrEmpInfo o where o.empStatus='R' and o.eMail is not null and o.jobGrpId in (1,7,8) and o.eMail like :mail"),
    @NamedQuery(name = "HrEmpInfo.findManagersMail", query = "select o from HrEmpInfo o where o.eMail is not null and o.jobGrpId in (1,7,8) and o.empStatus='R' and o.locId=:loc"),
    @NamedQuery(name = "HrEmpInfo.findLocationManagers", query = "select o from HrEmpInfo o where o.jobGrpId in (1,7,8) and o.empStatus='R' and o.locId=:loc"),
    @NamedQuery(name = "HrEmpInfo.chkEmpHaveEmail", query = "select count(o) from HrEmpInfo o where o.eMail is null and o.empNo=:empNo"),
    @NamedQuery(name = "HrEmpInfo.findAlternativeEmp", query = "select o from HrEmpInfo o where (o.locId=:loc and 1<=(select count(x) from  HrEmpInfo x where x.deptId=:dept and x.locId=:loc and x.empNo<>:emp) "
    + "and o.deptId=:dept and o.empNo<>:emp) or (o.deptId=:dept and o.empNo<>:emp and 0=(select count(y) from  HrEmpInfo y where y.deptId=:dept and y.locId=:loc and y.empNo<>:emp)) and o.empStatus='R'")})
public class HrEmpInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Id
    @Column(name = "EMP_NO")
    private long empNo;
    @Column(name = "EMP_NAME")
    private String empName;
    @Column(name = "COM_ID")
    private Long comId;
    @Column(name = "COM_NAME")
    private String comName;
    @Column(name = "DEPT_ID")
    private Long deptId;
    @Column(name = "DEPT_NAME")
    private String deptName;
    @Column(name = "JOB_GRP_ID")
    private Long jobGrpId;
    @Column(name = "JOB_GRP_NAME")
    private String jobGrpName;
    @Column(name = "JOB_ID")
    private Long jobId;
    @Column(name = "JOB_NAME")
    private String jobName;
    @Column(name = "LOC_ID")
    private Long locId;
    @Column(name = "LOCATION")
    private String location;
    @Column(name = "HIRE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hireDate;
    @Column(name = "WORKING_YEARS")
    private Long workingYears;
    @Column(name = "BIRTH_DATE")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Column(name = "TOT_SAL")
    private Long totSal;
    @Column(name = "BASIC_SAL")
    private Long basicSal;
    @Column(name = "VAR_SAL")
    private Long varSal;
    @Column(name = "OTHERS")
    private Long others;
    @Column(name = "TAX")
    private Long tax;
    @Column(name = "DAY_OFF")
    private Long dayOff;
    @Column(name = "INS_NO")
    private Long insNo;
    @Column(name = "EMP_REG_ID")
    private Long empRegId;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "IDENT")
    private String ident;
    @Column(name = "PASS")
    private String pass;
    @Column(name = "E_MAIL")
    private String eMail;
    @Column(name = "THEME")
    private String theme;
    @Column(name = "AUTOMATIC_SWITCH")
    private String automaticSwitch;
    @Column(name = "EMP_STATUS")
    private Character empStatus;
    @Column(name = "EXTRA_AS_HOURS")
    private String extraAsHours;
    @Column(name = "CRMK_ID")
    private Long crmkId;
    @Column(name = "PASSWORD_EXPIRY_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date passwordExpiryDate;
    @OneToMany(mappedBy = "alternativeEmp")
    private List<HrHolidayRequest> alternativeEmpList;
    @OneToMany(mappedBy = "mngNo")
    private List<HrHolidayRequest> HolidayMngList;
    @OneToMany(mappedBy = "mngNo")
    private List<HrAuthorizeRequest> AuthorizeMngList;
    @OneToMany(mappedBy = "empNo")
    private List<HrGzaDt> gza_emp;
    @OneToMany(mappedBy = "mngNo")
    private List<HrSchedule> hrScheduleList;
    @OneToMany(mappedBy = "empNo")
    private List<HrShiftChangeRequest> hrShiftChangeRequestEmpList;
    @OneToMany(mappedBy = "mngNo")
    private List<HrShiftChangeRequest> hrShiftChangeRequestMngList;
    @OneToMany(mappedBy = "empId")
    private List<HrTamyozDt> hrTamyozDtList;
    @OneToMany(mappedBy = "hrId")
    private List<CrmkOrdersNotPaied> crmkOrdersNotPaiedList;
    @OneToMany(mappedBy = "hrId")
    private List<CrmkOrdersNotDelivered> crmkOrdersNotDeliveredList;
    @OneToMany(mappedBy = "mngNo")
    private List<HrWHolidayAttendanceReq> hrWHolidayAttendanceReqList;
    @OneToMany(mappedBy = "empId")
    private List<HrCheckupDutyDt> hrCheckupDutyDtList;
    @OneToMany(mappedBy = "entryId")
    private List<HrCheckupDutyHd> hrCheckupDutyHdList;

    public List<HrCheckupDutyHd> getHrCheckupDutyHdList() {
        return hrCheckupDutyHdList;
    }

    public void setHrCheckupDutyHdList(List<HrCheckupDutyHd> hrCheckupDutyHdList) {
        this.hrCheckupDutyHdList = hrCheckupDutyHdList;
    }

    public List<CrmkOrdersNotDelivered> getCrmkOrdersNotDeliveredList() {
        return crmkOrdersNotDeliveredList;
    }

    public List<HrWHolidayAttendanceReq> getHrWHolidayAttendanceReqList() {
        return hrWHolidayAttendanceReqList;
    }

    public void setHrWHolidayAttendanceReqList(List<HrWHolidayAttendanceReq> hrWHolidayAttendanceReqList) {
        this.hrWHolidayAttendanceReqList = hrWHolidayAttendanceReqList;
    }

    public void setCrmkOrdersNotDeliveredList(List<CrmkOrdersNotDelivered> crmkOrdersNotDeliveredList) {
        this.crmkOrdersNotDeliveredList = crmkOrdersNotDeliveredList;
    }

    public List<HrTamyozDt> getHrTamyozDtList() {
        return hrTamyozDtList;
    }

    public List<CrmkOrdersNotPaied> getCrmkOrdersNotPaiedList() {
        return crmkOrdersNotPaiedList;
    }

    public void setCrmkOrdersNotPaiedList(List<CrmkOrdersNotPaied> crmkOrdersNotPaiedList) {
        this.crmkOrdersNotPaiedList = crmkOrdersNotPaiedList;
    }

    public void setHrTamyozDtList(List<HrTamyozDt> hrTamyozDtList) {
        this.hrTamyozDtList = hrTamyozDtList;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public List<HrShiftChangeRequest> getHrShiftChangeRequestEmpList() {
        return hrShiftChangeRequestEmpList;
    }

    public void setHrShiftChangeRequestEmpList(List<HrShiftChangeRequest> hrShiftChangeRequestEmpList) {
        this.hrShiftChangeRequestEmpList = hrShiftChangeRequestEmpList;
    }

    public List<HrShiftChangeRequest> getHrShiftChangeRequestMngList() {
        return hrShiftChangeRequestMngList;
    }

    public void setHrShiftChangeRequestMngList(List<HrShiftChangeRequest> hrShiftChangeRequestMngList) {
        this.hrShiftChangeRequestMngList = hrShiftChangeRequestMngList;
    }

    public List<HrSchedule> getHrScheduleList() {
        return hrScheduleList;
    }

    public void setHrScheduleList(List<HrSchedule> hrScheduleList) {
        this.hrScheduleList = hrScheduleList;
    }

    public List<HrGzaDt> getGza_emp() {
        return gza_emp;
    }

    public void setGza_emp(List<HrGzaDt> gza_emp) {
        this.gza_emp = gza_emp;
    }

    public List<HrHolidayRequest> getHolidayMngList() {
        return HolidayMngList;
    }

    public List<HrAuthorizeRequest> getAuthorizeMngList() {
        return AuthorizeMngList;
    }

    public void setAuthorizeMngList(List<HrAuthorizeRequest> AuthorizeMngList) {
        this.AuthorizeMngList = AuthorizeMngList;
    }

    public void setHolidayMngList(List<HrHolidayRequest> HolidayMngList) {
        this.HolidayMngList = HolidayMngList;
    }

    public List<HrHolidayRequest> getAlternativeEmpList() {
        return alternativeEmpList;
    }

    public void setAlternativeEmpList(List<HrHolidayRequest> alternativeEmpList) {
        this.alternativeEmpList = alternativeEmpList;
    }

    public HrEmpInfo() {
    }

    public long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(long empNo) {
        this.empNo = empNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Long getComId() {
        return comId;
    }

    public void setComId(Long comId) {
        this.comId = comId;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Long getJobGrpId() {
        return jobGrpId;
    }

    public void setJobGrpId(Long jobGrpId) {
        this.jobGrpId = jobGrpId;
    }

    public String getJobGrpName() {
        return jobGrpName;
    }

    public void setJobGrpName(String jobGrpName) {
        this.jobGrpName = jobGrpName;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Long getLocId() {
        return locId;
    }

    public void setLocId(Long locId) {
        this.locId = locId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Long getEmpRegId() {
        return empRegId;
    }

    public void setEmpRegId(Long empRegId) {
        this.empRegId = empRegId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public String getPass() {
        return pass;
    }

    public Long getBasicSal() {
        return basicSal;
    }

    public void setBasicSal(Long basicSal) {
        this.basicSal = basicSal;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Long getDayOff() {
        return dayOff;
    }

    public void setDayOff(Long dayOff) {
        this.dayOff = dayOff;
    }

    public Long getInsNo() {
        return insNo;
    }

    public void setInsNo(Long insNo) {
        this.insNo = insNo;
    }

    public Long getOthers() {
        return others;
    }

    public void setOthers(Long others) {
        this.others = others;
    }

    public Long getTax() {
        return tax;
    }

    public void setTax(Long tax) {
        this.tax = tax;
    }

    public Long getTotSal() {
        return totSal;
    }

    public void setTotSal(Long totSal) {
        this.totSal = totSal;
    }

    public Long getVarSal() {
        return varSal;
    }

    public void setVarSal(Long varSal) {
        this.varSal = varSal;
    }

    public Long getWorkingYears() {
        return workingYears;
    }

    public void setWorkingYears(Long workingYears) {
        this.workingYears = workingYears;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public List<HrCheckupDutyDt> getHrCheckupDutyDtList() {
        return hrCheckupDutyDtList;
    }

    public void setHrCheckupDutyDtList(List<HrCheckupDutyDt> hrCheckupDutyDtList) {
        this.hrCheckupDutyDtList = hrCheckupDutyDtList;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getAutomaticSwitch() {
        return automaticSwitch;
    }

    public void setAutomaticSwitch(String automaticSwitch) {
        this.automaticSwitch = automaticSwitch;
    }

    public Character getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(Character empStatus) {
        this.empStatus = empStatus;
    }

    public String getExtraAsHours() {
        return extraAsHours;
    }

    public void setExtraAsHours(String extraAsHours) {
        this.extraAsHours = extraAsHours;
    }

    public Long getCrmkId() {
        return crmkId;
    }

    public void setCrmkId(Long crmkId) {
        this.crmkId = crmkId;
    }

    public Date getPasswordExpiryDate() {
        return passwordExpiryDate;
    }

    public void setPasswordExpiryDate(Date passwordExpiryDate) {
        this.passwordExpiryDate = passwordExpiryDate;
    }
    
}
