/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sb;

import e.BrnQtyTrgetVu;
import e.BrnQtyTrgetYearVu;
import e.CrmkActivatePreviousDocReq;
import e.CrmkActivatePreviousDocVu;
import e.CrmkBranch;
import e.CrmkCOrdrEmp;
import e.CrmkDOrdrEmp;
import e.CrmkEmpHstry;
import e.CrmkEmployee;
import e.CrmkF2bMe2bNotClosed;
import e.CrmkOrdersNotDelivered;
import e.CrmkOrdersNotPaied;
import e.CrmkOrdrAndRmnWithoutSrf;
import e.CrmkOrdrEmpLog;
import e.CrmkOrdrHd;
import e.CrmkOrdrSader;
import e.CrmkOrdrSaderSetting;
import e.CrmkRmnPrintRequest;
import e.CrmkRsrvDt;
import e.CrmkSOrdrEmp;
import e.CrmkShowRecivRmndrQDt;
import e.CrmkShowRecivRmndrQHd;
import e.DmsDrivers;
import e.DmsTransportOrdrHd;
import e.DmsTransportOrdrParent;
import e.DmsTrnsOrdrDt;
import e.DmsUsers;
import e.EmpQtyTrgetVu;
import e.EmpQtyTrgetYearMv;
import e.EmpSuggest;
import e.HrActiveAlert;
import e.HrAdvanceRequest;
import e.HrAdvanceZamalaDt;
import e.HrAdvanceZamalaHd;
import e.HrAllShowroomTrgt;
import e.HrArea;
import e.HrAuthAndHolBalance;
import e.HrAuthorization;
import e.HrAuthorizeRequest;
import e.HrAuthorizeRequestDt;
import e.HrBadlHistory;
import e.HrBorrowDt;
import e.HrBorrowFundRequest;
import e.HrBorrowHd;
import e.HrBorrowZamalaHd;
import e.HrCheckupDutyBonus;
import e.HrCheckupDutyDt;
import e.HrCheckupDutyDt1;
import e.HrCheckupDutyDt2;
import e.HrCheckupDutyEmp2;
import e.HrCheckupDutyEmployees;
import e.HrCheckupDutyHd;
import e.HrCheckupDutyHd1;
import e.HrCheckupDutyHd2;
import e.HrCheckupDutyLocations;
import e.HrCheckupDutySetupHd;
import e.HrCutoffVu;
import e.HrDutyEmpMngDt;
import e.HrDutyTrnsDt;
import e.HrDutyTrnsHd;
import e.HrDutyTrnsHdVu;
import e.HrDynAlertHd;
import e.HrDynAlertTemplateDt;
import e.HrDynAlertTemplateHd;
import e.HrEgadaBonus;
import e.HrEgadaSetup;
import e.HrEmpCrmkBranch;
import e.HrEmpHd;
import e.HrEmpHolidays;
import e.HrEmpInfo;
import e.HrEmpMangers;
import e.HrEmpSal;
import e.HrEmpSalary;
import e.HrEmpTime;
import e.HrFundAdvanceSetup;
import e.HrFundBorrowDelayReq;
import e.HrFundBorrowSetup;
import e.HrFundBorrowSummary;
import e.HrGzaDt;
import e.HrGzaEmpMngDt;
import e.HrGzaHd;
import e.HrGzaReason;
import e.HrHafezSehyDt;
import e.HrHafezSehyMngDt;
import e.HrHolidayRequest;
import e.HrHolidayRequestDt;
import e.HrHolidayType;
import e.HrInOutManualTrnsVu;
import e.HrInquestDt;
import e.HrInquestHd;
import e.HrInsuranceOffice;
import e.HrInvestigateDt;
import e.HrInvestigateEmp;
import e.HrInvestigateHd;
import e.HrJobGrp;
import e.HrJobs;
import e.HrLetterRequest;
import e.HrLetterType;
import e.HrLocation;
import e.HrLocationIpMapping;
import e.HrMachineTimesheetLive;
import e.HrMainTrgtLevelsDt;
import e.HrMainTrgtLevelsHd;
import e.HrManNotesDt;
import e.HrManNotesHd;
import e.HrManagement;
import e.HrMangaerialDecisions;
import e.HrManualEffectionDt;
import e.HrManualEffectionHd;
import e.HrManualInOutTrns;
import e.HrManualTrnsLevelDt;
import e.HrMenuTable;
import e.HrMilitarily;
import e.HrMontlySalaryCalcPeriod;
import e.HrMosqueCrmkReq;
import e.HrNationality;
import e.HrNewEmpExceed3Months;
import e.HrNewEmpExceed3monthDt;
import e.HrOpenDutyDt;
import e.HrOpenDutyExpectedLocDt;
import e.HrOpenDutyHd;
import e.HrPersonalOrdrRequest;
import e.HrPoundHafezDt;
import e.HrPrayTimes;
import e.HrProfileAccessLog;
import e.HrProfileAlertDt;
import e.HrProfileAlertHd;
import e.HrProfileAlertReceiver;
import e.HrProfileCustomVote;
import e.HrProfileMessage;
import e.HrProfileMsg;
import e.HrProfilePrifilage;
import e.HrProfilePrivilage;
import e.HrPrvYearTransHolidays;
import e.HrRegion;
import e.HrSalDetail;
import e.HrSalHistory;
import e.HrSchedule;
import e.HrScheduleDt;
import e.HrShift;
import e.HrShiftChangeRequest;
import e.HrShiftDt;
import e.HrShiftRequestDt;
import e.HrShowroomManNotes;
import e.HrShowroomTrgt;
import e.HrTamyozApproveEmp;
import e.HrTamyozDt;
import e.HrTamyozHd;
import e.HrTamyozSecurityTransfer;
import e.HrTotalSalComponents;
import e.HrUsers;
import e.HrWHolidayAttendanceReq;
import e.HrWHolidayAttendanceReqDt;
import e.HrZamalaGiftSrfDt;
import e.TaxDesc;
import e.Uni;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author ahmed abbas
 */
@Local
public interface SessionBeanLocal {

    public HrEmpInfo finduserbyid(Long emp_no);

