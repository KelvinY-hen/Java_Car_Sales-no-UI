/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author yelia
 */
@Entity
public class ProductClass implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String Name;
    
    @OneToMany(mappedBy = "productClass")
    private List<Product> products;
    
    @OneToMany(mappedBy = "productClass")
    private List<SaleRecords> saleRecords;

    public ProductClass(String id, String Name) {
        this.id = id;
        this.Name = Name;
    }

    public ProductClass() {
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<SaleRecords> getSaleRecords() {
        return saleRecords;
    }

    public void setSaleRecords(List<SaleRecords> saleRecords) {
        this.saleRecords = saleRecords;
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
        if (!(object instanceof ProductClass)) {
            return false;
        }
        ProductClass other = (ProductClass) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ProductClass[ id=" + id + " ]";
    }
    
}
