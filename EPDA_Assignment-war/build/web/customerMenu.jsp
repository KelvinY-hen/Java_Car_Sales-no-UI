<%-- 
    Document   : customerMenu
    Created on : Feb 21, 2023, 3:20:23 PM
    Author     : yelia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Menu</title>
    </head>
    <body>
        <table>
                <tr>
                    <td><a href="CustomerController?action=getCustomerEdit">Edit Profile</a></td>
                </tr>
                <tr>
                    <td><a href="CustomerController?action=getPurchaseHistory">View Purchases</a></td>
                </tr>
                <tr>
                    <td><a href="logOut">Log Out</a></td>
                </tr>
        </table>
    </body>
</html>