    public List<HrEmpInfo> findChatEmp(Long emp, Long dept, Long loc, Long job);

    public List<HrEmpInfo> findAlternativeEmp(Long emp, Long dept, Long loc);

    public HrEmpHd findempbyid(Long emp_no);

    public void mergeHrEmpHd(HrEmpHd hrEmpHd);

    public List<HrPrayTimes> getPrayTimeToday(Date d);

    public List<HrMenuTable> findAllRoot(Long emp);

    public List<HrMenuTable> findAllChild(Long emp);

    public Long thereIsChild(Long pid, Long emp);

    public List<Object[]> findAllEff(Long emp_id, Date date_from, Date date_to);

    public List<HrMangaerialDecisions> getManagerialDecisions();

    public HrHolidayType find_holiday_By_Id(Long id);

    public List<Object[]> find_holiday_types();
    //public List<Object[]> find_holiday_request(Long emp_id,Date from_date);

    public List<HrHolidayRequest> find_holiday_request(Long emp_id, Date from_date);

    public void mergeHrHolidayRequest(HrHolidayRequest hrholidayrequest);

    public String chk_holiday_avilability(Long emp_no, Long year, Long holiday_type, Long days_no);

    public Long cntOfHolidays10(Long emp_id, Long m, Long y);

    public HrMontlySalaryCalcPeriod find_month_period(Date date);

    public Long cntOfSalCalc(Date date_from, Date date_to);

    public Long cntOfSalHistory(Date date_from, Date date_to);

    public Long findRequestId();

    public void persistHrHolidayRequest(HrHolidayRequest hrHolidayRequest);

    public Long cntOfAuth(Long emp_id, Date date_from, Date date_to);

    public Long chkTrnsEntry(Long emp_id, Date date_from, Date date_to);

    public Long chkHolidayEntry(Long emp_id, Date date_from, Date date_to, Long id);

    public List<HrProfileMessage> find_messages();

    public List<HrProfileMessage> find_img();

    public List<HrBorrowHd> findAllBorrow(Long emp_no);

    public List<HrBorrowDt> findAllBorrowDt();

    public Long find_suggest_id();

    public void persist_suggestion(EmpSuggest empSuggestion);

    public void send_mail(String send_to, String subject, String txt);

    public List<HrSchedule> findAll(Long emp_no);

    public void mergeHrSchedule(HrSchedule hrSchedule);

    public Long getseq();

    public void persistHrSchedule(HrSchedule hrSchedule);

    public HrSchedule getschedule(Long id);

    public Long findOthersInBrn(Long emp_no, Long brn_id, int x);

    public Long findOthers(Long emp_no, Long brnId);

    public List<EmpQtyTrgetVu> findAllInBrn(Long brn_id);

    public List<Object[]> find_trgt_hist(Long emp_no);

    public HrNationality genationailty(Long id);

    public HrInsuranceOffice getinsoffice(Long id);

    public HrMilitarily getmilitarily(Long id);

    public List<Object[]> findAllEducation(Long emp_id);

    public List<Object[]> findAllJob(Long emp_id);

    public Long cntOfSalCalc(Date authorize_date);

    public Long cntOfSalHistory(Date authorize_date);

    public Long chkCardCreated(Long emp, Date authorize_date);

    public List<HrAuthorization> CalcSumAuthurization(Long emp, Date from_date, Date to_date);

    public Long getAuthorizeMinutesSum(Long emp, Date from_date, Date to_date, Long id);

    public Long getAuthorizeRequestMax();

    public void authorizeRequestPersist(HrAuthorizeRequest hrAuthorizeRequest);

    public List<HrAuthorizeRequest> getAuthorizeRequestList(Long emp, Date authorize_date);

    public void mergeHrAuthorizeRequest(HrAuthorizeRequest hrAuthorizeRequest);

    public Long chkAuthorizeRequestExist(Date authorize_date, Long emp, Long id);

    public List<HrHolidayRequestDt> getHolidayRequestDt(Long mng, Long dept, Long loc, Long grp, Long job);

    public HrHolidayRequest findHolidayRequestById(Long id);

    public Long chkCardExist(Long emp, Long month, Long year);

    public void addEmpCard(Long emp, Long m, Long y);

    public void mergeHrEffectionManualDt(HrManualEffectionDt hrManualEffectionDt);

    public HrManualEffectionDt getHrManualEffectionDt(Long hd_id, Date trns_date);

    public HrManualEffectionHd getHrManualEffectionHd(Long emp, Long m, Long y);

    public List<HrManualEffectionDt> getHrManualEffectionDt(Long emp, Date from_date, Date to_date);

    public Long chkManualEffectionDt(Long hd_id, Date trns_date);

    public List<HrAuthorizeRequestDt> getAuthorizeRequestDt(Long mng, Long dept, Long loc, Long grp, Long job);

    public HrAuthorizeRequest getAuthorizeRequestById(Long id);

    public List<HrGzaEmpMngDt> getGzaEmpMngDt(Long dept, Long loc, Long grp, Long job, String emp_name);

    public List<String> getEmpByEmpNameSubstr(Long dept, Long loc, Long grp, Long job, String emp_name);

    public List<HrGzaReason> getGzaReasons();

    public Long getGzaDtMax();

    public Long getGzaHdMax();

    public void persistGzaHd(HrGzaHd hrGzaHd);

    public void persistGzaDt(HrGzaDt hrGzaDt);

    public Long chkInvestigate(Long investigate_id, Long emp);

    public List<HrInvestigateHd> getHrInvestigateHd(Date current_date, Long dept, Long job, Long grp, Long loc);

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

    public HrSalHistory getHrSalHistory(Long emp, Long y, long m);

    public Double getHrCutoffVu(Long m, Long y, Long emp);

    public Long getHrTamyozHistory(Long m, Long y, Long emp);

    public Double getSalesTamyoz(Long m, Long y, Long emp);

    public Double getOtherTamyoz(Long m, Long y, Long emp);

    public Double getHafez(Long m, Long y, Long emp);

    public List<HrBadlHistory> hrBadlHistorys(Long m, Long y, Long emp);

    public List<HrCutoffVu> getHrCutoffVus(Long m, Long y, Long emp);

