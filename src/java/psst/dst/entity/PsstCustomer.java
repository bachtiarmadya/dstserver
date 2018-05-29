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
@Table(name = "psst_customer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PsstCustomer.findAll", query = "SELECT p FROM PsstCustomer p"),
    @NamedQuery(name = "PsstCustomer.findByCustId", query = "SELECT p FROM PsstCustomer p WHERE p.custId = :custId"),
    @NamedQuery(name = "PsstCustomer.findByName", query = "SELECT p FROM PsstCustomer p WHERE p.name = :name"),
    @NamedQuery(name = "PsstCustomer.findByPhone", query = "SELECT p FROM PsstCustomer p WHERE p.phone = :phone"),
    @NamedQuery(name = "PsstCustomer.findByEmail", query = "SELECT p FROM PsstCustomer p WHERE p.email = :email"),
    @NamedQuery(name = "PsstCustomer.findByPwd", query = "SELECT p FROM PsstCustomer p WHERE p.pwd = :pwd"),
    @NamedQuery(name = "PsstCustomer.findByCreatedAt", query = "SELECT p FROM PsstCustomer p WHERE p.createdAt = :createdAt")})
public class PsstCustomer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cust_id", nullable = false)
    private Integer custId;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Basic(optional = false)
    @Column(name = "phone", nullable = false, length = 20)
    private String phone;
    @Basic(optional = false)
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Basic(optional = false)
    @Column(name = "pwd", nullable = false, length = 15)
    private String pwd;
    @Basic(optional = false)
    @Lob
    @Column(name = "gcm_token", nullable = false, length = 65535)
    private String gcmToken;
    @Basic(optional = false)
    @Lob
    @Column(name = "img", nullable = false, length = 65535)
    private String img;
    @Basic(optional = false)
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private String createdAt;

    public PsstCustomer() {
    }

    public PsstCustomer(Integer custId) {
        this.custId = custId;
    }

    public PsstCustomer(Integer custId, String name, String phone, String email, String pwd, String gcmToken, String img, String createdAt) {
        this.custId = custId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.pwd = pwd;
        this.gcmToken = gcmToken;
        this.img = img;
        this.createdAt = createdAt;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getGcmToken() {
        return gcmToken;
    }

    public void setGcmToken(String gcmToken) {
        this.gcmToken = gcmToken;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
        hash += (custId != null ? custId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PsstCustomer)) {
            return false;
        }
        PsstCustomer other = (PsstCustomer) object;
        if ((this.custId == null && other.custId != null) || (this.custId != null && !this.custId.equals(other.custId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "psst.dst.entity.PsstCustomer[ custId=" + custId + " ]";
    }
    
}
