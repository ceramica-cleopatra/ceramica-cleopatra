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
import javax.persistence.JoinColumn;
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
@Table(name = "CRMK_ORDR_HD", catalog = "", schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "CrmkOrdrHd.findAll", query = "SELECT c FROM CrmkOrdrHd c"),
    @NamedQuery(name = "CrmkOrdrHd.findForOrdrPercentage", query = "SELECT c FROM CrmkOrdrHd c where c.no=:no and c.crmkSehy=:crmk_sehy and c.crmkBranch.id=:brn_id "
    + " and (c.clntName = :clnt_name or :clnt_name is null) and (c.trnsDate=:ordrDate or :ordrDate is null)"),
    @NamedQuery(name = "CrmkOrdrHd.findAllClnt", query = "SELECT distinct c.clntName FROM CrmkOrdrHd c where c.crmkBranch.id=:brnId and (c.clntName like :clntName or :clntName is null)"),
    @NamedQuery(name = "CrmkOrdrHd.findClntCount", query = "SELECT count(distinct c.clntName) FROM CrmkOrdrHd c where c.crmkBranch.id=:brnId and (c.clntName like :clntName or :clntName is null)"),
    @NamedQuery(name = "CrmkOrdrHd.findById", query = "SELECT c FROM CrmkOrdrHd c WHERE c.id = :id"),
    @NamedQuery(name = "CrmkOrdrHd.findByTrnsDate", query = "SELECT c FROM CrmkOrdrHd c WHERE c.trnsDate = :trnsDate"),
    @NamedQuery(name = "CrmkOrdrHd.findByClntName", query = "SELECT c FROM CrmkOrdrHd c WHERE c.clntName = :clntName"),
    @NamedQuery(name = "CrmkOrdrHd.findByAddress", query = "SELECT c FROM CrmkOrdrHd c WHERE c.address = :address"),
    @NamedQuery(name = "CrmkOrdrHd.findByPhone1", query = "SELECT c FROM CrmkOrdrHd c WHERE c.phone1 = :phone1"),
    @NamedQuery(name = "CrmkOrdrHd.findByPhone2", query = "SELECT c FROM CrmkOrdrHd c WHERE c.phone2 = :phone2"),
    @NamedQuery(name = "CrmkOrdrHd.findByMobile", query = "SELECT c FROM CrmkOrdrHd c WHERE c.mobile = :mobile"),
    @NamedQuery(name = "CrmkOrdrHd.findByCrmkSehy", query = "SELECT c FROM CrmkOrdrHd c WHERE c.crmkSehy = :crmkSehy"),
    @NamedQuery(name = "CrmkOrdrHd.findByClosed", query = "SELECT c FROM CrmkOrdrHd c WHERE c.closed = :closed"),
    @NamedQuery(name = "CrmkOrdrHd.findByCanceled", query = "SELECT c FROM CrmkOrdrHd c WHERE c.canceled = :canceled"),
    @NamedQuery(name = "CrmkOrdrHd.findByCrtDate", query = "SELECT c FROM CrmkOrdrHd c WHERE c.crtDate = :crtDate"),
    @NamedQuery(name = "CrmkOrdrHd.findByEdtDate", query = "SELECT c FROM CrmkOrdrHd c WHERE c.edtDate = :edtDate"),
    @NamedQuery(name = "CrmkOrdrHd.findByClsDate", query = "SELECT c FROM CrmkOrdrHd c WHERE c.clsDate = :clsDate"),
    @NamedQuery(name = "CrmkOrdrHd.findByOpnDate", query = "SELECT c FROM CrmkOrdrHd c WHERE c.opnDate = :opnDate"),
    @NamedQuery(name = "CrmkOrdrHd.findByDelDate", query = "SELECT c FROM CrmkOrdrHd c WHERE c.delDate = :delDate"),
    @NamedQuery(name = "CrmkOrdrHd.findByUndelDate", query = "SELECT c FROM CrmkOrdrHd c WHERE c.undelDate = :undelDate"),
    @NamedQuery(name = "CrmkOrdrHd.findByNotes", query = "SELECT c FROM CrmkOrdrHd c WHERE c.notes = :notes"),
    @NamedQuery(name = "CrmkOrdrHd.findByHandNo", query = "SELECT c FROM CrmkOrdrHd c WHERE c.handNo = :handNo"),
    @NamedQuery(name = "CrmkOrdrHd.findByDscnt1", query = "SELECT c FROM CrmkOrdrHd c WHERE c.dscnt1 = :dscnt1"),
    @NamedQuery(name = "CrmkOrdrHd.findByDscnt2", query = "SELECT c FROM CrmkOrdrHd c WHERE c.dscnt2 = :dscnt2"),
    @NamedQuery(name = "CrmkOrdrHd.findByDscnt3", query = "SELECT c FROM CrmkOrdrHd c WHERE c.dscnt3 = :dscnt3"),
    @NamedQuery(name = "CrmkOrdrHd.findByDscnt4", query = "SELECT c FROM CrmkOrdrHd c WHERE c.dscnt4 = :dscnt4"),
    @NamedQuery(name = "CrmkOrdrHd.findByDscnt5", query = "SELECT c FROM CrmkOrdrHd c WHERE c.dscnt5 = :dscnt5"),
    @NamedQuery(name = "CrmkOrdrHd.findByOver", query = "SELECT c FROM CrmkOrdrHd c WHERE c.over = :over"),
    @NamedQuery(name = "CrmkOrdrHd.findByReserve", query = "SELECT c FROM CrmkOrdrHd c WHERE c.reserve = :reserve"),
    @NamedQuery(name = "CrmkOrdrHd.findByPayed", query = "SELECT c FROM CrmkOrdrHd c WHERE c.payed = :payed"),
    @NamedQuery(name = "CrmkOrdrHd.findByExtraInUp", query = "SELECT c FROM CrmkOrdrHd c WHERE c.extraInUp = :extraInUp"),
    @NamedQuery(name = "CrmkOrdrHd.findByTax1", query = "SELECT c FROM CrmkOrdrHd c WHERE c.tax1 = :tax1"),
    @NamedQuery(name = "CrmkOrdrHd.findByTax3", query = "SELECT c FROM CrmkOrdrHd c WHERE c.tax3 = :tax3"),
    @NamedQuery(name = "CrmkOrdrHd.findByCrncyId", query = "SELECT c FROM CrmkOrdrHd c WHERE c.crncyId = :crncyId"),
    @NamedQuery(name = "CrmkOrdrHd.findByRate", query = "SELECT c FROM CrmkOrdrHd c WHERE c.rate = :rate"),
    @NamedQuery(name = "CrmkOrdrHd.findByYmya", query = "SELECT c FROM CrmkOrdrHd c WHERE c.ymya = :ymya"),
    @NamedQuery(name = "CrmkOrdrHd.findByCanceledYmya", query = "SELECT c FROM CrmkOrdrHd c WHERE c.canceledYmya = :canceledYmya"),
    @NamedQuery(name = "CrmkOrdrHd.findByFinished", query = "SELECT c FROM CrmkOrdrHd c WHERE c.finished = :finished"),
    @NamedQuery(name = "CrmkOrdrHd.findByNo", query = "SELECT c FROM CrmkOrdrHd c WHERE c.no = :no"),
    @NamedQuery(name = "CrmkOrdrHd.findByPrcDiff", query = "SELECT c FROM CrmkOrdrHd c WHERE c.prcDiff = :prcDiff"),
    @NamedQuery(name = "CrmkOrdrHd.findByShortage", query = "SELECT c FROM CrmkOrdrHd c WHERE c.shortage = :shortage"),
    @NamedQuery(name = "CrmkOrdrHd.findByPrevOrdr", query = "SELECT c FROM CrmkOrdrHd c WHERE c.prevOrdr = :prevOrdr"),
    @NamedQuery(name = "CrmkOrdrHd.findByPrevDate", query = "SELECT c FROM CrmkOrdrHd c WHERE c.prevDate = :prevDate"),
    @NamedQuery(name = "CrmkOrdrHd.findByPrevUser", query = "SELECT c FROM CrmkOrdrHd c WHERE c.prevUser = :prevUser"),
    @NamedQuery(name = "CrmkOrdrHd.findByMaxDiscount", query = "SELECT c FROM CrmkOrdrHd c WHERE c.maxDiscount = :maxDiscount"),
    @NamedQuery(name = "CrmkOrdrHd.findByApprove", query = "SELECT c FROM CrmkOrdrHd c WHERE c.approve = :approve"),
    @NamedQuery(name = "CrmkOrdrHd.findByVoucherPrint", query = "SELECT c FROM CrmkOrdrHd c WHERE c.voucherPrint = :voucherPrint")})
