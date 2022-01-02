/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrEmpMangers;
import e.HrEmpTime;
import e.HrLocation;
import e.HrProfileAccessLog;
import e.HrShift;
import e.HrShiftDt;
import e.HrUsers;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import sb.SessionBeanRemote;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import sb.SessionBeanLocal;

/**
 *
 * @author ahmed abbas
 */
@ManagedBean
@ViewScoped
public class shift_table_entry implements Serializable {

    @EJB
    private SessionBeanLocal sessionBean;
    private List<HrEmpMangers> mngEmpList = new ArrayList<HrEmpMangers>();
    private List<Object[]> shiftAppliedEmpList = new ArrayList<Object[]>();
    private List<SelectItem> shift_selected_list = new ArrayList<SelectItem>();
    private Date date_from;
    private Date date_to;
    private Long shift_id;
    private int rowIndex;
    private String usercode;
    private HrEmpInfo hrEmpInfo;
    private List<HrLocation> locList;
    private HrLocation selectedLoc;
    private HrLocation filteredLoc;
    private List<Object[]> shiftEmpListToPersist = new ArrayList<Object[]>();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @PostConstruct
    public void init() {
        Date now = new Date();
        System.out.println(now.toString());
        usercode = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usercode");
        hrEmpInfo = (HrEmpInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrEmpInfo");
        HrUsers hrUsers = (HrUsers) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrUsers");
        if (hrUsers != null) {
            HrProfileAccessLog hrProfileAccessLog = new HrProfileAccessLog();
            hrProfileAccessLog.setEmpNo(hrEmpInfo.getEmpNo());
            hrProfileAccessLog.setPageName(FacesContext.getCurrentInstance().getViewRoot().getViewId());
            hrProfileAccessLog.setTrnsDate(new Date());
            hrProfileAccessLog.setUserName(hrUsers.getUserName());
            sessionBean.persistProfileAccessLog(hrProfileAccessLog);
        }
        locList = sessionBean.findLocationList();
        List<HrShiftDt> shiftList = sessionBean.getShift(hrEmpInfo.getDeptId(), null);
        for (HrShiftDt hrShiftDt : shiftList) {
            shift_selected_list.add(new SelectItem(hrShiftDt.getHrShift().getId(), hrShiftDt.getHrShift().getName()));
        }
        mngEmpList = CashHandler.getMngEmployees().get(Long.parseLong(usercode));
        for (HrEmpMangers hrEmpMangers : mngEmpList) {
            String s3 = null;
            Long s1 = null;
            String s2 = null;
            Date d1 = null;
            Date d2 = null;
            Boolean b = null;
            Object[] o = {hrEmpMangers, d1, d2, s1, b, s2, s3};
            shiftAppliedEmpList.add(o);
        }
    }

    public List<Object[]> getShiftAppliedEmpList() {
        return shiftAppliedEmpList;
    }

    public void onSelectEmp(ActionEvent ae) {
        Object[] row = shiftAppliedEmpList.get(getRowIndex());
        Boolean x = (Boolean) row[4];
        if (x) {
            if (shift_id == null || date_to == null || date_from == null) {
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· Ã„Ì⁄ «·»Ì«‰« "));
                row[4] = (Object) Boolean.FALSE;
                return;
            }
            if (date_from.before(new Date())) {
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·« Ì„ﬂ‰ ≈œŒ«· «·‘Ì›  ·› —… ”«»ﬁ…"));
                row[4] = (Object) Boolean.FALSE;
                return;
            }
            if (date_to.before(date_from)) {
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· «·› —… »‘ﬂ· ’ÕÌÕ"));
                row[4] = (Object) Boolean.FALSE;
                return;
            }
            Calendar c = Calendar.getInstance();
            c.add(Calendar.YEAR, 1);
            if (date_to.after(c.getTime())) {
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·« Ì„ﬂ‰ ≈œŒ«· ÃœÊ· «·‘Ì›  ·√ﬂÀ— „‰ ”‰… ﬁ«œ„…"));
                row[4] = (Object) Boolean.FALSE;
                return;
            }
            HrShift hrShift = sessionBean.getShiftById(shift_id);
            row[3] = shift_id;
            row[5] = hrShift.getName();
            row[2] = date_to;
            row[1] = date_from;
            shiftEmpListToPersist.remove(row);
            shiftEmpListToPersist.add(row);
        } else {
            row[3] = null;
            row[5] = null;
            row[2] = null;
            row[4] = null;
            row[1] = null;
            row[6] = null;
            shiftEmpListToPersist.remove(row);
        }
    }

    public void save_shift_table(ActionEvent ae) {
        FacesContext fc = FacesContext.getCurrentInstance();
        for (Object[] o : shiftEmpListToPersist) {
            Date d1 = (Date) o[1];
            Date d2 = (Date) o[2];
            //Calendar c = Calendar.getInstance();
            //c.setTime(d2);
            //c.add(Calendar.DAY_OF_MONTH, 1);
            //d2 = c.getTime();
            HrEmpMangers hrEmpMangers = (HrEmpMangers) o[0];
            sessionBean.addShiftTable(hrEmpMangers.getEmpNo(), d1, d2, (Long) o[3], hrEmpInfo.getEmpNo());
            o[1] = null;
            o[2] = null;
            o[3] = null;
            o[4] = null;
            o[5] = null;
            o[6] = null;
        }
        fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ «·Õ›Ÿ", " „ Õ›Ÿ ÃœÊ· «·‘Ì› "));
        shiftEmpListToPersist.clear();
    }

    /** Creates a new instance of shift_table_entry */
    public shift_table_entry() {
    }

    public void print(ActionEvent ae) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {

        FacesContext fc = FacesContext.getCurrentInstance();
        if (date_from == null || date_to == null) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«·  «—ÌŒ › —… «·»ÕÀ"));
            return;
        }
        Connection connection = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String oracleURL = "jdbc:oracle:thin:@20.1.1.1:1521:cleodb";
            connection = DriverManager.getConnection(oracleURL, "hr", "hrorange");
            connection.setAutoCommit(false);
            Integer selected_row = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("row").toString());
            Object o[] = shiftAppliedEmpList.get(selected_row);
            HrEmpMangers hrEmpMangers = (HrEmpMangers) o[0];
            JasperPrint jasperPrint = null;
            JasperReport jasperReport = null;
            java.io.InputStream url = getClass().getResourceAsStream("/emp_shift_table.jrxml");
            JasperDesign design = JRXmlLoader.load(url);
            jasperReport = JasperCompileManager.compileReport(design);
            HashMap jasperParameter = new HashMap();
            jasperParameter.put("emp_no", Integer.parseInt(String.valueOf(hrEmpMangers.getEmpNo())));
            jasperParameter.put("from_date", date_from);
            jasperParameter.put("to_date", date_to);
            jasperParameter.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.FALSE);
            jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParameter, connection);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=shift_table.pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            httpServletResponse.getOutputStream().flush();
            httpServletResponse.getOutputStream().close();
            fc.responseComplete();

        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void printAll(ActionEvent ae) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {

        FacesContext fc = FacesContext.getCurrentInstance();
        if (date_from == null || date_to == null) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«·  «—ÌŒ › —… «·»ÕÀ"));
            return;
        }
        Connection connection = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String oracleURL = "jdbc:oracle:thin:@20.1.1.1:1521:cleodb";
            connection = DriverManager.getConnection(oracleURL, "hr", "hrorange");
            connection.setAutoCommit(false);
            JasperPrint jasperPrint = null;
            JasperReport jasperReport = null;
            java.io.InputStream url = getClass().getResourceAsStream("/shift_table_by_entry.jrxml");
            JasperDesign design = JRXmlLoader.load(url);
            jasperReport = JasperCompileManager.compileReport(design);
            HashMap jasperParameter = new HashMap();
            jasperParameter.put("entry_no", Integer.parseInt(hrEmpInfo.getEmpNo() + ""));
            jasperParameter.put("date_from", date_from);
            jasperParameter.put("date_to", date_to);
            jasperParameter.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.FALSE);
            jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParameter, connection);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=shift_table.pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            httpServletResponse.getOutputStream().flush();
            httpServletResponse.getOutputStream().close();
            fc.responseComplete();

        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public void updateSelectedLoc() {
        filteredLoc = selectedLoc;
        List<HrShiftDt> shiftList = sessionBean.getShift(hrEmpInfo.getDeptId(), filteredLoc.getId());
        shift_selected_list.clear();
        for (HrShiftDt hrShiftDt : shiftList) {
            shift_selected_list.add(new SelectItem(hrShiftDt.getHrShift().getId(), hrShiftDt.getHrShift().getName()));
        }
        shiftAppliedEmpList.clear();
        for (HrEmpMangers hrEmpMangers : mngEmpList) {
            if(!hrEmpMangers.getLocation().equals(filteredLoc.getName()))
                continue;
            String s3 = null;
            Long s1 = null;
            String s2 = null;
            Date d1 = null;
            Date d2 = null;
            Boolean b = null;
            Object[] o = {hrEmpMangers, d1, d2, s1, b, s2, s3};
            shiftAppliedEmpList.add(o);
        }
        
    }

    public void resetSearch(ActionEvent e){
        List<HrShiftDt> shiftList = sessionBean.getShift(hrEmpInfo.getDeptId(), null);
        shift_selected_list.clear();
        for (HrShiftDt hrShiftDt : shiftList) {
            shift_selected_list.add(new SelectItem(hrShiftDt.getHrShift().getId(), hrShiftDt.getHrShift().getName()));
        }
        shiftAppliedEmpList.clear();
        for (HrEmpMangers hrEmpMangers : mngEmpList) {
            String s3 = null;
            Long s1 = null;
            String s2 = null;
            Date d1 = null;
            Date d2 = null;
            Boolean b = null;
            Object[] o = {hrEmpMangers, d1, d2, s1, b, s2, s3};
            shiftAppliedEmpList.add(o);
        }
        date_from=null;
        date_to=null;
        selectedLoc=null;
        filteredLoc=null;
    }
    public void setShiftAppliedEmpList(List<Object[]> shiftAppliedEmpList) {
        this.shiftAppliedEmpList = shiftAppliedEmpList;
    }

    public List<SelectItem> getShift_selected_list() {
        return shift_selected_list;
    }

    public void setShift_selected_list(List<SelectItem> shift_selected_list) {
        this.shift_selected_list = shift_selected_list;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public Date getDate_from() {
        return date_from;
    }

    public void setDate_from(Date date_from) {
        this.date_from = date_from;
    }

    public Date getDate_to() {
        return date_to;
    }

    public void setDate_to(Date date_to) {
        this.date_to = date_to;
    }

    public Long getShift_id() {
        return shift_id;
    }

    public void setShift_id(Long shift_id) {
        this.shift_id = shift_id;
    }

    public HrLocation getFilteredLoc() {
        return filteredLoc;
    }

    public void setFilteredLoc(HrLocation filteredLoc) {
        this.filteredLoc = filteredLoc;
    }

    public List<HrLocation> getLocList() {
        return locList;
    }

    public void setLocList(List<HrLocation> locList) {
        this.locList = locList;
    }

    public HrLocation getSelectedLoc() {
        return selectedLoc;
    }

    public void setSelectedLoc(HrLocation selectedLoc) {
        this.selectedLoc = selectedLoc;
    }
}
