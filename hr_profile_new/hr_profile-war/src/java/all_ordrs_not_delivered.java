/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.CrmkBranch;
import e.CrmkOrdersNotDelivered;
import e.HrEmpInfo;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.component.datatable.DataTable;
import sb.SessionBeanRemote;
import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import  org.apache.poi.hssf.usermodel.HSSFRow;
import  org.apache.poi.hssf.usermodel.HSSFCell;
/**
 *
 * @author ahmed abbas
 */
@ManagedBean
@RequestScoped
public class all_ordrs_not_delivered implements Serializable
{
    @EJB
    private SessionBeanRemote sessionBean;

    /** Creates a new instance of orders_not_delivered */
    public all_ordrs_not_delivered() {
    }
private Date from_date;
private Date to_date;
private DataTable dt;
private Long brn_id;
public final static int  DEFAULT_BUFFER_SIZE=1000;
private List<CrmkOrdersNotDelivered> orders_not_delivered_list=new ArrayList<CrmkOrdersNotDelivered>();
private List<SelectItem> crmkBranchList=new ArrayList<SelectItem>();

    public Long getBrn_id() {
        return brn_id;
    }

    public void setBrn_id(Long brn_id) {
        this.brn_id = brn_id;
    }

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
     orders_not_delivered_list=sessionBean.getAllOdrdsNotDelivered(brn_id, from_date, to_date);
     dt.setValue(orders_not_delivered_list);
    }

public void export_to_excel(ActionEvent ae)
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
 orders_not_delivered_list=sessionBean.getAllOdrdsNotDelivered(brn_id, from_date, to_date);
 dt.setValue(orders_not_delivered_list);



try{
String filename="/opt/web/Hr/orders_not_delivered/data.xls" ;
HSSFWorkbook hwb=new HSSFWorkbook();
HSSFSheet sheet =  hwb.createSheet("new sheet");

HSSFRow rowhead=   sheet.createRow((short)0);
rowhead.createCell((short) 0).setCellValue("Ordr No");
rowhead.createCell((short) 1).setCellValue("Type");
rowhead.createCell((short) 2).setCellValue("Trns Date");
rowhead.createCell((short) 3).setCellValue("Customer Name");
rowhead.createCell((short) 4).setCellValue("Customer Phone");
rowhead.createCell((short) 5).setCellValue("Item");
rowhead.createCell((short) 6).setCellValue("Qty");
rowhead.createCell((short) 7).setCellValue("Employee");
rowhead.createCell((short) 8).setCellValue("Location");
rowhead.createCell((short) 9).setCellValue("Trns Ordr No");
rowhead.createCell((short) 10).setCellValue("Driver");
rowhead.createCell((short) 11).setCellValue("Driver Phone No");
rowhead.createCell((short) 12).setCellValue("Delivery Date");
rowhead.createCell((short) 13).setCellValue("Customer Group");
int i=1;
SimpleDateFormat sdf1=new SimpleDateFormat("dd/MM/yyyy");
   
for (CrmkOrdersNotDelivered ordr : orders_not_delivered_list)
{
   HSSFRow row=   sheet.createRow((short)i);
    System.out.println("i="+i);
   row.createCell((short) 0).setCellValue(String.valueOf(ordr.getOrdrNo()));
   row.createCell((short) 1).setCellValue(ordr.getCrmkSehy());
   row.createCell((short) 2).setCellValue(sdf1.format(ordr.getTrnsDate()));
   row.createCell((short) 3).setCellValue(ordr.getClntName());
   row.createCell((short) 4).setCellValue(ordr.getPhone1());
   row.createCell((short) 5).setCellValue(ordr.getNoCTone());
   row.createCell((short) 6).setCellValue(String.valueOf(ordr.getQty()));
    
   if(ordr.getHrId()!=null)
   row.createCell((short) 7).setCellValue(ordr.getHrId().getEmpName());
   else
   row.createCell((short) 7).setCellValue("");
   
   row.createCell((short) 8).setCellValue(ordr.getBranchName());
   
   if(ordr.getTrnsNo()!=null)
   row.createCell((short) 9).setCellValue(ordr.getTrnsNo());
   else
   row.createCell((short) 9).setCellValue("");
   
   if(ordr.getName()!=null)
   row.createCell((short) 10).setCellValue(ordr.getName());
   else
   row.createCell((short) 10).setCellValue("");
   
   if(ordr.getMobile()!=null)
   row.createCell((short) 11).setCellValue(ordr.getMobile());
   else
    row.createCell((short) 11).setCellValue("");
   
   if(ordr.getDeliveryDate()!=null)
   row.createCell((short) 12).setCellValue(sdf1.format(ordr.getDeliveryDate()));
   else
   row.createCell((short) 12).setCellValue("");
   if(ordr.getGrpId()!=null)
   row.createCell((short) 13).setCellValue(ordr.getGrpId());
   else
   row.createCell((short) 13).setCellValue("");
   i++;
}
    
FileOutputStream fileOut =  new FileOutputStream(filename);
hwb.write(fileOut);
fileOut.close();
System.out.println("Your excel file has been generated!");

ExternalContext externalContext = fc.getExternalContext();
HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
HashMap hm=new HashMap();

File file = new File("/opt/web/Hr/orders_not_delivered/", "data.xls");
BufferedInputStream input = null;
BufferedOutputStream output = null;
    
try {

    input = new BufferedInputStream(new FileInputStream(file), DEFAULT_BUFFER_SIZE);
    response.reset();
    response.setHeader("Content-Type", "application/vnd.ms-excel");
    response.setHeader("Content-Length", String.valueOf(file.length()));
    response.setHeader("Content-Disposition", "attachment; filename=\"" + "data.xls" + "\"");
    output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);

    byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
    int length;
    while ((length = input.read(buffer)) > 0) {
        output.write(buffer, 0, length);
    }

    output.flush();

} catch(IOException io){io.printStackTrace();}
finally {

    close(output);
    close(input);
}

        fc.responseComplete();

} catch ( Exception ex ) {
    System.out.println(ex);

}

}
   private static void close(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    
}
