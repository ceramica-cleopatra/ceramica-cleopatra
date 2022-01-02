/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrProfileMsg;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import sb.SessionBeanLocal;

/**
 *
 * @author Administrator
 */
@ManagedBean(name = "userProfileHandler")
@RequestScoped
public class UserProfileHandler {

    @EJB
    private SessionBeanLocal sessionBean;
    private HrEmpInfo hrEmpInfo;
    private String menuStyle;
    private Long holCnt = 0L;
    private Long authCnt = 0L;
    private Long hrLetterCnt = 0L;
    private Long shiftCnt = 0L;
    private Long tmyozCnt = 0L;
    private Long saderCnt = 0L;
    private Long gzaCnt = 0L;
    private Long overtimeSubCnt = 0L;
    private Long dutyCnt = 0L;
    private Long fundBorrowGuaranteeCnt = 0L;
    private Long fundBorrowDeptMngApproveCnt = 0L;
    private Long fundBorrowResponsipleMngApproveCnt = 0L;
    private Long advanceGuaranteeCnt = 0L;
    private Long advanceDeptMngApproveCnt = 0L;
    private Long advanceResponsipleMngApproveCnt = 0L;
    private Long checkupDutyCnt = 0L;
    private Long newEmpApproveCnt = 0L;
    private Long f2beF2meNotClosed = 0L;
    private List<String[]> msgList = new ArrayList<String[]>();
    private List<String[]> alertList = new ArrayList<String[]>();
    private List<String[]> decList = new ArrayList<String[]>();
    private List<String[]> breifMsgList = new ArrayList<String[]>();
    private List<String[]> breiefAlertList = new ArrayList<String[]>();
    private List<String[]> breiefDecList = new ArrayList<String[]>();
    private int alertListSize = 0;
    private int msgListSize = 0;
    private int decCnt = 0;
    private int msgCnt = 0;
    private String usercode;
    private String empImg;
    private int flag;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    @ManagedProperty("#{themeService}")
    private ThemeService service;

