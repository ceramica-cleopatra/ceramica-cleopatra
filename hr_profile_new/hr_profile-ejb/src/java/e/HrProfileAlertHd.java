/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "HR_PROFILE_ALERT_HD", catalog = "", schema = "HR")
@NamedQueries({
   @NamedQuery(name = "HrProfileAlertHd.findAll", query = "SELECT h FROM HrProfileAlertHd h order by h.trnsDate"),
    @NamedQuery(name = "HrProfileAlertHd.findAMaxId", query = "SELECT max(h.id) FROM HrProfileAlertHd h"),
    @NamedQuery(name = "HrProfileAlertHd.findById", query = "SELECT h FROM HrProfileAlertHd h WHERE h.id = :id"),
    @NamedQuery(name = "HrProfileAlertHd.findByAlert", query = "SELECT h FROM HrProfileAlertHd h WHERE h.alert = :alert"),
    @NamedQuery(name = "HrProfileAlertHd.findByTrnsDate", query = "SELECT h FROM HrProfileAlertHd h WHERE h.trnsDate = :trnsDate"),
    @NamedQuery(name = "HrProfileAlertHd.findCurrentAlert", query = "SELECT h FROM HrProfileAlertHd h WHERE h.active = 'Y' and not exists (select d from HrProfileAlertHd d left join d.hrProfileAlertDtList x where d.id=h.id and x.empNo=:emp_no)")})
public class HrProfileAlertHd implements Serializable {
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "ALERT_TXT")
    private String alert;
    @Column(name = "TRNS_DATE")
    @Temporal(TemporalType.DATE)
    private Date trnsDate;
    @Column(name = "ACTIVE")
    private Character active;
    @Column(name = "BG_COLOR")
    private String bgColor;
    @OneToMany(mappedBy = "hrProfileAlertHd")
    private List<HrProfileAlertDt> hrProfileAlertDtList;
    @OneToMany(mappedBy = "hdId")
    private List<HrProfileAlertReceiver> hrProfileAlertReceivers;

    public HrProfileAlertHd() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

   

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public Date getTrnsDate() {
        return trnsDate;
    }

    public void setTrnsDate(Date trnsDate) {
        this.trnsDate = trnsDate;
    }

    public Character getActive() {
        return active;
    }

    public void setActive(Character active) {
        this.active = active;
    }

    public List<HrProfileAlertDt> getHrProfileAlertDtList() {
        return hrProfileAlertDtList;
    }

    public void setHrProfileAlertDtList(List<HrProfileAlertDt> hrProfileAlertDtList) {
        this.hrProfileAlertDtList = hrProfileAlertDtList;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public List<HrProfileAlertReceiver> getHrProfileAlertReceivers() {
        return hrProfileAlertReceivers;
    }

    public void setHrProfileAlertReceivers(List<HrProfileAlertReceiver> hrProfileAlertReceivers) {
        this.hrProfileAlertReceivers = hrProfileAlertReceivers;
    }

}
