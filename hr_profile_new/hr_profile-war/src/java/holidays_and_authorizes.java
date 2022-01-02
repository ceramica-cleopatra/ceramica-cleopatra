/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrAuthAndHolBalance;
import e.HrProfileAccessLog;
import e.HrPrvYearTransHolidays;
import e.HrUsers;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.mindmap.MindmapNode;
import sb.SessionBeanLocal;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.diagram.DefaultDiagramModel;

@ManagedBean
@ViewScoped
public class holidays_and_authorizes implements Serializable {

    @EJB
    private SessionBeanLocal sessionBean;
    private HrAuthAndHolBalance hrAuthAndHolBalance;
    private String usercode;
    private List<Object[]> list = new ArrayList<Object[]>();
    private HrPrvYearTransHolidays previousEmpHolidays = new HrPrvYearTransHolidays();

    @PostConstruct
    public void init() {
        usercode = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usercode");
        HrUsers hrUsers = (HrUsers) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrUsers");
        if (hrUsers != null) {
            HrProfileAccessLog hrProfileAccessLog = new HrProfileAccessLog();
            hrProfileAccessLog.setEmpNo(Long.parseLong(usercode));
            hrProfileAccessLog.setPageName(FacesContext.getCurrentInstance().getViewRoot().getViewId());
            hrProfileAccessLog.setTrnsDate(new Date());
            hrProfileAccessLog.setUserName(hrUsers.getUserName());
            sessionBean.persistProfileAccessLog(hrProfileAccessLog);
        }
        hrAuthAndHolBalance = sessionBean.findEmpAuthAndHolBalance(Long.parseLong(usercode));
        Object[] arr1 = {"����� ��������", ""};
        list.add(arr1);
        Object[] arr2 = {"����", "-"};
        list.add(arr2);
        Object[] arr3 = {"������", hrAuthAndHolBalance.getNormalHolUsed()};
        list.add(arr3);
        Object[] arr4 = {"�����", "-"};
        list.add(arr4);
        Object[] arr5 = {"����� �����", ""};
        list.add(arr5);
        Object[] arr6 = {"����", "10"};
        list.add(arr6);
        Object[] arr7 = {"������", hrAuthAndHolBalance.getOpposedHolUsed()};
        list.add(arr7);
        Object[] arr8 = {"�����", 10 - hrAuthAndHolBalance.getOpposedHolUsed()};
        list.add(arr8);
        Object[] arr9 = {"����� ����", ""};
        list.add(arr9);
        Object[] arr10 = {"����", "6"};
        list.add(arr10);
        Object[] arr11 = {"������", hrAuthAndHolBalance.getAnnualHolUsed()};
        list.add(arr11);
        Object[] arr12 = {"�����", 6 - hrAuthAndHolBalance.getAnnualHolUsed()};
        list.add(arr12);
        Object[] arr45 = {"����� ���������", ""};
        list.add(arr45);
        Object[] arr46 = {"����", hrAuthAndHolBalance.getTotalExceptionalHol()};
        list.add(arr46);
        Object[] arr47 = {"������", hrAuthAndHolBalance.getExceptionalHolUsed()};
        list.add(arr47);
        Object[] arr48 = {"�����", hrAuthAndHolBalance.getTotalExceptionalHol() - hrAuthAndHolBalance.getExceptionalHolUsed()};
        list.add(arr48);
        Object[] arr13 = {"������ ������� ����������", ""};
        list.add(arr13);
        Object[] arr14 = {"����", hrAuthAndHolBalance.getTotalNormalHol()};
        list.add(arr14);
        Object[] arr15 = {"������", hrAuthAndHolBalance.getTotalNormalHol() - hrAuthAndHolBalance.getNormalHolRemind()};
        list.add(arr15);
        Object[] arr16 = {"�����", hrAuthAndHolBalance.getNormalHolRemind()};
        list.add(arr16);
        Object[] arr17 = {"��� ����", ""};
        list.add(arr17);
        Object[] arr18 = {"����", (hrAuthAndHolBalance.getInsteadHolRemind() + hrAuthAndHolBalance.getInsteadHolUsed())};
        list.add(arr18);
        Object[] arr19 = {"������", hrAuthAndHolBalance.getInsteadHolUsed()};
        list.add(arr19);
        Object[] arr20 = {"�����", hrAuthAndHolBalance.getInsteadHolRemind()};
        list.add(arr20);
        Object[] arr21 = {"��� �� �����", ""};
        list.add(arr21);
        Object[] arr22 = {"����", "-"};
        list.add(arr22);
        Object[] arr23 = {"������", hrAuthAndHolBalance.getTotBindingAuth()};
        list.add(arr23);
        Object[] arr24 = {"�����", "-"};
        list.add(arr24);
        Object[] arr25 = {"��� �����", ""};
        list.add(arr25);
        Object[] arr26 = {"����", "-"};
        list.add(arr26);
        Object[] arr27 = {"������", hrAuthAndHolBalance.getTotAuthUsed()};
        list.add(arr27);
        Object[] arr28 = {"�����", "-"};
        list.add(arr28);
        Object[] arr29 = {"������ ������", ""};
        list.add(arr29);
        Object[] arr30 = {"����", "240"};
        list.add(arr30);
        Object[] arr31 = {"������", (hrAuthAndHolBalance.getTotAuthUsed() + hrAuthAndHolBalance.getTotBindingAuth())};
        list.add(arr31);
        Object[] arr32 = {"�����", (240 - (hrAuthAndHolBalance.getTotAuthUsed() + hrAuthAndHolBalance.getTotBindingAuth()))};
        list.add(arr32);
        Object[] arr33 = {"���� �� ����� ����� ������", ""};
        list.add(arr33);
        Object[] arr34 = {"����", "-"};
        list.add(arr34);
        Object[] arr35 = {"������", hrAuthAndHolBalance.getPrevMonthBindingAuthCnt()};
        list.add(arr35);
        Object[] arr36 = {"�����", "-"};
        list.add(arr36);
        Object[] arr37 = {"���� ������ ����� ������", ""};
        list.add(arr37);
        Object[] arr38 = {"����", "-"};
        list.add(arr38);
        Object[] arr39 = {"������", hrAuthAndHolBalance.getPrevMonthAuthUsedCnt()};
        list.add(arr39);
        Object[] arr40 = {"�����", "-"};
        list.add(arr40);
        Object[] arr41 = {"������ ���� ����� ������", ""};
        list.add(arr41);
        Object[] arr42 = {"����", "240"};
        list.add(arr42);
        Object[] arr43 = {"������", (hrAuthAndHolBalance.getPrevMonthAuthUsedCnt() + hrAuthAndHolBalance.getPrevMonthBindingAuthCnt())};
        list.add(arr43);
        Object[] arr44 = {"�����", 240 - (hrAuthAndHolBalance.getPrevMonthAuthUsedCnt() + hrAuthAndHolBalance.getPrevMonthBindingAuthCnt())};
        list.add(arr44);
        List<HrPrvYearTransHolidays> hrPrvYearTransHolidayses = sessionBean.getPreviousEmpHolidays(Long.parseLong(usercode));
        if (!hrPrvYearTransHolidayses.isEmpty()) {
            previousEmpHolidays = hrPrvYearTransHolidayses.get(0);
        }else{
            previousEmpHolidays.setHolDays(0L);
            previousEmpHolidays.setRahatDays(0L);
        }
    }

    public List<Object[]> getList() {
        return list;
    }

    public void setList(List<Object[]> list) {
        this.list = list;
    }

    public HrAuthAndHolBalance getHrAuthAndHolBalance() {
        return hrAuthAndHolBalance;
    }

    public void setHrAuthAndHolBalance(HrAuthAndHolBalance hrAuthAndHolBalance) {
        this.hrAuthAndHolBalance = hrAuthAndHolBalance;
    }

    public HrPrvYearTransHolidays getPreviousEmpHolidays() {
        return previousEmpHolidays;
    }

    public void setPreviousEmpHolidays(HrPrvYearTransHolidays previousEmpHolidays) {
        this.previousEmpHolidays = previousEmpHolidays;
    }

    
}
