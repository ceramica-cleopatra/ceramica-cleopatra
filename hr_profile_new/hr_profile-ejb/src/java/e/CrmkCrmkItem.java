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
@Table(name = "CRMK_CRMK_ITEM", catalog = "", schema = "CRMK")
@NamedQueries({
    @NamedQuery(name = "CrmkCrmkItem.findAll", query = "SELECT c FROM CrmkCrmkItem c"),
    @NamedQuery(name = "CrmkCrmkItem.findById", query = "SELECT c FROM CrmkCrmkItem c WHERE c.id = :id"),
    @NamedQuery(name = "CrmkCrmkItem.findByCode", query = "SELECT c FROM CrmkCrmkItem c WHERE c.code = :code"),
    @NamedQuery(name = "CrmkCrmkItem.findByFactoryNo", query = "SELECT c FROM CrmkCrmkItem c WHERE c.factoryNo = :factoryNo"),
    @NamedQuery(name = "CrmkCrmkItem.findByTablow", query = "SELECT c FROM CrmkCrmkItem c WHERE c.tablow = :tablow"),
    @NamedQuery(name = "CrmkCrmkItem.findByFrz", query = "SELECT c FROM CrmkCrmkItem c WHERE c.frz = :frz"),
    @NamedQuery(name = "CrmkCrmkItem.findByC", query = "SELECT c FROM CrmkCrmkItem c WHERE c.c = :c"),
    @NamedQuery(name = "CrmkCrmkItem.findByTone", query = "SELECT c FROM CrmkCrmkItem c WHERE c.tone = :tone"),
    @NamedQuery(name = "CrmkCrmkItem.findByThickness", query = "SELECT c FROM CrmkCrmkItem c WHERE c.thickness = :thickness"),
    @NamedQuery(name = "CrmkCrmkItem.findByImgFile", query = "SELECT c FROM CrmkCrmkItem c WHERE c.imgFile = :imgFile"),
    @NamedQuery(name = "CrmkCrmkItem.findByNotes", query = "SELECT c FROM CrmkCrmkItem c WHERE c.notes = :notes"),
    @NamedQuery(name = "CrmkCrmkItem.findByNoCTone", query = "SELECT c FROM CrmkCrmkItem c WHERE c.noCTone = :noCTone"),
    @NamedQuery(name = "CrmkCrmkItem.findByTypicalGrp", query = "SELECT c FROM CrmkCrmkItem c WHERE c.typicalGrp = :typicalGrp"),
    @NamedQuery(name = "CrmkCrmkItem.findByLikeGrp", query = "SELECT c FROM CrmkCrmkItem c WHERE c.likeGrp = :likeGrp"),
    @NamedQuery(name = "CrmkCrmkItem.findByCrtDate", query = "SELECT c FROM CrmkCrmkItem c WHERE c.crtDate = :crtDate"),
    @NamedQuery(name = "CrmkCrmkItem.findByEnglishName", query = "SELECT c FROM CrmkCrmkItem c WHERE c.englishName = :englishName")})
public class CrmkCrmkItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "CODE")
    private String code;
    @Column(name = "FACTORY_NO")
    private String factoryNo;
    @Column(name = "TABLOW")
    private Long tablow;
    @Column(name = "FRZ")
    private Short frz;
    @Column(name = "C")
    private String c;
    @Column(name = "TONE")
    private String tone;
    @Column(name = "THICKNESS")
    private BigDecimal thickness;
    @Column(name = "IMG_FILE")
    private String imgFile;
    @Column(name = "NOTES")
    private String notes;
    @Basic(optional = false)
    @Column(name = "NO_C_TONE")
    private String noCTone;
    @Column(name = "TYPICAL_GRP")
    private String typicalGrp;
    @Column(name = "LIKE_GRP")
    private String likeGrp;
    @Basic(optional = false)
    @Column(name = "CRT_DATE")
    @Temporal(TemporalType.DATE)
    private Date crtDate;
    @Column(name = "ENGLISH_NAME")
    private String englishName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmkCrmkItem")
    private List<CrmkCMe2ordrDt> crmkCMe2ordrDtList;

    public CrmkCrmkItem() {
    }

    public CrmkCrmkItem(Long id) {
        this.id = id;
    }

    public CrmkCrmkItem(Long id, String code, String noCTone, Date crtDate) {
        this.id = id;
        this.code = code;
        this.noCTone = noCTone;
        this.crtDate = crtDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFactoryNo() {
        return factoryNo;
    }

    public void setFactoryNo(String factoryNo) {
        this.factoryNo = factoryNo;
    }

    public Long getTablow() {
        return tablow;
    }

    public void setTablow(Long tablow) {
        this.tablow = tablow;
    }

    public Short getFrz() {
        return frz;
    }

    public void setFrz(Short frz) {
        this.frz = frz;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getTone() {
        return tone;
    }

    public void setTone(String tone) {
        this.tone = tone;
    }

    public BigDecimal getThickness() {
        return thickness;
    }

    public void setThickness(BigDecimal thickness) {
        this.thickness = thickness;
    }

    public String getImgFile() {
        return imgFile;
    }

    public void setImgFile(String imgFile) {
        this.imgFile = imgFile;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNoCTone() {
        return noCTone;
    }

    public void setNoCTone(String noCTone) {
        this.noCTone = noCTone;
    }

    public String getTypicalGrp() {
        return typicalGrp;
    }

    public void setTypicalGrp(String typicalGrp) {
        this.typicalGrp = typicalGrp;
    }

    public String getLikeGrp() {
        return likeGrp;
    }

    public void setLikeGrp(String likeGrp) {
        this.likeGrp = likeGrp;
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public List<CrmkCMe2ordrDt> getCrmkCMe2ordrDtList() {
        return crmkCMe2ordrDtList;
    }

    public void setCrmkCMe2ordrDtList(List<CrmkCMe2ordrDt> crmkCMe2ordrDtList) {
        this.crmkCMe2ordrDtList = crmkCMe2ordrDtList;
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
        if (!(object instanceof CrmkCrmkItem)) {
            return false;
        }
        CrmkCrmkItem other = (CrmkCrmkItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e.CrmkCrmkItem[id=" + id + "]";
    }

}
