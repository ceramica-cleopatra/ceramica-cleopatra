
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author data
 */
public class OvertimeApproveHdDTO {
private Long   empNo;
private String empName;
private String Location;
private boolean approveAll;
private Long totalPlusMinuts;
private List<OvertimeApproveDtDTO> overtimeApproveDtDTOList;

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Long empNo) {
        this.empNo = empNo;
    }

    public List<OvertimeApproveDtDTO> getOvertimeApproveDtDTOList() {
        return overtimeApproveDtDTOList;
    }

    public void setOvertimeApproveDtDTOList(List<OvertimeApproveDtDTO> overtimeApproveDtDTOList) {
        this.overtimeApproveDtDTOList = overtimeApproveDtDTOList;
    }

    public boolean isApproveAll() {
        return approveAll;
    }

    public void setApproveAll(boolean approveAll) {
        this.approveAll = approveAll;
    }

    public Long getTotalPlusMinuts() {
        return totalPlusMinuts;
    }

    public void setTotalPlusMinuts(Long totalPlusMinuts) {
        this.totalPlusMinuts = totalPlusMinuts;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OvertimeApproveHdDTO other = (OvertimeApproveHdDTO) obj;
        if (this.empNo != other.empNo && (this.empNo == null || !this.empNo.equals(other.empNo))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (this.empNo != null ? this.empNo.hashCode() : 0);
        return hash;
    }

    

}
