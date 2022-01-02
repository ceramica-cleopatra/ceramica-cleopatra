/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrSchedule;
import e.HrScheduleDt;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.primefaces.component.schedule.Schedule;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import sb.SessionBeanRemote;

/**
 *
 * @author ahmed abbas
 */
@ManagedBean
@RequestScoped
public class add_task implements Serializable {
    @EJB
    private SessionBeanRemote sessionBean;
    private ScheduleModel eventModel=new DefaultScheduleModel();;

    private ScheduleModel lazyEventModel;

    private ScheduleEvent event = new DefaultScheduleEvent();

    private String usercode;

    private Long lastStatus=1L;

    private String emp;

    private String editor;

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

   public List<String> complete(String query)
   {
      FacesContext fc=FacesContext.getCurrentInstance();
      System.out.println(query);
      ValueBinding vb = fc.getApplication().createValueBinding("#{loginBean.username}");
      usercode = vb.getValue(fc).toString();
      HrEmpInfo hrEmpInfo=sessionBean.finduserbyid(Long.parseLong(usercode));
      System.out.println(hrEmpInfo.getEmpName());
      List<String> results =sessionBean.getScheduleEmpByEmpNameSubstr(hrEmpInfo.getDeptId(),hrEmpInfo.getLocId(),hrEmpInfo.getJobGrpId(),hrEmpInfo.getJobId(),query);
      emp=null;
      return results;
    }

    public String getEmp() {
        if(event!=null && event.getData()!=null)
        {
        HrSchedule hrSchedule=sessionBean.getschedule(Long.parseLong(event.getData().toString()));
        emp=sessionBean.finduserbyid(hrSchedule.getEmpNo()).getEmpName();
        }
        return emp;
    }

    public void setEmp(String emp) {
        this.emp = emp;
    }

    HrSchedule hrSchedule=new HrSchedule();

    public Long getLastStatus() {
       if(event!=null && event.getData()!=null)
        {   try{lastStatus = sessionBean.getLastStatusOfSchedule(Long.parseLong(event.getData().toString())).getStatus();
            }catch(NullPointerException e)
            {
              lastStatus = 1L;
            }
       }
        else
        {
            lastStatus = 1L;
        }
        return lastStatus;
    }

    public void setLastStatus(Long lastStatus) {
        this.lastStatus = lastStatus;
    }


    public ScheduleEvent getEvent() {
        return event;
    }

    public Schedule getS() {
        return s;
    }

