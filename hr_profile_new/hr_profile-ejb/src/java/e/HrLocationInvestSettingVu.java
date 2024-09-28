/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
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
/**
 *
 * @author DEV
 */
@Entity
@Table(name = "HR_LOCATION_INVEST_SETTING_VU", catalog = "", schema = "HR")

@NamedQueries({
    @NamedQuery(name = "HrLocationInvestSettingVu.findAll", query = "SELECT h FROM HrLocationInvestSettingVu h where h.empNo=:empNo")
})
public class HrLocationInvestSettingVu {

 private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "EMP_NO")
    private Long empNo;

    public Long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Long empNo) {
        this.empNo = empNo;
    }


    
}
