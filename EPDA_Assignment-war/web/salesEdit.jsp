<%-- 
    Document   : salesEdit
    Created on : Feb 21, 2023, 6:02:38 PM
    Author     : yelia
--%>

<%@page import="model.Salesman"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Salesman Profile</title>
    </head>
    <body>
        <a href="salesMenu.jsp">Back</a>
        <br><br><br>
        <form action="SalesmanController" method="POST">
            <table>
                <tr>
                    <td>Username: </td>
                    <td><input type="text" name="idSales" value="${salesman.id}" size="20"></td>
                </tr>
                <tr>
                    <td>Name: </td>
                    <td><input type="text" name="nameSales" value="${salesman.name}" size="20"></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type="text" name="passSales" value="${salesman.password}" size="20"></td>
                </tr>
            </table>
                <input type="hidden" name="action" value="postSalesmanEdit">
            <p><input type="submit" value="Submit Edit"></p>
        </form>
    </body>
</html>
