/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.CrmkBranch;
import e.CrmkOrdrSader;
import e.HrEmpCrmkBranch;
import e.HrEmpInfo;
import e.HrProfileAccessLog;
import e.HrProfileMsg;
import e.HrUsers;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.codehaus.jackson.map.ObjectMapper;
import redis.clients.jedis.Jedis;
import sb.SessionBeanLocal;

/**
 *
 * @author ahmed abbas
 */
@ManagedBean
@SessionScoped
public class sader_store_print implements Serializable {

    @EJB
    private SessionBeanLocal sessionBean;
    private List<CrmkOrdrSader> crmkSaderOrdrNotPrintedList = new ArrayList<CrmkOrdrSader>();
    private List<CrmkOrdrSader> crmkSaderOrdrPrintedList = new ArrayList<CrmkOrdrSader>();
    public final static int DEFAULT_BUFFER_SIZE = 1000;
    private String usercode;
    private HrEmpInfo hrEmpInfo;
    private CrmkOrdrSader crmkOrdrSader = new CrmkOrdrSader();
    private String showroomName;
    private List<CrmkBranch> showRoomList;
    private CrmkBranch showroomSelected;
    private Long ordrNo;
    private Character crmkSehy;
    private String prdYear;

    @PostConstruct
    public void init() {
        System.setProperty("java.awt.headless", "true");
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
        usercode = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usercode");
        crmkSaderOrdrNotPrintedList = sessionBean.findCrmkOrdrSaderNotPrinted(hrEmpInfo.getLocId().equals(40L) ? 41L : hrEmpInfo.getLocId());
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        crmkSaderOrdrPrintedList = sessionBean.findCrmkOrdrSaderPrinted(usercode, c.getTime());
        showRoomList = sessionBean.getShow();
    }

    static {
        System.setProperty("java.awt.headless", "true");
    }

    public String search() {
        crmkSaderOrdrNotPrintedList = sessionBean.findCrmkOrdrSaderNotPrinted(hrEmpInfo.getLocId().equals(40L) ? 41L : hrEmpInfo.getLocId(), showroomSelected != null ? showroomSelected.getId() : null, ordrNo, crmkSehy);
        return null;
    }

    public String newSearch() {
        ordrNo = null;
        crmkSehy = null;
        showroomSelected = null;
        showroomName = null;
        crmkSaderOrdrNotPrintedList = sessionBean.findCrmkOrdrSaderNotPrinted(hrEmpInfo.getLocId().equals(40L) ? 41L : hrEmpInfo.getLocId());
        return null;
    }

    /** Creates a new instance of sader_store_print */
    public sader_store_print() {
    }

    public void updateOrderStatus(ActionEvent ae) {
        Integer selected_row = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ordr").toString());
        crmkOrdrSader = crmkSaderOrdrNotPrintedList.get(selected_row);
        crmkOrdrSader.setPrintDate(new Date());
        crmkOrdrSader.setPrinted("Y");
        crmkOrdrSader.setPrintEmpId(usercode);
        sessionBean.mergeCrmkOrdrSader(crmkOrdrSader);
        crmkSaderOrdrNotPrintedList = sessionBean.findCrmkOrdrSaderNotPrinted(hrEmpInfo.getLocId().equals(40L) ? 41L : hrEmpInfo.getLocId());
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        crmkSaderOrdrPrintedList = sessionBean.findCrmkOrdrSaderPrinted(usercode, c.getTime());
    }

    public void updateShowroomName() {
        showroomName = showroomSelected.getName();
    }

    public String print() throws JRException, SQLException, ClassNotFoundException, FileNotFoundException, IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        List<HrEmpCrmkBranch> hrEmpCrmkBranchs;
        try {
            hrEmpCrmkBranchs = sessionBean.findStoreManagers(crmkOrdrSader.getStoreId().getId());
        } catch (NullPointerException e) {
            hrEmpCrmkBranchs = null;
        }
        if (hrEmpCrmkBranchs != null && hrEmpCrmkBranchs.size() > 0) {
            Jedis jedis = null;
            try {
                jedis = WorkerBean.pool.getResource();
                for (HrEmpCrmkBranch hrEmpCrmkBranch : sessionBean.findStoreManagers(crmkOrdrSader.getStoreId().getId())) {
                    if (jedis.hgetAll("alerts:" + hrEmpCrmkBranch.getEmpNo()) != null && !jedis.hgetAll("alerts:" + hrEmpCrmkBranch.getEmpNo()).isEmpty()) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        Map<String, String> hrProfileMsgMap = jedis.hgetAll("alerts:" + hrEmpCrmkBranch.getEmpNo());
                        for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                            HrProfileMsg hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                            if (hrProfileMsg.getMsgId().equals(crmkOrdrSader.getId()) && hrProfileMsg.getEntityName().equals("CrmkOrdrSader")) {
                                hrProfileMsg.setReadDone('Y');
                                jedis.hset("alerts:" + hrEmpCrmkBranch.getEmpNo(), hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                                jedis.expire("alerts:" + hrEmpCrmkBranch.getEmpNo(), Integer.MAX_VALUE);
                            }
                        }

                    }
                }
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
            sessionBean.updateReadDoneMsg("CrmkOrdrSader", crmkOrdrSader.getId(), 'Y', null);
        }