    public List<Object[]> getHrGzaHds(Long mng, String emp);

    public Long chkHolidaysNotConfirmed(Long mng, Long dept, Long loc, Long grp, Long job);

    public Long chkEmpReadGza(Long empNo);

    public Long chkEmpReadApprovedGza(Long empNo);

    public Long chkAuthorizeNotConfirmed(Long mng, Long dept, Long loc, Long grp, Long job);

    public List<Object[]> getHrGzaDtForEmp(Long emp);

    public void mergeHrGzaDt(HrGzaDt hrGzaDt);

    public List<HrGzaDt> getEmpNotReadGza(Long Emp);

    public List<HrGzaDt> getEmpReadApprovedGza(Long Emp);

    public HrMontlySalaryCalcPeriod getHrMontlySalaryCalcPeriodByTrnsMonthAndYear(Long trnsMonth, Long trnsYear);

    public Long getHrScheduleDtId();

    public void persistHrScheduleDt(HrScheduleDt hrScheduleDt);

    public HrScheduleDt getLastStatusOfSchedule(Long id);

    public List<String> getScheduleEmpByEmpNameSubstr(Long dept, Long loc, Long grp, Long job, String emp_name);

    public HrEmpInfo findByEmpName(String emp_name);

    public List<HrSchedule> getMngSchedule(Long mng);

    public List<HrTamyozDt> getPersonalyTamyoz(Long emp, Date from_date, Date to_date);

    public Long chkInvetstigation(Date current_date, Long dept, Long job, Long grp, Long loc);

    public List<HrEmpMangers> getMngEmp(Long dept, Long job, Long grp, Long loc);

    public List<HrShiftDt> getShift(Long dept,Long loc);

    public HrShift getShiftById(Long id);

    public Long chkShiftTableEntered(Long emp, Date from_date);

    public List<HrEmpTime> getEmpTimeLastStatus(Long emp);

    public void persistHrEmpTime(HrEmpTime hrEmpTime);

    public List<HrEmpInfo> getShiftTables(Long dept, Long job, Long grp, Long loc);

    public Long getHrShiftRequest();

    public Long chkShiftRequestExist(Long emp, Date from_date, Long id);

    public void persistHrShiftChangeRequest(HrShiftChangeRequest hrShiftChangeRequest);

    public List<HrShiftChangeRequest> getHrShiftChangeRequestList(Long emp, Date from_date);

    public Long chkShiftExistAtSpecifiedDay(Long emp, Date from_date);

    public void merge_shift_request(HrShiftChangeRequest hrShiftChangeRequest);

    public List<HrShiftRequestDt> getHrShiftRequestDtList(Long mng, Long dept, Long loc, Long grp, Long job);

    public List<HrEmpTime> getHrEmpTimeByTrnsDateAndEmpNo(Long emp, Date trnsDate);
    //public BigDecimal getHrEmpTimeByTrnsDateAndEmpNo(BigDecimal emp,Date trnsDate);

    public void mergeHrEmpTime(HrEmpTime hrEmpTime);

    public HrShiftChangeRequest getHrShiftChangeRequestById(Long id);

    public HrEmpTime getHrEmpTimeById(Long id);

    public Long chkShiftNotConfirmed(Long mng, Long dept, Long loc, Long grp, Long job);

    public Long chkTaskReadDone(Long emp);

    public List<HrSchedule> getAllTasksNotRead(Long emp);

    public HrEmpHolidays getTotalHolidays(Long emp);

    public Long getAnnualHolidays(Long emp, Long year);

    public Long getOpposeHolidays(Long emp, Long year);

    public HrHolidayType getTotalAnnualHolidays(Long id);

    public Long getRemineHolidays(Long emp, Long year);

    public Long getInsteadHolidays(Long emp);

    public Long getTotalInsteadHolidays(Long emp, Long year);

    public Long getNormalHolidays(Long emp, Long year);

    public Long chkEmpDailyTamyoz(Long emp, Date trns_date);

    public void persistHrTamyozHd(HrTamyozHd hrTamyozHd);

    public void persistHrTamyozDt(HrTamyozDt hrTamyozDt);

    public HrTamyozHd getPersistHrTamyozHd(Date date, Long loc, Long emp);

    public List<CrmkOrdersNotPaied> getOrdersNotPaied(Date from_date, Date to_date, Long emp, Long loc);

    public List<CrmkOrdersNotDelivered> getOrdersNotDelivered(Date from_date, Date to_date, Long emp, Long loc);

    public List<HrTamyozHd> getEmpDailyTamyozApprove1(Long loc, Date date);

    public List<HrTamyozHd> getEmpDailyTamyozApprove2(Long loc, Date date);

    public List<HrTamyozHd> getEmpDailyTamyozApprove3(Long loc, Date date);

    public HrTamyozSecurityTransfer getSecurityTransfer1(long emp);

    public HrTamyozSecurityTransfer getSecurityTransfer2(long emp);

    public HrTamyozSecurityTransfer getSecurityTransfer3(long emp);

    public HrLocation findLocationById(Long id);

    public void mergeHrTamyozHd(HrTamyozHd hrTamyozHd);

    public HrTamyozDt mergeHrTamyozDt(HrTamyozDt hrTamyozDt);

    public HrTamyozHd getHrTamyozHdById(Long id);

    public List<HrTamyozHd> getEmpDailyTamyozEntry(Date date, Long loc);

    public List<Long> findTamyozEmpsByHdId(Long id);

    public HrTamyozDt findHrTamyozDtById(Long id);

    public void removeHrTamyozDt(HrTamyozDt hrTamyozDt);

    public List<CrmkOrdrSader> getSaderNotApproved(Long loc_id);

    public List<CrmkBranch> getStore();

    public List<CrmkOrdrSaderSetting> getCrmkOrdrSaderSettings(Long brn_id);

    public Long chkCrmkOrdrSaderCnt(Long brn_id, Date from_date, Date to_date);

    public Long chkCrmkOrdrSaderSum(Long brn_id, Date from_date, Date to_date);

    public void mergeCrmkOrdrSader(CrmkOrdrSader crmkOrdrSader);

    public CrmkBranch getCrmkBranchById(Long id);

