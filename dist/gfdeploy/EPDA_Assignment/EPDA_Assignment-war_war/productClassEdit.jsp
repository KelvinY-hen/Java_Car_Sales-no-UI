<%-- 
    Document   : productClassEdit
    Created on : Feb 27, 2023, 3:09:44 PM
    Author     : yelia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>edit Profile</title>
    </head>
    <body>
        <a href="productManage.jsp">&laquo; Previous Page</a>
        <br><br><br>
        
        <form action="ProductController" method="POST">
            <table>
                 <tr>
                    <td>Product Class ID: </td>
                    <td><input type="text" name="idProductClass" value="${productClass.id}" size="20"></td>
                </tr>
                <tr>
                    <td>Product Class Name:</td>
                    <td><input type="text" name="nameProductClass" value="${productClass.name}" size="20"></td>
                </tr>
                
            </table>
                <input type="hidden" name="action" value="postProductClassEdit">
            <p><input type="submit" value="Submit Edit"></p>
        </form>
    </body>
</html>
