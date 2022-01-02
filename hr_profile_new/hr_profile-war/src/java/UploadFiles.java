/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrProfileAccessLog;
import e.HrUsers;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.FileUploadEvent;
import sb.SessionBeanLocal;

/**
 *
 * @author Administrator
 */
@ManagedBean
@SessionScoped
public class UploadFiles {

    @EJB
    private SessionBeanLocal sessionBean;
    private Long month;
    private Long year;

    /** Creates a new instance of UploadFiles */
    public UploadFiles() {
    }

    @PostConstruct
    public void init() {
        HrEmpInfo hrEmpInfo = (HrEmpInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrEmpInfo");
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

    public void copyFile(String fileName, InputStream in, String destination) {
        try {
            System.out.print(destination + fileName);
            OutputStream out = new FileOutputStream(new File(destination + fileName));
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();

            System.out.println("New file created!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleMedicalFileUpload(FileUploadEvent event) {
        System.out.println("try to copy");
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        try {

            System.out.println(event.getFile().getFileName());
            copyFile("medical_insurance.xls", event.getFile().getInputstream(), "/opt/web/Hr/");
            System.out.println("end copy");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void handlePoundRulesFileUpload(FileUploadEvent event) {
        System.out.println("try to copy");
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        try {

            System.out.println(event.getFile().getFileName());
            copyFile("rules.pdf", event.getFile().getInputstream(), "/opt/glassfish3/glassfish3/glassfish/domains/domain1/applications/hr_profile_new/hr_profile-war-new_war/resources/");
            System.out.println("end copy");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void handlePhoneListFileUpload(FileUploadEvent event) {
        System.out.println("try to copy");
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        try {

            System.out.println(event.getFile().getFileName());
            copyFile("phone_list.xls", event.getFile().getInputstream(), "/opt/web/Hr/");
            System.out.println("end copy");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void handleFundBalanceFileUpload(FileUploadEvent event) {
        System.out.println(event.getFile().getFileName());
        if (month == null || year == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· «·‘Â— Ê«·”‰…");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        System.out.println("try to copy");
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        try {

            System.out.println(event.getFile().getFileName());
            copyFile(month + "-" + year + ".jpg", event.getFile().getInputstream(), "/opt/web/Hr/fund_balance/");//"/opt/web/Hr/fund_balance/"
            month = null;
            year = null;
            System.out.println("end copy");
        } catch (IOException e) {
            e.printStackTrace();
        }

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
}
