<%-- 
    Document   : customerManage
    Created on : Feb 22, 2023, 10:33:13 AM
    Author     : yelia
--%>

<%@page import="javax.naming.InitialContext"%>
<%@page import="java.util.List"%>
<%@page import="model.CustomerFacade"%>
<%@page import="model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <a href="<customerMenu.jsp">&laquo; Previous Page</a>
        <table id="table">
            <thead id="thead">
                <tr id="trow">
                    <th id="th">Id</th>
                    <th id="th">Name</th>
                    <th id="th">Password</th>
                    <th id="th">Gender</th>
                    <th id="th"></th>
                    <th id="th"></th>
                </tr>
            </thead>
                <tbody id="tbody">
                    <c:choose>
                        <c:when test="${customerList != null }"  >

                            <c:forEach items="${customerList}" var="customer">
                                <tr id="trow">
                                    <td id="tdata">${customer.id}</td>
                                    <td id="tdata">${customer.name}</td>
                                    <td id="tdata">${customer.password}</td>
                                    <td id="tdata">${customer.gender}</td>
                                    <td id="tdata"><a href="CustomerController?id=${customer.id}&amp;action=getCustomerEdit">Edit</a></td>
                                    <td id="tdata"><a href="CustomerController?id=${customer.id}&amp;action=getCustomerDelete">Delete</a></td>
                                </tr>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                </tbody>
        </table>
        <form action="CustomerController" method="POST">
            <table>
                <tr>
                    <td>Username: </td>
                    <td><input type="text" name="idSearch" size="20"></td>
                </tr>
            </table>
            <input type="hidden" name="action" value="postCustomerSearch">
            <p><input type="submit" value="Search"></p>
        </form>
        <br><br>
        <td><a href="customerRegister.jsp">Register New Customer</a></td>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>
    </body>
</html>
