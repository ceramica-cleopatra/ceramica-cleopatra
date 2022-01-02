/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class OrdrDtDTO {
private Double qty;
private Double qtyOut;
private Double qtyCanceled;
private String noCTone;
private Double rmnQty;
private Double actQty;
private String itemCode;
private String crmkSehy;
private boolean selectedItem;
    public OrdrDtDTO() {
    }

    public String getNoCTone() {
        return noCTone;
    }

    public void setNoCTone(String noCTone) {
        this.noCTone = noCTone;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Double getQtyCanceled() {
        return qtyCanceled;
    }

    public void setQtyCanceled(Double qtyCanceled) {
        this.qtyCanceled = qtyCanceled;
    }

    public Double getQtyOut() {
        return qtyOut;
    }

    public void setQtyOut(Double qtyOut) {
        this.qtyOut = qtyOut;
    }

    public Double getRmnQty() {
        return rmnQty;
    }

    public void setRmnQty(Double rmnQty) {
        this.rmnQty = rmnQty;
    }

    public Double getActQty() {
        return actQty;
    }

    public void setActQty(Double actQty) {
        this.actQty = actQty;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getCrmkSehy() {
        return crmkSehy;
    }

    public void setCrmkSehy(String crmkSehy) {
        this.crmkSehy = crmkSehy;
    }

    public boolean isSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(boolean selectedItem) {
        this.selectedItem = selectedItem;
    }

    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrdrDtDTO other = (OrdrDtDTO) obj;
        if (this.itemCode != other.itemCode && (this.itemCode == null || !this.itemCode.equals(other.itemCode))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.itemCode != null ? this.itemCode.hashCode() : 0);
        return hash;
    }


}
