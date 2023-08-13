<%-- 
    Document   : salesMenu
    Created on : Feb 21, 2023, 2:31:32 PM
    Author     : yelia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sales Menu Page</title>
    </head>
    <body>
        <table>
                <tr>
                    <td><a href="SalesmanController?action=getSalesmanEdit">Edit Profile</a></td>
                </tr>
                <tr>
                    <td><a href="SalesmanController?action=getProductManage">Manage Product</a></td>
                </tr>
                <tr>
                    <td><a href="SalesmanController?action=getSalesRecord">View Sales Record</a></td>
                </tr>
                <tr>
                    <td><a href="logOut">Log Out</a></td>
                </tr>
        </table>
    </body>
</html>
