<%-- 
    Document   : customerManage
    Created on : Feb 22, 2023, 10:33:13 AM
    Author     : yelia
--%>

<%@page import="javax.naming.InitialContext"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <a href="managerMenu.jsp">&laquo; Previous Page</a>
        <table id="table">
            <thead id="thead">
                <tr id="trow">
                    <th id="th">Id</th>
                    <th id="th">Name</th>
                    <th id="th">Password</th>
                    <th id="th"></th>
                    <th id="th"></th>
                </tr>
            </thead>
                <tbody id="tbody">
                    <c:choose>
                        <c:when test="${managerList != null }"  >

                            <c:forEach items="${managerList}" var="manager">
                                <tr id="trow">
                                    <td id="tdata">${manager.id}</td>
                                    <td id="tdata">${manager.name}</td>
                                    <td id="tdata">${manager.password}</td>
                                    <td id="tdata"><a href="ManagerController?id=${manager.id}&amp;action=getManagerEdit">Edit</a></td>
                                    <td id="tdata"><a href="ManagerController?id=${manager.id}&amp;action=getManagerDelete">Delete</a></td>
                                </tr>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                </tbody>
        </table>
        <form action="ManagerController" method="POST">
            <table>
                <tr>
                    <td>Username: </td>
                    <td><input type="text" name="idSearch" size="20"></td>
                </tr>
            </table>
            <input type="hidden" name="action" value="postManagerSearch">
            <p><input type="submit" value="Search"></p>
        </form>
        <br><br>
        <td><a href="managerRegister.jsp">Register New Manager</a></td>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>
    </body>
</html>
