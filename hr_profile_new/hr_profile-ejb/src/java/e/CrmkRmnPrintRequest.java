/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "CRMK_RMN_PRINT_REQUEST", catalog = "", schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "CrmkRmnPrintRequest.findAll", query = "SELECT c FROM CrmkRmnPrintRequest c"),
    @NamedQuery(name = "CrmkRmnPrintRequest.findById", query = "SELECT c FROM CrmkRmnPrintRequest c WHERE c.id = :id"),
    @NamedQuery(name = "CrmkRmnPrintRequest.findByRmnId", query = "SELECT c FROM CrmkRmnPrintRequest c WHERE c.rmnId = :rmnId"),
    @NamedQuery(name = "CrmkRmnPrintRequest.findByRmnNo", query = "SELECT c FROM CrmkRmnPrintRequest c WHERE c.rmnNo = :rmnNo"),
    @NamedQuery(name = "CrmkRmnPrintRequest.findByPrdId", query = "SELECT c FROM CrmkRmnPrintRequest c WHERE c.prdId = :prdId"),
    @NamedQuery(name = "CrmkRmnPrintRequest.findByClntName", query = "SELECT c FROM CrmkRmnPrintRequest c WHERE c.clntName = :clntName"),
    @NamedQuery(name = "CrmkRmnPrintRequest.findByRmnTrnsDate", query = "SELECT c FROM CrmkRmnPrintRequest c WHERE c.rmnTrnsDate = :rmnTrnsDate"),
    @NamedQuery(name = "CrmkRmnPrintRequest.findByEmpRequestedId", query = "SELECT c FROM CrmkRmnPrintRequest c WHERE c.empRequestedId = :empRequestedId"),
    @NamedQuery(name = "CrmkRmnPrintRequest.findByTargetBrnId", query = "SELECT c FROM CrmkRmnPrintRequest c WHERE c.targetBrnId = :targetBrnId"),
    @NamedQuery(name = "CrmkRmnPrintRequest.findByTrnsDate", query = "SELECT c FROM CrmkRmnPrintRequest c WHERE c.trnsDate = :trnsDate"),
    @NamedQuery(name = "CrmkRmnPrintRequest.findByPrinted", query = "SELECT c FROM CrmkRmnPrintRequest c WHERE c.printed = :printed"),
    @NamedQuery(name = "CrmkRmnPrintRequest.findByPrintDate", query = "SELECT c FROM CrmkRmnPrintRequest c WHERE c.printDate = :printDate"),
    @NamedQuery(name = "CrmkRmnPrintRequest.findByEmpPrintedId", query = "SELECT c FROM CrmkRmnPrintRequest c WHERE c.empPrintedId = :empPrintedId"),
    @NamedQuery(name = "CrmkRmnPrintRequest.findByCrmkSehy", query = "SELECT c FROM CrmkRmnPrintRequest c WHERE c.crmkSehy = :crmkSehy"),
    @NamedQuery(name = "CrmkRmnPrintRequest.findByRmnHandNo", query = "SELECT c FROM CrmkRmnPrintRequest c WHERE c.rmnHandNo = :rmnHandNo"),
    @NamedQuery(name = "CrmkRmnPrintRequest.findByClntPhone", query = "SELECT c FROM CrmkRmnPrintRequest c WHERE c.clntPhone = :clntPhone"),
    @NamedQuery(name = "CrmkRmnPrintRequest.findByDriverName", query = "SELECT c FROM CrmkRmnPrintRequest c WHERE c.driverName = :driverName"),
    @NamedQuery(name = "CrmkRmnPrintRequest.findByDriverPhone", query = "SELECT c FROM CrmkRmnPrintRequest c WHERE c.driverPhone = :driverPhone"),
    @NamedQuery(name = "CrmkRmnPrintRequest.findByDriverId", query = "SELECT c FROM CrmkRmnPrintRequest c WHERE c.driverId = :driverId"),
    @NamedQuery(name = "CrmkRmnPrintRequest.findByBrnRequestedId", query = "SELECT c FROM CrmkRmnPrintRequest c WHERE c.brnRequestedId = :brnRequestedId"),
    @NamedQuery(name = "CrmkRmnPrintRequest.findByEmpRequestedName", query = "SELECT c FROM CrmkRmnPrintRequest c WHERE c.empRequestedName = :empRequestedName"),
    @NamedQuery(name = "CrmkRmnPrintRequest.findByBrnRequestedName", query = "SELECT c FROM CrmkRmnPrintRequest c WHERE c.brnRequestedName = :brnRequestedName")})
