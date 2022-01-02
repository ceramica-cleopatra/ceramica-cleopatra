/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "IP_BRN", catalog = "", schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "IpBrn.findAll", query = "SELECT i FROM IpBrn i"),
    @NamedQuery(name = "IpBrn.findByBrnNo", query = "SELECT i FROM IpBrn i WHERE i.brnNo = :brnNo"),
    @NamedQuery(name = "IpBrn.findByBrnIp", query = "SELECT i FROM IpBrn i WHERE i.brnIp = :brnIp"),
    @NamedQuery(name = "IpBrn.findByBrnId", query = "SELECT i FROM IpBrn i WHERE i.brnId = :brnId"),
    @NamedQuery(name = "IpBrn.findByGovern", query = "SELECT i FROM IpBrn i WHERE i.govern = :govern"),
    @NamedQuery(name = "IpBrn.findByAxiomId", query = "SELECT i FROM IpBrn i WHERE i.axiomId = :axiomId")})
public class IpBrn implements Serializable {
    @Column(name = "BRN_NO")
    private Integer brnNo;
    @Id
    @Column(name = "BRN_IP")
    private String brnIp;
    @Column(name = "BRN_ID")
    private Long brnId;
    @Column(name = "GOVERN")
    private Long govern;
    @Column(name = "AXIOM_ID")
    private Long axiomId;

    public IpBrn() {
    }


    public Integer getBrnNo() {
        return brnNo;
    }

    public void setBrnNo(Integer brnNo) {
        this.brnNo = brnNo;
    }

    public String getBrnIp() {
        return brnIp;
    }

    public void setBrnIp(String brnIp) {
        this.brnIp = brnIp;
    }

    public Long getBrnId() {
        return brnId;
    }

    public void setBrnId(Long brnId) {
        this.brnId = brnId;
    }

    public Long getGovern() {
        return govern;
    }

    public void setGovern(Long govern) {
        this.govern = govern;
    }

    public Long getAxiomId() {
        return axiomId;
    }

    public void setAxiomId(Long axiomId) {
        this.axiomId = axiomId;
    }



}
