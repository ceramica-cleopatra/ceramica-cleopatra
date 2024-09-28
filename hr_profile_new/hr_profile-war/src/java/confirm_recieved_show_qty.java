/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.CrmkCMe2ordrHd;
import e.CrmkShowRecivRmndrQDt;
import e.CrmkShowRecivRmndrQHd;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.RowEditEvent;
import sb.SessionBeanLocal;
import sb.SessionBeanRemote;

/**
 *
 * @author Administrator
 */
@ManagedBean
@ViewScoped
public class confirm_recieved_show_qty implements Serializable {

    @EJB
    private SessionBeanLocal sessionBean;
    private List<CrmkShowRecivRmndrQHd> crmkShowRecivRmndrQHdList = new ArrayList<CrmkShowRecivRmndrQHd>();
    private Long deptId;
    private CrmkShowRecivRmndrQDt selectedRow;

    public CrmkShowRecivRmndrQDt getSelectedRow() {
        if (selectedRow != null) {
            System.out.println(selectedRow.getDtId() + "   " + selectedRow.getRecivQty());
        }
        return selectedRow;
    }

    public void setSelectedRow(CrmkShowRecivRmndrQDt selectedRow) {
        if (selectedRow != null) {
            System.out.println(selectedRow.getDtId() + "   " + selectedRow.getRecivQty());
        }
        this.selectedRow = selectedRow;
    }

    /** Creates a new instance of confirm_recieved_show_qty */
    public confirm_recieved_show_qty() {
    }

    public List<CrmkShowRecivRmndrQHd> getCrmkShowRecivRmndrQHdList() {
        if (crmkShowRecivRmndrQHdList.isEmpty() == true) {
            System.out.println("getCrmkShowRecivRmndrQHdList");
            resetCrmkShowRecivRmndrQHd();
        }
        return crmkShowRecivRmndrQHdList;
    }

    public void setCrmkShowRecivRmndrQHdList(List<CrmkShowRecivRmndrQHd> crmkShowRecivRmndrQHdList) {
        this.crmkShowRecivRmndrQHdList = crmkShowRecivRmndrQHdList;
    }

    public Long getDeptId() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ValueBinding vb = fc.getApplication().createValueBinding("#{loginBean.username}");
        Long usercode = Long.parseLong(vb.getValue(fc).toString());
        deptId = sessionBean.finduserbyid(usercode).getDeptId();
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public void save(ActionEvent ae) {
        String show_indx = null;
        String show_dt_indx = null;
        try {
            show_indx = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("show_indx").toString().substring(22, FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("show_indx").toString().indexOf(","));
            show_dt_indx = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("show_dt_indx").toString();
        } catch (NullPointerException e) {
        }
        FacesContext fc = FacesContext.getCurrentInstance();
        ValueBinding vb = fc.getApplication().createValueBinding("#{loginBean.username}");
        Long usercode = Long.parseLong(vb.getValue(fc).toString());
        CrmkShowRecivRmndrQDt crmkShowRecivRmndrQDt = crmkShowRecivRmndrQHdList.get(Integer.parseInt(show_indx)).getCrmkShowRecivRmndrQDtList().get(Integer.parseInt(show_dt_indx));
        if ((getDeptId() == 19 && crmkShowRecivRmndrQDt.getRecivQty() == null) || (getDeptId() == 34 && crmkShowRecivRmndrQDt.getRmndrQty() == null)) {
            fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("·„ Ì „ «·Õ›Ÿ", "ÌÃ» ≈œŒ«· «·ﬂ„Ì…"));
            resetCrmkShowRecivRmndrQHd();
            return;
        } else if ((crmkShowRecivRmndrQDt.getRecivQty() != null && crmkShowRecivRmndrQDt.getRecivQty() > crmkShowRecivRmndrQDt.getMe2ordrQty())
                || (crmkShowRecivRmndrQDt.getRmndrQty() != null && crmkShowRecivRmndrQDt.getRmndrQty() > crmkShowRecivRmndrQDt.getMe2ordrQty())) {
            fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("·„ Ì „ «·Õ›Ÿ", "«·ﬂ„Ì… √ﬂ»— „‰ «·ﬂ„Ì… «·„’—Ê›…"));
            resetCrmkShowRecivRmndrQHd();
            return;
        } else if (getDeptId() == 19 && crmkShowRecivRmndrQDt.getRecivQty() != null) {
            sessionBean.updateCrmkShowRecivRmndrQty(crmkShowRecivRmndrQDt.getHdId().getId(), crmkShowRecivRmndrQDt.getDtId(), usercode, null, crmkShowRecivRmndrQDt.getRecivQty(), null);
        } else if (getDeptId() == 34 && crmkShowRecivRmndrQDt.getRmndrQty() != null) {
            sessionBean.updateCrmkShowRecivRmndrQty(crmkShowRecivRmndrQDt.getHdId().getId(), crmkShowRecivRmndrQDt.getDtId(), null, usercode, null, crmkShowRecivRmndrQDt.getRmndrQty());
        }
        fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage(" „ «·Õ›Ÿ »‰Ã«Õ", " „ «·Õ›Ÿ »‰Ã«Õ"));
        resetCrmkShowRecivRmndrQHd();
    }

    public List<CrmkShowRecivRmndrQHd> resetCrmkShowRecivRmndrQHd() {
        if (getDeptId() == 19) {
            crmkShowRecivRmndrQHdList = sessionBean.getCrmkShowRecivRmndrQHdsReciev("1");
        } else if (getDeptId() == 34) {
            crmkShowRecivRmndrQHdList = sessionBean.getCrmkShowRecivRmndrQHdsRmndr(getIpAddress());
        }
        return crmkShowRecivRmndrQHdList;
    }

    public String getIpAddress() {
        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String reqIp = httpServletRequest.getHeader("x-forwarded-for");
        if (reqIp != null) {
            String[] ipArray = reqIp.split(",");
            for (int i = 0; i < ipArray.length; i++) {
                if (ipArray[i] != null && !ipArray[i].isEmpty() && !ipArray[i].trim().contains("20.1.1.46")) {
                    reqIp = ipArray[i];
                    break;
                }
            }
        }
        if (reqIp == null || "".equals(reqIp)) {
            reqIp = httpServletRequest.getRemoteAddr();
        }
        String ipSec = reqIp.substring(5, reqIp.length()).toString();
        int counter = ipSec.indexOf(".");
        ipSec = ipSec.substring(0, counter).toString();
        return ipSec;
    }
}
