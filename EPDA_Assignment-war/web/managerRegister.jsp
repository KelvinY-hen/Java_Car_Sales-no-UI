<%-- 
    Document   : managerRegister
    Created on : Feb 21, 2023, 2:31:12 PM
    Author     : yelia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Manager Registration Page</title>
    </head>
    <body>
        <a href="managerManage.jsp">Previous Page</a>
        <br><br><br>
        <form action="managerRegister" method="POST">
            <table>
                <tr>
                    <td>Username: </td>
                    <td><input type="text" name="idManager" size="20"></td>
                </tr>
                <tr>
                    <td>Name: </td>
                    <td><input type="text" name="nameManager" size="20"></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type="text" name="passManager" size="20"></td>
                </tr>
            </table>
            <input type="hidden" name="action" value="postManagerAdd">
            <p><input type="submit" value="Register"></p>
        </form>
    </body>
</html>

