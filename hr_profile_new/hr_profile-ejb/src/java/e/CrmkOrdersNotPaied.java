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
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author ahmed abbas
 */

@Entity
@Table(name = "CRMK_ORDERS_NOT_PAIED", catalog = "", schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "CrmkOrdersNotPaied.findAll", query = "SELECT c FROM CrmkOrdersNotPaied c where c.ordrDate between :from_date and :to_date and (c.hrId.empNo=:emp or :emp is null) and (c.hrBrnId=:brn or :brn is null) order by c.no,c.clntName"),
    @NamedQuery(name = "CrmkOrdersNotPaied.findById", query = "SELECT c FROM CrmkOrdersNotPaied c WHERE c.id = :id"),
    @NamedQuery(name = "CrmkOrdersNotPaied.findByOrdrId", query = "SELECT c FROM CrmkOrdersNotPaied c WHERE c.ordrId = :ordrId"),
    @NamedQuery(name = "CrmkOrdersNotPaied.findAllByBrnId", query = "SELECT c FROM CrmkOrdersNotPaied c WHERE (c.brnId = :brnId or :brnId is null) and c.ordrDate between :from_date and :to_date order by c.no,c.clntName"),
    @NamedQuery(name = "CrmkOrdersNotPaied.findByCrmkDcre", query = "SELECT c FROM CrmkOrdersNotPaied c WHERE c.crmkDcre = :crmkDcre"),
    @NamedQuery(name = "CrmkOrdersNotPaied.findByNo", query = "SELECT c FROM CrmkOrdersNotPaied c WHERE c.no = :no"),
    @NamedQuery(name = "CrmkOrdersNotPaied.findByHandNo", query = "SELECT c FROM CrmkOrdersNotPaied c WHERE c.handNo = :handNo"),
    @NamedQuery(name = "CrmkOrdersNotPaied.findByClntName", query = "SELECT c FROM CrmkOrdersNotPaied c WHERE c.clntName = :clntName"),
    @NamedQuery(name = "CrmkOrdersNotPaied.findByClintPhone", query = "SELECT c FROM CrmkOrdersNotPaied c WHERE c.clintPhone = :clintPhone"),
    @NamedQuery(name = "CrmkOrdersNotPaied.findByClintGrp", query = "SELECT c FROM CrmkOrdersNotPaied c WHERE c.clintGrp = :clintGrp"),
    @NamedQuery(name = "CrmkOrdersNotPaied.findByOrdrDate", query = "SELECT c FROM CrmkOrdersNotPaied c WHERE c.ordrDate = :ordrDate"),
    @NamedQuery(name = "CrmkOrdersNotPaied.findByYomyaNo", query = "SELECT c FROM CrmkOrdersNotPaied c WHERE c.yomyaNo = :yomyaNo"),
    @NamedQuery(name = "CrmkOrdersNotPaied.findByTotal", query = "SELECT c FROM CrmkOrdersNotPaied c WHERE c.total = :total"),
    @NamedQuery(name = "CrmkOrdersNotPaied.findByRmn", query = "SELECT c FROM CrmkOrdersNotPaied c WHERE c.rmn = :rmn"),
    @NamedQuery(name = "CrmkOrdersNotPaied.findByPayed", query = "SELECT c FROM CrmkOrdersNotPaied c WHERE c.payed = :payed"),
    @NamedQuery(name = "CrmkOrdersNotPaied.findByHdId", query = "SELECT c FROM CrmkOrdersNotPaied c WHERE c.hdId = :hdId"),
    @NamedQuery(name = "CrmkOrdersNotPaied.findByEmpId", query = "SELECT c FROM CrmkOrdersNotPaied c WHERE c.empId = :empId"),
    @NamedQuery(name = "CrmkOrdersNotPaied.findByPercent", query = "SELECT c FROM CrmkOrdersNotPaied c WHERE c.percent = :percent"),
    @NamedQuery(name = "CrmkOrdersNotPaied.findByHrId", query = "SELECT c FROM CrmkOrdersNotPaied c WHERE c.hrId = :hrId"),
    @NamedQuery(name = "CrmkOrdersNotPaied.findByBranchName", query = "SELECT c FROM CrmkOrdersNotPaied c WHERE c.branchName = :branchName")})