    public List<CrmkOrdrSader> getSaderApproved(Long mng_id);

    public CrmkOrdrHd findCrmkOrdrHdById(Long id);

    public List<CrmkOrdrSader> findCrmkOrdrSaderNotPrinted(Long loc);

    public List<CrmkOrdrSader> findCrmkOrdrSaderPrinted(String emp, Date frm_date);

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

    public List<HrManualInOutTrns> getManualInTrns(Date frm_date, Date to_date, Long user);

    public List<HrManualInOutTrns> getManualOutTrns(Date frm_date, Date to_date, Long user);

    public List<HrManualTrnsLevelDt> getManualTrnsLevelDts(Long loc, Long user);

    public Long getManual_in_out_id();

    public void persistHrManualInOutTrns(HrManualInOutTrns hrManualInOutTrns);

    public HrEmpSalary getLastHrEmpSalary(Long emp);

    public HrEmpSalary getPeriviousHrEmpSalary(Long emp, Date date);

    public HrManualInOutTrns chkLastInOut(Long emp);

    public Uni getAddHafez(Long empId);

    public List<CrmkOrdersNotDelivered> getAllOdrdsNotDelivered(Long brnId, Date from_date, Date to_date);

    public List<CrmkOrdersNotPaied> getAllOrdrsNotPaied(Long brnId, Date from_date, Date to_date);

    public List<CrmkBranch> getShow();

    public BigDecimal get_tot_holidays(Long emp);

    public Long getHrWHolidayAttendanceReqMaxId();

    public void hrWHolidayAttendanceReqPersist(HrWHolidayAttendanceReq hrWHolidayAttendanceReq);

    public List<HrWHolidayAttendanceReq> getHrWHolidayAttendanceReqHist(Long emp_no, Date from_date);

    public void mergeHrWHolidayAttendanceReq(HrWHolidayAttendanceReq hrWHolidayAttendanceReq);

    public HrTotalSalComponents getFixedHafez(Long empId);

    public HrWHolidayAttendanceReq getHrWHolidayAttendanceById(Long id);

    public void megeHrWholidayAttandanceRwauest(HrWHolidayAttendanceReq hrWHolidayAttendanceReq);

    public List<HrWHolidayAttendanceReqDt> getHolidayAttendanceReqDtList(Long mng, Long dept, Long loc, Long grp, Long job);

    public void update_emp_password(Long emp_no, String password, String question, String answer);

    public List<CrmkShowRecivRmndrQHd> getCrmkShowRecivRmndrQHdsReciev(String ip);

    public List<CrmkShowRecivRmndrQHd> getCrmkShowRecivRmndrQHdsRmndr(String ip);

    public void updateCrmkShowRecivRmndrQty(Long hd_id, Long dt_id, Long rciv_emp, Long rmndr_emp, Double rciv_qty, Double rmndr_qty);

    public CrmkShowRecivRmndrQDt getCrmkShowRecivRmndrQDtById(Long id);

    public List<String> getShiftEmpByEmpNameSubstr(Long dept, Long loc, Long grp, Long job, String emp_name);

    public Long chkShiftEntry(Date from_date, Date to_date, Long emp);

    public void applyFingerPrintPerEmp(Date from_date, Date to_date, Long emp);

    public void removePreviousShiftTable(String from_date, String to_date, long emp_no);

    public Long chkPersnalOrdrExist(Long brn_id, Long ordr_no, Long prd_id, Character crmk_sehy);

    public CrmkOrdrHd getPersonalOrdr(Long brn_id, Long ordr_no, Long prd_id, Character crmk_sehy);

    public void persistPersonalOrdr(HrPersonalOrdrRequest personalOrdrRequest);

    public Long chkOrdrExist(Long ordr_id);

    public List<HrPersonalOrdrRequest> getPersonalOrdrRequests(Long emp_no);

    public BigDecimal getTotalCrmkOrdrValue(Long ordr_id);

    public BigDecimal getTotalDcreOrdrValue(Long ordr_id);

    public BigDecimal getTotalSehyOrdrValue(Long ordr_id);

    public List<HrPersonalOrdrRequest> getPersonalOrdrToConfirm(Date from_date, Date to_date);

    public void merge_personal_ordr_req(HrPersonalOrdrRequest hrPersonalOrdrRequest);

    public TaxDesc getT2meenDifferance(Long emp);

    public HrEmpHd findEmpById(Long empNo);

    public void update_emp_mobile(Long emp_no, String mob, String old_mob);

    public void update_emp_ident(Long emp_no, String id, String old_id);

    public HrProfileAlertHd getProfileAlertHd(Long emp_no);

    public void persistAlertHd(HrProfileAlertHd hrProfileAlertHd);

    public void persistAlertDt(HrProfileAlertDt hrProfileAlertDt);

    public Long getAlertHdMaxId();

    public Long getAlertDtMaxId();

    public List<HrProfileAlertHd> hrProfileAlertHdList();

    public void mergeAlertHd(HrProfileAlertHd hrProfileAlertHd);

    public void emp_visa(Long emp_no, String visa_no);

    public void persistOpenDutyHd(HrOpenDutyHd hrOpenDutyHd);

    public List<HrDutyEmpMngDt> getDutyEmpMngDt(Long dept, Long loc, Long grp, Long job, String emp_name);

    public List<String> getEmpDutyByEmpNameSubstr(Long dept, Long loc, Long grp, Long job, String emp_name);

    public List<HrLocation> getDutyLocations(Long loc_id);

    public void persistHrOpenDutyHd(HrOpenDutyHd hrOpenDutyHd);

    public void persisHrOpenDutyExpectedDt(HrOpenDutyExpectedLocDt hrOpenDutyExpectedLocDt);

    public HrLocation getLocationByName(String loc);

    public List<HrOpenDutyHd> find_duty_for_specific_emp(HrEmpInfo emp_id, Date trns_date);

    public List<HrOpenDutyExpectedLocDt> getDutyExpectedLocations(HrOpenDutyHd hrOpenDutyHd);

    public void mergeHrOpenDutyHd(HrOpenDutyHd hrOpenDutyHd);

