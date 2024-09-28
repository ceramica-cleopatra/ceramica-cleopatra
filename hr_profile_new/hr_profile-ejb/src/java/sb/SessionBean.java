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
import e.CrmkColor;
import e.CrmkCrmkDekala;
import e.CrmkCrmkSize;
import e.CrmkCrmkType;
import e.CrmkCurrentBrnTrgt;
import e.CrmkDOrdrEmp;
import e.CrmkDcreDekala;
import e.CrmkDcreSize;
import e.CrmkDcreType;
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
import e.CrmkSehyGrp;
import e.CrmkSehyName;
import e.CrmkSehyType;
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
import e.HrEmpLocInvest;
import e.HrEmpMangers;
import e.HrEmpSal;
import e.HrEmpSalary;
import e.HrEmpTime;
import e.HrFaculty;
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
import e.HrLocationInvestSetting;
import e.HrLocationInvestSettingVu;
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
import e.ShowBathHd;
import e.TaxDesc;
import e.Uni;
import e.Vote;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author ahmed abbas
 */
@Stateless
public class SessionBean implements SessionBeanLocal, Serializable {

    @PersistenceContext(unitName = "hr_profile-ejbPU")
    private EntityManager em;

    @Override
    public HrEmpInfo finduserbyid(Long emp_no) {
        try {
            System.out.println("sessionBean:" + emp_no);
            return (HrEmpInfo) em.createNamedQuery("HrEmpInfo.finduserbyid").setParameter("emp", emp_no).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch(Exception ex){
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hr_profile-ejbPU");

        // Create EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return (HrEmpInfo) entityManager.createNamedQuery("HrEmpInfo.finduserbyid").setParameter("emp", emp_no).getSingleResult();
        }
    }
    
    @PostConstruct
    public void init() {
        // Initialization logic here
        System.out.println("SessionBean initialized.");
    }

    @Override
    public List<HrEmpInfo> findChatEmp(Long emp, Long dept, Long loc, Long job) {
        return em.createNamedQuery("HrEmpInfo.findchatemp").setParameter("emp", emp).setParameter("dept", dept).setParameter("loc", loc).setParameter("job", job).getResultList();
    }

    @Override
    public List<HrEmpInfo> findAlternativeEmp(Long emp, Long dept, Long loc) {
        //Query q=em.createNamedQuery("HrEmpInfo.findAlternativeEmp").setParameter("emp", emp).setParameter("dept", dept).setParameter("loc", loc);
        Query query = em.createNativeQuery("select * from Hr_Emp_Info_full o where o.emp_status='R' and  ((o.loc_Id=" + loc + " and 1<=(select count(*) from  Hr_Emp_Info x where x.dept_Id=" + dept + " and x.loc_Id=" + loc + " and x.emp_No<>" + emp + ") "
                + " and o.dept_Id=" + dept + " and o.emp_No<>" + emp + ") or (o.dept_Id=" + dept + " and o.emp_No<>" + emp + " and 0=(select count(*) from  Hr_Emp_Info y where y.dept_Id=" + dept + " and y.loc_Id=" + loc + " and y.emp_No<>" + emp + ")))", HrEmpInfo.class);
        return query.getResultList();
    }

    @Override
    public HrEmpHd findempbyid(Long emp_no) {
        return (HrEmpHd) em.createNamedQuery("HrEmpHd.findAll").setParameter("emp_id", emp_no).getSingleResult();
    }

    @Override
    public void mergeHrEmpHd(HrEmpHd hrEmpHd) {
        em.merge(hrEmpHd);
        em.flush();
    }

    @Override
    public List<HrPrayTimes> getPrayTimeToday(Date d) {
        return em.createNamedQuery("HrPrayTimes.findByTrnsDate").setParameter("trnsDate", d).getResultList();
    }

    @Override
    public List<HrMenuTable> findAllRoot(Long emp) {
        return em.createNamedQuery("HrMenuTable.findAllRoot").setParameter("emp", emp).getResultList();
    }

    @Override
    public List<HrMenuTable> findAllChild(Long emp) {
        Cache cache = em.getEntityManagerFactory().getCache();
        System.out.println("cache.contain should return true: " + cache.contains(HrMenuTable.class, 1));

        System.out.println("cache.contain should return false: " + cache.contains(HrMenuTable.class, 1));

        return em.createNamedQuery("HrMenuTable.findAllChild").setParameter("emp", emp).getResultList();
    }

    @Override
    public Long thereIsChild(Long pid, Long emp) {
        return (Long) em.createNamedQuery("HrMenuTable.thereIsChild").setParameter("emp", emp).setParameter("pid", pid).getSingleResult();
    }

    @Override
    public List<Object[]> findAllEff(Long emp_id, Date date_from, Date date_to) {
        return em.createNamedQuery("HrEmpEffectionVu.findAllById").setParameter("emp_id", emp_id).setParameter("date_from", date_from, TemporalType.DATE).setParameter("date_to", date_to, TemporalType.DATE).getResultList();
    }

    @Override
    public List<HrMangaerialDecisions> getManagerialDecisions() {
        return em.createNamedQuery("HrMangaerialDecisions.findAll").getResultList();
    }

    @Override
    public HrHolidayType find_holiday_By_Id(Long id) {
        return (HrHolidayType) em.createNamedQuery("HrHolidayType.findById").setParameter("id", id).getSingleResult();
    }

    @Override
    public List<Object[]> find_holiday_types() {
        return em.createNamedQuery("HrHolidayType.findAll").getResultList();
    }

    public List<HrHolidayRequest> find_holiday_request(Long emp_id, Date from_date) {
        return em.createNamedQuery("HrHolidayRequest.findAll").setParameter("emp_id", emp_id).setParameter("from_date", from_date, TemporalType.DATE).getResultList();
    }

    public void mergeHrHolidayRequest(HrHolidayRequest hrholidayrequest) {
        em.merge(hrholidayrequest);
    }

    public String chk_holiday_avilability(Long emp_no, Long year, Long holiday_type, Long days_no) {
        Query q = em.createNativeQuery("select get_emp_holiday_avalibilty(?,?,?,?) from dual");
        q.setParameter(1, emp_no);
        q.setParameter(2, year);
        q.setParameter(3, holiday_type);
        q.setParameter(4, days_no);
        return (String) q.getSingleResult();
    }

    public Long chk_holiday_request(Long emp_no, Date from_date, Date to_date, Long hol_type, Long req_id) {
        Query q = em.createNativeQuery("select hr_holiday_request_chk(?,?,?,?,?) from dual");
        q.setParameter(1, emp_no);
        q.setParameter(2, from_date);
        q.setParameter(3, to_date);
        q.setParameter(4, hol_type);
        q.setParameter(5, req_id);
        return Long.parseLong(q.getSingleResult().toString());
    }

    public Long chk_authorize_request(Date auth_date, Long emp_no, Long req_id, Long min_sum) {
        Query q = em.createNativeQuery("select hr_authorize_req_chk(?,?,?,?) from dual");
        q.setParameter(1, auth_date);
        q.setParameter(2, emp_no);
        q.setParameter(3, req_id);
        q.setParameter(4, min_sum);
        return Long.parseLong(q.getSingleResult().toString());
    }

    public Long cntOfHolidays10(Long emp_id, Long m, Long y) {
        return (Long) em.createNamedQuery("HrManualEffectionDt.cntOfHolidays10").setParameter("emp_id", emp_id).setParameter("m", m).setParameter("y", y).getSingleResult();
    }

    public HrMontlySalaryCalcPeriod find_month_period(Date date) {
        return (HrMontlySalaryCalcPeriod) em.createNamedQuery("HrMontlySalaryCalcPeriod.findAll").setParameter("date", date, TemporalType.DATE).getSingleResult();
    }

    public Long cntOfSalCalc(Date date_from, Date date_to) {
        return (Long) em.createNamedQuery("HrSalCalc.chkReview").setParameter("from_date", date_from).setParameter("to_date", date_to).getSingleResult();
    }

    public Long cntOfSalHistory(Date date_from, Date date_to) {
        return (Long) em.createNamedQuery("HrSalHistory.chkOldDate").setParameter("from_date", date_from).setParameter("to_date", date_to).getSingleResult();
    }

    public Long cntOfAuth(Long emp_id, Date date_from, Date date_to) {
        return (Long) em.createNamedQuery("HrManualEffectionDt.cntOfAuth").setParameter("emp_id", emp_id).setParameter("from_date", date_from).setParameter("to_date", date_to).getSingleResult();
    }

    public Long chkHolidayEntry(Long emp_id, Date date_from, Date date_to, Long id) {
        return (Long) em.createNamedQuery("HrHolidayRequest.chkHolidayEntry").setParameter("emp_id", emp_id).setParameter("from_date", date_from).setParameter("to_date", date_to).setParameter("id", id).getSingleResult();
    }

    public Long chkTrnsEntry(Long emp_id, Date date_from, Date date_to) {
        return (Long) em.createNamedQuery("HrManualEffectionDt.chkTrnsEntry").setParameter("emp_id", emp_id).setParameter("from_date", date_from).setParameter("to_date", date_to).getSingleResult();
    }

    public void persistHrHolidayRequest(HrHolidayRequest hrHolidayRequest) {
        em.persist(hrHolidayRequest);
    }

    public Long findRequestId() {
        return (Long) em.createNamedQuery("HrHolidayRequest.getMax").getSingleResult();
    }

    public List<HrProfileMessage> find_messages() {
        return em.createNamedQuery("HrProfileMessage.findAll").getResultList();
    }

    public List<HrProfileMessage> find_img() {
        return em.createNamedQuery("HrProfileMessage.findAllImg").getResultList();
    }

    public List<HrBorrowHd> findAllBorrow(Long emp_no) {
        return em.createNamedQuery("HrBorrowHd.findAll").setParameter("emp_no", emp_no).getResultList();
    }

    public List<HrBorrowDt> findAllBorrowDt() {
        return em.createNamedQuery("HrBorrowDt.findAll").getResultList();
    }

    public Long find_suggest_id() {
        return (Long) em.createNamedQuery("EmpSuggest.findmax").getSingleResult();
    }

    public void persist_suggestion(EmpSuggest empSuggestion) {
        em.persist(empSuggestion);
    }

    public void send_mail(String send_to, String subject, String txt) {
        Query q = em.createNativeQuery("call send_mail(?,?,?)");
        q.setParameter(1, send_to);
        q.setParameter(2, subject);
        q.setParameter(3, txt);
        q.executeUpdate();

    }

    public void updateReadDoneMsg(String entity, Long msgId, char val, Long usercode) {
        Query q = em.createNativeQuery("call HrUpdateReadDoneMsg(?,?,?,?)");
        q.setParameter(1, entity);
        q.setParameter(2, msgId);
        q.setParameter(3, val);
        q.setParameter(4, usercode);
        q.executeUpdate();

    }

    public List<HrSchedule> findAll(Long emp_no) {
        return em.createNamedQuery("HrSchedule.findAll").setParameter("emp", emp_no).getResultList();
    }

    public void mergeHrSchedule(HrSchedule hrSchedule) {
        em.merge(hrSchedule);
    }

    public void persistHrSchedule(HrSchedule hrSchedule) {
        em.persist(hrSchedule);
    }

    public Long getseq() {
        return (Long) em.createNamedQuery("HrSchedule.getseq").getSingleResult();
    }

    public HrSchedule getschedule(Long id) {
        return (HrSchedule) em.createNamedQuery("HrSchedule.getSchedule").setParameter("x", id).getSingleResult();
    }

    public Long findOthersInBrn(Long emp_no, Long brn_id, int x) {
        return (Long) em.createNamedQuery("EmpQtyTrgetVu.findOthersInBrn").setParameter("emp_no", emp_no).setParameter("brn_id", brn_id).setParameter("x", x).getSingleResult();

    }

    public Long findOthers(Long emp_no, Long brnId) {
        return (Long) em.createNamedQuery("EmpQtyTrgetVu.findOthers").setParameter("emp_no", emp_no).setParameter("brn_id", brnId).getSingleResult();
    }

    public EmpQtyTrgetVu findEmpTrgt(HrEmpInfo emp_no) {
        try {
            return (EmpQtyTrgetVu) em.createNamedQuery("EmpQtyTrgetVu.findEmp").setParameter("emp_no", emp_no).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<EmpQtyTrgetVu> findAllInBrn(Long brn_id) {
        return em.createNamedQuery("EmpQtyTrgetVu.findAllInBrn").setParameter("brn_id", brn_id).getResultList();
    }

    public List<Object[]> find_trgt_hist(Long emp_no) {
        return em.createNamedQuery("HrTrgtHist.findAll").setParameter("emp_no", emp_no).getResultList();
    }

    public HrNationality genationailty(Long id) {
        return (HrNationality) em.createNamedQuery("HrNationality.findAll").setParameter("id", id).getSingleResult();
    }

    public HrInsuranceOffice getinsoffice(Long id) {
        return (HrInsuranceOffice) em.createNamedQuery("HrInsuranceOffice.findAll").setParameter("id", id).getSingleResult();
    }

    public HrMilitarily getmilitarily(Long id) {
        return (HrMilitarily) em.createNamedQuery("HrMilitarily.findAll").setParameter("id", id).getSingleResult();
    }

    public List<Object[]> findAllEducation(Long emp_id) {
        return em.createNamedQuery("HrEmpEducation.findAllById").setParameter("emp_id", emp_id).getResultList();
    }

    public List<Object[]> findAllJob(Long emp_id) {
        return em.createNamedQuery("HrEmpJob.findAllById").setParameter("emp_id", emp_id).getResultList();
    }

    public Long cntOfSalCalc(Date authorize_date) {
        return (Long) em.createNamedQuery("HrSalCalc.chkReviewForAuthorize").setParameter("authorize_date", authorize_date).getSingleResult();
    }

    public Long cntOfSalHistory(Date authorize_date) {
        return (Long) em.createNamedQuery("HrSalHistory.chkOldDateForAuthorize").setParameter("authorize_date", authorize_date).getSingleResult();
    }

    public Long chkCardCreated(Long emp, Date authorize_date) {
        return (Long) em.createNamedQuery("HrEmpEffectionVu.chkCard").setParameter("emp", emp).setParameter("authorize_date", authorize_date).getSingleResult();
    }

    public List<HrAuthorization> CalcSumAuthurization(Long emp, Date from_date, Date to_date) {
        return em.createNamedQuery("HrAuthorization.calcAuthorizeSum").setParameter("emp", emp).setParameter("from_date", from_date).setParameter("to_date", to_date).getResultList();
    }

    public Long getAuthorizeMinutesSum(Long emp, Date from_date, Date to_date, Long id) {
        return (Long) em.createNamedQuery("HrAuthorizeRequest.minutesSum").setParameter("emp", emp).setParameter("from_date", from_date).setParameter("to_date", to_date).setParameter("id", id).getSingleResult();
    }

    public Long getAuthorizeRequestMax() {
        return (Long) em.createNamedQuery("HrAuthorizeRequest.findMax").getSingleResult();
    }

    public void authorizeRequestPersist(HrAuthorizeRequest hrAuthorizeRequest) {
        em.persist(hrAuthorizeRequest);
    }

    public List<HrAuthorizeRequest> getAuthorizeRequestList(Long emp, Date authorize_date) {
        return em.createNamedQuery("HrAuthorizeRequest.findAll").setParameter("emp", emp).setParameter("authorize_date", authorize_date).getResultList();
    }

    public void mergeHrAuthorizeRequest(HrAuthorizeRequest hrAuthorizeRequest) {
        em.merge(hrAuthorizeRequest);
    }

    public Long chkAuthorizeRequestExist(Date authorize_date, Long emp, Long id) {
        return (Long) em.createNamedQuery("HrAuthorizeRequest.chkAuthorizeExist").setParameter("id", id).setParameter("authorize_date", authorize_date).setParameter("emp", emp).getSingleResult();
    }

    public List<HrHolidayRequestDt> getHolidayRequestDt(Long mng, Long dept, Long loc, Long grp, Long job) {
        return em.createNamedQuery("HrHolidayRequestDt.findAll").setParameter("mng", mng).setParameter("dept", dept).setParameter("loc", loc).setParameter("grp", grp).setParameter("job", job).getResultList();
    }

    public HrHolidayRequest findHolidayRequestById(Long id) {
        return (HrHolidayRequest) em.createNamedQuery("HrHolidayRequest.findById").setParameter("id", id).getSingleResult();
    }

    public Long chkCardExist(Long emp, Long month, Long year) {
        return (Long) em.createNamedQuery("HrManualEffectionHd.chkCardExist").setParameter("emp", emp).setParameter("m", month).setParameter("y", year).getSingleResult();
    }

    public void addEmpCard(Long emp, Long m, Long y) {
        Query q = em.createNativeQuery("call create_emp_month(?,?,?)");
        q.setParameter(1, emp);
        q.setParameter(2, m);
        q.setParameter(3, y);
        q.executeUpdate();
    }

    public void mergeHrEffectionManualDt(HrManualEffectionDt hrManualEffectionDt) {
        em.merge(hrManualEffectionDt);
    }

    public HrManualEffectionDt getHrManualEffectionDt(Long hd_id, Date trns_date) {
        return (HrManualEffectionDt) em.createNamedQuery("HrManualEffectionDt.updateHoliday").setParameter("hd_id", hd_id).setParameter("trns_date", trns_date).getSingleResult();
    }

    public HrManualEffectionHd getHrManualEffectionHd(Long emp, Long m, Long y) {
        return (HrManualEffectionHd) em.createNamedQuery("HrManualEffectionHd.findAll").setParameter("emp", emp).setParameter("y", y).setParameter("m", m).getSingleResult();
    }

    public List<HrManualEffectionDt> getHrManualEffectionDt(Long emp, Date from_date, Date to_date) {
        return em.createNamedQuery("HrManualEffectionDt.updateHolidays").setParameter("emp", emp).setParameter("from_date", from_date).setParameter("to_date", to_date).getResultList();
    }

    public Long chkManualEffectionDt(Long hd_id, Date trns_date) {
        return (Long) em.createNamedQuery("HrManualEffectionDt.chkUpdateHoliday").setParameter("hd_id", hd_id).setParameter("trns_date", trns_date).getSingleResult();
    }

    public List<HrAuthorizeRequestDt> getAuthorizeRequestDt(Long mng, Long dept, Long loc, Long grp, Long job) {
        return em.createNamedQuery("HrAuthorizeRequestDt.findAll").setParameter("mng", mng).setParameter("dept", dept).setParameter("loc", loc).setParameter("grp", grp).setParameter("job", job).getResultList();
    }

    public HrAuthorizeRequest getAuthorizeRequestById(Long id) {
        return (HrAuthorizeRequest) em.createNamedQuery("HrAuthorizeRequest.findById").setParameter("id", id).getSingleResult();
    }

    public List<HrGzaEmpMngDt> getGzaEmpMngDt(Long dept, Long loc, Long grp, Long job, String emp_name) {
        return em.createNamedQuery("HrGzaEmpMngDt.findAll").setParameter("dept", dept).setParameter("loc", loc).setParameter("grp", grp).setParameter("job", job).setParameter("emp_name", emp_name).getResultList();
    }

    public List<HrGzaEmpMngDt> getGzaEmpMngDtForMng(Long mng_no, String emp_name) {
        return em.createNamedQuery("HrGzaEmpMngDt.findAllEmpUnderMng").setParameter("mng_no", mng_no).setParameter("emp_name", emp_name).getResultList();
    }

    public List<String> getEmpByEmpNameSubstr(Long dept, Long loc, Long grp, Long job, String emp_name) {
        System.out.println("'%" + emp_name + "%'");
        return em.createNamedQuery("HrGzaEmpMngDt.findByEmpSubstr").setParameter("dept", dept).setParameter("loc", loc).setParameter("grp", grp).setParameter("job", job).setParameter("emp_name", "%" + emp_name.trim() + "%").getResultList();
    }

    public List<String> getGzaEmpByEmpNameSubstrAndMngNo(Long mng_no, String emp_name) {
        return em.createNamedQuery("HrGzaEmpMngDt.findByEmpSubstrAndMngNo").setParameter("mng_no", mng_no).setParameter("emp_name", "%" + emp_name.trim() + "%").getResultList();
    }

    public List<String> getScheduleEmpByEmpNameSubstr(Long dept, Long loc, Long grp, Long job, String emp_name) {
        System.out.println("'%" + emp_name + "%'");
        return em.createNamedQuery("HrEmpMangers.findEmpByEmpSubstr").setParameter("dept", dept).setParameter("loc", loc).setParameter("grp", grp).setParameter("job", job).setParameter("emp_name", "%" + emp_name.trim() + "%").getResultList();
    }

    public List<HrGzaReason> getGzaReasons() {
        return em.createNamedQuery("HrGzaReason.findAll").getResultList();
    }

    public Long getGzaHdMax() {
        return (Long) em.createNamedQuery("HrGzaHd.findMax").getSingleResult();
    }

    public Long getGzaDtMax() {
        return (Long) em.createNamedQuery("HrGzaDt.findMax").getSingleResult();
    }

    public void persistGzaHd(HrGzaHd hrGzaHd) {
        em.persist(hrGzaHd);
    }

    public void persistGzaDt(HrGzaDt hrGzaDt) {
        em.persist(hrGzaDt);
    }

    public Long chkInvestigate(Long investigate_id, Long emp) {
        return (Long) em.createNamedQuery("HrInvestigateEmp.chkcnt").setParameter("investigate_id", investigate_id).setParameter("emp", emp).getSingleResult();
    }

    public List<HrInvestigateHd> getHrInvestigateHd(Date current_date, Long dept, Long job, Long grp, Long loc) {
        return em.createNamedQuery("HrInvestigateHd.findAll").setParameter("current_date", current_date).setParameter("dept", dept).setParameter("job", job).setParameter("grp", grp).setParameter("loc", loc).getResultList();
    }

    public List<HrInvestigateDt> getHrInvestigateDt(Long id) {
        return em.createNamedQuery("HrInvestigateDt.findAll").setParameter("hd_id", id).getResultList();
    }

    public void persistHrInvestigateEmp(HrInvestigateEmp hrInvestigateEmp) {
        em.persist(hrInvestigateEmp);
    }

    public Long getHrInvestigateEmpMax() {
        return (Long) em.createNamedQuery("HrInvestigateEmp.findMax").getSingleResult();
    }

    public List<Object[]> getTotalRadioResult(HrInvestigateHd hrInvestigateHd) {
        return (List<Object[]>) em.createNamedQuery("HrInvestigateDt.findTotalRadioResult").setParameter("hd_id", hrInvestigateHd).getResultList();
    }

    public Long findTotalRadioCount(HrInvestigateHd hrInvestigateHd) {
        return (Long) em.createNamedQuery("HrInvestigateEmp.findTotalRadioCount").setParameter("hd_id", hrInvestigateHd).getSingleResult();
    }

    public List<Object[]> getTotalSliderResult(HrInvestigateHd hrInvestigateHd) {
        return (List<Object[]>) em.createNamedQuery("HrInvestigateDt.findTotalSlidaerResult").setParameter("hd_id", hrInvestigateHd).getResultList();
    }

    public HrEmpSal getHrEmpSal(Long emp) {
        return (HrEmpSal) em.createNamedQuery("HrEmpSal.findAll").setParameter("emp", emp).getSingleResult();
    }

    public List<HrTotalSalComponents> getHrTotalSalComponentses(Long empId) {
        return em.createNamedQuery("HrTotalSalComponents.findByEmpId").setParameter("empId", empId).getResultList();
    }

    public Long getMaxSalHistYear() {
        return (Long) em.createNamedQuery("HrSalHistory.maxYear").getSingleResult();
    }

    public Long getMaxSalHistMonth(Long y) {
        return (Long) em.createNamedQuery("HrSalHistory.maxMonth").setParameter("y", y).getSingleResult();
    }

    public HrSalHistory getHrSalHistory(Long emp, Long y, long m) {
        try {
            return (HrSalHistory) em.createNamedQuery("HrSalHistory.findAll").setParameter("emp", emp).setParameter("y", y).setParameter("m", m).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Double getHrCutoffVu(Long m, Long y, Long emp) {
        try {
            return (Double) em.createNamedQuery("HrCutoffVu.findAll").setParameter("y", y).setParameter("emp", emp).setParameter("m", m).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Long getHrTamyozHistory(Long m, Long y, Long emp) {
        try {
            return (Long) em.createNamedQuery("HrTamyozHistory.findAll").setParameter("y", y).setParameter("emp", emp).setParameter("m", m).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    public Double getSalesTamyoz(Long m, Long y, Long emp) {
        try {
            return (Double) em.createNamedQuery("HrTamyozHistory.salesTamyoz").setParameter("y", y).setParameter("emp", emp).setParameter("m", m).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Double getOtherTamyoz(Long m, Long y, Long emp) {
        try {
            return (Double) em.createNamedQuery("HrTamyozHistory.otherTamyoz").setParameter("y", y).setParameter("emp", emp).setParameter("m", m).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    public Double getHafez(Long m, Long y, Long emp) {
        try {
            return (Double) em.createNamedQuery("HrHafezHistory.findAll").setParameter("y", y).setParameter("emp", emp).setParameter("m", m).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<HrBadlHistory> hrBadlHistorys(Long m, Long y, Long emp) {
        try {
            return em.createNamedQuery("HrBadlHistory.findAll").setParameter("y", y).setParameter("emp", emp).setParameter("m", m).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<HrCutoffVu> getHrCutoffVus(Long m, Long y, Long emp) {
        return em.createNamedQuery("HrCutoffVu.findAllCutoff").setParameter("y", y).setParameter("emp", emp).setParameter("m", m).getResultList();
    }

    public List<Object[]> getHrGzaHds(Long mng, String emp) {
        try {
            return em.createNamedQuery("HrGzaDt.findAll").setParameter("mng", mng).setParameter("emp", emp).getResultList();
        } catch (NullPointerException e) {
            return null;
        }
    }

    public Long chkHolidaysNotConfirmed(Long mng, Long dept, Long loc, Long grp, Long job) {

        return (Long) em.createNamedQuery("HrHolidayRequestDt.chkHolidaysNotConfirmed").setParameter("mng", mng).setParameter("dept", dept).setParameter("loc", loc).setParameter("grp", grp).setParameter("job", job).getSingleResult();
    }

    public Long chkEmpReadGza(Long empNo) {
        return (Long) em.createNamedQuery("HrGzaDt.chkEmpReadGza").setParameter("empNo", empNo).getSingleResult();
    }

    public Long chkEmpReadApprovedGza(Long empNo) {
        return (Long) em.createNamedQuery("HrGzaDt.chkEmpReadApprovedGza").setParameter("empNo", empNo).getSingleResult();
    }

    public Long chkAuthorizeNotConfirmed(Long mng, Long dept, Long loc, Long grp, Long job) {
        return (Long) em.createNamedQuery("HrAuthorizeRequestDt.chkAuthorizeNotConfirmed").setParameter("mng", mng).setParameter("dept", dept).setParameter("loc", loc).setParameter("grp", grp).setParameter("job", job).getSingleResult();
    }

    public Long chkShiftNotConfirmed(Long mng, Long dept, Long loc, Long grp, Long job) {
        return (Long) em.createNamedQuery("HrShiftRequestDt.chkShiftNotConfirmed").setParameter("mng", mng).setParameter("dept", dept).setParameter("loc", loc).setParameter("grp", grp).setParameter("job", job).getSingleResult();
    }

    public List<Object[]> getHrGzaDtForEmp(Long emp) {
        return em.createNamedQuery("HrGzaDt.findAllForEmp").setParameter("emp", emp).getResultList();
    }

    public void mergeHrGzaDt(HrGzaDt hrGzaDt) {
        em.merge(hrGzaDt);
    }

    public List<HrGzaDt> getEmpNotReadGza(Long Emp) {
        return em.createNamedQuery("HrGzaDt.getEmpNotReadGza").setParameter("emp", Emp).getResultList();
    }

    public List<HrGzaDt> getEmpReadApprovedGza(Long Emp) {
        return em.createNamedQuery("HrGzaDt.getEmpReadApprovedGza").setParameter("emp", Emp).getResultList();
    }

    public HrMontlySalaryCalcPeriod getHrMontlySalaryCalcPeriodByTrnsMonthAndYear(Long trnsMonth, Long trnsYear) {
        return (HrMontlySalaryCalcPeriod) em.createNamedQuery("HrMontlySalaryCalcPeriod.findByTrnsMonth").setParameter("trnsMonth", trnsMonth).setParameter("trnsYear", trnsYear).getSingleResult();
    }

    public Long getHrScheduleDtId() {
        return (Long) em.createNamedQuery("HrScheduleDt.getMax").getSingleResult();
    }

    public void persistHrScheduleDt(HrScheduleDt hrScheduleDt) {
        em.persist(hrScheduleDt);
    }

    public HrScheduleDt getLastStatusOfSchedule(Long id) {
        try {
            return (HrScheduleDt) em.createNamedQuery("HrScheduleDt.findLastStatus").setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public HrEmpInfo findByEmpName(String emp_name) {
        try {
            return (HrEmpInfo) em.createNamedQuery("HrEmpInfo.findByEmpName").setParameter("emp_name", emp_name).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<HrSchedule> getMngSchedule(Long mng) {
        return em.createNamedQuery("HrSchedule.findAllForMng").setParameter("mng", mng).getResultList();
    }

    public List<HrTamyozDt> getPersonalyTamyoz(Long emp, Date from_date, Date to_date) {
        return em.createNamedQuery("HrTamyozDt.personalyTamyoz").setParameter("emp", emp).setParameter("from_date", from_date).setParameter("to_date", to_date).getResultList();
    }

    public Long chkInvetstigation(Date current_date, Long dept, Long job, Long grp, Long loc) {
        return (Long) em.createNamedQuery("HrInvestigateHd.chkInvestigation").setParameter("current_date", current_date).setParameter("dept", dept).setParameter("job", job).setParameter("grp", grp).setParameter("loc", loc).getSingleResult();
    }

    public List<HrEmpMangers> getMngEmp(Long dept, Long job, Long grp, Long loc) {
        return em.createNamedQuery("HrEmpMangers.findMngEmp").setParameter("dept", dept).setParameter("job", job).setParameter("grp", grp).setParameter("loc", loc).getResultList();
    }

    public List<HrShiftDt> getShift(Long dept,Long loc) {
        return em.createNamedQuery("HrShiftDt.findAll").setParameter("deptId", dept).setParameter("locId", loc).getResultList();
    }

    public HrShift getShiftById(Long id) {
        return (HrShift) em.createNamedQuery("HrShift.findById").setParameter("id", id).getSingleResult();
    }

    public Long chkShiftTableEntered(Long emp, Date from_date) {
        return (Long) em.createNamedQuery("HrEmpTime.chkcnt").setParameter("emp", emp).setParameter("from_date", from_date).getSingleResult();
    }

    public List<HrEmpTime> getEmpTimeLastStatus(Long emp) {
        return em.createNamedQuery("HrEmpTime.lastStatus").setParameter("emp", emp).getResultList();
    }

    public void persistHrEmpTime(HrEmpTime hrEmpTime) {
        em.persist(hrEmpTime);
    }

    public List<HrEmpInfo> getShiftTables(Long dept, Long job, Long grp, Long loc) {
        return em.createNamedQuery("HrEmpInfo.findAll").setParameter("dept", dept).setParameter("job", job).setParameter("grp", grp).setParameter("loc", loc).getResultList();
    }

    public Long getHrShiftRequest() {
        return (Long) em.createNamedQuery("HrShiftChangeRequest.findMax").getSingleResult();
    }

    public Long chkShiftRequestExist(Long emp, Date from_date, Long id) {
        return (Long) em.createNamedQuery("HrShiftChangeRequest.chkShiftExist").setParameter("emp", emp).setParameter("from_date", from_date).setParameter("id", id).getSingleResult();
    }

    public void persistHrShiftChangeRequest(HrShiftChangeRequest hrShiftChangeRequest) {
        em.persist(hrShiftChangeRequest);
    }

    public List<HrShiftChangeRequest> getHrShiftChangeRequestList(Long emp, Date from_date) {
        return em.createNamedQuery("HrShiftChangeRequest.findAll").setParameter("emp", emp).setParameter("from_date", from_date).getResultList();
    }

    public Long chkShiftExistAtSpecifiedDay(Long emp, Date from_date) {
        return (Long) em.createNamedQuery("HrEmpTime.chkcntOfShiftAtDay").setParameter("emp", emp).setParameter("from_date", from_date).getSingleResult();
    }

    public void merge_shift_request(HrShiftChangeRequest hrShiftChangeRequest) {
        em.merge(hrShiftChangeRequest);
    }

    public List<HrShiftRequestDt> getHrShiftRequestDtList(Long mng, Long dept, Long loc, Long grp, Long job) {
        return em.createNamedQuery("HrShiftRequestDt.findAll").setParameter("loc", loc).setParameter("mng", mng).setParameter("dept", dept).setParameter("job", job).setParameter("grp", grp).getResultList();
    }

    public List<HrEmpTime> getHrEmpTimeByTrnsDateAndEmpNo(Long emp, Date trnsDate) {
        System.out.println("emp_no=" + emp);
        System.out.println("trns_date=" + trnsDate);
        System.out.println(em.createNamedQuery("HrEmpTime.findByTrnsDateAndEmpNo").setParameter("emp", emp).setParameter("trnsDate", trnsDate).getResultList().get(0));
        return em.createNamedQuery("HrEmpTime.findByTrnsDateAndEmpNo").setParameter("emp", emp).setParameter("trnsDate", trnsDate).getResultList();
    }

//public BigDecimal getHrEmpTimeByTrnsDateAndEmpNo(BigDecimal emp,Date trnsDate)
//    {
//Query q=em.createNativeQuery("select id from hr_emp_time where trns_date=? and hd_id=?");
//q.setParameter(1,trnsDate);
//q.setParameter(2,emp);
//return (BigDecimal) q.getSingleResult() ;
//}
    public void mergeHrEmpTime(HrEmpTime hrEmpTime) {
        em.merge(hrEmpTime);
    }

    public HrShiftChangeRequest getHrShiftChangeRequestById(Long id) {
        return (HrShiftChangeRequest) em.createNamedQuery("HrShiftChangeRequest.findById").setParameter("id", id).getSingleResult();
    }

    public HrEmpTime getHrEmpTimeById(Long id) {
        return (HrEmpTime) em.createNamedQuery("HrEmpTime.findById").setParameter("id", id).getSingleResult();
    }

    public Long chkTaskReadDone(Long emp) {
        return (Long) em.createNamedQuery("HrSchedule.chkReadDone").setParameter("emp", emp).getSingleResult();
    }

    public List<HrSchedule> getAllTasksNotRead(Long emp) {
        return em.createNamedQuery("HrSchedule.findAllNotRead").setParameter("emp", emp).getResultList();
    }

    public HrEmpHolidays getTotalHolidays(Long emp) {
        try {
            return (HrEmpHolidays) em.createNamedQuery("HrEmpHolidays.findNormalHolidays").setParameter("emp", emp).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public BigDecimal get_tot_holidays(Long emp) {
        Query q = em.createNativeQuery("SELECT days FROM Hr_Emp_Holidays h "
                + " where h.emp_No=? and h.hol_Type=1 and h.trns_Date "
                + "  =(select max(o.trns_Date) from Hr_Emp_Holidays o "
                + "  where o.emp_No=h.emp_No and o.hol_Type=1 "
                + "   and o.days is not null )");
        q.setParameter(1, emp);
        return (BigDecimal) q.getSingleResult();
    }

    public Long getAnnualHolidays(Long emp, Long year) {
        return (Long) em.createNamedQuery("HrEmpEffectionVu.findAnnualHolidays").setParameter("emp", emp).setParameter("year", year).getSingleResult();
    }

    public Long getOpposeHolidays(Long emp, Long year) {
        return (Long) em.createNamedQuery("HrEmpEffectionVu.findOpposeHolidays").setParameter("emp", emp).setParameter("year", year).getSingleResult();
    }

    public HrHolidayType getTotalAnnualHolidays(Long id) {
        return (HrHolidayType) em.createNamedQuery("HrHolidayType.findById").setParameter("id", id).getSingleResult();
    }

    public Long getRemineHolidays(Long emp, Long year) {
        Query q = em.createNativeQuery("select hr_get_emp_holidays(?,?) from dual");
        q.setParameter(1, emp);
        q.setParameter(2, year);
        return Long.parseLong(q.getSingleResult().toString());
    }

    public Long getInsteadHolidays(Long emp) {
        Query q = em.createNativeQuery("select GET_EMP_BADL_RAHAT(?) from dual");
        q.setParameter(1, emp);
        return Long.parseLong(q.getSingleResult().toString());
    }

    public Long getTotalInsteadHolidays(Long emp, Long year) {
        return (Long) em.createNamedQuery("HrEmpEffectionVu.findInsteadHolidays").setParameter("year", year).setParameter("emp", emp).getSingleResult();
    }

    public Long getNormalHolidays(Long emp, Long year) {
        return (Long) em.createNamedQuery("HrEmpEffectionVu.findNormalHolidays").setParameter("year", year).setParameter("emp", emp).getSingleResult();
    }

    public Long chkEmpDailyTamyoz(Long emp, Date trns_date) {
        return (Long) em.createNamedQuery("HrTamyozDt.chkEmpDailyTamyoz").setParameter("emp", emp).setParameter("date", trns_date).getSingleResult();
    }

    public void persistHrTamyozHd(HrTamyozHd hrTamyozHd) {
        em.persist(hrTamyozHd);
    }

    public void persistHrTamyozDt(HrTamyozDt hrTamyozDt) {
        em.persist(hrTamyozDt);
    }

    public HrTamyozHd getPersistHrTamyozHd(Date date, Long loc, Long emp) {
        return (HrTamyozHd) em.createNamedQuery("HrTamyozHd.findPersist").setParameter("date", date).setParameter("loc", loc).setParameter("emp", emp).getSingleResult();
    }

    public List<CrmkOrdersNotPaied> getOrdersNotPaied(Date from_date, Date to_date, Long emp, Long loc) {
        return em.createNamedQuery("CrmkOrdersNotPaied.findAll").setParameter("from_date", from_date).setParameter("to_date", to_date).setParameter("emp", emp).setParameter("brn", loc).getResultList();
    }

    public List<CrmkOrdersNotDelivered> getOrdersNotDelivered(Date from_date, Date to_date, Long emp, Long loc) {
        return em.createNamedQuery("CrmkOrdersNotDelivered.findAll").setParameter("from_date", from_date).setParameter("to_date", to_date).setParameter("emp", emp).setParameter("brn", loc).getResultList();
    }

    public List<HrTamyozHd> getEmpDailyTamyozApprove1(Long loc, Date date) {
        return em.createNamedQuery("HrTamyozHd.getEmpDailyTamyozApprove1").setParameter("loc", loc).setParameter("d", date).getResultList();
    }

    public List<HrTamyozHd> getEmpDailyTamyozApprove2(Long loc, Date date) {
        return em.createNamedQuery("HrTamyozHd.getEmpDailyTamyozApprove2").setParameter("d", date).setParameter("loc", loc).getResultList();
    }

    public List<HrTamyozHd> getEmpDailyTamyozApprove3(Long loc, Date date) {
        return em.createNamedQuery("HrTamyozHd.getEmpDailyTamyozApprove3").setParameter("d", date).setParameter("loc", loc).getResultList();
    }

    public HrTamyozSecurityTransfer getSecurityTransfer1(long emp) {
        try {
            return (HrTamyozSecurityTransfer) em.createNamedQuery("HrTamyozSecurityTransfer.chkSecurityTransfer1").setParameter("emp", emp).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public HrTamyozSecurityTransfer getSecurityTransfer2(long emp) {
        try {
            return (HrTamyozSecurityTransfer) em.createNamedQuery("HrTamyozSecurityTransfer.chkSecurityTransfer2").setParameter("emp", emp).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public HrTamyozSecurityTransfer getSecurityTransfer3(long emp) {
        try {
            return (HrTamyozSecurityTransfer) em.createNamedQuery("HrTamyozSecurityTransfer.chkSecurityTransfer3").setParameter("emp", emp).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public HrLocation findLocationById(Long id) {
        return (HrLocation) em.createNamedQuery("HrLocation.findById").setParameter("id", id).getSingleResult();
    }

    public void mergeHrTamyozHd(HrTamyozHd hrTamyozHd) {
        em.merge(hrTamyozHd);
    }

    public HrTamyozDt mergeHrTamyozDt(HrTamyozDt hrTamyozDt) {
        return em.merge(hrTamyozDt);
    }

    public HrTamyozHd getHrTamyozHdById(Long id) {
        return (HrTamyozHd) em.createNamedQuery("HrTamyozHd.findById").setParameter("id", id).getSingleResult();
    }

    public List<HrTamyozHd> getEmpDailyTamyozEntry(Date date, Long loc) {
        return em.createNamedQuery("HrTamyozHd.getEmpDailyTamyozEntry").setParameter("d", date).setParameter("loc", loc).getResultList();
    }

    public List<Long> findTamyozEmpsByHdId(Long id) {
        return em.createNamedQuery("HrTamyozDt.findEmpsByHdId").setParameter("id", id).getResultList();
    }

    public HrTamyozDt findHrTamyozDtById(Long id) {
        return (HrTamyozDt) em.createNamedQuery("HrTamyozDt.findById").setParameter("id", id).getSingleResult();
    }

    public void removeHrTamyozDt(HrTamyozDt hrTamyozDt) {
        em.remove(em.merge(hrTamyozDt));
    }

    public List<CrmkOrdrSader> getSaderNotApproved(Long loc_id,Date trnsDate) {
        return em.createNamedQuery("CrmkOrdrSader.findNotApproved").setParameter("loc_id", loc_id).setParameter("trnsDate", trnsDate).getResultList();
    }

    public List<CrmkBranch> getStore() {
        return em.createNamedQuery("CrmkBranch.findStore").getResultList();
    }

    public List<CrmkOrdrSaderSetting> getCrmkOrdrSaderSettings(Long brn_id) {
        return em.createNamedQuery("CrmkOrdrSaderSetting.findAll").setParameter("brn_id", brn_id).getResultList();
    }

    public Long chkCrmkOrdrSaderCnt(Long brn_id, Date from_date, Date to_date) {
        return (Long) em.createNamedQuery("CrmkOrdrSader.chkCnt").setParameter("brn_id", brn_id).setParameter("from_date", from_date).setParameter("to_date", to_date).getSingleResult();
    }

    public Long chkCrmkOrdrSaderSum(Long brn_id, Date from_date, Date to_date) {
        try {
            return (Long) em.createNamedQuery("CrmkOrdrSader.chkSum").setParameter("brn_id", brn_id).setParameter("from_date", from_date).setParameter("to_date", to_date).getSingleResult();
        } catch (NoResultException e) {
            return 0L;
        }
    }

    public void mergeCrmkOrdrSader(CrmkOrdrSader crmkOrdrSader) {
        em.merge(crmkOrdrSader);
    }

    public CrmkBranch getCrmkBranchById(Long id) {
        try {
            return (CrmkBranch) em.createNamedQuery("CrmkBranch.findById").setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<CrmkOrdrSader> getSaderApproved(Long mng_id) {
        return em.createNamedQuery("CrmkOrdrSader.findApproved").setParameter("mng_id", mng_id).getResultList();
    }

    public CrmkOrdrHd findCrmkOrdrHdById(Long id) {
        return (CrmkOrdrHd) em.createNamedQuery("CrmkOrdrHd.findById").setParameter("id", id).getSingleResult();
    }

    public List<CrmkOrdrSader> findCrmkOrdrSaderNotPrinted(Long loc) {
        return em.createNamedQuery("CrmkOrdrSader.findNotPrinted").setParameter("loc_id", loc).getResultList();
    }

    public List<CrmkOrdrSader> findCrmkOrdrSaderNotPrinted(Long loc, Long brnId, Long ordrNo, Character crmkSehy) {
        return em.createNamedQuery("CrmkOrdrSader.findNotPrintedWithSearch").setParameter("loc_id", loc).setParameter("ordrNo", ordrNo).setParameter("brnId", brnId).setParameter("crmkSehy", crmkSehy).getResultList();
    }

    public List<CrmkOrdrSader> findCrmkOrdrSaderPrinted(String emp, Date frm_date) {
        return em.createNamedQuery("CrmkOrdrSader.findPrinted").setParameter("emp_id", emp).setParameter("frm_date", frm_date).getResultList();
    }

    public String getMeterByTxt(Double m) {
        Query q = em.createNativeQuery("select FU_TAFKEET2(?) from dual");
        q.setParameter(1, m);
        return (String) q.getSingleResult();
    }

    public String getPieceByTxt(Double m) {
        Query q = em.createNativeQuery("select FU_TAFKEET1(?) from dual");
        q.setParameter(1, m);
        return (String) q.getSingleResult();
    }

    public Double getCrmkQtySum(Long ordr_id) {
        return (Double) em.createNamedQuery("CrmkCOrdrDt.findSum").setParameter("ordr_id", ordr_id).getSingleResult();
    }

    public Double getDcreQtySum(Long ordr_id) {
        return (Double) em.createNamedQuery("CrmkDOrdrDt.findSum").setParameter("ordr_id", ordr_id).getSingleResult();
    }

    public Double getSehyQtySum(Long ordr_id) {
        return (Double) em.createNamedQuery("CrmkSOrdrDt.findSum").setParameter("ordr_id", ordr_id).getSingleResult();
    }

    public List<HrLetterType> getHrLetterTypes() {
        return em.createNamedQuery("HrLetterType.findAll").getResultList();
    }

    public void persistHrLetterRequest(HrLetterRequest hrLetterRequest) {
        em.persist(hrLetterRequest);
    }

    public List<HrLetterRequest> hrLetterRequestList(Long emp) {
        return em.createNamedQuery("HrLetterRequest.findAll").setParameter("emp", emp).getResultList();
    }

    public Long getHrLetterRequestId() {
        return (Long) em.createNamedQuery("HrLetterRequest.findMaxId").getSingleResult();
    }

    public HrLetterType getHrLetterTypeById(Long id) {
        return (HrLetterType) em.createNamedQuery("HrLetterType.findById").setParameter("id", id).getSingleResult();
    }

    public void mergeHrLetterRequest(HrLetterRequest hrLetterRequest) {
        em.merge(hrLetterRequest);
    }

    public List<HrLetterRequest> getHrLetterRequestNotRead(Long emp) {
        return em.createNamedQuery("HrLetterRequest.findNotRead").setParameter("emp", emp).getResultList();
    }

    public List<HrManualInOutTrns> getManualInTrns(Date frm_date, Date to_date, Long user) {
        return em.createNamedQuery("HrManualInOutTrns.findIn").setParameter("user", user).setParameter("frm_date", frm_date).setParameter("to_date", to_date).getResultList();
    }

    public List<HrManualInOutTrns> getManualOutTrns(Date frm_date, Date to_date, Long user) {
        return em.createNamedQuery("HrManualInOutTrns.findOut").setParameter("user", user).setParameter("frm_date", frm_date).setParameter("to_date", to_date).getResultList();
    }

    public List<HrManualTrnsLevelDt> getManualTrnsLevelDts(Long loc, Long user) {
        return em.createNamedQuery("HrManualTrnsLevelDt.findAll").setParameter("loc", loc).setParameter("user", user).getResultList();
    }

    public Long getManual_in_out_id() {
        return (Long) em.createNamedQuery("HrManualInOutTrns.findId").getSingleResult();
    }

    public void persistHrManualInOutTrns(HrManualInOutTrns hrManualInOutTrns) {
        em.persist(hrManualInOutTrns);
    }

    public HrEmpSalary getLastHrEmpSalary(Long emp) {
        return (HrEmpSalary) em.createNamedQuery("HrEmpSalary.findLast").setParameter("emp", emp).getSingleResult();
    }

    public HrEmpSalary getPeriviousHrEmpSalary(Long emp, Date date) {
        return (HrEmpSalary) em.createNamedQuery("HrEmpSalary.findPrevious").setParameter("date", date).setParameter("emp", emp).getSingleResult();
    }

    public HrManualInOutTrns chkLastInOut(Long emp) {
        try {
            return (HrManualInOutTrns) em.createNamedQuery("HrManualInOutTrns.chkLastInOut").setParameter("emp", emp).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Uni getAddHafez(Long empId) {
        try {
            return (Uni) em.createNamedQuery("Uni.findByEmpId").setParameter("empId", empId).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<CrmkOrdersNotDelivered> getAllOdrdsNotDelivered(Long brnId, Date from_date, Date to_date) {
        return em.createNamedQuery("CrmkOrdersNotDelivered.findAllByBrnId").setParameter("brnId", brnId).setParameter("from_date", from_date).setParameter("to_date", to_date).getResultList();
    }

    public List<CrmkOrdersNotPaied> getAllOrdrsNotPaied(Long brnId, Date from_date, Date to_date) {
        return em.createNamedQuery("CrmkOrdersNotPaied.findAllByBrnId").setParameter("brnId", brnId).setParameter("from_date", from_date).setParameter("to_date", to_date).getResultList();
    }

    public List<CrmkBranch> getShow() {
        return em.createNamedQuery("CrmkBranch.findShow").getResultList();
    }

    public Long getHrWHolidayAttendanceReqMaxId() {
        return (Long) em.createNamedQuery("HrWHolidayAttendanceReq.findMaxId").getSingleResult();
    }

    public void hrWHolidayAttendanceReqPersist(HrWHolidayAttendanceReq hrWHolidayAttendanceReq) {
        em.persist(hrWHolidayAttendanceReq);
    }

    public List<HrWHolidayAttendanceReq> getHrWHolidayAttendanceReqHist(Long emp_no, Date from_date) {
        return em.createNamedQuery("HrWHolidayAttendanceReq.findHist").setParameter("emp_no", emp_no).setParameter("from_date", from_date).getResultList();
    }

    public void mergeHrWHolidayAttendanceReq(HrWHolidayAttendanceReq hrWHolidayAttendanceReq) {
        em.merge(hrWHolidayAttendanceReq);
    }

    public HrTotalSalComponents getFixedHafez(Long empId) {
        try {
            return (HrTotalSalComponents) em.createNamedQuery("HrTotalSalComponents.findFixedHafezByEmpId").setParameter("empId", empId).getSingleResult();
        } catch (NoResultException e) {
            HrTotalSalComponents hrTotalSalComponents = new HrTotalSalComponents();
            hrTotalSalComponents.setOldSal(0D);
            return hrTotalSalComponents;
        }
    }

    public HrWHolidayAttendanceReq getHrWHolidayAttendanceById(Long id) {
        return (HrWHolidayAttendanceReq) em.createNamedQuery("HrWHolidayAttendanceReq.findById").setParameter("id", id).getSingleResult();
    }

    public void megeHrWholidayAttandanceRwauest(HrWHolidayAttendanceReq hrWHolidayAttendanceReq) {
        em.merge(hrWHolidayAttendanceReq);
    }

    public List<HrWHolidayAttendanceReqDt> getHolidayAttendanceReqDtList(Long mng, Long dept, Long loc, Long grp, Long job) {
        return em.createNamedQuery("HrWHolidayAttendanceReqDt.findAll").setParameter("loc", loc).setParameter("mng", mng).setParameter("dept", dept).setParameter("job", job).setParameter("grp", grp).getResultList();
    }

    public void update_emp_password(Long emp_no, String password, String question, String answer) {
        Query q = em.createNativeQuery("call update_emp_password(?,?,?,?)");
        q.setParameter(1, emp_no);
        q.setParameter(2, password);
        q.setParameter(3, question);
        q.setParameter(4, answer);
        q.executeUpdate();
    }

    public List<CrmkShowRecivRmndrQHd> getCrmkShowRecivRmndrQHdsReciev(String ip) {
        return em.createNamedQuery("CrmkShowRecivRmndrQHd.findAllReciev").setParameter("ip", ip).getResultList();
    }

    public List<CrmkShowRecivRmndrQHd> getCrmkShowRecivRmndrQHdsRmndr(String ip) {
        return em.createNamedQuery("CrmkShowRecivRmndrQHd.findAllRmndr").setParameter("ip", ip).getResultList();
    }

    public void updateCrmkShowRecivRmndrQty(Long hd_id, Long dt_id, Long rciv_emp, Long rmndr_emp, Double rciv_qty, Double rmndr_qty) {
        if (rciv_emp != null) {
            Query q1 = em.createNativeQuery("update CRMK.CRMK_SHOW_RECIV_RMNDR_QTY_HD set RECIV_EMP=" + rciv_emp + ",RECIV_DATE=sysdate where id=" + hd_id);
            Query q2 = em.createNativeQuery("update CRMK.CRMK_SHOW_RECIV_RMNDR_QTY_DT set RECIV_QTY=" + rciv_qty + " where id=" + dt_id);
            q1.executeUpdate();
            q2.executeUpdate();
        } else if (rmndr_emp != null) {
            Query q3 = em.createNativeQuery("update CRMK.CRMK_SHOW_RECIV_RMNDR_QTY_HD set rmndr_emp=" + rmndr_emp + ",RMNDR_DATE=sysdate where id=" + hd_id);
            Query q4 = em.createNativeQuery("update CRMK.CRMK_SHOW_RECIV_RMNDR_QTY_DT set RMNDR_QTY=" + rmndr_qty + " where id=" + dt_id);
            q3.executeUpdate();
            q4.executeUpdate();
        }


    }

    public CrmkShowRecivRmndrQDt getCrmkShowRecivRmndrQDtById(Long id) {
        return (CrmkShowRecivRmndrQDt) em.createNamedQuery("CrmkShowRecivRmndrQDt.findByDtId").setParameter("dtId", id).getSingleResult();
    }

    public List<String> getShiftEmpByEmpNameSubstr(Long dept, Long loc, Long grp, Long job, String emp_name) {
        System.out.println("'%" + emp_name + "%'");
        return em.createNamedQuery("HrEmpMangers.findEmpByEmpSubstr").setParameter("dept", dept).setParameter("loc", loc).setParameter("grp", grp).setParameter("job", job).setParameter("emp_name", "%" + emp_name.trim() + "%").getResultList();
    }

    public Long chkShiftEntry(Date from_date, Date to_date, Long emp) {
        return (Long) em.createNamedQuery("HrEmpTime.chkShiftEntry").setParameter("emp", emp).setParameter("from_date", from_date).setParameter("to_date", to_date).getSingleResult();
    }

    public void applyFingerPrintPerEmp(Date from_date, Date to_date, Long emp) {
        Query q = em.createNativeQuery("call HR_APPLY_FINGER_PER_EMP(?,?,?)");
        q.setParameter(1, from_date);
        q.setParameter(2, to_date);
        q.setParameter(3, emp);
        q.executeUpdate();

    }

    public void removePreviousShiftTable(String from_date, String to_date, long emp_no) {
        Query q = em.createNativeQuery("delete hr.hr_emp_time where trns_date between to_date('" + from_date + "','dd-mm-rrrr') and to_date('" + to_date + "','dd-mm-rrrr') and hd_id=" + emp_no);
        q.executeUpdate();
    }

    public Long chkPersnalOrdrExist(Long brn_id, Long ordr_no, Long prd_id, Character crmk_sehy) {
        return (Long) em.createNamedQuery("HrPersonalOrdrRequest.chkOrdrExist").setParameter("crmk_sehy", crmk_sehy).setParameter("brn_id", brn_id).setParameter("prd_id", prd_id).setParameter("ordr_no", ordr_no).getSingleResult();
    }

    public CrmkOrdrHd getPersonalOrdr(Long brn_id, Long ordr_no, Long prd_id, Character crmk_sehy) {
        return (CrmkOrdrHd) em.createNamedQuery("HrPersonalOrdrRequest.getPersonalOrdr").setParameter("crmk_sehy", crmk_sehy).setParameter("brn_id", brn_id).setParameter("prd_id", prd_id).setParameter("ordr_no", ordr_no).getSingleResult();
    }

    public void persistPersonalOrdr(HrPersonalOrdrRequest personalOrdrRequest) {
        em.persist(personalOrdrRequest);
    }

    public Long chkOrdrExist(Long ordr_id) {
        return (Long) em.createNamedQuery("HrPersonalOrdrRequest.chkOrdrIdExist").setParameter("ordr_id", ordr_id).getSingleResult();
    }

    public List<HrPersonalOrdrRequest> getPersonalOrdrRequests(Long emp_no) {
        return em.createNamedQuery("HrPersonalOrdrRequest.findByEmpNo").setParameter("empNo", emp_no).getResultList();
    }

    public BigDecimal getTotalCrmkOrdrValue(Long ordr_id) {
        return (BigDecimal) em.createNativeQuery("select round(sum(qty*uprice)) from crmk.crmk_c_ordr_dt where hd_id=" + ordr_id).getSingleResult();
    }

    public BigDecimal getTotalDcreOrdrValue(Long ordr_id) {
        return (BigDecimal) em.createNativeQuery("select round(sum(qty*uprice)) from crmk.crmk_d_ordr_dt where hd_id=" + ordr_id).getSingleResult();
    }

    public BigDecimal getTotalSehyOrdrValue(Long ordr_id) {
        return (BigDecimal) em.createNativeQuery("select round(sum(qty*uprice)) from crmk.crmk_s_ordr_dt where hd_id=" + ordr_id).getSingleResult();
    }

    public List<HrPersonalOrdrRequest> getPersonalOrdrToConfirm(Date from_date, Date to_date) {
        return em.createNamedQuery("HrPersonalOrdrRequest.getPersonalOrdrToConfirm").setParameter("from_date", from_date).setParameter("to_date", to_date).getResultList();
    }

    public void merge_personal_ordr_req(HrPersonalOrdrRequest hrPersonalOrdrRequest) {
        em.merge(hrPersonalOrdrRequest);
    }

    public TaxDesc getT2meenDifferance(Long emp) {
        try {
            return (TaxDesc) em.createNamedQuery("TaxDesc.findByEmpNo").setParameter("empNo", emp).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public HrEmpHd findEmpById(Long empNo) {
        return em.find(HrEmpHd.class, empNo);
    }

    public void update_emp_mobile(Long emp_no, String mob, String old_mob) {
        Query q = em.createNativeQuery("call update_emp_phone(?,?,?)");
        q.setParameter(1, emp_no);
        q.setParameter(2, mob);
        q.setParameter(3, old_mob);
        q.executeUpdate();
    }

    public void update_emp_ident(Long emp_no, String id, String old_id) {
        Query q = em.createNativeQuery("call update_emp_ident(?,?,?)");
        q.setParameter(1, emp_no);
        q.setParameter(2, id);
        q.setParameter(3, old_id);
        q.executeUpdate();
    }

    public void emp_visa(Long emp_no, String visa_no) {
        Query q = em.createNativeQuery("call update_emp_visa(?,?)");
        q.setParameter(1, emp_no);
        q.setParameter(2, visa_no);
        q.executeUpdate();
    }

    public HrProfileAlertHd getProfileAlertHd(Long emp_no) {
        try {
            return (HrProfileAlertHd) em.createNamedQuery("HrProfileAlertHd.findCurrentAlert").setParameter("emp_no", emp_no).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    public void persistAlertHd(HrProfileAlertHd hrProfileAlertHd) {
        em.persist(hrProfileAlertHd);
    }

    public void persistAlertDt(HrProfileAlertDt hrProfileAlertDt) {
        em.persist(hrProfileAlertDt);
    }

    public Long getAlertHdMaxId() {
        try {
            Long l = (Long) em.createNamedQuery("HrProfileAlertHd.findAMaxId").getSingleResult();
            if (l != null) {
                return l;
            } else {
                return 0L;
            }
        } catch (NoResultException e) {
            return 0L;
        }
    }

    public Long getAlertDtMaxId() {
        try {
            Long l = (Long) em.createNamedQuery("HrProfileAlertDt.findAMaxId").getSingleResult();
            if (l != null) {
                return l;
            } else {
                return 0L;
            }
        } catch (NoResultException e) {
            return 0L;
        }
    }

    public List<HrProfileAlertHd> hrProfileAlertHdList() {
        return em.createNamedQuery("HrProfileAlertHd.findAll").getResultList();
    }

    public void mergeAlertHd(HrProfileAlertHd hrProfileAlertHd) {
        em.merge(hrProfileAlertHd);
    }

    public void persistOpenDutyHd(HrOpenDutyHd hrOpenDutyHd) {
        em.persist(hrOpenDutyHd);
    }

    public List<HrDutyEmpMngDt> getDutyEmpMngDt(Long dept, Long loc, Long grp, Long job, String emp_name) {
        return em.createNamedQuery("HrDutyEmpMngDt.findAll").setParameter("dept", dept).setParameter("loc", loc).setParameter("grp", grp).setParameter("job", job).setParameter("emp_name", emp_name).getResultList();
    }

    public List<String> getEmpDutyByEmpNameSubstr(Long dept, Long loc, Long grp, Long job, String emp_name) {
        System.out.println("'%" + emp_name + "%'");
        return em.createNamedQuery("HrDutyEmpMngDt.findByEmpSubstr").setParameter("dept", dept).setParameter("loc", loc).setParameter("grp", grp).setParameter("job", job).setParameter("emp_name", "%" + emp_name.trim() + "%").getResultList();
    }

    public List<HrLocation> getDutyLocations(Long loc_id) {
        return em.createNamedQuery("HrLocation.dutyLocations").setParameter("loc_id", loc_id).getResultList();
    }

    public void persistHrOpenDutyHd(HrOpenDutyHd hrOpenDutyHd) {
        em.persist(hrOpenDutyHd);
    }

    public void persisHrOpenDutyExpectedDt(HrOpenDutyExpectedLocDt hrOpenDutyExpectedLocDt) {
        em.persist(hrOpenDutyExpectedLocDt);
    }

    public HrLocation getLocationByName(String loc) {
        return (HrLocation) em.createNamedQuery("HrLocation.findByName").setParameter("name", loc).getSingleResult();
    }

    public List<HrOpenDutyHd> find_duty_for_specific_emp(HrEmpInfo emp_id, Date trns_date) {
        return em.createNamedQuery("HrOpenDutyHd.findDutyForSpecificEmp").setParameter("emp_id", emp_id).setParameter("trns_date", trns_date).getResultList();
    }

    public List<HrOpenDutyExpectedLocDt> getDutyExpectedLocations(HrOpenDutyHd hrOpenDutyHd) {
        return em.createNamedQuery("HrOpenDutyHd.findExpectedLocations").setParameter("open_hd", hrOpenDutyHd).getResultList();
    }

    public void mergeHrOpenDutyHd(HrOpenDutyHd hrOpenDutyHd) {
        em.merge(hrOpenDutyHd);
    }

    public void removeHrOpenDutyExpectedLocationDt(HrOpenDutyExpectedLocDt hrOpenDutyExpectedLocDt) {
        HrOpenDutyExpectedLocDt x = em.merge(hrOpenDutyExpectedLocDt);
        em.remove(x);
    }

    public List<HrOpenDutyExpectedLocDt> findExpectedDutyLocations(Long loc_id, Date trns_date, String ip) {
        return em.createNamedQuery("HrOpenDutyExpectedLocDt.findExpectedEmployees").setParameter("loc_id", loc_id).setParameter("trns_date", trns_date).setParameter("ip", ip).getResultList();
    }

    public HrOpenDutyDt getLastEmpTransaction(Long emp_no, Date from_date, Date to_date, Long loc_id) {
        try {
            return (HrOpenDutyDt) em.createNamedQuery("HrOpenDutyDt.findLastEmpTransaction").setParameter("emp_no", emp_no).setParameter("from_date", from_date).setParameter("to_date", to_date).setParameter("loc_id", loc_id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<HrLocation> getLoactionByIp(Long ip) {
        return em.createNamedQuery("HrLocation.findByIpAddress").setParameter("ipAddress", ip).getResultList();
    }

    public void persist_Hr_Open_Duty_Dt(HrOpenDutyDt hrOpenDutyDt) {
        em.persist(hrOpenDutyDt);
    }

    public List<HrOpenDutyDt> getTodayTransactions(Date from_date, Date To_date) {
        return em.createNamedQuery("HrOpenDutyDt.findToday'sTransactions").setParameter("from_date", from_date).setParameter("to_date", To_date).getResultList();
    }

    public Long chkExistanceOfDutyCurrentely(HrEmpInfo emp_id, Date trns_date) {
        return (Long) em.createNamedQuery("HrOpenDutyHd.chkDutyForSpecificEmp").setParameter("emp_id", emp_id).setParameter("trns_date", trns_date).getSingleResult();
    }

    public Long chkExistsanceOfNotClosedF2BOrME2B(Long emp_no) {
        return (Long) em.createNamedQuery("CrmkF2bMe2bNotClosed.chk").setParameter("emp_no", emp_no).getSingleResult();
    }

    public List<CrmkF2bMe2bNotClosed> findNotClosedF2BOrME2BForEmp(Long emp_no) {
        return em.createNamedQuery("CrmkF2bMe2bNotClosed.findByEmpNo").setParameter("emp_no", emp_no).getResultList();
    }

    public List<HrLocation> getLocations() {
        return em.createNamedQuery("HrLocation.findAll").getResultList();
    }

    public List<HrLocation> getDutyLocations() {
        return em.createNamedQuery("HrLocation.findLocationsForDuties").getResultList();
    }

    public void persist_checkup_duty(HrCheckupDutyHd hrCheckupDutyHd) {
        em.persist(hrCheckupDutyHd);
    }

    public HrLocation getLocationById(Long loc_id) {
        return (HrLocation) em.createNamedQuery("HrLocation.findById").setParameter("id", loc_id).getSingleResult();
    }

    public List<HrEmpInfo> getManagersMail(Long loc) {
        return em.createNamedQuery("HrEmpInfo.findManagersMail").setParameter("loc", loc).getResultList();
    }

    public List<HrCheckupDutyHd> getCheckUpDutiesNotApproved() {
        List<HrCheckupDutyHd> l = em.createNamedQuery("HrCheckupDutyHd.findNotApproved").getResultList();
        for (HrCheckupDutyHd hrCheckupDutyHd : l) {
            System.out.println(hrCheckupDutyHd.getHrCheckupDutyDtList().size());
        }

        return l;
    }

    public void mergeHrCheckUpDutyHd(HrCheckupDutyHd hrCheckupDutyHd) {
        em.merge(hrCheckupDutyHd);
    }

    public List<String> getDeptManagersMails(String mail) {
        return em.createNamedQuery("HrEmpInfo.findDeptManagersMail").setParameter("mail", "%" + mail.trim() + "%").getResultList();
    }

    public HrCheckupDutyBonus getCheckUpDutyBonus(Long loc) {
        try {
            return (HrCheckupDutyBonus) em.createNamedQuery("HrCheckupDutyBonus.findByLocId").setParameter("locId", loc).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void mergeHrCheckupDutyDt(HrCheckupDutyDt hrCheckupDutyDt) {
        em.merge(hrCheckupDutyDt);
    }

    public Long check_checkup_duty_existance(Long empNo, Date trnsDate, Long locId, Long id) {
        return (Long) em.createNamedQuery("HrCheckupDutyHd.checkExistance").setParameter("id", id).setParameter("locId", locId).setParameter("empNo", empNo).setParameter("trnsDate", trnsDate).getSingleResult();
    }

    public List<HrProfilePrivilage> allProfilePrivilage(Long empNo) {
        return em.createNamedQuery("HrProfilePrivilage.findByEmpNo").setParameter("empNo", empNo).getResultList();
    }

    public List<HrProfilePrivilage> allProfilePrivilage() {
        return em.createNamedQuery("HrProfilePrivilage.findAll").getResultList();
    }

    public List<HrEmpInfo> allHrEmpInfo() {
        return em.createNamedQuery("HrEmpInfo.findAllEmpInfo").getResultList();
    }

    @Override
    public Long chk_shift_change_request(Long emp_no, Date from_date, Long req_id) {
        Query q = em.createNativeQuery("select HR_SHIFT_REQUEST_CHK(?,?,?) from dual");
        q.setParameter(1, emp_no);
        q.setParameter(2, from_date);
        q.setParameter(3, req_id);
        return Long.parseLong(q.getSingleResult().toString());
    }

    public void persistHrProfileMSG(HrProfileMsg hrProfileMsg) {
        em.persist(hrProfileMsg);
    }

    @Override
    public List<HrEmpMangers> findManagersPerEmp(Long empNo) {
        return em.createNamedQuery("HrEmpMangers.findByEmpNo").setParameter("empNo", empNo).getResultList();
    }

    @Override
    public List<HrEmpMangers> findAllEmpManagers(Long empNo) {
        return em.createNamedQuery("HrEmpMangers.findAll").setParameter("empNo", empNo).getResultList();
    }

    @Override
    public List<HrEmpMangers> findAllMngEmployees(Long mngNo) {
        return em.createNamedQuery("HrEmpMangers.findAllEmployees").setParameter("mngNo", mngNo).getResultList();
    }

    @Override
    public List<HrProfileMsg> findAllProfilMsgs(Long empNo) {
        return em.createNamedQuery("HrProfileMsg.findAll").setParameter("empNo", empNo).getResultList();
    }

    @Override
    public void mergeHrProfileMsgList(List<HrProfileMsg> hrProfileMsgList) {
        for (HrProfileMsg hrProfileMsg : hrProfileMsgList) {
            em.merge(hrProfileMsg);
        }
    }

    public List<HrTamyozSecurityTransfer> findTamyozSecutityTransferPerEmp(Long emp) {
        try {
            return em.createNamedQuery("HrTamyozSecurityTransfer.findByEmpNo").setParameter("empNo", emp).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<HrTamyozApproveEmp> findAllTamyozApproveEmps() {
        return em.createNamedQuery("HrTamyozApproveEmp.findAll").getResultList();
    }

    public List<HrLocation> findAllShowRooms() {
        return em.createNamedQuery("HrLocation.findAllShowRooms").getResultList();
    }

    public Long findCrmkShowRoomIdByLocId(Long loc_id) {
        return (Long) em.createNamedQuery("HrLocation.findCrmkShowRoomIdByLocId").setParameter("loc_id", loc_id).getSingleResult();
    }

    public List<HrEmpInfo> findLocationManagers(Long loc_id) {
        return em.createNamedQuery("HrEmpInfo.findLocationManagers").setParameter("locId", loc_id).getResultList();
    }

    public List<HrEmpCrmkBranch> findStoreManagers(Long storeId) {
        return em.createNamedQuery("HrEmpCrmkBranch.findByCrmkId").setParameter("crmkId", storeId).getResultList();
    }

    public List<HrGzaEmpMngDt> findAllGzaEmpMng() {
        return em.createNamedQuery("HrGzaEmpMngDt.findAllGiza").getResultList();
    }

    public void addShiftTable(Long emp_no, Date from_date, Date to_date, Long shift_id, Long entry_no) {
        Query q = em.createNativeQuery("call add_shift_table(?,?,?,?,?)");
        q.setParameter(1, emp_no);
        q.setParameter(2, from_date);
        q.setParameter(3, to_date);
        q.setParameter(4, shift_id);
        q.setParameter(5, entry_no);
        q.executeUpdate();
    }

    public Long chkShiftTableCopy(Long emp_no, Date from_date1, Date to_date1, Date from_date2) {
        Query q = em.createNativeQuery("select hr_copy_shift_table_chk(?,?,?,?) from dual");
        q.setParameter(1, emp_no);
        q.setParameter(2, from_date1);
        q.setParameter(3, to_date1);
        q.setParameter(4, from_date2);
        return Long.parseLong(q.getSingleResult().toString());

    }

    public void copyShiftTable(Long emp_no, Date from_date1, Date from_date2, Date to_date2) {
        Query q = em.createNativeQuery("call hr_copy_shift_table(?,?,?,?)");
        q.setParameter(1, emp_no);
        q.setParameter(2, from_date1);
        q.setParameter(3, from_date2);
        q.setParameter(4, to_date2);
        q.executeUpdate();
    }

    public List<HrCheckupDutyHd> findCheckUpDutyHdForEmp(HrEmpInfo entryId, Date trnsDate) {
        return em.createNamedQuery("HrCheckupDutyHd.findByEntryId").setParameter("entryId", entryId).setParameter("trnsDate", trnsDate).getResultList();
    }

    public void removeHrCheckDutyDt(HrCheckupDutyDt hrCheckupDutyDt) {
        em.remove(em.merge(hrCheckupDutyDt));
    }

    public HrAuthAndHolBalance findEmpAuthAndHolBalance(Long emp) {
        return (HrAuthAndHolBalance) em.createNamedQuery("HrAuthAndHolBalance.findByEmpNo").setParameter("empNo", emp).getSingleResult();
    }

    public HrSalDetail findSalDetail(Long empNo) {
        return (HrSalDetail) em.createNamedQuery("HrSalDetail.findByEmpNo").setParameter("empNo", empNo).getSingleResult();
    }

    public List<HrPoundHafezDt> findPoundHafezDts(Long empId) {
        return em.createNamedQuery("HrPoundHafezDt.findAll").setParameter("emp_id", empId).getResultList();
    }

    public String findManagerialHierarchy(String type) {
        Query q = em.createNativeQuery("select hr_draw_managerial_hierarchy(?) from dual");
        q.setParameter(1, type);
        return q.getSingleResult().toString();
    }

    public List<HrShowroomManNotes> findHrShowroomManNoteses(Long manId) {

        return em.createNamedQuery("HrShowroomManNotes.findByManId").setParameter("manId", manId).getResultList();
    }

    public void persistHrShowroomManNotes(HrShowroomManNotes hrShowroomManNotes) {
        em.persist(hrShowroomManNotes);
    }

    public void mergeHrShowroomManNotes(HrShowroomManNotes hrShowroomManNotes) {
        em.merge(hrShowroomManNotes);
    }

    public List<HrShowroomManNotes> findHrShowroomManNotesNotApproved() {
        return em.createNamedQuery("HrShowroomManNotes.findByApproved").getResultList();
    }

//    public List<CrmkCurrentBrnTrgt> findShowroomTrgt(Long brn_id) {
//        return em.createNamedQuery("CrmkCurrentBrnTrgt.findAll").setParameter("brn_id", brn_id).getResultList();
//    }
//    public Long findShowroomOrder(Double percent) {
//        return (Long) em.createNamedQuery("CrmkCurrentBrnTrgt.findBrnOrder").setParameter("percent", percent).getSingleResult();
//    }
    public List<EmpQtyTrgetVu> findSalesmenTrgtPerLocation(Long brn_id) {
        return em.createNamedQuery("EmpQtyTrgetVu.findAll").setParameter("brn_id", brn_id).getResultList();
    }

    public Long findShowroomOrder(Long percent) {
        return (Long) em.createNamedQuery("BrnQtyTrgetVu.findOrdr").setParameter("percent", percent).getSingleResult();
    }

    public List<BrnQtyTrgetVu> findShowroomTrgt(Long brn_id) {
        return em.createNamedQuery("BrnQtyTrgetVu.findByLocId").setParameter("brn_id", brn_id).getResultList();
    }

    public List<BrnQtyTrgetVu> findAllShowroomTrgts() {
        return em.createNamedQuery("BrnQtyTrgetVu.findAll").getResultList();
    }

    @Override
    public Long chkCheckupDuty(Long v_emp_no, Date v_trns_date, Long id, Long v_loc_id, Long v_dept_id) {
        Query q = em.createNativeQuery("select hr_checkup_duty_chk(?,?,?,?,?) from dual");
        q.setParameter(1, id);
        q.setParameter(2, v_emp_no);
        q.setParameter(3, v_trns_date);
        q.setParameter(4, v_loc_id);
        q.setParameter(5, v_dept_id);
        return Long.parseLong(q.getSingleResult().toString());
    }

    @Override
    public Long chkCheckupDuty2(Long v_emp_no, Date v_trns_date, Long v_loc_id, Long v_current_duty) {
        Query q = em.createNativeQuery("select hr_checkup_duty_chk2(?,?,?,?) from dual");
        q.setParameter(1, v_emp_no);
        q.setParameter(2, v_trns_date);
        q.setParameter(3, v_loc_id);
        q.setParameter(4, v_current_duty);
        return Long.parseLong(q.getSingleResult().toString());
    }

    public List<HrInOutManualTrnsVu> findAllInOutManualTrns() {
        return em.createNamedQuery("HrInOutManualTrnsVu.findAll").getResultList();
    }

    public List<HrManualInOutTrns> findAllInOutTrns(Date d1, Date d2, Long ip) {
        return em.createNamedQuery("HrManualInOutTrns.findAll").setParameter("frm_date", d1).setParameter("to_date", d2).setParameter("ip", ip).getResultList();
    }

    public List<BrnQtyTrgetVu> findAllShowroomTrgt(Long brn_id) {
        return em.createNamedQuery("BrnQtyTrgetVu.findByBrnId").setParameter("brnId", brn_id).getResultList();
    }

    public DmsTrnsOrdrDt findDmsTrnsOrdrDt(Long brn_id, Long ordr_no, String govern_name, String region_name, String mobile, String tel1, String clnt_name, String address) {
        List<DmsTrnsOrdrDt> l = em.createNamedQuery("DmsTrnsOrdrDt.findAll").setParameter("brn_id", brn_id).setParameter("ordr_no", ordr_no).setParameter("govern_name", govern_name).setParameter("region_name", region_name).setParameter("tel1", tel1).setParameter("mobile", mobile).setParameter("address", address).setParameter("clnt_name", clnt_name).getResultList();
        if (l.isEmpty()) {
            return null;
        } else {
            return l.get(0);
        }
    }

    public List<String> findGovernNameBySubstr(String name) {
        System.out.println("city name" + name);
        return em.createNamedQuery("CrmkGovern.findByName").setParameter("name", "%" + name.trim() + "%").getResultList();
    }

    public List<String> findRegionNameBySubstr(String name, String gvrn_name) {
        return em.createNamedQuery("CrmkRegion.findByName").setParameter("name", "%" + name.trim() + "%").setParameter("gvrn_name", "%" + gvrn_name.trim() + "%").getResultList();
    }

    public DmsTransportOrdrHd findDmsOrdrHdById(Long id) {
        return (DmsTransportOrdrHd) em.createNamedQuery("DmsTransportOrdrHd.findById").setParameter("id", id).getSingleResult();
    }

    public void mergeDmsTranOrdrHd(DmsTransportOrdrHd dmsTransportOrdrHd) {
        em.merge(dmsTransportOrdrHd);
    }

    @Override
    public Long chkEmpHaveEmail(Long emp) {
        return (Long) em.createNamedQuery("HrEmpInfo.chkEmpHaveEmail").setParameter("empNo", emp).getSingleResult();
    }

    public int chkFundBorrow(Long emp_no, Long g1, Long g2, Long amount, Long months, Long sal, Date startDate) {
        Query q = em.createNativeQuery("select HR_CHK_FUND_BORROW(?,?,?,?,?,?,?) from dual");
        q.setParameter(1, emp_no);
        q.setParameter(2, g1);
        q.setParameter(3, g2);
        q.setParameter(4, amount);
        q.setParameter(5, months);
        q.setParameter(6, sal);
        q.setParameter(7, startDate);
        return Integer.parseInt(q.getSingleResult().toString());
    }

    public void persistFundBorrow(HrBorrowFundRequest hrBorrowFundRequest) {
        em.persist(hrBorrowFundRequest);
    }

    public void mergeFundBorrow(HrBorrowFundRequest hrBorrowFundRequest) {
        em.merge(hrBorrowFundRequest);
    }

    public List<HrBorrowFundRequest> findHrBorrowFundRequests(HrEmpInfo empNo) {
        try {
            return em.createNamedQuery("HrBorrowFundRequest.findByEmpNo").setParameter("empNo", empNo).getResultList();
        } catch (Exception e) {
            return null;
        }

    }

    public List<HrBorrowFundRequest> findAllGuaranteeRequests(HrEmpInfo guarantee) {
        return em.createNamedQuery("HrBorrowFundRequest.findByAllGuarantee").setParameter("guarantee", guarantee).getResultList();
    }

    public List<HrBorrowFundRequest> findDeptMngRequests(HrEmpInfo deptMng) {
        return em.createNamedQuery("HrBorrowFundRequest.findByDeptMng").setParameter("deptMng", deptMng).getResultList();
    }

    public List<HrBorrowFundRequest> findFundBorrowRequestsNeedConfirm() {
        return em.createNamedQuery("HrBorrowFundRequest.findRequestsNeedConfirm").getResultList();
    }

    public List<CrmkOrdrAndRmnWithoutSrf> findOrdrAndRemainWithoutSrfPerEmp(int first, int pageSize, Map<String, Object> filters, int rowCount) {
        Query q = em.createNamedQuery("CrmkOrdrAndRmnWithoutSrf.findAll");
        if (filters.containsKey("fromDate")) {
            q.setParameter("fromDate", filters.get("fromDate"));
        } else {
            q.setParameter("fromDate", null);
        }
        if (filters.containsKey("toDate")) {
            q.setParameter("toDate", filters.get("toDate"));
        } else {
            q.setParameter("toDate", null);
        }
        if (filters.containsKey("brnId")) {
            q.setParameter("brnId", filters.get("brnId"));
        } else {
            q.setParameter("brnId", null);
        }

        if (filters.containsKey("rmnFlag")) {
            q.setParameter("rmnFlag", filters.get("rmnFlag"));
        } else {
            q.setParameter("rmnFlag", 0L);
        }

        if (filters.containsKey("no")) {
            q.setParameter("no", filters.get("no"));
        } else {
            q.setParameter("no", null);
        }
        if (filters.containsKey("trnsNo")) {
            q.setParameter("trnsNo", filters.get("trnsNo"));
        } else {
            q.setParameter("trnsNo", null);
        }
        if (filters.containsKey("actQty")) {
            q.setParameter("actQty", filters.get("actQty"));
        } else {
            q.setParameter("actQty", null);
        }
        if (filters.containsKey("empName")) {
            q.setParameter("empName", "%" + filters.get("empName") + "%");
        } else {
            q.setParameter("empName", "");
        }
        if (filters.containsKey("name")) {
            q.setParameter("name", "%" + filters.get("name") + "%");
        } else {
            q.setParameter("name", "");
        }
        if (filters.containsKey("clntName")) {
            q.setParameter("clntName", "%" + filters.get("clntName") + "%");
        } else {
            q.setParameter("clntName", null);
        }
        if (filters.containsKey("showroomName")) {
            q.setParameter("showroomName", "%" + filters.get("showroomName") + "%");
        } else {
            q.setParameter("showroomName", null);
        }
        if (filters.containsKey("phone")) {
            q.setParameter("phone", filters.get("phone"));
        } else {
            q.setParameter("phone", null);
        }
        if (filters.containsKey("crmkSehy")) {
            q.setParameter("crmkSehy", filters.get("crmkSehy"));
        } else {
            q.setParameter("crmkSehy", null);
        }
        if (filters.containsKey("rmnNo")) {
            q.setParameter("rmnNo", filters.get("rmnNo"));
        } else {
            q.setParameter("rmnNo", null);
        }
        if (filters.containsKey("ordrType")) {
            q.setParameter("ordrType", filters.get("ordrType"));
        } else {
            q.setParameter("ordrType", 3);
        }
        if (filters.containsKey("shadow")) {
            q.setParameter("shadow", Integer.parseInt(filters.get("shadow").toString()));
        } else {
            q.setParameter("shadow", 3);
        }
        if (rowCount >= 100) {
            q.setFirstResult(first);
            q.setMaxResults(pageSize);
        }
        return q.getResultList();

    }

    public int countOrdrAndRemainWithoutSrfPerEmp(Map<String, Object> filters) {
        Query q = em.createNamedQuery("CrmkOrdrAndRmnWithoutSrf.findCount");
        if (filters.containsKey("fromDate")) {
            q.setParameter("fromDate", filters.get("fromDate"));
        } else {
            q.setParameter("fromDate", null);
        }
        if (filters.containsKey("toDate")) {
            q.setParameter("toDate", filters.get("toDate"));
        } else {
            q.setParameter("toDate", null);
        }
        if (filters.containsKey("brnId")) {
            q.setParameter("brnId", filters.get("brnId"));
        } else {
            q.setParameter("brnId", null);
        }
        if (filters.containsKey("no")) {
            q.setParameter("no", filters.get("no"));
        } else {
            q.setParameter("no", null);
        }
        if (filters.containsKey("trnsNo")) {
            q.setParameter("trnsNo", filters.get("trnsNo"));
        } else {
            q.setParameter("trnsNo", null);
        }
        if (filters.containsKey("actQty")) {
            q.setParameter("actQty", filters.get("actQty"));
        } else {
            q.setParameter("actQty", null);
        }
        if (filters.containsKey("empName")) {
            q.setParameter("empName", "%" + filters.get("empName") + "%");
        } else {
            q.setParameter("empName", "");
        }
        if (filters.containsKey("rmnFlag")) {
            q.setParameter("rmnFlag", filters.get("rmnFlag"));
        } else {
            q.setParameter("rmnFlag", 0L);
        }
        if (filters.containsKey("name")) {
            q.setParameter("name", "%" + filters.get("name") + "%");
        } else {
            q.setParameter("name", "");
        }
        if (filters.containsKey("clntName")) {
            q.setParameter("clntName", "%" + filters.get("clntName") + "%");
        } else {
            q.setParameter("clntName", null);
        }
        if (filters.containsKey("showroomName")) {
            q.setParameter("showroomName", "%" + filters.get("showroomName") + "%");
        } else {
            q.setParameter("showroomName", null);
        }
        if (filters.containsKey("phone")) {
            q.setParameter("phone", filters.get("phone"));
        } else {
            q.setParameter("phone", null);
        }
        if (filters.containsKey("crmkSehy")) {
            q.setParameter("crmkSehy", filters.get("crmkSehy"));
        } else {
            q.setParameter("crmkSehy", null);
        }
        if (filters.containsKey("rmnNo")) {
            q.setParameter("rmnNo", filters.get("rmnNo"));
        } else {
            q.setParameter("rmnNo", null);
        }
        if (filters.containsKey("ordrType")) {
            q.setParameter("ordrType", filters.get("ordrType"));
        } else {
            q.setParameter("ordrType", 3);
        }
        if (filters.containsKey("shadow")) {
            q.setParameter("shadow", Integer.parseInt(filters.get("shadow").toString()));
        } else {
            q.setParameter("shadow", 3);
        }
        return Integer.parseInt(q.getSingleResult().toString());
    }

    public List<String> findOrdrClntNames(Long brnId) {
        return em.createNamedQuery("CrmkOrdrAndRmnWithoutSrf.findAllClntName").setParameter("brnId", brnId).getResultList();
    }

    public List<String> findOrdrEmployeeNames(Long brnId) {
        return em.createNamedQuery("CrmkOrdrAndRmnWithoutSrf.findAllEmpName").setParameter("brnId", brnId).getResultList();
    }

    public List<String> findOrdrDriverNames(Long brnId) {
        return em.createNamedQuery("CrmkOrdrAndRmnWithoutSrf.findAllDrvName").setParameter("brnId", brnId).getResultList();
    }

    public List<Object[]> findActualQtyDetailsPerGovern(String itemCode, Long governId, String crmkSehy) {
        Query query = null;
        if (crmkSehy.equals("C")) {
            query = em.createNativeQuery("select sum(qty) q,brn.name store_name,(nvl(i.size_id,0) ||'/'||nvl(i.dekala_id,0) ||'/' || nvl(i.factory_no,0)||'/' ||i.frz) itemCode,C,i.TONE,gov.GOVERN_ID "
                    + " from crmk.CRMK_C_FREE_MV mv,crmk.crmk_crmk_item i, crmk.crmk_branch brn,crmk.crmk_brn_govern gov "
                    + " where mv.item_id = i.id and mv.store_id=brn.id and brn.id=gov.BRN_ID and (nvl(i.size_id,0) ||'/'||nvl(i.dekala_id,0) ||'/' || nvl(i.factory_no,0)||'/' ||i.frz)=? and gov.GOVERN_ID=? "
                    + " group by gov.GOVERN_ID,brn.name,(nvl(i.size_id, 0) || '/' || nvl(i.dekala_id, 0) || '/' || nvl(i.factory_no, 0) || '/' || i.frz),i.C,i.TONE "
                    + " having sum(qty)>0 order by tone,c");

        } else if (crmkSehy.equals("D")) {
            query = em.createNativeQuery("select sum(qty) q,brn.name store_name,i.no_c_tone itemCode,C,i.TONE,gov.GOVERN_ID "
                    + " from crmk.CRMK_D_FREE_MV mv,crmk.crmk_dcre_item i, crmk.crmk_branch brn,crmk.crmk_brn_govern gov "
                    + " where mv.item_id = i.id and mv.store_id=brn.id and brn.id=gov.BRN_ID and i.no_c_tone=? and gov.GOVERN_ID=? "
                    + " group by gov.GOVERN_ID,brn.name,i.no_c_tone,i.C,i.TONE"
                    + " having sum(qty)>0 order by tone,c");
        }
        query.setParameter(1, itemCode);
        query.setParameter(2, governId);
        return query.getResultList();
    }

    public List<Object[]> findCrmkOrdrAndRmnWithoutSrfsByOrdrIds(String ordrIds) {
        Query query = em.createNativeQuery("SELECT distinct c.remain_id,c.rmn_no,c.clnt_name,c.phone,c.rmn_prd_id,c.rmn_trns_date FROM crmk.Crmk_Ordr_And_Rmn_Without_Srf c where c.ordr_id in(" + ordrIds + ")");
        return query.getResultList();
    }

    @Override
    public List<Object[]> getStoreByGovern(Long governId, String target) {
        Query query = em.createNativeQuery("select brn.id,brn.name from crmk.crmk_branch brn,crmk.crmk_brn_govern gov "
                + " where brn.id=gov.BRN_ID "
                + " and brn.STORE_SHOW in ( " + target + ")"
                + " and gov.GOVERN_ID=? ");
        query.setParameter(1, governId);
        return query.getResultList();
    }

    public List<Object[]> findDriversForBrn(Long brnId) {
        Query query = em.createNativeQuery("select drv.id,drv.name,drv.MOBILE "
                + "from dms.DMS_BRN_DRV brn,dms.DMS_DRIVERS drv "
                + "where brn.DRV_ID=drv.ID and brn.ACTIVE='Y' "
                + "and brn.BRN_ID=? order by drv.id");
        query.setParameter(1, brnId);
        return query.getResultList();
    }

    public CrmkRmnPrintRequest findCrmkRmnPrintRequestById(Long reqId) {
        return (CrmkRmnPrintRequest) em.createNamedQuery("CrmkRmnPrintRequest.findById").setParameter("id", reqId).getSingleResult();
    }

    public List<CrmkOrdrAndRmnWithoutSrf> findCrmkOrdrAndRmnWithoutSrf(Long reqId) {
        return em.createNamedQuery("CrmkOrdrAndRmnWithoutSrf.findByReqId").setParameter("reqId", reqId).getResultList();
    }

    public void persistCrmkRmnPrintRequest(List<CrmkRmnPrintRequest> requestList) {
        for (CrmkRmnPrintRequest crmkRmnPrintRequest : requestList) {
            em.persist(crmkRmnPrintRequest);
        }
    }

    public void persistCrmkRmnPrintRequest(CrmkRmnPrintRequest request) {
        em.persist(request);
    }

    public void mergeCrmkRmnPrintRequest(List<CrmkRmnPrintRequest> requestList) {
        for (CrmkRmnPrintRequest crmkRmnPrintRequest : requestList) {
            em.merge(crmkRmnPrintRequest);
        }
    }

    public void mergeCrmkRmnPrintRequest(CrmkRmnPrintRequest request) {
        em.merge(request);
    }

    public List<CrmkOrdrAndRmnWithoutSrf> findAllRequestsPerBrn(int first, int pageSize, Map<String, Object> filters, int rowCount) {
        Query q = em.createNamedQuery("CrmkOrdrAndRmnWithoutSrf.findAllRequestsPerBrn");
        if (filters.containsKey("trgtBrnId")) {
            q.setParameter("trgtBrnId", filters.get("trgtBrnId"));
        } else {
            q.setParameter("trgtBrnId", null);
        }
        if (filters.containsKey("brnId")) {
            q.setParameter("brnId", filters.get("brnId"));
        } else {
            q.setParameter("brnId", null);
        }
        if (filters.containsKey("empName")) {
            q.setParameter("empName", "%" + filters.get("empName") + "%");
        } else {
            q.setParameter("empName", "");
        }
        if (filters.containsKey("driverName")) {
            q.setParameter("driverName", "%" + filters.get("driverName") + "%");
        } else {
            q.setParameter("driverName", "");
        }
        if (filters.containsKey("clntName")) {
            q.setParameter("clntName", "%" + filters.get("clntName") + "%");
        } else {
            q.setParameter("clntName", null);
        }
        if (filters.containsKey("showroomName")) {
            q.setParameter("showroomName", "%" + filters.get("showroomName") + "%");
        } else {
            q.setParameter("showroomName", null);
        }
        if (filters.containsKey("clntPhone")) {
            q.setParameter("clntPhone", filters.get("clntPhone"));
        } else {
            q.setParameter("clntPhone", null);
        }
        if (filters.containsKey("crmkSehy")) {
            q.setParameter("crmkSehy", filters.get("crmkSehy"));
        } else {
            q.setParameter("crmkSehy", null);
        }
        if (filters.containsKey("rmnNo")) {
            q.setParameter("rmnNo", filters.get("rmnNo"));
        } else {
            q.setParameter("rmnNo", null);
        }
        if (filters.containsKey("ordrNo")) {
            q.setParameter("ordrNo", filters.get("ordrNo"));
        } else {
            q.setParameter("ordrNo", null);
        }
        if (filters.containsKey("driverPhone")) {
            q.setParameter("driverPhone", filters.get("driverPhone"));
        } else {
            q.setParameter("driverPhone", null);
        }
        if (filters.containsKey("load_date")) {
            q.setParameter("load_date", filters.get("load_date"));
        }
        if (rowCount >= 100) {
            q.setFirstResult(first);
            q.setMaxResults(pageSize);
        }
        return q.getResultList();
    }

    public int findCountOfRequestsPerBrn(Map<String, Object> filters) {
        Query q = em.createNamedQuery("CrmkOrdrAndRmnWithoutSrf.findCntOfRequestsPerBrn");
        if (filters.containsKey("trgtBrnId")) {
            q.setParameter("trgtBrnId", filters.get("trgtBrnId"));
        } else {
            q.setParameter("trgtBrnId", null);
        }
        if (filters.containsKey("brnId")) {
            q.setParameter("brnId", filters.get("brnId"));
        } else {
            q.setParameter("brnId", null);
        }
        if (filters.containsKey("empName")) {
            q.setParameter("empName", "%" + filters.get("empName") + "%");
        } else {
            q.setParameter("empName", "");
        }
        if (filters.containsKey("driverName")) {
            q.setParameter("driverName", "%" + filters.get("driverName") + "%");
        } else {
            q.setParameter("driverName", "");
        }
        if (filters.containsKey("clntName")) {
            q.setParameter("clntName", "%" + filters.get("clntName") + "%");
        } else {
            q.setParameter("clntName", null);
        }
        if (filters.containsKey("showroomName")) {
            q.setParameter("showroomName", "%" + filters.get("showroomName") + "%");
        } else {
            q.setParameter("showroomName", null);
        }
        if (filters.containsKey("clntPhone")) {
            q.setParameter("clntPhone", filters.get("clntPhone"));
        } else {
            q.setParameter("clntPhone", null);
        }
        if (filters.containsKey("crmkSehy")) {
            q.setParameter("crmkSehy", filters.get("crmkSehy"));
        } else {
            q.setParameter("crmkSehy", null);
        }
        if (filters.containsKey("rmnNo")) {
            q.setParameter("rmnNo", filters.get("rmnNo"));
        } else {
            q.setParameter("rmnNo", null);
        }

        if (filters.containsKey("ordrNo")) {
            q.setParameter("ordrNo", filters.get("ordrNo"));
        } else {
            q.setParameter("ordrNo", null);
        }

        if (filters.containsKey("driverPhone")) {
            q.setParameter("driverPhone", filters.get("driverPhone"));
        } else {
            q.setParameter("driverPhone", null);
        }
        if (filters.containsKey("load_date")) {
            q.setParameter("load_date", filters.get("load_date"));
        }
        return Integer.parseInt(q.getSingleResult().toString());
    }

    public List<Object[]> findRemainDt(Long rmnId, String crmkSehy) {
        Query query = null;
        if (crmkSehy.equals("C")) {
            query = em.createNativeQuery("select rownum serial,typ.NAME typeName,siz.NAME sizeName,item.FACTORY_NO,dekala.NAME dekalaName,item.FRZ,item.C,item.TONE,dt.QTY "
                    + " ,ROUND(((((dt.QTY/CRTN_MTR)-TRUNC(dt.QTY/CRTN_MTR))*CRTN_MTR)/((siz.WIDTH*siz.LENGTH)/10000)),1) BLT,TRUNC(dt.QTY/CRTN_MTR) CRTN "
                    + " from CRMK.CRMK_C_REMAIN_DT dt,CRMK.CRMK_CRMK_DEKALA dekala,CRMK.CRMK_CRMK_SIZE siz,CRMK.CRMK_CRMK_ITEM item,CRMK.CRMK_C_ORDR_DT o_dt,CRMK.CRMK_CRMK_TYPE typ "
                    + " where dt.ORDR_DT=o_dt.ID and   o_dt.ITEM_ID=item.id and   item.SIZE_ID=siz.ID(+) and   item.TYPE_ID=typ.ID and   item.DEKALA_ID=dekala.ID(+) and dt.HD_ID=?");
        } else if (crmkSehy.equals("D")) {
            query = em.createNativeQuery("select rownum serial,typ.NAME typeName,siz.NAME sizeName,item.FACTORY_NO,dekala.NAME dekalaName,item.FRZ,item.C,item.TONE,dt.QTY "
                    + " ,ROUND(((((dt.QTY/CRTN_MTR)-TRUNC(dt.QTY/CRTN_MTR))*CRTN_MTR)/((siz.WIDTH*siz.LENGTH)/10000)),1) BLT,TRUNC(dt.QTY/CRTN_MTR) CRTN,color.name colorName,item.TABLOW "
                    + " from CRMK.CRMK_D_REMAIN_DT dt,CRMK.CRMK_DCRE_DEKALA dekala,CRMK.CRMK_DCRE_SIZE siz,CRMK.CRMK_DCRE_ITEM item,CRMK.CRMK_D_ORDR_DT o_dt,CRMK.CRMK_DCRE_TYPE typ,CRMK.CRMK_COLOR color "
                    + " where dt.ORDR_DT=o_dt.ID and   o_dt.ITEM_ID=item.id and   item.SIZE_ID=siz.ID(+) and   item.TYPE_ID=typ.ID and   item.DEKALA_ID=dekala.ID(+)  and item.COLOR_ID=color.ID(+) and  dt.HD_ID=?");
        }

        query.setParameter(1, rmnId);
        return query.getResultList();
    }

    public String findQtyAsString(Double qty) {
        Query query = em.createNativeQuery("select FU_TAFKEET2(" + qty + ") from dual");
        return (String) query.getSingleResult();
    }

    public List<String> findTrgtClntNameList(Long brnId) {
        return em.createNamedQuery("CrmkOrdrAndRmnWithoutSrf.findAllTrgtClntName").setParameter("brnId", brnId).getResultList();
    }

    public List<String> findShowNameNameList(Long brnId) {
        return em.createNamedQuery("CrmkOrdrAndRmnWithoutSrf.findAllShowNameName").setParameter("brnId", brnId).getResultList();
    }

    public List<String> findEmpRequestedNameList(Long brnId) {
        return em.createNamedQuery("CrmkOrdrAndRmnWithoutSrf.findAllEmpRequestedName").setParameter("brnId", brnId).getResultList();
    }

    public List<String> findTrgtDriverNameList(Long brnId) {
        return em.createNamedQuery("CrmkOrdrAndRmnWithoutSrf.findAllDriverRequestedName").setParameter("brnId", brnId).getResultList();
    }

    public List<Object[]> findBrnRmnPrintRequestCnt(Long trgBrn, Date loadDate) {
        Query query = em.createNativeQuery("select brn.id,brn.NAME,cnt,to_char(last_req,'dd-mm-rrrr hh24:mi'),'false'"
                + " from (select count(distinct r.id) cnt,max(r.trns_date) last_req,r.brn_requested_id "
                + " from crmk.Crmk_Rmn_Print_Request r,crmk.Crmk_Rmn_Without_Srf_MV mv"
                + " where r.id=mv.req_id "
                + " and nvl(r.printed,'N')='N' "
                + " and r.TARGET_BRN_ID=? "
                + " and r.LOAD_DATE=? "
                + " group by r.brn_requested_id) req,crmk.crmk_branch brn "
                + " where req.brn_requested_id(+)=brn.id "
                + " and   cnt>0 "
                + " order by cnt desc");
        query.setParameter(1, trgBrn);
        query.setParameter(2, loadDate);
        return query.getResultList();
    }

    public void refreshCrmkRmnWithoutSrfMv() {
        Query q = em.createNativeQuery("call dbms_mview.refresh('CRMK.CRMK_RMN_WITHOUT_SRF_MV','C')");
        q.executeUpdate();
    }

    public Long chkRmnDispatch(String crmkSehy, Long rmnId) {
        Query query;
        if (crmkSehy.equals("C")) {
            query = em.createNativeQuery("select count(*) from CRMK.CRMK_C_ME2ORDR_HD where rmn_id=?");
        } else if (crmkSehy.equals("D")) {
            query = em.createNativeQuery("select count(*) from CRMK.CRMK_D_ME2ORDR_HD where rmn_id=?");
        } else {
            query = em.createNativeQuery("select count(*) from CRMK.CRMK_S_ME2ORDR_HD where rmn_id=?");
        }
        query.setParameter(1, rmnId);
        return Long.parseLong(query.getSingleResult().toString());
    }

    public List<HrShiftDt> findAllShift(Long deptId, Long locId) {
        return em.createNamedQuery("HrShiftDt.findAll").setParameter("deptId", deptId).setParameter("locId", locId).getResultList();
    }

    public List<HrShiftDt> findAllShiftByDeptId(Long deptId) {
        return em.createNamedQuery("HrShiftDt.findByDeptId").setParameter("deptId", deptId).getResultList();
    }

    public void insertFundBorrow(Long v_req_id, Long v_emp_no, Long v_amount, Date v_start_date, Long v_pay_months, Long v_g1, Long v_g2, Long v_mng, String v_g1_phone, String v_g2_phone, String v_mng_phone) {
        Query q = em.createNativeQuery("call hr_insert_fund_borrow(?,?,?,?,?,?,?,?,?,?,?)");
        q.setParameter(1, v_req_id);
        q.setParameter(2, v_emp_no);
        q.setParameter(3, v_amount);
        q.setParameter(4, v_start_date);
        q.setParameter(5, v_pay_months);
        q.setParameter(6, v_g1);
        q.setParameter(7, v_g2);
        q.setParameter(8, v_mng);
        q.setParameter(9, v_g1_phone);
        q.setParameter(10, v_g2_phone);
        q.setParameter(11, v_mng_phone);
        q.executeUpdate();

    }

    public HrFundBorrowSetup findBorrowSetup() {
        return (HrFundBorrowSetup) em.createNamedQuery("HrFundBorrowSetup.findAll").getResultList().get(0);
    }

    public HrFundAdvanceSetup findAdvanceSetup() {
        return (HrFundAdvanceSetup) em.createNamedQuery("HrFundAdvanceSetup.findAll").getResultList().get(0);
    }

    public List<Object[]> findEmpTarget(HrEmpInfo hrEmpInfo, String month, String year) {
        Query q = em.createNativeQuery("select BRN_ID, NET, NET_C, NET_D, NET_S, QTY_C, QTY_D, QTY_S, TRGT, TRGT_PERCENT,QTY,HR_ID"
                + " from( "
                + " select hr_id,crmk_id BRN_ID,emp_name,trgt,net_c,net_d,net_s,qty_c,qty_d,qty_s,qty,net,trgt_percent "
                + " from(select hr_id,sum(target) trgt,sum(net_c) net_c "
                + " ,sum(net_d) net_d,sum(net_s) net_s,sum(qty_c) qty_c,sum(qty_d) qty_d "
                + " ,sum(qty_s) qty_s,sum(qty) qty,sum(net) net,round(sum(net)/sum(target)*100) trgt_percent "
                + " from crmk.EMP_QTY_TRGET_YEAR_MV "
                + " where months =" + month
                + " and years =" + year
                + " group by hr_id "
                + " having sum(target)>0) vu,hr.hr_emp_info info,hr.hr_location loc "
                + " where vu.hr_id=info.emp_no  "
                + " and loc.id=info.LOC_ID  "
                + " and hr_id=" + hrEmpInfo.getEmpNo()
                //                + " and job_id=49 "
                + ")");
        return q.getResultList();
    }

    public Long chkFundBorrowDelay(Long empNo, Long borrowNo, Long delayMonth, Long delayYear) {
        Query q = em.createNativeQuery("select HR_CHK_FUND_BORROW_DELAY(?,?,?,?) from dual");
        q.setParameter(1, empNo);
        q.setParameter(2, borrowNo);
        q.setParameter(3, delayMonth);
        q.setParameter(4, delayYear);
        return Long.parseLong(q.getSingleResult().toString());
    }

    public void applyFundBorrowDelay(Long empNo, Long borrowNo, Long delayMonth, Long delayYear) {
        Query q = em.createNativeQuery("call HR_APPLY_FUND_BORROW_DELAY(?,?,?,?)");
        q.setParameter(1, empNo);
        q.setParameter(2, borrowNo);
        q.setParameter(3, delayMonth);
        q.setParameter(4, delayYear);
        q.executeUpdate();
    }

    public void persistHrFundBorrowDelayReq(HrFundBorrowDelayReq hrFundBorrowDelayReq) {
        em.persist(hrFundBorrowDelayReq);
    }

    public BrnQtyTrgetYearVu findBrnQtyTrgetYearVu(Long brnId, String months, String selectedYear) {
        try {
            return (BrnQtyTrgetYearVu) em.createNamedQuery("BrnQtyTrgetYearVu.findByBrnIdAndMonths").setParameter("brn_id", brnId).setParameter("months", months).setParameter("year", selectedYear).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Long findBrnOrdrPerMonth(Long trgt_percent, String month, String year) {
        return (Long) em.createNamedQuery("BrnQtyTrgetYearVu.findOrdr").setParameter("trgt_percent", trgt_percent).setParameter("month", month).setParameter("year", year).getSingleResult();
    }

    public List<EmpQtyTrgetYearMv> findEmpQtyTrgetYearVuList(Long brnId, String month) {
        return em.createNamedQuery("EmpQtyTrgetYearMv.findAllSalesMenInBrn").setParameter("brn_id", brnId).setParameter("month", month).getResultList();
    }

    public List<BrnQtyTrgetYearVu> findAllBrnTrgtForPreviousMonth(String month, String year) {
        return em.createNamedQuery("BrnQtyTrgetYearVu.findByMonths").setParameter("month", month).setParameter("year", year).getResultList();
    }

    public List<Object[]> findShowroomsPerCrmkName() {
        Query query = em.createNativeQuery("select loc.id,brn.name from hr.hr_location loc,crmk.crmk_branch brn where loc.crmk_id=brn.id and loc.type_id=1 order by loc.id");
        return query.getResultList();
    }

    public List<Object[]> findBrnQtyTrgetYearVuInPeriod(String months, Long loc_id) {
        Query query = em.createNativeQuery("select * from (select brn_id,sum(brn_target) brn_target,sum(net_c) net_c,sum(net_d) net_d"
                + ",sum(net_s) net_s,sum(qty_c) qty_c,sum(qty_d) qty_d"
                + ",sum(qty_s) qty_s,sum(qty) qty,sum(net) net,round(sum(net)/sum(brn_target)*100) trgt_percent,loc.name,loc_id "
                + "from CRMK.BRN_QTY_TRGET_YEAR_MV vu,hr.hr_location loc "
                + "where vu.loc_id=loc.id"
                + " and months in (" + months + ") "
                + (loc_id == null ? "" : "and   loc.id=" + loc_id)
                + " group by brn_id,loc.name,loc_id"
                + ") order by trgt_percent desc");
        return query.getResultList();
    }

    public List<Object[]> findBrnQtyTrgetYearVuInPeriod(String monthsFrom, String yearsFrom, String monthsTo, String yearsTo, Long loc_id) {
        Query query = em.createNativeQuery("select * from (select brn_id,sum(brn_target) brn_target,sum(net_c) net_c,sum(net_d) net_d"
                + ",sum(net_s) net_s,sum(qty_c) qty_c,sum(qty_d) qty_d"
                + ",sum(qty_s) qty_s,sum(qty) qty,sum(net) net,round(sum(net)/sum(brn_target)*100) trgt_percent,loc.name,loc_id "
                + "from CRMK.BRN_QTY_TRGET_YEAR_MV vu,hr.hr_location loc "
                + "where vu.loc_id=loc.id"
                + " and to_date(months||'-'||years,'mm-yyyy') between to_date('" + monthsFrom + "-" + yearsFrom + "','mm-yyyy') and to_date('" + monthsTo + "-" + yearsTo + "','mm-yyyy')"
                + (loc_id == null ? "" : "and   loc.id=" + loc_id)
                + " group by brn_id,loc.name,loc_id"
                + ") order by trgt_percent desc");
        return query.getResultList();
    }

    public List<Object[]> findEmpQtyTrgetYearVuListInPeriod(String months, String years, Long loc_id) {
        Query query = em.createNativeQuery("select hr_id,search_brn,emp_name,brn_target,net_c,net_d,net_s,qty_c,qty_d,qty_s,qty,net,trgt_percent"
                + ",decode(loc.crmk_id,search_brn,1,0) current_brn"
                + " from(select hr_id,sum(decode(target,0,search_trgt,target)) brn_target,sum(net_c) net_c"
                + ",sum(net_d) net_d,sum(net_s) net_s,sum(qty_c) qty_c,sum(qty_d) qty_d"
                + ",sum(qty_s) qty_s,sum(qty) qty,sum(net) net,round(sum(net)/sum(decode(target,0,search_trgt,target))*100) trgt_percent,search_brn"
                + " from crmk.EMP_QTY_TRGET_YEAR_MV"
                + " where months in (" + months + ") "
                + " and years = " + years
                + " group by hr_id,search_brn"
                + " having sum(decode(target,0,search_trgt,target))>0) vu,hr.hr_emp_info info "
                + ",hr.hr_location loc,hr.hr_location hr_brn "
                + " where vu.hr_id=info.emp_no "
                + " and vu.search_brn=hr_brn.crmk_id"
                + " and hr_brn.id=" + loc_id
                + " and loc.id=info.LOC_ID "
                //                + " and loc.crmk_id=" + loc_id
                //                + " and job_id=49"
                + " order by trgt_percent desc");
        System.out.println("query" + months + "   " + years + "  " + loc_id);
        return query.getResultList();
    }

    public List<Object[]> findEmpQtyTrgetYearVuListInPeriod(String monthsFrom, String yearsFrom, String monthsTo, String yearsTo, Long loc_id) {
        Query query = em.createNativeQuery("select hr_id,search_brn,emp_name,brn_target,net_c,net_d,net_s,qty_c,qty_d,qty_s,qty,net,trgt_percent"
                + ",decode(loc.crmk_id,search_brn,1,0) current_brn"
                + " from(select hr_id,sum(decode(target,0,search_trgt,target)) brn_target,sum(net_c) net_c"
                + ",sum(net_d) net_d,sum(net_s) net_s,sum(qty_c) qty_c,sum(qty_d) qty_d"
                + ",sum(qty_s) qty_s,sum(qty) qty,sum(net) net,round(sum(net)/sum(decode(target,0,search_trgt,target))*100) trgt_percent,search_brn"
                + " from crmk.EMP_QTY_TRGET_YEAR_MV"
                + " where to_date(months||'-'||years,'mm-yyyy') between to_date('" + monthsFrom + "-" + yearsFrom + "','mm-yyyy') and to_date('" + monthsTo + "-" + yearsTo + "','mm-yyyy')"
                + " group by hr_id,search_brn"
                + " having sum(decode(target,0,search_trgt,target))>0) vu,hr.hr_emp_info info "
                + ",hr.hr_location loc,hr.hr_location hr_brn "
                + " where vu.hr_id=info.emp_no "
                + " and vu.search_brn=hr_brn.crmk_id"
                + " and hr_brn.id=" + loc_id
                + " and loc.id=info.LOC_ID "
                //                + " and loc.crmk_id=" + loc_id
                //                + " and job_id=49"
                + " order by trgt_percent desc");
        System.out.println("query" + monthsFrom + "   " + yearsFrom + " >>>>>>>>>> " + monthsTo + "        " + yearsTo + "     " + loc_id);
        return query.getResultList();
    }

    public List<String> findTrgtYearList() {
        Query query = em.createNativeQuery("select distinct years from crmk.EMP_QTY_TRGET_YEAR_MV order by to_number(years) desc");
        return query.getResultList();
    }

    public Object[] findTotalBrnQtyTrgetYearVuInPeriod(String monthsFrom, String yearsFrom, String monthsTo, String yearsTo) {
        Query query = em.createNativeQuery("select sum(brn_target) brn_target,sum(net_c) net_c,sum(net_d) net_d "
                + ",sum(net_s) net_s,sum(qty_c) qty_c,sum(qty_d) qty_d "
                + ",sum(qty_s) qty_s,sum(qty) qty,sum(net) net,round(sum(net)/sum(brn_target)*100) trgt_percent "
                + "from CRMK.BRN_QTY_TRGET_YEAR_MV vu "
                + "where to_date(months||'-'||years,'mm-yyyy') between to_date('" + monthsFrom + "-" + yearsFrom + "','mm-yyyy') and to_date('" + monthsTo + "-" + yearsTo + "','mm-yyyy')");
        return (Object[]) query.getSingleResult();
    }

    public List<EmpQtyTrgetYearMv> findEmpTargetByMonth(HrEmpInfo hrEmpInfo, String month) {
        return em.createNamedQuery("EmpQtyTrgetYearMv.findEmpTarget").setParameter("emp_no", hrEmpInfo.getEmpNo()).setParameter("months", month).getResultList();
    }

    public Long findSalesOrderByMonth(Long emp_no, Long brn_id, Long percent, String month, String year) {
        Query query = em.createNativeQuery("select count(*) "
                + " from(select hr_id,sum(target) brn_target,sum(net_c) net_c "
                + ",sum(net_d) net_d,sum(net_s) net_s,sum(qty_c) qty_c,sum(qty_d) qty_d "
                + ",sum(qty_s) qty_s,sum(qty) qty,sum(net) net,round(sum(net)/sum(target)*100) trgt_percent "
                + " from crmk.EMP_QTY_TRGET_YEAR_MV "
                + " where months=" + month
                + " and years=" + year
                + " group by hr_id "
                + " having sum(target)>0) vu,hr.hr_emp_info info,hr.hr_location loc "
                + " where vu.hr_id=info.emp_no "
                + " and loc.id=info.LOC_ID "
                + " and trgt_percent>=" + percent
                + " and hr_id<>" + emp_no
                + (brn_id == null ? "" : " and loc.crmk_id=" + brn_id)
                //               + " and job_id=49 "
                + " order by trgt_percent desc");
        return Long.parseLong(query.getSingleResult().toString());
    }

    public List<Object[]> findSalesmenWithHeigherTargetByMonths(String monthFrom, String yearFrom, String monthTo, String yearTo) {
        Query query = em.createNativeQuery("select serial,emp_no,emp_name,location,net,target,trgt_percent from "
                + " (select rownum serial ,net,target,trgt_percent,emp_no,emp_name,location from "
                + " (select * from "
                + " (select hr_id,sum(target) target,sum(net) net,round(sum(net)/sum(target)*100) trgt_percent,emp_no,emp_name,location "
                + " from crmk.EMP_QTY_TRGET_YEAR_MV mv,hr.hr_emp_info info "
                + " where mv.hr_id=info.emp_no "
                + " and to_date(months||'-'||years,'mm-yyyy') between to_date('" + monthFrom + "-" + yearFrom + "','mm-yyyy') and to_date('" + monthTo + "-" + yearTo + "','mm-yyyy') "
                + " group by hr_id,emp_no,emp_name,location "
                + " having sum(target)>0)z "
                + " order by  trgt_percent desc )) x "
                + " where serial<=20 "
                + " order by serial");
        return query.getResultList();
    }

    public Object[] findEmpCountAndTrgtPerMonths(Long brn_id, String monthsFrom, String yearsFrom, String monthsTo, String yearsTo) {
        Query query = em.createNativeQuery("SELECT round(sum(d.t_target)/sum(TARGET)) EMP_COUNT,sum(TARGET) "
                + " FROM crmk.crmk_sls_rep d,hr.hr_location loc "
                + " WHERE d.brn_id=loc.crmk_id "
                + " AND   trunc(d.trns_date, 'MONTH')  between to_date('" + monthsFrom + "-" + yearsFrom + "','mm-yyyy') and to_date('" + monthsTo + "-" + yearsTo + "','mm-yyyy') "
                + " and   loc.id=" + brn_id);
        return (Object[]) query.getSingleResult();
    }

    public Long findMaxFundBorrowSerial() {
        Long result = 0L;
        try {
            result = (Long) em.createNamedQuery("HrBorrowFundRequest.maxSerial").getSingleResult();
            if (result == null) {
                return 0L;
            } else {
                return result;
            }
        } catch (NoResultException e) {
            return 0L;
        }
    }

    public List<HrBorrowZamalaHd> findAllFundBorrow(HrEmpInfo emp_no) {
        return em.createNamedQuery("HrBorrowZamalaHd.findAll").setParameter("emp_no", emp_no).getResultList();
    }

    @Override
    public List<HrBorrowZamalaHd> findPreviousFundBorrowForEmp(HrEmpInfo emp_no) {
        return em.createNamedQuery("HrBorrowZamalaHd.findByEmpNo").setParameter("empNo", emp_no).getResultList();
    }

    public HrFundBorrowSummary findFundBorrowSummaryByHdId(HrBorrowZamalaHd hd_id) {
        return (HrFundBorrowSummary) em.createNamedQuery("HrFundBorrowSummary.findByHdId").setParameter("hd_id", hd_id).getSingleResult();
    }

    public Long findNextIdFromDmsTransportOrdrParent() {
        return (Long) em.createNamedQuery("DmsTransportOrdrParent.findMaxId").getSingleResult();
    }

    public void persistDmsTransportOrdrParent(DmsTransportOrdrParent dmsTransportOrdrParent) {
        em.persist(dmsTransportOrdrParent);
    }

    public List<HrProfilePrifilage> findProfilePrivilages(Long grp_id, Long job_id, Long loc_id, Long dept_id, Long emp_no, Long menu_id) {
        Query q = em.createNamedQuery("HrProfilePrifilage.findAll").setParameter("grp_id", grp_id).setParameter("job_id", job_id).setParameter("dept_id", dept_id).setParameter("loc_id", loc_id).setParameter("emp_no", emp_no).setParameter("menu_id", menu_id);
        return q.getResultList();
    }

    public List<HrJobGrp> findJobGrpList() {
        return em.createNamedQuery("HrJobGrp.findAllGrpNames").getResultList();
    }

    public List<HrManagement> findDeptList() {
        return em.createNamedQuery("HrManagement.findAllDeptNames").getResultList();
    }

    public List<HrJobs> findJobList() {
        return em.createNamedQuery("HrJobs.findJobNames").getResultList();
    }

    public List<HrLocation> findLocationList() {
        return em.createNamedQuery("HrLocation.findLocationNames").getResultList();
    }

    public List<HrLocation> findSpecificLocationList(Long current_loc) {
        return em.createNamedQuery("HrLocation.findSpecificLocationNames").setParameter("current_loc", current_loc).getResultList();
    }

    public List<HrEmpInfo> findEmpList() {
        return em.createNamedQuery("HrEmpInfo.findEmpNames").getResultList();
    }

    public List<HrMenuTable> findMenuList() {
        return em.createNamedQuery("HrMenuTable.findMenuNames").getResultList();
    }

    public List<HrMenuTable> findAllMenuList() {
        return em.createNamedQuery("HrMenuTable.findAll").getResultList();
    }

    public List<HrProfilePrifilage> findAllPrivilages(Long grp_id, Long job_id, Long loc_id, Long dept_id, Long emp_no, Long menu_id, String orderByList) {
        String sql = "SELECT * FROM Hr_Profile_Prifilage h "
                + " LEFT JOIN hr_job_grp g on(h.grp_id=g.id)   "
                + " LEFT JOIN hr_location l on(h.loc_id=l.id) "
                + " LEFT JOIN hr_jobs j on(h.job_id=j.id) "
                + " LEFT JOIN hr_management d on(h.dept_id=d.id) "
                + " LEFT JOIN hr_emp_info  e on(h.emp_id=e.emp_no)  "
                + " JOIN hr_menu_table m on(h.new_menu_id=m.id) "
                + " where ((h.emp_id is not null and e.emp_name is not null) or h.emp_id is null) ";
        if (grp_id != null) {
            sql += " and h.grp_Id=" + grp_id;
        }
        if (loc_id != null) {
            sql += " and h.loc_Id=" + loc_id;
        }
        if (job_id != null) {
            sql += " and h.job_Id=" + job_id;
        }
        if (dept_id != null) {
            sql += " and h.dept_Id=" + dept_id;
        }
        if (emp_no != null && emp_no != 0) {
            sql += " and h.emp_Id=" + emp_no;
        }
        if (menu_id != null) {
            sql += " and h.new_Menu_Id=" + menu_id;
        }
        if (orderByList != null && !orderByList.isEmpty()) {
            sql += " order by " + orderByList;
        }
        Query query = em.createNativeQuery(sql, HrProfilePrifilage.class);
        return query.getResultList();
    }


    public List<Object[]> findAllPrivilagesByEmpNo(Long grp_id, Long job_id, Long loc_id, Long dept_id, Long emp_no, Long menu_id) {
        String sql = "SELECT to_number(emp_no||menu_id) ID, emp_no, menu_id, arabic_name "+
                     "FROM (SELECT DISTINCT info.emp_no, m.ID menu_id, m.arabic_name "+
                     "FROM hr_menu_table m,hr_profile_prifilage p,hr_emp_info info "+
                     "WHERE m.ID = p.new_menu_id "+
                     "AND (p.dept_id = info.dept_id OR p.dept_id IS NULL) "+
                     "AND (p.loc_id = info.loc_id OR p.loc_id IS NULL) "+
                     "AND (p.job_id = info.job_id OR p.job_id IS NULL) "+
                     "AND (p.grp_id = info.job_grp_id OR p.grp_id IS NULL) "+
                     "AND (p.emp_id = info.emp_no OR p.emp_id IS NULL)";
                     if (grp_id != null) {
                        sql += " and p.grp_Id=" + grp_id;
                    }
                    if (loc_id != null) {
                        sql += " and p.loc_Id=" + loc_id;
                    }
                    if (job_id != null) {
                        sql += " and p.job_Id=" + job_id;
                    }
                    if (dept_id != null) {
                        sql += " and p.dept_Id=" + dept_id;
                    }
                    if (emp_no != null && emp_no != 0) {
                        sql += " and p.emp_Id=" + emp_no;
                    }
                    if (menu_id != null) {
                        sql += " and p.new_Menu_Id=" + menu_id;
                    }
                    sql += ") ";

        Query query = em.createNativeQuery(sql);
        return query.getResultList();
    }


    public List<HrLocationInvestSetting> findAllLocationInvestSetting(Long grp_id, Long job_id, Long loc_id, Long dept_id, Long emp_no, String orderByList) {
        String sql = "SELECT * FROM Hr_Location_Invest_Setting h "
                + " LEFT JOIN hr_job_grp g on(h.grp_id=g.id)   "
                + " LEFT JOIN hr_location l on(h.loc_id=l.id) "
                + " LEFT JOIN hr_jobs j on(h.job_id=j.id) "
                + " LEFT JOIN hr_management d on(h.dept_id=d.id) "
                + " LEFT JOIN hr_emp_info  e on(h.emp_id=e.emp_no)  "
                + " WHERE  ((h.emp_id is not null and e.emp_name is not null) or h.emp_id is null) ";
        if (grp_id != null) {
            sql += " and h.grp_Id=" + grp_id;
        }
        if (loc_id != null) {
            sql += " and h.loc_Id=" + loc_id;
        }
        if (job_id != null) {
            sql += " and h.job_Id=" + job_id;
        }
        if (dept_id != null) {
            sql += " and h.dept_Id=" + dept_id;
        }
        if (emp_no != null && emp_no != 0) {
            sql += " and h.emp_Id=" + emp_no;
        }
        
        if (orderByList != null && !orderByList.isEmpty()) {
            sql += " order by " + orderByList;
        }
        Query query = em.createNativeQuery(sql, HrProfilePrifilage.class);
        return query.getResultList();
    }

    public Long checkParentPrivilages(Long grp_id, Long job_id, Long loc_id, Long dept_id, Long emp_no, Long menu_id) {
        String sql = "SELECT count(*) FROM Hr_Profile_Prifilage h "
                + " LEFT JOIN hr_job_grp g on(h.grp_id=g.id)   "
                + " LEFT JOIN hr_location l on(h.loc_id=l.id) "
                + " LEFT JOIN hr_jobs j on(h.job_id=j.id) "
                + " LEFT JOIN hr_management d on(h.dept_id=d.id) "
                + " LEFT JOIN hr_emp_info  e on(h.emp_id=e.emp_no)  "
                + " JOIN hr_menu_table m on(h.new_menu_id=m.id) "
                + " where m.parent_id is null ";
        if (grp_id != null) {
            sql += " and h.grp_Id=" + grp_id;
        }
        if (loc_id != null) {
            sql += " and h.loc_Id=" + loc_id;
        }
        if (job_id != null) {
            sql += " and h.job_Id=" + job_id;
        }
        if (dept_id != null) {
            sql += " and h.dept_Id=" + dept_id;
        }
        if (emp_no != null && emp_no != 0) {
            sql += " and h.emp_Id=" + emp_no;
        }
        if (menu_id != null) {
            sql += " and h.new_Menu_Id=" + menu_id;
        }
        Query query = em.createNativeQuery(sql);
        return Long.parseLong(query.getSingleResult().toString());
    }


    public Long checkSettingsExistance(Long grp_id, Long job_id, Long loc_id, Long dept_id, Long emp_no) {
        String sql = "SELECT count(*) FROM HR_LOCATION_INVEST_SETTING h "
                + " LEFT JOIN hr_job_grp g on(h.grp_id=g.id)   "
                + " LEFT JOIN hr_location l on(h.loc_id=l.id) "
                + " LEFT JOIN hr_jobs j on(h.job_id=j.id) "
                + " LEFT JOIN hr_management d on(h.dept_id=d.id) "
                + " LEFT JOIN hr_emp_info  e on(h.emp_id=e.emp_no)  "
                + " where 1=1 ";
        if (grp_id != null) {
            sql += " and h.grp_Id=" + grp_id;
        }
        if (loc_id != null) {
            sql += " and h.loc_Id=" + loc_id;
        }
        if (job_id != null) {
            sql += " and h.job_Id=" + job_id;
        }
        if (dept_id != null) {
            sql += " and h.dept_Id=" + dept_id;
        }
        if (emp_no != null && emp_no != 0) {
            sql += " and h.emp_Id=" + emp_no;
        }
        
        Query query = em.createNativeQuery(sql);
        return Long.parseLong(query.getSingleResult().toString());
    }


    public void persistProfilePrivilage(HrProfilePrifilage hrProfilePrifilage) {
        em.persist(hrProfilePrifilage);
    }

    public Long findHrMenuTableNextId() {
        Query query = em.createNamedQuery("HrMenuTable.findMaxId");
        return (Long) query.getSingleResult();
    }

    public Long findHrProfilePrifilageNextId() {
        Query query = em.createNamedQuery("HrProfilePrifilage.findMaxId");
        return (Long) query.getSingleResult();
    }

    public Long findHrLocationInvestigationSettingsNextId() {
        Query query = em.createNamedQuery("HrLocationInvestSetting.findMaxId");
        return (Long) query.getSingleResult();
    }

    public void deleteProfilePrifilage(HrProfilePrifilage hrProfilePrifilage) {
        em.remove(em.merge(hrProfilePrifilage));
    }

    public void persistAdvanceRequest(HrAdvanceRequest hrAdvanceRequest) {
        em.persist(hrAdvanceRequest);
    }

    public void deleteHrLocationInvestSetting(HrLocationInvestSetting hrLocationInvestSetting){
        em.remove(em.merge(hrLocationInvestSetting));
    }

    public void persistHrLocationInvestigationSettings(HrLocationInvestSetting hrLocationInvestSetting){
        em.persist(hrLocationInvestSetting);
    }

    public void mergeAdvanceRequest(HrAdvanceRequest hrAdvanceRequest) {
        em.merge(hrAdvanceRequest);
    }

    public List<HrAdvanceRequest> findEmpAdvanceRequests(HrEmpInfo hrEmpInfo) {
        return em.createNamedQuery("HrAdvanceRequest.findByEmpNo").setParameter("empNo", hrEmpInfo).getResultList();
    }

    public List<HrAdvanceRequest> findAdvanceRequestNeedGuaranteeApprove(HrEmpInfo hrEmpInfo, Date fromDate) {
        return em.createNamedQuery("HrAdvanceRequest.findByGuaranteeNo").setParameter("guaranteeNo", hrEmpInfo).setParameter("from_date", fromDate).getResultList();
    }

    public List<HrAdvanceRequest> findAdvanceRequestNeedDeptMngApprove(HrEmpInfo hrEmpInfo, Date fromDate) {
        return em.createNamedQuery("HrAdvanceRequest.findByDeptMngNo").setParameter("from_date", fromDate).setParameter("deptMngNo", hrEmpInfo).getResultList();
    }

    public List<HrAdvanceRequest> findAdvanceRequestNeedRespApprove(Date fromDate) {
        return em.createNamedQuery("HrAdvanceRequest.findByRespNo").setParameter("from_date", fromDate).getResultList();
    }

    public List<Object[]> profilePrivilageReport(Long grp_id, Long job_id, Long loc_id, Long dept_id, Long emp_no, Long menu_id, String orderByList) {
        String sql = "SELECT DISTINCT info.emp_no,info.emp_name,info.job_grp_name,info.job_name,info.location,info.dept_name, m.ID, m.arabic_name"
                + " FROM hr_menu_table m,hr_profile_prifilage p,hr_emp_info info"
                + " WHERE m.ID = p.new_menu_id"
                + " AND (p.dept_id = info.dept_id OR p.dept_id IS NULL)"
                + " AND (p.loc_id = info.loc_id OR p.loc_id IS NULL)"
                + " AND (p.job_id = info.job_id OR p.job_id IS NULL)"
                + " AND (p.grp_id = info.job_grp_id OR p.grp_id IS NULL)"
                + " AND (p.emp_id = info.emp_no OR p.emp_id IS NULL)";
        if (grp_id != null) {
            sql += " and info.job_grp_Id=" + grp_id;
        }
        if (loc_id != null) {
            sql += " and info.loc_Id=" + loc_id;
        }
        if (job_id != null) {
            sql += " and info.job_Id=" + job_id;
        }
        if (dept_id != null) {
            sql += " and info.dept_Id=" + dept_id;
        }
        if (emp_no != null && emp_no != 0) {
            sql += " and info.emp_no=" + emp_no;
        }
        if (menu_id != null) {
            sql += " and p.new_Menu_Id=" + menu_id;
        }
        if (orderByList != null && !orderByList.isEmpty()) {
            sql += " order by " + orderByList;
        }
        Query query = em.createNativeQuery(sql);
        System.out.println("sql>>" + sql);
        return query.getResultList();
    }

    public List<Object[]> findMenuToConstruct(Long grp_id, Long job_id, Long loc_id, Long dept_id, Long emp_no, Long menu_id) {
        String sql = "SELECT DISTINCT m.arabic_name,m.id,m.parent_id,m.menu_order"
                + " FROM hr_menu_table m,hr_profile_prifilage p,hr_emp_info info"
                + " WHERE m.ID = p.new_menu_id"
                + " AND (p.dept_id = info.dept_id OR p.dept_id IS NULL)"
                + " AND (p.loc_id = info.loc_id OR p.loc_id IS NULL)"
                + " AND (p.job_id = info.job_id OR p.job_id IS NULL)"
                + " AND (p.grp_id = info.job_grp_id OR p.grp_id IS NULL)"
                + " AND (p.emp_id = info.emp_no OR p.emp_id IS NULL)";
        if (grp_id != null) {
            sql += " and info.job_grp_Id=" + grp_id;
        }
        if (loc_id != null) {
            sql += " and info.loc_Id=" + loc_id;
        }
        if (job_id != null) {
            sql += " and info.job_Id=" + job_id;
        }
        if (dept_id != null) {
            sql += " and info.dept_Id=" + dept_id;
        }
        if (emp_no != null && emp_no != 0) {
            sql += " and info.emp_no=" + emp_no;
        }
        if (menu_id != null) {
            sql += " and p.new_Menu_Id=" + menu_id;
        }
        sql += " order by m.MENU_ORDER";
        Query query = em.createNativeQuery(sql);
        System.out.println("sql>>" + sql);
        return query.getResultList();
    }

    public Long chkFundAdvanceRequest(Long empNo, Long advanceId, Long guarantee, Long amount) {
        Query q = em.createNativeQuery("select hr_chk_fund_advance(?,?,?,?) from dual");
        q.setParameter(1, empNo);
        q.setParameter(2, advanceId);
        q.setParameter(3, guarantee);
        q.setParameter(4, amount);
        return Long.parseLong(q.getSingleResult().toString());
    }

    public void persistAdvanceZamalaHd(HrAdvanceZamalaHd hrAdvanceZamalaHd) {
        em.persist(hrAdvanceZamalaHd);
    }

    public Long findSerialOfAdvanceZamalalaHd() {
        return Long.parseLong(em.createNativeQuery("select HR_ADVANCE_SERIAL_SEQ.nextval from dual").getSingleResult().toString());
    }

    public List<Object[]> findFundBorrowMonthlyBudget() {
        Query q = em.createNativeQuery("select nvl(used,0) used,nvl(used_cnt,0) used_cnt,budget,bud.trns_month,bud.trns_year,not_conf_sum,not_conf_cnt,nvl(res.rsp_sum,0) rsp_sum,nvl(res.rsp_cnt,0) rsp_cnt from"
                + " ("
                + " select sum(BORROW_AMOUNT) used,count(hd.id) used_cnt,to_number(to_char(start_date,'mm')) trns_month,to_number(to_char(start_date,'rrrr')) trns_year"
                + " from   hr_borrow_zamala_hd hd"
                + " where  nvl(canceled,'N')='N'"
                + //                                    " and    trunc(start_date,'MONTH')>=trunc(sysdate,'MONTH')"+
                " group by to_number(to_char(start_date,'mm')),to_number(to_char(start_date,'rrrr'))"
                + " ) bor,("
                + " select sum(REQ_AMOUNT) not_conf_sum,count(req.EMP_NO) not_conf_cnt,to_number(to_char(req_start,'mm')) trns_month,to_number(to_char(req_start,'rrrr')) trns_year"
                + " from hr_borrow_fund_request req,hr_borrow_zamala_hd hd"
                + " where req.id=hd.REQ_ID(+)"
                //                                    " and    trunc(req_start,'MONTH')>=trunc(sysdate,'MONTH')"+
                + " and   nvl(req.DEPT_MNG_CONFIRM,'N')='Y'"
                + " and   req.MNG_CONFIRM is null"
                + " and   nvl(req.CANCEL,'N')='N'"
                + " and   hd.id is null"
                + " group by to_number(to_char(req_start,'mm')) ,to_number(to_char(req_start,'rrrr'))"
                + " )not_confirmed,"
                + "("
                + "select sum(REQ_AMOUNT) rsp_sum,count(req.EMP_NO) rsp_cnt,to_number(to_char(req_start,'mm')) trns_month,to_number(to_char(req_start,'rrrr')) trns_year"
                + " from hr_borrow_fund_request req,hr_borrow_zamala_hd hd"
                + " where req.id=hd.REQ_ID(+)"
                //                +"--and    trunc(req_start,'MONTH')>=trunc(sysdate,'MONTH')"
                + " and   nvl(req.DEPT_MNG_CONFIRM,'N')='Y'"
                + " and   req.MNG_CONFIRM is null"
                + " and   req.RES_START is not null"
                + " and   nvl(req.CANCEL,'N')='N'"
                + " and   hd.id is null"
                + " group by to_number(to_char(req_start,'mm')) ,to_number(to_char(req_start,'rrrr'))"
                + ") res"
                + ",hr_fund_monthly_budgets bud"
                + " where bor.trns_month(+)=bud.TRNS_MONTH"
                + " and   bor.trns_year(+)=bud.trns_year"
                + " and   not_confirmed.trns_month(+)=bud.TRNS_MONTH"
                + " and   not_confirmed.trns_year(+)=bud.trns_year"
                + " and   res.trns_month(+)=bud.TRNS_MONTH"
                + " and   res.trns_year(+)=bud.trns_year"
                + //                                    " and   to_date(lpad(bud.trns_month,2,'0')||'-'||bud.trns_year,'mm-rrrr')>=trunc(sysdate,'MONTH')"+
                " and   bud.TYP=1"
                + " order by to_date(lpad(bud.trns_month,2,'0')||'-'||bud.trns_year,'mm-rrrr')");
        return q.getResultList();
    }

    public Object[] findFundAdvanceMonthlyBudget() {
        try {
            Query q = em.createNativeQuery("select budget,nvl(used,0) sum_used,nvl(emp_used,0) cnt_emp_used,nvl(sum_not_confirmed,0) sum_not_confirmed,nvl(cnt_not_confirmed,0) cnt_not_confirmed,bud.trns_month,bud.trns_year from"
                    + " (select sum(AMOUNT) used,count(hd.id)emp_used,dt.pay_month,dt.pay_year"
                    + " from   hr_advance_zamala_hd hd,hr_advance_zamala_dt dt"
                    + " where  hd.id=dt.hd_id"
                    + " and    dt.id=(select min(id) from hr_advance_zamala_dt where hd_id=dt.hd_id)"
                    + " and    nvl(canceled,'N')='N'"
                    + " and    to_date(lpad(dt.pay_month,2,'0')||'-'||dt.pay_year,'mm-rrrr')>=trunc(sysdate,'MONTH')"
                    + " group by dt.pay_month,dt.pay_year   "
                    + " ) confirmed,"
                    + "("
                    + " select sum(amount) sum_not_confirmed,count(req.id) cnt_not_confirmed,to_number(to_char(req.TRNS_DATE,'mm')) trns_month,to_number(to_char(req.TRNS_DATE,'rrrr')) trns_year"
                    + " from   hr_advance_request req,hr_advance_zamala_hd hd"
                    + " where  req.id=hd.REQ_ID(+)"
                    + " and    hd.id is null"
                    + " and    req.RESP_APPROVE is null"
                    + " and    nvl(req.CANCEL,'N')='N'"
                    + " and    nvl(req.DEPT_MNG_APPROVE,'N')='Y'"
                    + " and    trunc(req.trns_date,'MONTH')>=trunc(sysdate,'MONTH')"
                    + " group by to_number(to_char(req.TRNS_DATE,'mm')),to_number(to_char(req.TRNS_DATE,'rrrr'))"
                    + " )not_confirmed,"
                    + " hr_fund_monthly_budgets bud"
                    + " where confirmed.pay_month(+)=bud.TRNS_MONTH"
                    + " and   confirmed.pay_year(+)=bud.TRNS_YEAR"
                    + " and   not_confirmed.trns_month(+)=bud.TRNS_MONTH"
                    + " and   not_confirmed.trns_year(+)=bud.trns_year"
                    + " and    to_date(lpad(bud.trns_month,2,'0')||'-'||bud.trns_year,'mm-rrrr')=trunc(sysdate,'MONTH')"
                    + " and   bud.TYP=2");
            return (Object[]) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Object[] findBrnAndGovern(Long loc_id) {
        Query q = em.createNativeQuery("select distinct crmk_id,GOVERN_ID"
                + " from hr.hr_location loc,crmk.crmk_branch brn,crmk.crmk_brn_govern gov"
                + " where loc.crmk_id=brn.id"
                + " and   brn.id=gov.BRN_ID"
                + " and   nvl(default_gov,'N')='Y'"
                + " and   loc.id=" + loc_id);
        return (Object[]) q.getResultList().get(0);
    }

    public List<HrZamalaGiftSrfDt> findFundEmpGifts(HrEmpInfo hrEmpInfo) {
        return em.createNamedQuery("HrZamalaGiftSrfDt.findAll").setParameter("emp_id", hrEmpInfo).getResultList();
    }

    public List<HrAdvanceZamalaDt> findEmpAdvanceZamalaDt(HrEmpInfo empNo) {
        return em.createNamedQuery("HrAdvanceZamalaDt.findAll").setParameter("emp_no", empNo).getResultList();
    }

    public List<HrBorrowZamalaHd> findAllFundBorrowForGuarantee(HrEmpInfo emp_no) {
        return em.createNamedQuery("HrBorrowZamalaHd.findAllForGuarantee").setParameter("emp_no", emp_no).getResultList();
    }

    public List<HrAdvanceZamalaDt> findGuaranteeAdvanceZamalaDt(Long empNo) {
        return em.createNamedQuery("HrAdvanceZamalaDt.findAllForGuarantee").setParameter("emp_no", empNo).getResultList();
    }

    public HrUsers findHrUserByName(String userName) {
        try {
            return (HrUsers) em.createNamedQuery("HrUsers.findByHrUserName").setParameter("userName", userName).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public String getDercrybtedHrPassword(String pass) {
        Query q = em.createNativeQuery("select HR_CYPHER(?) from dual");
        q.setParameter(1, pass.toUpperCase());
        return (String) q.getSingleResult();
    }

    public void persistProfileAccessLog(HrProfileAccessLog hrProfileAccessLog) {
        em.persist(hrProfileAccessLog);
    }

    public Long chkLocInvest(Long empNo) {
        return (Long) em.createNamedQuery("HrEmpLocInvest.chkInvest").setParameter("emp_id", empNo).getSingleResult();
    }

     public void removeAllHrEmpLocInvest() {
        em.createNativeQuery("delete from HR_LOCATION_INVEST_SETTING").executeUpdate();
    }

    public void removeAllHrLocationInvestSetting() {
        em.createNativeQuery("delete from HR_EMP_LOC_INVEST").executeUpdate();
    }

    public List<HrArea> findAllArea() {
        return em.createNamedQuery("HrArea.findAll").getResultList();
    }

    public List<HrRegion> findAllRegion() {
        return em.createNamedQuery("HrRegion.findAll").getResultList();
    }

    public List<DmsTransportOrdrHd> findTrnsOrdrAdvanceList(Long manager_id) {
        return em.createNamedQuery("DmsTransportOrdrHd.findAll").setParameter("manager_id", manager_id).getResultList();
    }

    public void persistHrEmpInvestLoc(HrEmpLocInvest hrEmpLocInvest) {
        em.persist(hrEmpLocInvest);
    }

    public Long findHrEmpLocInvestId() {
        Long id = (Long) em.createNamedQuery("HrEmpLocInvest.findMaxId").getSingleResult();
        if (id == null) {
            return 0L;
        }
        return id;
    }

    public List<HrNewEmpExceed3monthDt> getNewEmpExceed3MonthDt(Long mng, Long dept, Long loc, Long grp, Long job) {
        return em.createNamedQuery("HrNewEmpExceed3monthDt.findAll").setParameter("mng", mng).setParameter("dept", dept).setParameter("loc", loc).setParameter("grp", grp).setParameter("job", job).getResultList();
    }

    public HrNewEmpExceed3Months findEmpExceed3MonthsById(Long id) {
        return (HrNewEmpExceed3Months) em.createNamedQuery("HrNewEmpExceed3Months.findById").setParameter("id", id).getSingleResult();
    }

    public void mergeHrNewEmpExceed3Months(HrNewEmpExceed3Months hrNewEmpExceed3Months) {
        em.merge(hrNewEmpExceed3Months);
    }

    public List<HrCheckupDutyHd1> findCheckUpDutyHdForEmp1(HrEmpInfo entryId, Date trnsDate) {
        return em.createNamedQuery("HrCheckupDutyHd1.findByEntryNo").setParameter("entryNo", entryId).setParameter("trnsDate", trnsDate).getResultList();
    }

    public List<Object[]> findCheckupDutyTitles(Long empNo) {
        Query query = em.createNativeQuery("SELECT dt.TITLE,dt.display_order"
                + " FROM HR_CHECKUP_DUTY_TITLE_HD hd,"
                + " HR_CHECKUP_DUTY_TITLE_DT dt,hr_emp_info info"
                + " WHERE hd.ID = dt.hd_id"
                + " and   (hd.DEPT_ID=info.DEPT_ID or hd.DEPT_ID is null)"
                + " and   (hd.job_id=info.job_id or hd.job_id is null)"
                + " and   (hd.emp_no=info.emp_no or hd.emp_no is null)"
                + " and   (hd.JOB_GRP_ID=info.JOB_GRP_ID or hd.JOB_GRP_ID is null)"
                + " and info.emp_no=?"
                + " order by display_order");
        query.setParameter(1, empNo);
        return query.getResultList();
    }

    public List<Object[]> findCheckupDutyTitles2(Long empNo) {
        Query query = em.createNativeQuery("SELECT dt.TITLE,dt.display_order"
                + " FROM HR_CHECKUP_DUTY_SETUP_HD hd,"
                + " HR_CHECKUP_DUTY_ITEMS dt"
                + " WHERE hd.ID = dt.hd_id"
                + " and hd.emp_no=?"
                + " order by display_order");
        query.setParameter(1, empNo);
        return query.getResultList();
    }

    public void mergeHrCheckUpDutyHd1(HrCheckupDutyHd1 hrCheckupDutyHd) {
        em.merge(hrCheckupDutyHd);
    }

    public void persistHrCheckUpDutyHd1(HrCheckupDutyHd1 hrCheckupDutyHd) {
        em.persist(hrCheckupDutyHd);
    }

    public void mergeHrCheckUpDutyDt1(HrCheckupDutyDt1 hrCheckupDutyDt) {
        em.merge(hrCheckupDutyDt);
    }

    public void persistHrCheckUpDutyDt1(HrCheckupDutyDt1 hrCheckupDutyDt) {
        em.persist(hrCheckupDutyDt);
    }

    public void removeHrCheckupDutyEmployees(HrCheckupDutyEmployees hrCheckupDutyEmployees) {
        em.remove(em.merge(hrCheckupDutyEmployees));
    }

    public List<HrCheckupDutyHd1> getCheckUpDutiesNotApproved1() {
        List<HrCheckupDutyHd1> l = em.createNamedQuery("HrCheckupDutyHd1.findNotApproved").getResultList();
        return l;
    }

    public void mergeHrCheckupDutyEmployees(HrCheckupDutyEmployees hrCheckupDutyEmployees) {
        em.merge(hrCheckupDutyEmployees);
    }

    public List<HrManNotesHd> findHrManNotes(Long empNo) {
        return em.createNamedQuery("HrManNotesHd.findByEmpNo").setParameter("empNo", empNo).getResultList();
    }

    public void persistHrManNotesHd(HrManNotesHd hrManNotesHd) {
        em.persist(hrManNotesHd);
    }

    public void mergeHrManNotesHd(HrManNotesHd hrManNotesHd) {
        em.merge(hrManNotesHd);
    }

    public List<Object[]> findManNotesTitles(Long empNo) {
        Query query = em.createNativeQuery("SELECT dt.TITLE,dt.display_order"
                + " FROM HR_MAN_NOTES_TITLES_HD hd,"
                + " HR_MAN_NOTES_TITLES_DT dt,hr_emp_info info"
                + " WHERE hd.ID = dt.hd_id"
                + " and   (hd.DEPT_ID=info.DEPT_ID or hd.DEPT_ID is null)"
                + " and   (hd.job_id=info.job_id or hd.job_id is null)"
                + " and   (hd.emp_no=info.emp_no or hd.emp_no is null)"
                + " and   (hd.JOB_GRP_ID=info.JOB_GRP_ID or hd.JOB_GRP_ID is null)"
                + " and info.emp_no=?"
                + " order by display_order");
        query.setParameter(1, empNo);
        return query.getResultList();
    }

    public List<HrHafezSehyDt> findHrHafezSehyDts(Long empNo) {
        return em.createNamedQuery("HrHafezSehyDt.findAll").setParameter("emp_no", empNo).getResultList();
    }

    public List<HrManNotesHd> findManNotesNotApproved() {
        return em.createNamedQuery("HrManNotesHd.findNotApproved").getResultList();
    }

    public void mergeHrManNotesDt(HrManNotesDt hrManNotesDt) {
        em.merge(hrManNotesDt);
    }

    public List<HrCheckupDutyHd1> findCheckupDutyForAddAction(HrEmpInfo emp, HrLocation loc, Long dept_id, Date fromDate, Date toDate, int approved) {

        System.out.println("approved>>>" + approved);
        System.out.println("toDate" + toDate);
        System.out.println("fromDate" + fromDate);
        System.out.println("deptId" + dept_id);
        return em.createNamedQuery("HrCheckupDutyHd1.searchForActions").setParameter("emp", emp).setParameter("loc", loc).setParameter("dept_id", dept_id).setParameter("approved", approved).setParameter("fromDate", fromDate).setParameter("toDate", toDate).getResultList();
    }

    public Object[] findBasicTaxData(String trnsMonth, String trnsYear, String empNo) {
        String q = "select sal.emp_no,tot_sal,nvl(plus_value,0) plus,nvl(tamyoz,0) tmyz,nvl(hafez,0) hfz,basic_sal,nvl(worker_pay,0) wrkr,nvl(tby,0) tby,nvl(emp_tby_val,0) emp_tby_val from "
                + " hr_sal_history sal, "
                + " (select emp_id,nvl(sum(final_amount),0) hafez from  hr_hafez_history where emp_id=" + empNo + " and MONTH_CALC=" + trnsMonth + " and TRNS_YEAR=" + trnsYear + " group by emp_id) hafez,"
                + " (select emp_id,sum(amount)badl from  hr_badl_history where emp_id=" + empNo + " and TRNS_MONTH=" + trnsMonth + " and  TRNS_YEAR=" + trnsYear + " group by emp_id) badl,"
                + " (select emp_id,sum(amount)tamyoz from  hr_tamyoz_history where emp_id=" + empNo + " and TRNS_MONTH=" + trnsMonth + " and TRNS_YEAR=" + trnsYear + " group by emp_id) tamyoz,"
                + " (select hd.emp_id,SUM(dt.amount) tby from "
                + " hr_cutoff_hd hd,hr_cutoff_dt dt ,hr_cutoff_type type "
                + " where hd.id=dt.hd_id "
                + " and hd.type_id=21 "
                + " and hd.type_id=type.id(+) "
                + " and hd.emp_id=" + empNo
                + " and dt.MONTH=" + trnsMonth
                + " and dt.YEAR=" + trnsYear
                + " GROUP BY hd.emp_id,dt.month,dt.year,hd.type_id)tby, "
                + " (select hd.emp_id,SUM(dt.amount) emp_tby_val from "
                + " hr_cutoff_hd hd,hr_cutoff_dt dt ,hr_cutoff_type type "
                + " where hd.id=dt.hd_id "
                + " and hd.type_id=20 "
                + " and hd.type_id=type.id(+) "
                + " and hd.emp_id=" + empNo
                + " and dt.MONTH=" + trnsMonth
                + " and dt.YEAR=" + trnsYear
                + " GROUP BY hd.emp_id,dt.month,dt.year,hd.type_id)emp_tby "
                + " where sal.TRNS_MONTH=" + trnsMonth
                + " and sal.TRNS_YEAR=" + trnsYear
                + " and sal.emp_no=hafez.emp_id(+) "
                + " and sal.emp_no=badl.emp_id(+) "
                + " and sal.emp_no=tamyoz.EMP_ID(+) "
                + " and sal.emp_no=tby.emp_id(+) "
                + " and sal.emp_no=" + empNo;
        Query query = em.createNativeQuery(q);
        return (Object[]) query.getSingleResult();
    }

    public DmsUsers findDmsUserByHrId(String empNo) {
        try {
            return (DmsUsers) em.createNamedQuery("DmsUsers.findByHrId").setParameter("empNo", empNo).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<HrInquestHd> getAllInquests() {
        return em.createNamedQuery("HrInquestHd.findAllForMng").getResultList();
    }

    public void mergeHrInquestDt(HrInquestDt hrInquestDt) {
        em.merge(hrInquestDt);
    }

    public void mergeHrInquestHd(HrInquestHd hrInquestHd) {
        em.merge(hrInquestHd);
    }

    public List<HrInquestHd> getAllMngApprovedInquests() {
        return em.createNamedQuery("HrInquestHd.findAllForHr").getResultList();
    }

   public HrLocationInvestSettingVu findEmpInLocationInvestSettingVu(Long empNo) {
        try {
            return (HrLocationInvestSettingVu) em.createNamedQuery("HrLocationInvestSettingVu.findAll").setParameter("empNo", empNo).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Object[]> getOvertimeToApprove(Date fromDate, Date toDate, Long mngNo, Date approvedFromDate) {
        Query query = em.createNativeQuery("select hd.emp_id,info.emp_name,info.LOCATION,dt.plus_minuts,dt.TRNS_DATE,to_char(dt.IN_TRNS,'HH24:MI') in_trns,to_char(dt.OUT_TRNS,'HH24:MI') out_trns,OVERTIME_APPROVE,dt.id dt_id "
                + " from hr_emp_mangers mngr,hr_manual_effection_hd hd,hr_manual_effection_dt dt,hr_emp_info info "
                + " where hd.id=dt.hd_id "
                + " and   hd.emp_id=info.emp_no "
                + " and   hd.emp_id=mngr.EMP_NO "
                + " and   nvl(info.extra_as_hours,'N')='N' "
                + " and   dt.IN_TRNS is not null "
                + " and   dt.OUT_TRNS is not null "
                + " and   ((dt.trns_date between ? and ? and nvl(dt.OVERTIME_APPROVE,'N')='N') or (nvl(dt.OVERTIME_APPROVE,'N')='Y' and trunc(dt.OVERTIME_APPROVE_DATE)>=trunc(?)))"
                + " and   dt.plus_minuts>=30 "
                + " and mng_no=" + mngNo
                + " and hd.emp_id!=" + mngNo
                + " order by info.location,info.emp_name,dt.trns_date");
        query.setParameter(1, fromDate);
        query.setParameter(2, toDate);
        query.setParameter(3, approvedFromDate);
        return query.getResultList();
    }

    public HrManualEffectionDt getManaEffectionDtById(Long id) {
        return (HrManualEffectionDt) em.createNamedQuery("HrManualEffectionDt.findById").setParameter("id", id).getSingleResult();
    }

    public void mergeHrManaualEffectionDt(HrManualEffectionDt manualEffectionDt) {
        em.merge(manualEffectionDt);
    }

    public List<CrmkActivatePreviousDocReq> getActivatePreviousDocReqs(HrEmpInfo empNo, Date trnsDate) {
        return em.createNamedQuery("CrmkActivatePreviousDocReq.findAll").setParameter("emp_no", empNo).setParameter("trns_date", trnsDate).getResultList();
    }

    public List<CrmkActivatePreviousDocVu> getActivatePreviousDocVus(Long docType, Long docBrnId, String crmkSehy, Long docNo, String docHandNo, Long docPrdId) {
        return em.createNamedQuery("CrmkActivatePreviousDocVu.findAll").setParameter("doc_type", docType).setParameter("doc_brn_id", docBrnId).setParameter("crmk_sehy", crmkSehy).setParameter("doc_no", docNo).setParameter("doc_hand_no", docHandNo).setParameter("doc_prd_id", docPrdId).getResultList();
    }

    public void saveCrmkActivatePreviousDocReq(CrmkActivatePreviousDocReq crmkActivatePreviousDocReq) {
        em.persist(crmkActivatePreviousDocReq);
    }

    public List<CrmkActivatePreviousDocReq> getPreviousDocReqToActivate(Long locId) {
        return em.createNamedQuery("CrmkActivatePreviousDocReq.findReqNeedActivate").setParameter("locId", locId).getResultList();
    }

    public void activatePreviousDoc(CrmkRsrvDt crmkRsrvDt) {
        em.remove(em.merge(crmkRsrvDt));
    }

    public CrmkRsrvDt getCrmkRsrvDt(Long rsrvId) {
        return (CrmkRsrvDt) em.createNamedQuery("CrmkRsrvDt.findById").setParameter("id", rsrvId).getSingleResult();
    }

    public void mergeCrmkActivatePreviousDocReq(CrmkActivatePreviousDocReq crmkActivatePreviousDocReq) {
        em.merge(crmkActivatePreviousDocReq);
    }

    public Integer chkCustomVote(Long empNo) {
        return Integer.valueOf(em.createNamedQuery("HrProfileCustomVote.chk").setParameter("emp_no", empNo).getSingleResult().toString());
    }

    public void saveProfileCustomVote(HrProfileCustomVote hrProfileCustomVote) {
        em.persist(hrProfileCustomVote);
    }

    public void saveHrProfileAlertRecivers(HrProfileAlertReceiver hrProfileAlertReceiver) {
        em.persist(hrProfileAlertReceiver);
    }

    public void deleteAllReciversForAlert(HrProfileAlertHd hrProfileAlertHd) {
        for (HrProfileAlertReceiver receiver : hrProfileAlertHd.getHrProfileAlertReceivers()) {
            em.remove(em.merge(receiver));
        }
    }

    public List<HrEmpInfo> findDriversList(Long jobId) {
        return em.createNamedQuery("HrEmpInfo.findByJobId").setParameter("jobId", jobId).getResultList();
    }

    public DmsDrivers findDriverById(Long driverId) {
        return (DmsDrivers) em.createNamedQuery("DmsDrivers.findById").setParameter("id", driverId).getSingleResult();
    }

    public List<HrActiveAlert> findActiveAlerts(Long empNo) {
        return em.createNamedQuery("HrActiveAlert.findByEmpNo").setParameter("empNo", empNo).getResultList();
    }

    public void deleteAlertReciver(HrProfileAlertReceiver hrProfileAlertReceiver) {
        em.remove(em.merge(hrProfileAlertReceiver));
    }

    public List<HrCheckupDutyHd2> findCheckUpDutyHdForEmp2(HrEmpInfo entryId, Date trnsDate) {
        return em.createNamedQuery("HrCheckupDutyHd2.findByEntryNo").setParameter("entryNo", entryId).setParameter("trnsDate", trnsDate).getResultList();
    }

    public void persistHrCheckUpDutyHd2(HrCheckupDutyHd2 hrCheckupDutyHd) {
        em.persist(hrCheckupDutyHd);
    }

    public void mergeHrCheckUpDutyHd2(HrCheckupDutyHd2 hrCheckupDutyHd) {
        em.merge(hrCheckupDutyHd);
    }

    public HrCheckupDutySetupHd findCheckupDutySetupHdByEmpNo(Long empNo) {
        try {
            return (HrCheckupDutySetupHd) em.createNamedQuery("HrCheckupDutySetupHd.findByEmpNo").setParameter("empNo", empNo).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<HrCheckupDutyHd2> getCheckUpDutiesNotApproved2() {
        List<HrCheckupDutyHd2> l = em.createNamedQuery("HrCheckupDutyHd2.findNotApproved").getResultList();
        return l;
    }

    public List<HrCheckupDutyLocations> getCheckUpDutyBonus(HrLocation loc, Long empNo, Long deptId) {
        List<HrCheckupDutyLocations> resultList = null;
        try {
            resultList = em.createNamedQuery("HrCheckupDutyLocations.findByLocId").setParameter("locId", loc).setParameter("empNo", empNo).getResultList();
            if (resultList.isEmpty()) {
                resultList = em.createNamedQuery("HrCheckupDutyLocations.findByLocIdAndDeptId").setParameter("locId", loc).setParameter("dept_id", deptId).getResultList();
            }
        } catch (NoResultException e) {
            return null;
        }
        return resultList;
    }

    public void mergeHrCheckupDutyEmp2(HrCheckupDutyEmp2 hrCheckupDutyEmp) {
        em.merge(hrCheckupDutyEmp);
    }

    public void mergeHrCheckUpDutyDt2(HrCheckupDutyDt2 hrCheckupDutyDt2) {
        em.merge(hrCheckupDutyDt2);
    }

    public CrmkBranch findShowroomByNo(Long no) {
        try {
            return (CrmkBranch) em.createNamedQuery("CrmkBranch.findShowByNo").setParameter("no", no).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<CrmkOrdrHd> findForOrdrPercentage(Long no, char crmk_sehy, Long brn_id, Date ordrDate, String clnt_name) {
        return em.createNamedQuery("CrmkOrdrHd.findForOrdrPercentage").setParameter("no", no).setParameter("crmk_sehy", crmk_sehy).setParameter("brn_id", brn_id).setParameter("ordrDate", ordrDate).setParameter("clnt_name", clnt_name).getResultList();
    }

    public List<CrmkEmpHstry> findAllCrmkEmployees() {
        return em.createNamedQuery("CrmkEmpHstry.findAll").getResultList();
    }

    public void deleteCrmkCOrderEmp(CrmkCOrdrEmp crmkCOrdrEmp) {
        em.remove(em.merge(crmkCOrdrEmp));
    }

    public void deleteCrmkDOrderEmp(CrmkDOrdrEmp crmkDOrdrEmp) {
        em.remove(em.merge(crmkDOrdrEmp));
    }

    public void deleteCrmkSOrderEmp(CrmkSOrdrEmp crmkSOrdrEmp) {
        em.remove(em.merge(crmkSOrdrEmp));
    }

    public void saveCrmkCOrderEmp(CrmkCOrdrEmp crmkCOrdrEmp) {
        em.persist(crmkCOrdrEmp);
    }

    public void saveCrmkDOrderEmp(CrmkDOrdrEmp crmkDOrdrEmp) {
        em.persist(crmkDOrdrEmp);
    }

    public void saveCrmkSOrderEmp(CrmkSOrdrEmp crmkSOrdrEmp) {
        em.persist(crmkSOrdrEmp);
    }

    public Long getCrmkOrderEmpId(String crmk_dcre, Long brnId) {
        Query query = em.createNativeQuery("SELECT CRMK.GET_CRMK_ORDR_EMP_ID(?,?) from dual");
        query.setParameter(1, crmk_dcre);
        query.setParameter(2, brnId);
        return Long.parseLong(query.getSingleResult().toString());
    }

    public void deleteCrmkOrdrEmp(Long ordrId, String crmkSehy) {
        Query q = em.createNativeQuery("call crmk.delete_crmk_ordr_emp(?,?)");
        q.setParameter(1, ordrId);
        q.setParameter(2, crmkSehy);
        q.executeUpdate();
    }

    public CrmkEmpHstry findCrmkEmployeeBtId(Long empId) {
        List<CrmkEmpHstry> list;
        list = em.createNamedQuery("CrmkEmpHstry.findByHdId").setParameter("emp_id", empId).getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public void saveCrmkOrdrEmpLog(CrmkOrdrEmpLog crmkOrdrEmpLog) {
        em.persist(crmkOrdrEmpLog);
    }

    public HrDynAlertTemplateHd findActiveAlert() {
        try {
            return (HrDynAlertTemplateHd) em.createNamedQuery("HrDynAlertTemplateHd.findAll").getResultList().get(0);
        } catch (Exception e) {
            return null;
        }
    }

    public void persistHrDynAlertTemplateHd(HrDynAlertTemplateHd hrDynAlertTemplateHd) {
        em.persist(hrDynAlertTemplateHd);
    }

    public void persistHrDynAlertTemplateDt(HrDynAlertTemplateDt hrDynAlertTemplateDt) {
        em.persist(hrDynAlertTemplateDt);
    }

    public List<HrDynAlertTemplateHd> findAlertList() {
        return em.createNamedQuery("HrDynAlertTemplateHd.findAll").getResultList();
    }

    public void removeAlertDT(HrDynAlertTemplateDt hrDynAlertTemplateDt) {
        em.remove(em.merge(hrDynAlertTemplateDt));
    }

    public void updateAlertHD(HrDynAlertTemplateHd hrDynAlertTemplateHd) {
        em.merge(hrDynAlertTemplateHd);
    }

    public List<HrDynAlertTemplateHd> findAlertsNotApplied(Long empNo) {
        return em.createNamedQuery("HrDynAlertTemplateHd.findNotApplied").setParameter("emp_no", empNo).getResultList();
    }

    public void persistHrDynAlertHd(HrDynAlertHd hrDynAlertHd) {
        em.persist(hrDynAlertHd);
    }

    public List<HrLocationIpMapping> findAllLOocationIpMapping() {
        return em.createNamedQuery("HrLocationIpMapping.findAll").getResultList();
    }

    public List<HrDutyTrnsHdVu> findDutyToApproveList(Long mng, Long dept, Long loc, Long grp, Long job) {
        return em.createNamedQuery("HrDutyTrnsHdVu.findNeedToApprove").setParameter("mngNo", mng).setParameter("dept", dept).setParameter("loc", loc).setParameter("grp", grp).setParameter("job", job).getResultList();
    }

    public void updateDutyDt(HrDutyTrnsDt hrDutyTrnsDt) {
        em.merge(hrDutyTrnsDt);
    }

    public List<HrDutyTrnsHd> findDutyToFollowUp(HrEmpInfo empNo, Date trnsDate) {
        return em.createNamedQuery("HrDutyTrnsHd.findAll").setParameter("empNo", empNo).setParameter("trnsDate", trnsDate).getResultList();
    }

    public HrEgadaBonus findEmpEgadaBonus(Long empNo, Long trnsMonth, Long trnsYear) {
        return (HrEgadaBonus) em.createNamedQuery("HrEgadaBonus.findByEmpNo").setParameter("trnsMonth", trnsMonth).setParameter("trnsYear", trnsYear).setParameter("empNo", empNo).getSingleResult();
    }

    public HrEgadaSetup findEgadaValue(Long jobGrpId) {
        try {
            return (HrEgadaSetup) em.createNamedQuery("HrEgadaSetup.findByJobGrpId").setParameter("jobGrpId", jobGrpId).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public HrMainTrgtLevelsHd checkMainTrgtEmp(HrEmpInfo hrEmpInfo) {
        try {
            return (HrMainTrgtLevelsHd) em.createNamedQuery("HrMainTrgtLevelsHd.findByEmpNo").setParameter("empNo", hrEmpInfo).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<HrMainTrgtLevelsHd> getMainTrgtLevelList(){
        return em.createNamedQuery("HrMainTrgtLevelsHd.findAll").getResultList();
    }

    public List<Object[]> getEmpActiveAlerts(Long dept_id,Long job_id,Long loc_id,Long job_grp_id,Long EMP_NO){
        Query query = em.createNativeQuery("SELECT hd.ID,alert_txt,bg_color "+
                                           "FROM hr_profile_alert_hd hd, "+
                                           "hr_profile_alert_receiver r "+
                                           "WHERE hd.ID = r.hd_id(+) "+
                                           "AND NVL (hd.active, 'N') = 'Y' "+
                                           "AND (r.dept_id=? or r.dept_id is null) "+
                                           "AND (r.job_id=? or r.job_id is null) "+
                                           "AND (r.loc_id=? or r.loc_id is null) "+
                                           "AND (r.job_grp_id=? or r.job_grp_id is null) "+
                                           "AND NOT EXISTS (SELECT 1 FROM hr_profile_alert_dt WHERE EMP_NO=? AND HD_ID=HD.ID)");
        query.setParameter(1, dept_id);
        query.setParameter(2, job_id);
        query.setParameter(3, loc_id);
        query.setParameter(4, job_grp_id);
        query.setParameter(5, EMP_NO);
        return query.getResultList();
    }

    public void saveHrMainTrgtLevelHd(HrMainTrgtLevelsHd hrMainTrgtLevelsHd){
        em.persist(hrMainTrgtLevelsHd);
    }

    public void saveHrMainTrgtLevelDt(HrMainTrgtLevelsDt hrMainTrgtLevelsDt){
        em.persist(hrMainTrgtLevelsDt);
    }

    public void removeHrMainTrgtLevelDt(HrMainTrgtLevelsDt hrMainTrgtLevelsDt){
        em.remove(em.merge(hrMainTrgtLevelsDt));
    }

    public void removeHrMainTrgtLevelHd(HrMainTrgtLevelsHd hrMainTrgtLevelsHd){
        em.remove(em.merge(hrMainTrgtLevelsHd));
    }

    public List<HrHafezSehyMngDt> getHafezSehyMng(Long empNo){
       return em.createNamedQuery("HrHafezSehyMngDt.findAll").setParameter("emp_no", empNo).getResultList();
    }

    public List<Object[]> getPoundHafezRules(){
        Query query = em.createNativeQuery("select PLIST.NO,PLIST_TYPE.NAME,INC.EMP_PERCENT,INC.TOTAL_PERCENT "+
                                          "from CRMK.INCENTIVE_SETTING_PLIST inc,CRMK.CRMK_PLIST_DEF plist,CRMK.CRMK_PLIST_TYPE plist_type "+
                                          "where inc.list_id=plist.id "+
                                          "and plist.type_id=plist_type.id "+
                                          "and nvl(INC.ACTIV,'N')='Y' ");
        return query.getResultList();
    }

    public List<HrPrvYearTransHolidays> getPreviousEmpHolidays(Long empNo){
        return em.createNamedQuery("HrPrvYearTransHolidays.findByEmpNo").setParameter("empNo", empNo).getResultList();
    }

    public void saveMosqueCrmkRequest(HrMosqueCrmkReq mosqueCrmkReq){
        em.persist(mosqueCrmkReq);
    }


    public List<HrMosqueCrmkReq> getMosqueCrmkReqs(){
        return em.createNamedQuery("HrMosqueCrmkReq.findAll").getResultList();
    }

    public void updateHrMosqueCrmkReq(HrMosqueCrmkReq hrMosqueCrmkReq){
        em.merge(hrMosqueCrmkReq);
    }

    public boolean chkEmpAttendance(Date trnsDate,Long empNo){
        Query query = em.createNativeQuery("select * from HR.HR_MACHINE_TIMESHEET "+
                                           "where trns_date=? "
                                           + "and emp_no=? ");

        query.setParameter(1, trnsDate);
        query.setParameter(2, empNo);
        if(query.getResultList()!=null && query.getResultList().size()>0)
            return true;

        return false;
    }

    public Long getMosqueReqSerialNo(){
        return (Long) em.createNamedQuery("HrMosqueCrmkReq.findMaxSerial").getSingleResult();
    }

    public List<CrmkCrmkType> getCrmkTypes(){
        return em.createNamedQuery("CrmkCrmkType.findAll").getResultList();
    }

    public List<CrmkDcreType> getDcreTypes(){
        return em.createNamedQuery("CrmkDcreType.findAll").getResultList();
    }

    public List<CrmkCrmkSize> getCrmkSizes(){
        return em.createNamedQuery("CrmkCrmkSize.findAll").getResultList();
    }

    public List<CrmkDcreSize> getDcreSizes(){
        return em.createNamedQuery("CrmkDcreSize.findAll").getResultList();
    }

    public List<CrmkDcreDekala> getDcreDekala(){
        return em.createNamedQuery("CrmkDcreDekala.findAll").getResultList();
    }

    public List<CrmkCrmkDekala> getCrmkDekala(){
        return em.createNamedQuery("CrmkCrmkDekala.findAll").getResultList();
    }

    public List<CrmkColor> getColors(){
        return em.createNamedQuery("CrmkColor.findAll").getResultList();
    }

    public List<ShowBathHd> getShowBaths(){
        return em.createNamedQuery("ShowBathHd.findAll").getResultList();
    }

    public Vote checkVote(Long empId){
        try{
            return (Vote) em.createNamedQuery("Vote.findByEmpId").setParameter("empId", empId).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void persistVote(Vote vote) {
        em.persist(vote);
    }

    public List<CrmkSehyGrp> findAllSehyGrp(){
        return em.createNamedQuery("CrmkSehyGrp.findAll").getResultList();
    }

    public HrProfilePrifilage findPrivilageById(Long id){
        try{
            return (HrProfilePrifilage) em.createNamedQuery("HrProfilePrifilage.findById").setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
                return null;
        }

    }

    public HrMenuTable findMenuById(Long menuId){
        try{
            return (HrMenuTable) em.createNamedQuery("HrMenuTable.findById").setParameter("id", menuId).getSingleResult();
      } catch (NoResultException e) {
                return null;
        }
    }

    public List<CrmkSehyName> getCrmkSehyNameList(){
        return em.createNamedQuery("CrmkSehyName.findAll").getResultList();
    }

    public List<CrmkSehyType> getCrmkSehyTypeList(){
        return em.createNamedQuery("CrmkSehyType.findAll").getResultList();
    }
}
