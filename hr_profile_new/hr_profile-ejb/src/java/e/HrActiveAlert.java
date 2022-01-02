/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author data
 */
@Entity
@Table(name = "HR_ACTIVE_ALERT", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrActiveAlert.findAll", query = "SELECT h FROM HrActiveAlert h"),
    @NamedQuery(name = "HrActiveAlert.findById", query = "SELECT h FROM HrActiveAlert h WHERE h.id = :id"),
    @NamedQuery(name = "HrActiveAlert.findByEmpNo", query = "SELECT h FROM HrActiveAlert h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrActiveAlert.findByAlertId", query = "SELECT h FROM HrActiveAlert h WHERE h.alertId = :alertId"),
    @NamedQuery(name = "HrActiveAlert.findByBgColor", query = "SELECT h FROM HrActiveAlert h WHERE h.bgColor = :bgColor")})
public class HrActiveAlert implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "EMP_NO")
    private Long empNo;
    @Column(name = "ALERT_ID")
    private Long alertId;
    @Lob
    @Column(name = "ALERT_TXT")
    private String alertTxt;
    @Column(name = "BG_COLOR")
    private String bgColor;
    @Column(name = "DT_ID")
    private Long dtId;

    public HrActiveAlert() {
    }

    public Long getAlertId() {
        return alertId;
    }

    public void setAlertId(Long alertId) {
        this.alertId = alertId;
    }

    public String getAlertTxt() {
        return alertTxt;
    }

    public void setAlertTxt(String alertTxt) {
        this.alertTxt = alertTxt;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public Long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Long empNo) {
        this.empNo = empNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDtId() {
        return dtId;
    }

    public void setDtId(Long dtId) {
        this.dtId = dtId;
    }

    
}
