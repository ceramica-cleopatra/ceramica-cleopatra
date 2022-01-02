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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author data
 */
@Entity
@Table(name = "HR_MOSQUE_CRMK_REQ", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrMosqueCrmkReq.findAll", query = "SELECT h FROM HrMosqueCrmkReq h order by h.print"),
    @NamedQuery(name = "HrMosqueCrmkReq.findMaxSerial", query = "SELECT max(h.serialNo) FROM HrMosqueCrmkReq h"),
    @NamedQuery(name = "HrMosqueCrmkReq.findById", query = "SELECT h FROM HrMosqueCrmkReq h WHERE h.id = :id"),
    @NamedQuery(name = "HrMosqueCrmkReq.findByEmpNo", query = "SELECT h FROM HrMosqueCrmkReq h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrMosqueCrmkReq.findByTrnsDate", query = "SELECT h FROM HrMosqueCrmkReq h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrMosqueCrmkReq.findByMosque", query = "SELECT h FROM HrMosqueCrmkReq h WHERE h.mosque = :mosque"),
    @NamedQuery(name = "HrMosqueCrmkReq.findByVillage", query = "SELECT h FROM HrMosqueCrmkReq h WHERE h.village = :village"),
    @NamedQuery(name = "HrMosqueCrmkReq.findByCity", query = "SELECT h FROM HrMosqueCrmkReq h WHERE h.city = :city"),
    @NamedQuery(name = "HrMosqueCrmkReq.findByGovern", query = "SELECT h FROM HrMosqueCrmkReq h WHERE h.govern = :govern"),
    @NamedQuery(name = "HrMosqueCrmkReq.findByAddress", query = "SELECT h FROM HrMosqueCrmkReq h WHERE h.address = :address"),
    @NamedQuery(name = "HrMosqueCrmkReq.findByViewDate", query = "SELECT h FROM HrMosqueCrmkReq h WHERE h.viewDate = :viewDate"),
    @NamedQuery(name = "HrMosqueCrmkReq.findByPhoneNo", query = "SELECT h FROM HrMosqueCrmkReq h WHERE h.phoneNo = :phoneNo"),
    @NamedQuery(name = "HrMosqueCrmkReq.findByWallL1", query = "SELECT h FROM HrMosqueCrmkReq h WHERE h.wallL1 = :wallL1"),
    @NamedQuery(name = "HrMosqueCrmkReq.findByWallW1", query = "SELECT h FROM HrMosqueCrmkReq h WHERE h.wallW1 = :wallW1"),
    @NamedQuery(name = "HrMosqueCrmkReq.findByWallL2", query = "SELECT h FROM HrMosqueCrmkReq h WHERE h.wallL2 = :wallL2"),
    @NamedQuery(name = "HrMosqueCrmkReq.findByWallW2", query = "SELECT h FROM HrMosqueCrmkReq h WHERE h.wallW2 = :wallW2"),
    @NamedQuery(name = "HrMosqueCrmkReq.findByWallBathNo", query = "SELECT h FROM HrMosqueCrmkReq h WHERE h.wallBathNo = :wallBathNo"),
    @NamedQuery(name = "HrMosqueCrmkReq.findByMirrorW", query = "SELECT h FROM HrMosqueCrmkReq h WHERE h.mirrorW = :mirrorW"),
    @NamedQuery(name = "HrMosqueCrmkReq.findByMirrorNo", query = "SELECT h FROM HrMosqueCrmkReq h WHERE h.mirrorNo = :mirrorNo"),
    @NamedQuery(name = "HrMosqueCrmkReq.findByWallStreamArea", query = "SELECT h FROM HrMosqueCrmkReq h WHERE h.wallStreamArea = :wallStreamArea"),
    @NamedQuery(name = "HrMosqueCrmkReq.findByFloorL", query = "SELECT h FROM HrMosqueCrmkReq h WHERE h.floorL = :floorL"),
    @NamedQuery(name = "HrMosqueCrmkReq.findByFloorW", query = "SELECT h FROM HrMosqueCrmkReq h WHERE h.floorW = :floorW"),
    @NamedQuery(name = "HrMosqueCrmkReq.findByFloorBathNo", query = "SELECT h FROM HrMosqueCrmkReq h WHERE h.floorBathNo = :floorBathNo"),
    @NamedQuery(name = "HrMosqueCrmkReq.findByFloorSittingArea", query = "SELECT h FROM HrMosqueCrmkReq h WHERE h.floorSittingL = :floorSittingL"),
    @NamedQuery(name = "HrMosqueCrmkReq.findByTotalWall", query = "SELECT h FROM HrMosqueCrmkReq h WHERE h.totalWall = :totalWall"),
    @NamedQuery(name = "HrMosqueCrmkReq.findByTotalFloor", query = "SELECT h FROM HrMosqueCrmkReq h WHERE h.totalFloor = :totalFloor")})
