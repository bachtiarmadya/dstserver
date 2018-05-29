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
@Table(name = "psst_utility")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PsstUtility.findAll", query = "SELECT p FROM PsstUtility p"),
    @NamedQuery(name = "PsstUtility.findByUtilId", query = "SELECT p FROM PsstUtility p WHERE p.utilId = :utilId"),
    @NamedQuery(name = "PsstUtility.findByName", query = "SELECT p FROM PsstUtility p WHERE p.name = :name"),
    @NamedQuery(name = "PsstUtility.findByAmount", query = "SELECT p FROM PsstUtility p WHERE p.amount = :amount"),
    @NamedQuery(name = "PsstUtility.findByUnit", query = "SELECT p FROM PsstUtility p WHERE p.unit = :unit"),
    @NamedQuery(name = "PsstUtility.findByUpdatedAt", query = "SELECT p FROM PsstUtility p WHERE p.updatedAt = :updatedAt")})
public class PsstUtility implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "util_id", nullable = false)
    private Integer utilId;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Basic(optional = false)
    @Column(name = "amount", nullable = false)
    private double amount;
    @Basic(optional = false)
    @Column(name = "unit", nullable = false, length = 10)
    private String unit;
    @Basic(optional = false)
    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private String updatedAt;

    public PsstUtility() {
    }

    public PsstUtility(Integer utilId) {
        this.utilId = utilId;
    }

    public PsstUtility(Integer utilId, String name, double amount, String unit, String updatedAt) {
        this.utilId = utilId;
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.updatedAt = updatedAt;
    }

    public Integer getUtilId() {
        return utilId;
    }

    public void setUtilId(Integer utilId) {
        this.utilId = utilId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (utilId != null ? utilId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PsstUtility)) {
            return false;
        }
        PsstUtility other = (PsstUtility) object;
        if ((this.utilId == null && other.utilId != null) || (this.utilId != null && !this.utilId.equals(other.utilId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "psst.dst.entity.PsstUtility[ utilId=" + utilId + " ]";
    }
    
}
