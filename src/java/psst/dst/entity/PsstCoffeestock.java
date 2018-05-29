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
@Table(name = "psst_coffeestock")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PsstCoffeestock.findAll", query = "SELECT p FROM PsstCoffeestock p"),
    @NamedQuery(name = "PsstCoffeestock.findByCoffeeId", query = "SELECT p FROM PsstCoffeestock p WHERE p.coffeeId = :coffeeId"),
    @NamedQuery(name = "PsstCoffeestock.findByName", query = "SELECT p FROM PsstCoffeestock p WHERE p.name = :name"),
    @NamedQuery(name = "PsstCoffeestock.findByVarian", query = "SELECT p FROM PsstCoffeestock p WHERE p.varian = :varian"),
    @NamedQuery(name = "PsstCoffeestock.findByType", query = "SELECT p FROM PsstCoffeestock p WHERE p.type = :type"),
    @NamedQuery(name = "PsstCoffeestock.findByAmount", query = "SELECT p FROM PsstCoffeestock p WHERE p.amount = :amount"),
    @NamedQuery(name = "PsstCoffeestock.findByUnit", query = "SELECT p FROM PsstCoffeestock p WHERE p.unit = :unit"),
    @NamedQuery(name = "PsstCoffeestock.findByUpdatedAt", query = "SELECT p FROM PsstCoffeestock p WHERE p.updatedAt = :updatedAt")})
public class PsstCoffeestock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "coffee_id", nullable = false)
    private Integer coffeeId;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Basic(optional = false)
    @Column(name = "varian", nullable = false, length = 50)
    private String varian;
    @Basic(optional = false)
    @Column(name = "type", nullable = false, length = 50)
    private String type;
    @Basic(optional = false)
    @Column(name = "amount", nullable = false)
    private double amount;
    @Basic(optional = false)
    @Column(name = "unit", nullable = false, length = 15)
    private String unit;
    @Basic(optional = false)
    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private String updatedAt;

    public PsstCoffeestock() {
    }

    public PsstCoffeestock(Integer coffeeId) {
        this.coffeeId = coffeeId;
    }

    public PsstCoffeestock(Integer coffeeId, String name, String varian, String type, double amount, String unit, String updatedAt) {
        this.coffeeId = coffeeId;
        this.name = name;
        this.varian = varian;
        this.type = type;
        this.amount = amount;
        this.unit = unit;
        this.updatedAt = updatedAt;
    }

    public Integer getCoffeeId() {
        return coffeeId;
    }

    public void setCoffeeId(Integer coffeeId) {
        this.coffeeId = coffeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVarian() {
        return varian;
    }

    public void setVarian(String varian) {
        this.varian = varian;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        hash += (coffeeId != null ? coffeeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PsstCoffeestock)) {
            return false;
        }
        PsstCoffeestock other = (PsstCoffeestock) object;
        if ((this.coffeeId == null && other.coffeeId != null) || (this.coffeeId != null && !this.coffeeId.equals(other.coffeeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "psst.dst.entity.PsstCoffeestock[ coffeeId=" + coffeeId + " ]";
    }
    
}
