/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ahmed abbas
 */
@Entity
@Table(name = "HR_TRGT_HIST", catalog = "", schema = "HR")
@NamedQueries({
    @NamedQuery(name = "HrTrgtHist.findAll",query = "select o.id,o.empNo,o.net,o.trgt,m.name,o.trnsYear from HrTrgtHist o JOIN o.trnsMonth m  where o.empNo=:emp_no order by o.trnsYear,o.trnsMonth.id "),
    @NamedQuery(name = "HrTrgtHist.findByEmpNo", query = "SELECT h FROM HrTrgtHist h WHERE h.empNo = :empNo"),
    @NamedQuery(name = "HrTrgtHist.findByNet", query = "SELECT h FROM HrTrgtHist h WHERE h.net = :net"),
    @NamedQuery(name = "HrTrgtHist.findByTrgt", query = "SELECT h FROM HrTrgtHist h WHERE h.trgt = :trgt"),
    @NamedQuery(name = "HrTrgtHist.findByTrnsMonth", query = "SELECT h FROM HrTrgtHist h WHERE h.trnsMonth = :trnsMonth"),
    @NamedQuery(name = "HrTrgtHist.findByTrnsYear", query = "SELECT h FROM HrTrgtHist h WHERE h.trnsYear = :trnsYear"),
    @NamedQuery(name = "HrTrgtHist.findById", query = "SELECT h FROM HrTrgtHist h WHERE h.id = :id")})
public class HrTrgtHist implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "EMP_NO")
    private Long empNo;
    @Column(name = "NET")
    private Long net;
    @Column(name = "TRGT")
    private Long trgt;
    @ManyToOne
    @JoinColumn(name = "TRNS_MONTH")
    private HrMonthes trnsMonth;
    @Column(name = "TRNS_YEAR")
    private Long trnsYear;
    @Id
    @Column(name = "ID")
    private Long id;

    public HrTrgtHist() {
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

    public Long getNet() {
        return net;
    }

    public void setNet(Long net) {
        this.net = net;
    }

    public Long getTrgt() {
        return trgt;
    }

    public void setTrgt(Long trgt) {
        this.trgt = trgt;
    }

    public Long getTrnsYear() {
        return trnsYear;
    }

    public void setTrnsYear(Long trnsYear) {
        this.trnsYear = trnsYear;
    }
    
    public HrMonthes getTrnsMonth() {
        return trnsMonth;
    }

    public void setTrnsMonth(HrMonthes trnsMonth) {
        this.trnsMonth = trnsMonth;
    }


}
