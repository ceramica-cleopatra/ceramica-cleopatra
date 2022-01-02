/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "HR_EMP_HD", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrEmpHd.findAll", query = "SELECT h FROM HrEmpHd h where h.id=:emp_id"),
    @NamedQuery(name = "HrEmpHd.findById", query = "SELECT h FROM HrEmpHd h WHERE h.id = :id"),
    @NamedQuery(name = "HrEmpHd.findByName", query = "SELECT h FROM HrEmpHd h WHERE h.name = :name"),
    @NamedQuery(name = "HrEmpHd.findByAddress", query = "SELECT h FROM HrEmpHd h WHERE h.address = :address"),
    @NamedQuery(name = "HrEmpHd.findByIdent", query = "SELECT h FROM HrEmpHd h WHERE h.ident = :ident"),
    @NamedQuery(name = "HrEmpHd.findByBirthDate", query = "SELECT h FROM HrEmpHd h WHERE h.birthDate = :birthDate"),
    @NamedQuery(name = "HrEmpHd.findByMobile", query = "SELECT h FROM HrEmpHd h WHERE h.mobile = :mobile"),
    @NamedQuery(name = "HrEmpHd.findByPhone", query = "SELECT h FROM HrEmpHd h WHERE h.phone = :phone"),
    @NamedQuery(name = "HrEmpHd.findByGender", query = "SELECT h FROM HrEmpHd h WHERE h.gender = :gender"),
    @NamedQuery(name = "HrEmpHd.findByEMail", query = "SELECT h FROM HrEmpHd h WHERE h.eMail = :eMail"),
    @NamedQuery(name = "HrEmpHd.findByHireDate", query = "SELECT h FROM HrEmpHd h WHERE h.hireDate = :hireDate"),
    @NamedQuery(name = "HrEmpHd.findByFireDate", query = "SELECT h FROM HrEmpHd h WHERE h.fireDate = :fireDate"),
    @NamedQuery(name = "HrEmpHd.findByInsDate", query = "SELECT h FROM HrEmpHd h WHERE h.insDate = :insDate"),
    @NamedQuery(name = "HrEmpHd.findByNotes", query = "SELECT h FROM HrEmpHd h WHERE h.notes = :notes"),
    @NamedQuery(name = "HrEmpHd.findByInsNo", query = "SELECT h FROM HrEmpHd h WHERE h.insNo = :insNo"),
    @NamedQuery(name = "HrEmpHd.findByFired", query = "SELECT h FROM HrEmpHd h WHERE h.fired = :fired"),
    @NamedQuery(name = "HrEmpHd.findByHolidayDay", query = "SELECT h FROM HrEmpHd h WHERE h.holidayDay = :holidayDay"),
    @NamedQuery(name = "HrEmpHd.findByImagePath", query = "SELECT h FROM HrEmpHd h WHERE h.imagePath = :imagePath"),
    @NamedQuery(name = "HrEmpHd.findByIdDate", query = "SELECT h FROM HrEmpHd h WHERE h.idDate = :idDate"),
    @NamedQuery(name = "HrEmpHd.findByIdFrom", query = "SELECT h FROM HrEmpHd h WHERE h.idFrom = :idFrom"),
    @NamedQuery(name = "HrEmpHd.findByReligion", query = "SELECT h FROM HrEmpHd h WHERE h.religion = :religion"),
    @NamedQuery(name = "HrEmpHd.findByEnglishName", query = "SELECT h FROM HrEmpHd h WHERE h.englishName = :englishName"),
    @NamedQuery(name = "HrEmpHd.findByTrnsDate", query = "SELECT h FROM HrEmpHd h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrEmpHd.findByUserName", query = "SELECT h FROM HrEmpHd h WHERE h.userName = :userName"),
    @NamedQuery(name = "HrEmpHd.findByMachineName", query = "SELECT h FROM HrEmpHd h WHERE h.machineName = :machineName"),
    @NamedQuery(name = "HrEmpHd.findByApprovePrivilage", query = "SELECT h FROM HrEmpHd h WHERE h.approvePrivilage = :approvePrivilage"),
    @NamedQuery(name = "HrEmpHd.findByRestInSal", query = "SELECT h FROM HrEmpHd h WHERE h.restInSal = :restInSal"),
    @NamedQuery(name = "HrEmpHd.findByExtraAsHours", query = "SELECT h FROM HrEmpHd h WHERE h.extraAsHours = :extraAsHours"),
    @NamedQuery(name = "HrEmpHd.findByPass", query = "SELECT h FROM HrEmpHd h WHERE h.pass = :pass"),
    @NamedQuery(name = "HrEmpHd.findByCreateBy", query = "SELECT h FROM HrEmpHd h WHERE h.createBy = :createBy"),
    @NamedQuery(name = "HrEmpHd.findByCreateAt", query = "SELECT h FROM HrEmpHd h WHERE h.createAt = :createAt"),
    @NamedQuery(name = "HrEmpHd.findByModifyBy", query = "SELECT h FROM HrEmpHd h WHERE h.modifyBy = :modifyBy"),
    @NamedQuery(name = "HrEmpHd.findByModifyAt", query = "SELECT h FROM HrEmpHd h WHERE h.modifyAt = :modifyAt"),
    @NamedQuery(name = "HrEmpHd.findByA", query = "SELECT h FROM HrEmpHd h WHERE h.a = :a"),
    @NamedQuery(name = "HrEmpHd.findByQuestion", query = "SELECT h FROM HrEmpHd h WHERE h.question = :question"),
    @NamedQuery(name = "HrEmpHd.findByQAnswer", query = "SELECT h FROM HrEmpHd h WHERE h.qAnswer = :qAnswer")})
