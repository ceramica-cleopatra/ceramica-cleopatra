/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import e.HrEmpMangers;
import e.HrLocation;
import e.HrLocationIpMapping;
import e.HrMenuTable;
import e.HrMontlySalaryCalcPeriod;
import e.HrProfileMsg;
import e.HrProfilePrivilage;
import e.HrTamyozApproveEmp;
import java.io.FileInputStream;
import java.io.IOException;
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
import org.codehaus.jackson.map.ObjectMapper;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import sb.SessionBeanLocal;

/**
 *
 * @author Administrator
 */
public class CashHandler {

    private static Map<Long, Map<Long, List<Long>>> tamyozApprove;
    public static Long tamyozApproveRefreshTime = 0L;
    public static Long empManagersRefreshTime = 0L;
    public static Long msgsRefreshTime = 0L;
    private static SessionBeanLocal sessionBean;
    public static Long hscpRefreshTime = 0L;
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd");
  //  private static Map<Long, List<Long>> locationIpMapping;
    private static Long ipLocationRefreshTime = 0L;

    public static synchronized void fillCash(Long empNo) {
        long x = System.currentTimeMillis();
        Jedis jedis = null;
        try {
            jedis = WorkerBean.pool.getResource();
            sessionBean = lookupSessionBeanLocal();
            ObjectMapper objectMapper = new ObjectMapper();
            if (!jedis.exists("menu:" + empNo)) {
                List<HrProfilePrivilage> hrProfilePrivilages = sessionBean.allProfilePrivilage(empNo);
                for (HrProfilePrivilage hrProfilePrivilage : hrProfilePrivilages) {
                    HrProfilePrivilageDTO hrProfilePrivilageDTO = new HrProfilePrivilageDTO(Long.parseLong(hrProfilePrivilage.getId() + ""), hrProfilePrivilage.getEmpNo(),
                            new HrMenuTableDTO(hrProfilePrivilage.getMenuId().getId(), hrProfilePrivilage.getMenuId().getMenuName(), hrProfilePrivilage.getMenuId().getArabicName(), hrProfilePrivilage.getMenuId().getIcon(), hrProfilePrivilage.getMenuId().getParentId(), hrProfilePrivilage.getMenuId().getMenuOrder()), hrProfilePrivilage.getArabicName());
                    try {
                        String hrProfilePrivilageAsString = objectMapper.writeValueAsString(hrProfilePrivilageDTO);
                        jedis.hset("menu:" + hrProfilePrivilage.getEmpNo(), hrProfilePrivilage.getId().toString(), hrProfilePrivilageAsString);
                        jedis.expire("menu:" + hrProfilePrivilage.getEmpNo(), Integer.MAX_VALUE);
                    } catch (IOException ex) {
                        Logger.getLogger(CashHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            if (!jedis.exists("alerts:" + empNo)) {
                List<HrProfileMsg> hrProfileMsgs = sessionBean.findAllProfilMsgs(empNo);
                for (HrProfileMsg msg : hrProfileMsgs) {
                    String hrProfileMsgAsString = null;
                    try {
                        hrProfileMsgAsString = objectMapper.writeValueAsString(msg);
                    } catch (IOException ex) {
                        Logger.getLogger(CashHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (msg.getMsgType() == 1L) {
                        jedis.hset("alerts:" + msg.getEmpNo(), msg.getEntityName() + "" + msg.getMsgType() + "" + msg.getEmpNo() + "" + msg.getMsgId(), hrProfileMsgAsString);
                        jedis.expire("alerts:" + msg.getEmpNo(), Integer.MAX_VALUE);
                    } else if (msg.getMsgType() == 2L) {
                        jedis.hset("msgs:" + msg.getEmpNo(), msg.getEntityName() + "" + msg.getMsgType() + "" + msg.getEmpNo() + "" + msg.getMsgId(), hrProfileMsgAsString);
                        jedis.expire("msgs:" + msg.getEmpNo(), Integer.MAX_VALUE);
                    } else if (msg.getMsgType() == 3L) {
                        jedis.hset("decisions:" + msg.getEmpNo(), msg.getEntityName() + "" + msg.getMsgType() + "" + msg.getEmpNo() + "" + msg.getMsgId(), hrProfileMsgAsString);
                        jedis.expire("decisions:" + msg.getEmpNo(), Integer.MAX_VALUE);
                    } else if (msg.getMsgType() == 4L) {
                        jedis.hset("tasks:" + msg.getEmpNo(), msg.getEntityName() + "" + msg.getMsgType() + "" + msg.getEmpNo() + "" + msg.getMsgId(), hrProfileMsgAsString);
                        jedis.expire("tasks:" + msg.getEmpNo(), Integer.MAX_VALUE);
                    }

                }
            }


//        if (System.currentTimeMillis() - empManagersRefreshTime >= 18000000) {
//            for (HrEmpInfo hrEmpInfo : hrEmpInfoList) {
//                Pipeline p = jedis.pipelined();
//                p.del("managers:" + hrEmpInfo.getEmpNo());
//                p.del("employees:" + hrEmpInfo.getEmpNo());
//                p.sync();
//            }
//            empManagersRefreshTime = System.currentTimeMillis();
//        }

            if (!jedis.exists("managers:" + empNo)) {
                List<HrEmpMangers> hrEmpMangers = sessionBean.findAllEmpManagers(empNo);
                for (HrEmpMangers empMng : hrEmpMangers) {
                    if (empNo != empMng.getMngNo()) {
                        jedis.hset("managers:" + empNo, empMng.getEmpNo() + "" + empMng.getMngNo(), empMng.getMngNo().toString());
                        jedis.expire("managers:" + empNo, Integer.MAX_VALUE);
                    }
                }
                List<HrEmpMangers> mangersEmployees = sessionBean.findAllMngEmployees(empNo);
                for (HrEmpMangers mngEmp : mangersEmployees) {
                    jedis.hset("employees:" + empNo, mngEmp.getEmpNo() + "" + mngEmp.getMngNo(), mngEmp.getEmpNo() + "");
                    jedis.expire("employees:" + empNo, Integer.MAX_VALUE);
                }
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


            if (!jedis.exists("hscp")) {
                HrMontlySalaryCalcPeriod hscp = new HrMontlySalaryCalcPeriod();
                java.util.Calendar cal = java.util.Calendar.getInstance();
                if (Long.parseLong(sdf.format(new Date())) > 15) {
                    cal.add(Calendar.DATE, -15);
                    hscp = sessionBean.find_month_period(cal.getTime());
                } else {
                    hscp = sessionBean.find_month_period(new Date());
                }
                try {
                    jedis.set("hscp", new ObjectMapper().writeValueAsString(hscp));
                    jedis.expire("hscp", Integer.MAX_VALUE);
                } catch (IOException ex) {
                    Logger.getLogger(CashHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }


//            if (locationIpMapping
//                    == null || System.currentTimeMillis() - ipLocationRefreshTime >= 10800000L) {
//                locationIpMapping = new HashMap<Long, List<Long>>();
//                List<HrLocationIpMapping> ipLocationMapping = sessionBean.findAllLOocationIpMapping();
//                for (HrLocationIpMapping hrLocationIpMapping : ipLocationMapping) {
//                    if (locationIpMapping.containsKey(hrLocationIpMapping.getHrLocationIpMappingPK().getIp())) {
//                        locationIpMapping.get(hrLocationIpMapping.getHrLocationIpMappingPK().getIp()).add(hrLocationIpMapping.getHrLocationIpMappingPK().getLocId());
//                    } else {
//                        List<Long> locationList = new ArrayList<Long>();
//                        locationList.add(hrLocationIpMapping.getHrLocationIpMappingPK().getLocId());
//                        locationIpMapping.put(hrLocationIpMapping.getHrLocationIpMappingPK().getIp(), locationList);
//                    }
//                }
//                ipLocationRefreshTime = System.currentTimeMillis();
//            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
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

    public static Long getEmpManagersRefreshTime() {
        return empManagersRefreshTime;
    }

    public static void setEmpManagersRefreshTime(Long empManagersRefreshTime) {
        CashHandler.empManagersRefreshTime = empManagersRefreshTime;
    }

    public static Long getHscpRefreshTime() {
        return hscpRefreshTime;
    }

    public static void setHscpRefreshTime(Long hscpRefreshTime) {
        CashHandler.hscpRefreshTime = hscpRefreshTime;
    }

    public static Long getIpLocationRefreshTime() {
        return ipLocationRefreshTime;
    }

    public static void setIpLocationRefreshTime(Long ipLocationRefreshTime) {
        CashHandler.ipLocationRefreshTime = ipLocationRefreshTime;
    }

//    public static Map<Long, List<Long>> getLocationIpMapping() {
//        return locationIpMapping;
//    }
//
//    public static void setLocationIpMapping(Map<Long, List<Long>> locationIpMapping) {
//        CashHandler.locationIpMapping = locationIpMapping;
//    }

    public static Long getMsgsRefreshTime() {
        return msgsRefreshTime;
    }

    public static void setMsgsRefreshTime(Long msgsRefreshTime) {
        CashHandler.msgsRefreshTime = msgsRefreshTime;
    }

    public static SessionBeanLocal getSessionBean() {
        return sessionBean;
    }

    public static void setSessionBean(SessionBeanLocal sessionBean) {
        CashHandler.sessionBean = sessionBean;
    }

    public static Map<Long, Map<Long, List<Long>>> getTamyozApprove() {
        return tamyozApprove;
    }

    public static void setTamyozApprove(Map<Long, Map<Long, List<Long>>> tamyozApprove) {
        CashHandler.tamyozApprove = tamyozApprove;
    }

    public static Long getTamyozApproveRefreshTime() {
        return tamyozApproveRefreshTime;
    }

    public static void setTamyozApproveRefreshTime(Long tamyozApproveRefreshTime) {
        CashHandler.tamyozApproveRefreshTime = tamyozApproveRefreshTime;
    }
}
