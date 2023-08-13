package model;

import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Customer;
import model.Product;
import model.ProductClass;
import model.Salesman;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-03-06T23:16:16")
@StaticMetamodel(SaleRecords.class)
public class SaleRecords_ { 

    public static volatile SingularAttribute<SaleRecords, ZonedDateTime> dt;
    public static volatile SingularAttribute<SaleRecords, String> salesReview;
    public static volatile SingularAttribute<SaleRecords, ProductClass> productClass;
    public static volatile SingularAttribute<SaleRecords, Integer> customerRating;
    public static volatile SingularAttribute<SaleRecords, String> customerFeedback;
    public static volatile SingularAttribute<SaleRecords, Salesman> salesman;
    public static volatile SingularAttribute<SaleRecords, Product> id;
    public static volatile SingularAttribute<SaleRecords, Customer> customer;

}