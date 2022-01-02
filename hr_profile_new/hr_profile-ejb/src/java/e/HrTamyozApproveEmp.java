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
@Table(name = "HR_TAMYOZ_APPROVE_EMP")
@NamedQueries({
    @NamedQuery(name = "HrTamyozApproveEmp.findAll", query = "SELECT h FROM HrTamyozApproveEmp h"),
    @NamedQuery(name = "HrTamyozApproveEmp.findByEmpNo", query = "SELECT h FROM HrTamyozApproveEmp h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrTamyozApproveEmp.findByLocId", query = "SELECT h FROM HrTamyozApproveEmp h WHERE h.locId = :locId"),
    @NamedQuery(name = "HrTamyozApproveEmp.findByApproveLevel", query = "SELECT h FROM HrTamyozApproveEmp h WHERE h.approveLevel = :approveLevel")})
public class HrTamyozApproveEmp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "EMP_NO")
    private Long empNo;
    @Column(name = "LOC_ID")
    private Long locId;
    @Column(name = "APPROVE_LEVEL")
    private Long approveLevel;

    public HrTamyozApproveEmp() {
    }

    public Long getApproveLevel() {
        return approveLevel;
    }

    public void setApproveLevel(Long approveLevel) {
        this.approveLevel = approveLevel;
    }

    public Long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Long empNo) {
        this.empNo = empNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLocId() {
        return locId;
    }

    public void setLocId(Long locId) {
        this.locId = locId;
    }

   
}