public class HrMosqueCrmkReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="HR_MOSQUE_CRMK_REQ_SEQ", sequenceName="HR_MOSQUE_CRMK_REQ_SEQ", initialValue=1, allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HR_MOSQUE_CRMK_REQ_SEQ")
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "EMP_NO")
    private HrEmpInfo empNo;
    @ManyToOne
    @JoinColumn(name = "EMP_NO_2")
    private HrEmpInfo empNo2;
    @ManyToOne
    @JoinColumn(name = "EMP_NO_3")
    private HrEmpInfo empNo3;
    @ManyToOne
    @JoinColumn(name = "EMP_NO_4")
    private HrEmpInfo empNo4;
    @ManyToOne
    @JoinColumn(name = "EMP_NO_5")
    private HrEmpInfo empNo5;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @Column(name = "MOSQUE")
    private String mosque;
    @Column(name = "VILLAGE")
    private String village;
    @Column(name = "CITY")
    private String city;
    @Column(name = "GOVERN")
    private String govern;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "VIEW_DATE")
    @Temporal(TemporalType.DATE)
    private Date viewDate;
    @Column(name = "PHONE_NO")
    private String phoneNo;
    @Column(name = "WALL_L_1")
    private Double wallL1;
    @Column(name = "WALL_W_1")
    private Double wallW1;
    @Column(name = "WALL_L_2")
    private Double wallL2;
    @Column(name = "WALL_W_2")
    private Double wallW2;
    @Column(name = "WALL_BATH_NO")
    private Long wallBathNo;
    @Column(name = "MIRROR_W")
    private Double mirrorW;
    @Column(name = "MIRROR_NO")
    private Long mirrorNo;
    @Column(name = "WALL_STREAM_AREA")
    private Double wallStreamArea;
    @Column(name = "FLOOR_L")
    private Double floorL;
    @Column(name = "FLOOR_W")
    private Double floorW;
    @Column(name = "FLOOR_BATH_NO")
    private Long floorBathNo;
    @Column(name = "FLOOR_SITTING_L")
    private Double floorSittingL;
    @Column(name = "FLOOR_SITTING_W")
    private Double floorSittingW;
    @Column(name = "TOTAL_WALL")
    private Double totalWall;
    @Column(name = "TOTAL_FLOOR")
    private Double totalFloor;
    @Column(name = "PRINT")
    private String print;
    @Column(name = "PRINT_DATE")
    @Temporal(TemporalType.DATE)
    private Date printDate;
    @Column(name = "EMP_NAME_1")
    private String empName1;
    @Column(name = "EMP_NAME_2")
    private String empName2;
    @Column(name = "EMP_NAME_3")
    private String empName3;
    @Column(name = "EMP_NAME_4")
    private String empName4;
    @Column(name = "EMP_NAME_5")
    private String empName5;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "SERIAL_NO")
    private Long serialNo;
    public HrMosqueCrmkReq() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public HrEmpInfo getEmpNo() {
        return empNo;
    }

    public void setEmpNo(HrEmpInfo empNo) {
        this.empNo = empNo;
        if(empNo!=null && empNo.getEmpName()!=null)
            this.empName1 = empNo.getEmpName();
    }

    public Long getFloorBathNo() {
        return floorBathNo;
    }

    public void setFloorBathNo(Long floorBathNo) {
        this.floorBathNo = floorBathNo;
    }

    public Double getFloorL() {
        return floorL;
    }

    public void setFloorL(Double floorL) {
        this.floorL = floorL;
    }

    public Double getFloorSittingL() {
        return floorSittingL;
    }

    public void setFloorSittingL(Double floorSittingL) {
        this.floorSittingL = floorSittingL;
    }

    public Double getFloorSittingW() {
        return floorSittingW;
    }

    public void setFloorSittingW(Double floorSittingW) {
        this.floorSittingW = floorSittingW;
    }

    


    public Double getFloorW() {
        return floorW;
    }

    public void setFloorW(Double floorW) {
        this.floorW = floorW;
    }

    public String getGovern() {
        return govern;
    }

    public void setGovern(String govern) {
        this.govern = govern;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMirrorNo() {
        return mirrorNo;
    }

    public void setMirrorNo(Long mirrorNo) {
        this.mirrorNo = mirrorNo;
    }

    public Double getMirrorW() {
        return mirrorW;
    }

    public void setMirrorW(Double mirrorW) {
        this.mirrorW = mirrorW;
    }

    public String getMosque() {
        return mosque;
    }

    public void setMosque(String mosque) {
        this.mosque = mosque;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPrint() {
        return print;
    }

    public void setPrint(String print) {
        this.print = print;
    }

    public Date getPrintDate() {
        return printDate;
    }

    public void setPrintDate(Date printDate) {
        this.printDate = printDate;
    }

    public Double getTotalFloor() {
        return totalFloor;
    }

    public void setTotalFloor(Double totalFloor) {
        this.totalFloor = totalFloor;
    }

    public Double getTotalWall() {
        return totalWall;
    }

    public void setTotalWall(Double totalWall) {
        this.totalWall = totalWall;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public Date getViewDate() {
        return viewDate;
    }

    public void setViewDate(Date viewDate) {
        this.viewDate = viewDate;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public Long getWallBathNo() {
        return wallBathNo;
    }

    public void setWallBathNo(Long wallBathNo) {
        this.wallBathNo = wallBathNo;
    }

    public Double getWallL1() {
        return wallL1;
    }

    public void setWallL1(Double wallL1) {
        this.wallL1 = wallL1;
    }

    public Double getWallL2() {
        return wallL2;
    }

    public void setWallL2(Double wallL2) {
        this.wallL2 = wallL2;
    }

    public Double getWallStreamArea() {
        return wallStreamArea;
    }

    public void setWallStreamArea(Double wallStreamArea) {
        this.wallStreamArea = wallStreamArea;
    }

    public Double getWallW1() {
        return wallW1;
    }

    public void setWallW1(Double wallW1) {
        this.wallW1 = wallW1;
    }

    public Double getWallW2() {
        return wallW2;
    }

    public void setWallW2(Double wallW2) {
        this.wallW2 = wallW2;
    }

    public HrEmpInfo getEmpNo2() {
        return empNo2;
    }

    public void setEmpNo2(HrEmpInfo empNo2) {
        this.empNo2 = empNo2;
        if(empNo2!=null && empNo2.getEmpName()!=null)
            this.empName2 = empNo2.getEmpName();
    }

    public HrEmpInfo getEmpNo3() {
        return empNo3;
    }

    public void setEmpNo3(HrEmpInfo empNo3) {
        this.empNo3 = empNo3;
        if(empNo3!=null && empNo3.getEmpName()!=null)
            this.empName3 = empNo3.getEmpName();
    }

    public HrEmpInfo getEmpNo4() {
        return empNo4;
    }

    public void setEmpNo4(HrEmpInfo empNo4) {
        this.empNo4 = empNo4;
        if(empNo4!=null && empNo4.getEmpName()!=null)
            this.empName4 = empNo4.getEmpName();
    }

    public HrEmpInfo getEmpNo5() {
        return empNo5;
    }

    public void setEmpNo5(HrEmpInfo empNo5) {
        this.empNo5 = empNo5;
        if(empNo5!=null && empNo5.getEmpName()!=null)
            this.empName5 = empNo5.getEmpName();
    }

    public String getEmpName1() {
        return empName1;
    }

    public void setEmpName1(String empName1) {
        this.empName1 = empName1;
    }

    public String getEmpName2() {
        return empName2;
    }

    public void setEmpName2(String empName2) {
        this.empName2 = empName2;
    }

    public String getEmpName3() {
        return empName3;
    }

    public void setEmpName3(String empName3) {
        this.empName3 = empName3;
    }

    public String getEmpName4() {
        return empName4;
    }

    public void setEmpName4(String empName4) {
        this.empName4 = empName4;
    }

    public String getEmpName5() {
        return empName5;
    }

    public void setEmpName5(String empName5) {
        this.empName5 = empName5;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Long serialNo) {
        this.serialNo = serialNo;
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
        if (!(object instanceof HrMosqueCrmkReq)) {
            return false;
        }
        HrMosqueCrmkReq other = (HrMosqueCrmkReq) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrMosqueCrmkReq[id=" + id + "]";
    }

}
