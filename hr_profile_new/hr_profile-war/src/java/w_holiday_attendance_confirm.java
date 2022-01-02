/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrEmpTime;
import e.HrRegion;
import e.HrShift;
import e.HrShiftChangeRequest;
import e.HrShiftRequestDt;
import e.HrWHolidayAttendanceReq;
import e.HrWHolidayAttendanceReqDt;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.RowEditEvent;
import sb.SessionBeanRemote;
/**
 *
 * @author Administrator
 */
@ManagedBean
@RequestScoped
public class w_holiday_attendance_confirm {
    @EJB
    private SessionBeanRemote sessionBean;

    /** Creates a new instance of w_holiday_attendance_confirm */
    public w_holiday_attendance_confirm() {
    }



private List<HrWHolidayAttendanceReqDt> hrWHolidayAttendanceReqDtList=new ArrayList<HrWHolidayAttendanceReqDt>();
private String usercode;
private DataTable dt;

    public DataTable getDt() {
        return dt;
    }

    public void setDt(DataTable dt) {
        this.dt = dt;
    }

    public List<HrWHolidayAttendanceReqDt> getHrWHolidayAttendanceReqDtList() {
        return hrWHolidayAttendanceReqDtList;
    }

    public void setHrWHolidayAttendanceReqDtList(List<HrWHolidayAttendanceReqDt> hrWHolidayAttendanceReqDtList) {
        this.hrWHolidayAttendanceReqDtList = hrWHolidayAttendanceReqDtList;
    }


    /** Creates a new instance of shift_confirm */



public void update(RowEditEvent e)
{
FacesContext fc = FacesContext.getCurrentInstance();
ValueBinding vb = fc.getApplication().createValueBinding("#{loginBean.username}");
usercode = vb.getValue(fc).toString();
HrWHolidayAttendanceReqDt hrWHolidayAttendanceReqDt=(HrWHolidayAttendanceReqDt) e.getObject();
HrWHolidayAttendanceReq hrWHolidayAttendanceReq=sessionBean.getHrWHolidayAttendanceById(hrWHolidayAttendanceReqDt.getReqId());
if(hrWHolidayAttendanceReqDt.getApproved().equals("Y"))
{

hrWHolidayAttendanceReq.setMngNo(sessionBean.finduserbyid(Long.parseLong(usercode)));
hrWHolidayAttendanceReq.setApproved("Y");
sessionBean.mergeHrWHolidayAttendanceReq(hrWHolidayAttendanceReq);
fc.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO," „ »‰Ã«Õ"," „ ≈⁄ „«œ «·ÿ·» »‰Ã«Õ"));
}
 else if(hrWHolidayAttendanceReqDt.getApproved().equals("N"))
{
 hrWHolidayAttendanceReq.setMngNo(sessionBean.finduserbyid(Long.parseLong(usercode)));
 hrWHolidayAttendanceReq.setApproved("N");
 sessionBean.mergeHrWHolidayAttendanceReq(hrWHolidayAttendanceReq);
 fc.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO," „ »‰Ã«Õ"," „ —›÷ «·ÿ·» »‰Ã«Õ"));
 }
}

public void reset_request_list()
{
FacesContext fc = FacesContext.getCurrentInstance();
ValueBinding vb = fc.getApplication().createValueBinding("#{loginBean.username}");
usercode = vb.getValue(fc).toString();
HrEmpInfo hrEmpInfo=sessionBean.finduserbyid(Long.parseLong(usercode));
hrWHolidayAttendanceReqDtList=sessionBean.getHolidayAttendanceReqDtList(Long.parseLong(usercode),hrEmpInfo.getDeptId(),hrEmpInfo.getLocId(),hrEmpInfo.getJobGrpId(),hrEmpInfo.getJobId());
dt.setValue(hrWHolidayAttendanceReqDtList);
}
public void update_request_list(ActionEvent ae)
    {
reset_request_list();
}





}
