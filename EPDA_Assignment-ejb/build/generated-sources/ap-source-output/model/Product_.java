package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.ProductClass;
import model.SaleRecords;
import model.Salesman;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-03-06T23:16:16")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile SingularAttribute<Product, ProductClass> productClass;
    public static volatile SingularAttribute<Product, Double> price;
    public static volatile SingularAttribute<Product, Salesman> salesman;
    public static volatile SingularAttribute<Product, Long> id;
    public static volatile SingularAttribute<Product, SaleRecords> saleRecords;
    public static volatile SingularAttribute<Product, String> status;

}