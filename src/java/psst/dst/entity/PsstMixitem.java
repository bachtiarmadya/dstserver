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
@Table(name = "psst_mixitem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PsstMixitem.findAll", query = "SELECT p FROM PsstMixitem p"),
    @NamedQuery(name = "PsstMixitem.findByMixId", query = "SELECT p FROM PsstMixitem p WHERE p.mixId = :mixId"),
    @NamedQuery(name = "PsstMixitem.findByName", query = "SELECT p FROM PsstMixitem p WHERE p.name = :name"),
    @NamedQuery(name = "PsstMixitem.findByAmount", query = "SELECT p FROM PsstMixitem p WHERE p.amount = :amount"),
    @NamedQuery(name = "PsstMixitem.findByUnit", query = "SELECT p FROM PsstMixitem p WHERE p.unit = :unit"),
    @NamedQuery(name = "PsstMixitem.findByUpdatedAt", query = "SELECT p FROM PsstMixitem p WHERE p.updatedAt = :updatedAt")})
public class PsstMixitem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "mix_id", nullable = false)
    private Integer mixId;
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

    public PsstMixitem() {
    }

    public PsstMixitem(Integer mixId) {
        this.mixId = mixId;
    }

    public PsstMixitem(Integer mixId, String name, double amount, String unit, String updatedAt) {
        this.mixId = mixId;
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.updatedAt = updatedAt;
    }

    public Integer getMixId() {
        return mixId;
    }

    public void setMixId(Integer mixId) {
        this.mixId = mixId;
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
        hash += (mixId != null ? mixId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PsstMixitem)) {
            return false;
        }
        PsstMixitem other = (PsstMixitem) object;
        if ((this.mixId == null && other.mixId != null) || (this.mixId != null && !this.mixId.equals(other.mixId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "psst.dst.entity.PsstMixitem[ mixId=" + mixId + " ]";
    }
    
}