public class HrEmpHd implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "IDENT")
    private String ident;
    @Column(name = "BIRTH_DATE")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Column(name = "MOBILE")
    private String mobile;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "GENDER")
    private String gender;
    @Column(name = "E_MAIL")
    private String eMail;
    @Column(name = "HIRE_DATE")
    @Temporal(TemporalType.DATE)
    private Date hireDate;
    @Column(name = "FIRE_DATE")
    @Temporal(TemporalType.DATE)
    private Date fireDate;
    @Column(name = "INS_DATE")
    @Temporal(TemporalType.DATE)
    private Date insDate;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "INS_NO")
    private Long insNo;
    @Column(name = "FIRED")
    private String fired;
    @Column(name = "HOLIDAY_DAY")
    private String holidayDay;
    @Column(name = "IMAGE_PATH")
    private String imagePath;
    @Column(name = "ID_DATE")
    @Temporal(TemporalType.DATE)
    private Date idDate;
    @Column(name = "ID_FROM")
    private String idFrom;
    @Column(name = "RELIGION")
    private Integer religion;
    @Column(name = "ENGLISH_NAME")
    private String englishName;
    @Lob
    @Column(name = "IMAGE")
    private Serializable image;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "MACHINE_NAME")
    private String machineName;
    @Column(name = "APPROVE_PRIVILAGE")
    private String approvePrivilage;
    @Column(name = "REST_IN_SAL")
    private String restInSal;
    @Column(name = "EXTRA_AS_HOURS")
    private String extraAsHours;
    @Column(name = "PASS")
    private String pass;
    @Column(name = "CREATE_BY")
    private BigInteger createBy;
    @Column(name = "CREATE_AT")
    @Temporal(TemporalType.DATE)
    private Date createAt;
    @Column(name = "MODIFY_BY")
    private BigInteger modifyBy;
    @Column(name = "MODIFY_AT")
    @Temporal(TemporalType.DATE)
    private Date modifyAt;
    @Column(name = "A")
    private String a;
    @Column(name = "QUESTION")
    private String question;
    @Column(name = "Q_ANSWER")
    private String qAnswer;
    @Column(name = "BANK_EMP_ACCOUNT")
    private String bankEmpAccount;
    @Column(name = "BANK_EMP_ID")
    private String bankEmpId;
    @Column(name = "OLD_MOBILE")
    private String oldMobile;
    @Column(name = "OLD_IDENT")
    private String oldIdent;
    @Column(name = "VISA_CARD_NO")
    private String visaCardNo;
    @Column(name = "THEME")
    private String theme;
    @Column(name = "AUTOMATIC_SWITCH")
    private String automaticSwitch;
    @ManyToOne
    @JoinColumn(name = "BANK_BRN_ID")
    private HrBankBranch hrBankBranch;
    
    @JoinColumn(name = "REG_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrRegion hrRegion;
    @JoinColumn(name = "NATIONALITY", referencedColumnName = "ID")
    @ManyToOne
    private HrNationality hrNationality;
    @JoinColumn(name = "MILITARY", referencedColumnName = "ID")
    @ManyToOne
    private HrMilitarily hrMilitarily;
    @JoinColumn(name = "INS_JOB", referencedColumnName = "ID")
    @ManyToOne
    private HrJobs hrJobs;
    @JoinColumn(name = "INS_OFFICE_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrInsuranceOffice hrInsuranceOffice;
    @JoinColumn(name = "AREA_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrArea hrArea;
    @JoinColumn(name = "BORN_AREA", referencedColumnName = "ID")
    @ManyToOne
    private HrArea hrArea1;
    @OneToMany(mappedBy="hrEmpHd")
    private List<HrManualInOutTrns> hrManualInOutTrnseList;
    @OneToMany(mappedBy = "empApproved")
    private List<HrPersonalOrdrRequest> hrPersonalOrdrRequestList;
    @OneToMany(mappedBy = "empNo")
    private List<HrPersonalOrdrRequest> hrEmpPersonalOrdrRequestList;

    @Column(name = "AMRA")
    private String amra;

    public List<HrPersonalOrdrRequest> getHrPersonalOrdrRequestList() {
        return hrPersonalOrdrRequestList;
    }

    public List<HrPersonalOrdrRequest> getHrEmpPersonalOrdrRequestList() {
        return hrEmpPersonalOrdrRequestList;
    }

    public void setHrEmpPersonalOrdrRequestList(List<HrPersonalOrdrRequest> hrEmpPersonalOrdrRequestList) {
        this.hrEmpPersonalOrdrRequestList = hrEmpPersonalOrdrRequestList;
    }

    public void setHrPersonalOrdrRequestList(List<HrPersonalOrdrRequest> hrPersonalOrdrRequestList) {
        this.hrPersonalOrdrRequestList = hrPersonalOrdrRequestList;
    }
    
    public List<HrManualInOutTrns> getHrManualInOutTrnseList() {
        return hrManualInOutTrnseList;
    }

    public void setHrManualInOutTrnseList(List<HrManualInOutTrns> hrManualInOutTrnseList) {
        this.hrManualInOutTrnseList = hrManualInOutTrnseList;
    }

    public String getBankEmpAccount() {
        return bankEmpAccount;
    }

    public void setBankEmpAccount(String bankEmpAccount) {
        this.bankEmpAccount = bankEmpAccount;
    }

    public String getBankEmpId() {
        return bankEmpId;
    }

    public void setBankEmpId(String bankEmpId) {
        this.bankEmpId = bankEmpId;
    }

    
   

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public HrBankBranch getHrBankBranch() {
        return hrBankBranch;
    }

    public void setHrBankBranch(HrBankBranch hrBankBranch) {
        this.hrBankBranch = hrBankBranch;
    }

    public String getqAnswer() {
        return qAnswer;
    }

    public void setqAnswer(String qAnswer) {
        this.qAnswer = qAnswer;
    }

    public HrEmpHd() {
    }

    public HrEmpHd(Long id) {
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getFireDate() {
        return fireDate;
    }

    public void setFireDate(Date fireDate) {
        this.fireDate = fireDate;
    }

    public Date getInsDate() {
        return insDate;
    }

    public void setInsDate(Date insDate) {
        this.insDate = insDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getInsNo() {
        return insNo;
    }

    public void setInsNo(Long insNo) {
        this.insNo = insNo;
    }

    public String getFired() {
        return fired;
    }

    public void setFired(String fired) {
        this.fired = fired;
    }

    public String getHolidayDay() {
        return holidayDay;
    }

    public void setHolidayDay(String holidayDay) {
        this.holidayDay = holidayDay;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Date getIdDate() {
        return idDate;
    }

    public void setIdDate(Date idDate) {
        this.idDate = idDate;
    }

    public String getIdFrom() {
        return idFrom;
    }

    public void setIdFrom(String idFrom) {
        this.idFrom = idFrom;
    }

    public Integer getReligion() {
        return religion;
    }

    public void setReligion(Integer religion) {
        this.religion = religion;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public Serializable getImage() {
        return image;
    }

    public void setImage(Serializable image) {
        this.image = image;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getApprovePrivilage() {
        return approvePrivilage;
    }

    public void setApprovePrivilage(String approvePrivilage) {
        this.approvePrivilage = approvePrivilage;
    }

    public String getRestInSal() {
        return restInSal;
    }

    public void setRestInSal(String restInSal) {
        this.restInSal = restInSal;
    }

    public String getExtraAsHours() {
        return extraAsHours;
    }

    public void setExtraAsHours(String extraAsHours) {
        this.extraAsHours = extraAsHours;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public BigInteger getCreateBy() {
        return createBy;
    }

    public void setCreateBy(BigInteger createBy) {
        this.createBy = createBy;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public BigInteger getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(BigInteger modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyAt() {
        return modifyAt;
    }

    public void setModifyAt(Date modifyAt) {
        this.modifyAt = modifyAt;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQAnswer() {
        return qAnswer;
    }

    public void setQAnswer(String qAnswer) {
        this.qAnswer = qAnswer;
    }


    public HrRegion getHrRegion() {
        return hrRegion;
    }

    public void setHrRegion(HrRegion hrRegion) {
        this.hrRegion = hrRegion;
    }

    public HrNationality getHrNationality() {
        return hrNationality;
    }

    public void setHrNationality(HrNationality hrNationality) {
        this.hrNationality = hrNationality;
    }

    public HrMilitarily getHrMilitarily() {
        return hrMilitarily;
    }

    public void setHrMilitarily(HrMilitarily hrMilitarily) {
        this.hrMilitarily = hrMilitarily;
    }

    public HrJobs getHrJobs() {
        return hrJobs;
    }

    public void setHrJobs(HrJobs hrJobs) {
        this.hrJobs = hrJobs;
    }

    public HrInsuranceOffice getHrInsuranceOffice() {
        return hrInsuranceOffice;
    }

    public void setHrInsuranceOffice(HrInsuranceOffice hrInsuranceOffice) {
        this.hrInsuranceOffice = hrInsuranceOffice;
    }

    public HrArea getHrArea() {
        return hrArea;
    }

    public void setHrArea(HrArea hrArea) {
        this.hrArea = hrArea;
    }

    public HrArea getHrArea1() {
        return hrArea1;
    }

    public void setHrArea1(HrArea hrArea1) {
        this.hrArea1 = hrArea1;
    }

    public String getOldMobile() {
        return oldMobile;
    }

    public void setOldMobile(String oldMobile) {
        this.oldMobile = oldMobile;
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
        if (!(object instanceof HrEmpHd)) {
            return false;
        }
        HrEmpHd other = (HrEmpHd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrEmpHd[id=" + id + "]";
    }

    public String getOldIdent() {
        return oldIdent;
    }

    public void setOldIdent(String oldIdent) {
        this.oldIdent = oldIdent;
    }

    public String getVisaCardNo() {
        return visaCardNo;
    }

    public void setVisaCardNo(String visaCardNo) {
        this.visaCardNo = visaCardNo;
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

    public String getAmra() {
        return amra;
    }

    public void setAmra(String amra) {
        this.amra = amra;
    }

    
    

}
