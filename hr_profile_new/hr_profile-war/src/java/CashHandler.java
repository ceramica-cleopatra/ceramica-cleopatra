/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrEmpMangers;
import e.HrGzaEmpMngDt;
import e.HrInOutManualTrnsVu;
import e.HrLocation;
import e.HrLocationIpMapping;
import e.HrMenuTable;
import e.HrMontlySalaryCalcPeriod;
import e.HrProfileMsg;
import e.HrProfilePrivilage;
import e.HrTamyozApproveEmp;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.Long;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import sb.SessionBeanLocal;

/**
 *
 * @author Administrator
 */
public class CashHandler {

    private static Map<Long, List<HrEmpMangers>> empManagers;
    private static Map<Long, List<HrEmpMangers>> mngEmployees;
    private static Map<Long, List<HrProfilePrivilage>> menuCashMap;
    private static Map<Long, List<HrProfileMsg>> alerts;
    private static Map<Long, List<HrProfileMsg>> msgs;
    private static Map<Long, List<HrProfileMsg>> decisions;
    private static Map<Long, List<HrProfileMsg>> tasks;
    private static Map<Long, Map<Long, List<Long>>> tamyozApprove;
    public static Long menuRefreshTime;
    public static Long tamyozApproveRefreshTime;
    public static Long empManagersRefreshTime;
    public static Long hierarchyRefreshTime;
    public static Long msgsRefreshTime;
    private static SessionBeanLocal sessionBean;
    private static Map<Long, Map<Long, String>> inOutManualTrnsMap;
    private static Long inOutManualRefreshTime;
    public static HrMontlySalaryCalcPeriod hscp;
    public static Long hscpRefreshTime;
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd");
    private static String headHierachy;
    private static String salesHierachy;
    private static String storeHierarchy;
    private static Map<String, FileInputStream> empImageMap;
    private static Map<Long, List<Long>> locationIpMapping;
    private static Long ipLocationRefreshTime;

    public CashHandler() {
    }

