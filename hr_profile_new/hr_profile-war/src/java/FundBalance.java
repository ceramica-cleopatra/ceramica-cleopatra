/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Administrator
 */
@ManagedBean
@ViewScoped
public class FundBalance {

    private List<String> monthList = new ArrayList<String>();
    private String currentMonth;
    @PostConstruct
    public void init() {
        //listFilesAndFolders("/opt/web/Hr/fund_balance/");
        listFilesAndFolders("/opt/web/Hr/fund_balance/");
    }

    /** Creates a new instance of FundBalance */
    public FundBalance() {
    }

    public void listFilesAndFolders(String folder) {
        monthList = new ArrayList<String>();
        File file = new File(folder);
        File[] fileArray = file.listFiles();
        if (fileArray.length > 0) {
            for (int i = 0; i < fileArray.length; i++) {
                if (fileArray[i].toString().contains(".jpg")) {
                    monthList.add(fileArray[i].getName().toString().replaceAll(".jpg", ""));
                    Collections.sort(monthList, new Comparator() {

                        @Override
                        public int compare(Object o1, Object o2) {
                            String s1 = o1.toString();
                            String s2 = o2.toString();
                            if (Long.parseLong(s1.substring(s1.indexOf("-") + 1))>Long.parseLong(s2.substring(s2.indexOf("-") + 1))) {
                                return 1;
                            } else if (Long.parseLong(s1.substring(s1.indexOf("-") + 1))==Long.parseLong(s2.substring(s2.indexOf("-") + 1))) {
                                if (Long.parseLong(s1.substring(0, s1.indexOf("-")))>Long.parseLong(s2.substring(0, s2.indexOf("-")))) {
                                    return 1;
                                } else {
                                    return -1;
                                }
                            } else {
                                return -1;
                            }
                        }
                    });
                }
            }
        }
    }


    public void findCurrentMonth(ActionEvent ae){
        currentMonth=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("month");
    }
    public List<String> getMonthList() {
        return monthList;
    }

    public void setMonthList(List<String> monthList) {
        this.monthList = monthList;
    }

    public String getCurrentMonth() {
        System.out.println("currentMonth:"+currentMonth);
        return currentMonth;
    }

    public void setCurrentMonth(String currentMonth) {
        this.currentMonth = currentMonth;
    }

    
}