    public void removeHrOpenDutyExpectedLocationDt(HrOpenDutyExpectedLocDt hrOpenDutyExpectedLocDt);

    public List<HrOpenDutyExpectedLocDt> findExpectedDutyLocations(Long loc_id, Date trns_date, String ip);

    public HrOpenDutyDt getLastEmpTransaction(Long emp_no, Date from_date, Date to_date, Long loc_id);

    public List<HrLocation> getLoactionByIp(Long ip);

    public void persist_Hr_Open_Duty_Dt(HrOpenDutyDt hrOpenDutyDt);

    public List<HrOpenDutyDt> getTodayTransactions(Date from_date, Date To_date);

    public Long chkExistanceOfDutyCurrentely(HrEmpInfo emp_id, Date trns_date);

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

    public Long check_checkup_duty_existance(Long empNo, Date trnsDate, Long locId, Long id);

    public List<HrProfilePrivilage> allProfilePrivilage(Long empNo);

    public Long chk_holiday_request(Long emp_no, Date from_date, Date to_date, Long hol_type, Long req_id);

    public Long chk_authorize_request(Date auth_date, Long emp_no, Long req_id, Long min_sum);

    public List<HrEmpInfo> allHrEmpInfo();

    public List<HrProfilePrivilage> allProfilePrivilage();

    public Long chk_shift_change_request(Long emp_no, Date from_date, Long req_id);

    public void persistHrProfileMSG(HrProfileMsg hrProfileMsg);

    public List<HrEmpMangers> findManagersPerEmp(Long empNo);

    public List<HrEmpMangers> findAllEmpManagers();

    public List<HrProfileMsg> findAllProfilMsgs();

    public void mergeHrProfileMsgList(List<HrProfileMsg> hrProfileMsgList);

    public List<HrTamyozSecurityTransfer> findTamyozSecutityTransferPerEmp(Long emp);

    public List<HrTamyozApproveEmp> findAllTamyozApproveEmps();

    public List<HrLocation> findAllShowRooms();

    public void updateReadDoneMsg(String entity, Long msgId, char val, Long usercode);

    public List<HrEmpInfo> findLocationManagers(Long loc_id);

    public List<HrEmpCrmkBranch> findStoreManagers(Long storeId);

    public List<HrGzaEmpMngDt> findAllGzaEmpMng();

    public void addShiftTable(Long emp_no, Date from_date, Date to_date, Long shift_id, Long entry_no);

    public Long chkShiftTableCopy(Long emp_no, Date from_date1, Date to_date1, Date from_date2);

    public void copyShiftTable(Long emp_no, Date from_date1, Date from_date2, Date to_date2);

    public List<HrCheckupDutyHd> findCheckUpDutyHdForEmp(HrEmpInfo entryId, Date trnsDate);

    public void removeHrCheckDutyDt(HrCheckupDutyDt hrCheckupDutyDt);

    public HrAuthAndHolBalance findEmpAuthAndHolBalance(Long emp);

    public HrSalDetail findSalDetail(Long empNo);

    public List<HrPoundHafezDt> findPoundHafezDts(Long empId);

    public String findManagerialHierarchy(String type);

    public List<HrShowroomManNotes> findHrShowroomManNoteses(Long manId);

    public void persistHrShowroomManNotes(HrShowroomManNotes hrShowroomManNotes);

    public void mergeHrShowroomManNotes(HrShowroomManNotes hrShowroomManNotes);

    public List<HrShowroomManNotes> findHrShowroomManNotesNotApproved();

    public List<BrnQtyTrgetVu> findShowroomTrgt(Long brn_id);

    public Long findShowroomOrder(Long percent);

    public List<EmpQtyTrgetVu> findSalesmenTrgtPerLocation(Long loc_id);

    public List<BrnQtyTrgetVu> findAllShowroomTrgts();

    public Long findCrmkShowRoomIdByLocId(Long loc_id);

    public Long chkCheckupDuty(Long v_emp_no, Date v_trns_date, Long id, Long v_loc_id, Long v_dept_id);

    public List<HrInOutManualTrnsVu> findAllInOutManualTrns();

    public List<HrManualInOutTrns> findAllInOutTrns(Date d1, Date d2, Long ip);

    public List<BrnQtyTrgetVu> findAllShowroomTrgt(Long brn_id);

    public DmsTrnsOrdrDt findDmsTrnsOrdrDt(Long brn_id, Long ordr_no, String govern_name, String region_name, String mobile, String tel1, String clnt_name, String address);

    public List<String> findGovernNameBySubstr(String name);

    public List<String> findRegionNameBySubstr(String name, String gvrn_name);

    public DmsTransportOrdrHd findDmsOrdrHdById(Long id);

    public void mergeDmsTranOrdrHd(DmsTransportOrdrHd dmsTransportOrdrHd);

    public Long chkEmpHaveEmail(Long emp);

    public int chkFundBorrow(Long emp_no, Long g1, Long g2, Long amount, Long months, Long sal, Date startDate);

    public void persistFundBorrow(HrBorrowFundRequest hrBorrowFundRequest);

    public void mergeFundBorrow(HrBorrowFundRequest hrBorrowFundRequest);

    public List<HrBorrowFundRequest> findAllGuaranteeRequests(HrEmpInfo guarantee);

    public List<HrBorrowFundRequest> findDeptMngRequests(HrEmpInfo deptMng);

    public List<HrBorrowFundRequest> findFundBorrowRequestsNeedConfirm();

    public java.util.List<e.CrmkOrdrAndRmnWithoutSrf> findOrdrAndRemainWithoutSrfPerEmp(int first, int pageSize, Map<java.lang.String, java.lang.Object> filters, int rowCount);

    public int countOrdrAndRemainWithoutSrfPerEmp(Map<String, Object> filters);

    public List<String> findOrdrClntNames(Long brnId);

    public List<String> findOrdrEmployeeNames(Long brnId);

    public List<String> findOrdrDriverNames(Long brnId);

    public List<Object[]> findActualQtyDetailsPerGovern(String itemCode, Long governId, String crmkSehy);

    public List<Object[]> findCrmkOrdrAndRmnWithoutSrfsByOrdrIds(String ordrIds);

    public List<Object[]> getStoreByGovern(Long governId, String target);

