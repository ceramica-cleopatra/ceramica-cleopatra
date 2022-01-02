/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.BrnQtyTrgetVu;
import e.BrnQtyTrgetYearVu;
import e.CrmkCurrentBrnTrgt;
import e.EmpQtyTrgetVu;
import e.EmpQtyTrgetYearMv;
import e.EmpQtyTrgetYearMv;
import e.HrAllShowroomTrgt;
import e.HrEmpInfo;
import e.HrProfileAccessLog;
import e.HrShowroomTrgt;
import e.HrUsers;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.chart.PieChartModel;
import sb.SessionBeanLocal;

/**
 *
 * @author Administrator
 */
@ManagedBean
@SessionScoped
public class ShowroomTrgt {

    @EJB
    private SessionBeanLocal sessionBean;
    private HrEmpInfo hrEmpInfo;
    private BrnQtyTrgetVu hrShowroomTrgt = new BrnQtyTrgetVu();
    private Long rate;
    private Long order;
    private String chartLabels = "";
    private String chartTarget = "";
    private String chartSales = "";
    private String trgtMonth;
    private List<EmpQtyTrgetVu> hrTrgtVuList;
    private List<BrnQtyTrgetVu> hrShowroomTrgtList;
    private List<HrAllShowroomTrgt> hrAllShowroomTrgtList;
    private PieChartModel pie1;
    private PieChartModel pie2;
    private PieChartModel pie3;
    private Long empCount = 0L;
    private String selectedMonth;
    private Long brnId;
    private String previousMonth;
    private String currentTabId;
    private List<SelectItem> monthsList = new ArrayList<SelectItem>();
    private List<String> yearList;
    private String selectedYear;

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
        //  brnId=sessionBean.findCrmkShowRoomIdByLocId(hrEmpInfo.getLocId());
        Calendar calendar=Calendar.getInstance();
        BrnQtyTrgetYearVu currentTarget = sessionBean.findBrnQtyTrgetYearVu(hrEmpInfo.getLocId(),(((calendar.get(Calendar.MONTH)+1) + "").length() == 1 ? "0" + (calendar.get(Calendar.MONTH)+1) : (calendar.get(Calendar.MONTH)+1) + ""), calendar.get(Calendar.YEAR)+"");
        if (currentTarget==null) {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MONTH, -1);
            int currentMonth = (c.get(Calendar.MONTH) + 1);
            for (int i = currentMonth; i > 0; i--) {
                monthsList.add(new SelectItem(((i + "").length() == 1 ? "0" + i : i + ""), ((i + "").length() == 1 ? "0" + i : i + "")));
            }
            previousMonth = (String) monthsList.get(0).getValue();
            selectedMonth = (String) monthsList.get(0).getValue();
            yearList = sessionBean.findTrgtYearList();
            selectedYear = yearList.get(0);
            System.out.println(hrEmpInfo.getLocId() + "    " + previousMonth);
            BrnQtyTrgetYearVu brnQtyTrgetYearVu = sessionBean.findBrnQtyTrgetYearVu(hrEmpInfo.getLocId(), previousMonth, selectedYear);
            order = sessionBean.findBrnOrdrPerMonth(brnQtyTrgetYearVu.getTrgtPercent(), previousMonth, selectedYear) + 1;
            hrShowroomTrgt = new BrnQtyTrgetVu();
            hrShowroomTrgt.setBrnId(brnId);
            hrShowroomTrgt.setBrnTarget(brnQtyTrgetYearVu.getBrnTarget());
            hrShowroomTrgt.setNet(brnQtyTrgetYearVu.getNet());
            hrShowroomTrgt.setQty(brnQtyTrgetYearVu.getQty());
            hrShowroomTrgt.setNetC(brnQtyTrgetYearVu.getNetC());
            hrShowroomTrgt.setNetD(brnQtyTrgetYearVu.getNetD());
            hrShowroomTrgt.setNetS(brnQtyTrgetYearVu.getNetS());
            hrShowroomTrgt.setQtyC(brnQtyTrgetYearVu.getQtyC());
            hrShowroomTrgt.setQtyD(brnQtyTrgetYearVu.getQtyD());
            hrShowroomTrgt.setQtyS(brnQtyTrgetYearVu.getQtyS());
            hrShowroomTrgt.setPercent(brnQtyTrgetYearVu.getTrgtPercent());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            trgtMonth = "  «—Ã  ‘Â— " + previousMonth + " - " + selectedYear;
            rate = hrShowroomTrgt.getPercent() / 10;
            if (rate > 10L) {
                rate = 10L;
            }
        } else {
            hrShowroomTrgt = new BrnQtyTrgetVu();
            hrShowroomTrgt.setBrnId(brnId);
            hrShowroomTrgt.setBrnTarget(currentTarget.getBrnTarget());
            hrShowroomTrgt.setNet(currentTarget.getNet());
            hrShowroomTrgt.setQty(currentTarget.getQty());
            hrShowroomTrgt.setNetC(currentTarget.getNetC());
            hrShowroomTrgt.setNetD(currentTarget.getNetD());
            hrShowroomTrgt.setNetS(currentTarget.getNetS());
            hrShowroomTrgt.setQtyC(currentTarget.getQtyC());
            hrShowroomTrgt.setQtyD(currentTarget.getQtyD());
            hrShowroomTrgt.setQtyS(currentTarget.getQtyS());
            hrShowroomTrgt.setPercent(currentTarget.getTrgtPercent());
            rate = hrShowroomTrgt.getPercent() / 10;
            if (rate > 10L) {
                rate = 10L;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("MM - yyyy");
            trgtMonth = "  «—Ã  ‘Â— " + sdf.format(new Date());


            Calendar c = Calendar.getInstance();
            int currentMonth = (c.get(Calendar.MONTH) + 1);
            for (int i = currentMonth; i > 0; i--) {
                monthsList.add(new SelectItem(((i + "").length() == 1 ? "0" + i : i + ""), ((i + "").length() == 1 ? "0" + i : i + "")));
            }
            selectedMonth = monthsList.get(0).getLabel();
            yearList = sessionBean.findTrgtYearList();
            selectedYear = yearList.get(0);
            order = sessionBean.findBrnOrdrPerMonth(hrShowroomTrgt.getPercent(),selectedMonth,selectedYear) + 1;
        }

        pie1 = new PieChartModel();
        pie1.set("”Ì—«„Ìﬂ", hrShowroomTrgt.getNetC());
        pie1.set("œÌﬂÊ—", hrShowroomTrgt.getNetD());
        pie1.set("’ÕÏ", hrShowroomTrgt.getNetS());
        pie1.setTitle("‰”»… ﬁÌ„…  ”·Ì„«  «·„‰ Ã«  «·Ï ≈Ã„«·Ï «· ”·Ì„« ");
        pie1.setLegendPosition("e");
        pie1.setShowDataLabels(true);
        pie1.setDiameter(150);

        pie2 = new PieChartModel();
        Long t = hrShowroomTrgt.getBrnTarget() - hrShowroomTrgt.getNet();
        if (t < 0) {
            t = 0L;
        }
        pie2.set(" «—Ã ", t);
        pie2.set("„»Ì⁄« ", hrShowroomTrgt.getNet());
        pie2.setTitle("‰”»… ﬁÌ„… «· ”·Ì„«  «·Ï «· «—Ã ");
        pie2.setLegendPosition("e");
        pie2.setShowDataLabels(true);
        pie2.setDiameter(150);

        pie3 = new PieChartModel();
        pie3.set("”Ì—«„Ìﬂ", hrShowroomTrgt.getQtyC());
        pie3.set("œÌﬂÊ—", hrShowroomTrgt.getQtyD());
        pie3.set("’ÕÏ", hrShowroomTrgt.getQtyS());
        pie3.setTitle("‰”»… ﬂ„Ì… «· ”·Ì„«  «·Ï ≈Ã„«·Ï «·ﬂ„Ì…");
        pie3.setLegendPosition("e");
        pie3.setShowDataLabels(true);
        pie3.setDiameter(150);
        currentTabId = "target";
        //       createBarModel(hrEmpInfo.getLocId());
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
        if (currentTabId.equals("target")) {
            BrnQtyTrgetYearVu brnQtyTrgetYearVu = sessionBean.findBrnQtyTrgetYearVu(hrEmpInfo.getLocId(), selectedMonth, selectedYear);
            hrShowroomTrgt = new BrnQtyTrgetVu();
            hrShowroomTrgt.setBrnId(brnId);
            hrShowroomTrgt.setBrnTarget(brnQtyTrgetYearVu.getBrnTarget());
            hrShowroomTrgt.setNet(brnQtyTrgetYearVu.getNet());
            hrShowroomTrgt.setQty(brnQtyTrgetYearVu.getQty());
            hrShowroomTrgt.setNetC(brnQtyTrgetYearVu.getNetC());
            hrShowroomTrgt.setNetD(brnQtyTrgetYearVu.getNetD());
            hrShowroomTrgt.setNetS(brnQtyTrgetYearVu.getNetS());
            hrShowroomTrgt.setQtyC(brnQtyTrgetYearVu.getQtyC());
            hrShowroomTrgt.setQtyD(brnQtyTrgetYearVu.getQtyD());
            hrShowroomTrgt.setQtyS(brnQtyTrgetYearVu.getQtyS());
            hrShowroomTrgt.setPercent(brnQtyTrgetYearVu.getTrgtPercent());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            trgtMonth = "  «—Ã  ‘Â— " + selectedMonth + " - " + selectedYear;
            rate = hrShowroomTrgt.getPercent() / 10;
            if (rate > 10L) {
                rate = 10L;
            }
            order = sessionBean.findBrnOrdrPerMonth(hrShowroomTrgt.getPercent(), selectedMonth, selectedYear) + 1;

            pie1 = new PieChartModel();
            pie1.set("”Ì—«„Ìﬂ", hrShowroomTrgt.getNetC());
            pie1.set("œÌﬂÊ—", hrShowroomTrgt.getNetD());
            pie1.set("’ÕÏ", hrShowroomTrgt.getNetS());
            pie1.setTitle("‰”»… ﬁÌ„…  ”·Ì„«  «·„‰ Ã«  «·Ï ≈Ã„«·Ï «· ”·Ì„« ");
            pie1.setLegendPosition("e");
            pie1.setShowDataLabels(true);
            pie1.setDiameter(150);

            pie2 = new PieChartModel();
            Long t = hrShowroomTrgt.getBrnTarget() - hrShowroomTrgt.getNet();
            if (t < 0) {
                t = 0L;
            }
            pie2.set(" «—Ã ", t);
            pie2.set("„»Ì⁄« ", hrShowroomTrgt.getNet());
            pie2.setTitle("‰”»… ﬁÌ„… «· ”·Ì„«  «·Ï «· «—Ã ");
            pie2.setLegendPosition("e");
            pie2.setShowDataLabels(true);
            pie2.setDiameter(150);


            pie3 = new PieChartModel();
            pie3.set("”Ì—«„Ìﬂ", hrShowroomTrgt.getQtyC());
            pie3.set("œÌﬂÊ—", hrShowroomTrgt.getQtyD());
            pie3.set("’ÕÏ", hrShowroomTrgt.getQtyS());
            pie3.setTitle("‰”»… ﬂ„Ì… «· ”·Ì„«  «·Ï ≈Ã„«·Ï «·ﬂ„Ì…");
            pie3.setLegendPosition("e");
            pie3.setShowDataLabels(true);
            pie3.setDiameter(150);
            hrTrgtVuList = new ArrayList<EmpQtyTrgetVu>();
            hrShowroomTrgtList = new ArrayList<BrnQtyTrgetVu>();
        } else if (event.getTab().getId().equals("salesmenTarget")) {
            List<Object[]> empList = sessionBean.findEmpQtyTrgetYearVuListInPeriod(selectedMonth, selectedYear, hrEmpInfo.getLocId());
            hrTrgtVuList = new ArrayList<EmpQtyTrgetVu>();
            for (Object[] emp : empList) {
                EmpQtyTrgetVu empQtyTrgetVu = new EmpQtyTrgetVu();
                HrEmpInfo info = new HrEmpInfo();
                info.setEmpNo(Long.parseLong(emp[0].toString()));
                info.setEmpName((String) emp[2]);
                info.setLocId(Long.parseLong(emp[13].toString()));
                empQtyTrgetVu.setEmpNo(info);
                empQtyTrgetVu.setNet(Long.parseLong(emp[11].toString()));
                empQtyTrgetVu.setQty(Long.parseLong(emp[10].toString()));
                empQtyTrgetVu.setNetC(Long.parseLong(emp[4].toString()));
                empQtyTrgetVu.setNetD(Long.parseLong(emp[5].toString()));
                empQtyTrgetVu.setNetS(Long.parseLong(emp[6].toString()));
                empQtyTrgetVu.setQtyC(Long.parseLong(emp[7].toString()));
                empQtyTrgetVu.setQtyD(Long.parseLong(emp[8].toString()));
                empQtyTrgetVu.setQtyS(Long.parseLong(emp[9].toString()));
                empQtyTrgetVu.setTrgt(Long.parseLong(emp[3].toString()));
                empQtyTrgetVu.setTrgtPercent(Long.parseLong(emp[12].toString()));
                hrTrgtVuList.add(empQtyTrgetVu);
            }
            Double d1 = Double.valueOf(hrShowroomTrgt.getBrnTarget() + "");
            Double d2 = Double.valueOf(hrTrgtVuList.get(0).getTrgt() + "");
            empCount = Long.parseLong(Math.round(d1 / d2) + "");
            hrShowroomTrgtList = new ArrayList<BrnQtyTrgetVu>();

        } else if (event.getTab().getId().equals("showroomsPercentageOrder")) {
            List<BrnQtyTrgetYearVu> brnQtyTrgetYearVus = sessionBean.findAllBrnTrgtForPreviousMonth(selectedMonth, selectedYear);
            hrShowroomTrgtList = new ArrayList<BrnQtyTrgetVu>();
            for (BrnQtyTrgetYearVu brn : brnQtyTrgetYearVus) {
                BrnQtyTrgetVu brnQtyTrgetVu = new BrnQtyTrgetVu();
                brnQtyTrgetVu.setBrnTarget(brn.getBrnTarget());
                brnQtyTrgetVu.setNet(brn.getNet());
                brnQtyTrgetVu.setNetC(brn.getNetC());
                brnQtyTrgetVu.setNetD(brn.getNetD());
                brnQtyTrgetVu.setNetS(brn.getNetS());
                brnQtyTrgetVu.setQty(brn.getQty());
                brnQtyTrgetVu.setQtyC(brn.getQtyC());
                brnQtyTrgetVu.setQtyD(brn.getQtyD());
                brnQtyTrgetVu.setQtyS(brn.getQtyS());
                brnQtyTrgetVu.setLocId(brn.getLocId());
                hrShowroomTrgtList.add(brnQtyTrgetVu);
            }

            hrTrgtVuList = new ArrayList<EmpQtyTrgetVu>();
        }
    }

    public void onListChange(ActionEvent ae) {
        if (currentTabId.equals("target")) {
            BrnQtyTrgetYearVu brnQtyTrgetYearVu = sessionBean.findBrnQtyTrgetYearVu(hrEmpInfo.getLocId(), selectedMonth, selectedYear);
            hrShowroomTrgt = new BrnQtyTrgetVu();
            hrShowroomTrgt.setBrnId(brnId);
            hrShowroomTrgt.setBrnTarget(brnQtyTrgetYearVu.getBrnTarget());
            hrShowroomTrgt.setNet(brnQtyTrgetYearVu.getNet());
            hrShowroomTrgt.setQty(brnQtyTrgetYearVu.getQty());
            hrShowroomTrgt.setNetC(brnQtyTrgetYearVu.getNetC());
            hrShowroomTrgt.setNetD(brnQtyTrgetYearVu.getNetD());
            hrShowroomTrgt.setNetS(brnQtyTrgetYearVu.getNetS());
            hrShowroomTrgt.setQtyC(brnQtyTrgetYearVu.getQtyC());
            hrShowroomTrgt.setQtyD(brnQtyTrgetYearVu.getQtyD());
            hrShowroomTrgt.setQtyS(brnQtyTrgetYearVu.getQtyS());
            hrShowroomTrgt.setPercent(brnQtyTrgetYearVu.getTrgtPercent());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            trgtMonth = "  «—Ã  ‘Â— " + selectedMonth + " - " + selectedYear;
            rate = hrShowroomTrgt.getPercent() / 10;
            if (rate > 10L) {
                rate = 10L;
            }
            order = sessionBean.findBrnOrdrPerMonth(hrShowroomTrgt.getPercent(), selectedMonth, selectedYear) + 1;

            pie1 = new PieChartModel();
            pie1.set("”Ì—«„Ìﬂ", hrShowroomTrgt.getNetC());
            pie1.set("œÌﬂÊ—", hrShowroomTrgt.getNetD());
            pie1.set("’ÕÏ", hrShowroomTrgt.getNetS());
            pie1.setTitle("‰”»… ﬁÌ„…  ”·Ì„«  «·„‰ Ã«  «·Ï ≈Ã„«·Ï «· ”·Ì„« ");
            pie1.setLegendPosition("e");
            pie1.setShowDataLabels(true);
            pie1.setDiameter(150);

            pie2 = new PieChartModel();
            Long t = hrShowroomTrgt.getBrnTarget() - hrShowroomTrgt.getNet();
            if (t < 0) {
                t = 0L;
            }
            pie2.set(" «—Ã ", t);
            pie2.set("„»Ì⁄« ", hrShowroomTrgt.getNet());
            pie2.setTitle("‰”»… ﬁÌ„… «· ”·Ì„«  «·Ï «· «—Ã ");
            pie2.setLegendPosition("e");
            pie2.setShowDataLabels(true);
            pie2.setDiameter(150);


            pie3 = new PieChartModel();
            pie3.set("”Ì—«„Ìﬂ", hrShowroomTrgt.getQtyC());
            pie3.set("œÌﬂÊ—", hrShowroomTrgt.getQtyD());
            pie3.set("’ÕÏ", hrShowroomTrgt.getQtyS());
            pie3.setTitle("‰”»… ﬂ„Ì… «· ”·Ì„«  «·Ï ≈Ã„«·Ï «·ﬂ„Ì…");
            pie3.setLegendPosition("e");
            pie3.setShowDataLabels(true);
            pie3.setDiameter(150);
            hrTrgtVuList = new ArrayList<EmpQtyTrgetVu>();
            hrShowroomTrgtList = new ArrayList<BrnQtyTrgetVu>();

        } else if (currentTabId.equals("salesmenTarget")) {
            System.out.println(selectedMonth + " " + hrEmpInfo.getLocId());
            List<Object[]> empList = sessionBean.findEmpQtyTrgetYearVuListInPeriod(selectedMonth, selectedYear, hrEmpInfo.getLocId());
            hrTrgtVuList = new ArrayList<EmpQtyTrgetVu>();
            for (Object[] emp : empList) {
                EmpQtyTrgetVu empQtyTrgetVu = new EmpQtyTrgetVu();
                HrEmpInfo info = new HrEmpInfo();
                info.setEmpNo(Long.parseLong(emp[0].toString()));
                info.setEmpName((String) emp[2]);
                info.setLocId(Long.parseLong(emp[13].toString()));
                empQtyTrgetVu.setEmpNo(info);
                empQtyTrgetVu.setNet(Long.parseLong(emp[11].toString()));
                empQtyTrgetVu.setQty(Long.parseLong(emp[10].toString()));
                empQtyTrgetVu.setNetC(Long.parseLong(emp[4].toString()));
                empQtyTrgetVu.setNetD(Long.parseLong(emp[5].toString()));
                empQtyTrgetVu.setNetS(Long.parseLong(emp[6].toString()));
                empQtyTrgetVu.setQtyC(Long.parseLong(emp[7].toString()));
                empQtyTrgetVu.setQtyD(Long.parseLong(emp[8].toString()));
                empQtyTrgetVu.setQtyS(Long.parseLong(emp[9].toString()));
                empQtyTrgetVu.setTrgt(Long.parseLong(emp[3].toString()));
                empQtyTrgetVu.setTrgtPercent(Long.parseLong(emp[12].toString()));
                hrTrgtVuList.add(empQtyTrgetVu);
            }
            Double d1 = Double.valueOf(hrShowroomTrgt.getBrnTarget() + "");
            Double d2 = Double.valueOf(hrTrgtVuList.get(0).getTrgt() + "");
            empCount = Long.parseLong(Math.round(d1 / d2) + "");

            hrShowroomTrgtList = new ArrayList<BrnQtyTrgetVu>();

        } else if (currentTabId.equals("showroomsPercentageOrder")) {
            List<BrnQtyTrgetYearVu> brnQtyTrgetYearVus = sessionBean.findAllBrnTrgtForPreviousMonth(selectedMonth, selectedYear);
            hrShowroomTrgtList = new ArrayList<BrnQtyTrgetVu>();
            for (BrnQtyTrgetYearVu brn : brnQtyTrgetYearVus) {
                BrnQtyTrgetVu brnQtyTrgetVu = new BrnQtyTrgetVu();
                brnQtyTrgetVu.setBrnTarget(brn.getBrnTarget());
                brnQtyTrgetVu.setNet(brn.getNet());
                brnQtyTrgetVu.setNetC(brn.getNetC());
                brnQtyTrgetVu.setNetD(brn.getNetD());
                brnQtyTrgetVu.setNetS(brn.getNetS());
                brnQtyTrgetVu.setQty(brn.getQty());
                brnQtyTrgetVu.setQtyC(brn.getQtyC());
                brnQtyTrgetVu.setQtyD(brn.getQtyD());
                brnQtyTrgetVu.setQtyS(brn.getQtyS());
                brnQtyTrgetVu.setLocId(brn.getLocId());
                hrShowroomTrgtList.add(brnQtyTrgetVu);
            }


            hrTrgtVuList = new ArrayList<EmpQtyTrgetVu>();
        }

    }

    private void createBarModel(Long locId) {
        //hrAllShowroomTrgtList=sessionBean.findAllShowroomTrgt(locId);
        Collections.sort(hrAllShowroomTrgtList, new CustomDateComparator());
        for (HrAllShowroomTrgt hrAllShowroomTrgt : hrAllShowroomTrgtList.subList(hrAllShowroomTrgtList.size() - 13, hrAllShowroomTrgtList.size())) {
            chartLabels = chartLabels + hrAllShowroomTrgt.getMonths() + ",";
            chartSales = chartSales + hrAllShowroomTrgt.getAmount() + ",";
            chartTarget = chartTarget + hrAllShowroomTrgt.gettTarget() + ",";
        }
        chartLabels = chartLabels.substring(0, chartLabels.length() - 1);
        chartSales = chartSales.substring(0, chartSales.length() - 1);
        chartTarget = chartTarget.substring(0, chartTarget.length() - 1);
    }

    public ShowroomTrgt() {
    }

    public BrnQtyTrgetVu getHrShowroomTrgt() {
        return hrShowroomTrgt;
    }

    public void setHrShowroomTrgt(BrnQtyTrgetVu hrShowroomTrgt) {
        this.hrShowroomTrgt = hrShowroomTrgt;
    }

    public List<BrnQtyTrgetVu> getHrShowroomTrgtList() {
        return hrShowroomTrgtList;
    }

    public void setHrShowroomTrgtList(List<BrnQtyTrgetVu> hrShowroomTrgtList) {
        this.hrShowroomTrgtList = hrShowroomTrgtList;
    }

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    public String getChartLabels() {
        return chartLabels;
    }

    public void setChartLabels(String chartLabels) {
        this.chartLabels = chartLabels;
    }

    public String getChartSales() {
        return chartSales;
    }

    public void setChartSales(String chartSales) {
        this.chartSales = chartSales;
    }

    public String getChartTarget() {
        return chartTarget;
    }

    public void setChartTarget(String chartTarget) {
        this.chartTarget = chartTarget;
    }

    public List<EmpQtyTrgetVu> getHrTrgtVuList() {
        return hrTrgtVuList;
    }

    public void setHrTrgtVuList(List<EmpQtyTrgetVu> hrTrgtVuList) {
        this.hrTrgtVuList = hrTrgtVuList;
    }

    public List<HrAllShowroomTrgt> getHrAllShowroomTrgtList() {
        return hrAllShowroomTrgtList;
    }

    public void setHrAllShowroomTrgtList(List<HrAllShowroomTrgt> hrAllShowroomTrgtList) {
        this.hrAllShowroomTrgtList = hrAllShowroomTrgtList;
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

    public PieChartModel getPie3() {
        return pie3;
    }

    public void setPie3(PieChartModel pie3) {
        this.pie3 = pie3;
    }

    public Long getEmpCount() {
        return empCount;
    }

    public void setEmpCount(Long empCount) {
        this.empCount = empCount;
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

    public String getSelectedYear() {
        return selectedYear;
    }

    public void setSelectedYear(String selectedYear) {
        this.selectedYear = selectedYear;
    }

    public List<String> getYearList() {
        return yearList;
    }

    public void setYearList(List<String> yearList) {
        this.yearList = yearList;
    }
}
