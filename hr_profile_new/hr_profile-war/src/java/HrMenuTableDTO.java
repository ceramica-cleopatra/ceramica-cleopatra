/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DEV
 */
public class HrMenuTableDTO {
    private Long id;
    private String menuName;
    private String arabicName;
    private String icon;
    private Long parentId;
    private Long menuOrder;

    public HrMenuTableDTO(Long id, String menuName, String arabicName, String icon, Long parentId, Long menuOrder) {
        this.id = id;
        this.menuName = menuName;
        this.arabicName = arabicName;
        this.icon = icon;
        this.parentId = parentId;
        this.menuOrder = menuOrder;
    }

    
    public String getArabicName() {
        return arabicName;
    }

    public void setArabicName(String arabicName) {
        this.arabicName = arabicName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Long getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Long menuOrder) {
        this.menuOrder = menuOrder;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }


}