    public List<Object[]> findDriversForBrn(Long brnId);

    public void persistCrmkRmnPrintRequest(List<CrmkRmnPrintRequest> requestList);

    public void persistCrmkRmnPrintRequest(CrmkRmnPrintRequest request);

    public CrmkRmnPrintRequest findCrmkRmnPrintRequestById(Long reqId);

    public void mergeCrmkRmnPrintRequest(List<CrmkRmnPrintRequest> requestList);

    public void mergeCrmkRmnPrintRequest(CrmkRmnPrintRequest request);

    public List<CrmkOrdrAndRmnWithoutSrf> findAllRequestsPerBrn(int first, int pageSize, Map<String, Object> filters, int rowCount);

    public int findCountOfRequestsPerBrn(Map<String, Object> filters);

    public List<Object[]> findRemainDt(Long rmnId, String crmkSehy);

    public List<CrmkOrdrAndRmnWithoutSrf> findCrmkOrdrAndRmnWithoutSrf(Long reqId);

    public String findQtyAsString(Double qty);

    public List<String> findTrgtClntNameList(Long brnId);

    public List<String> findEmpRequestedNameList(Long brnId);

    public List<String> findTrgtDriverNameList(Long brnId);

    public List<Object[]> findBrnRmnPrintRequestCnt(Long trgtBrn, Date loadDate);

    public void refreshCrmkRmnWithoutSrfMv();

    public Long chkRmnDispatch(String crmkSehy, Long rmnId);

    public List<HrShiftDt> findAllShift(Long deptId, Long locId);

    public List<HrShiftDt> findAllShiftByDeptId(Long deptId);

    public List<e.HrBorrowFundRequest> findHrBorrowFundRequests(e.HrEmpInfo empNo);

    public void insertFundBorrow(Long v_req_id, Long v_emp_no, Long v_amount, Date v_start_date, Long v_pay_months, Long v_g1, Long v_g2, Long v_mng, String v_g1_phone, String v_g2_phone, String v_mng_phone);

    public HrFundBorrowSetup findBorrowSetup();

    public Long chkFundBorrowDelay(Long empNo, Long borrowNo, Long delayMonth, Long delayYear);

    public void applyFundBorrowDelay(Long empNo, Long borrowNo, Long delayMonth, Long delayYear);

    public void persistHrFundBorrowDelayReq(HrFundBorrowDelayReq hrFundBorrowDelayReq);

    public BrnQtyTrgetYearVu findBrnQtyTrgetYearVu(Long brnId, String months, String years);

    public Long findBrnOrdrPerMonth(Long trgt_percent, String month, String year);

    public List<EmpQtyTrgetYearMv> findEmpQtyTrgetYearVuList(Long brnId, String month);

    public List<BrnQtyTrgetYearVu> findAllBrnTrgtForPreviousMonth(String month, String year);

    public List<Object[]> findBrnQtyTrgetYearVuInPeriod(String months, Long loc_id);

    public List<Object[]> findEmpQtyTrgetYearVuListInPeriod(String months, String years, Long loc_id);

    public Object[] findTotalBrnQtyTrgetYearVuInPeriod(String monthsFrom, String yearsFrom, String monthsTo, String yearsTo);

    public List<EmpQtyTrgetYearMv> findEmpTargetByMonth(HrEmpInfo hrEmpInfo, String month);

    public Long findSalesOrderByMonth(Long emp_no, Long brn_id, Long percent, String month, String year);

    public List<Object[]> findSalesmenWithHeigherTargetByMonths(String monthFrom, String yearFrom, String monthTo, String yearTo);

    public Object[] findEmpCountAndTrgtPerMonths(Long brn_id, String monthsFrom, String yearsFrom, String monthsTo, String yearsTo);

    public Long findMaxFundBorrowSerial();

    public List<HrBorrowZamalaHd> findPreviousFundBorrowForEmp(HrEmpInfo emp_no);

    public List<HrBorrowZamalaHd> findAllFundBorrow(HrEmpInfo emp_no);

    public HrFundBorrowSummary findFundBorrowSummaryByHdId(HrBorrowZamalaHd hd_id);

    public Long findNextIdFromDmsTransportOrdrParent();

    public void persistDmsTransportOrdrParent(DmsTransportOrdrParent dmsTransportOrdrParent);

    public List<HrGzaEmpMngDt> getGzaEmpMngDtForMng(Long mng_no, String emp_name);

    public List<String> getGzaEmpByEmpNameSubstrAndMngNo(Long mng_no, String emp_name);

    public List<HrProfilePrifilage> findProfilePrivilages(Long grp_id, Long job_id, Long loc_id, Long dept_id, Long emp_no, Long menu_id);

    public List<HrJobGrp> findJobGrpList();

    public List<HrManagement> findDeptList();

    public List<HrJobs> findJobList();

    public List<HrLocation> findLocationList();

    public List<HrEmpInfo> findEmpList();

    public List<HrMenuTable> findMenuList();

    public List<HrProfilePrifilage> findAllPrivilages(Long grp_id, Long job_id, Long loc_id, Long dept_id, Long emp_no, Long menu_id, String orderByList);

    public Long checkParentPrivilages(Long grp_id, Long job_id, Long loc_id, Long dept_id, Long emp_no, Long menu_id);

    public void persistProfilePrivilage(HrProfilePrifilage hrProfilePrifilage);

    public Long findHrMenuTableNextId();

    public Long findHrProfilePrifilageNextId();

    public void deleteProfilePrifilage(HrProfilePrifilage hrProfilePrifilage);

    public void persistAdvanceRequest(HrAdvanceRequest hrAdvanceRequest);

    public List<HrAdvanceRequest> findEmpAdvanceRequests(HrEmpInfo hrEmpInfo);

    public void mergeAdvanceRequest(HrAdvanceRequest hrAdvanceRequest);

    public List<HrAdvanceRequest> findAdvanceRequestNeedGuaranteeApprove(HrEmpInfo hrEmpInfo, Date fromDate);

    public List<HrAdvanceRequest> findAdvanceRequestNeedDeptMngApprove(HrEmpInfo hrEmpInfo, Date fromDate);

