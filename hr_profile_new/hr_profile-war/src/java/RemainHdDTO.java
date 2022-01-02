
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class RemainHdDTO {
private Long remainId;
private Long remainNo;
private String crmkSehy;
private String store;
private List<RemainDtDTO> remainDtDTOList;

    public String getCrmkSehy() {
        return crmkSehy;
    }

    public void setCrmkSehy(String crmkSehy) {
        this.crmkSehy = crmkSehy;
    }

    public List<RemainDtDTO> getRemainDtDTOList() {
        return remainDtDTOList;
    }

    public void setRemainDtDTOList(List<RemainDtDTO> remainDtDTOList) {
        this.remainDtDTOList = remainDtDTOList;
    }

    public Long getRemainId() {
        return remainId;
    }

    public void setRemainId(Long remainId) {
        this.remainId = remainId;
    }

    public Long getRemainNo() {
        return remainNo;
    }

    public void setRemainNo(Long remainNo) {
        this.remainNo = remainNo;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }


}
