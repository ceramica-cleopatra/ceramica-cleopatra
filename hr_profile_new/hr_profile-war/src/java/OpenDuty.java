/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrDutyEmpMngDt;
import e.HrEmpInfo;
import e.HrLocation;
import e.HrOpenDutyExpectedLocDt;
import e.HrOpenDutyHd;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;
import sb.SessionBeanLocal;
import sb.SessionBeanRemote;

/**
 *
 * @author Administrator
 */
@ManagedBean
@SessionScoped
public class OpenDuty{
    @EJB
    private SessionBeanLocal sessionBean;

private Long duty_typ;
private Long emp_id;
private Date from_date;
private Date to_date;
private String notes;
private String usercode;
private String txt;
private Long selected_row;
private String emp_name;
private boolean cancel;
private DualListModel<HrLocation> duty_locations;
private List<HrDutyEmpMngDt> hrDutyEmpMngDtList=new ArrayList<HrDutyEmpMngDt>();
private List<HrLocation> available_duty_locations=new ArrayList<HrLocation>();
private List<HrLocation> selected_duty_locations=new ArrayList<HrLocation>();
private List<HrOpenDutyHd> previous_duty=new ArrayList<HrOpenDutyHd>();
private HrOpenDutyHd selected_duty=new HrOpenDutyHd();
private HrEmpInfo hrEmpInfo=new HrEmpInfo();
    /** Creates a new instance of OpenDuty */
    public OpenDuty() {
    }

    public List<HrDutyEmpMngDt> getHrDutyEmpMngDtList() {
        if(hrDutyEmpMngDtList.isEmpty()){
        FacesContext fc = FacesContext.getCurrentInstance();
        fc = FacesContext.getCurrentInstance();
        ValueBinding vb = fc.getApplication().createValueBinding("#{loginBean.username}");
        usercode = vb.getValue(fc).toString();
        HrEmpInfo hrEmpInfo=sessionBean.finduserbyid(Long.parseLong(usercode));
        hrDutyEmpMngDtList=sessionBean.getDutyEmpMngDt(hrEmpInfo.getDeptId(),hrEmpInfo.getLocId(),hrEmpInfo.getJobGrpId(),hrEmpInfo.getJobId(),txt);
        }
        return hrDutyEmpMngDtList;
    }

    public List<String> complete(String query) {
      FacesContext fc = FacesContext.getCurrentInstance();
      if(usercode.length()==0){
      ValueBinding vb = fc.getApplication().createValueBinding("#{loginBean.username}");
      usercode = vb.getValue(fc).toString();
      }
      HrEmpInfo hrEmpInfo=sessionBean.finduserbyid(Long.parseLong(usercode));
      List<String> results =sessionBean.getEmpDutyByEmpNameSubstr(hrEmpInfo.getDeptId(),hrEmpInfo.getLocId(),hrEmpInfo.getJobGrpId(),hrEmpInfo.getJobId(),query);
      txt=null;
      return results;
    }

    public void search(ActionEvent ae)
    {   HrEmpInfo hrEmpInfo=sessionBean.finduserbyid(Long.parseLong(usercode));
        hrDutyEmpMngDtList=sessionBean.getDutyEmpMngDt(hrEmpInfo.getDeptId(),hrEmpInfo.getLocId(),hrEmpInfo.getJobGrpId(),hrEmpInfo.getJobId(),txt);
    }

