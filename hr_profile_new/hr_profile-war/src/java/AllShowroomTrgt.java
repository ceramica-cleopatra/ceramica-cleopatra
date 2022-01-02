/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.BrnQtyTrgetVu;
import e.BrnQtyTrgetYearVu;
import e.CrmkBranch;
import e.EmpQtyTrgetVu;
import e.EmpQtyTrgetYearMv;
import e.HrAllShowroomTrgt;
import e.HrEmpInfo;
import e.HrLocation;
import e.HrMainTrgtLevelsDt;
import e.HrMainTrgtLevelsHd;
import e.HrProfileAccessLog;
import e.HrShowroomTrgt;
import e.HrUsers;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.model.chart.PieChartModel;
import sb.SessionBeanLocal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author Administrator
 */
@ManagedBean
@SessionScoped
public class AllShowroomTrgt implements Serializable {

    @EJB
    private SessionBeanLocal sessionBean;
    private List<SelectItem> showroomList;
    private BrnQtyTrgetVu hrShowroomTrgt = new BrnQtyTrgetVu();
    private BrnQtyTrgetVu totShowroomTrgt = new BrnQtyTrgetVu();
    private Long rate;
    private Long totalRate;
    private Long order;
    private String chartLabels = "";
    private String chartTarget = "";
    private String chartSales = "";
    private List<EmpQtyTrgetVu> hrTrgtVuList;
    private List<BrnQtyTrgetVu> hrShowroomTrgtList;
    private List<HrAllShowroomTrgt> hrAllShowroomTrgtList;
    private Long locId;
    private String trgtMonth;
    private PieChartModel pie1;
    private PieChartModel pie2;
    private PieChartModel pie3;
    private PieChartModel pie4;
    private PieChartModel pie5;
    private PieChartModel pie6;
    private List<Object[]> heighestSalesPersons;
    private Long empCount = 0L;
    private Long empTrgt = 0L;
    private String selectedFromMonth;
    private String selectedFromYear;
    private String selectedToYear;
    private String selectedToMonth;
    private List<SelectItem> monthsFromList = new ArrayList<SelectItem>();
    private List<SelectItem> monthsToList = new ArrayList<SelectItem>();
    private SimpleDateFormat sdf = new SimpleDateFormat("MM - yyyy");
    private List<String> yearFromList;
    private List<String> yearToList;
    private boolean notRestricted = true;

