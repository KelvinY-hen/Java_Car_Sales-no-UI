package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Product;
import model.SaleRecords;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-03-06T23:16:16")
@StaticMetamodel(ProductClass.class)
public class ProductClass_ { 

    public static volatile SingularAttribute<ProductClass, String> id;
    public static volatile ListAttribute<ProductClass, SaleRecords> saleRecords;
    public static volatile SingularAttribute<ProductClass, String> Name;
    public static volatile ListAttribute<ProductClass, Product> products;

}