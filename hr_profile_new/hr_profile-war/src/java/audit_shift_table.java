/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrEmpMangers;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import sb.SessionBeanRemote;

/**
 *
 * @author ahmed abbas
 */
@ManagedBean
@RequestScoped
public class audit_shift_table {
    @EJB
    private SessionBeanRemote sessionBean;
    private List<HrEmpMangers> getMngEmpList=new ArrayList<HrEmpMangers>();
    /** Creates a new instance of audit_shift_table */
    public audit_shift_table() {
    }

    public List<HrEmpMangers> getGetMngEmpList() {
        FacesContext fc=FacesContext.getCurrentInstance();
        ValueBinding vb = fc.getApplication().createValueBinding("#{loginBean.username}");
        String usercode = vb.getValue(fc).toString();
        HrEmpInfo hrEmpInfo=sessionBean.finduserbyid(Long.parseLong(usercode));
        getMngEmpList=sessionBean.getMngEmp(hrEmpInfo.getDeptId(),hrEmpInfo.getJobId(),hrEmpInfo.getJobGrpId(),hrEmpInfo.getLocId());
        return getMngEmpList;
    }

    public void setGetMngEmpList(List<HrEmpMangers> getMngEmpList) {
        this.getMngEmpList = getMngEmpList;
    }

}
