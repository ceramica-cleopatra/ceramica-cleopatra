/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.EmpQtyTrgetVu;
import e.HrEmpInfo;
import e.HrProfileAccessLog;
import e.HrTamyozDt;
import e.HrTrgtHist;
import e.HrUsers;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.chart.PieChartModel;
import sb.SessionBeanLocal;

/**
 *
 * @author ahmed abbas
 */
@ManagedBean
@ViewScoped
public class target {

    @EJB
    private SessionBeanLocal sessionBean;
    private String usercode;
    private EmpQtyTrgetVu hrTrgtVu = new EmpQtyTrgetVu();
    private Double rate;
    private List<HrTamyozDt> hrTamyozDtList = null;
    private Long ORDR_PER_BRANCH;
    private Long ORDR_PER_COMPANY;
    private String trgtMonth;
    private PieChartModel pie1;
    private PieChartModel pie2;
    private PieChartModel pie3;
    private List<Object[]> heighestSalesPersons = null;
    private List<SelectItem> monthsList = new ArrayList<SelectItem>();
    private String selectedMonth;
    private HrEmpInfo hrEmpInfo;
    private List<Object[]> objList;
    private Calendar from_date;
    private Calendar to_date;
    private String currentTabId;
    private List<String> yearList;
    private String selectedYear;

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
        Calendar c = Calendar.getInstance();
        int currentMonth = (c.get(Calendar.MONTH))+1;
        selectedMonth = ((currentMonth + "").length() == 1 ? "0" + currentMonth : currentMonth + "");
        selectedYear = c.get(Calendar.YEAR)+"";
        hrTrgtVu = new EmpQtyTrgetVu();
        objList = sessionBean.findEmpTarget(hrEmpInfo, selectedMonth, selectedYear);
        yearList = sessionBean.findTrgtYearList();
        if(objList.isEmpty()){
            c.add(Calendar.MONTH, -1);
            currentMonth = (c.get(Calendar.MONTH) + 1);
            String previous_month = ((c.get(Calendar.MONTH) + 1) + "").length() == 1 ? "0" + (c.get(Calendar.MONTH) + 1) : (c.get(Calendar.MONTH) + 1) + "";
            for (int i = Integer.parseInt(previous_month); i > 0; i--) {
                monthsList.add(new SelectItem(((i + "").length() == 1 ? "0" + i : i + ""), ((i + "").length() == 1 ? "0" + i : i + "")));
            }
            selectedYear = yearList.get(0);
            selectedMonth = ((currentMonth + "").length() == 1 ? "0" + currentMonth : currentMonth + "");
            objList = sessionBean.findEmpTarget(hrEmpInfo, selectedMonth, selectedYear);
        }

        Object[] obj = objList.get(0);
        hrTrgtVu.setEmpNo(hrEmpInfo);
        hrTrgtVu.setNet(Long.parseLong(obj[1].toString()));
        hrTrgtVu.setNetC(Long.parseLong(obj[2].toString()));
        hrTrgtVu.setNetD(Long.parseLong(obj[3].toString()));
        hrTrgtVu.setNetS(Long.parseLong(obj[4].toString()));
        hrTrgtVu.setQtyC(Long.parseLong(obj[5].toString()));
        hrTrgtVu.setQtyD(Long.parseLong(obj[6].toString()));
        hrTrgtVu.setQtyS(Long.parseLong(obj[7].toString()));
        hrTrgtVu.setTrgt(Long.parseLong(obj[8].toString()));
        hrTrgtVu.setTrgtPercent(Long.parseLong(obj[9].toString()));
        hrTrgtVu.setQty(Long.parseLong(obj[10].toString()));


        rate = Double.valueOf(hrTrgtVu.getTrgtPercent() / 10);
        if (rate > 10D) {
            rate = 10D;
        }

        ORDR_PER_COMPANY = sessionBean.findSalesOrderByMonth(hrTrgtVu.getEmpNo().getEmpNo(), null, hrTrgtVu.getTrgtPercent(), selectedMonth, selectedYear) + 1;
        ORDR_PER_BRANCH = sessionBean.findSalesOrderByMonth(hrTrgtVu.getEmpNo().getEmpNo(), Long.parseLong(obj[0].toString()), hrTrgtVu.getTrgtPercent(), selectedMonth, selectedYear) + 1;
        SimpleDateFormat sdf = new SimpleDateFormat("MM - yyyy");
        trgtMonth = "  «—Ã  ‘Â— " + sdf.format(new Date());