public class CrmkOrdersNotPaied implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "ORDR_ID")
    private Long ordrId;
    @Basic(optional = false)
    @Column(name = "BRN_ID")
    private long brnId;
    @Basic(optional = false)
    @Column(name = "CRMK_DCRE")
    private String crmkDcre;
    @Column(name = "NO")
    private Long no;
    @Column(name = "HAND_NO")
    private Long handNo;
    @Column(name = "CLNT_NAME")
    private String clntName;
    @Column(name = "CLINT_PHONE")
    private String clintPhone;
    @Column(name = "CLINT_GRP")
    private Long clintGrp;
    @Column(name = "ORDR_DATE")
    @Temporal(TemporalType.DATE)
    private Date ordrDate;
    @Column(name = "YOMYA_NO")
    private Long yomyaNo;
    @Column(name = "TOTAL")
    private Long total;
    @Column(name = "RMN")
    private Long rmn;
    @Column(name = "PAYED")
    private Long payed;
    @Column(name = "HD_ID")
    private Long hdId;
    @Column(name = "EMP_ID")
    private Long empId;
    @Column(name = "PERCENT")
    private Long percent;
    @ManyToOne
    @JoinColumn(name = "HR_ID")
    private HrEmpInfo hrId;
    @Column(name = "BRANCH_NAME")
    private String branchName;
    @Basic(optional = false)
    @Column(name = "ID_1")
    private long hrBrnId;

    public CrmkOrdersNotPaied() {
    }

  

    public Long getOrdrId() {
        return ordrId;
    }

    public void setOrdrId(Long ordrId) {
        this.ordrId = ordrId;
    }

    public long getBrnId() {
        return brnId;
    }

    public void setBrnId(long brnId) {
        this.brnId = brnId;
    }

   

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public Long getHandNo() {
        return handNo;
    }

    public void setHandNo(Long handNo) {
        this.handNo = handNo;
    }

    public String getClntName() {
        return clntName;
    }

    public void setClntName(String clntName) {
        this.clntName = clntName;
    }

    public String getClintPhone() {
        return clintPhone;
    }

    public void setClintPhone(String clintPhone) {
        this.clintPhone = clintPhone;
    }

    public Long getClintGrp() {
        return clintGrp;
    }

    public void setClintGrp(Long clintGrp) {
        this.clintGrp = clintGrp;
    }

    public Date getOrdrDate() {
        return ordrDate;
    }

    public void setOrdrDate(Date ordrDate) {
        this.ordrDate = ordrDate;
    }

    public Long getYomyaNo() {
        return yomyaNo;
    }

    public void setYomyaNo(Long yomyaNo) {
        this.yomyaNo = yomyaNo;
    }

  

    public Long getHdId() {
        return hdId;
    }

    public void setHdId(Long hdId) {
        this.hdId = hdId;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public HrEmpInfo getHrId() {
        return hrId;
    }

    public void setHrId(HrEmpInfo hrId) {
        this.hrId = hrId;
    }

  
   

    public String getCrmkDcre() {
        return crmkDcre;
    }

    public void setCrmkDcre(String crmkDcre) {
        this.crmkDcre = crmkDcre;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPayed() {
        return payed;
    }

    public void setPayed(Long payed) {
        this.payed = payed;
    }

    public Long getPercent() {
        return percent;
    }

    public void setPercent(Long percent) {
        this.percent = percent;
    }

    public Long getRmn() {
        return rmn;
    }

    public void setRmn(Long rmn) {
        this.rmn = rmn;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

   

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public long getHrBrnId() {
        return hrBrnId;
    }

    public void setHrBrnId(long hrBrnId) {
        this.hrBrnId = hrBrnId;
    }



   

}