    public void setS(Schedule s) {
        this.s = s;
    }
    private Schedule s;
    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }


    public ScheduleModel getEventModel() {

        return eventModel;
    }


       public void addEvent(ActionEvent actionEvent)
       {
           FacesContext fc = FacesContext.getCurrentInstance();
            ValueBinding vb = fc.getApplication().createValueBinding("#{loginBean.username}");
            usercode = vb.getValue(fc).toString();
            java.sql.Timestamp timestamp1 = new java.sql.Timestamp(event.getStartDate().getTime());
            java.sql.Timestamp timestamp2 = new java.sql.Timestamp(event.getEndDate().getTime());
//            if(event.getData().toString().length()==0)
//            {
            hrSchedule=new HrSchedule();
            if(sessionBean.getseq()==null)
            hrSchedule.setId(1L);
            else
            hrSchedule.setId(sessionBean.getseq()+1L);
            if(event.getTitle().length()==0)
            {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Œÿ√", "ÌÃ» ≈œŒ«· «·„Â„…"));
            return;
           }
            hrSchedule.setTitle(event.getTitle());
            if(event.getStartDate().toString().length()==0)
            {
            fc.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Œÿ√","ÌÃ» ≈œŒ«·  «—ÌŒ »œ¡ «·„Â„…"));
            return;
           }
            hrSchedule.setFromDate(timestamp1);
            if(event.getEndDate().toString().length()==0)
            {
            fc.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Œÿ√","ÌÃ» ≈œŒ«·  «—ÌŒ ≈‰ Â«¡ «·„Â„…"));
            return;
            }
            hrSchedule.setToDate((Timestamp)timestamp2);
            hrSchedule.setMngNo(sessionBean.finduserbyid(Long.parseLong(usercode)));
            if(emp.length()==0)
            {
            fc.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Œÿ√","ÌÃ»  ÕœÌœ «·„ÊŸ›"));
            return;
            }
            hrSchedule.setEmpNo(sessionBean.findByEmpName(emp).getEmpNo());
            sessionBean.persistHrSchedule(hrSchedule);
            eventModel.addEvent(event);
//        }
//        else
//        {
//            eventModel.updateEvent(event);
//            System.out.println(event.getData());
//            hrSchedule=sessionBean.getschedule(Long.parseLong(event.getData().toString()));
//            hrSchedule.setTitle(event.getTitle());
//            hrSchedule.setFromDate(timestamp1);
//            hrSchedule.setToDate((Timestamp)timestamp2);
//            sessionBean.mergeHrSchedule(hrSchedule);
//
//        }
        event = new DefaultScheduleEvent();
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO," „ «·Õ›Ÿ"," „ Õ›Ÿ «·„Â„…"));
    }

 private void send(String smtpHost, int smtpPort,
                            String from, String to,
                            String subject, String content)
                throws AddressException, MessagingException {

        // Create a mail session
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", ""+smtpPort);
        Session session = Session.getDefaultInstance(props, null);

        // Construct the message
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setSubject(subject);
        msg.setText(content);
        msg.setContent(content,"text/html; charset=utf-8");


        // Send the message
        Transport.send(msg);
    }

     public void sendResponse(ActionEvent e)
       {
        FacesContext fc = FacesContext.getCurrentInstance();
        ValueBinding vb = fc.getApplication().createValueBinding("#{loginBean.username}");
        usercode = vb.getValue(fc).toString();
        if(editor.length()==0)
        {fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"·„ Ì „ «·≈—”«·", "ÌÃ» ≈œŒ«· «·—œ"));
        return;
        }
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
        HrSchedule hrSchedule=sessionBean.getschedule(Long.parseLong(event.getData().toString()));
        HrEmpInfo hrEmpInfo=sessionBean.finduserbyid(hrSchedule.getEmpNo());
        if(hrEmpInfo.geteMail().length()==0)
        {fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"·„ Ì „ «·≈—”«·", "·« ÌÊÃœ »—Ìœ ≈·ﬂ —Ê‰Ï „Œ’’ ·Â–« «·„ÊŸ›"));
        return;
        }
        String s="<div align='right'><h3>«·„Â„… /"+hrSchedule.getTitle()+"</h3>";
        s=s+"<h3>«·„œÌ— /"+hrSchedule.getMngNo().getEmpName()+"</h3>";
        s=s+"<h4>"+sdf.format(hrSchedule.getFromDate())+"/„‰  «—ÌŒ</h4>";
        s=s+"<h4>"+sdf.format(hrSchedule.getToDate())+"/«·Ï  «—ÌŒ</h4>";
        if(getLastStatus()==1)
        s=s+"<h4> «·Õ«·Â /·„ Ì „ «· ‰›Ì–</h4>";
        else if(getLastStatus()==2)
        s=s+"<h4> «·Õ«·Â /Ã«—Ï «· ‰›Ì–</h4>";
        else
        s=s+"<h4>«·Õ«·Â / „ «· ‰›Ì–</h4></div>";
        s=s+"<div  style='background-color:#FFA500;clear:both;text-align:center;height:3px'></div>";
        try
        {
        send("20.1.1.21", 25, " oracle@ccg.com.eg", hrEmpInfo.geteMail(),"Follow Up Task",s+editor);
        }catch(Exception exc){}
        editor=null;
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO," „ »‰Ã«Õ", " „ ≈—”«· «·»—Ìœ «·≈·ﬂ —Ê‰Ï »‰Ã«Õ"));

     }

    public void onEventSelect(ScheduleEntryMoveEvent moveEvent) {
        event = moveEvent.getScheduleEvent();

    }
    public void onDateSelect(SelectEvent selectEvent) {

        event = new DefaultScheduleEvent("", (Date)selectEvent.getObject(), (Date)selectEvent.getObject());
      System.out.println(event.getId()+"date");
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
    FacesContext.getCurrentInstance().addMessage(null, message);
    }


    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public HrSchedule getHrSchedule() {
        return hrSchedule;
    }

    public void setHrSchedule(HrSchedule hrSchedule) {
        this.hrSchedule = hrSchedule;
    }

    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }

    public void setLazyEventModel(ScheduleModel lazyEventModel) {
        this.lazyEventModel = lazyEventModel;
    }



public void populate_schedule(ActionEvent ae)
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        ValueBinding vb = fc.getApplication().createValueBinding("#{loginBean.username}");
        usercode = vb.getValue(fc).toString();
        for(HrSchedule hs : sessionBean.getMngSchedule(Long.parseLong(usercode)))
        {
          Object o=(Object)hs.getId().toString();
            System.out.println(hs.getFromDate());
          eventModel.addEvent(new DefaultScheduleEvent(hs.getTitle(),hs.getFromDate(),hs.getToDate(),o));
        }
          lazyEventModel = new LazyScheduleModel() {

            public void fetchEvents(Date start, Date end) {
                clear();

                Date random = start;
                addEvent(new DefaultScheduleEvent("Lazy Event 1", random, random));

                random = start;
                addEvent(new DefaultScheduleEvent("Lazy Event 2", random, random));
            }

          };
          s.setValue(eventModel);
}

}