        pie1 = new PieChartModel();
        pie1.set("”Ì—«„Ìﬂ", hrTrgtVu.getNetC());
        pie1.set("œÌﬂÊ—", hrTrgtVu.getNetD());
        pie1.set("’ÕÏ", hrTrgtVu.getNetS());
        pie1.setTitle("‰”»… ﬁÌ„…  ”·Ì„«  «·„‰ Ã«  «·Ï ≈Ã„«·Ï «· ”·Ì„« ");
        pie1.setLegendPosition("e");
        pie1.setShowDataLabels(true);
        pie1.setDiameter(150);

        pie2 = new PieChartModel();
        Long t = hrTrgtVu.getTrgt() - hrTrgtVu.getNet();
        if (t < 0) {
            t = 0L;
        }
        pie2.set(" «—Ã ", t);
        pie2.set("„»Ì⁄« ", hrTrgtVu.getNet());
        pie2.setTitle("‰”»… ﬁÌ„… «· ”·Ì„«  «·Ï «· «—Ã ");
        pie2.setLegendPosition("e");
        pie2.setShowDataLabels(true);
        pie2.setDiameter(150);

        pie3 = new PieChartModel();
        pie3.set("”Ì—«„Ìﬂ", hrTrgtVu.getQtyC());
        pie3.set("œÌﬂÊ—", hrTrgtVu.getQtyD());
        pie3.set("’ÕÏ", hrTrgtVu.getQtyS());
        pie3.setTitle("‰”»… ﬂ„Ì… «· ”·Ì„«  «·Ï ≈Ã„«·Ï «·ﬂ„Ì…");
        pie3.setLegendPosition("e");
        pie3.setShowDataLabels(true);
        pie3.setDiameter(150);
        currentTabId = "trgt";
    }

    public void onListChanged(ValueChangeEvent e) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        monthsList=new ArrayList<SelectItem>();
        if (e.getNewValue().equals(sdf.format(new Date()))) {
            Calendar c = Calendar.getInstance();
            int currentMonth = (c.get(Calendar.MONTH) + 1);
            for (int i = currentMonth; i > 0; i--) {
                monthsList.add(new SelectItem(((i + "").length() == 1 ? "0" + i : i + ""), ((i + "").length() == 1 ? "0" + i : i + "")));
            }
        }else{
           for (int i = 12; i > 0; i--) {
                monthsList.add(new SelectItem(((i + "").length() == 1 ? "0" + i : i + ""), ((i + "").length() == 1 ? "0" + i : i + "")));
            }
        }
    }

    public void onTabChange(TabChangeEvent event) {
        currentTabId = event.getTab().getId();
        if (event.getTab().getId().equals("sales") ) {
            heighestSalesPersons = sessionBean.findSalesmenWithHeigherTargetByMonths(selectedMonth, selectedYear,selectedMonth,selectedYear);
            hrTamyozDtList=new ArrayList<HrTamyozDt>();
        } else if (event.getTab().getId().equals("tmyoz") ) {
            from_date = Calendar.getInstance();
            to_date = Calendar.getInstance();
            from_date.set(Calendar.DAY_OF_MONTH, 1);
            to_date.add(Calendar.MONTH, 1);
            to_date.add(Calendar.DAY_OF_MONTH, -1);
            hrTamyozDtList = sessionBean.getPersonalyTamyoz(Long.parseLong(usercode), from_date.getTime(), to_date.getTime());
            heighestSalesPersons=new ArrayList<Object[]>();
            hrTrgtVu = new EmpQtyTrgetVu();
        } else if (currentTabId.equals("trgt")) {
            trgtMonth = "  «—Ã  ‘Â— " + selectedMonth + " ”‰… " + selectedYear;
            hrTrgtVu = new EmpQtyTrgetVu();
            Object[] obj = sessionBean.findEmpTarget(hrEmpInfo, selectedMonth, selectedYear).get(0);
            hrTrgtVu.setEmpNo(hrEmpInfo);
            hrTrgtVu.setNet(Long.parseLong(obj[1].toString()));
            hrTrgtVu.setNetC(Long.parseLong(obj[2].toString()));
            hrTrgtVu.setNetD(Long.parseLong(obj[3].toString()));
            hrTrgtVu.setNetS(Long.parseLong(obj[4].toString()));
            hrTrgtVu.setQtyC(Long.parseLong(obj[5].toString()));
            hrTrgtVu.setQtyD(Long.parseLong(obj[6].toString()));
            hrTrgtVu.setQtyS(Long.parseLong(obj[7].toString()));
            hrTrgtVu.setTrgt(Long.parseLong(obj[8].toString()));
            hrTrgtVu.setTrgtPercent(Long.parseLong(obj[9].toString()));
            hrTrgtVu.setQty(Long.parseLong(obj[10].toString()));
            rate = Double.valueOf(hrTrgtVu.getTrgtPercent() / 10);
            if (rate > 10D) {
                rate = 10D;
            }
            rate = Double.valueOf(hrTrgtVu.getTrgtPercent() / 10);
            if (rate > 10D) {
                rate = 10D;
            }
            ORDR_PER_BRANCH = sessionBean.findSalesOrderByMonth(hrTrgtVu.getEmpNo().getEmpNo(), Long.parseLong(obj[0].toString()), hrTrgtVu.getTrgtPercent(), selectedMonth, selectedYear) + 1;
            ORDR_PER_COMPANY = sessionBean.findSalesOrderByMonth(hrTrgtVu.getEmpNo().getEmpNo(), null, hrTrgtVu.getTrgtPercent(), selectedMonth, selectedYear) + 1;
            pie1 = new PieChartModel();
            pie1.set("”Ì—«„Ìﬂ", hrTrgtVu.getNetC());
            pie1.set("œÌﬂÊ—", hrTrgtVu.getNetD());
            pie1.set("’ÕÏ", hrTrgtVu.getNetS());
            pie1.setTitle("‰”»… ﬁÌ„…  ”·Ì„«  «·„‰ Ã«  «·Ï ≈Ã„«·Ï «· ”·Ì„« ");
            pie1.setLegendPosition("e");
            pie1.setShowDataLabels(true);
            pie1.setDiameter(150);

            pie2 = new PieChartModel();
            Long t = hrTrgtVu.getTrgt() - hrTrgtVu.getNet();
            if (t < 0) {
                t = 0L;
            }
            pie2.set(" «—Ã ", t);
            pie2.set("„»Ì⁄« ", hrTrgtVu.getNet());
            pie2.setTitle("‰”»… ﬁÌ„… «· ”·Ì„«  «·Ï «· «—Ã ");
            pie2.setLegendPosition("e");
            pie2.setShowDataLabels(true);
            pie2.setDiameter(150);
            heighestSalesPersons=new ArrayList<Object[]>();
            hrTamyozDtList=new ArrayList<HrTamyozDt>();
        }
    }

    public void search(ActionEvent ae) {
        if (currentTabId.equals("sales")) {
            heighestSalesPersons = sessionBean.findSalesmenWithHeigherTargetByMonths(selectedMonth, selectedYear,selectedMonth,selectedYear);
        } else if (currentTabId.equals("trgt")) {
            trgtMonth = "  «—Ã  ‘Â— " + selectedMonth + " ”‰… " + selectedYear;
            hrTrgtVu = new EmpQtyTrgetVu();
            Object[] obj = sessionBean.findEmpTarget(hrEmpInfo, selectedMonth, selectedYear).get(0);
            hrTrgtVu.setEmpNo(hrEmpInfo);
            hrTrgtVu.setNet(Long.parseLong(obj[1].toString()));
            hrTrgtVu.setNetC(Long.parseLong(obj[2].toString()));
            hrTrgtVu.setNetD(Long.parseLong(obj[3].toString()));
            hrTrgtVu.setNetS(Long.parseLong(obj[4].toString()));
            hrTrgtVu.setQtyC(Long.parseLong(obj[5].toString()));
            hrTrgtVu.setQtyD(Long.parseLong(obj[6].toString()));
            hrTrgtVu.setQtyS(Long.parseLong(obj[7].toString()));
            hrTrgtVu.setTrgt(Long.parseLong(obj[8].toString()));
            hrTrgtVu.setTrgtPercent(Long.parseLong(obj[9].toString()));
            hrTrgtVu.setQty(Long.parseLong(obj[10].toString()));
            rate = Double.valueOf(hrTrgtVu.getTrgtPercent() / 10);
            if (rate > 10D) {
                rate = 10D;
            }
            rate = Double.valueOf(hrTrgtVu.getTrgtPercent() / 10);
            if (rate > 10D) {
                rate = 10D;
            }
            ORDR_PER_BRANCH = sessionBean.findSalesOrderByMonth(hrTrgtVu.getEmpNo().getEmpNo(), Long.parseLong(obj[0].toString()), hrTrgtVu.getTrgtPercent(), selectedMonth, selectedYear) + 1;
            ORDR_PER_COMPANY = sessionBean.findSalesOrderByMonth(hrTrgtVu.getEmpNo().getEmpNo(), null, hrTrgtVu.getTrgtPercent(), selectedMonth, selectedYear) + 1;

            pie1 = new PieChartModel();
            pie1.set("”Ì—«„Ìﬂ", hrTrgtVu.getNetC());
            pie1.set("œÌﬂÊ—", hrTrgtVu.getNetD());
            pie1.set("’ÕÏ", hrTrgtVu.getNetS());
            pie1.setTitle("‰”»… ﬁÌ„…  ”·Ì„«  «·„‰ Ã«  «·Ï ≈Ã„«·Ï «· ”·Ì„« ");
            pie1.setLegendPosition("e");
            pie1.setShowDataLabels(true);
            pie1.setDiameter(150);

            pie2 = new PieChartModel();
            Long t = hrTrgtVu.getTrgt() - hrTrgtVu.getNet();
            if (t < 0) {
                t = 0L;
            }
            pie2.set(" «—Ã ", t);
            pie2.set("„»Ì⁄« ", hrTrgtVu.getNet());
            pie2.setTitle("‰”»… ﬁÌ„… «· ”·Ì„«  «·Ï «· «—Ã ");
            pie2.setLegendPosition("e");
            pie2.setShowDataLabels(true);
            pie2.setDiameter(150);
            heighestSalesPersons=new ArrayList<Object[]>();
        }
    }

    public List<HrTamyozDt> getHrTamyozDtList() {
        return hrTamyozDtList;
    }

    public void setHrTamyozDtList(List<HrTamyozDt> hrTamyozDtList) {
        this.hrTamyozDtList = hrTamyozDtList;
    }

    public Long getORDR_PER_BRANCH() {
        return ORDR_PER_BRANCH;
    }

    public void setORDR_PER_BRANCH(Long ORDR_PER_BRANCH) {
        this.ORDR_PER_BRANCH = ORDR_PER_BRANCH;
    }

    public Long getORDR_PER_COMPANY() {
        return ORDR_PER_COMPANY;
    }

    public void setORDR_PER_COMPANY(Long ORDR_PER_COMPANY) {
        this.ORDR_PER_COMPANY = ORDR_PER_COMPANY;
    }

    public EmpQtyTrgetVu getHrTrgtVu() {
        return hrTrgtVu;
    }

    public void setHrTrgtVu(EmpQtyTrgetVu hrTrgtVu) {
        this.hrTrgtVu = hrTrgtVu;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getTrgtMonth() {
        return trgtMonth;
    }

    public void setTrgtMonth(String trgtMonth) {
        this.trgtMonth = trgtMonth;
    }

    public PieChartModel getPie1() {
        return pie1;
    }

    public void setPie1(PieChartModel pie1) {
        this.pie1 = pie1;
    }

    public PieChartModel getPie2() {
        return pie2;
    }

    public void setPie2(PieChartModel pie2) {
        this.pie2 = pie2;
    }

    public List<Object[]> getHeighestSalesPersons() {
        return heighestSalesPersons;
    }

    public void setHeighestSalesPersons(List<Object[]> heighestSalesPersons) {
        this.heighestSalesPersons = heighestSalesPersons;
    }

//    public void onTabChange(TabChangeEvent event) {
//        if (event.getTab().getTitle().equals("√Ê«∆· «·„»Ì⁄« ")) {
//            heighestSalesPersons = sessionBean.findSalesmenWithHeigherTargetByMonths(selectedMonth);
//        } else if (event.getTab().getTitle().equals("«· „Ì“ «·›—œÏ") && heighestSalesPersons.isEmpty()) {
//            Calendar from_date = Calendar.getInstance();
//            from_date.set(Calendar.DAY_OF_MONTH, 1);
//            Calendar to_date = Calendar.getInstance();
//            to_date.add(Calendar.MONTH, 1);
//            to_date.set(Calendar.DAY_OF_MONTH, -1);
//            hrTamyozDtList = sessionBean.getPersonalyTamyoz(Long.parseLong(usercode), from_date.getTime(), to_date.getTime());
//        }
//    }
//    public List<SelectItem> getBranchSelectList() {
//        return branchSelectList;
//    }
//
//    public void setBranchSelectList(List<SelectItem> branchSelectList) {
//        this.branchSelectList = branchSelectList;
//    }
//    public Long getSelectedBrn() {
//        return selectedBrn;
//    }
//
//    public void setSelectedBrn(Long selectedBrn) {
//        this.selectedBrn = selectedBrn;
//    }
    public PieChartModel getPie3() {
        return pie3;
    }

    public void setPie3(PieChartModel pie3) {
        this.pie3 = pie3;
    }

    public List<SelectItem> getMonthsList() {
        return monthsList;
    }

    public void setMonthsList(List<SelectItem> monthsList) {
        this.monthsList = monthsList;
    }

    public String getSelectedMonth() {
        return selectedMonth;
    }

    public void setSelectedMonth(String selectedMonth) {
        this.selectedMonth = selectedMonth;
    }

    public List<String> getYearList() {
        return yearList;
    }

    public void setYearList(List<String> yearList) {
        this.yearList = yearList;
    }

    public String getSelectedYear() {
        return selectedYear;
    }

    public void setSelectedYear(String selectedYear) {
        this.selectedYear = selectedYear;
    }

    /** Creates a new instance of target */
    public target() {
    }
}
