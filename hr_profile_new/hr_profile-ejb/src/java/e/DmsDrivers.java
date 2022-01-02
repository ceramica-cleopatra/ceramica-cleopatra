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
@Table(name = "DMS_DRIVERS", catalog = "", schema = "DMS")
@NamedQueries({
    @NamedQuery(name = "DmsDrivers.findAll", query = "SELECT d FROM DmsDrivers d where d.drvType=2 and d.carType is null"),
    @NamedQuery(name = "DmsDrivers.findById", query = "SELECT d FROM DmsDrivers d WHERE d.id = :id"),
    @NamedQuery(name = "DmsDrivers.findByName", query = "SELECT d FROM DmsDrivers d WHERE d.name = :name"),
    @NamedQuery(name = "DmsDrivers.findByLicense", query = "SELECT d FROM DmsDrivers d WHERE d.license = :license"),
    @NamedQuery(name = "DmsDrivers.findByMobile", query = "SELECT d FROM DmsDrivers d WHERE d.mobile = :mobile"),
    @NamedQuery(name = "DmsDrivers.findByTba3", query = "SELECT d FROM DmsDrivers d WHERE d.tba3 = :tba3"),
    @NamedQuery(name = "DmsDrivers.findByActualDrv", query = "SELECT d FROM DmsDrivers d WHERE d.actualDrv = :actualDrv")})
public class DmsDrivers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Column(name = "LICENSE")
    private String license;
    @Column(name = "MOBILE")
    private String mobile;
    @Column(name = "TBA3")
    private String tba3;
    @Column(name = "ACTUAL_DRV")
    private String actualDrv;
    @Column(name = "DRV_TYPE")
    private Long drvType;
    @Column(name = "CAR_TYPE")
    private Long carType;
    public DmsDrivers() {
    }

    public DmsDrivers(Long id) {
        this.id = id;
    }

    public DmsDrivers(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTba3() {
        return tba3;
    }

    public void setTba3(String tba3) {
        this.tba3 = tba3;
    }

    public String getActualDrv() {
        return actualDrv;
    }

    public void setActualDrv(String actualDrv) {
        this.actualDrv = actualDrv;
    }

    public Long getCarType() {
        return carType;
    }

    public void setCarType(Long carType) {
        this.carType = carType;
    }

    public Long getDrvType() {
        return drvType;
    }

    public void setDrvType(Long drvType) {
        this.drvType = drvType;
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
        if (!(object instanceof DmsDrivers)) {
            return false;
        }
        DmsDrivers other = (DmsDrivers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.DmsDrivers[id=" + id + "]";
    }

}
