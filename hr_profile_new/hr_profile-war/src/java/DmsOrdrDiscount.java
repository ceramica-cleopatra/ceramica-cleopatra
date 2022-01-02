/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.DmsTransportOrdrHd;
import e.DmsTrnsOrdrDt;
import e.HrEmpInfo;
import e.HrProfileAccessLog;
import e.HrUsers;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.BehaviorEvent;
import sb.SessionBeanLocal;

/**
 *
 * @author Administrator
 */
@ManagedBean
@ViewScoped
public class DmsOrdrDiscount {

    @EJB
    private SessionBeanLocal sessionBean;
    private HrEmpInfo hrEmpInfo;
    private Long ordrNo;
    private String clntName;
    private String address;
    private String cityName;
    private String areaName;
    private String mobile;
    private String tel1;
    private DmsTrnsOrdrDt dmsTrnsOrdrDt;
    private Double discountValue;
    private Double discountPercent;
    private Double finalPrice;
    private String notes;

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

    public void search(ActionEvent ae) {
        if (ordrNo == null) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· √„— «·‰ﬁ·"));
            return;
        }
        dmsTrnsOrdrDt = sessionBean.findDmsTrnsOrdrDt(hrEmpInfo.getLocId(), ordrNo, cityName, areaName, mobile, tel1, clntName, address);
        if (dmsTrnsOrdrDt == null || dmsTrnsOrdrDt.getId() == null) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·„ Ì „ «·⁄ÀÊ— ⁄·Ï »Ì«‰« "));
            return;
        }
        Date maxDate = new Date(System.currentTimeMillis() - 3600 * 1000);
        if(dmsTrnsOrdrDt.getTrnsDate().before(maxDate)){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", " „  Ã«Ê“ «·› —… «·„”„ÊÕ »Â« ··Œ’„..»—Ã«¡ „—«Ã⁄… «·√œ„‰"));
            return;
        }
        cityName = dmsTrnsOrdrDt.getGovernName();
        areaName = dmsTrnsOrdrDt.getRegionName();
        mobile = dmsTrnsOrdrDt.getMobile();
        tel1 = dmsTrnsOrdrDt.getTel1();
        address = dmsTrnsOrdrDt.getAddress();
        clntName = dmsTrnsOrdrDt.getClntName();
    }

    public void newSearch(ActionEvent ae) {
        cityName = null;
        ordrNo = null;
        areaName = null;
        mobile = null;
        tel1 = null;
        address = null;
        clntName = null;
        dmsTrnsOrdrDt = null;
        finalPrice = null;
        discountPercent = null;
        discountValue = null;
        notes = null;
    }

    public List<String> completeCityName(String query) {
        System.out.println("query city" + query);
        List<String> results = sessionBean.findGovernNameBySubstr(query);
        cityName = null;
        return results;
    }

    public List<String> completeAreaName(String query) {
        System.out.println("query area" + query + " city name " + cityName);
        List<String> results = new ArrayList<String>();
        if (cityName != null) {
            results = sessionBean.findRegionNameBySubstr(query, cityName);
        }
        areaName = null;
        return results;
    }

    public void updateCityForArea(AjaxBehaviorEvent be) {
        System.out.println("<<<<<city name>>>>>>" + cityName);
    }

    public void claculateFinalPrice(AjaxBehaviorEvent be) {
        if (dmsTrnsOrdrDt == null || dmsTrnsOrdrDt.getId() == null) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ»  ÕœÌœ √„— «·‰ﬁ·"));
            discountPercent = null;
            finalPrice = null;
            return;
        }
        if (discountValue > dmsTrnsOrdrDt.getListPrice()) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", " „  Ã«Ê“ ‰”»… «·Œ’„"));
            discountPercent = null;
            finalPrice = null;
            return;
        }
        if (discountValue % 5 > 0) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÕ» ≈œŒ«· „»·€ ’ÕÌÕ „ﬁ—» ·√ﬁ—» Œ„” Ã‰ÌÂ« "));
            discountPercent = null;
            finalPrice = null;
            return;
        }
        discountPercent = Double.valueOf(Math.round(discountValue / dmsTrnsOrdrDt.getListPrice() * 100));
        finalPrice = Double.valueOf(Math.round(dmsTrnsOrdrDt.getListPrice() - discountValue));
    }

    public void saveDiscount(ActionEvent ae) {
        if (dmsTrnsOrdrDt == null || dmsTrnsOrdrDt.getId() == null) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ»  ÕœÌœ √„— «·‰ﬁ·"));
            discountPercent = null;
            finalPrice = null;
            return;
        }

        if (discountValue == null) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ»  ÕœÌœ ﬁÌ„… «·Œ’„"));
            discountPercent = null;
            finalPrice = null;
            return;
        }

        if (discountValue > dmsTrnsOrdrDt.getListPrice()) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", " „  Ã«Ê“ ‰”»… «·Œ’„"));
            discountPercent = null;
            finalPrice = null;
            return;
        }
        if (discountValue % 5 > 0) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÕ» ≈œŒ«· „»·€ ’ÕÌÕ „ﬁ—» ·√ﬁ—» Œ„” Ã‰ÌÂ« "));
            discountPercent = null;
            finalPrice = null;
            return;
        }

        DmsTransportOrdrHd dmsTransportOrdrHd = sessionBean.findDmsOrdrHdById(dmsTrnsOrdrDt.getId());
        dmsTransportOrdrHd.setPrice(finalPrice);
        dmsTransportOrdrHd.setDiscountEmp(hrEmpInfo);
        dmsTransportOrdrHd.setPriceRmn(finalPrice - dmsTransportOrdrHd.getPricePayed());
        dmsTransportOrdrHd.setDiscount(dmsTrnsOrdrDt.getListPrice() - finalPrice);
        dmsTransportOrdrHd.setDiscountNotes(notes);
        sessionBean.mergeDmsTranOrdrHd(dmsTransportOrdrHd);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " „ »‰Ã«Õ", " „ ≈œŒ«· Œ’„ «·‰ﬁ· »‰Ã«Õ"));
        cityName = null;
        ordrNo = null;
        areaName = null;
        mobile = null;
        tel1 = null;
        address = null;
        clntName = null;
        dmsTrnsOrdrDt = null;
        finalPrice = null;
        discountPercent = null;
        discountValue = null;
        notes = null;
    }

    /** Creates a new instance of DmsOrdrDiscount */
    public DmsOrdrDiscount() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCityName() {
        System.out.println("getCityName:" + cityName);
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getClntName() {
        return clntName;
    }

    public void setClntName(String clntName) {
        this.clntName = clntName;
    }

    public HrEmpInfo getHrEmpInfo() {
        return hrEmpInfo;
    }

    public void setHrEmpInfo(HrEmpInfo hrEmpInfo) {
        this.hrEmpInfo = hrEmpInfo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getOrdrNo() {
        return ordrNo;
    }

    public void setOrdrNo(Long ordrNo) {
        this.ordrNo = ordrNo;
    }

    public SessionBeanLocal getSessionBean() {
        return sessionBean;
    }

    public void setSessionBean(SessionBeanLocal sessionBean) {
        this.sessionBean = sessionBean;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public DmsTrnsOrdrDt getDmsTrnsOrdrDt() {
        return dmsTrnsOrdrDt;
    }

    public void setDmsTrnsOrdrDt(DmsTrnsOrdrDt dmsTrnsOrdrDt) {
        this.dmsTrnsOrdrDt = dmsTrnsOrdrDt;
    }

    public Double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(Double discountValue) {
        this.discountValue = discountValue;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }
}
