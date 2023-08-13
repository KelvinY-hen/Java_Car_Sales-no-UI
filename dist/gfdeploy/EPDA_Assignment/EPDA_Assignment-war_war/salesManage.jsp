<%-- 
    Document   : salesManage
    Created on : Feb 27, 2023, 12:38:38 PM
    Author     : yelia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.SalesmanFacade"%>
<%@page import="model.Salesman"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
                        <c:when test="${salesmanList != null }"  >

                            <c:forEach items="${salesmanList}" var="salesman">
                                <tr id="trow">
                                    <td id="tdata">${salesman.id}</td>
                                    <td id="tdata">${salesman.name}</td>
                                    <td id="tdata">${salesman.password}</td>
                                    <td id="tdata"><a href="SalesmanController?id=${salesman.id}&amp;action=getSalesmanEdit&amp;role=manager">Edit</a></td>
                                    <td id="tdata"><a href="SalesmanController?id=${salesman.id}&amp;action=getSalesmanDelete">Delete</a></td>
                                </tr>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                </tbody>
        </table>
        <form action="SalesmanController" method="POST">
            <table>
                <tr>
                    <td>Username: </td>
                    <td><input type="text" name="idSearch" size="20"></td>
                </tr>
            </table>
            <input type="hidden" name="action" value="postSalesmanSearch">
            <p><input type="submit" value="Search"></p>
        </form>
        <br><br>
        <td><a href="salesRegister.jsp">Register New Salesman</a></td>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>
    </body>
</html>