/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author data
 */
@Entity
@Table(name = "DMS_USERS", catalog = "", schema = "DMS")
@NamedQueries({
    @NamedQuery(name = "DmsUsers.findAll", query = "SELECT d FROM DmsUsers d"),
    @NamedQuery(name = "DmsUsers.findById", query = "SELECT d FROM DmsUsers d WHERE d.id = :id"),
    @NamedQuery(name = "DmsUsers.findByUserName", query = "SELECT d FROM DmsUsers d WHERE d.userName = :userName"),
    @NamedQuery(name = "DmsUsers.findByPassWord", query = "SELECT d FROM DmsUsers d WHERE d.passWord = :passWord"),
    @NamedQuery(name = "DmsUsers.findByAdmin", query = "SELECT d FROM DmsUsers d WHERE d.admin = :admin"),
    @NamedQuery(name = "DmsUsers.findByHrId", query = "SELECT d FROM DmsUsers d WHERE d.hrId = :empNo")})
public class DmsUsers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "PASS_WORD")
    private String passWord;
    @Column(name = "ADMIN")
    private String admin;
    @Column(name = "HR_ID")
    private String hrId;
    @Column(name = "DISCOUNT_PRIVILAGE")
    private String discountPrivilage;

    public DmsUsers() {
    }

    public DmsUsers(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getHrId() {
        return hrId;
    }

    public void setHrId(String hrId) {
        this.hrId = hrId;
    }

    public String getDiscountPrivilage() {
        return discountPrivilage;
    }

    public void setDiscountPrivilage(String discountPrivilage) {
        this.discountPrivilage = discountPrivilage;
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
        if (!(object instanceof DmsUsers)) {
            return false;
        }
        DmsUsers other = (DmsUsers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.DmsUsers[id=" + id + "]";
    }

}
