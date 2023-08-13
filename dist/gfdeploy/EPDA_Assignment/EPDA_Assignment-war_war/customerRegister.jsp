<%-- 
    Document   : customerRegister
    Created on : Feb 21, 2023, 11:42:14 AM
    Author     : yelia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Registration Page</title>
    </head>
    <body>
        <a href="<%= request.getHeader("Referer") %>">&laquo; Previous Page</a>
        <br><br><br>
        <form action="CustomerController" method="POST">
            <table>
                <tr>
                    <td>Username: </td>
                    <td><input type="text" name="idCust" size="20"></td>
                </tr>
                <tr>
                    <td>Name: </td>
                    <td><input type="text" name="nameCust" size="20"></td>
                </tr>
                <tr>
                    <td>Gender: </td>
                    <td>
                        <select name="genderCust">
                            <option value="Male">Male</option>
                            <option value="Female">Female</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type="text" name="passCust" size="20"></td>
                </tr>
            </table>
            <input type="hidden" name="action" value="postCustomerAdd">
            <p><input type="submit" value="Register"></p>
        </form>
    </body>
</html>
