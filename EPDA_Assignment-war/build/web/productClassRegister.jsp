<%-- 
    Document   : productClassRegister
    Created on : Feb 27, 2023, 11:17:22 AM
    Author     : yelia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="<%= request.getHeader("Referer") %>">&laquo; Previous Page</a>
        <br><br><br>
        <form action="ProductController" method="POST">
            <table>
                <tr>
                    <td>Product Class ID: </td>
                    <td><input type="text" name="idProdClass" size="20"></td>
                </tr>
                <tr>
                    <td>Product Class Name: </td>
                    <td><input type="text" name="nameProdClass" size="20"></td>
                </tr>
            </table>
            <input type="hidden" name="action" value="postProductClassAdd">
            <p><input type="submit" value="Register"></p>
        </form>
    </body>
</html>
