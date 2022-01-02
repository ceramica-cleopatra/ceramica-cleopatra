/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.CrmkBranch;
import e.CrmkOrdersNotPaied;
import e.HrEmpInfo;
import java.io.Serializable;
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
import javax.faces.model.SelectItem;
import org.primefaces.component.datatable.DataTable;
import sb.SessionBeanRemote;

/**
 *
 * @author ahmed abbas
 */
@ManagedBean
@RequestScoped
public class all_ordrs_not_paied implements Serializable {
@EJB
private SessionBeanRemote sessionBean;
private Date from_date;
private Date to_date;
private DataTable dt;
private Long brn_id;
private List<SelectItem> crmkBranchList=new ArrayList<SelectItem>();

    public List<SelectItem> getCrmkBranchList() {
    for(CrmkBranch crmkBranch : sessionBean.getShow())
    {
    crmkBranchList.add(new SelectItem(crmkBranch.getId(),crmkBranch.getName()));
    }
        return crmkBranchList;
    }

    public void setCrmkBranchList(List<SelectItem> crmkBranchList) {
        this.crmkBranchList = crmkBranchList;
    }


    public Long getBrn_id() {
        return brn_id;
    }

    public void setBrn_id(Long brn_id) {
        this.brn_id = brn_id;
    }


    public DataTable getDt() {
        return dt;
    }

    public void setDt(DataTable dt) {
        this.dt = dt;
    }

    private List<CrmkOrdersNotPaied> orders_not_paied_list=new ArrayList<CrmkOrdersNotPaied>();

    public List<CrmkOrdersNotPaied> getOrders_not_paied_list() {
        return orders_not_paied_list;
    }

    public void setOrders_not_paied_list(List<CrmkOrdersNotPaied> orders_not_paied_list) {
        this.orders_not_paied_list = orders_not_paied_list;
    }
    /** Creates a new instance of orders_not_paied */
    public all_ordrs_not_paied() {
    }

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
        System.out.println("brn_id"+brn_id);
     orders_not_paied_list=sessionBean.getAllOrdrsNotPaied(brn_id, from_date, to_date);
     dt.setValue(orders_not_paied_list);
    }
}
