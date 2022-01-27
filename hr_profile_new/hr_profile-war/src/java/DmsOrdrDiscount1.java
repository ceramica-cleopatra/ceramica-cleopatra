/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.DmsTransportOrdrHd;
import e.DmsTransportOrdrParent;
import e.DmsTrnsOrdrDt;
import e.DmsUsers;
import e.HrEmpInfo;
import e.HrLocation;
import e.HrProfileAccessLog;
import e.HrUsers;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.BehaviorEvent;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import sb.SessionBeanLocal;

/**
 *
 * @author Administrator
 */

@ManagedBean
@ViewScoped
public class DmsOrdrDiscount1 {

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
    private String discountType;
    private Long attachedOrdrNo;
    private DmsTrnsOrdrDt atttachedDmsTrnsOrdrDt;
    private String attachedClntName;
    private String attachedAddress;
    private String attachedCityName;
    private String attachedAreaName;
    private String attachedMobile;
    private String attachedTel1;
    private boolean isAdmin=false;
    private List<SelectItem> showList;
    private Long locId;
    private boolean activeSave=false;
    private Double addFees;

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
        showList = new ArrayList<SelectItem>();
        DmsUsers dmsUsers = sessionBean.findDmsUserByHrId(hrEmpInfo.getEmpNo() + "");
        if (dmsUsers != null && dmsUsers.getDiscountPrivilage() != null && dmsUsers.getDiscountPrivilage().equals("Y")) {
            isAdmin = true;
            List<HrLocation> showes = sessionBean.findAllShowRooms();
            for (HrLocation show : showes) {
                showList.add(new SelectItem(show.getId(), show.getName()));
            }
        }
    }

    public void search(ActionEvent ae) {
        if (discountType == null) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ»  ÕœÌœ ‰Ê⁄ «·Œ’„"));
            return;
        }

        if (discountType.equals("1")) {

            if (isAdmin && (locId==null || locId == 0L)) {
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ»  ÕœÌœ «·„⁄—÷"));
                return;
            }

            if (ordrNo == null) {
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· √„— «·‰ﬁ·"));
                return;
            }

            dmsTrnsOrdrDt = sessionBean.findDmsTrnsOrdrDt(isAdmin ? locId : hrEmpInfo.getLocId(), ordrNo, cityName, areaName, mobile, tel1, clntName, address);
            if (dmsTrnsOrdrDt == null || dmsTrnsOrdrDt.getId() == null) {
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·„ Ì „ «·⁄ÀÊ— ⁄·Ï »Ì«‰« "));
                return;
            }

            cityName = dmsTrnsOrdrDt.getGovernName();
            areaName = dmsTrnsOrdrDt.getRegionName();
            mobile = dmsTrnsOrdrDt.getMobile();
            tel1 = dmsTrnsOrdrDt.getTel1();
            address = dmsTrnsOrdrDt.getAddress();
            clntName = dmsTrnsOrdrDt.getClntName();
        } else {
            if (ordrNo == null) {
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· √„— «·‰ﬁ· «·”«»ﬁ"));
                return;
            }
            if (attachedOrdrNo == null) {
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ«· √„— «·‰ﬁ· «·„·Õﬁ"));
                return;
            }
            dmsTrnsOrdrDt = sessionBean.findDmsTrnsOrdrDt(hrEmpInfo.getLocId(), ordrNo, cityName, areaName, mobile, tel1, clntName, address);
            if (dmsTrnsOrdrDt == null || dmsTrnsOrdrDt.getId() == null) {
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·„ Ì „ «·⁄ÀÊ— ⁄·Ï »Ì«‰«  ·√„— «·‰ﬁ· «·”«»ﬁ"));
                return;
            }

            atttachedDmsTrnsOrdrDt = sessionBean.findDmsTrnsOrdrDt(hrEmpInfo.getLocId(), attachedOrdrNo, attachedCityName, attachedAreaName, attachedMobile, attachedTel1, attachedClntName, attachedAddress);
            if (atttachedDmsTrnsOrdrDt == null || atttachedDmsTrnsOrdrDt.getId() == null) {
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·„ Ì „ «·⁄ÀÊ— ⁄·Ï »Ì«‰«  ·√„— «·‰ﬁ· «·„·Õﬁ"));
                return;
            }

            if ((!atttachedDmsTrnsOrdrDt.getBrings().equals("O") && !atttachedDmsTrnsOrdrDt.getBrings().equals("C") && !atttachedDmsTrnsOrdrDt.getBrings().equals("X"))
                    || (!dmsTrnsOrdrDt.getBrings().equals("O") && !dmsTrnsOrdrDt.getBrings().equals("C") && !dmsTrnsOrdrDt.getBrings().equals("X"))) {
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "€Ì— „”„ÊÕ √‰ ÌﬂÊ‰ √Õœ «·√„—Ì‰ „— Ã⁄"));
                return;
            }

            if (dmsTrnsOrdrDt.getPricePayed().equals(0D)) {
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "€Ì— „”„ÊÕ √‰ ÌﬂÊ‰ √„— «·‰ﬁ· «·√’·Ï ﬁÌ„ Â ’›—"));
                return;
            }

            if (!atttachedDmsTrnsOrdrDt.getMobile().equals(dmsTrnsOrdrDt.getMobile())) {
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "√„— «·‰ﬁ· «·„·Õﬁ €Ì— „— »ÿ »√„— «·‰ﬁ· «·”«»ﬁ"));
                return;
            }

            if (atttachedDmsTrnsOrdrDt.getHdId() != null) {
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", " √„— «·‰ﬁ· «·„·Õﬁ  „ ≈·Õ«ﬁÂ „‰ ﬁ»· »√„— ‰ﬁ· ”«»ﬁ"));
                return;
            }
            if (atttachedDmsTrnsOrdrDt.getId() < dmsTrnsOrdrDt.getId()) {
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", " „ ≈‰‘«¡ √„— «·‰ﬁ· «·„·Õﬁ ﬁ»· √„— «·‰ﬁ· «·”«»ﬁ"));
                return;
            }

            if (atttachedDmsTrnsOrdrDt.getId().equals(dmsTrnsOrdrDt.getId())) {
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "√„— «·‰ﬁ· «·”«»ﬁ ÂÊ ‰›”Â √„— «·‰ﬁ· «·„·Õﬁ"));
                return;
            }
            cityName = dmsTrnsOrdrDt.getGovernName();
            areaName = dmsTrnsOrdrDt.getRegionName();
            mobile = dmsTrnsOrdrDt.getMobile();
            tel1 = dmsTrnsOrdrDt.getTel1();
            address = dmsTrnsOrdrDt.getAddress();
            clntName = dmsTrnsOrdrDt.getClntName();

            attachedCityName = dmsTrnsOrdrDt.getGovernName();
            attachedAreaName = dmsTrnsOrdrDt.getRegionName();
            attachedMobile = dmsTrnsOrdrDt.getMobile();
            attachedTel1 = dmsTrnsOrdrDt.getTel1();
            attachedAddress = dmsTrnsOrdrDt.getAddress();
            attachedClntName = dmsTrnsOrdrDt.getClntName();
        }
       activeSave=true;
    }

    public void newSearch(ActionEvent ae) {
        resetValues();
        discountType = null;
    }

    public List<String> completeCityName(String query) {
        List<String> results = sessionBean.findGovernNameBySubstr(query);
        cityName = null;
        return results;
    }

    public List<String> completeAttachedCityName(String query) {
        List<String> results = sessionBean.findGovernNameBySubstr(query);
        attachedCityName = null;
        return results;
    }

    public List<String> completeAreaName(String query) {
        List<String> results = new ArrayList<String>();
        if (cityName != null) {
            results = sessionBean.findRegionNameBySubstr(query, cityName);
        }
        areaName = null;
        return results;
    }

    public List<String> completeAttachedAreaName(String query) {
        List<String> results = new ArrayList<String>();
        if (attachedCityName != null) {
            results = sessionBean.findRegionNameBySubstr(query, attachedCityName);
        }
        attachedAreaName = null;
        return results;
    }

    public void updateCityForArea(AjaxBehaviorEvent be) {
       
    }

    public void claculateFinalPrice(AjaxBehaviorEvent be) {
        if (dmsTrnsOrdrDt == null || dmsTrnsOrdrDt.getId() == null) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ»  ÕœÌœ √„— «·‰ﬁ·"));
            discountPercent = null;
            finalPrice = null;
            addFees=0d;
            return;
        }
        if (discountValue > dmsTrnsOrdrDt.getListPrice()) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", " „  Ã«Ê“ ‰”»… «·Œ’„"));
            discountPercent = null;
            finalPrice = null;
            addFees=0d;
            return;
        }
        if (discountValue % 5 > 0) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÕ» ≈œŒ«· „»·€ ’ÕÌÕ „ﬁ—» ·√ﬁ—» Œ„” Ã‰ÌÂ« "));
            discountPercent = null;
            finalPrice = null;
            addFees=0d;
            return;
        }
