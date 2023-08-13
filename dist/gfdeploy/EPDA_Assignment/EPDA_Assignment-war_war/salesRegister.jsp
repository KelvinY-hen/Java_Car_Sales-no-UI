<%-- 
    Document   : salesRegister
    Created on : Feb 21, 2023, 1:29:58 PM
    Author     : yelia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Salesman Registration Page</title>
    </head>
    <body>
        <a href="salesLogin.jsp">Salesman Login Page</a>
        <br><br><br>
        <form action="SalesmanController" method="POST">
            <table>
                <tr>
                    <td>Username: </td>
                    <td><input type="text" name="idSales" size="20"></td>
                </tr>
                <tr>
                    <td>Name: </td>
                    <td><input type="text" name="nameSales" size="20"></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type="text" name="passSales" size="20"></td>
                </tr>
            </table>
            <input type="hidden" name="action" value="postSalesmanAdd">
            <p><input type="submit" value="Register"></p>
        </form>
    </body>
</html>