    public List<Object[]> profilePrivilageReport(Long grp_id, Long job_id, Long loc_id, Long dept_id, Long emp_no, Long menu_id, String orderByList);

    public List<HrMenuTable> findAllMenuList();

    public List<Object[]> findMenuToConstruct(Long grp_id, Long job_id, Long loc_id, Long dept_id, Long emp_no, Long menu_id);

    public HrFundAdvanceSetup findAdvanceSetup();

    public Long chkFundAdvanceRequest(Long empNo, Long advanceId, Long guarantee, Long amount);

    public List<HrAdvanceRequest> findAdvanceRequestNeedRespApprove(Date fromDate);

    public void persistAdvanceZamalaHd(HrAdvanceZamalaHd hrAdvanceZamalaHd);

    public Long findSerialOfAdvanceZamalalaHd();

    public List<Object[]> findFundBorrowMonthlyBudget();

    public Object[] findFundAdvanceMonthlyBudget();

    public Object[] findBrnAndGovern(Long loc_id);

    public List<HrZamalaGiftSrfDt> findFundEmpGifts(HrEmpInfo hrEmpInfo);

    public List<HrAdvanceZamalaDt> findEmpAdvanceZamalaDt(HrEmpInfo empNo);

    public List<HrBorrowZamalaHd> findAllFundBorrowForGuarantee(HrEmpInfo emp_no);

    public List<HrAdvanceZamalaDt> findGuaranteeAdvanceZamalaDt(Long empNo);

    public HrUsers findHrUserByName(String userName);

    public String getDercrybtedHrPassword(String pass);

    public void persistProfileAccessLog(HrProfileAccessLog hrProfileAccessLog);

    public Long chkLocInvest(Long empNo);

    public List<HrArea> findAllArea();

    public List<HrRegion> findAllRegion();

    public List<DmsTransportOrdrHd> findTrnsOrdrAdvanceList(Long manager_id);

    public Long findHrEmpLocInvestId();

    public void persistHrEmpInvestLoc(e.HrEmpLocInvest hrEmpLocInvest);

    public List<HrNewEmpExceed3monthDt> getNewEmpExceed3MonthDt(Long mng, Long dept, Long loc, Long grp, Long job);

    public HrNewEmpExceed3Months findEmpExceed3MonthsById(Long id);

    public void mergeHrNewEmpExceed3Months(HrNewEmpExceed3Months hrNewEmpExceed3Months);

    public List<HrCheckupDutyHd1> findCheckUpDutyHdForEmp1(HrEmpInfo entryId, Date trnsDate);

    public List<Object[]> findCheckupDutyTitles(Long empNo);

    public void mergeHrCheckUpDutyHd1(HrCheckupDutyHd1 hrCheckupDutyHd);

    public void persistHrCheckUpDutyHd1(HrCheckupDutyHd1 hrCheckupDutyHd);

    public void mergeHrCheckUpDutyDt1(HrCheckupDutyDt1 hrCheckupDutyDt);

    public void persistHrCheckUpDutyDt1(HrCheckupDutyDt1 hrCheckupDutyDt);

    public void removeHrCheckupDutyEmployees(HrCheckupDutyEmployees hrCheckupDutyEmployees);

    public List<HrCheckupDutyHd1> getCheckUpDutiesNotApproved1();

    public void mergeHrCheckupDutyEmployees(HrCheckupDutyEmployees hrCheckupDutyEmployees);

    public List<HrManNotesHd> findHrManNotes(Long empNo);

    public void persistHrManNotesHd(HrManNotesHd hrManNotesHd);

    public void mergeHrManNotesHd(HrManNotesHd hrManNotesHd);

    public List<Object[]> findManNotesTitles(Long empNo);

    public List<HrHafezSehyDt> findHrHafezSehyDts(Long empNo);

    public List<HrManNotesHd> findManNotesNotApproved();

    public void mergeHrManNotesDt(HrManNotesDt hrManNotesDt);

    public List<HrCheckupDutyHd1> findCheckupDutyForAddAction(HrEmpInfo emp, HrLocation loc, Long dept_id, Date fromDate, Date toDate, int approved);

    public Object[] findBasicTaxData(String trnsMonth, String trnsYear, String empNo);

    public DmsUsers findDmsUserByHrId(String empNo);

    public List<HrInquestHd> getAllInquests();

    public void mergeHrInquestDt(HrInquestDt hrInquestDt);

    public void mergeHrInquestHd(HrInquestHd hrInquestHd);

    public List<HrInquestHd> getAllMngApprovedInquests();

    public List<HrLocation> findSpecificLocationList(Long current_loc);

    public List<Object[]> getOvertimeToApprove(Date fromDate, Date toDate, Long mngNo, Date approvedFromDate);

    public HrManualEffectionDt getManaEffectionDtById(Long id);

    public void mergeHrManaualEffectionDt(HrManualEffectionDt manualEffectionDt);

    public List<HrLocation> getDutyLocations();

    public List<String> findShowNameNameList(Long brnId);

    public List<String> findTrgtYearList();

    public List<Object[]> findEmpTarget(e.HrEmpInfo hrEmpInfo, String month, String year);

    public List<Object[]> findShowroomsPerCrmkName();

    public List<Object[]> findEmpQtyTrgetYearVuListInPeriod(String monthsFrom, String yearsFrom, String monthsTo, String yearsTo, Long loc_id);

    public List<Object[]> findBrnQtyTrgetYearVuInPeriod(String monthsFrom, String yearsFrom, String monthsTo, String yearsTo, Long loc_id);

    public List<CrmkActivatePreviousDocReq> getActivatePreviousDocReqs(HrEmpInfo empNo, Date trnsDate);

    public List<CrmkActivatePreviousDocVu> getActivatePreviousDocVus(Long docType, Long docBrnId, String crmkSehy, Long docNo, String docHandNo, Long docPrdId);

    public void saveCrmkActivatePreviousDocReq(CrmkActivatePreviousDocReq crmkActivatePreviousDocReq);

    public List<CrmkActivatePreviousDocReq> getPreviousDocReqToActivate(Long locId);

    public void activatePreviousDoc(CrmkRsrvDt crmkRsrvDt);

