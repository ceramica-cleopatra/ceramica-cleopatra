/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "CRMK_EMPLOYEE",schema="CRMK")
@NamedQueries({
    @NamedQuery(name = "CrmkEmployee.findAllEmpName", query = "SELECT c.name FROM CrmkEmployee c"),
    @NamedQuery(name = "CrmkEmployee.findAll", query = "SELECT c FROM CrmkEmployee c , HrEmpInfo e where c.hrId.empNo=e.empNo and e.jobId in(49,112)"),
    @NamedQuery(name = "CrmkEmployee.findById", query = "SELECT c FROM CrmkEmployee c WHERE c.id = :id"),
    @NamedQuery(name = "CrmkEmployee.findByName", query = "SELECT c FROM CrmkEmployee c WHERE c.name = :name"),
    @NamedQuery(name = "CrmkEmployee.findByGender", query = "SELECT c FROM CrmkEmployee c WHERE c.gender = :gender"),
    @NamedQuery(name = "CrmkEmployee.findByRlgn", query = "SELECT c FROM CrmkEmployee c WHERE c.rlgn = :rlgn"),
    @NamedQuery(name = "CrmkEmployee.findByBirthDate", query = "SELECT c FROM CrmkEmployee c WHERE c.birthDate = :birthDate"),
    @NamedQuery(name = "CrmkEmployee.findByStartDate", query = "SELECT c FROM CrmkEmployee c WHERE c.startDate = :startDate"),
    @NamedQuery(name = "CrmkEmployee.findByAddress", query = "SELECT c FROM CrmkEmployee c WHERE c.address = :address"),
    @NamedQuery(name = "CrmkEmployee.findByEducate", query = "SELECT c FROM CrmkEmployee c WHERE c.educate = :educate"),
    @NamedQuery(name = "CrmkEmployee.findByIdNo", query = "SELECT c FROM CrmkEmployee c WHERE c.idNo = :idNo"),
    @NamedQuery(name = "CrmkEmployee.findBySalesman", query = "SELECT c FROM CrmkEmployee c WHERE c.salesman = :salesman"),
    @NamedQuery(name = "CrmkEmployee.findByHomePhone", query = "SELECT c FROM CrmkEmployee c WHERE c.homePhone = :homePhone"),
    @NamedQuery(name = "CrmkEmployee.findByOfficePhone", query = "SELECT c FROM CrmkEmployee c WHERE c.officePhone = :officePhone"),
    @NamedQuery(name = "CrmkEmployee.findByMobile", query = "SELECT c FROM CrmkEmployee c WHERE c.mobile = :mobile"),
    @NamedQuery(name = "CrmkEmployee.findByEMail", query = "SELECT c FROM CrmkEmployee c WHERE c.eMail = :eMail"),
    @NamedQuery(name = "CrmkEmployee.findByNotes", query = "SELECT c FROM CrmkEmployee c WHERE c.notes = :notes"),
    @NamedQuery(name = "CrmkEmployee.findByHrId", query = "SELECT c FROM CrmkEmployee c WHERE c.hrId.empNo = :hrId")})
public class CrmkEmployee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Column(name = "GENDER")
    private Character gender;
    @Column(name = "RLGN")
    private Character rlgn;
    @Column(name = "BIRTH_DATE")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "EDUCATE")
    private String educate;
    @Basic(optional = false)
    @Column(name = "ID_NO")
    private String idNo;
    @Column(name = "SALESMAN")
    private Character salesman;
    @Column(name = "HOME_PHONE")
    private String homePhone;
    @Column(name = "OFFICE_PHONE")
    private String officePhone;
    @Column(name = "MOBILE")
    private String mobile;
    @Column(name = "E_MAIL")
    private Long eMail;
    @Column(name = "NOTES")
    private String notes;
    @ManyToOne
    @JoinColumn(name = "HR_ID",referencedColumnName="EMP_NO")
    private HrEmpInfo hrId;

    public CrmkEmployee() {
    }

    public CrmkEmployee(Long id) {
        this.id = id;
    }

    public CrmkEmployee(Long id, String name, String idNo) {
        this.id = id;
        this.name = name;
        this.idNo = idNo;
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

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public Character getRlgn() {
        return rlgn;
    }

    public void setRlgn(Character rlgn) {
        this.rlgn = rlgn;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEducate() {
        return educate;
    }

    public void setEducate(String educate) {
        this.educate = educate;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public Character getSalesman() {
        return salesman;
    }

    public void setSalesman(Character salesman) {
        this.salesman = salesman;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getEMail() {
        return eMail;
    }

    public void setEMail(Long eMail) {
        this.eMail = eMail;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public HrEmpInfo getHrId() {
        return hrId;
    }

    public void setHrId(HrEmpInfo hrId) {
        this.hrId = hrId;
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
        if (!(object instanceof CrmkEmployee)) {
            return false;
        }
        CrmkEmployee other = (CrmkEmployee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.CrmkEmployee[id=" + id + "]";
    }

}
