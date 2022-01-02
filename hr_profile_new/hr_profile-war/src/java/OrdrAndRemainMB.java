/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.CrmkOrdrAndRmnWithoutSrf;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import sb.SessionBeanLocal;

/**
 *
 * @author Administrator
 */
@ManagedBean(name = "ordrAndRemainMB", eager = true)
@SessionScoped
public class OrdrAndRemainMB implements Serializable {

    @EJB
    private SessionBeanLocal sessionBean;

    /** Creates a new instance of OrdrAndRemainMB */
    public OrdrAndRemainMB() {
    }

    public List<CrmkOrdrAndRmnWithoutSrfDTO> getFormattedResults(int first, int pageSize, Map<String, Object> filters, int flag,int rowCount) {
        List<CrmkOrdrAndRmnWithoutSrf> ordrsAndRemains = null;
        if (flag == 1) {
            ordrsAndRemains = sessionBean.findOrdrAndRemainWithoutSrfPerEmp(first, pageSize, filters,rowCount);
        } else if(flag == 2) {
            ordrsAndRemains = sessionBean.findAllRequestsPerBrn(first, pageSize, filters,rowCount);
        } 
        List<CrmkOrdrAndRmnWithoutSrfDTO> crmkOrdrAndRmnWithoutSrfDTOs = new ArrayList<CrmkOrdrAndRmnWithoutSrfDTO>();

        for (CrmkOrdrAndRmnWithoutSrf crmkOrdrAndRmnWithoutSrf : ordrsAndRemains) {
            CrmkOrdrAndRmnWithoutSrfDTO crmkOrdrAndRmnWithoutSrfDTO = new CrmkOrdrAndRmnWithoutSrfDTO();
            crmkOrdrAndRmnWithoutSrfDTO.setClntName(crmkOrdrAndRmnWithoutSrf.getClntName());
            if (crmkOrdrAndRmnWithoutSrfDTOs.contains(crmkOrdrAndRmnWithoutSrfDTO)) {
                crmkOrdrAndRmnWithoutSrfDTO = crmkOrdrAndRmnWithoutSrfDTOs.get(crmkOrdrAndRmnWithoutSrfDTOs.indexOf(crmkOrdrAndRmnWithoutSrfDTO));
                OrdrHdDTO ordrHdDTO = new OrdrHdDTO();
                ordrHdDTO.setOrdrId(crmkOrdrAndRmnWithoutSrf.getOrdrId());
                if (crmkOrdrAndRmnWithoutSrfDTO.getOrdrHdDTOList() != null && crmkOrdrAndRmnWithoutSrfDTO.getOrdrHdDTOList().contains(ordrHdDTO)) {
                    ordrHdDTO = crmkOrdrAndRmnWithoutSrfDTO.getOrdrHdDTOList().get(crmkOrdrAndRmnWithoutSrfDTO.getOrdrHdDTOList().indexOf(ordrHdDTO));
                    ordrHdDTO.getDrivers().add(crmkOrdrAndRmnWithoutSrf.getName());
                    OrdrDtDTO ordrDtDTO = new OrdrDtDTO();
                    ordrDtDTO.setItemCode(crmkOrdrAndRmnWithoutSrf.getItemcode());
                    if (ordrHdDTO.getOrdrDtDTOList() == null || !ordrHdDTO.getOrdrDtDTOList().contains(ordrDtDTO)) {
                        ordrDtDTO.setNoCTone(crmkOrdrAndRmnWithoutSrf.getNoCTone());
                        ordrDtDTO.setQty(crmkOrdrAndRmnWithoutSrf.getQty());
                        ordrDtDTO.setQtyCanceled(crmkOrdrAndRmnWithoutSrf.getCanQty());
                        ordrDtDTO.setQtyOut(crmkOrdrAndRmnWithoutSrf.getOutQty());
                        ordrDtDTO.setRmnQty(crmkOrdrAndRmnWithoutSrf.getRemain());
                        ordrDtDTO.setActQty(crmkOrdrAndRmnWithoutSrf.getActQty());
                        ordrDtDTO.setCrmkSehy(crmkOrdrAndRmnWithoutSrf.getCrmkSehy());
                        if (ordrHdDTO.getOrdrDtDTOList() != null) {
                            ordrHdDTO.getOrdrDtDTOList().add(ordrDtDTO);
                        } else {
                            List<OrdrDtDTO> l = new ArrayList<OrdrDtDTO>();
                            l.add(ordrDtDTO);
                            ordrHdDTO.setOrdrDtDTOList(l);
                        }
                    }
                } else {
                    ordrHdDTO.setCRMK_SEHY(crmkOrdrAndRmnWithoutSrf.getCrmkSehy());
                    ordrHdDTO.setEmpName(crmkOrdrAndRmnWithoutSrf.getEmpName());
                    ordrHdDTO.setGrpName(crmkOrdrAndRmnWithoutSrf.getGrpName());
                    ordrHdDTO.setHAND_NO(crmkOrdrAndRmnWithoutSrf.getHandNo());
                    ordrHdDTO.setOrdrNo(crmkOrdrAndRmnWithoutSrf.getNo());
                    ordrHdDTO.setShowName(crmkOrdrAndRmnWithoutSrf.getShowName());
                    ordrHdDTO.setTrnsDate(crmkOrdrAndRmnWithoutSrf.getTrnsDate());
                    ordrHdDTO.setPrdId(crmkOrdrAndRmnWithoutSrf.getPrdId());
                    ordrHdDTO.setRmnPrdId(crmkOrdrAndRmnWithoutSrf.getRmnPrdId());
                    ordrHdDTO.setRmnTrnsDate(crmkOrdrAndRmnWithoutSrf.getRmnTrnsDate());
                    ordrHdDTO.setRmnNo(crmkOrdrAndRmnWithoutSrf.getRmnNo());
                    ordrHdDTO.setRmnId(crmkOrdrAndRmnWithoutSrf.getRemainId());
                    ordrHdDTO.setStoreName(crmkOrdrAndRmnWithoutSrf.getStr());
                    ordrHdDTO.setTrnsNo(crmkOrdrAndRmnWithoutSrf.getTrnsNo());
                    ordrHdDTO.setRmnHandNo(crmkOrdrAndRmnWithoutSrf.getRmnHandNo());
                    ordrHdDTO.setClntName(crmkOrdrAndRmnWithoutSrf.getClntName());
                    ordrHdDTO.setClntPhone(crmkOrdrAndRmnWithoutSrf.getPhone());
                    ordrHdDTO.setAddress(crmkOrdrAndRmnWithoutSrf.getAddress());
                    ordrHdDTO.setArea(crmkOrdrAndRmnWithoutSrf.getArea());
                    ordrHdDTO.setCity(crmkOrdrAndRmnWithoutSrf.getCity());
                    ordrHdDTO.setReqId(crmkOrdrAndRmnWithoutSrf.getCrmkRmnPrintRequest());
                    ordrHdDTO.setTrgtClntName(crmkOrdrAndRmnWithoutSrf.getTrgtClntName());
                    ordrHdDTO.setTrgtDriverId(crmkOrdrAndRmnWithoutSrf.getTrgtDriverId());
                    ordrHdDTO.setTrgtDriverName(crmkOrdrAndRmnWithoutSrf.getTrgtDriverName());
                    ordrHdDTO.setEmpRequestedId(crmkOrdrAndRmnWithoutSrf.getEmpRequestedId());
                    ordrHdDTO.setEmpRequestedName(crmkOrdrAndRmnWithoutSrf.getEmpRequestedName());
                    ordrHdDTO.setBrnRequestedName(crmkOrdrAndRmnWithoutSrf.getBrnRequestedName());
                    ordrHdDTO.setTrgtClntPhone(crmkOrdrAndRmnWithoutSrf.getTrgtClntPhone());
                    ordrHdDTO.setTrgtDriverPhone(crmkOrdrAndRmnWithoutSrf.getTrgtDriverPhone());
                    ordrHdDTO.setTrgtBrnId(crmkOrdrAndRmnWithoutSrf.getTrgtBrnId());
                    Set<String> drivers = new HashSet<String>();
                    drivers.add(crmkOrdrAndRmnWithoutSrf.getName());
                    ordrHdDTO.setDrivers(drivers);
                    List<OrdrDtDTO> list = new ArrayList<OrdrDtDTO>();
                    OrdrDtDTO ordrDtDTO = new OrdrDtDTO();
                    ordrDtDTO.setNoCTone(crmkOrdrAndRmnWithoutSrf.getNoCTone());
                    ordrDtDTO.setQty(crmkOrdrAndRmnWithoutSrf.getQty());
                    ordrDtDTO.setQtyCanceled(crmkOrdrAndRmnWithoutSrf.getCanQty());
                    ordrDtDTO.setQtyOut(crmkOrdrAndRmnWithoutSrf.getOutQty());
                    ordrDtDTO.setRmnQty(crmkOrdrAndRmnWithoutSrf.getRemain());
                    ordrDtDTO.setActQty(crmkOrdrAndRmnWithoutSrf.getActQty());
                    ordrDtDTO.setItemCode(crmkOrdrAndRmnWithoutSrf.getItemcode());
                    ordrDtDTO.setCrmkSehy(crmkOrdrAndRmnWithoutSrf.getCrmkSehy());
                    list.add(ordrDtDTO);
                    ordrHdDTO.setOrdrDtDTOList(list);
                    if (crmkOrdrAndRmnWithoutSrfDTO.getOrdrHdDTOList() == null) {
                        List<OrdrHdDTO> l = new ArrayList<OrdrHdDTO>();
                        l.add(ordrHdDTO);
                        crmkOrdrAndRmnWithoutSrfDTO.setOrdrHdDTOList(l);
                    } else {
                        crmkOrdrAndRmnWithoutSrfDTO.getOrdrHdDTOList().add(ordrHdDTO);
                    }
                }
            } else {
                crmkOrdrAndRmnWithoutSrfDTO.setPhone(crmkOrdrAndRmnWithoutSrf.getPhone());
                OrdrHdDTO ordrHdDTO = new OrdrHdDTO();
                ordrHdDTO.setOrdrId(crmkOrdrAndRmnWithoutSrf.getOrdrId());
                ordrHdDTO.setCRMK_SEHY(crmkOrdrAndRmnWithoutSrf.getCrmkSehy());
                ordrHdDTO.setEmpName(crmkOrdrAndRmnWithoutSrf.getEmpName());
                ordrHdDTO.setGrpName(crmkOrdrAndRmnWithoutSrf.getGrpName());
                ordrHdDTO.setHAND_NO(crmkOrdrAndRmnWithoutSrf.getHandNo());
                ordrHdDTO.setOrdrNo(crmkOrdrAndRmnWithoutSrf.getNo());
                ordrHdDTO.setShowName(crmkOrdrAndRmnWithoutSrf.getShowName());
                ordrHdDTO.setTrnsDate(crmkOrdrAndRmnWithoutSrf.getTrnsDate());
                ordrHdDTO.setPrdId(crmkOrdrAndRmnWithoutSrf.getPrdId());
                ordrHdDTO.setRmnPrdId(crmkOrdrAndRmnWithoutSrf.getRmnPrdId());
                ordrHdDTO.setRmnTrnsDate(crmkOrdrAndRmnWithoutSrf.getRmnTrnsDate());
                ordrHdDTO.setRmnNo(crmkOrdrAndRmnWithoutSrf.getRmnNo());
                ordrHdDTO.setRmnId(crmkOrdrAndRmnWithoutSrf.getRemainId());
                ordrHdDTO.setStoreName(crmkOrdrAndRmnWithoutSrf.getStr());
                ordrHdDTO.setTrnsNo(crmkOrdrAndRmnWithoutSrf.getTrnsNo());
                ordrHdDTO.setRmnHandNo(crmkOrdrAndRmnWithoutSrf.getRmnHandNo());
                ordrHdDTO.setClntPhone(crmkOrdrAndRmnWithoutSrf.getPhone());
                ordrHdDTO.setClntName(crmkOrdrAndRmnWithoutSrf.getClntName());
                ordrHdDTO.setAddress(crmkOrdrAndRmnWithoutSrf.getAddress());
                ordrHdDTO.setArea(crmkOrdrAndRmnWithoutSrf.getArea());
                ordrHdDTO.setCity(crmkOrdrAndRmnWithoutSrf.getCity());
                ordrHdDTO.setReqId(crmkOrdrAndRmnWithoutSrf.getCrmkRmnPrintRequest());
                ordrHdDTO.setTrgtClntName(crmkOrdrAndRmnWithoutSrf.getTrgtClntName());
                ordrHdDTO.setTrgtDriverId(crmkOrdrAndRmnWithoutSrf.getTrgtDriverId());
                ordrHdDTO.setTrgtDriverName(crmkOrdrAndRmnWithoutSrf.getTrgtDriverName());
                ordrHdDTO.setEmpRequestedId(crmkOrdrAndRmnWithoutSrf.getEmpRequestedId());
                ordrHdDTO.setEmpRequestedName(crmkOrdrAndRmnWithoutSrf.getEmpRequestedName());
                ordrHdDTO.setBrnRequestedName(crmkOrdrAndRmnWithoutSrf.getBrnRequestedName());
                ordrHdDTO.setTrgtClntPhone(crmkOrdrAndRmnWithoutSrf.getTrgtClntPhone());
                ordrHdDTO.setTrgtDriverPhone(crmkOrdrAndRmnWithoutSrf.getTrgtDriverPhone());
                ordrHdDTO.setTrgtBrnId(crmkOrdrAndRmnWithoutSrf.getTrgtBrnId());
                Set<String> drivers = new HashSet<String>();
                drivers.add(crmkOrdrAndRmnWithoutSrf.getName());
                ordrHdDTO.setDrivers(drivers);
                OrdrDtDTO ordrDtDTO = new OrdrDtDTO();
                ordrDtDTO.setNoCTone(crmkOrdrAndRmnWithoutSrf.getNoCTone());
                ordrDtDTO.setQty(crmkOrdrAndRmnWithoutSrf.getQty());
                ordrDtDTO.setQtyCanceled(crmkOrdrAndRmnWithoutSrf.getCanQty());
                ordrDtDTO.setQtyOut(crmkOrdrAndRmnWithoutSrf.getOutQty());
                ordrDtDTO.setRmnQty(crmkOrdrAndRmnWithoutSrf.getRemain());
                ordrDtDTO.setActQty(crmkOrdrAndRmnWithoutSrf.getActQty());
                ordrDtDTO.setItemCode(crmkOrdrAndRmnWithoutSrf.getItemcode());
                ordrDtDTO.setCrmkSehy(crmkOrdrAndRmnWithoutSrf.getCrmkSehy());
                List<OrdrDtDTO> list = new ArrayList<OrdrDtDTO>();
                list.add(ordrDtDTO);
                ordrHdDTO.setOrdrDtDTOList(list);
                if (crmkOrdrAndRmnWithoutSrfDTO.getOrdrHdDTOList() == null) {
                    List<OrdrHdDTO> l = new ArrayList<OrdrHdDTO>();
                    l.add(ordrHdDTO);
                    crmkOrdrAndRmnWithoutSrfDTO.setOrdrHdDTOList(l);
                } else {
                    crmkOrdrAndRmnWithoutSrfDTO.getOrdrHdDTOList().add(ordrHdDTO);

                }
                crmkOrdrAndRmnWithoutSrfDTOs.add(crmkOrdrAndRmnWithoutSrfDTO);
            }
        }
        return crmkOrdrAndRmnWithoutSrfDTOs;
    }
}
