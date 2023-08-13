/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author yelia
 */
@Entity
public class SaleRecords implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OneToOne
    private Product id;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Salesman salesman;
    
    @ManyToOne
    private ProductClass productClass;
    
    
    private String customerFeedback;
    private int customerRating;
    private String salesReview;
    
    private ZonedDateTime dt;

    public SaleRecords() {
    }

    public SaleRecords(Product id, Customer customer, Salesman salesman, ProductClass productClass, ZonedDateTime dt) {
        this.id = id;
        this.customer = customer;
        this.salesman = salesman;
        this.productClass = productClass;
        this.dt = dt;
    }

    
    
    public Product getId() {
        return id;
    }

    public void setId(Product id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Salesman getSalesman() {
        return salesman;
    }

    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }

    public String getCustomerFeedback() {
        return customerFeedback;
    }

    public void setCustomerFeedback(String customerFeedback) {
        this.customerFeedback = customerFeedback;
    }

    public String getSalesReview() {
        return salesReview;
    }

    public void setSalesReview(String salesReview) {
        this.salesReview = salesReview;
    }

    public ZonedDateTime getDt() {
        return dt;
    }

    public void setDt(ZonedDateTime dt) {
        this.dt = dt;
    }

    public int getCustomerRating() {
        return customerRating;
    }

    public void setCustomerRating(int customerRating) {
        this.customerRating = customerRating;
    }

    public ProductClass getProductClass() {
        return productClass;
    }

    public void setProductClass(ProductClass productClass) {
        this.productClass = productClass;
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
        if (!(object instanceof SaleRecords)) {
            return false;
        }
        SaleRecords other = (SaleRecords) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.SaleRecords[ id=" + id + " ]";
    }
    
}
