/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrBorrowZamalaDt;
import e.HrBorrowZamalaHd;
import e.HrEmpInfo;
import e.HrFundBorrowSummary;
import e.HrProfileAccessLog;
import e.HrUsers;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import sb.SessionBeanLocal;

/**
 *
 * @author a.abbas
 */
@ManagedBean
@ViewScoped
public class FundBorrowFollowUp {
    @EJB
    private SessionBeanLocal sessionBean;
    private HrEmpInfo hrEmpInfo;
    private List<HrBorrowZamalaHd> hrBorrowZamalaHdList;
    private HrBorrowZamalaHd selectedBorrow;
    private List<HrBorrowZamalaDt> borrowDetailList;
    @PostConstruct
    public void init(){
        hrEmpInfo=(HrEmpInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrEmpInfo");
        HrUsers hrUsers = (HrUsers) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrUsers");
        if (hrUsers != null) {
            HrProfileAccessLog hrProfileAccessLog = new HrProfileAccessLog();
            hrProfileAccessLog.setEmpNo(hrEmpInfo.getEmpNo());
            hrProfileAccessLog.setPageName(FacesContext.getCurrentInstance().getViewRoot().getViewId());
            hrProfileAccessLog.setTrnsDate(new Date());
            hrProfileAccessLog.setUserName(hrUsers.getUserName());
            sessionBean.persistProfileAccessLog(hrProfileAccessLog);
        }
        hrBorrowZamalaHdList=sessionBean.findAllFundBorrow(hrEmpInfo);
    }

    public void onRowSelected(){
        if(selectedBorrow!=null && selectedBorrow.getHrBorrowZamalaDtList()!=null && !selectedBorrow.getHrBorrowZamalaDtList().isEmpty()){
            borrowDetailList=new ArrayList<HrBorrowZamalaDt>();
            borrowDetailList.addAll(selectedBorrow.getHrBorrowZamalaDtList());
            Collections.sort(borrowDetailList,new FundBorrowDetailsComparator());
        }
    }

    public List<HrBorrowZamalaHd> getHrBorrowZamalaHdList() {
        return hrBorrowZamalaHdList;
    }

    public void setHrBorrowZamalaHdList(List<HrBorrowZamalaHd> hrBorrowZamalaHdList) {
        this.hrBorrowZamalaHdList = hrBorrowZamalaHdList;
    }

    public HrBorrowZamalaHd getSelectedBorrow() {
        if(selectedBorrow!=null && selectedBorrow.getListHrFundBorrowSummary().get(0).getX().getId()!=selectedBorrow.getId()){
            List<HrFundBorrowSummary> l=new ArrayList<HrFundBorrowSummary>();
            l.add(sessionBean.findFundBorrowSummaryByHdId(selectedBorrow));
            selectedBorrow.setListHrFundBorrowSummary(l);
        }
        return selectedBorrow;
    }


    public void setSelectedBorrow(HrBorrowZamalaHd selectedBorrow) {
        this.selectedBorrow = selectedBorrow;

    }
    
    /** Creates a new instance of FundBorrowFollowUp */
    public FundBorrowFollowUp() {
    }

    public List<HrBorrowZamalaDt> getBorrowDetailList() {
        return borrowDetailList;
    }

    public void setBorrowDetailList(List<HrBorrowZamalaDt> borrowDetailList) {
        this.borrowDetailList = borrowDetailList;
    }



}
