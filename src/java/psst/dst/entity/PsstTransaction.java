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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author root
 */
@Entity
@Table(name = "psst_transaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PsstTransaction.findAll", query = "SELECT p FROM PsstTransaction p"),
    @NamedQuery(name = "PsstTransaction.findByTrxId", query = "SELECT p FROM PsstTransaction p WHERE p.trxId = :trxId"),
    @NamedQuery(name = "PsstTransaction.findByOrderNo", query = "SELECT p FROM PsstTransaction p WHERE p.orderNo = :orderNo"),
    @NamedQuery(name = "PsstTransaction.findByCustName", query = "SELECT p FROM PsstTransaction p WHERE p.custName = :custName"),
    @NamedQuery(name = "PsstTransaction.findByCoffeeName", query = "SELECT p FROM PsstTransaction p WHERE p.coffeeName = :coffeeName"),
    @NamedQuery(name = "PsstTransaction.findByServeName", query = "SELECT p FROM PsstTransaction p WHERE p.serveName = :serveName"),
    @NamedQuery(name = "PsstTransaction.findByNoncoffeeName", query = "SELECT p FROM PsstTransaction p WHERE p.noncoffeeName = :noncoffeeName"),
    @NamedQuery(name = "PsstTransaction.findByCoffeeQty", query = "SELECT p FROM PsstTransaction p WHERE p.coffeeQty = :coffeeQty"),
    @NamedQuery(name = "PsstTransaction.findByNoncoffeeQty", query = "SELECT p FROM PsstTransaction p WHERE p.noncoffeeQty = :noncoffeeQty"),
    @NamedQuery(name = "PsstTransaction.findByUprice", query = "SELECT p FROM PsstTransaction p WHERE p.uprice = :uprice"),
    @NamedQuery(name = "PsstTransaction.findByDiscount", query = "SELECT p FROM PsstTransaction p WHERE p.discount = :discount"),
    @NamedQuery(name = "PsstTransaction.findByTprice", query = "SELECT p FROM PsstTransaction p WHERE p.tprice = :tprice"),
    @NamedQuery(name = "PsstTransaction.findByCreatedAt", query = "SELECT p FROM PsstTransaction p WHERE p.createdAt = :createdAt"),
    @NamedQuery(name = "PsstTransaction.findByCashId", query = "SELECT p FROM PsstTransaction p WHERE p.cashId = :cashId")})
public class PsstTransaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "trx_id", nullable = false)
    private Integer trxId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "order_no", nullable = false)
    private int orderNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "cust_name", nullable = false, length = 50)
    private String custName;
    @Size(max = 50)
    @Column(name = "coffee_name", length = 50)
    private String coffeeName;
    @Size(max = 50)
    @Column(name = "serve_name", length = 50)
    private String serveName;
    @Size(max = 50)
    @Column(name = "noncoffee_name", length = 50)
    private String noncoffeeName;
    @Column(name = "coffee_qty")
    private Integer coffeeQty;
    @Column(name = "noncoffee_qty")
    private Integer noncoffeeQty;
    @Basic(optional = false)
    @NotNull
    @Column(name = "uprice", nullable = false)
    private double uprice;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "discount", precision = 22)
    private Double discount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tprice", nullable = false)
    private double tprice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private String createdAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cash_id", nullable = false)
    private int cashId;

    public PsstTransaction() {
    }

    public PsstTransaction(Integer trxId) {
        this.trxId = trxId;
    }

    public PsstTransaction(Integer trxId, int orderNo, String custName, double uprice, double tprice, String createdAt, int cashId) {
        this.trxId = trxId;
        this.orderNo = orderNo;
        this.custName = custName;
        this.uprice = uprice;
        this.tprice = tprice;
        this.createdAt = createdAt;
        this.cashId = cashId;
    }

    public Integer getTrxId() {
        return trxId;
    }

    public void setTrxId(Integer trxId) {
        this.trxId = trxId;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCoffeeName() {
        return coffeeName;
    }

    public void setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    public String getServeName() {
        return serveName;
    }

    public void setServeName(String serveName) {
        this.serveName = serveName;
    }

    public String getNoncoffeeName() {
        return noncoffeeName;
    }

    public void setNoncoffeeName(String noncoffeeName) {
        this.noncoffeeName = noncoffeeName;
    }

    public Integer getCoffeeQty() {
        return coffeeQty;
    }

    public void setCoffeeQty(Integer coffeeQty) {
        this.coffeeQty = coffeeQty;
    }

    public Integer getNoncoffeeQty() {
        return noncoffeeQty;
    }

    public void setNoncoffeeQty(Integer noncoffeeQty) {
        this.noncoffeeQty = noncoffeeQty;
    }

    public double getUprice() {
        return uprice;
    }

    public void setUprice(double uprice) {
        this.uprice = uprice;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public double getTprice() {
        return tprice;
    }

    public void setTprice(double tprice) {
        this.tprice = tprice;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getCashId() {
        return cashId;
    }

    public void setCashId(int cashId) {
        this.cashId = cashId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trxId != null ? trxId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PsstTransaction)) {
            return false;
        }
        PsstTransaction other = (PsstTransaction) object;
        if ((this.trxId == null && other.trxId != null) || (this.trxId != null && !this.trxId.equals(other.trxId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "psst.dst.entity.PsstTransaction[ trxId=" + trxId + " ]";
    }
    
}
