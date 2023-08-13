<%-- 
    Document   : managerEdit
    Created on : Feb 28, 2023, 3:58:57 PM
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
        <a href="managerManage.jsp">Manager Login Page</a>
        <br><br><br>
        <form action="ManagerController" method="POST">
            <table>
                <tr>
                    <td>Username: </td>
                    <td>
                        ${manager.id}
                        <input type="hidden" name="idManager" value="${manager.id}" />
                    </td>
                </tr>
                <tr>
                    <td>Name: </td>
                    <td><input type="text" name="nameManager" value="${manager.name}" size="20"></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type="text" name="passManager" value="${manager.password}" size="20"></td>
                </tr>
            </table>
                <input type="hidden" name="action" value="postManagerEdit">
            <p><input type="submit" value="Submit Edit"></p>
        </form>
    </body>
</html>
