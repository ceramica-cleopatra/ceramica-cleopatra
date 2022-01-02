/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author a.abbas
 */
@Entity
@Table(name = "DMS_TRANSPORT_ORDR_PARENT", catalog = "", schema = "DMS")
@NamedQueries({
    @NamedQuery(name = "DmsTransportOrdrParent.findAll", query = "SELECT d FROM DmsTransportOrdrParent d"),
    @NamedQuery(name = "DmsTransportOrdrParent.findMaxId", query = "SELECT max(d.id) FROM DmsTransportOrdrParent d"),
    @NamedQuery(name = "DmsTransportOrdrParent.findById", query = "SELECT d FROM DmsTransportOrdrParent d WHERE d.id = :id"),
    @NamedQuery(name = "DmsTransportOrdrParent.findByDiscount", query = "SELECT d FROM DmsTransportOrdrParent d WHERE d.discount = :discount"),
    @NamedQuery(name = "DmsTransportOrdrParent.findByDiscountPercent", query = "SELECT d FROM DmsTransportOrdrParent d WHERE d.discountPercent = :discountPercent"),
    @NamedQuery(name = "DmsTransportOrdrParent.findByPrice", query = "SELECT d FROM DmsTransportOrdrParent d WHERE d.price = :price")})
public class DmsTransportOrdrParent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "DISCOUNT")
    private Double discount;
    @Column(name = "DISCOUNT_PERCENT")
    private Double discountPercent;
    @Column(name = "LIST_PRICE")
    private Double price;

    public DmsTransportOrdrParent() {
    }

    public DmsTransportOrdrParent(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmsTransportOrdrParent)) {
            return false;
        }
        DmsTransportOrdrParent other = (DmsTransportOrdrParent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.DmsTransportOrdrParent[id=" + id + "]";
    }

}