    public static synchronized void fillCash() {
        sessionBean = lookupSessionBeanLocal();
        List<HrEmpInfo> hrEmpInfoList = sessionBean.allHrEmpInfo();

        if (menuCashMap == null || menuCashMap.isEmpty() || System.currentTimeMillis() - menuRefreshTime >= 18000000) {
            menuCashMap = new HashMap<Long, List<HrProfilePrivilage>>();
            Map<Long, List<HrProfilePrivilage>> menuCashMapTemp = new HashMap<Long, List<HrProfilePrivilage>>();
            List<HrProfilePrivilage> hrProfilePrivilages = sessionBean.allProfilePrivilage();
            for (HrProfilePrivilage hrProfilePrivilage : hrProfilePrivilages) {
                if (menuCashMapTemp.containsKey(hrProfilePrivilage.getEmpNo())) {
                    if (!menuCashMapTemp.get(hrProfilePrivilage.getEmpNo()).contains(hrProfilePrivilage)) {
                        menuCashMapTemp.get(hrProfilePrivilage.getEmpNo()).add(hrProfilePrivilage);
                    }
                } else {
                    List<HrProfilePrivilage> empPrivilages = new ArrayList<HrProfilePrivilage>();
                    empPrivilages.add(hrProfilePrivilage);
                    menuCashMapTemp.put(hrProfilePrivilage.getEmpNo(), empPrivilages);
                }
            }
            menuCashMap.putAll(menuCashMapTemp);
            menuRefreshTime = System.currentTimeMillis();
        }


        if (inOutManualTrnsMap == null || inOutManualTrnsMap.isEmpty() || System.currentTimeMillis() - inOutManualRefreshTime >= 18000000) {
            inOutManualTrnsMap = new HashMap<Long, Map<Long, String>>();
            List<HrInOutManualTrnsVu> hrInOutManualTrnsVus = sessionBean.findAllInOutManualTrns();
            for (HrInOutManualTrnsVu hrInOutManualTrnsVu : hrInOutManualTrnsVus) {
                if (!inOutManualTrnsMap.containsKey(hrInOutManualTrnsVu.getUserId())) {
                    inOutManualTrnsMap.put(hrInOutManualTrnsVu.getUserId(), new HashMap<Long, String>());
                }
                inOutManualTrnsMap.get(hrInOutManualTrnsVu.getUserId()).put(hrInOutManualTrnsVu.getEmpNo(), hrInOutManualTrnsVu.getEmpName());

            }
            inOutManualRefreshTime = System.currentTimeMillis();
        }

        if (alerts == null || alerts.isEmpty() || System.currentTimeMillis() - msgsRefreshTime >= 10800000) {

            alerts = new HashMap<Long, List<HrProfileMsg>>();
            msgs = new HashMap<Long, List<HrProfileMsg>>();
            decisions = new HashMap<Long, List<HrProfileMsg>>();
            tasks = new HashMap<Long, List<HrProfileMsg>>();
            empImageMap = new HashMap<String, FileInputStream>();
            List<HrProfileMsg> hrProfileMsgs = sessionBean.findAllProfilMsgs();
            if (hrProfileMsgs != null && hrProfileMsgs.size() > 0) {

                for (HrEmpInfo hrEmpInfo : hrEmpInfoList) {
                    List<HrProfileMsg> empAlerts = new ArrayList<HrProfileMsg>();
                    List<HrProfileMsg> empMsgs = new ArrayList<HrProfileMsg>();
                    List<HrProfileMsg> empDec = new ArrayList<HrProfileMsg>();
                    List<HrProfileMsg> empTasks = new ArrayList<HrProfileMsg>();
                    for (HrProfileMsg msg : hrProfileMsgs) {
                        if (hrEmpInfo.getEmpNo() == msg.getEmpNo()) {
                            if (msg.getMsgType() == 1L) {
                                empAlerts.add(msg);
                            } else if (msg.getMsgType() == 2L) {
                                empMsgs.add(msg);
                            } else if (msg.getMsgType() == 3L) {
                                empDec.add(msg);
                            } else if (msg.getMsgType() == 4L) {
                                empTasks.add(msg);
                            }
                        }
                    }
                    if (empAlerts != null && empAlerts.size() > 0) {
                        alerts.put(hrEmpInfo.getEmpNo(), empAlerts);

                    }
                    if (empMsgs != null && empMsgs.size() > 0) {
                        msgs.put(hrEmpInfo.getEmpNo(), empMsgs);

                    }
                    if (empTasks != null && empTasks.size() > 0) {
                        tasks.put(hrEmpInfo.getEmpNo(), empTasks);
                    }
                    if (empDec != null && empDec.size() > 0) {
                        decisions.put(hrEmpInfo.getEmpNo(), empDec);
                    }

                }
            }
            msgsRefreshTime = System.currentTimeMillis();
        }

        if (empManagers == null || empManagers.isEmpty() || System.currentTimeMillis() - empManagersRefreshTime >= 18000000) {
            empManagers = new HashMap<Long, List<HrEmpMangers>>();
            List<HrEmpMangers> hrEmpMangerses = sessionBean.findAllEmpManagers();
            for (HrEmpInfo hrEmpInfo : hrEmpInfoList) {
                List<HrEmpMangers> mngPerEmp = new ArrayList<HrEmpMangers>();
                for (HrEmpMangers empMng : hrEmpMangerses) {
                    if (hrEmpInfo.getEmpNo() == empMng.getEmpNo() && hrEmpInfo.getEmpNo() != empMng.getMngNo()) {
                        mngPerEmp.add(empMng);
                    }
                }
                empManagers.put(hrEmpInfo.getEmpNo(), mngPerEmp);
            }
            mngEmployees = new HashMap<Long, List<HrEmpMangers>>();
            for (HrEmpInfo hrEmpInfo : hrEmpInfoList) {
                List<HrEmpMangers> empPerMng = new ArrayList<HrEmpMangers>();
                for (HrEmpMangers mngEmp : hrEmpMangerses) {
                    if (hrEmpInfo.getEmpNo() == mngEmp.getMngNo() && hrEmpInfo.getEmpNo() != mngEmp.getEmpNo()) {
                        empPerMng.add(mngEmp);
                    }
                }
                mngEmployees.put(hrEmpInfo.getEmpNo(), empPerMng);
            }
            empManagersRefreshTime = System.currentTimeMillis();
        }




        if (tamyozApprove == null || tamyozApprove.isEmpty() || System.currentTimeMillis() - tamyozApproveRefreshTime >= 18000000) {
            List<HrTamyozApproveEmp> hrTamyozApproveEmps = sessionBean.findAllTamyozApproveEmps();
            tamyozApprove = new HashMap<Long, Map<Long, List<Long>>>();
            for (HrLocation hrLocation : sessionBean.findAllShowRooms()) {
                Map<Long, List<Long>> empMap = new HashMap<Long, List<Long>>();
                List<Long> sec = new ArrayList<Long>();
                List<Long> mng = new ArrayList<Long>();
                List<Long> tre = new ArrayList<Long>();
                for (HrTamyozApproveEmp hrTamyozApproveEmp : hrTamyozApproveEmps) {

                    if (hrLocation.getId().equals(hrTamyozApproveEmp.getLocId())) {
                        if (hrTamyozApproveEmp.getApproveLevel().equals(1L)) {
                            sec.add(hrTamyozApproveEmp.getEmpNo());
                        } else if (hrTamyozApproveEmp.getApproveLevel().equals(2L)) {
                            mng.add(hrTamyozApproveEmp.getEmpNo());
                        } else if (hrTamyozApproveEmp.getApproveLevel().equals(3L)) {
                            tre.add(hrTamyozApproveEmp.getEmpNo());
                        }
                    }
                }

                empMap.put(1L, sec);
                empMap.put(2L, mng);
                empMap.put(3L, tre);
                tamyozApprove.put(hrLocation.getId(), empMap);
            }
            tamyozApproveRefreshTime = System.currentTimeMillis();
        }

        if (hscp == null || System.currentTimeMillis() - hscpRefreshTime >= 10800000L) {
            hscp = new HrMontlySalaryCalcPeriod();
            java.util.Calendar cal = java.util.Calendar.getInstance();
            if (Long.parseLong(sdf.format(new Date())) > 15) {
                cal.add(Calendar.DATE, -15);
                hscp = sessionBean.find_month_period(cal.getTime());
            } else {
                hscp = sessionBean.find_month_period(new Date());
            }

            hscpRefreshTime = System.currentTimeMillis();
        }

        /* if (headHierachy == null || headHierachy.isEmpty() || System.currentTimeMillis() - hierarchyRefreshTime >= 18000000) {
        headHierachy = sessionBean.findManagerialHierarchy("1");
        storeHierarchy = sessionBean.findManagerialHierarchy("2");
        salesHierachy = sessionBean.findManagerialHierarchy("3");
        hierarchyRefreshTime = System.currentTimeMillis();
        }*/
        if (locationIpMapping == null || System.currentTimeMillis() - ipLocationRefreshTime >= 10800000L) {
            locationIpMapping=new HashMap<Long, List<Long>>();
            List<HrLocationIpMapping> ipLocationMapping = sessionBean.findAllLOocationIpMapping();
            for(HrLocationIpMapping hrLocationIpMapping : ipLocationMapping){
                if(locationIpMapping.containsKey(hrLocationIpMapping.getHrLocationIpMappingPK().getIp())){
                   locationIpMapping.get(hrLocationIpMapping.getHrLocationIpMappingPK().getIp()).add(hrLocationIpMapping.getHrLocationIpMappingPK().getLocId());
                }else{
                   List<Long> locationList=new ArrayList<Long>();
                   locationList.add(hrLocationIpMapping.getHrLocationIpMappingPK().getLocId());
                   locationIpMapping.put(hrLocationIpMapping.getHrLocationIpMappingPK().getIp(), locationList);
                }
            }
             ipLocationRefreshTime = System.currentTimeMillis();
        }

    }

