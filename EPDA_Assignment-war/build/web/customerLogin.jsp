<%-- 
    Document   : customerLogin
    Created on : Feb 21, 2023, 11:39:13 AM
    Author     : yelia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Login Page</title>
    </head>
    <body>
        <a href="index.html">&laquo; Previous Page</a>
        <br><br><br>
        <form action="CustomerController" method="POST">
            <table>
                <tr>
                    <td>Username: </td>
                    <td><input type="text" name="idCust" size="20"></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type="text" name="passCust" size="20"></td>
                </tr>
            </table>
            <input type="hidden" name="action" value="postCustomerLogin">
            <p><input type="submit" value="Login"></p>
        </form>
    </body>
</html>