 public String findEmp()
  {
    selected_row = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("emp").toString());
    hrEmpInfo=sessionBean.finduserbyid(selected_row);
    emp_name=hrEmpInfo.getEmpName();
    txt=null;
    selected_duty=new HrOpenDutyHd();
    available_duty_locations=new ArrayList<HrLocation>();
    duty_locations=new DualListModel<HrLocation>();
    selected_duty_locations=new ArrayList<HrLocation>();
    cancel=false;
    notes=null;
    from_date=null;
    to_date=null;
    previous_duty=new ArrayList<HrOpenDutyHd>();
    return "open_duty_dt";
    }
    public void setHrDutyEmpMngDtList(List<HrDutyEmpMngDt> hrDutyEmpMngDtList) {
        this.hrDutyEmpMngDtList = hrDutyEmpMngDtList;
    }

    public DualListModel<HrLocation> getDuty_locations() {
        if(hrEmpInfo!=null && selected_duty.getEmpId()==null)
        {
            available_duty_locations = sessionBean.getDutyLocations(hrEmpInfo.getLocId());
            duty_locations=new DualListModel<HrLocation>(available_duty_locations,selected_duty_locations);
        }
        return duty_locations;
    }

    public void save_duty(ActionEvent ae)
    {
      FacesContext fc = FacesContext.getCurrentInstance();
       if(from_date==null || to_date==null || duty_locations.getTarget().isEmpty() || notes==null)
        {
            fc.addMessage(notes, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Œÿ√","ÌÃ» ≈œŒ«· Ã„Ì⁄ «·»Ì«‰« "));
            return;
        }

     Calendar c1=Calendar.getInstance();
     c1.set(Calendar.HOUR, 0);
     c1.set(Calendar.MINUTE,0);
     c1.set(Calendar.SECOND, 0);
     c1.set(Calendar.MILLISECOND,0);
     c1.set(Calendar.HOUR_OF_DAY,0);
     c1.set(Calendar.AM_PM, 0);
     Calendar c2=Calendar.getInstance();
     c2.setTime(from_date);
      if(c2.before(c1))
       {
            fc.addMessage(notes, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Œÿ√","·« Ì„ﬂ‰ ≈œŒ«· „√„Ê—Ì… » «—ÌŒ ”«»ﬁ"));
            return;
        }
     c2.set(Calendar.HOUR, 0);
     c2.set(Calendar.MINUTE,0);
     c2.set(Calendar.SECOND, 0);
     c2.set(Calendar.AM_PM, 0);
     Calendar c3=Calendar.getInstance();
     c3.setTime(to_date);
     c3.set(Calendar.HOUR, 23);
     c3.set(Calendar.MINUTE,59);
     c3.set(Calendar.SECOND, 59);
     c3.set(Calendar.AM_PM, 0);

      if(from_date.after(to_date))
          {
            fc.addMessage(notes, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Œÿ√"," Õﬁﬁ „‰  «—ÌŒ »œ√ Ê √‰ Â«¡ «·„√„Ê—Ì…"));
            return;
        }

     
     from_date=c2.getTime();
     to_date=c3.getTime();
     
      if(selected_duty==null || selected_duty.getEmpId()==null)
      {
          if(sessionBean.chkExistanceOfDutyCurrentely(hrEmpInfo, from_date)>0L)
         {
             fc.addMessage(notes, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Œÿ√","·« Ì„ﬂ‰ › Õ „√„Ê—ÌÂ ·Â–« «·„ÊŸ› Œ·«· Â–Â «·› —…..«·„ÊŸ› ›Ï „√„Ê—Ì… Õ«·Ì«"));
                selected_duty=new HrOpenDutyHd();
                selected_duty_locations=new ArrayList<HrLocation>();
                getDuty_locations();
                cancel=false;
                notes=null;
                from_date=null;
                to_date=null;
                previous_duty=sessionBean.find_duty_for_specific_emp(hrEmpInfo, new Date());
             return;
         }
         if(sessionBean.chkExistanceOfDutyCurrentely(hrEmpInfo, to_date)>0L)
         {
             fc.addMessage(notes, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Œÿ√","·« Ì„ﬂ‰ › Õ „√„Ê—ÌÂ ·Â–« «·„ÊŸ› Œ·«· Â–Â «·› —…..«·„ÊŸ› ›Ï „√„Ê—Ì… Õ«·Ì«"));
               selected_duty=new HrOpenDutyHd();
                selected_duty_locations=new ArrayList<HrLocation>();
                getDuty_locations();
                cancel=false;
                notes=null;
                from_date=null;
                to_date=null;
                previous_duty=sessionBean.find_duty_for_specific_emp(hrEmpInfo, new Date());
             return;
         }
          if(usercode.length()==0)
          {
              ValueBinding vb = fc.getApplication().createValueBinding("#{loginBean.username}");
              usercode = vb.getValue(fc).toString();
          }
            HrEmpInfo mng=sessionBean.finduserbyid(Long.parseLong(usercode));
            HrOpenDutyHd hrOpenDutyHd=new HrOpenDutyHd();
            hrOpenDutyHd.setEmpId(hrEmpInfo);
            hrOpenDutyHd.setFromDate(from_date);
            hrOpenDutyHd.setToDate(to_date);
            hrOpenDutyHd.setNotes(notes);
            hrOpenDutyHd.setTrnsDate(new Date());
            hrOpenDutyHd.setMngId(mng);
            List<HrOpenDutyExpectedLocDt> hrOpenDutyExpectedLocDts=new ArrayList<HrOpenDutyExpectedLocDt>();
             if(cancel)
                hrOpenDutyHd.setCancel(new Character('Y'));
            else
                hrOpenDutyHd.setCancel(new Character('N'));
            for (HrLocation hrLocation : duty_locations.getTarget())
            {
                HrOpenDutyExpectedLocDt hrOpenDutyExpectedLocDt=new HrOpenDutyExpectedLocDt();
                hrOpenDutyExpectedLocDt.setHrOpenDutyHd(hrOpenDutyHd);
                hrOpenDutyExpectedLocDt.setHrLocation(hrLocation);
                hrOpenDutyExpectedLocDts.add(hrOpenDutyExpectedLocDt);
            }
            hrOpenDutyHd.setHrOpenDutyExpectedLocDtList(hrOpenDutyExpectedLocDts);
            sessionBean.persistHrOpenDutyHd(hrOpenDutyHd);
            fc.addMessage(notes,new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ › Õ «·„√„Ê—Ì… »‰Ã«Õ"));
        }
 else
      {
           if(from_date.compareTo(selected_duty.getFromDate())!=0 || to_date.compareTo(selected_duty.getToDate())!=0)
           {
            if(sessionBean.chkExistanceOfDutyCurrentely(hrEmpInfo, from_date)>1L)
             {
                 fc.addMessage(notes, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Œÿ√","·« Ì„ﬂ‰ › Õ „√„Ê—ÌÂ ·Â–« «·„ÊŸ› Œ·«· Â–Â «·› —…..«·„ÊŸ› ›Ï „√„Ê—Ì… Õ«·Ì«"));
                selected_duty=new HrOpenDutyHd();
                selected_duty_locations=new ArrayList<HrLocation>();
                getDuty_locations();
                cancel=false;
                notes=null;
                from_date=null;
                to_date=null;
                previous_duty=sessionBean.find_duty_for_specific_emp(hrEmpInfo, new Date());
                 return;
             }
             if(sessionBean.chkExistanceOfDutyCurrentely(hrEmpInfo, to_date)>1L)
             {
                 selected_duty=new HrOpenDutyHd();
                selected_duty_locations=new ArrayList<HrLocation>();
                getDuty_locations();
                cancel=false;
                notes=null;
                from_date=null;
                to_date=null;
                previous_duty=sessionBean.find_duty_for_specific_emp(hrEmpInfo, new Date());
                 return;
             }
          }
            selected_duty.setEmpId(hrEmpInfo);
            selected_duty.setFromDate(from_date);
            selected_duty.setToDate(to_date);
            selected_duty.setNotes(notes);
            List<HrOpenDutyExpectedLocDt> hrOpenDutyExpectedLocDts=new ArrayList<HrOpenDutyExpectedLocDt>();
             if(cancel)
                selected_duty.setCancel(new Character('Y'));
              else
                selected_duty.setCancel(new Character('N'));

            for  (HrOpenDutyExpectedLocDt hrOpenDutyExpectedLocDt :sessionBean.getDutyExpectedLocations(selected_duty))
            {
            sessionBean.removeHrOpenDutyExpectedLocationDt(hrOpenDutyExpectedLocDt);
          }
            for (HrLocation hrLocation : duty_locations.getTarget())
            {
                HrOpenDutyExpectedLocDt hrOpenDutyExpectedLocDt=new HrOpenDutyExpectedLocDt();
                hrOpenDutyExpectedLocDt.setHrOpenDutyHd(selected_duty);
                hrOpenDutyExpectedLocDt.setHrLocation(hrLocation);
                hrOpenDutyExpectedLocDts.add(hrOpenDutyExpectedLocDt);
            }
            selected_duty.setHrOpenDutyExpectedLocDtList(hrOpenDutyExpectedLocDts);
            sessionBean.mergeHrOpenDutyHd(selected_duty);
            fc.addMessage(notes,new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „  ⁄œÌ· «·„√„Ê—Ì… »‰Ã«Õ"));
        }
        selected_duty=new HrOpenDutyHd();
        selected_duty_locations=new ArrayList<HrLocation>();
        getDuty_locations();
        cancel=false;
        notes=null;
        from_date=null;
        to_date=null;
        previous_duty=sessionBean.find_duty_for_specific_emp(hrEmpInfo, new Date());
    }

    public List<HrOpenDutyHd> getPrevious_duty() {
         Calendar c1=Calendar.getInstance();
         c1.set(Calendar.HOUR, 0);
         c1.set(Calendar.MINUTE,0);
         c1.set(Calendar.SECOND, 0);
         c1.set(Calendar.MILLISECOND,0);
         c1.set(Calendar.HOUR_OF_DAY,0);
         c1.set(Calendar.AM_PM, 0);
        previous_duty=sessionBean.find_duty_for_specific_emp(hrEmpInfo, c1.getTime());
        return previous_duty;
    }

    public HrOpenDutyHd getSelected_duty() {
        return selected_duty;
    }

    public void setSelected_duty(HrOpenDutyHd selected_duty) {
        this.selected_duty = selected_duty;
    }

    public void onRowSlelect(SelectEvent e)
    {
        HrOpenDutyHd hrOpenDutyHd=(HrOpenDutyHd)e.getObject();
        selected_duty_locations=new ArrayList<HrLocation>();
        for (HrOpenDutyExpectedLocDt hrOpenDutyExpectedLocDt :sessionBean.getDutyExpectedLocations(hrOpenDutyHd))
        {
         selected_duty_locations.add(hrOpenDutyExpectedLocDt.getHrLocation());
         available_duty_locations.remove(hrOpenDutyExpectedLocDt.getHrLocation());
        }
        duty_locations=new DualListModel<HrLocation>(available_duty_locations,selected_duty_locations);
        from_date=selected_duty.getFromDate();
        to_date=selected_duty.getToDate();
        notes=selected_duty.getNotes();
         if (selected_duty.getCancel().equals(new Character('Y')))
            cancel=true;
          else
            cancel=false;
    }

    public void setPrevious_duty(List<HrOpenDutyHd> previous_duty) {
        this.previous_duty = previous_duty;
    }


    

    public void setDuty_locations(DualListModel<HrLocation> duty_locations) {
        this.duty_locations = duty_locations;
    }
    public List<HrLocation> getAvailable_duty_locations() {
        
        return available_duty_locations;
    }

    public void setAvailable_duty_locations(List<HrLocation> available_duty_locations) {
        this.available_duty_locations = available_duty_locations;
    }

    public List<HrLocation> getSelected_duty_locations() {
        return selected_duty_locations;
    }

    public void setSelected_duty_locations(List<HrLocation> selected_duty_locations) {
        this.selected_duty_locations = selected_duty_locations;
    }


    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getTo_date() {
        return to_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

   

    


}
