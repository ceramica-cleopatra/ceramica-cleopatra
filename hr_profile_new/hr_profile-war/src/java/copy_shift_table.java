/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrEmpMangers;
import e.HrEmpTime;
import e.HrProfileAccessLog;
import e.HrUsers;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import redis.clients.jedis.Jedis;
import sb.SessionBeanLocal;
import sb.SessionBeanRemote;

/**
 *
 * @author Administrator
 */
@ManagedBean
@ViewScoped
public class copy_shift_table {

    @EJB
    private SessionBeanLocal sessionBean;
    /** Creates a new instance of copy_shift_table */
    private String emp;
    private String usercode;
    private HrEmpInfo hrEmpInfo;
    private Date original_period_from;
    private Date original_period_to;
    private Date new_period_from;
    private Date new_period_to;
    private int rowIndex;
    private SimpleDateFormat sdf_hour = new SimpleDateFormat("HH");
    private List<Object[]> shiftAppliedEmpList = new ArrayList<Object[]>();
    private List<HrEmpMangers> getMngEmpList = new ArrayList<HrEmpMangers>();
    private List<Object[]> shiftEmpListToPersist = new ArrayList<Object[]>();

    @PostConstruct
    public void init() {
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
        Map<String, String> hrEmpManagersMap = null;
        Jedis jedis = null;
        try {
            jedis = WorkerBean.pool.getResource();
            hrEmpManagersMap = jedis.hgetAll("employees:" + usercode);
        } finally {
            if (jedis != null) {
                jedis.close();

            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        for (String hrEmpManagersAsString : hrEmpManagersMap.values()) {
            Date d1 = null;
            Date d2 = null;
            Date d3 = null;
            Date d4 = null;
            Boolean b = null;
            String s = null;
            Object[] o = {hrEmpManagersAsString, d1, d2, b, d3, d4, s};
            shiftAppliedEmpList.add(o);
        }
    }

    public void copy(ActionEvent ae) {
        FacesContext fc = FacesContext.getCurrentInstance();
        for (Object[] o : shiftEmpListToPersist) {
            Date d1 = (Date) o[1];
            Date d2 = (Date) o[2];
            Date d3 = (Date) o[4];
            Date d4 = (Date) o[5];
            Calendar c = Calendar.getInstance();
            sessionBean.copyShiftTable((Long) o[0], d1, d3, d4);
            o[1] = null;
            o[2] = null;
            o[3] = null;
            o[4] = null;
            o[5] = null;
            o[6] = null;
        }
        fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ «·Õ›Ÿ", " „ ‰”Œ ÃœÊ· «·‘Ì› "));
        shiftEmpListToPersist.clear();
        getMngEmpList.clear();
    }

    public void setShiftAppliedEmpList(List<Object[]> shiftAppliedEmpList) {
        this.shiftAppliedEmpList = shiftAppliedEmpList;
    }

    public List<Object[]> getShiftAppliedEmpList() {
        return shiftAppliedEmpList;
    }

    public void onSelectEmp(ActionEvent ae) {
        Object[] row = shiftAppliedEmpList.get(getRowIndex());
        Boolean x = (Boolean) row[3];
        FacesContext fc = FacesContext.getCurrentInstance();
        if (x) {
            if (original_period_from == null || original_period_to == null || new_period_from == null || new_period_to == null) {

                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· Ã„Ì⁄ «·»Ì«‰« "));
                row[3] = (Object) Boolean.FALSE;
                return;
            }
            if (original_period_from.after(original_period_to) || new_period_from.after(new_period_to)) {

                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· › — Ï «·‰”Œ »ÿ—Ìﬁ… ’ÕÌÕ…"));
                row[3] = (Object) Boolean.FALSE;
                return;
            }
            if (new_period_from.before(new Date())) {
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·« Ì„ﬂ‰ ‰”Œ ‘Ì›  ·› —… ”«»ﬁ…"));
                row[3] = (Object) Boolean.FALSE;
                return;
            }

            if (Math.round((original_period_to.getTime() - original_period_from.getTime()) / 86400000) != Math.round((new_period_to.getTime() - new_period_from.getTime()) / 86400000)) {
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "«·› — Ì‰ €Ì— „ ”«ÊÌ Ì‰"));
                row[3] = (Object) Boolean.FALSE;
                return;
            }

            row[2] = original_period_to;
            row[1] = original_period_from;
            row[4] = new_period_from;
            row[5] = new_period_to;
            shiftEmpListToPersist.remove(row);
            HrEmpMangers hrEmpMangers = (HrEmpMangers) row[0];
            Long chk = sessionBean.chkShiftTableCopy(hrEmpMangers.getEmpNo(), original_period_from, original_period_to, new_period_from);

            if (chk == 1L) {
                row[6] = "Y";
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", " „ ≈œŒ«· ÃœÊ· «·‘Ì›  Œ·«· «·› —… «·„—«œ «·‰”Œ ›ÌÂ«"));
                row[3] = (Object) Boolean.FALSE;
            } else if (chk == 2L) {
                row[6] = "Y";
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÊÃœ »⁄÷ «·√Ì«„ ›Ï «·› —… «·„—«œ ‰”ŒÂ« ·„ Ì „ ≈œŒ«· ‘Ì›  »Â«"));
                row[3] = (Object) Boolean.FALSE;
            } else {
                shiftEmpListToPersist.add(row);
            }
        } else {
            row[1] = null;
            row[2] = null;
            row[3] = null;
            row[4] = null;
            row[5] = null;
            row[6] = null;
            shiftEmpListToPersist.remove(row);
        }
    }

