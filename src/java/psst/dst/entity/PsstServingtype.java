/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psst.dst.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author root
 */
@Entity
@Table(name = "psst_servingtype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PsstServingtype.findAll", query = "SELECT p FROM PsstServingtype p"),
    @NamedQuery(name = "PsstServingtype.findByServId", query = "SELECT p FROM PsstServingtype p WHERE p.servId = :servId"),
    @NamedQuery(name = "PsstServingtype.findByName", query = "SELECT p FROM PsstServingtype p WHERE p.name = :name"),
    @NamedQuery(name = "PsstServingtype.findByComp", query = "SELECT p FROM PsstServingtype p WHERE p.comp = :comp"),
    @NamedQuery(name = "PsstServingtype.findByPrice", query = "SELECT p FROM PsstServingtype p WHERE p.price = :price"),
    @NamedQuery(name = "PsstServingtype.findByUtilId", query = "SELECT p FROM PsstServingtype p WHERE p.utilId = :utilId"),
    @NamedQuery(name = "PsstServingtype.findByMixId", query = "SELECT p FROM PsstServingtype p WHERE p.mixId = :mixId")})
public class PsstServingtype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "serv_id", nullable = false)
    private Integer servId;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Basic(optional = false)
    @Column(name = "comp", nullable = false)
    private double comp;
    @Basic(optional = false)
    @Column(name = "price", nullable = false)
    private double price;
    @Basic(optional = false)
    @Column(name = "util_id", nullable = false)
    private int utilId;
    @Basic(optional = false)
    @Column(name = "mix_id", nullable = false)
    private int mixId;

    public PsstServingtype() {
    }

    public PsstServingtype(Integer servId) {
        this.servId = servId;
    }

    public PsstServingtype(Integer servId, String name, double comp, double price, int utilId, int mixId) {
        this.servId = servId;
        this.name = name;
        this.comp = comp;
        this.price = price;
        this.utilId = utilId;
        this.mixId = mixId;
    }

    public Integer getServId() {
        return servId;
    }

    public void setServId(Integer servId) {
        this.servId = servId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getComp() {
        return comp;
    }

    public void setComp(double comp) {
        this.comp = comp;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getUtilId() {
        return utilId;
    }

    public void setUtilId(int utilId) {
        this.utilId = utilId;
    }

    public int getMixId() {
        return mixId;
    }

    public void setMixId(int mixId) {
        this.mixId = mixId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (servId != null ? servId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PsstServingtype)) {
            return false;
        }
        PsstServingtype other = (PsstServingtype) object;
        if ((this.servId == null && other.servId != null) || (this.servId != null && !this.servId.equals(other.servId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "psst.dst.entity.PsstServingtype[ servId=" + servId + " ]";
    }
    
}
