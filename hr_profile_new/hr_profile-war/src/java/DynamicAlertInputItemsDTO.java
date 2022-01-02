/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author data
 */
public class DynamicAlertInputItemsDTO {
    private Long itemId;
    private String itemType;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

DynamicAlertInputItemsDTO(Long itemId,String itemType){
    this.itemId=itemId;
    this.itemType=itemType;
}
    
}
