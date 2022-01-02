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
        Object[] arr1 = {"≈Ã«“… ≈⁄ Ì«œÌ…", ""};
        list.add(arr1);
        Object[] arr2 = {"—’Ìœ", "-"};
        list.add(arr2);
        Object[] arr3 = {"„” Â·ﬂ", hrAuthAndHolBalance.getNormalHolUsed()};
        list.add(arr3);
        Object[] arr4 = {"„ »ﬁÏ", "-"};
        list.add(arr4);
        Object[] arr5 = {"≈Ã«“… ⁄«—÷…", ""};
        list.add(arr5);
        Object[] arr6 = {"—’Ìœ", "10"};
        list.add(arr6);
        Object[] arr7 = {"„” Â·ﬂ", hrAuthAndHolBalance.getOpposedHolUsed()};
        list.add(arr7);
        Object[] arr8 = {"„ »ﬁÏ", 10 - hrAuthAndHolBalance.getOpposedHolUsed()};
        list.add(arr8);
        Object[] arr9 = {"≈Ã«“… ”‰ÊÏ", ""};
        list.add(arr9);
        Object[] arr10 = {"—’Ìœ", "6"};
        list.add(arr10);
        Object[] arr11 = {"„” Â·ﬂ", hrAuthAndHolBalance.getAnnualHolUsed()};
        list.add(arr11);
        Object[] arr12 = {"„ »ﬁÏ", 6 - hrAuthAndHolBalance.getAnnualHolUsed()};
        list.add(arr12);
        Object[] arr45 = {"≈Ã«“… ≈” À‰«∆Ì…", ""};
        list.add(arr45);
        Object[] arr46 = {"—’Ìœ", hrAuthAndHolBalance.getTotalExceptionalHol()};
        list.add(arr46);
        Object[] arr47 = {"„” Â·ﬂ", hrAuthAndHolBalance.getExceptionalHolUsed()};
        list.add(arr47);
        Object[] arr48 = {"„ »ﬁÏ", hrAuthAndHolBalance.getTotalExceptionalHol() - hrAuthAndHolBalance.getExceptionalHolUsed()};
        list.add(arr48);
        Object[] arr13 = {"≈Ã„«·Ï «·≈Ã«“… «·≈⁄ Ì«œÌ…", ""};
        list.add(arr13);
        Object[] arr14 = {"—’Ìœ", hrAuthAndHolBalance.getTotalNormalHol()};
        list.add(arr14);
        Object[] arr15 = {"„” Â·ﬂ", hrAuthAndHolBalance.getTotalNormalHol() - hrAuthAndHolBalance.getNormalHolRemind()};
        list.add(arr15);
        Object[] arr16 = {"„ »ﬁÏ", hrAuthAndHolBalance.getNormalHolRemind()};
        list.add(arr16);
        Object[] arr17 = {"»œ· —«Õ…", ""};
        list.add(arr17);
        Object[] arr18 = {"—’Ìœ", (hrAuthAndHolBalance.getInsteadHolRemind() + hrAuthAndHolBalance.getInsteadHolUsed())};
        list.add(arr18);
        Object[] arr19 = {"„” Â·ﬂ", hrAuthAndHolBalance.getInsteadHolUsed()};
        list.add(arr19);
        Object[] arr20 = {"„ »ﬁÏ", hrAuthAndHolBalance.getInsteadHolRemind()};
        list.add(arr20);
        Object[] arr21 = {"≈–‰ ·„ Ì⁄ „œ", ""};
        list.add(arr21);
        Object[] arr22 = {"—’Ìœ", "-"};
        list.add(arr22);
        Object[] arr23 = {"„” Â·ﬂ", hrAuthAndHolBalance.getTotBindingAuth()};
        list.add(arr23);
        Object[] arr24 = {"„ »ﬁÏ", "-"};
        list.add(arr24);
        Object[] arr25 = {"≈–‰ „⁄ „œ", ""};
        list.add(arr25);
        Object[] arr26 = {"—’Ìœ", "-"};
        list.add(arr26);
        Object[] arr27 = {"„” Â·ﬂ", hrAuthAndHolBalance.getTotAuthUsed()};
        list.add(arr27);
        Object[] arr28 = {"„ »ﬁÏ", "-"};
        list.add(arr28);
        Object[] arr29 = {"≈Ã„«·Ï «·√–Ê‰", ""};
        list.add(arr29);
        Object[] arr30 = {"—’Ìœ", "240"};
        list.add(arr30);
        Object[] arr31 = {"„” Â·ﬂ", (hrAuthAndHolBalance.getTotAuthUsed() + hrAuthAndHolBalance.getTotBindingAuth())};
        list.add(arr31);
        Object[] arr32 = {"„ »ﬁÏ", (240 - (hrAuthAndHolBalance.getTotAuthUsed() + hrAuthAndHolBalance.getTotBindingAuth()))};
        list.add(arr32);
        Object[] arr33 = {"√–Ê‰ ·„  ⁄ „œ ··‘Â— «·„«÷Ï", ""};
        list.add(arr33);
        Object[] arr34 = {"—’Ìœ", "-"};
        list.add(arr34);
        Object[] arr35 = {"„” Â·ﬂ", hrAuthAndHolBalance.getPrevMonthBindingAuthCnt()};
        list.add(arr35);
        Object[] arr36 = {"„ »ﬁÏ", "-"};
        list.add(arr36);
        Object[] arr37 = {"√–Ê‰ √⁄ „œ  «·‘Â— «·„«÷Ï", ""};
        list.add(arr37);
        Object[] arr38 = {"—’Ìœ", "-"};
        list.add(arr38);
        Object[] arr39 = {"„” Â·ﬂ", hrAuthAndHolBalance.getPrevMonthAuthUsedCnt()};
        list.add(arr39);
        Object[] arr40 = {"„ »ﬁÏ", "-"};
        list.add(arr40);
        Object[] arr41 = {"≈Ã„«·Ï √–Ê‰ «·‘Â— «·„«÷Ï", ""};
        list.add(arr41);
        Object[] arr42 = {"—’Ìœ", "240"};
        list.add(arr42);
        Object[] arr43 = {"„” Â·ﬂ", (hrAuthAndHolBalance.getPrevMonthAuthUsedCnt() + hrAuthAndHolBalance.getPrevMonthBindingAuthCnt())};
        list.add(arr43);
        Object[] arr44 = {"„ »ﬁÏ", 240 - (hrAuthAndHolBalance.getPrevMonthAuthUsedCnt() + hrAuthAndHolBalance.getPrevMonthBindingAuthCnt())};
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