    public CrmkRsrvDt getCrmkRsrvDt(Long rsrvId);

    public void mergeCrmkActivatePreviousDocReq(CrmkActivatePreviousDocReq crmkActivatePreviousDocReq);

    public Integer chkCustomVote(java.lang.Long empNo);

    public void saveProfileCustomVote(HrProfileCustomVote hrProfileCustomVote);

    public void saveHrProfileAlertRecivers(HrProfileAlertReceiver hrProfileAlertReceiver);

    public void deleteAllReciversForAlert(HrProfileAlertHd hrProfileAlertHd);

    public List<HrEmpInfo> findDriversList(Long jobId);

    public DmsDrivers findDriverById(Long driverId);

    public List<HrActiveAlert> findActiveAlerts(Long empNo);

    public void deleteAlertReciver(HrProfileAlertReceiver hrProfileAlertReceiver);

    public List<HrCheckupDutyHd2> findCheckUpDutyHdForEmp2(HrEmpInfo entryId, Date trnsDate);

    public void persistHrCheckUpDutyHd2(HrCheckupDutyHd2 hrCheckupDutyHd);

    public void mergeHrCheckUpDutyHd2(HrCheckupDutyHd2 hrCheckupDutyHd);

    public HrCheckupDutySetupHd findCheckupDutySetupHdByEmpNo(Long empNo);

    public List<HrCheckupDutyHd2> getCheckUpDutiesNotApproved2();

    public List<HrCheckupDutyLocations> getCheckUpDutyBonus(HrLocation loc, Long empNo, Long deptId);

    public void mergeHrCheckupDutyEmp2(HrCheckupDutyEmp2 hrCheckupDutyEmp);

    public void mergeHrCheckUpDutyDt2(HrCheckupDutyDt2 hrCheckupDutyDt2);

    public Long chkCheckupDuty2(Long v_emp_no, Date v_trns_date, Long v_loc_id);

    public CrmkBranch findShowroomByNo(Long no);

    public List<CrmkOrdrHd> findForOrdrPercentage(Long no, char crmk_sehy, Long brn_id, Date ordrDate, String clnt_name);

    public List<CrmkEmpHstry> findAllCrmkEmployees();

    public void deleteCrmkCOrderEmp(CrmkCOrdrEmp crmkCOrdrEmp);

    public void deleteCrmkDOrderEmp(CrmkDOrdrEmp crmkDOrdrEmp);

    public void deleteCrmkSOrderEmp(CrmkSOrdrEmp crmkSOrdrEmp);

    public void saveCrmkCOrderEmp(CrmkCOrdrEmp crmkCOrdrEmp);

    public void saveCrmkDOrderEmp(CrmkDOrdrEmp crmkDOrdrEmp);

    public void saveCrmkSOrderEmp(CrmkSOrdrEmp crmkSOrdrEmp);

    public Long getCrmkOrderEmpId(String crmk_dcre, Long brnId);

    public void deleteCrmkOrdrEmp(Long ordrId, String crmkSehy);

    public CrmkEmpHstry findCrmkEmployeeBtId(Long empId);

    public void saveCrmkOrdrEmpLog(CrmkOrdrEmpLog crmkOrdrEmpLog);

    public HrDynAlertTemplateHd findActiveAlert();

    public void persistHrDynAlertTemplateHd(HrDynAlertTemplateHd hrDynAlertTemplateHd);

    public List<HrDynAlertTemplateHd> findAlertList();

    public void removeAlertDT(HrDynAlertTemplateDt hrDynAlertTemplateDt);

    public void updateAlertHD(HrDynAlertTemplateHd hrDynAlertTemplateHd);

    public void persistHrDynAlertTemplateDt(HrDynAlertTemplateDt hrDynAlertTemplateDt);

    public List<HrDynAlertTemplateHd> findAlertsNotApplied(Long empNo);

    public void persistHrDynAlertHd(HrDynAlertHd hrDynAlertHd);

    public List<HrLocationIpMapping> findAllLOocationIpMapping();

    public List<CrmkOrdrSader> findCrmkOrdrSaderNotPrinted(Long loc,Long brnId,Long ordrNo,Character crmkSehy);

    public List<HrDutyTrnsHdVu> findDutyToApproveList(Long mng, Long dept, Long loc, Long grp, Long job);

    public void updateDutyDt(HrDutyTrnsDt hrDutyTrnsDt);

    public List<HrDutyTrnsHd> findDutyToFollowUp(HrEmpInfo empNo,Date trnsDate);

    public HrEgadaBonus findEmpEgadaBonus(Long empNo,Long trnsMonth,Long trnsYear);

    public HrEgadaSetup findEgadaValue(Long jobGrpId);

    public HrMainTrgtLevelsHd checkMainTrgtEmp(HrEmpInfo hrEmpInfo);

    public List<HrMainTrgtLevelsHd> getMainTrgtLevelList();

    public List<Object[]> getEmpActiveAlerts(Long dept_id,Long job_id,Long loc_id,Long job_grp_id,Long EMP_NO);

    public void saveHrMainTrgtLevelHd(HrMainTrgtLevelsHd hrMainTrgtLevelsHd);

    public void saveHrMainTrgtLevelDt(HrMainTrgtLevelsDt hrMainTrgtLevelsDt);

    public void removeHrMainTrgtLevelDt(HrMainTrgtLevelsDt hrMainTrgtLevelsDt);

    public void removeHrMainTrgtLevelHd(HrMainTrgtLevelsHd hrMainTrgtLevelsHd);

    public List<HrHafezSehyMngDt> getHafezSehyMng(Long empNo);

    public List<Object[]> getPoundHafezRules();

    public List<HrPrvYearTransHolidays> getPreviousEmpHolidays(Long empNo);

    public void saveMosqueCrmkRequest(HrMosqueCrmkReq mosqueCrmkReq);

    public List<HrMosqueCrmkReq> getMosqueCrmkReqs();

    public void updateHrMosqueCrmkReq(HrMosqueCrmkReq hrMosqueCrmkReq);

    public boolean chkEmpAttendance(Date trnsDate,Long empNo);

    public Long getMosqueReqSerialNo();
}
