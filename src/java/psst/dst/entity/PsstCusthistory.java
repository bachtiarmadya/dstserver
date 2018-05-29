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
@Table(name = "psst_custhistory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PsstCusthistory.findAll", query = "SELECT p FROM PsstCusthistory p"),
    @NamedQuery(name = "PsstCusthistory.findByHistId", query = "SELECT p FROM PsstCusthistory p WHERE p.histId = :histId"),
    @NamedQuery(name = "PsstCusthistory.findByCustId", query = "SELECT p FROM PsstCusthistory p WHERE p.custId = :custId"),
    @NamedQuery(name = "PsstCusthistory.findByCoffeeId", query = "SELECT p FROM PsstCusthistory p WHERE p.coffeeId = :coffeeId"),
    @NamedQuery(name = "PsstCusthistory.findByServId", query = "SELECT p FROM PsstCusthistory p WHERE p.servId = :servId"),
    @NamedQuery(name = "PsstCusthistory.findByOrderNo", query = "SELECT p FROM PsstCusthistory p WHERE p.orderNo = :orderNo"),
    @NamedQuery(name = "PsstCusthistory.findByTprice", query = "SELECT p FROM PsstCusthistory p WHERE p.tprice = :tprice"),
    @NamedQuery(name = "PsstCusthistory.findByCreatedAt", query = "SELECT p FROM PsstCusthistory p WHERE p.createdAt = :createdAt")})
public class PsstCusthistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "hist_id", nullable = false)
    private Integer histId;
    @Basic(optional = false)
    @Column(name = "cust_id", nullable = false)
    private int custId;
    @Basic(optional = false)
    @Column(name = "coffee_id", nullable = false)
    private int coffeeId;
    @Basic(optional = false)
    @Column(name = "serv_id", nullable = false)
    private int servId;
    @Basic(optional = false)
    @Column(name = "order_no", nullable = false)
    private int orderNo;
    @Basic(optional = false)
    @Column(name = "tprice", nullable = false)
    private double tprice;
    @Basic(optional = false)
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public PsstCusthistory() {
    }

    public PsstCusthistory(Integer histId) {
        this.histId = histId;
    }

    public PsstCusthistory(Integer histId, int custId, int coffeeId, int servId, int orderNo, double tprice, Date createdAt) {
        this.histId = histId;
        this.custId = custId;
        this.coffeeId = coffeeId;
        this.servId = servId;
        this.orderNo = orderNo;
        this.tprice = tprice;
        this.createdAt = createdAt;
    }

    public Integer getHistId() {
        return histId;
    }

    public void setHistId(Integer histId) {
        this.histId = histId;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public int getCoffeeId() {
        return coffeeId;
    }

    public void setCoffeeId(int coffeeId) {
        this.coffeeId = coffeeId;
    }

    public int getServId() {
        return servId;
    }

    public void setServId(int servId) {
        this.servId = servId;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public double getTprice() {
        return tprice;
    }

    public void setTprice(double tprice) {
        this.tprice = tprice;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (histId != null ? histId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PsstCusthistory)) {
            return false;
        }
        PsstCusthistory other = (PsstCusthistory) object;
        if ((this.histId == null && other.histId != null) || (this.histId != null && !this.histId.equals(other.histId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "psst.dst.entity.PsstCusthistory[ histId=" + histId + " ]";
    }
    
}
