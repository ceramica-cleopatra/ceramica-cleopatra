/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrProfileAccessLog;
import e.HrProfilePrivilage;
import e.HrUsers;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.context.RequestContext;



import sb.SessionBeanLocal;

/**
 *
 * @author ahmed abbas
 */
@ManagedBean()
@SessionScoped
public class LoginBean implements Serializable {

    @EJB
    private SessionBeanLocal sessionBean;
    private String username;
    private String empname;
    private String password;
    private String newpassword;
    private String confirmnewpassword;
    private String result = null;
    private HtmlOutputText question;
    private HtmlInputText answer;
    private HtmlOutputText pass;
    private HtmlOutputText answer_label;
    private CommandButton answer_button;
    private HtmlOutputText question_label;
    private String q;
    private String a;
    private String p;
    private HrEmpInfo hrEmpInfo;
    private String background;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd");
    private boolean isAdmin = false;

    @PostConstruct
    public void init() {
        // background="images/background/"+sdf.format(new Date())+".jpg";
        background = "images/0.jpg";
    }

    public LoginBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdminFlag() {
        System.out.println("isAdmin" + isAdmin);
        if(isAdmin)
            isAdmin=false;
        else
            isAdmin=true;
    }

    public String login() {
        FacesMessage msg = null;
        int usercode;
        username = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("logInFRM:username");
        password = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("logInFRM:password");
        empname = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("logInFRM:empname");
        if (username.length() == 0) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√ ›Ï «·œŒÊ·", "ÌÕ» ≈œŒ«· ﬂÊœ «·„” Œœ„");
            RequestContext.getCurrentInstance().execute("btnReset()");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        } else if (password.length() == 0) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√ ›Ï «·œŒÊ·", "ÌÃ» ≈œŒ«· ﬂ·„… «·„—Ê—");
            RequestContext.getCurrentInstance().execute("btnReset()");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        } else if (isAdmin && empname.length() == 0) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√ ›Ï «·œŒÊ·", "ÌÃ» ≈œŒ«· ﬂÊœ «·„ÊŸ›");
            RequestContext.getCurrentInstance().execute("btnReset()");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        } else {
            if (isAdmin) {
                String userPassword = "";
                HrUsers hrUsers=null;
                try {
                    hrUsers = sessionBean.findHrUserByName(username.toUpperCase());
                    userPassword = hrUsers.getPassword();
                    if (!userPassword.equals(sessionBean.getDercrybtedHrPassword(password))) {
                        userPassword = "";
                    }
                } catch (Exception e) {
                    userPassword = "";
                }
                if (userPassword.isEmpty()) {
                    msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√ ›Ï «·œŒÊ·", "≈”„ «·„” Œœ„ √Ê ﬂ·„… «·„—Ê— €Ì— ’ÕÌÕ…");
                    RequestContext.getCurrentInstance().execute("btnReset()");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                    return null;
                } else {

                    try {
                        hrEmpInfo = sessionBean.finduserbyid(Long.parseLong(empname));
                        usercode = (int) hrEmpInfo.getEmpNo();
                        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
                      //  List<MenuModelDTO> menuBean=CashHandler.populateMenu();
                        sessionMap.put("usercode", String.valueOf(usercode));
                        sessionMap.put("hrEmpInfo", hrEmpInfo);
                        sessionMap.put("hrUsers", hrUsers);
                       // sessionMap.put("menuBean", menuBean);
                        HrProfileAccessLog hrProfileAccessLog=new HrProfileAccessLog();
                        hrProfileAccessLog.setEmpNo(hrEmpInfo.getEmpNo());
                        hrProfileAccessLog.setPageName(FacesContext.getCurrentInstance().getViewRoot().getViewId());
                        hrProfileAccessLog.setTrnsDate(new Date());
                        hrProfileAccessLog.setUserName(hrUsers.getUserName());
                        sessionBean.persistProfileAccessLog(hrProfileAccessLog);
                        CashHandler.fillCash();
                        return "home.xhtml?faces-redirect=true";
                    } catch (Exception e) {
                        msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√ ›Ï «·œŒÊ·", "ﬂÊœ «·„ÊŸ› €Ì— ’ÕÌÕ");
                        RequestContext.getCurrentInstance().execute("btnReset()");
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                        return null;
                    }
                }
            } else {
                try {
                    hrEmpInfo = sessionBean.finduserbyid(Long.parseLong(username));
                    usercode = (int) hrEmpInfo.getEmpNo();
                    if (!hrEmpInfo.getPass().equals(password)) {
                        usercode = 0;
                    }
                } catch (Exception e) {
                    usercode = 0;
                }

                if (username != null && password != null && usercode > 0) {
                    Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
                    sessionMap.put("usercode", String.valueOf(usercode));
                    sessionMap.put("hrEmpInfo", hrEmpInfo);
                  //  List<MenuModelDTO> menuBean=CashHandler.populateMenu();
                  //  sessionMap.put("menuBean", menuBean);
                    //                 if (CashHandler.getMenuCashMap() == null || CashHandler.getMenuCashMap().size() == 0) {
                    CashHandler.fillCash();
                    //            }
                    return "home.xhtml?faces-redirect=true";
                } else {
                    msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√ ›Ï «·œŒÊ·", "ﬂÊœ «·„ÊŸ› √Ê ﬂ·„… «·„—Ê— €Ì— ’ÕÌÕ…");
                    RequestContext.getCurrentInstance().execute("btnReset()");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }

            }
            return null;
        }
        
    }

    public String compress() {
        try {

            new ImageCompressor().compress("D:\\E\\hr_profile_new\\hr_profile-war\\web\\images\\background\\14.jpg");
            new ImageCompressor().compress("D:\\E\\hr_profile_new\\hr_profile-war\\web\\images\\background\\15.jpg");
            new ImageCompressor().compress("D:\\E\\hr_profile_new\\hr_profile-war\\web\\images\\background\\29.jpg");
            new ImageCompressor().compress("D:\\E\\hr_profile_new\\hr_profile-war\\web\\images\\background\\31.jpg");
            return null;
        } catch (IOException ex) {
            System.out.println("exception found");
            ex.printStackTrace();
            return null;
        }
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setConfirmnewpassword(String confirmnewpassword) {
        this.confirmnewpassword = confirmnewpassword;
    }

    public String getConfirmnewpassword() {
        return confirmnewpassword;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void onComplete() {

        try {
            FacesContext.getCurrentInstance().getExternalContext().dispatch("success");
        } catch (IOException e) {
        }
    }

    public void setQuestion(HtmlOutputText question) {
        this.question = question;
    }

    public HtmlOutputText getQuestion() {
        return question;
    }

    public void setAnswer(HtmlInputText answer) {
        this.answer = answer;
    }

    public HtmlInputText getAnswer() {
        return answer;
    }

    public void setAnswer_label(HtmlOutputText answer_label) {
        this.answer_label = answer_label;
    }

    public HtmlOutputText getAnswer_label() {
        return answer_label;
    }

    public void setQuestion_label(HtmlOutputText question_label) {
        this.question_label = question_label;
    }

    public HtmlOutputText getQuestion_label() {
        return question_label;
    }

    public CommandButton getAnswer_button() {
        return answer_button;
    }

    public void setAnswer_button(CommandButton answer_button) {
        this.answer_button = answer_button;
    }

    public void setPass(HtmlOutputText pass) {
        this.pass = pass;
    }

    public HtmlOutputText getPass() {
        return pass;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getP() {
        return p;
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        final HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        request.getSession().invalidate();
        return "index.xhtml?faces-redirect=true";
    }

    public HrEmpInfo getHrEmpInfo() {
        return hrEmpInfo;
    }

    public void setHrEmpInfo(HrEmpInfo hrEmpInfo) {
        this.hrEmpInfo = hrEmpInfo;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }
}
