/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "HR_PROFILE_ALERT_DT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrProfileAlertDt.findAll", query = "SELECT h FROM HrProfileAlertDt h"),
     @NamedQuery(name = "HrProfileAlertDt.findAMaxId", query = "SELECT max(h.id) FROM HrProfileAlertDt h"),
    @NamedQuery(name = "HrProfileAlertDt.findById", query = "SELECT h FROM HrProfileAlertDt h WHERE h.id = :id"),
    @NamedQuery(name = "HrProfileAlertDt.findByEmpNo", query = "SELECT h FROM HrProfileAlertDt h WHERE h.empNo = :empNo")})
public class HrProfileAlertDt implements Serializable {
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "EMP_NO")
    private Long empNo;
    @JoinColumn(name = "HD_ID", referencedColumnName = "ID")
    @ManyToOne
    private HrProfileAlertHd hrProfileAlertHd;

    public HrProfileAlertDt() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

    public Long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Long empNo) {
        this.empNo = empNo;
    }

   

    

    public HrProfileAlertHd getHrProfileAlertHd() {
        return hrProfileAlertHd;
    }

    public void setHrProfileAlertHd(HrProfileAlertHd hrProfileAlertHd) {
        this.hrProfileAlertHd = hrProfileAlertHd;
    }


   


}
