
import e.HrMenuTable;
import java.util.List;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class MenuModelDTO {
private HrMenuTable parent;
private List<HrMenuTable> childList;

    public List<HrMenuTable> getChildList() {
        return childList;
    }

    public void setChildList(List<HrMenuTable> childList) {
        this.childList = childList;
    }

    public HrMenuTable getParent() {
        return parent;
    }

    public void setParent(HrMenuTable parent) {
        this.parent = parent;
    }


}
