/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sb;

import e.CrmkBranch;
import e.CrmkF2bMe2bNotClosed;
import e.CrmkOrdersNotDelivered;
import e.CrmkOrdersNotPaied;
import e.CrmkOrdrHd;
import e.CrmkOrdrSader;
import e.CrmkOrdrSaderSetting;
import e.CrmkShowRecivRmndrQDt;
import e.CrmkShowRecivRmndrQHd;
import e.EmpSuggest;
import e.HrAuthorization;
import e.HrAuthorizeRequest;
import e.HrAuthorizeRequestDt;
import e.HrBadlHistory;
import e.HrBorrowDt;
import e.HrBorrowHd;
import e.HrCheckupDutyBonus;
import e.HrCheckupDutyDt;
import e.HrCheckupDutyHd;
import e.HrCutoffVu;
import e.HrDutyEmpMngDt;
import e.HrEmpHd;
import e.HrEmpHolidays;
import e.HrEmpInfo;
import e.HrEmpMangers;
import e.HrEmpSal;
import e.HrEmpSalary;
import e.HrEmpTime;
import e.HrGzaDt;
import e.HrGzaEmpMngDt;
import e.HrGzaHd;
import e.HrGzaReason;
import e.HrHolidayRequest;
import e.HrHolidayRequestDt;
import e.HrHolidayType;
import e.HrInsuranceOffice;
import e.HrInvestigateDt;
import e.HrInvestigateEmp;
import e.HrInvestigateHd;
import e.HrLetterRequest;
import e.HrLetterType;
import e.HrLocation;
import e.HrMangaerialDecisions;
import e.HrManualEffectionDt;
import e.HrManualEffectionHd;
import e.HrManualInOutTrns;
import e.HrManualTrnsLevelDt;
import e.HrMenuTable;
import e.HrMilitarily;
import e.HrMontlySalaryCalcPeriod;
import e.HrNationality;
import e.HrOpenDutyDt;
import e.HrOpenDutyExpectedLocDt;
import e.HrOpenDutyHd;
import e.HrPersonalOrdrRequest;
import e.HrPrayTimes;
import e.HrProfileAlertDt;
import e.HrProfileAlertHd;
import e.HrProfileMessage;
import e.HrSalHistory;
import e.HrSchedule;
import e.HrScheduleDt;
import e.HrShift;
import e.HrShiftChangeRequest;
import e.HrShiftRequestDt;
import e.HrTamyozDt;
import e.HrTamyozHd;
import e.HrTamyozSecurityTransfer;
import e.HrTotalSalComponents;
import e.HrWHolidayAttendanceReq;
import e.HrWHolidayAttendanceReqDt;
import e.TaxDesc;
import e.Uni;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author ahmed abbas
 */
@Remote
public interface SessionBeanRemote {

