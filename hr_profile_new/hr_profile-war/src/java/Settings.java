/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpHd;
import e.HrEmpInfo;
import e.HrProfileAccessLog;
import e.HrUsers;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import sb.SessionBeanLocal;

/**
 *
 * @author Administrator
 */
@ManagedBean
@ViewScoped
public class Settings implements Serializable {

    @EJB
    private SessionBeanLocal sessionBean;
    private List<Theme> themes;
    private String myTheme;
    private String usercode;
    private HrEmpInfo hrEmpInfo;
    private String newpassword;
    private String confirmnewpassword;
    private List<SelectItem> selectThemeList = new ArrayList<SelectItem>();
    private String automaticSwitch;
    @ManagedProperty("#{themeService}")
    private ThemeService service;

    @PostConstruct
    public void init() {

        themes = service.getThemes();
        for (Theme theme : themes) {
            selectThemeList.add(new SelectItem(theme.getName(), theme.getDisplayName()));
        }
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
        if (hrEmpInfo.getAutomaticSwitch() != null && hrEmpInfo.getAutomaticSwitch().equals("Y")) {
            automaticSwitch = "true";
        } else {
            automaticSwitch = "false";
            myTheme = hrEmpInfo.getTheme();
        }
    }

    /** Creates a new instance of Settings */
    public Settings() {
    }

    public void saveTheme(ActionEvent ae) {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (myTheme == null && automaticSwitch.equals("false")) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Œÿ√", "ÌÃ» ≈Œ Ì«— «·‰„ÿ «·À«»  «·–Ï  ›÷·Â"));
            return;
        }
        HrEmpHd hrEmpHd = sessionBean.findEmpById(Long.parseLong(usercode));
        if (automaticSwitch.equals("true")) {
            hrEmpHd.setAutomaticSwitch("Y");
            hrEmpHd.setTheme(null);
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String theme = service.getThemes().get(day - 1).getName();
            hrEmpInfo.setTheme(theme);
            hrEmpInfo.setAutomaticSwitch("Y");
        } else {
            hrEmpHd.setTheme(myTheme);
            hrEmpHd.setAutomaticSwitch(null);
            hrEmpInfo.setTheme(myTheme);
        }
        sessionBean.mergeHrEmpHd(hrEmpHd);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("hrEmpInfo", hrEmpInfo);
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ", " „ Õ›Ÿ «·‰„ÿ »‰Ã«Õ"));
    }

    public void change_password(ActionEvent ae) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage();
        boolean change_pass = false;
        if (confirmnewpassword != null && !confirmnewpassword.isEmpty() && newpassword != null && !newpassword.isEmpty()) {
            if (confirmnewpassword.equals(newpassword)) {
                sessionBean.update_emp_password(Long.parseLong(usercode), newpassword, null, null);
                System.out.println("hrEmpHd merged");
                msg = new FacesMessage(FacesMessage.SEVERITY_WARN, " „ »‰Ã«Õ", " „  €ÌÌ— ﬂ·„… «·„—Ê—");
                change_pass = true;
                confirmnewpassword = null;
                newpassword = null;
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Œÿ√  ", "ﬂ·„… «·„—Ê— ·«   Ê«›ﬁ „⁄  √ﬂÌœ ﬂ·„… «·„—Ê—");
                change_pass = false;
            }
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Œÿ√  ", "ÌÃ» ≈œŒ«· ﬂ·„… «·„—Ê—");
            change_pass = false;
        }
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("change_pass", change_pass);
    }

    public void onRadioChange(AjaxBehaviorEvent e){
        if(automaticSwitch.equals("true"))
            myTheme=null;
    }

    public String getMyTheme() {
        return myTheme;
    }

    public void setMyTheme(String myTheme) {
        this.myTheme = myTheme;
    }

    public List<SelectItem> getSelectThemeList() {
        return selectThemeList;
    }

    public void setSelectThemeList(List<SelectItem> selectThemeList) {
        this.selectThemeList = selectThemeList;
    }

    public ThemeService getService() {
        return service;
    }

    public void setService(ThemeService service) {
        this.service = service;
    }

    public String getConfirmnewpassword() {
        return confirmnewpassword;
    }

    public void setConfirmnewpassword(String confirmnewpassword) {
        this.confirmnewpassword = confirmnewpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getAutomaticSwitch() {
        return automaticSwitch;
    }

    public void setAutomaticSwitch(String automaticSwitch) {
        this.automaticSwitch = automaticSwitch;
    }

    
}