        Connection connection = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String oracleURL = "jdbc:oracle:thin:@20.1.1.1:1521:cleodb";
            connection = DriverManager.getConnection(oracleURL, "hr", "hrorange");
            connection.setAutoCommit(false);
            //CrmkOrdrSader crmkOrdrSader = crmkSaderOrdrNotPrintedList.get(selected_row);
            String QTY_TXT = null;
            JasperPrint jasperPrint = null;
            JasperReport jasperReport = null;
            if (crmkOrdrSader.getOrdrId().getCrmkSehy() != 'S') {
                java.io.InputStream url = getClass().getResourceAsStream("/sader_crmk_store.jrxml");
                JasperDesign design = JRXmlLoader.load(url);
                jasperReport = JasperCompileManager.compileReport(design);
                if (crmkOrdrSader.getOrdrId().getCrmkSehy() == 'C') {
                    QTY_TXT = sessionBean.getMeterByTxt(sessionBean.getCrmkQtySum(crmkOrdrSader.getOrdrId().getId()));
                } else {
                    QTY_TXT = sessionBean.getMeterByTxt(sessionBean.getDcreQtySum(crmkOrdrSader.getOrdrId().getId()));
                }
            } else {
                java.io.InputStream url = getClass().getResourceAsStream("/sader_sehy_store.jrxml");
                JasperDesign design = JRXmlLoader.load(url);
                jasperReport = JasperCompileManager.compileReport(design);
                QTY_TXT = sessionBean.getPieceByTxt(sessionBean.getSehyQtySum(crmkOrdrSader.getOrdrId().getId()));
            }
            HashMap jasperParameter = new HashMap();
            jasperParameter.put("P_ID", Integer.parseInt(crmkOrdrSader.getOrdrId().getId().toString()));
            jasperParameter.put("QTY_TXT", QTY_TXT);
            jasperParameter.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.FALSE);

            jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParameter, connection);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=order.pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (JRException ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
        }
        FacesContext.getCurrentInstance().responseComplete();
        return null;
    }

    public void downloadPDF(String f_path, String f_name) throws IOException {
        // Prepare.
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        File file = new File(f_path, f_name);
        if (file.exists()) {
            System.out.println("file exists");
        } else {
            System.out.println("file not exists");
        }
        BufferedInputStream input = null;
        BufferedOutputStream output = null;
        try {
            // Open file.
            input = new BufferedInputStream(new FileInputStream(file), DEFAULT_BUFFER_SIZE);
            // Init servlet response.
            response.reset();
            response.setHeader("Content-Type", "application/pdf");
            response.setHeader("Content-Length", String.valueOf(file.length()));
            response.setHeader("Content-Disposition", "inline; filename=\"" + f_name + "\"");
            output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);

            // Write file contents to response.
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }

            // Finalize task.
            output.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            // Gently close streams.
            output.close();
            input.close();

        }

        // Inform JSF that it doesn't need to handle response.
        // This is very important, otherwise you will get the following exception in the logs:
        // java.lang.IllegalStateException: Cannot forward after response has been committed.
        facesContext.responseComplete();
    }

    private static void close(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
                // Do your thing with the exception. Print it, log it or mail it. It may be useful to
                // know that this will generally only be thrown when the client aborted the download.
                e.printStackTrace();
            }
        }
    }

    public List<CrmkOrdrSader> getCrmkSaderOrdrNotPrintedList() {
        return crmkSaderOrdrNotPrintedList;
    }

    public void setCrmkSaderOrdrNotPrintedList(List<CrmkOrdrSader> crmkSaderOrdrNotPrintedList) {
        this.crmkSaderOrdrNotPrintedList = crmkSaderOrdrNotPrintedList;
    }

    public List<CrmkOrdrSader> getCrmkSaderOrdrPrintedList() {
        return crmkSaderOrdrPrintedList;
    }

    public void setCrmkSaderOrdrPrintedList(List<CrmkOrdrSader> crmkSaderOrdrPrintedList) {
        this.crmkSaderOrdrPrintedList = crmkSaderOrdrPrintedList;
    }

    public CrmkOrdrSader getCrmkOrdrSader() {
        return crmkOrdrSader;
    }

    public void setCrmkOrdrSader(CrmkOrdrSader crmkOrdrSader) {
        this.crmkOrdrSader = crmkOrdrSader;
    }

    public Character getCrmkSehy() {
        return crmkSehy;
    }

    public void setCrmkSehy(Character crmkSehy) {
        this.crmkSehy = crmkSehy;
    }

    public Long getOrdrNo() {
        return ordrNo;
    }

    public void setOrdrNo(Long ordrNo) {
        this.ordrNo = ordrNo;
    }

    public String getPrdYear() {
        return prdYear;
    }

    public void setPrdYear(String prdYear) {
        this.prdYear = prdYear;
    }

    public List<CrmkBranch> getShowRoomList() {
        return showRoomList;
    }

    public void setShowRoomList(List<CrmkBranch> showRoomList) {
        this.showRoomList = showRoomList;
    }

    public String getShowroomName() {
        return showroomName;
    }

    public void setShowroomName(String showroomName) {
        this.showroomName = showroomName;
    }

    public CrmkBranch getShowroomSelected() {
        return showroomSelected;
    }

    public void setShowroomSelected(CrmkBranch showroomSelected) {
        this.showroomSelected = showroomSelected;
    }
}