    @PostConstruct
    public void init() {
        System.setProperty("java.awt.headless", "true");
        HrEmpInfo hrEmpInfo = (HrEmpInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrEmpInfo");
        HrUsers hrUsers = (HrUsers) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrUsers");
        if (hrUsers != null) {
            HrProfileAccessLog hrProfileAccessLog = new HrProfileAccessLog();
            hrProfileAccessLog.setEmpNo(hrEmpInfo.getEmpNo());
            hrProfileAccessLog.setPageName(FacesContext.getCurrentInstance().getViewRoot().getViewId());
            hrProfileAccessLog.setTrnsDate(new Date());
            hrProfileAccessLog.setUserName(hrUsers.getUserName());
            sessionBean.persistProfileAccessLog(hrProfileAccessLog);
        }
        showroomList = new ArrayList<SelectItem>();
        List<Object[]> branches = sessionBean.findShowroomsPerCrmkName();
        HrMainTrgtLevelsHd hrMainTrgtLevelsHd = sessionBean.checkMainTrgtEmp(hrEmpInfo);
        if (hrMainTrgtLevelsHd == null) {
            for (Object[] branch : branches) {
                showroomList.add(new SelectItem(branch[0].toString(), branch[1].toString()));
            }
        } else {
            notRestricted = false;
            for (Object[] branch : branches) {
                for (HrMainTrgtLevelsDt hrMainTrgtLevelsDt : hrMainTrgtLevelsHd.getHrMainTrgtLevelsDtList()) {
                    if(hrMainTrgtLevelsDt.getBrnId().getId().toString().equals(branch[0].toString())){
                        showroomList.add(new SelectItem(branch[0].toString(), branch[1].toString()));
                        break;
                    }
                }
            } 
        }
        locId = Long.parseLong(showroomList.get(0).getValue().toString());
        pie1 = new PieChartModel();
        pie2 = new PieChartModel();
        pie3 = new PieChartModel();
        pie4 = new PieChartModel();
        Calendar calendar = Calendar.getInstance();
        BrnQtyTrgetYearVu currentTarget = sessionBean.findBrnQtyTrgetYearVu(locId, (((calendar.get(Calendar.MONTH) + 1) + "").length() == 1 ? "0" + (calendar.get(Calendar.MONTH) + 1) : (calendar.get(Calendar.MONTH) + 1) + ""), calendar.get(Calendar.YEAR) + "");
        if (currentTarget == null) {
            System.out.println("currentTarget==null");
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MONTH, -1);
            String previous_month = ((c.get(Calendar.MONTH) + 1) + "").length() == 1 ? "0" + (c.get(Calendar.MONTH) + 1) : (c.get(Calendar.MONTH) + 1) + "";
            for (int i = Integer.parseInt(previous_month); i > 0; i--) {
                monthsFromList.add(new SelectItem(((i + "").length() == 1 ? "0" + i : i + ""), ((i + "").length() == 1 ? "0" + i : i + "")));
                monthsToList.add(new SelectItem(((i + "").length() == 1 ? "0" + i : i + ""), ((i + "").length() == 1 ? "0" + i : i + "")));
            }
            selectedFromMonth = selectedToMonth = monthsFromList.get(0).getLabel();
            yearFromList = sessionBean.findTrgtYearList();
            yearToList = sessionBean.findTrgtYearList();
            selectedFromYear = selectedToYear = yearFromList.get(0);

            List<Object[]> empList = sessionBean.findEmpQtyTrgetYearVuListInPeriod(previous_month, selectedToYear, previous_month, selectedToYear, locId);
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

            List<Object[]> brnTargetList = sessionBean.findBrnQtyTrgetYearVuInPeriod(previous_month, locId);
            order = 0L;
            Object[] o = null;
            hrShowroomTrgtList = new ArrayList<BrnQtyTrgetVu>();
            for (Object[] obj : brnTargetList) {
                if (obj[12].toString().equals(locId.toString())) {
                    o = obj;
                    order++;
                }
                if (o == null) {
                    order++;
                }
                BrnQtyTrgetVu brnQtyTrgetVu = new BrnQtyTrgetVu();
                brnQtyTrgetVu.setBrnTarget(Long.parseLong(obj[1].toString()));
                brnQtyTrgetVu.setNet(Long.parseLong(obj[9].toString()));
                brnQtyTrgetVu.setNetC(Long.parseLong(obj[2].toString()));
                brnQtyTrgetVu.setNetD(Long.parseLong(obj[3].toString()));
                brnQtyTrgetVu.setNetS(Long.parseLong(obj[4].toString()));
                brnQtyTrgetVu.setQty(Long.parseLong(obj[8].toString()));
                brnQtyTrgetVu.setQtyC(Long.parseLong(obj[5].toString()));
                brnQtyTrgetVu.setQtyD(Long.parseLong(obj[6].toString()));
                brnQtyTrgetVu.setQtyS(Long.parseLong(obj[7].toString()));
                brnQtyTrgetVu.setPercent(Long.parseLong(obj[10].toString()));
                HrLocation hrLocation = new HrLocation();
                hrLocation.setId(Long.parseLong(obj[12].toString()));
                hrLocation.setName(obj[11].toString());
                brnQtyTrgetVu.setLocId(hrLocation);
                hrShowroomTrgtList.add(brnQtyTrgetVu);
            }

            hrShowroomTrgt = new BrnQtyTrgetVu();
            hrShowroomTrgt.setBrnId(Long.parseLong(o[0].toString()));
            hrShowroomTrgt.setBrnTarget(Long.parseLong(o[1].toString()));
            hrShowroomTrgt.setNetC(Long.parseLong(o[2].toString()));
            hrShowroomTrgt.setNetD(Long.parseLong(o[3].toString()));
            hrShowroomTrgt.setNetS(Long.parseLong(o[4].toString()));
            hrShowroomTrgt.setQtyC(Long.parseLong(o[5].toString()));
            hrShowroomTrgt.setQtyD(Long.parseLong(o[6].toString()));
            hrShowroomTrgt.setQtyS(Long.parseLong(o[7].toString()));
            hrShowroomTrgt.setQty(Long.parseLong(o[8].toString()));
            hrShowroomTrgt.setNet(Long.parseLong(o[9].toString()));
            hrShowroomTrgt.setPercent(Long.parseLong(o[10].toString()));

            rate = hrShowroomTrgt.getPercent() / 10;
            if (rate > 10L) {
                rate = 10L;
            }

            Object[] empCountAndTrget = sessionBean.findEmpCountAndTrgtPerMonths(locId, selectedFromMonth, selectedFromYear, selectedFromMonth, selectedFromYear);
            empCount = Long.parseLong(empCountAndTrget[0].toString());
            empTrgt = Long.parseLong(empCountAndTrget[1].toString());
            heighestSalesPersons = sessionBean.findSalesmenWithHeigherTargetByMonths(selectedFromMonth, selectedFromYear, selectedFromMonth, selectedFromYear);
            Object[] totBrnTrgt = sessionBean.findTotalBrnQtyTrgetYearVuInPeriod(selectedFromMonth, selectedFromYear, selectedFromMonth, selectedFromYear);
            totShowroomTrgt = new BrnQtyTrgetVu();
            totShowroomTrgt.setBrnTarget(Long.parseLong(totBrnTrgt[0].toString()));
            totShowroomTrgt.setNet(Long.parseLong(totBrnTrgt[8].toString()));
            totShowroomTrgt.setNetC(Long.parseLong(totBrnTrgt[1].toString()));
            totShowroomTrgt.setNetD(Long.parseLong(totBrnTrgt[2].toString()));
            totShowroomTrgt.setNetS(Long.parseLong(totBrnTrgt[3].toString()));
            totShowroomTrgt.setQty(Long.parseLong(totBrnTrgt[7].toString()));
            totShowroomTrgt.setQtyC(Long.parseLong(totBrnTrgt[4].toString()));
            totShowroomTrgt.setQtyD(Long.parseLong(totBrnTrgt[5].toString()));
            totShowroomTrgt.setQtyS(Long.parseLong(totBrnTrgt[6].toString()));
            totShowroomTrgt.setPercent(Long.parseLong(totBrnTrgt[9].toString()));
            trgtMonth = "  «—Ã  ‘Â— " + sdf.format(c.getTime());
        } else {
            System.out.println("currentTarget!=null");
            hrShowroomTrgt = new BrnQtyTrgetVu();
            hrShowroomTrgt.setBrnId(currentTarget.getBrnId());
            hrShowroomTrgt.setBrnTarget(currentTarget.getBrnTarget());
            hrShowroomTrgt.setNetC(currentTarget.getNetC());
            hrShowroomTrgt.setNetD(currentTarget.getNetD());
            hrShowroomTrgt.setNetS(currentTarget.getNetS());
            hrShowroomTrgt.setQtyC(currentTarget.getQtyC());
            hrShowroomTrgt.setQtyD(currentTarget.getQtyD());
            hrShowroomTrgt.setQtyS(currentTarget.getQtyS());
            hrShowroomTrgt.setQty(currentTarget.getQty());
            hrShowroomTrgt.setNet(currentTarget.getNet());
            hrShowroomTrgt.setPercent(currentTarget.getTrgtPercent());

            rate = hrShowroomTrgt.getPercent() / 10;
            if (rate > 10L) {
                rate = 10L;
            }

            Calendar c = Calendar.getInstance();
            int currentMonth = (c.get(Calendar.MONTH) + 1);
            for (int i = currentMonth; i > 0; i--) {
                monthsFromList.add(new SelectItem(((i + "").length() == 1 ? "0" + i : i + ""), ((i + "").length() == 1 ? "0" + i : i + "")));
                monthsToList.add(new SelectItem(((i + "").length() == 1 ? "0" + i : i + ""), ((i + "").length() == 1 ? "0" + i : i + "")));
            }
            yearFromList = sessionBean.findTrgtYearList();
            yearToList = sessionBean.findTrgtYearList();
            selectedFromYear = selectedToYear = yearFromList.get(0);
            order = sessionBean.findShowroomOrder(hrShowroomTrgt.getPercent()) + 1;
            hrShowroomTrgtList = sessionBean.findAllShowroomTrgts();
            selectedFromMonth = selectedToMonth = monthsFromList.get(0).getLabel();

            List<Object[]> brnTargetList = sessionBean.findBrnQtyTrgetYearVuInPeriod(selectedFromMonth, selectedFromYear, selectedToMonth, selectedToYear, null);
            order = 0L;
            Object[] o = null;
            hrShowroomTrgtList = new ArrayList<BrnQtyTrgetVu>();
            for (Object[] obj : brnTargetList) {
                if (obj[12].toString().equals(locId.toString())) {
                    o = obj;
                    order++;
                }
                if (o == null) {
                    order++;
                }
                BrnQtyTrgetVu brnQtyTrgetVu = new BrnQtyTrgetVu();
                brnQtyTrgetVu.setBrnTarget(Long.parseLong(obj[1].toString()));
                brnQtyTrgetVu.setNet(Long.parseLong(obj[9].toString()));
                brnQtyTrgetVu.setNetC(Long.parseLong(obj[2].toString()));
                brnQtyTrgetVu.setNetD(Long.parseLong(obj[3].toString()));
                brnQtyTrgetVu.setNetS(Long.parseLong(obj[4].toString()));
                brnQtyTrgetVu.setQty(Long.parseLong(obj[8].toString()));
                brnQtyTrgetVu.setQtyC(Long.parseLong(obj[5].toString()));
                brnQtyTrgetVu.setQtyD(Long.parseLong(obj[6].toString()));
                brnQtyTrgetVu.setQtyS(Long.parseLong(obj[7].toString()));
                brnQtyTrgetVu.setPercent(Long.parseLong(obj[10].toString()));
                HrLocation hrLocation = new HrLocation();
                hrLocation.setId(Long.parseLong(obj[12].toString()));
                hrLocation.setName(obj[11].toString());
                brnQtyTrgetVu.setLocId(hrLocation);
                hrShowroomTrgtList.add(brnQtyTrgetVu);
            }


            selectedFromMonth = selectedToMonth = monthsFromList.get(0).getLabel();
            List<Object[]> empList = sessionBean.findEmpQtyTrgetYearVuListInPeriod(selectedFromMonth, selectedFromYear, selectedToMonth, selectedToYear, locId);
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

            Object[] empCountAndTrget = sessionBean.findEmpCountAndTrgtPerMonths(locId, selectedFromMonth, selectedFromYear, selectedToMonth, selectedToYear);
            empCount = Long.parseLong(empCountAndTrget[0].toString());
            empTrgt = Long.parseLong(empCountAndTrget[1].toString());
            heighestSalesPersons = sessionBean.findSalesmenWithHeigherTargetByMonths(selectedFromMonth, selectedFromYear, selectedToMonth, selectedToYear);
            Object[] totBrnTrgt = sessionBean.findTotalBrnQtyTrgetYearVuInPeriod(selectedFromMonth, selectedFromYear, selectedToMonth, selectedToYear);
            totShowroomTrgt = new BrnQtyTrgetVu();
            totShowroomTrgt.setBrnTarget(Long.parseLong(totBrnTrgt[0].toString()));
            totShowroomTrgt.setNet(Long.parseLong(totBrnTrgt[8].toString()));
            totShowroomTrgt.setNetC(Long.parseLong(totBrnTrgt[1].toString()));
            totShowroomTrgt.setNetD(Long.parseLong(totBrnTrgt[2].toString()));
            totShowroomTrgt.setNetS(Long.parseLong(totBrnTrgt[3].toString()));
            totShowroomTrgt.setQty(Long.parseLong(totBrnTrgt[7].toString()));
            totShowroomTrgt.setQtyC(Long.parseLong(totBrnTrgt[4].toString()));
            totShowroomTrgt.setQtyD(Long.parseLong(totBrnTrgt[5].toString()));
            totShowroomTrgt.setQtyS(Long.parseLong(totBrnTrgt[6].toString()));
            totShowroomTrgt.setPercent(Long.parseLong(totBrnTrgt[9].toString()));

            totalRate = totShowroomTrgt.getPercent() / 10;
            if (totalRate > 10L) {
                totalRate = 10L;
            }
            trgtMonth = "  «—Ã  ‘Â— " + sdf.format(new Date());
        }

        pie1.set("”Ì—«„Ìﬂ", hrShowroomTrgt.getNetC());
        pie1.set("œÌﬂÊ—", hrShowroomTrgt.getNetD());
        pie1.set("’ÕÏ", hrShowroomTrgt.getNetS());
        pie1.setTitle("‰”»… ﬁÌ„…  ”·Ì„«  «·„‰ Ã«  «·Ï ≈Ã„«·Ï «· ”·Ì„« ");
        pie1.setLegendPosition("e");
        pie1.setShowDataLabels(true);
        pie1.setDiameter(150);
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

        pie3.set("”Ì—«„Ìﬂ", hrShowroomTrgt.getQtyC());
        pie3.set("œÌﬂÊ—", hrShowroomTrgt.getQtyD());
        pie3.set("’ÕÏ", hrShowroomTrgt.getQtyS());
        pie3.setTitle("‰”»… ﬂ„Ì… «· ”·Ì„«  «·Ï ≈Ã„«·Ï «·ﬂ„Ì…");
        pie3.setLegendPosition("e");
        pie3.setShowDataLabels(true);
        pie3.setDiameter(150);


        pie4.set("”Ì—«„Ìﬂ", totShowroomTrgt.getNetC());
        pie4.set("œÌﬂÊ—", totShowroomTrgt.getNetD());
        pie4.set("’ÕÏ", totShowroomTrgt.getNetS());
        pie4.setTitle("‰”»… ﬁÌ„…  ”·Ì„«  «·„‰ Ã«  «·Ï ≈Ã„«·Ï «· ”·Ì„« ");
        pie4.setLegendPosition("e");
        pie4.setShowDataLabels(true);
        pie4.setDiameter(150);

        pie5 = new PieChartModel();
        Long tot = totShowroomTrgt.getBrnTarget() - totShowroomTrgt.getNet();
        if (tot < 0) {
            tot = 0L;
        }
        pie5.set(" «—Ã ", tot);
        pie5.set("„»Ì⁄« ", totShowroomTrgt.getNet());
        pie5.setTitle("‰”»… ﬁÌ„… «· ”·Ì„«  «·Ï «· «—Ã ");
        pie5.setLegendPosition("e");
        pie5.setShowDataLabels(true);
        pie5.setDiameter(150);


        pie6 = new PieChartModel();
        pie6.set("”Ì—«„Ìﬂ", totShowroomTrgt.getQtyC());
        pie6.set("œÌﬂÊ—", totShowroomTrgt.getQtyD());
        pie6.set("’ÕÏ", totShowroomTrgt.getQtyS());
        pie6.setTitle("‰”»… ﬂ„Ì… «· ”·Ì„«  «·Ï ≈Ã„«·Ï «·ﬂ„Ì…");
        pie6.setLegendPosition("e");
        pie6.setShowDataLabels(true);
        pie6.setDiameter(150);


    }

