/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpHd;
import e.HrEmpInfo;
import e.HrProfileAccessLog;
import e.HrUsers;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import sb.SessionBeanLocal;

/**
 *
 * @author ahmed abbas
 */
@ManagedBean
@ViewScoped
public class emp_info {

    @EJB
    private SessionBeanLocal sessionBean;
    private String usercode;
    private String username;
    private String hiredate;
    private String birthdate;
    private String address;
    private String gender;
    private String nationality;
    private String military;
    private String phone;
    private String mobile;
    private String email;
    private String img;
    private String ident;
    private String id_date;
    private String id_from;
    private String ins_no;
    private String ins_office;
    private String ins_date;
    private List<Object[]> emp_education;
    private List<Object[]> emp_jobs;
    private List<Object[]> emp_eff;
    private String bank_branch;
    private String bank_account;
    private String bank_emp_id;
    private HrEmpInfo hrEmpInfo;
    private HrEmpHd hrEmpHd;

    @PostConstruct
    public void init()
    {
        hrEmpInfo=(HrEmpInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrEmpInfo");
        HrUsers hrUsers = (HrUsers) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrUsers");
        if (hrUsers != null) {
            HrProfileAccessLog hrProfileAccessLog = new HrProfileAccessLog();
            hrProfileAccessLog.setEmpNo(hrEmpInfo.getEmpNo());
            hrProfileAccessLog.setPageName(FacesContext.getCurrentInstance().getViewRoot().getViewId());
            hrProfileAccessLog.setTrnsDate(new Date());
            hrProfileAccessLog.setUserName(hrUsers.getUserName());
            sessionBean.persistProfileAccessLog(hrProfileAccessLog);
        }
        hrEmpHd = sessionBean.findempbyid(Long.parseLong(getUsercode()));
    }
    public emp_info() {
    }

    public HrEmpInfo getHrEmpInfo() {
        return hrEmpInfo;
    }

    public void setHrEmpInfo(HrEmpInfo hrEmpInfo) {
        this.hrEmpInfo = hrEmpInfo;
    }
    

    public String getAddress() {
        address = hrEmpInfo.getAddress();
        return address;
    }

    public String getBank_account() {
       
        try {
            bank_account = hrEmpHd.getBankEmpAccount();
        } catch (NullPointerException e) {
            bank_account = null;
        }
        return bank_account;
    }

    public void setBank_account(String bank_account) {
        this.bank_account = bank_account;
    }

    public String getBank_branch() {
        try {
            bank_branch = hrEmpHd.getHrBankBranch().getName();
        } catch (NullPointerException e) {
            bank_branch = null;
        }
        return bank_branch;
    }

    public void setBank_branch(String bank_branch) {
        this.bank_branch = bank_branch;
    }

    public String getBank_emp_id() {
        try {
            bank_emp_id = hrEmpHd.getBankEmpId();
        } catch (NullPointerException e) {
            bank_emp_id = null;
        }
        return bank_emp_id;
    }

    public void setBank_emp_id(String bank_emp_id) {
        this.bank_emp_id = bank_emp_id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthdate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            birthdate = sdf.format(hrEmpInfo.getBirthDate());
        } catch (NullPointerException e) {
        }
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        email = hrEmpHd.getEMail();
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Object[]> getEmp_education() {
        emp_education = sessionBean.findAllEducation(Long.parseLong(getUsercode()));
        return emp_education;
    }

    public void setEmp_education(List<Object[]> emp_education) {
        this.emp_education = emp_education;
    }

    public List<Object[]> getEmp_eff() {
        return emp_eff;
    }

    public void setEmp_eff(List<Object[]> emp_eff) {
        this.emp_eff = emp_eff;
    }

    public List<Object[]> getEmp_jobs() {
        emp_jobs = sessionBean.findAllJob(Long.parseLong(getUsercode()));
        return emp_jobs;
    }

    public void setEmp_jobs(List<Object[]> emp_jobs) {
        this.emp_jobs = emp_jobs;
    }

    public String getGender() {
        if (hrEmpHd.getGender().equals(String.valueOf(0))) {
            gender = "ÐßÑ";
        } else {
            gender = "ÃäËì";
        }
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHiredate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            hiredate = sdf.format(hrEmpInfo.getHireDate());
        } catch (NullPointerException e) {
        }
        return hiredate;
    }

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    public String getId_date() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            id_date = sdf.format(hrEmpHd.getIdDate());
        } catch (NullPointerException e) {
        }
        return id_date;
    }

    public void setId_date(String id_date) {
        this.id_date = id_date;
    }

    public String getId_from() {
        id_from = hrEmpHd.getIdFrom();
        return id_from;
    }

    public void setId_from(String id_from) {
        this.id_from = id_from;
    }

    public String getIdent() {
        ident = hrEmpHd.getIdent();
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public String getImg() {
        img = new String();
        if (getUsercode().length() > 0) {
            img = "/images/dynamic/?param=" + getUsercode();
        }
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIns_date() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            ins_date = sdf.format(hrEmpHd.getInsDate());
        } catch (NullPointerException e) {
        }
        return ins_date;
    }

    public void setIns_date(String ins_date) {
        this.ins_date = ins_date;
    }

    public String getIns_no() {
        try {
            ins_no = hrEmpHd.getInsNo().toString();
        } catch (NullPointerException e) {
        }
        return ins_no;
    }

    public void setIns_no(String ins_no) {
        this.ins_no = ins_no;
    }

    public String getIns_office() {
        try {
            ins_office = hrEmpHd.getHrInsuranceOffice().getName();
        } catch (NullPointerException e) {
        }
        return ins_office;
    }

    public void setIns_office(String ins_office) {
        this.ins_office = ins_office;
    }

    public String getMilitary() {
        try {
            military = hrEmpHd.getHrMilitarily().getName();
        } catch (NullPointerException e) {
            return null;
        }
        return military;
    }

    public void setMilitary(String military) {
        this.military = military;
    }

    public String getMobile() {
        mobile = hrEmpHd.getMobile();
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNationality() {
        nationality = hrEmpHd.getHrNationality().getName();
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPhone() {
        phone = hrEmpHd.getPhone();
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsercode() {
        if (usercode == null) {
            usercode = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usercode");
        }
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUsername() {
        username = hrEmpInfo.getEmpName();
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    
}
