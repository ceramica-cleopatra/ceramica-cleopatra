/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrMosqueCrmkReq;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
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
import sb.SessionBeanLocal;

/**
 *
 * @author data
 */
@ManagedBean
@ViewScoped
public class MosqueCrmkReqPrint {
    @EJB
    private SessionBeanLocal sessionBean;

    /** Creates a new instance of mosque_crmk_req_print */
    public MosqueCrmkReqPrint() {
    }
    private List<HrMosqueCrmkReq> hrMosqueCrmkReqList = new ArrayList<HrMosqueCrmkReq>();
    private HrMosqueCrmkReq selectedReport = new HrMosqueCrmkReq();
    @PostConstruct
    public void init(){
        hrMosqueCrmkReqList = sessionBean.getMosqueCrmkReqs();
    }

    public List<HrMosqueCrmkReq> getHrMosqueCrmkReqList() {
        return hrMosqueCrmkReqList;
    }

    public void setHrMosqueCrmkReqList(List<HrMosqueCrmkReq> hrMosqueCrmkReqList) {
        this.hrMosqueCrmkReqList = hrMosqueCrmkReqList;
    }


    public void updateMosqueCrmkReport(ActionEvent ae) {
        Integer selected_row = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("report").toString());
        selectedReport = hrMosqueCrmkReqList.get(selected_row);
        selectedReport.setPrintDate(new Date());
        selectedReport.setPrint("Y");
        sessionBean.updateHrMosqueCrmkReq(selectedReport);
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO," „ »‰Ã«Õ"," „ ÿ»«⁄… «· ﬁ—Ì— »‰Ã«Õ"));
    }



    public String print() throws JRException, SQLException, ClassNotFoundException, FileNotFoundException, IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        Connection connection = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String oracleURL = "jdbc:oracle:thin:@20.1.1.1:1521:cleodb";
            connection = DriverManager.getConnection(oracleURL, "hr", "hrorange");
            connection.setAutoCommit(false);
            JasperPrint jasperPrint = null;
            JasperReport jasperReport = null;
            java.io.InputStream url = getClass().getResourceAsStream("/mosque_crmk_rep.jrxml");
            JasperDesign design = JRXmlLoader.load(url);
            jasperReport = JasperCompileManager.compileReport(design);
            HashMap jasperParameter = new HashMap();
            jasperParameter.put("rep_id", Integer.parseInt(selectedReport.getId().toString()));
            jasperParameter.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.FALSE);

            jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParameter, connection);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
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

}
