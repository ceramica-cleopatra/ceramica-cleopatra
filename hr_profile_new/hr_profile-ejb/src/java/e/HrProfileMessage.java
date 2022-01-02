/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_PROFILE_MESSAGE", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrProfileMessage.findAll", query = "SELECT h FROM HrProfileMessage h where h.img is null or h.img='N'"),
    @NamedQuery(name = "HrProfileMessage.findAllImg", query = "select o from HrProfileMessage o where o.img='Y'"),
    @NamedQuery(name = "HrProfileMessage.findById", query = "SELECT h FROM HrProfileMessage h WHERE h.id = :id"),
    @NamedQuery(name = "HrProfileMessage.findByMsgText", query = "SELECT h FROM HrProfileMessage h WHERE h.msgText = :msgText"),
    @NamedQuery(name = "HrProfileMessage.findByImg", query = "SELECT h FROM HrProfileMessage h WHERE h.img = :img")})
public class HrProfileMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "MSG_TEXT")
    private String msgText;
    @Column(name = "IMG")
    private Character img;

    public HrProfileMessage() {
    }

    public HrProfileMessage(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    public Character getImg() {
        return img;
    }

    public void setImg(Character img) {
        this.img = img;
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
        if (!(object instanceof HrProfileMessage)) {
            return false;
        }
        HrProfileMessage other = (HrProfileMessage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrProfileMessage[id=" + id + "]";
    }

}
