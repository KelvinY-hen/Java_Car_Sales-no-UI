package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Product;
import model.SaleRecords;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-03-06T23:16:16")
@StaticMetamodel(Salesman.class)
public class Salesman_ { 

    public static volatile SingularAttribute<Salesman, String> password;
    public static volatile ListAttribute<Salesman, SaleRecords> SalesHistory;
    public static volatile SingularAttribute<Salesman, String> name;
    public static volatile SingularAttribute<Salesman, String> id;
    public static volatile ListAttribute<Salesman, Product> products;

}