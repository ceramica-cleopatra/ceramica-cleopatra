/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
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
@Table(name = "NEXT_HR_EMP_TIME", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "NextHrEmpTime.findAll", query = "SELECT n FROM NextHrEmpTime n"),
    @NamedQuery(name = "NextHrEmpTime.findById", query = "SELECT n FROM NextHrEmpTime n WHERE n.id = :id"),
    @NamedQuery(name = "NextHrEmpTime.findByTrnsDate", query = "SELECT n FROM NextHrEmpTime n WHERE n.trnsDate = :trnsDate"),
    @NamedQuery(name = "NextHrEmpTime.findByShiftId", query = "SELECT n FROM NextHrEmpTime n WHERE n.shiftId = :shiftId"),
    @NamedQuery(name = "NextHrEmpTime.findByShiftName", query = "SELECT n FROM NextHrEmpTime n WHERE n.shiftName = :shiftName")})
public class NextHrEmpTime implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Id
    @Column(name = "ID")
    private long id;
    @JoinColumn(name = "HD_ID" ,referencedColumnName="EMP_NO")
    @ManyToOne(fetch=FetchType.EAGER)
    private HrEmpMangers empNo;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @Basic(optional = false)
    @Column(name = "SHIFT_ID")
    private long shiftId;
    @Column(name = "SHIFT_NAME")
    private String shiftName;

    public NextHrEmpTime() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public HrEmpMangers getEmpNo() {
        return empNo;
    }

    public void setEmpNo(HrEmpMangers empNo) {
        this.empNo = empNo;
    }

 

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public long getShiftId() {
        return shiftId;
    }

    public void setShiftId(long shiftId) {
        this.shiftId = shiftId;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

}
