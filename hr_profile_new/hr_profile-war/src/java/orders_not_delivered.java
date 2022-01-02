/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.CrmkOrdersNotDelivered;
import e.HrEmpInfo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;
import org.primefaces.component.datatable.DataTable;
import sb.SessionBeanRemote;

/**
 *
 * @author ahmed abbas
 */
@ManagedBean
@RequestScoped
public class orders_not_delivered {
    @EJB
    private SessionBeanRemote sessionBean;

    /** Creates a new instance of orders_not_delivered */
    public orders_not_delivered() {
    }
private Date from_date;
private Date to_date;
private DataTable dt;
private List<CrmkOrdersNotDelivered> orders_not_delivered_list=new ArrayList<CrmkOrdersNotDelivered>();

    public DataTable getDt() {
        return dt;
    }

    public void setDt(DataTable dt) {
        this.dt = dt;
    }

    public List<CrmkOrdersNotDelivered> getOrders_not_delivered_list() {
        return orders_not_delivered_list;
    }

    public void setOrders_not_delivered_list(List<CrmkOrdersNotDelivered> orders_not_delivered_list) {
        this.orders_not_delivered_list = orders_not_delivered_list;
    }
    /** Creates a new instance of orders_not_paied */
  

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public Date getTo_date() {
        return to_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }
    public void search(ActionEvent ae)
    {
     FacesContext fc = FacesContext.getCurrentInstance();
     ValueBinding vb = fc.getApplication().createValueBinding("#{loginBean.username}");
     String usercode = vb.getValue(fc).toString();
     SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
     if(from_date==null)
      try{
       from_date=sdf.parse("20000101");
       }
       catch(ParseException e){e.printStackTrace();}
     if(to_date==null)
      try{
       to_date=sdf.parse("30000101");
       }
       catch(ParseException e){e.printStackTrace();}
       HrEmpInfo hrEmpInfo=sessionBean.finduserbyid(Long.parseLong(usercode));
     if(hrEmpInfo.getJobId()==14 || hrEmpInfo.getJobId()==15 || hrEmpInfo.getJobId()==64)
     orders_not_delivered_list=sessionBean.getOrdersNotDelivered(from_date,to_date, null, hrEmpInfo.getLocId());
     else
     orders_not_delivered_list=sessionBean.getOrdersNotDelivered(from_date, to_date, Long.parseLong(usercode), null);
     dt.setValue(orders_not_delivered_list);
    }
}