//        discountPercent = Double.valueOf(Math.round(discountValue / dmsTrnsOrdrDt.getListPrice() * 100));
        discountPercent = Double.valueOf(Math.round((discountValue + (dmsTrnsOrdrDt.getRmnDiscount() != null ? dmsTrnsOrdrDt.getRmnDiscount() : 0)) / dmsTrnsOrdrDt.getListPrice() * 100));
//        finalPrice = Double.valueOf(Math.round(dmsTrnsOrdrDt.getListPrice() - discountValue));
        finalPrice = Double.valueOf(Math.round(dmsTrnsOrdrDt.getListPrice() - discountValue - (dmsTrnsOrdrDt.getRmnDiscount() != null ? dmsTrnsOrdrDt.getRmnDiscount() : 0)));

        if (finalPrice>=dmsTrnsOrdrDt.getAddFees()){
                finalPrice=finalPrice-dmsTrnsOrdrDt.getAddFees();
                addFees=dmsTrnsOrdrDt.getAddFees();
        }
        else{
            addFees=0d;
        }

    }

    public void saveDiscount(ActionEvent ae) {
        if (discountType.equals("1")) {
            if (dmsTrnsOrdrDt == null || dmsTrnsOrdrDt.getId() == null) {
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ»  ÕœÌœ √„— «·‰ﬁ·"));
                discountPercent = null;
                finalPrice = null;
                addFees=0d;
                return;
            }

            if (dmsTrnsOrdrDt.getHdId() != null) {
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·« Ì„ﬂ‰ﬂ ⁄„· Œ’„ ·√„— ‰ﬁ· ”«»ﬁ √Ê „·Õﬁ"));
                discountPercent = null;
                finalPrice = null;
                addFees=0d;
                return;
            }

            if (discountValue == null) {
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ»  ÕœÌœ ﬁÌ„… «·Œ’„"));
                discountPercent = null;
                finalPrice = null;
                addFees=0d;
                return;
            }

            if (discountValue > dmsTrnsOrdrDt.getListPrice()) {
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", " „  Ã«Ê“ ‰”»… «·Œ’„"));
                discountPercent = null;
                finalPrice = null;
                addFees=0d;
                return;
            }

            DmsTransportOrdrHd dmsTransportOrdrHd = sessionBean.findDmsOrdrHdById(dmsTrnsOrdrDt.getId());


            if (dmsTransportOrdrHd.getDiscountTimes() != null && dmsTransportOrdrHd.getDiscountTimes() > 0 && !isAdmin) {
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·« Ì„ﬂ‰ﬂ ≈œŒ«· Œ’„ ·√ﬂÀ— „‰ „—…"));
                discountPercent = null;
                finalPrice = null;
                addFees=0d;
                return;
            }

            if (discountValue % 5 > 0) {
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÕ» ≈œŒ«· „»·€ ’ÕÌÕ „ﬁ—» ·√ﬁ—» Œ„” Ã‰ÌÂ« "));
                discountPercent = null;
                finalPrice = null;
                addFees=0d;
                return;
            }

            if (discountPercent > 50) {
                RequestContext.getCurrentInstance().execute("PF('confirmation').show();");
                return;
            }

            dmsTransportOrdrHd.setDiscountTimes(dmsTransportOrdrHd.getDiscountTimes() == null ? 1 : dmsTransportOrdrHd.getDiscountTimes() + 1);
            dmsTransportOrdrHd.setPrice(finalPrice);
            dmsTransportOrdrHd.setPricePayed(finalPrice);
            dmsTransportOrdrHd.setAddFees(addFees);
            dmsTransportOrdrHd.setDiscountEmp(hrEmpInfo);
            dmsTransportOrdrHd.setPriceRmn(0D);
            dmsTransportOrdrHd.setDiscount(dmsTrnsOrdrDt.getListPrice() - finalPrice);
            dmsTransportOrdrHd.setDiscountNotes(notes);
            sessionBean.mergeDmsTranOrdrHd(dmsTransportOrdrHd);
            resetValues();
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " „ »‰Ã«Õ", " „ ≈œŒ«· Œ’„ «·‰ﬁ· »‰Ã«Õ"));
        } else {
            if (dmsTrnsOrdrDt == null || dmsTrnsOrdrDt.getId() == null) {
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ»  ÕœÌœ √„— «·‰ﬁ· «·”«»ﬁ"));
                return;
            }
            if (atttachedDmsTrnsOrdrDt == null || atttachedDmsTrnsOrdrDt.getId() == null) {
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ»  ÕœÌœ √„— «·‰ﬁ· «·„·Õﬁ"));
                return;
            }



            if (dmsTrnsOrdrDt.getHdId() == null) {
                DmsTransportOrdrHd dmsTransportOrdrHdAttached = sessionBean.findDmsOrdrHdById(atttachedDmsTrnsOrdrDt.getId());
                DmsTransportOrdrParent dmsTransportOrdrParent = new DmsTransportOrdrParent();
                Long parentId = sessionBean.findNextIdFromDmsTransportOrdrParent();
                if (parentId == null) {
                    parentId = 1L;
                } else {
                    parentId += 1;
                }
                dmsTransportOrdrParent.setId(parentId);
                DmsTransportOrdrHd dmsTransportOrdrHd = sessionBean.findDmsOrdrHdById(dmsTrnsOrdrDt.getId());
                dmsTransportOrdrParent.setDiscount(dmsTransportOrdrHd.getDiscount());
                dmsTransportOrdrParent.setPrice(dmsTransportOrdrHd.getPrice());
                try {
                    dmsTransportOrdrParent.setDiscountPercent(dmsTransportOrdrHd.getDiscount() / Double.parseDouble(dmsTransportOrdrHd.getListPrice().toString()));
                } catch (Exception e) {
                    dmsTransportOrdrParent.setDiscountPercent(0D);
                }
                sessionBean.persistDmsTransportOrdrParent(dmsTransportOrdrParent);
                dmsTransportOrdrHd.setHdId(parentId);
                sessionBean.mergeDmsTranOrdrHd(dmsTransportOrdrHd);
                dmsTransportOrdrHdAttached.setHdId(parentId);
                dmsTransportOrdrHdAttached.setRltdOrdrId(new BigInteger(dmsTrnsOrdrDt.getId() + ""));
                dmsTransportOrdrHdAttached.setPrice(0D);
                dmsTransportOrdrHdAttached.setDiscountEmp(hrEmpInfo);
                dmsTransportOrdrHdAttached.setPriceRmn(0D);
                dmsTransportOrdrHdAttached.setPricePayed(0D);
                dmsTransportOrdrHdAttached.setAddFees(0D);
                dmsTransportOrdrHdAttached.setDiscount(Double.parseDouble(dmsTransportOrdrHdAttached.getListPrice().toString()));
                sessionBean.mergeDmsTranOrdrHd(dmsTransportOrdrHdAttached);
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " „ »‰Ã«Õ", " „ ≈·Õ«ﬁ √„— «·‰ﬁ· »‰Ã«Õ"));
            } else {
                DmsTransportOrdrHd dmsTransportOrdrHdAttached = sessionBean.findDmsOrdrHdById(atttachedDmsTrnsOrdrDt.getId());
                dmsTransportOrdrHdAttached.setHdId(dmsTrnsOrdrDt.getHdId());
                dmsTransportOrdrHdAttached.setRltdOrdrId(new BigInteger(dmsTrnsOrdrDt.getId() + ""));
                dmsTransportOrdrHdAttached.setPrice(0D);
                dmsTransportOrdrHdAttached.setDiscountEmp(hrEmpInfo);
                dmsTransportOrdrHdAttached.setPriceRmn(0D);
                dmsTransportOrdrHdAttached.setPricePayed(0D);
                dmsTransportOrdrHdAttached.setAddFees(0D);
                dmsTransportOrdrHdAttached.setDiscount(Double.parseDouble(dmsTransportOrdrHdAttached.getListPrice().toString()));
                sessionBean.mergeDmsTranOrdrHd(dmsTransportOrdrHdAttached);
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " „ »‰Ã«Õ", " „ ≈·Õ«ﬁ √„— «·‰ﬁ· »‰Ã«Õ"));
            }
            resetValues();
        }
         activeSave=false;
    }

    public void confirmDiscount() {
        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("confirm").toString().equals("1")) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·« Ì„ﬂ‰ ⁄„· Œ’„ ⁄«œÏ ·√„— ‰ﬁ· „·Õﬁ"));
        } else {
            DmsTransportOrdrHd dmsTransportOrdrHd = sessionBean.findDmsOrdrHdById(dmsTrnsOrdrDt.getId());
            dmsTransportOrdrHd.setPrice(finalPrice);
            dmsTransportOrdrHd.setPricePayed(finalPrice);
            dmsTransportOrdrHd.setAddFees(addFees);
            dmsTransportOrdrHd.setDiscountEmp(hrEmpInfo);
            dmsTransportOrdrHd.setPriceRmn(0D);
            dmsTransportOrdrHd.setDiscount(dmsTrnsOrdrDt.getListPrice() - finalPrice);
            dmsTransportOrdrHd.setDiscountNotes(notes);
            sessionBean.mergeDmsTranOrdrHd(dmsTransportOrdrHd);
            resetValues();
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " „ »‰Ã«Õ", " „ ≈œŒ«· Œ’„ «·‰ﬁ· »‰Ã«Õ"));
        }
        RequestContext.getCurrentInstance().execute("PF('confirmation').hide();");
    }

    public void resetValues() {
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
        attachedCityName = null;
        attachedAreaName = null;
        attachedMobile = null;
        attachedTel1 = null;
        attachedAddress = null;
        attachedClntName = null;
        atttachedDmsTrnsOrdrDt = null;
        attachedOrdrNo = null;
        locId=0L;
        activeSave=false;
        addFees=0d;
    }

    /** Creates a new instance of DmsOrdrDiscount */
    public DmsOrdrDiscount1() {
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

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public Long getAttachedOrdrNo() {
        return attachedOrdrNo;
    }

    public void setAttachedOrdrNo(Long attachedOrdrNo) {
        this.attachedOrdrNo = attachedOrdrNo;
    }

    public String getAttachedAddress() {
        return attachedAddress;
    }

    public void setAttachedAddress(String attachedAddress) {
        this.attachedAddress = attachedAddress;
    }

    public String getAttachedAreaName() {
        return attachedAreaName;
    }

    public void setAttachedAreaName(String attachedAreaName) {
        this.attachedAreaName = attachedAreaName;
    }

    public String getAttachedCityName() {
        return attachedCityName;
    }

    public void setAttachedCityName(String attachedCityName) {
        this.attachedCityName = attachedCityName;
    }

    public String getAttachedClntName() {
        return attachedClntName;
    }

    public void setAttachedClntName(String attachedClntName) {
        this.attachedClntName = attachedClntName;
    }

    public String getAttachedMobile() {
        return attachedMobile;
    }

    public void setAttachedMobile(String attachedMobile) {
        this.attachedMobile = attachedMobile;
    }

    public String getAttachedTel1() {
        return attachedTel1;
    }

    public void setAttachedTel1(String attachedTel1) {
        this.attachedTel1 = attachedTel1;
    }

    public DmsTrnsOrdrDt getAtttachedDmsTrnsOrdrDt() {
        return atttachedDmsTrnsOrdrDt;
    }

    public void setAtttachedDmsTrnsOrdrDt(DmsTrnsOrdrDt atttachedDmsTrnsOrdrDt) {
        this.atttachedDmsTrnsOrdrDt = atttachedDmsTrnsOrdrDt;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public List<SelectItem> getShowList() {
        return showList;
    }

    public void setShowList(List<SelectItem> showList) {
        this.showList = showList;
    }

    public Long getLocId() {
        return locId;
    }

    public void setLocId(Long locId) {
        this.locId = locId;
    }

    public boolean isActiveSave() {
        return activeSave;
    }

    public void setActiveSave(boolean activeSave) {
        this.activeSave = activeSave;
    }

    public Double getAddFees() {
        return addFees;
    }

    public void setAddFees(Double addFees) {
        this.addFees = addFees;
    }

    
    
}
