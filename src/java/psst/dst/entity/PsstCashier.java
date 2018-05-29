/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psst.dst.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author root
 */
@Entity
@Table(name = "psst_cashier")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PsstCashier.findAll", query = "SELECT p FROM PsstCashier p"),
    @NamedQuery(name = "PsstCashier.findByCashId", query = "SELECT p FROM PsstCashier p WHERE p.cashId = :cashId"),
    @NamedQuery(name = "PsstCashier.findByName", query = "SELECT p FROM PsstCashier p WHERE p.name = :name"),
    @NamedQuery(name = "PsstCashier.findByPhone", query = "SELECT p FROM PsstCashier p WHERE p.phone = :phone"),
    @NamedQuery(name = "PsstCashier.findByEmail", query = "SELECT p FROM PsstCashier p WHERE p.email = :email"),
    @NamedQuery(name = "PsstCashier.findByPwd", query = "SELECT p FROM PsstCashier p WHERE p.pwd = :pwd"),
    @NamedQuery(name = "PsstCashier.findByCreatedAt", query = "SELECT p FROM PsstCashier p WHERE p.createdAt = :createdAt")})
public class PsstCashier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cash_id", nullable = false)
    private Integer cashId;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Basic(optional = false)
    @Column(name = "phone", nullable = false, length = 20)
    private String phone;
    @Basic(optional = false)
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Basic(optional = false)
    @Lob
    @Column(name = "doc", nullable = false, length = 65535)
    private String doc;
    @Basic(optional = false)
    @Lob
    @Column(name = "img", nullable = false, length = 65535)
    private String img;
    @Basic(optional = false)
    @Column(name = "pwd", nullable = false, length = 15)
    private String pwd;
    @Basic(optional = false)
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private String createdAt;

    public PsstCashier() {
    }

    public PsstCashier(Integer cashId) {
        this.cashId = cashId;
    }

    public PsstCashier(Integer cashId, String name, String phone, String email, String doc, String img, String pwd, String createdAt) {
        this.cashId = cashId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.doc = doc;
        this.img = img;
        this.pwd = pwd;
        this.createdAt = createdAt;
    }

    public Integer getCashId() {
        return cashId;
    }

    public void setCashId(Integer cashId) {
        this.cashId = cashId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cashId != null ? cashId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PsstCashier)) {
            return false;
        }
        PsstCashier other = (PsstCashier) object;
        if ((this.cashId == null && other.cashId != null) || (this.cashId != null && !this.cashId.equals(other.cashId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "psst.dst.entity.PsstCashier[ cashId=" + cashId + " ]";
    }
    
}
