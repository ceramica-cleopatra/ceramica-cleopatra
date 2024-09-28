/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrBorrowFundRequest;
import e.HrBorrowHd;
import e.HrBorrowZamalaHd;
import e.HrEmpInfo;
import e.HrFundBorrowSetup;
import e.HrProfileAccessLog;
import e.HrProfileMsg;
import e.HrUsers;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionListener;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import sb.SessionBeanLocal;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.faces.bean.ViewScoped;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;
import redis.clients.jedis.Jedis;

/**
 *
 * @author Administrator
 */
@ManagedBean
@ViewScoped
public class FundBorrowMngConfirm {

    @EJB
    private SessionBeanLocal sessionBean;
    private HrEmpInfo hrEmpInfo;
    private List<HrBorrowFundRequest> requestsNeedConfirm;
    private HrBorrowFundRequest selectedRequest;
    private String reqStartDate;
    private Long month;
    private Long year;
    private Long budget;
    private Long remain;
    private List<HrBorrowZamalaHd> previousEmpFundBorrow = new ArrayList<HrBorrowZamalaHd>();
    private HrEmpInfo guarantee1 = new HrEmpInfo();
    private HrEmpInfo guarantee2 = new HrEmpInfo();
    private List<Object[]> fundBudgetList = new ArrayList<Object[]>();
    private HrFundBorrowSetup hrFundBorrowSetup = new HrFundBorrowSetup();

