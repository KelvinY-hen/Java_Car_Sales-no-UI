<%-- 
    Document   : salesLogin
    Created on : Feb 21, 2023, 1:26:23 PM
    Author     : yelia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sales Login Page</title>
    </head>
    <body>
        <a href="index.html">&laquo; Previous Page</a>
        <br><br><br>
        <form action="SalesmanController" method="POST">
            <table>
                <tr>
                    <td>Username: </td>
                    <td><input type="text" name="idSales" size="20"></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type="text" name="passSales" size="20"></td>
                </tr>
            </table>
            <input type="hidden" name="action" value="postSalesmanLogin">
            <p><input type="submit" value="Login"></p>
        </form>
    </body>
</html>