    public void search(ActionEvent e) {
        chartLabels = "";
        chartSales = "";
        chartTarget = "";
        if (locId == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "ÌÃ» ≈œŒ Ì«— «·„⁄—÷ √Ê·«"));
            return;
        }
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.set(Calendar.MONTH, Integer.parseInt(selectedFromMonth) - 1);
        c1.set(Calendar.YEAR, Integer.parseInt(selectedFromYear));
        c2.set(Calendar.MONTH, Integer.parseInt(selectedToMonth) - 1);
        c2.set(Calendar.YEAR, Integer.parseInt(selectedToYear));
        if (c1.after(c2)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "«·› —… €Ì— ’ÕÌÕ…"));
            return;
        }

        List<Object[]> brnTargetList = sessionBean.findBrnQtyTrgetYearVuInPeriod(selectedFromMonth, selectedFromYear, selectedToMonth, selectedToYear, null);
        if (brnTargetList.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·« ÌÊÃœ  «—Ã "));
            return;
        }
        order = 0L;
        Object[] o = null;
        hrShowroomTrgtList = new ArrayList<BrnQtyTrgetVu>();
        for (Object[] obj : brnTargetList) {
            if (obj[12].toString().equals(locId.toString())) {
                o = obj;
                order++;
            }
            if (o == null) {
                order++;
            }
            BrnQtyTrgetVu brnQtyTrgetVu = new BrnQtyTrgetVu();
            brnQtyTrgetVu.setBrnTarget(Long.parseLong(obj[1].toString()));
            brnQtyTrgetVu.setNet(Long.parseLong(obj[9].toString()));
            brnQtyTrgetVu.setNetC(Long.parseLong(obj[2].toString()));
            brnQtyTrgetVu.setNetD(Long.parseLong(obj[3].toString()));
            brnQtyTrgetVu.setNetS(Long.parseLong(obj[4].toString()));
            brnQtyTrgetVu.setQty(Long.parseLong(obj[8].toString()));
            brnQtyTrgetVu.setQtyC(Long.parseLong(obj[5].toString()));
            brnQtyTrgetVu.setQtyD(Long.parseLong(obj[6].toString()));
            brnQtyTrgetVu.setQtyS(Long.parseLong(obj[7].toString()));
            brnQtyTrgetVu.setPercent(Long.parseLong(obj[10].toString()));
            HrLocation hrLocation = new HrLocation();
            hrLocation.setId(Long.parseLong(obj[12].toString()));
            hrLocation.setName(obj[11].toString());
            brnQtyTrgetVu.setLocId(hrLocation);
            hrShowroomTrgtList.add(brnQtyTrgetVu);
        }
        if (o == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Œÿ√", "·« ÌÊÃœ  «—Ã "));
            return;
        }

        hrShowroomTrgt = new BrnQtyTrgetVu();
        hrShowroomTrgt.setBrnId(Long.parseLong(o[0].toString()));
        hrShowroomTrgt.setBrnTarget(Long.parseLong(o[1].toString()));
        hrShowroomTrgt.setNetC(Long.parseLong(o[2].toString()));
        hrShowroomTrgt.setNetD(Long.parseLong(o[3].toString()));
        hrShowroomTrgt.setNetS(Long.parseLong(o[4].toString()));
        hrShowroomTrgt.setQtyC(Long.parseLong(o[5].toString()));
        hrShowroomTrgt.setQtyD(Long.parseLong(o[6].toString()));
        hrShowroomTrgt.setQtyS(Long.parseLong(o[7].toString()));
        hrShowroomTrgt.setQty(Long.parseLong(o[8].toString()));
        hrShowroomTrgt.setNet(Long.parseLong(o[9].toString()));
        hrShowroomTrgt.setPercent(Long.parseLong(o[10].toString()));


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        if (selectedFromMonth.equals(selectedToMonth) && selectedFromYear.equals(selectedToYear)) {
            trgtMonth = "  «—Ã  ‘Â— " + selectedFromMonth + " - " + sdf.format(new Date());
        } else {
            trgtMonth = "  «· «—Ã  „‰ ‘Â— " + selectedFromMonth + " - " + selectedFromYear + " ≈·Ï ‘Â— " + selectedToMonth + " - " + selectedToYear;
        }
        rate = hrShowroomTrgt.getPercent() / 10;
        if (rate > 10L) {
            rate = 10L;
        }

        List<Object[]> empList = sessionBean.findEmpQtyTrgetYearVuListInPeriod(selectedFromMonth, selectedFromYear, selectedToMonth, selectedToYear, locId);
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
        Object[] empCountAndTrget = sessionBean.findEmpCountAndTrgtPerMonths(locId, selectedFromMonth, selectedFromYear, selectedToMonth, selectedToYear);
        if (empCountAndTrget != null && empCountAndTrget[0] != null) {
            empCount = Long.parseLong(empCountAndTrget[0].toString());
            empTrgt = Long.parseLong(empCountAndTrget[1].toString());
        } else {
            empCount = 0L;
            empTrgt = 0L;
        }
        heighestSalesPersons = sessionBean.findSalesmenWithHeigherTargetByMonths(selectedFromMonth, selectedFromYear, selectedToMonth, selectedToYear);
        Object[] totBrnTrgt = sessionBean.findTotalBrnQtyTrgetYearVuInPeriod(selectedFromMonth, selectedFromYear, selectedToMonth, selectedToYear);
        totShowroomTrgt = new BrnQtyTrgetVu();
        totShowroomTrgt.setBrnTarget(Long.parseLong(totBrnTrgt[0].toString()));
        totShowroomTrgt.setNet(Long.parseLong(totBrnTrgt[8].toString()));
        totShowroomTrgt.setNetC(Long.parseLong(totBrnTrgt[1].toString()));
        totShowroomTrgt.setNetD(Long.parseLong(totBrnTrgt[2].toString()));
        totShowroomTrgt.setNetS(Long.parseLong(totBrnTrgt[3].toString()));
        totShowroomTrgt.setQty(Long.parseLong(totBrnTrgt[7].toString()));
        totShowroomTrgt.setQtyC(Long.parseLong(totBrnTrgt[4].toString()));
        totShowroomTrgt.setQtyD(Long.parseLong(totBrnTrgt[5].toString()));
        totShowroomTrgt.setQtyS(Long.parseLong(totBrnTrgt[6].toString()));
        totShowroomTrgt.setPercent(Long.parseLong(totBrnTrgt[9].toString()));
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

        pie4 = new PieChartModel();
        pie4.set("”Ì—«„Ìﬂ", totShowroomTrgt.getNetC());
        pie4.set("œÌﬂÊ—", totShowroomTrgt.getNetD());
        pie4.set("’ÕÏ", totShowroomTrgt.getNetS());
        pie4.setTitle("‰”»… ﬁÌ„…  ”·Ì„«  «·„‰ Ã«  «·Ï ≈Ã„«·Ï «· ”·Ì„« ");
        pie4.setLegendPosition("e");
        pie4.setShowDataLabels(true);
        pie4.setDiameter(150);

        pie5 = new PieChartModel();
        Long tot = totShowroomTrgt.getBrnTarget() - totShowroomTrgt.getNet();
        if (tot < 0) {
            tot = 0L;
        }
        pie5.set(" «—Ã ", tot);
        pie5.set("„»Ì⁄« ", totShowroomTrgt.getNet());
        pie5.setTitle("‰”»… ﬁÌ„… «· ”·Ì„«  «·Ï «· «—Ã ");
        pie5.setLegendPosition("e");
        pie5.setShowDataLabels(true);
        pie5.setDiameter(150);


        pie6 = new PieChartModel();
        pie6.set("”Ì—«„Ìﬂ", totShowroomTrgt.getQtyC());
        pie6.set("œÌﬂÊ—", totShowroomTrgt.getQtyD());
        pie6.set("’ÕÏ", totShowroomTrgt.getQtyS());
        pie6.setTitle("‰”»… ﬂ„Ì… «· ”·Ì„«  «·Ï ≈Ã„«·Ï «·ﬂ„Ì…");
        pie6.setLegendPosition("e");
        pie6.setShowDataLabels(true);
        pie6.setDiameter(150);
    }

    public String print() throws JRException, SQLException, ClassNotFoundException, FileNotFoundException, IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        Connection connection = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String oracleURL = "jdbc:oracle:thin:@20.1.1.1:1521:cleodb";
            connection = DriverManager.getConnection(oracleURL, "crmk", "julcrmk");
            connection.setAutoCommit(false);
            //CrmkOrdrSader crmkOrdrSader = crmkSaderOrdrNotPrintedList.get(selected_row);
            String QTY_TXT = null;
            JasperPrint jasperPrint = null;
            JasperReport jasperReport = null;
            java.io.InputStream url = getClass().getResourceAsStream("/showroom_target_details.jrxml");
            JasperDesign design = JRXmlLoader.load(url);
            jasperReport = JasperCompileManager.compileReport(design);
            HashMap jasperParameter = new HashMap();
            jasperParameter.put("MONTHFROM", selectedFromMonth);
            jasperParameter.put("YEARFROM", selectedFromYear);
            jasperParameter.put("MONTHTO", selectedToMonth);
            jasperParameter.put("YEARTO", selectedToYear);
            jasperParameter.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.FALSE);

            jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParameter, connection);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=order.pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (JRException ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
        }
        FacesContext.getCurrentInstance().responseComplete();
        return null;
    }

    public void onListChanged1(ValueChangeEvent e) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        monthsFromList = new ArrayList<SelectItem>();
        if (e.getNewValue().equals(sdf.format(new Date()))) {
            Calendar c = Calendar.getInstance();
            int currentMonth = (c.get(Calendar.MONTH) + 1);
            for (int i = currentMonth; i > 0; i--) {
                monthsFromList.add(new SelectItem(((i + "").length() == 1 ? "0" + i : i + ""), ((i + "").length() == 1 ? "0" + i : i + "")));
            }
        } else {
            for (int i = 12; i > 0; i--) {
                monthsFromList.add(new SelectItem(((i + "").length() == 1 ? "0" + i : i + ""), ((i + "").length() == 1 ? "0" + i : i + "")));
            }
        }
    }

    public void onListChanged2(ValueChangeEvent e) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        monthsToList = new ArrayList<SelectItem>();
        if (e.getNewValue().equals(sdf.format(new Date()))) {
            Calendar c = Calendar.getInstance();
            int currentMonth = (c.get(Calendar.MONTH) + 1);
            for (int i = currentMonth; i > 0; i--) {
                monthsToList.add(new SelectItem(((i + "").length() == 1 ? "0" + i : i + ""), ((i + "").length() == 1 ? "0" + i : i + "")));
            }
        } else {
            for (int i = 12; i > 0; i--) {
                monthsToList.add(new SelectItem(((i + "").length() == 1 ? "0" + i : i + ""), ((i + "").length() == 1 ? "0" + i : i + "")));
            }
        }
    }

    private void createBarModel(Long locId) {
//        hrAllShowroomTrgtList=sessionBean.findAllShowroomTrgt(locId);
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

    /** Creates a new instance of AllShowroomTrgt */
    public AllShowroomTrgt() {
    }

    public List<SelectItem> getShowroomList() {
        return showroomList;
    }

    public void setShowroomList(List<SelectItem> showroomList) {
        this.showroomList = showroomList;
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

    public List<HrAllShowroomTrgt> getHrAllShowroomTrgtList() {
        return hrAllShowroomTrgtList;
    }

    public void setHrAllShowroomTrgtList(List<HrAllShowroomTrgt> hrAllShowroomTrgtList) {
        this.hrAllShowroomTrgtList = hrAllShowroomTrgtList;
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

    public List<EmpQtyTrgetVu> getHrTrgtVuList() {
        return hrTrgtVuList;
    }

    public void setHrTrgtVuList(List<EmpQtyTrgetVu> hrTrgtVuList) {
        this.hrTrgtVuList = hrTrgtVuList;
    }

    public Long getLocId() {
        return locId;
    }

    public void setLocId(Long locId) {
        this.locId = locId;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
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

    public String getTrgtMonth() {
        return trgtMonth;
    }

    public void setTrgtMonth(String trgtMonth) {
        this.trgtMonth = trgtMonth;
    }

    public List<Object[]> getHeighestSalesPersons() {
        return heighestSalesPersons;
    }

    public void setHeighestSalesPersons(List<Object[]> heighestSalesPersons) {
        this.heighestSalesPersons = heighestSalesPersons;
    }

    public Long getEmpCount() {
        return empCount;
    }

    public void setEmpCount(Long empCount) {
        this.empCount = empCount;
    }

    public String getSelectedFromMonth() {
        return selectedFromMonth;
    }

    public void setSelectedFromMonth(String selectedFromMonth) {
        this.selectedFromMonth = selectedFromMonth;
    }

    public String getSelectedToMonth() {
        return selectedToMonth;
    }

    public void setSelectedToMonth(String selectedToMonth) {
        this.selectedToMonth = selectedToMonth;
    }

    public BrnQtyTrgetVu getTotShowroomTrgt() {
        return totShowroomTrgt;
    }

    public void setTotShowroomTrgt(BrnQtyTrgetVu totShowroomTrgt) {
        this.totShowroomTrgt = totShowroomTrgt;
    }

    public Long getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(Long totalRate) {
        this.totalRate = totalRate;
    }

    public PieChartModel getPie4() {
        return pie4;
    }

    public void setPie4(PieChartModel pie4) {
        this.pie4 = pie4;
    }

    public PieChartModel getPie5() {
        return pie5;
    }

    public void setPie5(PieChartModel pie5) {
        this.pie5 = pie5;
    }

    public PieChartModel getPie6() {
        return pie6;
    }

    public void setPie6(PieChartModel pie6) {
        this.pie6 = pie6;
    }

    public Long getEmpTrgt() {
        return empTrgt;
    }

    public void setEmpTrgt(Long empTrgt) {
        this.empTrgt = empTrgt;
    }

    public List<String> getYearFromList() {
        return yearFromList;
    }

    public void setYearFromList(List<String> yearFromList) {
        this.yearFromList = yearFromList;
    }

    public List<String> getYearToList() {
        return yearToList;
    }

    public void setYearToList(List<String> yearToList) {
        this.yearToList = yearToList;
    }

    public String getSelectedFromYear() {
        return selectedFromYear;
    }

    public void setSelectedFromYear(String selectedFromYear) {
        this.selectedFromYear = selectedFromYear;
    }

    public String getSelectedToYear() {
        return selectedToYear;
    }

    public void setSelectedToYear(String selectedToYear) {
        this.selectedToYear = selectedToYear;
    }

    public List<SelectItem> getMonthsFromList() {
        return monthsFromList;
    }

    public void setMonthsFromList(List<SelectItem> monthsFromList) {
        this.monthsFromList = monthsFromList;
    }

    public List<SelectItem> getMonthsToList() {
        return monthsToList;
    }

    public void setMonthsToList(List<SelectItem> monthsToList) {
        this.monthsToList = monthsToList;
    }

    public boolean isNotRestricted() {
        return notRestricted;
    }

    public void setNotRestricted(boolean notRestricted) {
        this.notRestricted = notRestricted;
    }

}
