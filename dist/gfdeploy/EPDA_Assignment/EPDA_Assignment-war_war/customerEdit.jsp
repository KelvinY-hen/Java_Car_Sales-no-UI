<%-- 
    Document   : editProfile
    Created on : Feb 21, 2023, 3:30:52 PM
    Author     : yelia
--%>

<%@page import="model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>edit Profile</title>
    </head>
    <body>
        <a href="<%= request.getHeader("Referer") %>">&laquo; Previous Page</a>
        <br><br><br>
        <form action="CustomerController" method="POST">
            <table>
                <tr>
                    <td>Username: </td>
                    <td><input type="text" name="idCust" value="${customer.id}" size="20"></td>
                </tr>
                <tr>
                    <td>Name: </td>
                    <td><input type="text" name="nameCust" value="${customer.name}" size="20"></td>
                </tr>
                <tr>
                    <td>Gender: </td>
                    <td>
                        <select name="genderCust" value="${customer.gender}" size="20">
                            <option value="Male">Male</option>
                            <option value="Female">Female</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type="text" name="passCust" value="${customer.password}" size="20"></td>
                </tr>
            </table>
                <input type="hidden" name="action" value="postCustomerEdit">
            <p><input type="submit" value="Submit Edit"></p>
        </form>
    </body>
</html>