    public void print(ActionEvent ae) throws JRException, SQLException, ClassNotFoundException, FileNotFoundException, IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (original_period_from == null || original_period_to == null) {
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
            jasperParameter.put("from_date", original_period_from);
            jasperParameter.put("to_date", original_period_to);
            System.out.println("emp_no" + jasperParameter.get("emp_no"));
            System.out.println("emp_no" + original_period_from);
            System.out.println("emp_no" + original_period_to);
            jasperParameter.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.FALSE);
            jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParameter, connection);
            //JasperExportManager.exportReportToPdfFile(jasperPrint,"e://report//"+crmkOrdrSader.getOrdrId().getId().toString()+".pdf");
            //downloadPDF("e:\\report\\",crmkOrdrSader.getOrdrId().getId().toString()+".pdf");
            HttpServletResponse resp = (HttpServletResponse) fc.getExternalContext().getResponse();
            resp.reset();
            resp.setContentType("application/pdf");
            String filename = new StringBuffer(String.valueOf(hrEmpMangers.getEmpNo())).append(".pdf").toString();
            resp.addHeader("Content-Disposition", "inline; filename=" + filename);
            JasperExportManager.exportReportToPdfStream(jasperPrint, resp.getOutputStream());
            resp.getOutputStream().flush();
            resp.getOutputStream().close();
            fc.responseComplete();

        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public List<Object[]> getShiftEmpListToPersist() {
        return shiftEmpListToPersist;
    }

    public void setShiftEmpListToPersist(List<Object[]> shiftEmpListToPersist) {
        this.shiftEmpListToPersist = shiftEmpListToPersist;
    }

    public copy_shift_table() {
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public String getEmp() {
        return emp;
    }

    public void setEmp(String emp) {
        this.emp = emp;
    }

    public Date getNew_period_from() {
        return new_period_from;
    }

    public void setNew_period_from(Date new_period_from) {
        this.new_period_from = new_period_from;
    }

    public Date getNew_period_to() {
        return new_period_to;
    }

    public void setNew_period_to(Date new_period_to) {
        this.new_period_to = new_period_to;
    }

    public Date getOriginal_period_from() {
        return original_period_from;
    }

    public void setOriginal_period_from(Date original_period_from) {
        this.original_period_from = original_period_from;
    }

    public Date getOriginal_period_to() {
        return original_period_to;
    }

    public void setOriginal_period_to(Date original_period_to) {
        this.original_period_to = original_period_to;
    }
}
