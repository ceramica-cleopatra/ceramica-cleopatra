/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrMenuTable;
import e.HrProfilePrivilage;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import redis.clients.jedis.Jedis;
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
        if (menuModel == null || menuModel.isEmpty()) {
            menuModel = new ArrayList<MenuModelDTO>();
            usercode = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usercode");
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> entries = null;
            Jedis jedis = null;
            try {
                jedis = WorkerBean.pool.getResource();
                entries = jedis.hgetAll("menu:" + usercode);
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
            for (String key : entries.keySet()) {
                HrProfilePrivilage parent = null;
                try {
                    parent = objectMapper.readValue(entries.get(key), HrProfilePrivilage.class);
                } catch (IOException ex) {
                    Logger.getLogger(MenuBean.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (parent != null && parent.getMenuId() != null && parent.getMenuId().getParentId() == null) {
                    MenuModelDTO menuModelDTO = new MenuModelDTO();
                    menuModelDTO.setParent(parent.getMenuId());
                    List<HrMenuTable> childList = new ArrayList<HrMenuTable>();
                    for (String childKey : entries.keySet()) {
                        HrProfilePrivilage child = null;
                        try {
                            child = objectMapper.readValue(entries.get(childKey), HrProfilePrivilage.class);
                        } catch (IOException ex) {
                            Logger.getLogger(MenuBean.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (child != null && child.getMenuId() != null && child.getMenuId().getParentId() != null
                                && parent.getMenuId().getId().equals(child.getMenuId().getParentId())) {
                            childList.add(child.getMenuId());
                        }
                    }
                    Collections.sort(childList, new MenuComparator());
                    menuModelDTO.setChildList(childList);
                    if (!childList.isEmpty()) {
                        menuModel.add(menuModelDTO);
                    }
                }
            }

        }

        Collections.sort(menuModel, new MenuModelComparator());

    }

    public List<MenuModelDTO> getMenuModel() {
        for (MenuModelDTO menuModelDTO : menuModel) {
            Collections.sort(menuModelDTO.getChildList(), new MenuComparator());
        }
        Collections.sort(menuModel, new MenuModelComparator());
        return menuModel;
    }

    public void setMenuModel(List<MenuModelDTO> menuModel) {
        this.menuModel = menuModel;
    }

    public MenuBean() {
    }
}
