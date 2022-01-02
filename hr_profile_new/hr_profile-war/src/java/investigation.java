/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrInvestigateDt;
import e.HrInvestigateEmp;
import e.HrInvestigateHd;
import e.HrProfileAccessLog;
import e.HrUsers;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import sb.SessionBeanLocal;

/**
 *
 * @author ahmed abbas
 */
@ManagedBean
@ViewScoped
public class investigation implements Serializable {

    @EJB
    private SessionBeanLocal sessionBean;
    private String usercode;
    private FacesContext fc;
    private HrInvestigateHd hrInvestigateHd = new HrInvestigateHd();
    private List<HrInvestigateDt> hrInvestigateDtList = new ArrayList<HrInvestigateDt>();
    private List<HrInvestigateDt> hrInvestigateDtList1 = new ArrayList<HrInvestigateDt>();
    private List<SelectItem> radio = new ArrayList<SelectItem>();
    private List<Object[]> radio_result = new ArrayList<Object[]>();
    private List<Object[]> slider_result = new ArrayList<Object[]>();
    private List<Object[]> radio_result_avg = new ArrayList<Object[]>();
    private String investigate_name;
    private String investigate_dt;
    private String q;
    private Long radio_val;
    private Double x;
    private HrEmpInfo hrEmpInfo;

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
        List<HrInvestigateHd> l=sessionBean.getHrInvestigateHd(new Date(), hrEmpInfo.getDeptId(), hrEmpInfo.getJobId(), hrEmpInfo.getJobGrpId(), hrEmpInfo.getLocId());
        if(!l.isEmpty()){
        hrInvestigateHd = l.get(0);
            if (sessionBean.chkInvestigate(hrInvestigateHd.getId(), Long.parseLong(usercode)) == 0L) {
                if (hrInvestigateHd.getType().toString().length() != 0 && hrInvestigateHd.getType() == 1) {
                    for (HrInvestigateDt hrInvestigateDt : hrInvestigateHd.getHrInvestigateDtList()) {
                        radio.add(new SelectItem(hrInvestigateDt.getId(), hrInvestigateDt.getQuestionText()));
                    }

                } else if (hrInvestigateHd.getType().toString().length() != 0 && hrInvestigateHd.getType() == 2) {
                    for (HrInvestigateDt hrInvestigateDt : hrInvestigateHd.getHrInvestigateDtList()) {
                        hrInvestigateDtList1.add(new HrInvestigateDt(hrInvestigateDt.getId(), hrInvestigateDt.getQuestionText(), 0L));
                    }
                }
            } else {
                if (hrInvestigateHd.getType().toString().length() != 0 && hrInvestigateHd.getType() == 1) {
                    radio_result = sessionBean.getTotalRadioResult(hrInvestigateHd);
                    x = Double.parseDouble(sessionBean.findTotalRadioCount(hrInvestigateHd).toString());
                    for (Object[] o : radio_result) {
                        Object[] oo = {Double.parseDouble(o[0].toString()) / x * 100, o[1]};
                        radio_result_avg.add(oo);
                    }
                } else {
                    slider_result = sessionBean.getTotalSliderResult(hrInvestigateHd);
                    x = Double.parseDouble(slider_result.toString());
                }
            }
        
        }
    }



    public void save_investigate(ActionEvent ae) {
        fc = FacesContext.getCurrentInstance();
        ValueBinding vb = fc.getApplication().createValueBinding("#{loginBean.username}");
        usercode = vb.getValue(fc).toString();
        HrEmpInfo hrEmpInfo = sessionBean.finduserbyid(Long.parseLong(usercode));
        hrInvestigateHd = sessionBean.getHrInvestigateHd(new Date(), hrEmpInfo.getDeptId(), hrEmpInfo.getJobId(), hrEmpInfo.getJobGrpId(), hrEmpInfo.getLocId()).get(0);
        if (sessionBean.chkInvestigate(hrInvestigateHd.getId(), Long.parseLong(usercode)) == 0L) {
            if (hrInvestigateHd.getType() == 1) {
                if (radio_val == null) {
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈Œ Ì«— √Õœ «·≈Ã«»« ");
                    fc.addMessage(null, fm);
                    return;
                }
                HrInvestigateEmp hrInvestigateEmp = new HrInvestigateEmp();
                if (sessionBean.getHrInvestigateEmpMax() == null) {
                    hrInvestigateEmp.setId(1L);
                } else {
                    hrInvestigateEmp.setId(sessionBean.getHrInvestigateEmpMax() + 1L);
                }
                HrInvestigateDt hrInvestigateDt = new HrInvestigateDt();
                hrInvestigateDt.setId(radio_val);
                hrInvestigateEmp.setEmpNo(Long.parseLong(usercode));
                hrInvestigateEmp.setHrInvestigateDt(hrInvestigateDt);
                sessionBean.persistHrInvestigateEmp(hrInvestigateEmp);
                radio_result = sessionBean.getTotalRadioResult(hrInvestigateHd);
                x = Double.parseDouble(sessionBean.findTotalRadioCount(hrInvestigateHd).toString());
                System.out.println("x=" + x);
                for (Object[] o : sessionBean.getTotalRadioResult(hrInvestigateHd)) {
                    System.out.println(Double.parseDouble(o[0].toString()));
                    System.out.println((Double.parseDouble(o[0].toString()) / x) * 100 + "%");
                    Object[] oo = {Double.parseDouble(o[0].toString()) / x * 100, o[1]};
                    radio_result_avg.add(oo);
                }
                radio = new ArrayList<SelectItem>();
                hrInvestigateDtList1 = new ArrayList<HrInvestigateDt>();
                hrInvestigateDtList = new ArrayList<HrInvestigateDt>();
            } else if (hrInvestigateHd.getType() == 2) {
                for (HrInvestigateDt hrInvestigateDt : hrInvestigateDtList1) {
                    HrInvestigateEmp hrInvestigateEmp = new HrInvestigateEmp();
                    if (sessionBean.getHrInvestigateEmpMax() == null) {
                        hrInvestigateEmp.setId(1L);
                    } else {
                        hrInvestigateEmp.setId(sessionBean.getHrInvestigateEmpMax() + 1L);
                    }
                    hrInvestigateEmp.setAnswer(hrInvestigateDt.getQuestionVal());
                    hrInvestigateEmp.setEmpNo(Long.parseLong(usercode));
                    hrInvestigateEmp.setHrInvestigateDt(hrInvestigateDt);
                    sessionBean.persistHrInvestigateEmp(hrInvestigateEmp);
                }
                slider_result = sessionBean.getTotalSliderResult(hrInvestigateHd);
                x = Double.parseDouble(sessionBean.findTotalRadioCount(hrInvestigateHd).toString());
                hrInvestigateDtList1 = new ArrayList<HrInvestigateDt>();
                hrInvestigateDtList = new ArrayList<HrInvestigateDt>();
            }
            FacesMessage fm = new FacesMessage(" „ »‰Ã«Õ", " „ «· ’ÊÌ  »‰Ã«Õ");
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            fc.addMessage(null, fm);
        }
    }

    public List<Object[]> getSlider_result() {
        return slider_result;
    }

    public void setSlider_result(List<Object[]> slider_result) {
        this.slider_result = slider_result;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public List<Object[]> getRadio_result_avg() {
        return radio_result_avg;
    }

    public void setRadio_result_avg(List<Object[]> radio_result_avg) {
        this.radio_result_avg = radio_result_avg;
    }

    public List<Object[]> getRadio_result() {
        return radio_result;
    }

    public void setRadio_result(List<Object[]> radio_result) {
        this.radio_result = radio_result;
    }

    public Long getRadio_val() {
        return radio_val;
    }

    public List<HrInvestigateDt> getHrInvestigateDtList1() {
        return hrInvestigateDtList1;
    }

    public void setHrInvestigateDtList1(List<HrInvestigateDt> hrInvestigateDtList1) {
        this.hrInvestigateDtList1 = hrInvestigateDtList1;
    }

    public void setRadio_val(Long radio_val) {
        this.radio_val = radio_val;
    }

    public List<SelectItem> getRadio() {
        return radio;
    }

    public void setRadio(List<SelectItem> radio) {
        this.radio = radio;
    }

    /** Creates a new instance of investigation */
    public investigation() {
    }

    public List<HrInvestigateDt> getHrInvestigateDtList() {
        return hrInvestigateDtList;
    }

    public String getInvestigate_dt() {
        return investigate_dt;
    }

    public void setInvestigate_dt(String investigate_dt) {
        this.investigate_dt = investigate_dt;
    }

    public String getInvestigate_name() {
        return investigate_name;
    }

    public void setInvestigate_name(String investigate_name) {
        this.investigate_name = investigate_name;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public void setHrInvestigateDtList(List<HrInvestigateDt> hrInvestigateDt) {
        this.hrInvestigateDtList = hrInvestigateDt;
    }

    public HrInvestigateHd getHrInvestigateHd() {
        return hrInvestigateHd;
    }

    public void setHrInvestigateHd(HrInvestigateHd hrInvestigateHd) {
        this.hrInvestigateHd = hrInvestigateHd;
    }

    
}
