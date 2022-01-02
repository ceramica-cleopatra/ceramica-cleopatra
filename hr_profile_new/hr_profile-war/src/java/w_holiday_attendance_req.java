/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrWHolidayAttendanceReq;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
public class w_holiday_attendance_req
{
    @EJB
    private SessionBeanRemote sessionBean;
    public Date w_holiday_date;
    String usercode;
    private DataTable dt;
    public HrWHolidayAttendanceReq hrWHolidayAttendanceReq;
    public List<HrWHolidayAttendanceReq> hrWHolidayAttendanceReqHist=new ArrayList<HrWHolidayAttendanceReq>();
    /** Creates a new instance of w_holiday_attendance_req */
    public w_holiday_attendance_req() {
    }

    public DataTable getDt() {
        return dt;
    }

    public void setDt(DataTable dt) {
        this.dt = dt;
    }

    public List<HrWHolidayAttendanceReq> getHrWHolidayAttendanceReqHist() {
        
        return hrWHolidayAttendanceReqHist;
    }

    public void setHrWHolidayAttendanceReqHist(List<HrWHolidayAttendanceReq> hrWHolidayAttendanceReqHist) {
        this.hrWHolidayAttendanceReqHist = hrWHolidayAttendanceReqHist;
    }
    
    public Date getW_holiday_date() {
        return w_holiday_date;
    }

    public void setW_holiday_date(Date w_holiday_date) {
        this.w_holiday_date = w_holiday_date;
    }

    public HrWHolidayAttendanceReq getHrWHolidayAttendanceReq() {
        return hrWHolidayAttendanceReq;
    }