public class CrmkRmnPrintRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="CRMK_RMN_PRINT_REQ", sequenceName="CRMK.CRMK_RMN_PRINT_REQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRMK_RMN_PRINT_REQ")
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "RMN_ID")
    private Long rmnId;
    @Column(name = "RMN_NO")
    private Long rmnNo;
    @Column(name = "PRD_ID")
    private Long prdId;
    @Column(name = "CLNT_NAME")
    private String clntName;
    @Column(name = "RMN_TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date rmnTrnsDate;
    @Column(name = "EMP_REQUESTED_ID")
    private Long empRequestedId;
    @Column(name = "TARGET_BRN_ID")
    private Long targetBrnId;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date trnsDate;
    @Column(name = "PRINTED")
    private Character printed;
    @Column(name = "PRINT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date printDate;
    @Column(name = "EMP_PRINTED_ID")
    private Long empPrintedId;
    @Column(name = "CRMK_SEHY")
    private Character crmkSehy;
    @Column(name = "RMN_HAND_NO")
    private Long rmnHandNo;
    @Column(name = "CLNT_PHONE")
    private String clntPhone;
    @Column(name = "DRIVER_NAME")
    private String driverName;
    @Column(name = "DRIVER_PHONE")
    private String driverPhone;
    @Column(name = "DRIVER_ID")
    private Long driverId;
    @Column(name = "BRN_REQUESTED_ID")
    private Long brnRequestedId;
    @Column(name = "EMP_REQUESTED_NAME")
    private String empRequestedName;
    @Column(name = "BRN_REQUESTED_NAME")
    private String brnRequestedName;
    @Column(name="TARGET_BRN_NAME")
    private String trgtBrnName;
    @Column(name="PRINT_CANCELED_BY")
    private Long printCanceledBy;
    @Column(name = "LOAD_DATE")
    @Temporal(TemporalType.DATE)
    private Date loadDate;
    public CrmkRmnPrintRequest() {
    }

   

    public String getClntName() {
        return clntName;
    }

    public void setClntName(String clntName) {
        this.clntName = clntName;
    }

    public Date getRmnTrnsDate() {
        return rmnTrnsDate;
    }

    public void setRmnTrnsDate(Date rmnTrnsDate) {
        this.rmnTrnsDate = rmnTrnsDate;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public Character getPrinted() {
        return printed;
    }

    public void setPrinted(Character printed) {
        this.printed = printed;
    }

    public Date getPrintDate() {
        return printDate;
    }

    public void setPrintDate(Date printDate) {
        this.printDate = printDate;
    }

    

    public Character getCrmkSehy() {
        return crmkSehy;
    }

    public void setCrmkSehy(Character crmkSehy) {
        this.crmkSehy = crmkSehy;
    }

   

    public String getClntPhone() {
        return clntPhone;
    }

    public void setClntPhone(String clntPhone) {
        this.clntPhone = clntPhone;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public Long getBrnRequestedId() {
        return brnRequestedId;
    }

    public void setBrnRequestedId(Long brnRequestedId) {
        this.brnRequestedId = brnRequestedId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getEmpPrintedId() {
        return empPrintedId;
    }

    public void setEmpPrintedId(Long empPrintedId) {
        this.empPrintedId = empPrintedId;
    }

    public Long getEmpRequestedId() {
        return empRequestedId;
    }

    public void setEmpRequestedId(Long empRequestedId) {
        this.empRequestedId = empRequestedId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrdId() {
        return prdId;
    }

    public void setPrdId(Long prdId) {
        this.prdId = prdId;
    }

    public Long getRmnHandNo() {
        return rmnHandNo;
    }

    public void setRmnHandNo(Long rmnHandNo) {
        this.rmnHandNo = rmnHandNo;
    }

    public Long getRmnId() {
        return rmnId;
    }

    public void setRmnId(Long rmnId) {
        this.rmnId = rmnId;
    }

    public Long getRmnNo() {
        return rmnNo;
    }

    public void setRmnNo(Long rmnNo) {
        this.rmnNo = rmnNo;
    }

    public Long getTargetBrnId() {
        return targetBrnId;
    }

    public void setTargetBrnId(Long targetBrnId) {
        this.targetBrnId = targetBrnId;
    }

   

    public String getEmpRequestedName() {
        return empRequestedName;
    }

    public void setEmpRequestedName(String empRequestedName) {
        this.empRequestedName = empRequestedName;
    }

    public String getBrnRequestedName() {
        return brnRequestedName;
    }

    public void setBrnRequestedName(String brnRequestedName) {
        this.brnRequestedName = brnRequestedName;
    }

    public String getTrgtBrnName() {
        return trgtBrnName;
    }

    public void setTrgtBrnName(String trgtBrnName) {
        this.trgtBrnName = trgtBrnName;
    }

    public Long getPrintCanceledBy() {
        return printCanceledBy;
    }

    public void setPrintCanceledBy(Long printCanceledBy) {
        this.printCanceledBy = printCanceledBy;
    }

    public Date getLoadDate() {
        return loadDate;
    }

    public void setLoadDate(Date loadDate) {
        this.loadDate = loadDate;
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
        if (!(object instanceof CrmkRmnPrintRequest)) {
            return false;
        }
        CrmkRmnPrintRequest other = (CrmkRmnPrintRequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }





    @Override
    public String toString() {
        return "e.CrmkRmnPrintRequest[id=" + id + "]";
    }

}
