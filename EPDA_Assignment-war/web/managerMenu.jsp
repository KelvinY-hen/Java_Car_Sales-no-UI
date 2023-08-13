<%-- 
    Document   : managerMenu
    Created on : Feb 21, 2023, 2:31:23 PM
    Author     : yelia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager Menu Page</title>
    </head>
    <body>
        <table>
                <tr>
                    <td><a href="CustomerController?action=getCustomerManage">Manage Customers</a></td>
                </tr>
                <tr>
                    <td><a href="ProductController?action=getProductManage">Manage Product</a></td>
                </tr>
                <tr>
                    <td><a href="SalesmanController?action=getSalesmanManage">Manage Salesman</a></td>
                </tr>
                <tr>
                    <td><a href="ManagerController?action=getManagerManage">Manage Manager</a></td>
                </tr>
                <tr>
                    <td><a href="ManagerController?action=getProductPayment">View Product Payment</a></td>
                </tr>
                <tr>
                    <td><a href="ManagerController?action=getProductAnalysis">Product Analysis</a></td>
                </tr>
                <tr>
                    <td><a href="dailyAnalysis.jsp">Day Analysis</a></td>
                </tr>
                <tr>
                    <td><a href="MonthlyAnalysis.jsp">Month Analysis</a></td>
                </tr>
                <tr>
                    <td><a href="logOut">Log Out</a></td>
                </tr>
                
        </table>
    </body>
</html>