    public static List<MenuModelDTO> populateMenu() {
        sessionBean = lookupSessionBeanLocal();
        List<MenuModelDTO> menuModel = new ArrayList<MenuModelDTO>();
        String usercode = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usercode");
        List<HrProfilePrivilage> hrProfilePrivilageList = sessionBean.allProfilePrivilage(Long.parseLong(usercode));
        for (HrProfilePrivilage parent : hrProfilePrivilageList) {
            if (parent.getMenuId().getParentId() == null) {
                MenuModelDTO menuModelDTO = new MenuModelDTO();
                menuModelDTO.setParent(parent.getMenuId());
                List<HrMenuTable> childList = new ArrayList<HrMenuTable>();
                for (HrProfilePrivilage child : hrProfilePrivilageList) {
                    if (child.getMenuId().getParentId() != null && parent.getMenuId().getId().equals(child.getMenuId().getParentId())) {
                        childList.add(child.getMenuId());
                    }
                }
                menuModelDTO.setChildList(childList);
                if (!childList.isEmpty()) {
                    menuModel.add(menuModelDTO);
                }
            }
        }
        return menuModel;
    }

    private static SessionBeanLocal lookupSessionBeanLocal() {
        try {
            Context c = new InitialContext();
            return (SessionBeanLocal) c.lookup("java:comp/env/SessionBean");
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }

    public static Map<Long, List<HrProfilePrivilage>> getMenuCashMap() {
        return menuCashMap;
    }

    public static void setMenuCashMap(Map<Long, List<HrProfilePrivilage>> menuCashMap) {
        CashHandler.menuCashMap = menuCashMap;
    }

    public static Map<Long, List<HrProfileMsg>> getAlerts() {
        return alerts;
    }

    public static void setAlerts(Map<Long, List<HrProfileMsg>> alerts) {
        CashHandler.alerts = alerts;
    }

    public static Map<Long, List<HrProfileMsg>> getDecisions() {
        return decisions;
    }

    public static void setDecisions(Map<Long, List<HrProfileMsg>> decisions) {
        CashHandler.decisions = decisions;
    }

    public static Map<Long, List<HrProfileMsg>> getMsgs() {
        return msgs;
    }

    public static void setMsgs(Map<Long, List<HrProfileMsg>> msgs) {
        CashHandler.msgs = msgs;
    }

    public static Map<Long, List<HrProfileMsg>> getTasks() {
        return tasks;
    }

    public static void setTasks(Map<Long, List<HrProfileMsg>> tasks) {
        CashHandler.tasks = tasks;
    }

    public static Map<Long, List<HrEmpMangers>> getEmpManagers() {
        return empManagers;
    }

    public static void setEmpManagers(Map<Long, List<HrEmpMangers>> empManagers) {
        CashHandler.empManagers = empManagers;
    }

    public static Map<Long, Map<Long, List<Long>>> getTamyozApprove() {
        return tamyozApprove;
    }

    public static void setTamyozApprove(Map<Long, Map<Long, List<Long>>> tamyozApprove) {
        CashHandler.tamyozApprove = tamyozApprove;
    }

    public static HrMontlySalaryCalcPeriod getHscp() {
        return hscp;
    }

    public static void setHscp(HrMontlySalaryCalcPeriod hscp) {
        CashHandler.hscp = hscp;
    }

    public static Map<Long, List<HrEmpMangers>> getMngEmployees() {
        return mngEmployees;
    }

    public static void setMngEmployees(Map<Long, List<HrEmpMangers>> mngEmployees) {
        CashHandler.mngEmployees = mngEmployees;
    }

    public static String getHeadHierachy() {
        return headHierachy;
    }

    public static void setHeadHierachy(String headHierachy) {
        CashHandler.headHierachy = headHierachy;
    }

    public static String getSalesHierachy() {
        return salesHierachy;
    }

    public static void setSalesHierachy(String salesHierachy) {
        CashHandler.salesHierachy = salesHierachy;
    }

    public static String getStoreHierarchy() {
        return storeHierarchy;
    }

    public static void setStoreHierarchy(String storeHierarchy) {
        CashHandler.storeHierarchy = storeHierarchy;
    }

    public static Map<Long, Map<Long, String>> getInOutManualTrnsMap() {
        return inOutManualTrnsMap;
    }

    public static void setInOutManualTrnsMap(Map<Long, Map<Long, String>> inOutManualTrnsMap) {
        CashHandler.inOutManualTrnsMap = inOutManualTrnsMap;
    }

    public static Map<String, FileInputStream> getEmpImageMap() {
        return empImageMap;
    }

    public static void setEmpImageMap(Map<String, FileInputStream> empImageMap) {
        CashHandler.empImageMap = empImageMap;
    }

    public static Map<Long, List<Long>> getLocationIpMapping() {
        return locationIpMapping;
    }

    public static void setLocationIpMapping(Map<Long, List<Long>> locationIpMapping) {
        CashHandler.locationIpMapping = locationIpMapping;
    }
}