    @PostConstruct
    public void init() {
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
        requestsNeedConfirm = sessionBean.findFundBorrowRequestsNeedConfirm();
        Jedis jedis = null;
        try {
            jedis = WorkerBean.pool.getResource();
            if (jedis.hgetAll("msgs:" + hrEmpInfo.getEmpNo()) != null && !jedis.hgetAll("msgs:" + hrEmpInfo.getEmpNo()).isEmpty()) {
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, String> hrProfileMsgMap = jedis.hgetAll("msgs:" + hrEmpInfo.getEmpNo());
                for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                    try {
                        HrProfileMsg hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                        if (hrProfileMsg.getEntityName().equals("HrBorrowFundRequest")) {
                            hrProfileMsg.setReadDone('Y');
                            jedis.hset("msgs:" + hrEmpInfo.getEmpNo(), hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                            jedis.expire("msgs:" + hrEmpInfo.getEmpNo(), Integer.MAX_VALUE);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(FundBorrowMngConfirm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                sessionBean.updateReadDoneMsg("HrBorrowFundRequest", null, 'Y', hrEmpInfo.getEmpNo());
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        fundBudgetList = sessionBean.findFundBorrowMonthlyBudget();
        hrFundBorrowSetup = sessionBean.findBorrowSetup();
    }

    public String saveRequest(String flag) {
        selectedRequest.setMngNo(hrEmpInfo);
        if (year != null && month != null) {
            Calendar startDate = Calendar.getInstance();
            startDate.set(year.intValue(), month.intValue() - 1, 1);
            selectedRequest.setResStart(startDate.getTime());
        }
        if (flag.equals("1")) {
            selectedRequest.setMngConfirm('N');
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ —›÷ «·ÿ·» »‰Ã«Õ"));
            sendMessages(selectedRequest.getEmpNo().getEmpNo(), 1);
            sessionBean.updateReadDoneMsg("HrBorrowFundRequestResponsible", selectedRequest.getId(), 'Y', null);
            Long[] respArray = {hrFundBorrowSetup.getResponsibleCode(), hrFundBorrowSetup.getResponsible2(), hrFundBorrowSetup.getResponsible3()};
            for (int i = 0; i < respArray.length; i++) {
                if (respArray[i] == null) {
                    continue;
                }

                Jedis jedis = null;
                try {
                    jedis = WorkerBean.pool.getResource();
                    if (jedis.hgetAll("alerts:" + respArray[i]) != null && !jedis.hgetAll("alerts:" + respArray[i]).isEmpty()) {
                        Map<String, String> hrProfileMsgMap = jedis.hgetAll("alerts:" + respArray[i]);
                        ObjectMapper objectMapper = new ObjectMapper();
                        for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                            try {
                                HrProfileMsg hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                                if (hrProfileMsg.getEntityName().equals("HrBorrowFundRequestResponsible")) {
                                    hrProfileMsg.setReadDone('Y');
                                    jedis.hset("alerts:" + respArray[i], hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                                    jedis.expire("alerts:" + respArray[i], Integer.MAX_VALUE);
                                }
                            } catch (IOException ex) {
                                Logger.getLogger(FundBorrowMngConfirm.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }

                } finally {
                    if (jedis != null) {
                        jedis.close();
                    }
                }
            }
        } else if (flag.equals("2")) {

            if (selectedRequest.getResAmount() == null || selectedRequest.getResMonths() == null || selectedRequest.getResStart() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Œÿ√", "ÌÃ» ≈œŒ«· »œ√  «—ÌŒ «·”œ«œ Ê «·ﬁÌ„… Ê √‘Â— «·”œ«œ"));
                return null;
            }

            if (selectedRequest.getResMonths() > 12) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» √·« Ì Ã«Ê“ ⁄œœ ‘ÂÊ— «·”œ«œ 12 ‘Â—"));
                return null;
            }
            Calendar startDate = Calendar.getInstance();
            startDate.set(year.intValue(), month.intValue() - 1, 1);

            int chk = sessionBean.chkFundBorrow(selectedRequest.getEmpNo().getEmpNo(), selectedRequest.getGuarantee1().getEmpNo(), selectedRequest.getGuarantee2().getEmpNo(), selectedRequest.getResAmount(), selectedRequest.getResMonths(), hrEmpInfo.getTotSal(), startDate.getTime());
            if (chk == 1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ‹‰   ⁄œÏ „œ…  ⁄ÌÌ‰ «·„ÊŸ› ” … √‘Â— ⁄·Ï «·√ﬁ·"));
                return null;
            } else if (chk == 2) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", " „ «·Õ’Ê· ⁄·Ï ”·›… ·„ Ì „ ”œ«œÂ« »«·ﬂ«„· »⁄œ"));
                return null;
            } else if (chk == 3) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", " „ «·Õ’Ê· ⁄·Ï ”·›… ·„ Ì„— ⁄·Ï ≈‰ Â«¡ ”œ«œÂ« ”‰…"));
                return null;
            } else if (chk == 4) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "«·÷«„‰ «·√Ê· √Œ– ”·›… ·„ Ì „ ”œ«œÂ« »«·ﬂ«„·"));
                return null;
            } else if (chk == 5) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "«·÷«„‰ «·√Ê· ÷„‰ ”·›… ·„ Ì „ ”œ«œÂ« »«·ﬂ«„·"));
                return null;
            } else if (chk == 6) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "«·÷«„‰ «·À«‰Ï √Œ– ”·›… ·„ Ì „ ”œ«œÂ« »«·ﬂ«„·"));
                return null;
            } else if (chk == 7) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "«·÷«„‰ «·À«‰Ï ÷„‰ ”·›… ·„ Ì „ ”œ«œÂ« »«·ﬂ«„·"));
                return null;
            } else if (chk == 8) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "«·”·›…  ⁄œ  «·„»·€ «·„”„ÊÕ »Â"));
                //  return null;
            } else if (chk == 9) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", " „  ⁄œÏ «·„»·€ «·„”„ÊÕ »Â ·„Ã„Ê⁄ «·”·› Œ·«· › —… «· ⁄ÌÌ‰"));
                return null;
            } else if (chk == 10) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» √·« ÌﬂÊ‰ «· «—ÌŒ ﬁ»· »œ«Ì… «·‘Â— «·Õ«·Ï"));
                return null;
            }
            selectedRequest.setMngNo(hrEmpInfo);
            selectedRequest.setEmpConfirm(null);
            selectedRequest.setEmpConfirmDate(null);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ «·—œ ⁄·Ï «·ÿ·» »‰Ã«Õ"));
            sendMessages(selectedRequest.getEmpNo().getEmpNo(), 0);
        } else {
            selectedRequest.setMngConfirm('Y');
            selectedRequest.setMngConfirmDate(new Date());
            sessionBean.insertFundBorrow(selectedRequest.getId(), selectedRequest.getEmpNo().getEmpNo(), selectedRequest.getResAmount(), selectedRequest.getResStart(), selectedRequest.getResMonths(), selectedRequest.getGuarantee1().getEmpNo(), selectedRequest.getGuarantee2().getEmpNo(), selectedRequest.getDeptMng().getEmpNo(), selectedRequest.getG1Phone(), selectedRequest.getG2Phone(), selectedRequest.getDeptMngPhone());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „  ÃœÊ·… «·”·›… »‰Ã«Õ"));
            sendMessages(selectedRequest.getEmpNo().getEmpNo(), 1);
            sessionBean.updateReadDoneMsg("HrBorrowFundRequestResponsible", selectedRequest.getId(), 'Y', null);
            Long[] respArray = {hrFundBorrowSetup.getResponsibleCode(), hrFundBorrowSetup.getResponsible2(), hrFundBorrowSetup.getResponsible3()};
            for (int i = 0; i < respArray.length; i++) {
                if (respArray[i] == null) {
                    continue;
                }
                Jedis jedis = null;
                try {
                    jedis = WorkerBean.pool.getResource();
                    if (jedis.hgetAll("alerts:" + respArray[i]) != null && !jedis.hgetAll("alerts:" + respArray[i]).isEmpty()) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        Map<String, String> hrProfileMsgMap = jedis.hgetAll("alerts:" + respArray[i]);
                        for (String hrProfileMsgAsString : hrProfileMsgMap.values()) {
                            try {
                                HrProfileMsg hrProfileMsg = objectMapper.readValue(hrProfileMsgAsString, HrProfileMsg.class);
                                if (hrProfileMsg.getEntityName().equals("HrBorrowFundRequestResponsible")) {
                                    hrProfileMsg.setReadDone('Y');
                                    jedis.hset("alerts:" + respArray[i], hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), objectMapper.writeValueAsString(hrProfileMsg));
                                    jedis.expire("alerts:" + respArray[i], Integer.MAX_VALUE);
                                }
                            } catch (IOException ex) {
                                Logger.getLogger(FundBorrowMngConfirm.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }

                } finally {
                    if (jedis != null) {
                        jedis.close();
                    }
                }
            }
        }
        selectedRequest.setNewReplyFlag('N');
        sessionBean.mergeFundBorrow(selectedRequest);
        requestsNeedConfirm = sessionBean.findFundBorrowRequestsNeedConfirm();
        month = null;
        year = null;
        return null;
    }

    public void sendMessages(Long reciever, int type) {
        Connection connection = null;
        Session session = null;
        MessageProducer messageProducer = null;
        try {
            Context ctx = new InitialContext();
            ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("jms/profileMsgQueueFactory");
            Queue queue = (Queue) ctx.lookup("jms/profileMsgQueue");
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            messageProducer = session.createProducer(queue);
            ObjectMessage objectMessage = session.createObjectMessage();
            HrProfileMsg hrProfileMsg = new HrProfileMsg();

            hrProfileMsg.setSendDate(new Date());
            hrProfileMsg.setEmpNo(reciever);
            hrProfileMsg.setSenderNo(hrEmpInfo.getEmpNo());
            hrProfileMsg.setMsgId(selectedRequest.getId());
            hrProfileMsg.setEntityName("HrBorrowFundRequestResponsible");
            hrProfileMsg.setMsgType(2L);
            if (type == 1) {
                hrProfileMsg.setMsgApprove(selectedRequest.getMngConfirm());
            }
            objectMessage.setObject(hrProfileMsg);
            Jedis jedis = null;
            try {
                jedis = WorkerBean.pool.getResource();
                jedis.hset("msgs:" + reciever, hrProfileMsg.getEntityName() + "" + hrProfileMsg.getMsgType() + "" + hrProfileMsg.getEmpNo() + "" + hrProfileMsg.getMsgId(), new ObjectMapper().writeValueAsString(hrProfileMsg));
                jedis.expire("msgs:" + reciever, Integer.MAX_VALUE);
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
            messageProducer.send(objectMessage);
            System.out.println("message sent");
        } catch (IOException ex) {
            Logger.getLogger(FundBorrowMngConfirm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                messageProducer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void onSelectBorrow() {
        if (selectedRequest != null && selectedRequest.getResStart() != null) {
            System.out.println("selecteddddddddddddddddddddddd" + selectedRequest.getGuarantee1().getEmpName());
            month = Long.parseLong((selectedRequest.getResStart().getMonth() + 1) + "");
            year = Long.parseLong((selectedRequest.getResStart().getYear() + 1900) + "");
        }
        previousEmpFundBorrow = sessionBean.findPreviousFundBorrowForEmp(selectedRequest.getEmpNo());
        reqStartDate = (selectedRequest.getReqStart().get(Calendar.MONTH) + 1) + "/" + selectedRequest.getReqStart().get(Calendar.YEAR);
        for (Object[] arr : fundBudgetList) {
            if (selectedRequest.getReqStart().get(Calendar.MONTH) + 1 == Integer.parseInt(arr[3] + "")
                    && selectedRequest.getReqStart().get(Calendar.YEAR) == Integer.parseInt(arr[4] + "")) {
                budget = Long.valueOf(arr[2] + "");
                remain = Long.valueOf(arr[2] + "") - Long.valueOf(arr[0] + "") - Long.valueOf(arr[7] + "");
            }
        }

    }

    public String print() throws JRException, SQLException, ClassNotFoundException, FileNotFoundException, IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        java.sql.Connection connection = null;
        String trnsMonth = fc.getExternalContext().getRequestParameterMap().get("trnsMonth");
        String trnsYear = fc.getExternalContext().getRequestParameterMap().get("trnsYear");
        System.out.println("trnsMonth" + trnsMonth);
        System.out.println("trnsYear" + trnsYear);
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String oracleURL = "jdbc:oracle:thin:@20.1.1.1:1521:cleodb";
            connection = DriverManager.getConnection(oracleURL, "hr", "hrorange");
            connection.setAutoCommit(false);
            JasperPrint jasperPrint = null;
            JasperReport jasperReport = null;
            java.io.InputStream url = getClass().getResourceAsStream("/hr_fund_borrow_details.jrxml");
            JasperDesign design = JRXmlLoader.load(url);
            jasperReport = JasperCompileManager.compileReport(design);
            HashMap jasperParameter = new HashMap();
            jasperParameter.put("trnsMonth", trnsMonth);
            jasperParameter.put("trnsYear", trnsYear);
            jasperParameter.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.FALSE);

            jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParameter, connection);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=borrow.pdf");
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

    public String printBorrowRequest() throws JRException, SQLException, ClassNotFoundException, FileNotFoundException, IOException {
        java.sql.Connection connection = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String oracleURL = "jdbc:oracle:thin:@20.1.1.1:1521:cleodb";
            connection = DriverManager.getConnection(oracleURL, "hr", "hrorange");
            connection.setAutoCommit(false);
            JasperPrint jasperPrint = null;
            JasperReport jasperReport = null;
            java.io.InputStream url = getClass().getResourceAsStream("/HrFundBorrowRequest.jrxml");
            JasperDesign design = JRXmlLoader.load(url);
            jasperReport = JasperCompileManager.compileReport(design);

            HashMap jasperParameter = new HashMap();
            jasperParameter.put("borrow_id", Integer.parseInt(selectedRequest.getId().toString()));
            jasperParameter.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.FALSE);
            jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParameter, connection);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=borrow_request.pdf");
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

    public String findGuaranteeDetails() {
        String g = "";
        guarantee1 = null;
        g = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("g1");
        if (g != null) {
            guarantee1 = sessionBean.finduserbyid(Long.parseLong(g));
        }
        g = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("g2");
        if (g != null) {
            guarantee2 = sessionBean.finduserbyid(Long.parseLong(g));
        }
        return null;
    }

    public String updateMonthlyBudget() {
        fundBudgetList = sessionBean.findFundBorrowMonthlyBudget();
        return null;
    }

    public HrEmpInfo getGuarantee1() {
        return guarantee1;
    }

    public void setGuarantee1(HrEmpInfo guarantee1) {
        this.guarantee1 = guarantee1;
    }

    public HrEmpInfo getGuarantee2() {
        return guarantee2;
    }

    public void setGuarantee2(HrEmpInfo guarantee2) {
        this.guarantee2 = guarantee2;
    }

    /** Creates a new instance of FundBorrowMngConfirm */
    public FundBorrowMngConfirm() {
    }

    public List<HrBorrowFundRequest> getRequestsNeedConfirm() {
        return requestsNeedConfirm;
    }

    public void setRequestsNeedConfirm(List<HrBorrowFundRequest> requestsNeedConfirm) {
        this.requestsNeedConfirm = requestsNeedConfirm;
    }

    public HrBorrowFundRequest getSelectedRequest() {
        if (selectedRequest != null && selectedRequest.getReqStart() != null) {
            reqStartDate = (selectedRequest.getReqStart().get(Calendar.MONTH) + 1) + "/" + selectedRequest.getReqStart().get(Calendar.YEAR);
        } else if (selectedRequest == null) {
            selectedRequest = new HrBorrowFundRequest();
        }
        return selectedRequest;
    }

    public void setSelectedRequest(HrBorrowFundRequest selectedRequest) {
        this.selectedRequest = selectedRequest;
    }

    public String getReqStartDate() {
        return reqStartDate;
    }

    public void setReqStartDate(String reqStartDate) {
        this.reqStartDate = reqStartDate;
    }

    public Long getMonth() {
        return month;
    }

    public void setMonth(Long month) {
        this.month = month;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public List<HrBorrowZamalaHd> getPreviousEmpFundBorrow() {
        return previousEmpFundBorrow;
    }

    public void setPreviousEmpFundBorrow(List<HrBorrowZamalaHd> previousEmpFundBorrow) {
        this.previousEmpFundBorrow = previousEmpFundBorrow;
    }

    public List<Object[]> getFundBudgetList() {
        return fundBudgetList;
    }

    public void setFundBudgetList(List<Object[]> fundBudgetList) {
        this.fundBudgetList = fundBudgetList;
    }

    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }

    public Long getRemain() {
        return remain;
    }

    public void setRemain(Long remain) {
        this.remain = remain;
    }
}