    public void setHrWHolidayAttendanceReq(HrWHolidayAttendanceReq hrWHolidayAttendanceReq) {
        this.hrWHolidayAttendanceReq = hrWHolidayAttendanceReq;
    }
    
public void add_new_request(ActionEvent ae)
{
 FacesContext fc = FacesContext.getCurrentInstance();
 ValueBinding vb = fc.getApplication().createValueBinding("#{loginBean.username}");
 usercode = vb.getValue(fc).toString();

 if(w_holiday_date==null)
 {
   FacesMessage fm=new FacesMessage(FacesMessage.SEVERITY_ERROR,"Œÿ√", "ÌÃ» ≈œŒ«·  «—ÌŒ «·—«Õ… «·≈”»Ê⁄Ì…");
   fc.addMessage(null, fm);
   return;
 }
 if(w_holiday_date.before(new Date()) || w_holiday_date.equals(new Date()))
 {
   FacesMessage fm=new FacesMessage(FacesMessage.SEVERITY_ERROR,"Œÿ√", "ÌÃ» ≈œŒ«· «·ÿ·» ·ÌÊ„ ﬁ«œ„");
   fc.addMessage(null, fm);
   return;
 }
 Calendar c=Calendar.getInstance();
 c.setTime(w_holiday_date);
 HrEmpInfo hrEmpInfo=sessionBean.finduserbyid(Long.parseLong(usercode));
 int n=0;
 if((c.get(Calendar.DAY_OF_WEEK)+1)%7==0)
 n=7;
 else
 n=(c.get(Calendar.DAY_OF_WEEK)+1)%7;
    System.out.println("n="+n);
    System.out.println("day off"+hrEmpInfo.getDayOff());
 if(!hrEmpInfo.getDayOff().equals(new Long(n)))
 {
 FacesMessage fm=new FacesMessage(FacesMessage.SEVERITY_ERROR,"Œÿ√", "«·ÌÊ„ «·–Ï  „ ≈œŒ«·Â ·Ì” ÌÊ„ «·—«Õ… «·«”»Ê⁄Ï");
 fc.addMessage(null, fm);
 return;
 }
 hrWHolidayAttendanceReq=new HrWHolidayAttendanceReq();
 if(sessionBean.getHrWHolidayAttendanceReqMaxId()==null)
     hrWHolidayAttendanceReq.setId(1L);
 else
     hrWHolidayAttendanceReq.setId(sessionBean.getHrWHolidayAttendanceReqMaxId()+1L);
 hrWHolidayAttendanceReq.setEmpNo(Long.parseLong(usercode));
 hrWHolidayAttendanceReq.setTrnsDate(new Date());
 hrWHolidayAttendanceReq.setWeeklyHolidayDate(w_holiday_date);
 FacesMessage fm=new FacesMessage(FacesMessage.SEVERITY_INFO," „ »‰Ã«Õ", " „ Õ›Ÿ «·ÿ·» »‰Ã«Õ");
 fc.addMessage(null, fm);
 sessionBean.hrWHolidayAttendanceReqPersist(hrWHolidayAttendanceReq);
 
}
public void populate_WHoliday_list(ActionEvent ae)
  {
   reset_W_Holiday_Attendance_requets_list();
}
public void reset_W_Holiday_Attendance_requets_list()
{
    FacesContext fc = FacesContext.getCurrentInstance();
    ValueBinding vb = fc.getApplication().createValueBinding("#{loginBean.username}");
    usercode = vb.getValue(fc).toString();
    Calendar c=Calendar.getInstance();
    c.add(Calendar.MONTH,-1);
    c.set(Calendar.DATE, 1);
    hrWHolidayAttendanceReqHist=sessionBean.getHrWHolidayAttendanceReqHist(Long.parseLong(usercode), c.getTime());
    dt.setValue(hrWHolidayAttendanceReqHist);
}
public void update(RowEditEvent event)
 {
  HrWHolidayAttendanceReq hrWHolidayAttendanceReq=(HrWHolidayAttendanceReq) event.getObject();
  FacesContext fc=FacesContext.getCurrentInstance();
  ValueBinding vb = fc.getApplication().createValueBinding("#{loginBean.username}");
  usercode = vb.getValue(fc).toString();
  if(hrWHolidayAttendanceReq.getWeeklyHolidayDate()==null)
 {
   FacesMessage fm=new FacesMessage(FacesMessage.SEVERITY_ERROR,"Œÿ√", "ÌÃ» ≈œŒ«·  «—ÌŒ «·—«Õ… «·≈”»Ê⁄Ì…");
   fc.addMessage(null, fm);
   reset_W_Holiday_Attendance_requets_list();
   return;
 }
 if(hrWHolidayAttendanceReq.getWeeklyHolidayDate().before(new Date()) || hrWHolidayAttendanceReq.getWeeklyHolidayDate().equals(new Date()))
 {
   FacesMessage fm=new FacesMessage(FacesMessage.SEVERITY_ERROR,"Œÿ√", "ÌÃ» ≈œŒ«· «·ÿ·» ·ÌÊ„ ﬁ«œ„");
   fc.addMessage(null, fm);
   reset_W_Holiday_Attendance_requets_list();
   return;
 }
 Calendar c=Calendar.getInstance();
 c.setTime(hrWHolidayAttendanceReq.getWeeklyHolidayDate());
 HrEmpInfo hrEmpInfo=sessionBean.finduserbyid(Long.parseLong(usercode));
 int n=0;
 if((c.get(Calendar.DAY_OF_WEEK)+1)%7==0)
 n=7;
 else
 n=(c.get(Calendar.DAY_OF_WEEK)+1)%7;
    System.out.println("n="+n);
    System.out.println("day off"+hrEmpInfo.getDayOff());
 if(!hrEmpInfo.getDayOff().equals(new Long(n)))
 {
 FacesMessage fm=new FacesMessage(FacesMessage.SEVERITY_ERROR,"Œÿ√", "«·ÌÊ„ «·–Ï  „ ≈œŒ«·Â ·Ì” ÌÊ„ «·—«Õ… «·«”»Ê⁄Ï");
 fc.addMessage(null, fm);
 reset_W_Holiday_Attendance_requets_list();
 return;
 }
sessionBean.mergeHrWHolidayAttendanceReq(hrWHolidayAttendanceReq);
FacesMessage fm=new FacesMessage(FacesMessage.SEVERITY_INFO," „ »‰Ã«Õ", " „  ⁄œÌ· «·≈–‰ »‰Ã«Õ");
fc.addMessage(null, fm);
reset_W_Holiday_Attendance_requets_list();
}
}