public class CrmkOrdrHd implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @Column(name = "CLNT_NAME")
    private String clntName;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "PHONE1")
    private String phone1;
    @Column(name = "PHONE2")
    private String phone2;
    @Column(name = "MOBILE")
    private String mobile;
    @Basic(optional = false)
    @Column(name = "CRMK_SEHY")
    private char crmkSehy;
    @Column(name = "PRD_ID")
    private Long prdId;
    @Column(name = "CLOSED")
    private Character closed;
    @Column(name = "CANCELED")
    private Character canceled;
    @Basic(optional = false)
    @Column(name = "CRT_DATE")
    @Temporal(TemporalType.DATE)
    private Date crtDate;
    @Column(name = "EDT_DATE")
    @Temporal(TemporalType.DATE)
    private Date edtDate;
    @Column(name = "CLS_DATE")
    @Temporal(TemporalType.DATE)
    private Date clsDate;
    @Column(name = "OPN_DATE")
    @Temporal(TemporalType.DATE)
    private Date opnDate;
    @Column(name = "DEL_DATE")
    @Temporal(TemporalType.DATE)
    private Date delDate;
    @Column(name = "UNDEL_DATE")
    @Temporal(TemporalType.DATE)
    private Date undelDate;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "HAND_NO")
    private String handNo;
    @Column(name = "DSCNT1")
    private BigDecimal dscnt1;
    @Column(name = "DSCNT2")
    private BigDecimal dscnt2;
    @Column(name = "DSCNT3")
    private BigDecimal dscnt3;
    @Column(name = "DSCNT4")
    private BigDecimal dscnt4;
    @Column(name = "DSCNT5")
    private BigDecimal dscnt5;
    @Column(name = "OVER")
    private BigDecimal over;
    @Column(name = "RESERVE")
    private Character reserve;
    @Basic(optional = false)
    @Column(name = "PAYED")
    private char payed;
    @Column(name = "EXTRA_IN_UP")
    private String extraInUp;
    @Column(name = "TAX1")
    private BigDecimal tax1;
    @Column(name = "TAX3")
    private BigDecimal tax3;
    @Column(name = "CRNCY_ID")
    private Long crncyId;
    @Column(name = "RATE")
    private Long rate;
    @Column(name = "YMYA")
    private Long ymya;
    @Column(name = "CANCELED_YMYA")
    private Long canceledYmya;
    @Column(name = "FINISHED")
    private Character finished;
    @Column(name = "NO")
    private BigInteger no;
    @Column(name = "PRC_DIFF")
    private BigDecimal prcDiff;
    @Column(name = "SHORTAGE")
    private BigDecimal shortage;
    @Column(name = "PREV_ORDR")
    private Short prevOrdr;
    @Column(name = "PREV_DATE")
    @Temporal(TemporalType.DATE)
    private Date prevDate;
    @Column(name = "PREV_USER")
    private Long prevUser;
    @Column(name = "MAX_DISCOUNT")
    private BigInteger maxDiscount;
    @Column(name = "APPROVE")
    private String approve;
    @Column(name = "VOUCHER_PRINT")
    private String voucherPrint;
    @JoinColumn(name = "BRN_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CrmkBranch crmkBranch;
    @OneToMany(mappedBy="ordrId")
    List<CrmkOrdrSader> crmkOrdrSaderList;
    @OneToMany(mappedBy="crmkOrdrHd")
    List<CrmkCOrdrDt> crmkCOrdrDtList;
    @OneToMany(mappedBy="crmkOrdrHd")
    List<CrmkDOrdrDt> crmkDOrdrDtList;
    @OneToMany(mappedBy="crmkOrdrHd")
    List<CrmkSOrdrDt> crmkSOrdrDtList;
    @OneToMany(mappedBy="crmkOrdrHd")
    List<CrmkCOrdrEmp> crmkCOrdrEmps;
    @OneToMany(mappedBy="crmkOrdrHd")
    List<CrmkDOrdrEmp> crmkDOrdrEmps;
    @OneToMany(mappedBy="crmkOrdrHd")
    List<CrmkSOrdrEmp> crmkSOrdrEmps;

    public Long getPrdId() {
        return prdId;
    }

    public void setPrdId(Long prdId) {
        this.prdId = prdId;
    }

    public List<CrmkCOrdrDt> getCrmkCOrdrDtList() {
        return crmkCOrdrDtList;
    }

    public void setCrmkCOrdrDtList(List<CrmkCOrdrDt> crmkCOrdrDtList) {
        this.crmkCOrdrDtList = crmkCOrdrDtList;
    }

    public List<CrmkDOrdrDt> getCrmkDOrdrDtList() {
        return crmkDOrdrDtList;
    }

    public void setCrmkDOrdrDtList(List<CrmkDOrdrDt> crmkDOrdrDtList) {
        this.crmkDOrdrDtList = crmkDOrdrDtList;
    }

    public List<CrmkSOrdrDt> getCrmkSOrdrDtList() {
        return crmkSOrdrDtList;
    }

    public void setCrmkSOrdrDtList(List<CrmkSOrdrDt> crmkSOrdrDtList) {
        this.crmkSOrdrDtList = crmkSOrdrDtList;
    }

    
    public CrmkOrdrHd() {
    }

    public CrmkOrdrHd(Long id) {
        this.id = id;
    }

    public List<CrmkOrdrSader> getCrmkOrdrSaderList() {
        return crmkOrdrSaderList;
    }

    public void setCrmkOrdrSaderList(List<CrmkOrdrSader> crmkOrdrSaderList) {
        this.crmkOrdrSaderList = crmkOrdrSaderList;
    }

    public CrmkOrdrHd(Long id, char crmkSehy, Date crtDate, char payed) {
        this.id = id;
        this.crmkSehy = crmkSehy;
        this.crtDate = crtDate;
        this.payed = payed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public String getClntName() {
        return clntName;
    }

    public void setClntName(String clntName) {
        this.clntName = clntName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public char getCrmkSehy() {
        return crmkSehy;
    }

    public void setCrmkSehy(char crmkSehy) {
        this.crmkSehy = crmkSehy;
    }

    public Character getClosed() {
        return closed;
    }

    public void setClosed(Character closed) {
        this.closed = closed;
    }

    public Character getCanceled() {
        return canceled;
    }

    public void setCanceled(Character canceled) {
        this.canceled = canceled;
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    public Date getEdtDate() {
        return edtDate;
    }

    public void setEdtDate(Date edtDate) {
        this.edtDate = edtDate;
    }

    public Date getClsDate() {
        return clsDate;
    }

    public void setClsDate(Date clsDate) {
        this.clsDate = clsDate;
    }

    public Date getOpnDate() {
        return opnDate;
    }

    public void setOpnDate(Date opnDate) {
        this.opnDate = opnDate;
    }

    public Date getDelDate() {
        return delDate;
    }

    public void setDelDate(Date delDate) {
        this.delDate = delDate;
    }

    public Date getUndelDate() {
        return undelDate;
    }

    public void setUndelDate(Date undelDate) {
        this.undelDate = undelDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getHandNo() {
        return handNo;
    }

    public void setHandNo(String handNo) {
        this.handNo = handNo;
    }

    public BigDecimal getDscnt1() {
        return dscnt1;
    }

    public void setDscnt1(BigDecimal dscnt1) {
        this.dscnt1 = dscnt1;
    }

    public BigDecimal getDscnt2() {
        return dscnt2;
    }

    public void setDscnt2(BigDecimal dscnt2) {
        this.dscnt2 = dscnt2;
    }

    public BigDecimal getDscnt3() {
        return dscnt3;
    }

    public void setDscnt3(BigDecimal dscnt3) {
        this.dscnt3 = dscnt3;
    }

    public BigDecimal getDscnt4() {
        return dscnt4;
    }

    public void setDscnt4(BigDecimal dscnt4) {
        this.dscnt4 = dscnt4;
    }

    public BigDecimal getDscnt5() {
        return dscnt5;
    }

    public void setDscnt5(BigDecimal dscnt5) {
        this.dscnt5 = dscnt5;
    }

    public BigDecimal getOver() {
        return over;
    }

    public void setOver(BigDecimal over) {
        this.over = over;
    }

    public Character getReserve() {
        return reserve;
    }

    public void setReserve(Character reserve) {
        this.reserve = reserve;
    }

    public char getPayed() {
        return payed;
    }

    public void setPayed(char payed) {
        this.payed = payed;
    }

    public String getExtraInUp() {
        return extraInUp;
    }

    public void setExtraInUp(String extraInUp) {
        this.extraInUp = extraInUp;
    }

    public BigDecimal getTax1() {
        return tax1;
    }

    public void setTax1(BigDecimal tax1) {
        this.tax1 = tax1;
    }

    public BigDecimal getTax3() {
        return tax3;
    }

    public void setTax3(BigDecimal tax3) {
        this.tax3 = tax3;
    }

    public Long getCrncyId() {
        return crncyId;
    }

    public void setCrncyId(Long crncyId) {
        this.crncyId = crncyId;
    }

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }

    public Long getYmya() {
        return ymya;
    }

    public void setYmya(Long ymya) {
        this.ymya = ymya;
    }

    public Long getCanceledYmya() {
        return canceledYmya;
    }

    public void setCanceledYmya(Long canceledYmya) {
        this.canceledYmya = canceledYmya;
    }

    public Character getFinished() {
        return finished;
    }

    public void setFinished(Character finished) {
        this.finished = finished;
    }

    public BigInteger getNo() {
        return no;
    }

    public void setNo(BigInteger no) {
        this.no = no;
    }

    public BigDecimal getPrcDiff() {
        return prcDiff;
    }

    public void setPrcDiff(BigDecimal prcDiff) {
        this.prcDiff = prcDiff;
    }

    public BigDecimal getShortage() {
        return shortage;
    }

    public void setShortage(BigDecimal shortage) {
        this.shortage = shortage;
    }

    public Short getPrevOrdr() {
        return prevOrdr;
    }

    public void setPrevOrdr(Short prevOrdr) {
        this.prevOrdr = prevOrdr;
    }

    public Date getPrevDate() {
        return prevDate;
    }

    public void setPrevDate(Date prevDate) {
        this.prevDate = prevDate;
    }

    public Long getPrevUser() {
        return prevUser;
    }

    public void setPrevUser(Long prevUser) {
        this.prevUser = prevUser;
    }

    public BigInteger getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(BigInteger maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public String getApprove() {
        return approve;
    }

    public void setApprove(String approve) {
        this.approve = approve;
    }

    public String getVoucherPrint() {
        return voucherPrint;
    }

    public void setVoucherPrint(String voucherPrint) {
        this.voucherPrint = voucherPrint;
    }

    public CrmkBranch getCrmkBranch() {
        return crmkBranch;
    }

    public void setCrmkBranch(CrmkBranch crmkBranch) {
        this.crmkBranch = crmkBranch;
    }

    public List<CrmkCOrdrEmp> getCrmkCOrdrEmps() {
        return crmkCOrdrEmps;
    }

    public void setCrmkCOrdrEmps(List<CrmkCOrdrEmp> crmkCOrdrEmps) {
        this.crmkCOrdrEmps = crmkCOrdrEmps;
    }

    public List<CrmkDOrdrEmp> getCrmkDOrdrEmps() {
        return crmkDOrdrEmps;
    }

    public void setCrmkDOrdrEmps(List<CrmkDOrdrEmp> crmkDOrdrEmps) {
        this.crmkDOrdrEmps = crmkDOrdrEmps;
    }

    public List<CrmkSOrdrEmp> getCrmkSOrdrEmps() {
        return crmkSOrdrEmps;
    }

    public void setCrmkSOrdrEmps(List<CrmkSOrdrEmp> crmkSOrdrEmps) {
        this.crmkSOrdrEmps = crmkSOrdrEmps;
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
        if (!(object instanceof CrmkOrdrHd)) {
            return false;
        }
        CrmkOrdrHd other = (CrmkOrdrHd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.CrmkOrdrHd[id=" + id + "]";
    }

}
