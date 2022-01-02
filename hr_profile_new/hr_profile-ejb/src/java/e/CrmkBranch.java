/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "CRMK_BRANCH", catalog = "", schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "CrmkBranch.findAll", query = "SELECT c FROM CrmkBranch c"),
    @NamedQuery(name = "CrmkBranch.findStore", query = "SELECT c FROM CrmkBranch c where c.storeShow='T'"),
    @NamedQuery(name = "CrmkBranch.findStoreByGovern", query = "SELECT c FROM CrmkBranch c where c.storeShow='T'"),
    @NamedQuery(name = "CrmkBranch.findShow", query = "SELECT c FROM CrmkBranch c where c.storeShow='W'"),
    @NamedQuery(name = "CrmkBranch.findShowByNo", query = "SELECT c FROM CrmkBranch c where c.storeShow='W' and c.no=:no"),
    @NamedQuery(name = "CrmkBranch.findById", query = "SELECT c FROM CrmkBranch c WHERE c.id = :id"),
    @NamedQuery(name = "CrmkBranch.findByName", query = "SELECT c FROM CrmkBranch c WHERE c.name = :name"),
    @NamedQuery(name = "CrmkBranch.findByFloors", query = "SELECT c FROM CrmkBranch c WHERE c.floors = :floors"),
    @NamedQuery(name = "CrmkBranch.findByTotArea", query = "SELECT c FROM CrmkBranch c WHERE c.totArea = :totArea"),
    @NamedQuery(name = "CrmkBranch.findByPhone1", query = "SELECT c FROM CrmkBranch c WHERE c.phone1 = :phone1"),
    @NamedQuery(name = "CrmkBranch.findByPhone2", query = "SELECT c FROM CrmkBranch c WHERE c.phone2 = :phone2"),
    @NamedQuery(name = "CrmkBranch.findByPhone3", query = "SELECT c FROM CrmkBranch c WHERE c.phone3 = :phone3"),
    @NamedQuery(name = "CrmkBranch.findByPhone4", query = "SELECT c FROM CrmkBranch c WHERE c.phone4 = :phone4"),
    @NamedQuery(name = "CrmkBranch.findByFax1", query = "SELECT c FROM CrmkBranch c WHERE c.fax1 = :fax1"),
    @NamedQuery(name = "CrmkBranch.findByFax2", query = "SELECT c FROM CrmkBranch c WHERE c.fax2 = :fax2"),
    @NamedQuery(name = "CrmkBranch.findByEMail", query = "SELECT c FROM CrmkBranch c WHERE c.eMail = :eMail"),
    @NamedQuery(name = "CrmkBranch.findByNotes", query = "SELECT c FROM CrmkBranch c WHERE c.notes = :notes"),
    @NamedQuery(name = "CrmkBranch.findByStoreShow", query = "SELECT c FROM CrmkBranch c WHERE c.storeShow = :storeShow"),
    @NamedQuery(name = "CrmkBranch.findByNo", query = "SELECT c FROM CrmkBranch c WHERE c.no = :no"),
    @NamedQuery(name = "CrmkBranch.findByUnderProd", query = "SELECT c FROM CrmkBranch c WHERE c.underProd = :underProd"),
    @NamedQuery(name = "CrmkBranch.findByEmpCount", query = "SELECT c FROM CrmkBranch c WHERE c.empCount = :empCount"),
    @NamedQuery(name = "CrmkBranch.findByAddress", query = "SELECT c FROM CrmkBranch c WHERE c.address = :address")})
public class CrmkBranch implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Column(name = "FLOORS")
    private Short floors;
    @Column(name = "TOT_AREA")
    private BigDecimal totArea;
    @Column(name = "PHONE1")
    private String phone1;
    @Column(name = "PHONE2")
    private String phone2;
    @Column(name = "PHONE3")
    private String phone3;
    @Column(name = "PHONE4")
    private String phone4;
    @Column(name = "FAX1")
    private String fax1;
    @Column(name = "FAX2")
    private String fax2;
    @Column(name = "E_MAIL")
    private String eMail;
    @Column(name = "NOTES")
    private String notes;
    @Basic(optional = false)
    @Column(name = "STORE_SHOW")
    private char storeShow;
    @Basic(optional = false)
    @Column(name = "NO")
    private long no;
    @Column(name = "UNDER_PROD")
    private Character underProd;
    @Column(name = "EMP_COUNT")
    private Long empCount;
    @Column(name = "ADDRESS")
    private String address;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmkBranch")
    private List<CrmkOrdrHd> crmkOrdrHdList;
    @OneToMany(mappedBy = "storeId")
    private List<CrmkOrdrSader> crmkOrdrSaderStoreList;
    @OneToMany( mappedBy = "brnId")
    private List<CrmkOrdrSader> crmkOrdrSaderList;
    @OneToMany(mappedBy="brnId")
    private List<HrPersonalOrdrRequest> hrPersonalOrdrRequestList;
    public CrmkBranch() {
    }

    public CrmkBranch(Long id) {
        this.id = id;
    }

    public List<CrmkOrdrSader> getCrmkOrdrSaderList() {
        return crmkOrdrSaderList;
    }

    public void setCrmkOrdrSaderList(List<CrmkOrdrSader> crmkOrdrSaderList) {
        this.crmkOrdrSaderList = crmkOrdrSaderList;
    }

    public List<CrmkOrdrSader> getCrmkOrdrSaderStoreList() {
        return crmkOrdrSaderStoreList;
    }

    public void setCrmkOrdrSaderStoreList(List<CrmkOrdrSader> crmkOrdrSaderStoreList) {
        this.crmkOrdrSaderStoreList = crmkOrdrSaderStoreList;
    }

   
    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public CrmkBranch(Long id, String name, char storeShow, long no) {
        this.id = id;
        this.name = name;
        this.storeShow = storeShow;
        this.no = no;
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

    public Short getFloors() {
        return floors;
    }

    public void setFloors(Short floors) {
        this.floors = floors;
    }

    public BigDecimal getTotArea() {
        return totArea;
    }

    public void setTotArea(BigDecimal totArea) {
        this.totArea = totArea;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public String getPhone4() {
        return phone4;
    }

    public void setPhone4(String phone4) {
        this.phone4 = phone4;
    }

    public String getFax1() {
        return fax1;
    }

    public void setFax1(String fax1) {
        this.fax1 = fax1;
    }

    public String getFax2() {
        return fax2;
    }

    public void setFax2(String fax2) {
        this.fax2 = fax2;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public char getStoreShow() {
        return storeShow;
    }

    public void setStoreShow(char storeShow) {
        this.storeShow = storeShow;
    }

    public long getNo() {
        return no;
    }

    public void setNo(long no) {
        this.no = no;
    }

    public Character getUnderProd() {
        return underProd;
    }

    public void setUnderProd(Character underProd) {
        this.underProd = underProd;
    }

    public Long getEmpCount() {
        return empCount;
    }

    public void setEmpCount(Long empCount) {
        this.empCount = empCount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<CrmkOrdrHd> getCrmkOrdrHdList() {
        return crmkOrdrHdList;
    }

    public void setCrmkOrdrHdList(List<CrmkOrdrHd> crmkOrdrHdList) {
        this.crmkOrdrHdList = crmkOrdrHdList;
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
        if (!(object instanceof CrmkBranch)) {
            return false;
        }
        CrmkBranch other = (CrmkBranch) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.CrmkBranch[id=" + id + "]";
    }

}