   public Long finduser(int emp_no,String pass);
   public HrEmpInfo finduserbyid(Long emp_no);
   public List<HrEmpInfo> findChatEmp(Long emp,Long dept,Long loc,Long job);
   public List<HrEmpInfo> findAlternativeEmp(Long emp,Long dept,Long loc);
   public HrEmpHd findempbyid(Long emp_no);
   public void mergeHrEmpHd(HrEmpHd hrEmpHd);
   public List<HrPrayTimes> getPrayTimeToday(Date d);
   public List<HrMenuTable> findAllRoot(Long emp);
   public List<HrMenuTable> findAllChild(Long emp);
   public Long thereIsChild(Long pid,Long emp);
   public List<Object[]> findAllEff(Long emp_id,Date date_from,Date date_to);
   public List<HrMangaerialDecisions> getManagerialDecisions();
   public HrHolidayType find_holiday_By_Id(Long id);
   public List<Object[]> find_holiday_types();
   //public List<Object[]> find_holiday_request(Long emp_id,Date from_date);
   public List<HrHolidayRequest> find_holiday_request(Long emp_id,Date from_date);
   public void mergeHrHolidayRequest(HrHolidayRequest hrholidayrequest);
   public String chk_holiday_avilability(Long emp_no,Long year,Long holiday_type,Long days_no);
   public Long cntOfHolidays10(Long emp_id,Long m,Long y);
   public HrMontlySalaryCalcPeriod find_month_period(Date date);
   public Long cntOfSalCalc(Date date_from,Date date_to);
   public Long cntOfSalHistory(Date date_from,Date date_to);
   public Long findRequestId();
   public void persistHrHolidayRequest(HrHolidayRequest hrHolidayRequest);
   public Long cntOfAuth(Long emp_id,Date date_from,Date date_to);
   public Long chkTrnsEntry(Long emp_id,Date date_from,Date date_to);
   public Long chkHolidayEntry(Long emp_id,Date date_from,Date date_to,Long id);
   public List<HrProfileMessage> find_messages();
   public List<HrProfileMessage> find_img();
   public List<HrBorrowHd> findAllBorrow(Long emp_no);
   public List<HrBorrowDt> findAllBorrowDt();
   public Long find_suggest_id();
   public  void persist_suggestion(EmpSuggest empSuggestion);
   public void send_mail(String send_to,String subject,String txt);
   public List<HrSchedule> findAll(Long emp_no);
   public void mergeHrSchedule(HrSchedule hrSchedule);
   public Long getseq();
   public void persistHrSchedule(HrSchedule hrSchedule);
   public HrSchedule getschedule(Long id);
   public Long findOthersInBrn(Long emp_no,Long brn_id,int x);
   public Long findOthers(Long emp_no);
   public List<Object[]> find_trgt_hist(Long emp_no);
   public HrNationality genationailty(Long id);
   public HrInsuranceOffice getinsoffice(Long id);
   public HrMilitarily getmilitarily(Long id);
   public List<Object[]> findAllEducation(Long emp_id);
   public List<Object[]> findAllJob(Long emp_id);
   public Long cntOfSalCalc(Date authorize_date);
   public Long cntOfSalHistory(Date authorize_date);
   public Long chkCardCreated(Long emp,Date authorize_date);
   public List<HrAuthorization> CalcSumAuthurization(Long emp,Date from_date,Date to_date);
   public Long getAuthorizeMinutesSum(Long emp,Date from_date,Date to_date,Long id);
   public Long getAuthorizeRequestMax();
   public void authorizeRequestPersist(HrAuthorizeRequest hrAuthorizeRequest);
   public List<HrAuthorizeRequest> getAuthorizeRequestList(Long emp,Date authorize_date);
   public void mergeHrAuthorizeRequest(HrAuthorizeRequest hrAuthorizeRequest);
   public Long chkAuthorizeRequestExist(Date authorize_date,Long emp,Long id);
   public List<HrHolidayRequestDt> getHolidayRequestDt(Long mng,Long dept,Long loc,Long grp,Long job);
   public HrHolidayRequest findHolidayRequestById(Long id);
   public Long chkCardExist(Long emp,Long month,Long year);
   public void addEmpCard(Long emp,Long m,Long y);
   public void mergeHrEffectionManualDt(HrManualEffectionDt hrManualEffectionDt);
   public HrManualEffectionDt getHrManualEffectionDt(Long hd_id,Date trns_date);
   public HrManualEffectionHd getHrManualEffectionHd(Long emp,Long m,Long y);
   public List<HrManualEffectionDt> getHrManualEffectionDt(Long emp,Date from_date,Date to_date);
   public Long chkManualEffectionDt(Long hd_id,Date trns_date);
   public List<HrAuthorizeRequestDt> getAuthorizeRequestDt(Long mng,Long dept,Long loc,Long grp,Long job);
   public HrAuthorizeRequest getAuthorizeRequestById(Long id);
   public List<HrGzaEmpMngDt> getGzaEmpMngDt(Long dept,Long loc,Long grp,Long job,String emp_name);
   public List<String> getEmpByEmpNameSubstr(Long dept,Long loc,Long grp,Long job,String emp_name);
   public List<HrGzaReason> getGzaReasons();
   public Long getGzaDtMax();
   public Long getGzaHdMax();
   public void persistGzaHd(HrGzaHd hrGzaHd);
   public void persistGzaDt(HrGzaDt hrGzaDt);
   public Long chkInvestigate(Long investigate_id,Long emp);
   public List<HrInvestigateHd> getHrInvestigateHd(Date current_date,Long dept,Long job,Long grp,Long loc);
   public List<HrInvestigateDt> getHrInvestigateDt(Long id);
   public void persistHrInvestigateEmp(HrInvestigateEmp hrInvestigateEmp);
   public Long getHrInvestigateEmpMax();
   public List<Object[]> getTotalRadioResult(HrInvestigateHd hrInvestigateHd);
   public Long findTotalRadioCount(HrInvestigateHd hrInvestigateHd);
   public List<Object[]> getTotalSliderResult(HrInvestigateHd hrInvestigateHd);
   public HrEmpSal getHrEmpSal(Long emp);
   public List<HrTotalSalComponents> getHrTotalSalComponentses(Long empId);
   public Long getMaxSalHistYear();
   public Long getMaxSalHistMonth(Long y);
   public HrSalHistory getHrSalHistory(Long emp,Long y,long m);
   public Double getHrCutoffVu(Long m,Long y,Long emp);
   public Long getHrTamyozHistory(Long m,Long y,Long emp);
   public Double getSalesTamyoz(Long m,Long y,Long emp);
   public Double getOtherTamyoz(Long m,Long y,Long emp);
   public Double getHafez(Long m,Long y,Long emp);
   public List<HrBadlHistory> hrBadlHistorys(Long m,Long y,Long emp);
   public List<HrCutoffVu> getHrCutoffVus(Long m,Long y,Long emp);
   public List<Object[]> getHrGzaHds(Long mng,String emp);
   public Long chkHolidaysNotConfirmed(Long mng,Long dept,Long loc,Long grp,Long job);
   public Long chkEmpReadGza(Long empNo);
   public Long chkEmpReadApprovedGza(Long empNo);
   public Long chkAuthorizeNotConfirmed(Long mng,Long dept,Long loc,Long grp,Long job);
   public List<Object[]> getHrGzaDtForEmp(Long emp);
   public void mergeHrGzaDt(HrGzaDt hrGzaDt);
   public List<HrGzaDt> getEmpNotReadGza(Long Emp);
   public List<HrGzaDt> getEmpReadApprovedGza(Long Emp);
   public HrMontlySalaryCalcPeriod getHrMontlySalaryCalcPeriodByTrnsMonthAndYear(Long trnsMonth,Long trnsYear);
   public Long getHrScheduleDtId();
   public void persistHrScheduleDt(HrScheduleDt hrScheduleDt);
   public HrScheduleDt getLastStatusOfSchedule(Long id);
   public List<String> getScheduleEmpByEmpNameSubstr(Long dept,Long loc,Long grp,Long job,String emp_name);
   public HrEmpInfo findByEmpName(String emp_name);
   public List<HrSchedule> getMngSchedule(Long mng);
   public List<HrTamyozDt> getPersonalyTamyoz(Long emp,Date from_date,Date to_date);
   public Long chkInvetstigation(Date current_date,Long dept,Long job,Long grp,Long loc);
   public List<HrEmpMangers> getMngEmp(Long dept,Long job,Long grp,Long loc);
   public List<HrShift> getShift(Long dept);
   public HrShift getShiftById(Long id);
   public Long chkShiftTableEntered(Long emp,Date from_date);
   public List<HrEmpTime> getEmpTimeLastStatus(Long emp);
   public void persistHrEmpTime(HrEmpTime hrEmpTime);
   public List<HrEmpInfo> getShiftTables(Long dept,Long job,Long grp,Long loc);
   public Long getHrShiftRequest();
   public Long chkShiftRequestExist(Long emp,Date from_date,Long id);
   public void persistHrShiftChangeRequest(HrShiftChangeRequest hrShiftChangeRequest);
   public List<HrShiftChangeRequest> getHrShiftChangeRequestList(Long emp,Date from_date);
   public Long chkShiftExistAtSpecifiedDay(Long emp,Date from_date);
   public void merge_shift_request(HrShiftChangeRequest hrShiftChangeRequest);
   public List<HrShiftRequestDt> getHrShiftRequestDtList(Long mng,Long dept,Long loc,Long grp,Long job);
   public List<HrEmpTime> getHrEmpTimeByTrnsDateAndEmpNo(Long emp,Date trnsDate);
   //public BigDecimal getHrEmpTimeByTrnsDateAndEmpNo(BigDecimal emp,Date trnsDate);
   public void mergeHrEmpTime(HrEmpTime hrEmpTime);
   public HrShiftChangeRequest getHrShiftChangeRequestById(Long id);
   public HrEmpTime getHrEmpTimeById(Long id);
   public Long chkShiftNotConfirmed(Long mng,Long dept,Long loc,Long grp,Long job);
   public Long chkTaskReadDone(Long emp);
   public List<HrSchedule> getAllTasksNotRead(Long emp);
   public HrEmpHolidays getTotalHolidays(Long emp);
   public Long getAnnualHolidays(Long emp,Long year);
   public Long getOpposeHolidays(Long emp,Long year);
   public HrHolidayType getTotalAnnualHolidays(Long id);
   public Long getRemineHolidays(Long emp,Long year);
   public Long getInsteadHolidays(Long emp);
   public Long getTotalInsteadHolidays(Long emp,Long year);
   public Long getNormalHolidays(Long emp,Long year);
   public Long chkEmpDailyTamyoz(Long emp,Date trns_date);
   public void persistHrTamyozHd(HrTamyozHd hrTamyozHd);
   public void persistHrTamyozDt(HrTamyozDt hrTamyozDt);
   public HrTamyozHd getPersistHrTamyozHd(Date date,Long loc,Long emp);
   public List<CrmkOrdersNotPaied> getOrdersNotPaied(Date from_date,Date to_date,Long emp,Long loc);
   public List<CrmkOrdersNotDelivered> getOrdersNotDelivered(Date from_date,Date to_date,Long emp,Long loc);
   public List<HrTamyozHd> getEmpDailyTamyozApprove1(Long loc,Date date);
   public List<HrTamyozHd> getEmpDailyTamyozApprove2(Long loc,Date date);
   public List<HrTamyozHd> getEmpDailyTamyozApprove3(Long loc,Date date);
   public HrTamyozSecurityTransfer getSecurityTransfer1(long emp);
   public HrTamyozSecurityTransfer getSecurityTransfer2(long emp);
   public HrTamyozSecurityTransfer getSecurityTransfer3(long emp);
   public HrLocation findLocationById(Long id);
   public void mergeHrTamyozHd(HrTamyozHd hrTamyozHd);
   public HrTamyozDt mergeHrTamyozDt(HrTamyozDt hrTamyozDt);
   public HrTamyozHd getHrTamyozHdById(Long id);
   public List<HrTamyozHd> getEmpDailyTamyozEntry(Date date,Long loc);
   public List<Long> findTamyozEmpsByHdId(Long id);
   public HrTamyozDt findHrTamyozDtById(Long id);
   public void removeHrTamyozDt(HrTamyozDt hrTamyozDt);
   public List<CrmkOrdrSader> getSaderNotApproved(Long loc_id);
   public List<CrmkBranch> getStore();
   public List<CrmkOrdrSaderSetting> getCrmkOrdrSaderSettings(Long brn_id);
   public Long chkCrmkOrdrSaderCnt(Long brn_id,Date from_date,Date to_date);
   public Long chkCrmkOrdrSaderSum(Long brn_id,Date from_date,Date to_date);
   public void mergeCrmkOrdrSader(CrmkOrdrSader crmkOrdrSader);
   public CrmkBranch getCrmkBranchById(Long id);
   public List<CrmkOrdrSader> getSaderApproved(Long mng_id);
   public CrmkOrdrHd findCrmkOrdrHdById(Long id);
   public List<CrmkOrdrSader> findCrmkOrdrSaderNotPrinted(Long loc);
   public List<CrmkOrdrSader> findCrmkOrdrSaderPrinted(String emp,Date frm_date);
   public String getMeterByTxt(Double m);
   public String getPieceByTxt(Double m);
   public Double getCrmkQtySum(Long ordr_id);
   public Double getDcreQtySum(Long ordr_id);
   public Double getSehyQtySum(Long ordr_id);
   public List<HrLetterType> getHrLetterTypes();
   public void persistHrLetterRequest(HrLetterRequest hrLetterRequest);
   public List<HrLetterRequest> hrLetterRequestList(Long emp);
   public Long getHrLetterRequestId();
   public HrLetterType getHrLetterTypeById(Long id);
   public void mergeHrLetterRequest(HrLetterRequest hrLetterRequest);
   public List<HrLetterRequest> getHrLetterRequestNotRead(Long emp);
   public List<HrManualInOutTrns> getManualInTrns(Date frm_date,Date to_date,Long user);
   public List<HrManualInOutTrns> getManualOutTrns(Date frm_date,Date to_date,Long user);
   public List<HrManualTrnsLevelDt> getManualTrnsLevelDts(Long loc,Long user);
   public Long getManual_in_out_id();
   public void persistHrManualInOutTrns(HrManualInOutTrns hrManualInOutTrns);
   public HrEmpSalary getLastHrEmpSalary(Long emp);
   public HrEmpSalary getPeriviousHrEmpSalary(Long emp,Date date);
   public HrManualInOutTrns chkLastInOut(Long emp);
   public Uni getAddHafez(Long empId);
   public List<CrmkOrdersNotDelivered> getAllOdrdsNotDelivered(Long brnId,Date from_date,Date to_date);
   public List<CrmkOrdersNotPaied> getAllOrdrsNotPaied(Long brnId,Date from_date,Date to_date);
   public List<CrmkBranch> getShow();
   public BigDecimal get_tot_holidays(Long emp);
   public Long getHrWHolidayAttendanceReqMaxId();
   public void hrWHolidayAttendanceReqPersist(HrWHolidayAttendanceReq hrWHolidayAttendanceReq);
   public List<HrWHolidayAttendanceReq> getHrWHolidayAttendanceReqHist(Long emp_no,Date from_date);
   public void mergeHrWHolidayAttendanceReq(HrWHolidayAttendanceReq hrWHolidayAttendanceReq);
   public HrTotalSalComponents getFixedHafez(Long empId);
   public HrWHolidayAttendanceReq getHrWHolidayAttendanceById(Long id);
   public void megeHrWholidayAttandanceRwauest(HrWHolidayAttendanceReq hrWHolidayAttendanceReq);
   public List<HrWHolidayAttendanceReqDt> getHolidayAttendanceReqDtList(Long mng,Long dept,Long loc,Long grp,Long job);
   public void update_emp_password(Long emp_no,String password,String question,String answer);
   public List<CrmkShowRecivRmndrQHd> getCrmkShowRecivRmndrQHdsReciev(String ip);
   public List<CrmkShowRecivRmndrQHd> getCrmkShowRecivRmndrQHdsRmndr(String ip);
   public void updateCrmkShowRecivRmndrQty(Long hd_id,Long dt_id,Long rciv_emp,Long rmndr_emp,Double rciv_qty,Double rmndr_qty);
   public CrmkShowRecivRmndrQDt getCrmkShowRecivRmndrQDtById(Long id);
   public List<String> getShiftEmpByEmpNameSubstr(Long dept,Long loc,Long grp,Long job,String emp_name);
   public Long chkShiftEntry(Date from_date,Date to_date,Long emp);
   public void applyFingerPrintPerEmp(Date from_date,Date to_date,Long emp);
   public void removePreviousShiftTable(String from_date,String to_date,long emp_no);
   public Long chkPersnalOrdrExist(Long brn_id,Long ordr_no,Long prd_id,Character crmk_sehy);
   public CrmkOrdrHd getPersonalOrdr(Long brn_id,Long ordr_no,Long prd_id,Character crmk_sehy);
   public void persistPersonalOrdr(HrPersonalOrdrRequest personalOrdrRequest);
   public Long chkOrdrExist(Long ordr_id);
   public List<HrPersonalOrdrRequest> getPersonalOrdrRequests(Long emp_no);
   public BigDecimal getTotalCrmkOrdrValue(Long ordr_id);
   public BigDecimal getTotalDcreOrdrValue(Long ordr_id);
   public BigDecimal getTotalSehyOrdrValue(Long ordr_id);
   public List<HrPersonalOrdrRequest> getPersonalOrdrToConfirm(Date from_date,Date to_date);
   public void merge_personal_ordr_req(HrPersonalOrdrRequest hrPersonalOrdrRequest);
   public TaxDesc getT2meenDifferance(Long emp);
   public HrEmpHd findEmpById(Long empNo);
   public void update_emp_mobile(Long emp_no,String mob,String old_mob);
   public void update_emp_ident(Long emp_no,String id,String old_id);
   public HrProfileAlertHd getProfileAlertHd(Long emp_no);
   public void persistAlertHd(HrProfileAlertHd hrProfileAlertHd);
   public void persistAlertDt(HrProfileAlertDt hrProfileAlertDt);
   public Integer getAlertHdMaxId();
   public Integer getAlertDtMaxId();
   public List<HrProfileAlertHd> hrProfileAlertHdList();
   public void mergeAlertHd(HrProfileAlertHd hrProfileAlertHd);
   public void emp_visa(Long emp_no,String visa_no);
   public void persistOpenDutyHd(HrOpenDutyHd hrOpenDutyHd);
   public List<HrDutyEmpMngDt> getDutyEmpMngDt(Long dept,Long loc,Long grp,Long job,String emp_name);
   public List<String> getEmpDutyByEmpNameSubstr(Long dept,Long loc,Long grp,Long job,String emp_name);
   public List<HrLocation> getDutyLocations(Long loc_id);
   public void persistHrOpenDutyHd(HrOpenDutyHd hrOpenDutyHd);
   public void persisHrOpenDutyExpectedDt(HrOpenDutyExpectedLocDt hrOpenDutyExpectedLocDt);
   public HrLocation getLocationByName(String loc);
   public List<HrOpenDutyHd> find_duty_for_specific_emp(HrEmpInfo emp_id,Date trns_date);
   public List<HrOpenDutyExpectedLocDt> getDutyExpectedLocations(HrOpenDutyHd hrOpenDutyHd);
   public void mergeHrOpenDutyHd(HrOpenDutyHd hrOpenDutyHd);
   public void removeHrOpenDutyExpectedLocationDt(HrOpenDutyExpectedLocDt hrOpenDutyExpectedLocDt);
   public List<HrOpenDutyExpectedLocDt> findExpectedDutyLocations(Long loc_id,Date trns_date,String ip);
   public HrOpenDutyDt getLastEmpTransaction(Long emp_no,Date from_date,Date to_date,Long loc_id);
   public HrLocation getLoactionByIp(String ip);
   public void persist_Hr_Open_Duty_Dt(HrOpenDutyDt hrOpenDutyDt);
   public List<HrOpenDutyDt> getTodayTransactions(Date from_date,Date To_date);
   public Long chkExistanceOfDutyCurrentely(HrEmpInfo emp_id,Date trns_date);
   public Long chkExistsanceOfNotClosedF2BOrME2B(Long emp_no);
   public List<CrmkF2bMe2bNotClosed> findNotClosedF2BOrME2BForEmp(Long emp_no);
   public List<HrLocation> getLocations();
   public void persist_checkup_duty(HrCheckupDutyHd hrCheckupDutyHd);
   public HrLocation getLocationById(Long loc_id);
   public List<HrEmpInfo> getManagersMail(Long loc);
   public List<HrCheckupDutyHd> getCheckUpDutiesNotApproved();
   public void mergeHrCheckUpDutyHd(HrCheckupDutyHd hrCheckupDutyHd);
   public List<String> getDeptManagersMails(String mail);
   public HrCheckupDutyBonus getCheckUpDutyBonus(Long loc);
   public void mergeHrCheckupDutyDt(HrCheckupDutyDt hrCheckupDutyDt);
   public Long check_checkup_duty_existance(Long empNo,Date trnsDate,Long locId);
}