    @PostConstruct
    public void init() {
        usercode = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usercode");
        if (usercode == null) {
            FacesContext fc = FacesContext.getCurrentInstance();
            ValueBinding vb = fc.getApplication().createValueBinding("#{loginBean.username}");
            usercode = vb.getValue(fc).toString();
        }
        hrEmpInfo = (HrEmpInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrEmpInfo");
        if (hrEmpInfo == null) {
            hrEmpInfo = sessionBean.finduserbyid(Long.parseLong(usercode));
        }

        if (hrEmpInfo.getAutomaticSwitch() != null && hrEmpInfo.getAutomaticSwitch().equals("Y")) {
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String theme = service.getThemes().get(day - 1).getName();
            hrEmpInfo.setTheme(theme);
        }

        for (Theme theme : service.getThemes()) {
            if (theme.getName().equals(hrEmpInfo.getTheme())) {
                menuStyle = theme.getMenuStyle();
                break;
            }
        }
        if (CashHandler.getAlerts() != null && CashHandler.getAlerts().get(Long.parseLong(usercode)) != null && CashHandler.getAlerts().get(Long.parseLong(usercode)).size() > 0) {
            for (HrProfileMsg hrProfileMsg : CashHandler.getAlerts().get(Long.parseLong(usercode))) {
                if (hrProfileMsg.getEntityName().equals("HrHolidayRequest".trim()) && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N')))) {
                    holCnt++;
                } else if (hrProfileMsg.getEntityName().equals("HrAuthorizeRequest") && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N')))) {
                    authCnt++;
                } else if (hrProfileMsg.getEntityName().equals("HrLetterRequest") && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N')))) {
                    hrLetterCnt++;
                } else if (hrProfileMsg.getEntityName().equals("HrShiftChangeRequest") && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N')))) {
                    shiftCnt++;
                } else if (hrProfileMsg.getEntityName().equals("HrTamyozHd") && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N')))) {
                    tmyozCnt++;
                } else if (hrProfileMsg.getEntityName().equals("CrmkOrdrSader") && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N')))) {
                    saderCnt++;
                } else if (hrProfileMsg.getEntityName().equals("HrGzaHd") && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N')))) {
                    gzaCnt++;
                } else if (hrProfileMsg.getEntityName().equals("CrmkF2bMe2bNotClosed") && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N')))) {
                    f2beF2meNotClosed++;
                } else if (hrProfileMsg.getEntityName().equals("HrCheckupDutyHd2") && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N')))) {
                    checkupDutyCnt++;
                } else if ((hrProfileMsg.getEntityName().equals("HrBorrowFundRequestGuarantee1") || hrProfileMsg.getEntityName().equals("HrBorrowFundRequestGuarantee2")) && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N')))) {
                    fundBorrowGuaranteeCnt++;
                } else if (hrProfileMsg.getEntityName().equals("HrBorrowFundRequestDeptMng") && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N')))) {
                    fundBorrowDeptMngApproveCnt++;
                }else if (hrProfileMsg.getEntityName().equals("HrBorrowFundRequestResponsible") && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N')))) {
                    fundBorrowResponsipleMngApproveCnt++;
                }else if (hrProfileMsg.getEntityName().equals("HrAdvanceRequestGuarantee") && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N')))) {
                    advanceGuaranteeCnt++;
                } else if (hrProfileMsg.getEntityName().equals("HrAdvanceRequestDeptMng") && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N')))) {
                    advanceDeptMngApproveCnt++;
                }else if (hrProfileMsg.getEntityName().equals("HrAdvanceRequestResponsible") && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N')))) {
                    advanceResponsipleMngApproveCnt++;
                }else if (hrProfileMsg.getEntityName().equals("HR_NEW_EMP_EXCEED_3_MONTHS") && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N')))) {
                    newEmpApproveCnt++;
                }else if (hrProfileMsg.getEntityName().equals("HrOvertimeSubReq") && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N')))) {
                    overtimeSubCnt++;
                }else if (hrProfileMsg.getEntityName().equals("HrDutyTrnsDtReq") && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N')))) {
                    dutyCnt++;
                }

            }
            if (holCnt > 0) {
                String[] arr = new String[3];
                arr[0] = "ÅÌÇÒÉ áã íÊã ÅÚÊãÇÏåÇ";
                arr[1] = "holiday_confirm.xhtml";
                arr[2] = String.valueOf(holCnt);
                alertList.add(arr);
            }
            if (authCnt > 0) {
                String[] arr = new String[3];
                arr[0] = "ÅÐä áã íÊã ÅÚÊãÇÏå";
                arr[1] = "authorize_confirm.xhtml";
                arr[2] = String.valueOf(authCnt);
                alertList.add(arr);
            }
            if (hrLetterCnt > 0) {
                String[] arr = new String[3];
                arr[0] = "ãÝÑÏÇÊ ãÑÊÈ áã íÊã ÅÚÊãÇÏå";
                arr[1] = "#";
                arr[2] = String.valueOf(hrLetterCnt);
                alertList.add(arr);
            }
            if (shiftCnt > 0) {
                String[] arr = new String[3];
                arr[0] = "ÊÛííÑ ÔíÝÊ áã íÊã ÅÚÊãÇÏå";
                arr[1] = "shift_confirm.xhtml";
                arr[2] = String.valueOf(shiftCnt);
                alertList.add(arr);
            }
            if (tmyozCnt > 0) {
                String[] arr = new String[3];
                arr[0] = "ÊãíÒ íæãì áã íÊã ÅÚÊãÇÏå";
                arr[1] = "daily_tamyoz_approve.xhtml";
                arr[2] = String.valueOf(tmyozCnt);
                alertList.add(arr);
            }
            if (saderCnt > 0) {
                String[] arr = new String[3];
                arr[0] = "ÕÇÏÑ ØáÈíÉ áã íÊã ÅÚÊãÇÏå";
                arr[1] = "sader_manager_approve.xhtml";
                arr[2] = String.valueOf(saderCnt);
                alertList.add(arr);
            }
            if (gzaCnt > 0) {
                String[] arr = new String[3];
                arr[0] = "Êã ÊæÞíÚ ÌÒÇÁ äÊíÌÉ ÅÑÊßÇÈ ãÎÇáÝÉ";
                arr[1] = "gza_emp_audit.xhtml";
                arr[2] = String.valueOf(gzaCnt);
                alertList.add(arr);
            }
            if (f2beF2meNotClosed > 0) {
                String[] arr = new String[3];
                arr[0] = "ÓæÇÞØ ÊÍæíáÇÊ íÌÈ ãÊÇÈÚÊåÇ";
                arr[1] = "F2B_ME2B_Not_Closed.xhtml";
                arr[2] = String.valueOf(f2beF2meNotClosed);
                alertList.add(arr);
            }
            if (checkupDutyCnt > 0) {
                String[] arr = new String[3];
                arr[0] = "ãÃãæÑíÉ ãÑæÑ áã íÊã ÅÚÊãÇÏåÇ";
                arr[1] = "checkup_duty_approve2.xhtml";
                arr[2] = String.valueOf(checkupDutyCnt);
                alertList.add(arr);
            }
            if (fundBorrowGuaranteeCnt > 0) {
                String[] arr = new String[3];
                arr[0] = "ÓáÝÉ ãä ÇáÕäÏæÞ ãØáæÈ ÖãÇäåÇ";
                arr[1] = "fund_borrow_guarantee_approve.xhtml";
                arr[2] = String.valueOf(fundBorrowGuaranteeCnt);
                alertList.add(arr);
            }
            if (fundBorrowDeptMngApproveCnt > 0) {
                String[] arr = new String[3];
                arr[0] = "ÓáÝÉ ãä ÇáÕäÏæÞ ãØáæÈ ÅÚÊãÇÏåÇ";
                arr[1] = "fund_borrow_dept_mng_approve.xhtml";
                arr[2] = String.valueOf(fundBorrowDeptMngApproveCnt);
                alertList.add(arr);
            }
            if (fundBorrowResponsipleMngApproveCnt > 0) {
                String[] arr = new String[3];
                arr[0] = "ÓáÝÉ ãä ÇáÕäÏæÞ ãØáæÈ ÅÚÊãÇÏåÇ";
                arr[1] = "fund_borrow_mng_confirm.xhtml";
                arr[2] = String.valueOf(fundBorrowResponsipleMngApproveCnt);
                alertList.add(arr);
            }

            if (advanceGuaranteeCnt > 0) {
                String[] arr = new String[3];
                arr[0] = "ÚåÏå ãä ÇáÕäÏæÞ ãØáæÈ ÖãÇäåÇ";
                arr[1] = "advance_guarantee_approve.xhtml";
                arr[2] = String.valueOf(advanceGuaranteeCnt);
                alertList.add(arr);
            }

            if (advanceDeptMngApproveCnt > 0) {
                String[] arr = new String[3];
                arr[0] = "ÚåÏå ãä ÇáÕäÏæÞ ãØáæÈ ÅÚÊãÇÏåÇ";
                arr[1] = "advance_dept_mng_approve.xhtml";
                arr[2] = String.valueOf(advanceDeptMngApproveCnt);
                alertList.add(arr);
            }

            if (advanceResponsipleMngApproveCnt > 0) {
                String[] arr = new String[3];
                arr[0] = "ÚåÏå ãä ÇáÕäÏæÞ ãØáæÈ ÅÚÊãÇÏåÇ";
                arr[1] = "advance_resp_approve.xhtml";
                arr[2] = String.valueOf(advanceResponsipleMngApproveCnt);
                alertList.add(arr);
            }

            if (newEmpApproveCnt > 0) {
                String[] arr = new String[3];
                arr[0] = "ÅÚÊãÇÏ ÕáÇÍíÉ ãæÙÝ ÌÏíÏ";
                arr[1] = "new_emp_exceed_3month_approve.xhtml";
                arr[2] = String.valueOf(newEmpApproveCnt);
                alertList.add(arr);
            }

            if (overtimeSubCnt > 0) {
                String[] arr = new String[3];
                arr[0] = "ÓåÑ áã íÊã ÅÚÊãÇÏå";
                arr[1] = "overtime_approve.xhtml";
                arr[2] = String.valueOf(overtimeSubCnt);
                alertList.add(arr);
            }
            
            if (dutyCnt > 0) {
                String[] arr = new String[3];
                arr[0] = "ãÃãæÑíÉ áã íÊã ÅÚÊãÇÏåÇ";
                arr[1] = "approve_duty.xhtml";
                arr[2] = String.valueOf(dutyCnt);
                alertList.add(arr);
            }

            if (alertList.size() >= 4) {
                breiefAlertList.add(alertList.get(0));
                breiefAlertList.add(alertList.get(1));
                breiefAlertList.add(alertList.get(2));
                breiefAlertList.add(alertList.get(3));
            } else {
                breiefAlertList.addAll(alertList);
            }
        }



        if (CashHandler.getMsgs() != null && CashHandler.getMsgs().get(Long.parseLong(usercode)) != null && CashHandler.getMsgs().get(Long.parseLong(usercode)).size() > 0) {
            for (HrProfileMsg hrProfileMsg : CashHandler.getMsgs().get(Long.parseLong(usercode))) {
                if (hrProfileMsg.getEntityName().equals("HrHolidayRequest") && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N'))) && hrProfileMsg.getMsgApprove().equals('Y')) {
                    String[] arr = new String[3];
                    arr[0] = "ÅÌÇÒÉ Êã ÅÚÊãÇÏåÇ";
                    arr[1] = "holiday_request.xhtml";
                    arr[2] = String.valueOf(sdf.format(hrProfileMsg.getSendDate()));
                    msgList.add(arr);
                } else if (hrProfileMsg.getEntityName().equals("HrHolidayRequest") && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N'))) && hrProfileMsg.getMsgApprove().equals('N')) {
                    String[] arr = new String[3];
                    arr[0] = "ÅÌÇÒÉ Êã ÑÝÖåÇ";
                    arr[1] = "holiday_request.xhtml";
                    arr[2] = String.valueOf(sdf.format(hrProfileMsg.getSendDate()));
                    msgList.add(arr);
                } else if (hrProfileMsg.getEntityName().equals("HrAuthorizeRequest") && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N'))) && hrProfileMsg.getMsgApprove().equals('Y')) {
                    String[] arr = new String[3];
                    arr[0] = "ÅÐä Êã ÅÚÊãÇÏå";
                    arr[1] = "authorize_request.xhtml";
                    arr[2] = String.valueOf(sdf.format(hrProfileMsg.getSendDate()));
                    msgList.add(arr);
                } else if (hrProfileMsg.getEntityName().equals("HrAuthorizeRequest") && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N'))) && hrProfileMsg.getMsgApprove().equals('N')) {
                    String[] arr = new String[3];
                    arr[0] = "ÅÐä Êã ÑÝÖå";
                    arr[1] = "authorize_request.xhtml";
                    arr[2] = String.valueOf(sdf.format(hrProfileMsg.getSendDate()));
                    msgList.add(arr);
                } else if (hrProfileMsg.getEntityName().equals("HrLetterRequest") && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N')))) {
                    String[] arr = new String[3];
                    arr[0] = "ãÝÑÏÇÊ ãÑÊÈ Êã ÅÚÊãÇÏåÇ";
                    arr[1] = "hr_letter_request.xhtml";
                    arr[2] = String.valueOf(sdf.format(hrProfileMsg.getSendDate()));
                    msgList.add(arr);
                } else if (hrProfileMsg.getEntityName().equals("HrShiftChangeRequest") && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N'))) && hrProfileMsg.getMsgApprove().equals('Y')) {
                    String[] arr = new String[3];
                    arr[0] = "ÊÛííÑ ÔíÝÊ Êã ÅÚÊãÇÏå";
                    arr[1] = "shift_request.xhtml";
                    arr[2] = String.valueOf(sdf.format(hrProfileMsg.getSendDate()));
                    msgList.add(arr);
                } else if (hrProfileMsg.getEntityName().equals("HrShiftChangeRequest") && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N'))) && hrProfileMsg.getMsgApprove().equals('N')) {
                    String[] arr = new String[3];
                    arr[0] = "ÊÛííÑ ÔíÝÊ Êã ÑÝÖå";
                    arr[1] = "shift_request.xhtml";
                    arr[2] = String.valueOf(sdf.format(hrProfileMsg.getSendDate()));
                    msgList.add(arr);
                } else if (hrProfileMsg.getEntityName().equals("HrCheckupDutyHd2") && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N'))) && hrProfileMsg.getMsgApprove().equals('Y')) {
                    String[] arr = new String[3];
                    arr[0] = "Êã ÅÚÊãÇÏ ÊÞÑíÑ ãÃãæÑíÉ ãÑæÑ";
                    arr[1] = "checkup_duty_entry2.xhtml";
                    arr[2] = String.valueOf(sdf.format(hrProfileMsg.getSendDate()));
                    msgList.add(arr);
                } else if (hrProfileMsg.getEntityName().equals("HrCheckupDutyHd2") && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N'))) && hrProfileMsg.getMsgApprove().equals('N')) {
                    String[] arr = new String[3];
                    arr[0] = "Êã ÑÝÖ ÊÞÑíÑ ãÃãæÑíÉ ãÑæÑ";
                    arr[1] = "checkup_duty_entry2.xhtml";
                    arr[2] = String.valueOf(sdf.format(hrProfileMsg.getSendDate()));
                    msgList.add(arr);
                } else if ((hrProfileMsg.getEntityName().equals("HrBorrowFundRequestGuarantee1") || hrProfileMsg.getEntityName().equals("HrBorrowFundRequestGuarantee2")) && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N'))) && hrProfileMsg.getMsgApprove().equals('N')) {
                    String[] arr = new String[3];
                    arr[0] = "Êã ÑÝÖ ÖãÇä ÓáÝÉ ãä ÇáÕäÏæÞ";
                    arr[1] = "fund_borrow_req.xhtml";
                    arr[2] = String.valueOf(sdf.format(hrProfileMsg.getSendDate()));
                    msgList.add(arr);
                } else if ((hrProfileMsg.getEntityName().equals("HrBorrowFundRequestGuarantee1") || hrProfileMsg.getEntityName().equals("HrBorrowFundRequestGuarantee2")) && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N'))) && hrProfileMsg.getMsgApprove().equals('Y')) {
                    String[] arr = new String[3];
                    arr[0] = "ÊãÊ ÇáãæÇÝÞÉ Úáì ÖãÇä ÓáÝÉ ãä ÇáÕäÏæÞ";
                    arr[1] = "fund_borrow_req.xhtml";
                    arr[2] = String.valueOf(sdf.format(hrProfileMsg.getSendDate()));
                    msgList.add(arr);
                } else if (hrProfileMsg.getEntityName().equals("HrBorrowFundRequestDeptMng") && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N'))) && hrProfileMsg.getMsgApprove().equals('N')) {
                    String[] arr = new String[3];
                    arr[0] = "ÑÆíÓ ÇáÞÓã ÑÝÖ ÅÚÊãÇÏ ÓáÝÉ ãä ÇáÕäÏæÞ";
                    arr[1] = "fund_borrow_req.xhtml";
                    arr[2] = String.valueOf(sdf.format(hrProfileMsg.getSendDate()));
                    msgList.add(arr);
                } else if (hrProfileMsg.getEntityName().equals("HrBorrowFundRequestDeptMng") && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N'))) && hrProfileMsg.getMsgApprove().equals('Y')) {
                    String[] arr = new String[3];
                    arr[0] = "ÑÆíÓ ÇáÞÓã ÅÚÊãÏ ÓáÝÉ ãä ÇáÕäÏæÞ";
                    arr[1] = "fund_borrow_req.xhtml";
                    arr[2] = String.valueOf(sdf.format(hrProfileMsg.getSendDate()));
                    msgList.add(arr);
                } else if (hrProfileMsg.getEntityName().equals("HrBorrowFundRequestResponsible") && hrProfileMsg.getMsgApprove()==null && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N')))) {
                    String[] arr = new String[3];
                    arr[0] =  "Êã ÇáÑÏ Úáì ØáÈ ÓáÝÉ ãä ÇáÕäÏæÞ";
                    arr[1] = "fund_borrow_req.xhtml";
                    arr[2] = String.valueOf(sdf.format(hrProfileMsg.getSendDate()));
                    msgList.add(arr);
                } else if (hrProfileMsg.getEntityName().equals("HrBorrowFundRequestResponsible") && hrProfileMsg.getMsgApprove()!=null && hrProfileMsg.getMsgApprove().equals('N') && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N')))) {
                    String[] arr = new String[3];
                    arr[0] = "Êã ÑÝÖ ØáÈ ÓáÝÉ ãä ÇáÕäÏæÞ";
                    arr[1] = "fund_borrow_req.xhtml";
                    arr[2] = String.valueOf(sdf.format(hrProfileMsg.getSendDate()));
                    msgList.add(arr);
                }else if (hrProfileMsg.getEntityName().equals("HrBorrowFundRequestResponsible") && hrProfileMsg.getMsgApprove()!=null && hrProfileMsg.getMsgApprove().equals('Y') && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N')))) {
                    String[] arr = new String[3];
                    arr[0] = "ÊãÊ ÇáãæÇÝÞÉ Úáì ÓáÝÉ ãä ÇáÕäÏæÞ";
                    arr[1] = "fund_borrow_req.xhtml";
                    arr[2] = String.valueOf(sdf.format(hrProfileMsg.getSendDate()));
                    msgList.add(arr);
                }else if (hrProfileMsg.getEntityName().equals("HrBorrowFundRequest") && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N')))) {
                    String[] arr = new String[3];
                    arr[0] = "Êã ÇáÑÏ Úáì ØáÈ ÓáÝÉ ãä ÇáÕäÏæÞ";
                    arr[1] = "fund_borrow_mng_confirm.xhtml";
                    arr[2] = String.valueOf(sdf.format(hrProfileMsg.getSendDate()));
                    msgList.add(arr);
                }else if (hrProfileMsg.getEntityName().equals("HrAdvanceRequestGuarantee") && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N'))) && hrProfileMsg.getMsgApprove().equals('N')) {
                    String[] arr = new String[3];
                    arr[0] = "Êã ÑÝÖ ÖãÇä ÚåÏå ãä ÇáÕäÏæÞ";
                    arr[1] = "advance_request.xhtml";
                    arr[2] = String.valueOf(sdf.format(hrProfileMsg.getSendDate()));
                    msgList.add(arr);
                } else if (hrProfileMsg.getEntityName().equals("HrAdvanceRequestGuarantee") && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N'))) && hrProfileMsg.getMsgApprove().equals('Y')) {
                    String[] arr = new String[3];
                    arr[0] = "ÊãÊ ÇáãæÇÝÞÉ Úáì ÖãÇä ÚåÏå ãä ÇáÕäÏæÞ";
                    arr[1] = "advance_request.xhtml";
                    arr[2] = String.valueOf(sdf.format(hrProfileMsg.getSendDate()));
                    msgList.add(arr);
                } else if (hrProfileMsg.getEntityName().equals("HrAdvanceRequestDeptMng") && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N'))) && hrProfileMsg.getMsgApprove().equals('N')) {
                    String[] arr = new String[3];
                    arr[0] = "ÑÆíÓ ÇáÞÓã ÑÝÖ ÅÚÊãÇÏ ÚåÏå ãä ÇáÕäÏæÞ";
                    arr[1] = "advance_request.xhtml";
                    arr[2] = String.valueOf(sdf.format(hrProfileMsg.getSendDate()));
                    msgList.add(arr);
                } else if (hrProfileMsg.getEntityName().equals("HrAdvanceRequestDeptMng") && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N'))) && hrProfileMsg.getMsgApprove().equals('Y')) {
                    String[] arr = new String[3];
                    arr[0] = "ÑÆíÓ ÇáÞÓã ÅÚÊãÏ ÚåÏå ãä ÇáÕäÏæÞ";
                    arr[1] = "advance_request.xhtml";
                    arr[2] = String.valueOf(sdf.format(hrProfileMsg.getSendDate()));
                    msgList.add(arr);
                }  else if (hrProfileMsg.getEntityName().equals("HrAdvanceRequest") && hrProfileMsg.getMsgApprove()!=null && hrProfileMsg.getMsgApprove().equals('N') && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N')))) {
                    String[] arr = new String[3];
                    arr[0] = "Êã ÑÝÖ ØáÈ ÚåÏå ãä ÇáÕäÏæÞ";
                    arr[1] = "advance_request.xhtml";
                    arr[2] = String.valueOf(sdf.format(hrProfileMsg.getSendDate()));
                    msgList.add(arr);
                }else if (hrProfileMsg.getEntityName().equals("HrAdvanceRequest") && hrProfileMsg.getMsgApprove()!=null && hrProfileMsg.getMsgApprove().equals('Y') && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N')))) {
                    String[] arr = new String[3];
                    arr[0] = "ÊãÊ ÇáãæÇÝÞÉ Úáì ÚåÏå ãä ÇáÕäÏæÞ";
                    arr[1] = "advance_request.xhtml";
                    arr[2] = String.valueOf(sdf.format(hrProfileMsg.getSendDate()));
                    msgList.add(arr);
                }else if (hrProfileMsg.getEntityName().equals("HrOvertimeSubApprove") && hrProfileMsg.getMsgApprove()!=null && hrProfileMsg.getMsgApprove().equals('Y') && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N')))) {
                    String[] arr = new String[3];
                    arr[0] = "Êã ÅÚÊãÇÏ ÓåÑ";
                    arr[1] = "sal_effection.xhtml";
                    arr[2] = String.valueOf(sdf.format(hrProfileMsg.getSendDate()));
                    msgList.add(arr);
                }else if (hrProfileMsg.getEntityName().equals("HrDutyTrnsDtApprove") && hrProfileMsg.getMsgApprove()!=null && hrProfileMsg.getMsgApprove().equals('Y') && (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N')))) {
                    String[] arr = new String[3];
                    arr[0] = "Êã ÅÚÊãÇÏ ãÃãæÑíÉ";
                    arr[1] = "follow_up_duty.xhtml";
                    arr[2] = String.valueOf(sdf.format(hrProfileMsg.getSendDate()));
                    msgList.add(arr);
                }

            }
            if (msgList.size() >= 4) {
                breifMsgList.add(msgList.get(0));
                breifMsgList.add(msgList.get(1));
                breifMsgList.add(msgList.get(2));
                breifMsgList.add(msgList.get(3));
            } else {
                breifMsgList.addAll(msgList);
            }
        }
        if (CashHandler.getDecisions() != null && CashHandler.getDecisions().get(Long.parseLong(usercode)) != null && CashHandler.getDecisions().get(Long.parseLong(usercode)).size() > 0) {
            for (HrProfileMsg hrProfileMsg : CashHandler.getDecisions().get(Long.parseLong(usercode))) {
                if (hrProfileMsg.getReadDone() == null || hrProfileMsg.getReadDone().equals(new Character('N'))) {
                    String[] arr = new String[3];
                    arr[0] = hrProfileMsg.getMsgText();
                    arr[1] = "managerial_decision.xhtml";
                    arr[2] = String.valueOf(sdf.format(hrProfileMsg.getSendDate()));
                    decList.add(arr);
                }
            }
            if (decList.size() >= 4) {
                breiefDecList.add(decList.get(0));
                breiefDecList.add(decList.get(1));
                breiefDecList.add(decList.get(2));
                breiefDecList.add(decList.get(3));
            } else {
                breiefDecList.addAll(decList);
            }
        }


        empImg = "/images/dynamic/?param=" + usercode ;

//        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("flag") == null) {
//            if (getAlertCnt().equals("0")) {
//                flag = 0;
//            } else {
//                flag = 1;
//            }
//        }else{
//            flag=0;
//        }

    }

//    public void alertDlgCloseEvent(){
//       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("flag", "1");
//    }

    public String getMenuStyle() {
        return menuStyle;
    }

    public void setMenuStyle(String menuStyle) {
        this.menuStyle = menuStyle;
    }

    public ThemeService getService() {
        return service;
    }

    public void setService(ThemeService service) {
        this.service = service;
    }

    /** Creates a new instance of MsgsHandler */
    public UserProfileHandler() {
    }

    public List<String[]> getAlertList() {
        return alertList;
    }

    public void setAlertList(List<String[]> alertList) {
        this.alertList = alertList;
    }

    public String getAlertCnt() {
        return String.valueOf(authCnt + holCnt + shiftCnt + hrLetterCnt + f2beF2meNotClosed + tmyozCnt + saderCnt + gzaCnt + checkupDutyCnt
                +fundBorrowGuaranteeCnt+fundBorrowDeptMngApproveCnt+fundBorrowResponsipleMngApproveCnt+advanceGuaranteeCnt+advanceDeptMngApproveCnt
                +advanceResponsipleMngApproveCnt+newEmpApproveCnt+overtimeSubCnt+dutyCnt);
    }

    public List<String[]> getMsgList() {
        return msgList;
    }

    public void setMsgList(List<String[]> msgList) {
        this.msgList = msgList;
    }

    public int getAlertListSize() {
        return alertListSize;
    }

    public void setAlertListSize(int alertListSize) {
        this.alertListSize = alertListSize;
    }

    public int getMsgListSize() {
        return msgListSize;
    }

    public void setMsgListSize(int msgListSize) {
        this.msgListSize = msgListSize;
    }

    public int getMsgCnt() {
        return msgList.size();
    }

    public void setMsgCnt(int msgCnt) {
        this.msgCnt = msgCnt;
    }

    public HrEmpInfo getHrEmpInfo() {
        return hrEmpInfo;
    }

    public void setHrEmpInfo(HrEmpInfo hrEmpInfo) {
        this.hrEmpInfo = hrEmpInfo;
    }

    public List<String[]> getBreiefAlertList() {
        return breiefAlertList;
    }

    public void setBreiefAlertList(List<String[]> breiefAlertList) {
        this.breiefAlertList = breiefAlertList;
    }

    public List<String[]> getBreifMsgList() {
        return breifMsgList;
    }

    public void setBreifMsgList(List<String[]> breifMsgList) {
        this.breifMsgList = breifMsgList;
    }

    public List<String[]> getBreiefDecList() {
        return breiefDecList;
    }

    public void setBreiefDecList(List<String[]> breiefDecList) {
        this.breiefDecList = breiefDecList;
    }

    public List<String[]> getDecList() {
        return decList;
    }

    public void setDecList(List<String[]> decList) {
        this.decList = decList;
    }

    public int getDecCnt() {
        return decList.size();
    }

    public void setDecCnt(int decCnt) {
        this.decCnt = decCnt;
    }

    public String getEmpImg() {
        return empImg;
    }

    public void setEmpImg(String empImg) {
        this.empImg = empImg;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        System.out.println("flag" + flag);
        this.flag = flag;
    }
}
