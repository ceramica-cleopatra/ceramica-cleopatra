/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrMenuTable;
import e.HrProfilePrivilage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import sb.SessionBeanLocal;

/**
 *
 * @author Administrator
 */
@ManagedBean
@ViewScoped
public class MenuBean {

    @EJB
    private SessionBeanLocal sessionBean;
    private List<MenuModelDTO> menuModel;
    private String usercode;

    /** Creates a new instance of MenuBeanTest */
    @PostConstruct
    public void init() {
//        menuModel=(List<MenuModelDTO>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("menuBean");
        if (menuModel == null || menuModel.isEmpty()) {
        menuModel = new ArrayList<MenuModelDTO>();
        usercode = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usercode");
        if (CashHandler.getMenuCashMap() != null && CashHandler.getMenuCashMap().size() > 0 && CashHandler.getMenuCashMap().containsKey(Long.parseLong(usercode))) {
        for (HrProfilePrivilage parent : CashHandler.getMenuCashMap().get(Long.parseLong(usercode))) {
        if (parent.getMenuId().getParentId() == null) {
        MenuModelDTO menuModelDTO=new MenuModelDTO();
        menuModelDTO.setParent(parent.getMenuId());
        List<HrMenuTable> childList = new ArrayList<HrMenuTable>();
        for (HrProfilePrivilage child : CashHandler.getMenuCashMap().get(Long.parseLong(usercode))) {
        if (child.getMenuId().getParentId() != null && parent.getMenuId().getId().equals(child.getMenuId().getParentId())) {
        childList.add(child.getMenuId());
        }
        }
        menuModelDTO.setChildList(childList);
        if(!childList.isEmpty())
        menuModel.add(menuModelDTO);
        }
        }

        }



       }

    }

    public List<MenuModelDTO> getMenuModel() {
        return menuModel;
    }

    public void setMenuModel(List<MenuModelDTO> menuModel) {
        this.menuModel = menuModel;
    }

    public MenuBean() {
    }
}
