/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrPersonalOrdrRequest;
import java.util.ArrayList;
import java.util.Calendar;
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
import javax.faces.model.SelectItem;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.RowEditEvent;
import sb.SessionBeanRemote;

/**
 *
 * @author Administrator
 */
@ManagedBean
@RequestScoped
public class PersonalOrdrConfirm {
    @EJB
    private SessionBeanRemote sessionBean;

    /** Creates a new instance of PersonalOrdrConfirm */
    public PersonalOrdrConfirm() {
    }

    public DataTable getDt() {
        return dt;
    }

    public void setDt(DataTable dt) {
        this.dt = dt;
    }
private List<HrPersonalOrdrRequest> hrPersonalOrdrRequests=new ArrayList<HrPersonalOrdrRequest>();
private DataTable dt;
private HrPersonalOrdrRequest hrPersonalOrdrRequest;
private List<HrPersonalOrdrRequest> hrPersonalOrdrRequestList=new ArrayList<HrPersonalOrdrRequest>();

    public List<HrPersonalOrdrRequest> getHrPersonalOrdrRequestList() {
        if(hrPersonalOrdrRequest!=null)
        {
        hrPersonalOrdrRequestList=sessionBean.getPersonalOrdrRequests(hrPersonalOrdrRequest.getEmpNo().getId());
            System.out.println(hrPersonalOrdrRequest.getEmpNo().getName()+hrPersonalOrdrRequestList.size());
        }
        return hrPersonalOrdrRequestList;
    }

    public void setHrPersonalOrdrRequestList(List<HrPersonalOrdrRequest> hrPersonalOrdrRequestList) {
        this.hrPersonalOrdrRequestList = hrPersonalOrdrRequestList;
    }


    public HrPersonalOrdrRequest getHrPersonalOrdrRequest() {
        return hrPersonalOrdrRequest;
    }

    public void setHrPersonalOrdrRequest(HrPersonalOrdrRequest hrPersonalOrdrRequest) {
        this.hrPersonalOrdrRequest = hrPersonalOrdrRequest;
    }

public List<HrPersonalOrdrRequest> getHrPersonalOrdrRequests() {       
        return hrPersonalOrdrRequests;
    }

    public void setHrPersonalOrdrRequests(List<HrPersonalOrdrRequest> hrPersonalOrdrRequests) {
        this.hrPersonalOrdrRequests = hrPersonalOrdrRequests;
    }
public void confirm_ordr_dscnt(RowEditEvent event)
{
FacesContext fc = FacesContext.getCurrentInstance();    
ValueBinding vb = fc.getApplication().createValueBinding("#{loginBean.username}");
Long usercode= Long.parseLong(vb.getValue(fc).toString());

HrPersonalOrdrRequest hrPersonalOrdrRequest=(HrPersonalOrdrRequest) event.getObject();
if(hrPersonalOrdrRequest.getApprove()!=null && hrPersonalOrdrRequest.getApprove().equals('Y'))
{ if(hrPersonalOrdrRequest.getDscnt()==null)
    {   fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ÌÃ» ≈œŒ«· ‰”»… «·Œ’„", "Œÿ√"));
        hrPersonalOrdrRequest.setApprove(null);
        return;
    }

}
 else if(hrPersonalOrdrRequest.getApprove()==null)
{
     hrPersonalOrdrRequest.setDscnt(null);
     hrPersonalOrdrRequest.setEmpApproved(null);
}
hrPersonalOrdrRequest.setEmpApproved(sessionBean.findempbyid(usercode));
sessionBean.merge_personal_ordr_req(hrPersonalOrdrRequest);
hrPersonalOrdrRequests=sessionBean.getPersonalOrdrRequests(hrPersonalOrdrRequest.getEmpNo().getId());
fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ ≈⁄ „«œ «·ÿ·»", " „ »‰Ã«Õ"));
}
public void update_req_list(ActionEvent e)
{
Calendar c=Calendar.getInstance();
c.add(Calendar.DAY_OF_MONTH,-30);
     System.out.println(c.getTime());
hrPersonalOrdrRequests=sessionBean.getPersonalOrdrToConfirm(c.getTime(),new Date());
dt.setValue(hrPersonalOrdrRequests);
   }
}
