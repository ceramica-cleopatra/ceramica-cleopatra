/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.CrmkBranch;
import e.CrmkOrdrHd;
import e.HrPersonalOrdrRequest;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import sb.SessionBeanRemote;

/**
 *
 * @author Administrator
 */
@ManagedBean
@RequestScoped
public class PersonalOrdrRequest {
    @EJB
    private SessionBeanRemote sessionBean;
    private List<SelectItem> crmkBranchList=new ArrayList<SelectItem>();
    private Long brn_id;
    private Long prd_id;
    private Character crmk_sehy;
    private Long ordr_no;
    private CrmkOrdrHd crmkOrdrHd;
    private List<HrPersonalOrdrRequest> hrPersonalOrdrRequests=new ArrayList<HrPersonalOrdrRequest>();
    public Character getCrmk_sehy() {
        return crmk_sehy;
    }

    public List<HrPersonalOrdrRequest> getHrPersonalOrdrRequests() {
        FacesContext fc=FacesContext.getCurrentInstance();
        ValueBinding vb = fc.getApplication().createValueBinding("#{loginBean.username}");
        Long usercode = Long.parseLong(vb.getValue(fc).toString());
        hrPersonalOrdrRequests=sessionBean.getPersonalOrdrRequests(usercode);
        return hrPersonalOrdrRequests;
    }

    public void setHrPersonalOrdrRequests(List<HrPersonalOrdrRequest> hrPersonalOrdrRequests) {
        this.hrPersonalOrdrRequests = hrPersonalOrdrRequests;
    }

    public void setCrmk_sehy(Character crmk_sehy) {
        this.crmk_sehy = crmk_sehy;
    }
    
    /** Creates a new instance of PersonalOrdrRequest */
    public PersonalOrdrRequest() {
    }

    public Long getBrn_id() {
        return brn_id;
    }

    public void setBrn_id(Long brn_id) {
        this.brn_id = brn_id;
    }

    public Long getPrd_id() {
        return prd_id;
    }

    public void setPrd_id(Long prd_id) {
        this.prd_id = prd_id;
    }


    public List<SelectItem> getCrmkBranchList() {
    for(CrmkBranch crmkBranch : sessionBean.getShow())
    {
    crmkBranchList.add(new SelectItem(crmkBranch.getId(),crmkBranch.getName()));
    }
        return crmkBranchList;
    }

    public void setCrmkBranchList(List<SelectItem> crmkBranchList) {
        this.crmkBranchList = crmkBranchList;
    }

    public Long getOrdr_no() {
        return ordr_no;
    }

    public void setOrdr_no(Long ordr_no) {
        this.ordr_no = ordr_no;
    }
   
   public void addRequest(ActionEvent ae)
    {
   FacesContext fc=FacesContext.getCurrentInstance();

   if(brn_id==null || prd_id==null || crmk_sehy == null || ordr_no == null)
   {
   fc.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL, "Œÿ√","ÌÃ» ≈œŒ«· Ã„Ì⁄ «·»Ì«‰« "));
   return;
   }
   if(sessionBean.chkPersnalOrdrExist(brn_id, ordr_no, prd_id, crmk_sehy)==0L)
   {
   fc.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL, "Œÿ√","·« ÌÊÃœ ÿ·»Ì… »Â–Â «·»Ì«‰« "));
   return;
   }

    ValueBinding vb = fc.getApplication().createValueBinding("#{loginBean.username}");
    Long usercode = Long.parseLong(vb.getValue(fc).toString());
   crmkOrdrHd=sessionBean.getPersonalOrdr(brn_id, ordr_no, prd_id, crmk_sehy);
   if(sessionBean.chkOrdrExist(crmkOrdrHd.getId())>0L)
   {
   fc.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL, "Œÿ√"," „ ≈œŒ«· Â–« «·ÿ·» „‰ ﬁ»·"));
   return;
   }
   HrPersonalOrdrRequest personalOrdrRequest=new HrPersonalOrdrRequest();
   personalOrdrRequest.setBrnId(sessionBean.getCrmkBranchById(brn_id));
   personalOrdrRequest.setCrmkSehy(crmk_sehy);
   personalOrdrRequest.setEmpNo(sessionBean.findempbyid(usercode));
   personalOrdrRequest.setOrdrId(crmkOrdrHd.getId());
   personalOrdrRequest.setTrnsDate(new Date());
   personalOrdrRequest.setOrdrHandNo(crmkOrdrHd.getHandNo());
   personalOrdrRequest.setOrdrNo(ordr_no);
   personalOrdrRequest.setOrdrTrnsDate(crmkOrdrHd.getTrnsDate());
   personalOrdrRequest.setPrdrPrdId(prd_id);
   if(crmk_sehy.equals('C'))
   {
   personalOrdrRequest.setQty(sessionBean.getCrmkQtySum(crmkOrdrHd.getId()));
   personalOrdrRequest.setTotVal(sessionBean.getTotalCrmkOrdrValue(crmkOrdrHd.getId()));
   }
   else if(crmk_sehy.equals('D'))
   {
       personalOrdrRequest.setQty(sessionBean.getDcreQtySum(crmkOrdrHd.getId()));
       personalOrdrRequest.setTotVal(sessionBean.getTotalDcreOrdrValue(crmkOrdrHd.getId()));
   }
   else
   {
   personalOrdrRequest.setQty(sessionBean.getSehyQtySum(crmkOrdrHd.getId()));
   personalOrdrRequest.setTotVal(sessionBean.getTotalSehyOrdrValue(crmkOrdrHd.getId()));
   }
   sessionBean.persistPersonalOrdr(personalOrdrRequest);
   fc.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, " „ »‰Ã«Õ"," „ Õ›Ÿ «·ÿ·» »‰Ã«Õ"));
   }
 
}
