
import java.math.BigInteger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DEV
 */
public class HrProfilePrivilageDTO {
    private Long id;
    private long empNo;
    private HrMenuTableDTO menuId;
    private String arabicName;

    public String getArabicName() {
        return arabicName;
    }

    public void setArabicName(String arabicName) {
        this.arabicName = arabicName;
    }

    public long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(long empNo) {
        this.empNo = empNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HrMenuTableDTO getMenuId() {
        return menuId;
    }

    public void setMenuId(HrMenuTableDTO menuId) {
        this.menuId = menuId;
    }

    public HrProfilePrivilageDTO(Long id, long empNo, HrMenuTableDTO menuId, String arabicName) {
        this.id = id;
        this.empNo = empNo;
        this.menuId = menuId;
        this.arabicName = arabicName;
    }

     @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0) ;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HrProfilePrivilageDTO)) {
            return false;
        }
        HrProfilePrivilageDTO other = (HrProfilePrivilageDTO) object;
        if ((this.empNo == 0 && other.empNo != 0) || (this.empNo != 0 && other.empNo == 0) || (this.menuId == null && other.menuId != null) || (this.menuId != null && other.menuId == null) || (this.empNo != 0 && this.menuId!=null && (this.empNo!=other.empNo || !this.menuId.equals(other.menuId)))) {
            return false;
        }
        return true;
    }
}
