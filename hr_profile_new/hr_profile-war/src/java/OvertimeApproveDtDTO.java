
import java.util.Date;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author data
 */
public class OvertimeApproveDtDTO {
private Long plusMinuts;
private Date trnsDate;
private String inTrns;
private String outTrns;
private String approveAsString;
private Long id;
private OvertimeApproveHdDTO overtimeApproveHdDTO;

    public String getInTrns() {
        return inTrns;
    }

    public void setInTrns(String inTrns) {
        this.inTrns = inTrns;
    }

    public String getOutTrns() {
        return outTrns;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOutTrns(String outTrns) {
        this.outTrns = outTrns;
    }

    public Long getPlusMinuts() {
        return plusMinuts;
    }

    public void setPlusMinuts(Long plusMinuts) {
        this.plusMinuts = plusMinuts;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public OvertimeApproveHdDTO getOvertimeApproveHdDTO() {
        return overtimeApproveHdDTO;
    }

    public void setOvertimeApproveHdDTO(OvertimeApproveHdDTO overtimeApproveHdDTO) {
        this.overtimeApproveHdDTO = overtimeApproveHdDTO;
    }
    
    public String getApproveAsString() {
        return approveAsString;
    }

    public void setApproveAsString(String approveAsString) {
        this.approveAsString = approveAsString;
    }


}
