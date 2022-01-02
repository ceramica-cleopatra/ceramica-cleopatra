/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author data
 */
@Entity
@Table(name = "HR_PRV_YEAR_TRANS_HOLIDAYS")
@NamedQueries({
    @NamedQuery(name = "HrPrvYearTransHolidays.findAll", query = "SELECT h FROM HrPrvYearTransHolidays h"),
    @NamedQuery(name = "HrPrvYearTransHolidays.findByEmpNo", query = "SELECT h FROM HrPrvYearTransHolidays h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrPrvYearTransHolidays.findByRahatDays", query = "SELECT h FROM HrPrvYearTransHolidays h WHERE h.rahatDays = :rahatDays"),
    @NamedQuery(name = "HrPrvYearTransHolidays.findByHolDays", query = "SELECT h FROM HrPrvYearTransHolidays h WHERE h.holDays = :holDays")})
public class HrPrvYearTransHolidays implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "EMP_NO")
    private Long empNo;
    @Column(name = "RAHAT_DAYS")
    private Long rahatDays;
    @Column(name = "HOL_DAYS")
    private Long holDays;

    public HrPrvYearTransHolidays() {
    }

    public Long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Long empNo) {
        this.empNo = empNo;
    }

    public Long getRahatDays() {
        return rahatDays;
    }

    public void setRahatDays(Long rahatDays) {
        this.rahatDays = rahatDays;
    }

    public Long getHolDays() {
        return holDays;
    }

    public void setHolDays(Long holDays) {
        this.holDays = holDays;
    }


}
