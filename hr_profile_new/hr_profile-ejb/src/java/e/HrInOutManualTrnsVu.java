/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "HR_IN_OUT_MANUAL_TRNS_VU")
@NamedQueries({
    @NamedQuery(name = "HrInOutManualTrnsVu.findAll", query = "SELECT h FROM HrInOutManualTrnsVu h"),
    @NamedQuery(name = "HrInOutManualTrnsVu.findById", query = "SELECT h FROM HrInOutManualTrnsVu h WHERE h.id = :id"),
    @NamedQuery(name = "HrInOutManualTrnsVu.findByEmpNo", query = "SELECT h FROM HrInOutManualTrnsVu h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrInOutManualTrnsVu.findByEmpName", query = "SELECT h FROM HrInOutManualTrnsVu h WHERE h.empName = :empName"),
    @NamedQuery(name = "HrInOutManualTrnsVu.findByUserId", query = "SELECT h FROM HrInOutManualTrnsVu h WHERE h.userId = :userId")})
public class HrInOutManualTrnsVu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "EMP_NO")
    private Long empNo;
    @Column(name = "EMP_NAME")
    private String empName;
    @Column(name = "USER_ID")
    private Long userId;

    public HrInOutManualTrnsVu() {
    }


    public Long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Long empNo) {
        this.empNo = empNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    

}
