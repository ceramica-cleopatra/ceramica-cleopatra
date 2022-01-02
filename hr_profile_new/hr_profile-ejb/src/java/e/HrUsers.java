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
@Table(name = "HR_USERS", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrUsers.findAll", query = "SELECT h FROM HrUsers h"),
    @NamedQuery(name = "HrUsers.findById", query = "SELECT h FROM HrUsers h WHERE h.id = :id"),
    @NamedQuery(name = "HrUsers.findByHrUserName", query = "SELECT h FROM HrUsers h WHERE h.userName = :userName and h.profileAccess='Y'"),
    @NamedQuery(name = "HrUsers.findByPassword", query = "SELECT h FROM HrUsers h WHERE h.password = :password"),
    @NamedQuery(name = "HrUsers.findByArabicName", query = "SELECT h FROM HrUsers h WHERE h.arabicName = :arabicName"),
    @NamedQuery(name = "HrUsers.findByMiniAdmin", query = "SELECT h FROM HrUsers h WHERE h.miniAdmin = :miniAdmin"),
    @NamedQuery(name = "HrUsers.findByUnderMiniAdmin", query = "SELECT h FROM HrUsers h WHERE h.underMiniAdmin = :underMiniAdmin")})
public class HrUsers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "USER_NAME")
    private String userName;
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "ARABIC_NAME")
    private String arabicName;
    @Column(name = "MINI_ADMIN")
    private Character miniAdmin;
    @Column(name = "UNDER_MINI_ADMIN")
    private Character underMiniAdmin;
    @Column(name = "PROFILE_ACCESS")
    private Character profileAccess;
    public HrUsers() {
    }

    public HrUsers(Long id) {
        this.id = id;
    }

    public HrUsers(Long id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getArabicName() {
        return arabicName;
    }

    public void setArabicName(String arabicName) {
        this.arabicName = arabicName;
    }

    public Character getMiniAdmin() {
        return miniAdmin;
    }

    public void setMiniAdmin(Character miniAdmin) {
        this.miniAdmin = miniAdmin;
    }

    public Character getUnderMiniAdmin() {
        return underMiniAdmin;
    }

    public void setUnderMiniAdmin(Character underMiniAdmin) {
        this.underMiniAdmin = underMiniAdmin;
    }

    public Character getProfileAccess() {
        return profileAccess;
    }

    public void setProfileAccess(Character profileAccess) {
        this.profileAccess = profileAccess;
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
        if (!(object instanceof HrUsers)) {
            return false;
        }
        HrUsers other = (HrUsers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.HrUsers[id=" + id + "]";
    }

}
