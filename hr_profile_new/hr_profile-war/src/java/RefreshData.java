/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpHd;
import e.HrEmpInfo;
import e.HrProfileAccessLog;
import e.HrUsers;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import sb.SessionBeanLocal;

/**
 *
 * @author Administrator
 */
@ManagedBean
@RequestScoped
public class RefreshData {

    @EJB
    private SessionBeanLocal sessionBean;
    private Date changePasswordsDate;
    private HrEmpInfo hrEmpInfo;

    /** Creates a new instance of RefreshData */
    public RefreshData() {
    }

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
    }

    public void refreshMenu(ActionEvent e) {
        List<Object[]> privilageList = sessionBean.findAllPrivilagesByEmpNo(null, null, null, null, null, null);
        for (Object[] privilage : privilageList) {
            Jedis jedis = null;
            try {
                jedis = WorkerBean.pool.getResource();
                jedis.hdel("menu:" + privilage[1], new String[]{privilage[0].toString()});
            } finally {
                if (jedis != null) {
                    jedis.close();

                }
            }
        }

        addMessage();
    }

    public void refreshMsg(ActionEvent e) {
        List<HrEmpInfo> hrEmpInfoList = sessionBean.allHrEmpInfo();

        Jedis jedis = null;
        try {
            jedis = WorkerBean.pool.getResource();
            for (HrEmpInfo empInfo : hrEmpInfoList) {
                Pipeline p = jedis.pipelined();
                p.del("alerts:" + empInfo.getEmpNo());
                p.del("msgs:" + empInfo.getEmpNo());
                p.del("decisions:" + empInfo.getEmpNo());
                p.del("tasks:" + empInfo.getEmpNo());
                p.sync();
            }

        } finally {
            if (jedis != null) {
                jedis.close();

            }
        }
        addMessage();


    }

    public void refreshTamyozApprove(ActionEvent e) {
        CashHandler.setTamyozApprove(null);
        addMessage();
        //       CashHandler.fillCash();


    }

    public void refreshHierarchy(ActionEvent e) {
        //CashHandler.setHeadHierachy(null);
        addMessage();
        //      CashHandler.fillCash();


    }

    public void refreshApprove(ActionEvent e) {
        List<HrEmpInfo> hrEmpInfoList = sessionBean.allHrEmpInfo();
        Jedis jedis = null;
        try {
            jedis = WorkerBean.pool.getResource();

            for (HrEmpInfo empInfo : hrEmpInfoList) {
                Pipeline p = jedis.pipelined();
                p.del("managers:" + empInfo.getEmpNo());
                p.del("employees:" + empInfo.getEmpNo());
                p.sync();
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        addMessage();


    }

    public void refreshAll(ActionEvent e) {
        Jedis jedis = null;
        try {
            jedis = WorkerBean.pool.getResource();
            jedis.flushAll();
        } finally {
            if (jedis != null) {
                jedis.close();

            }
        }

        new Thread(new Runnable() {

            public void run() {
                List<HrEmpInfo> hrEmpInfoList = sessionBean.allHrEmpInfo();


                for (HrEmpInfo empInfo : hrEmpInfoList) {
                    CashHandler.fillCash(empInfo.getEmpNo());


                }
            }
        }).start();
        addMessage();


    }

    public void changePasswords(ActionEvent e) {
        List<HrEmpInfo> allEmp = sessionBean.allHrEmpInfo();


        for (HrEmpInfo emp : allEmp) {
            HrEmpHd hrEmpHd = sessionBean.findempbyid(emp.getEmpNo());
            hrEmpHd.setPasswordExpiryDate(changePasswordsDate);
            sessionBean.mergeHrEmpHd(hrEmpHd);


        }
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ «·Õ›Ÿ »‰Ã«Õ"));


    }

    public void addMessage() {
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „  ÕœÌÀ «·»Ì«‰«  »‰Ã«Õ"));


    }

    public Date getChangePasswordsDate() {
        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("trnsDate") != null) {
            String s = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("trnsDate");
            String dd = s.substring(0, s.indexOf('-'));
            String mm = s.substring(s.indexOf('-') + 1, s.lastIndexOf('-'));
            String yyyy = s.substring(s.lastIndexOf('-') + 1);
            Calendar c = Calendar.getInstance(new Locale("ar"));
            c.set(Integer.parseInt(yyyy), Integer.parseInt(mm) - 1, Integer.parseInt(dd));
            changePasswordsDate = c.getTime();


        }
        return changePasswordsDate;


    }

    public void setChangePasswordsDate(Date changePasswordsDate) {
        this.changePasswordsDate = changePasswordsDate;

    }
}
