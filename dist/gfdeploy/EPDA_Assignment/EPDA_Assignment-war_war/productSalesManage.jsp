<%-- 
    Document   : productStatusManage
    Created on : Feb 28, 2023, 4:53:55 PM
    Author     : yelia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <title>Manage Your Product</title>
    </head>
    <body>
        <a href="salesMenu.jsp">&laquo; Previous Page</a>
        <table id="table">
            <thead id="thead">
                <tr id="trow">
                    <th id="th">Id</th>
                    <th id="th">Price</th>
                    <th id="th">Status</th>
                    <th id="th">Salesman</th>
                    <th id="th">Class</th>
                    <th id="th"></th>
                </tr>
            </thead>
                <tbody id="tbody">
                    <c:choose>
                        <c:when test="${productList != null }"  >

                            <c:forEach items="${productList}" var="product">
                                <tr id="trow">
                                    <td id="tdata">${product.id}</td>
                                    <td id="tdata">${product.price}</td>
                                    <td id="tdata">${product.status}</td>
                                    <td id="tdata">${product.salesman.name}</td>
                                    <td id="tdata">${product.productClass.name}</td>
                                    <td id="tdata">
                                        <c:if test="${product.status == 'available'}">
                                          <a href="SalesmanController?id=${product.id}&amp;action=getProductEdit&amp;nstatus=booked">Book</a>
                                        </c:if>
                                        <c:if test="${product.status == 'booked'}">
                                          <a href="SalesmanController?id=${product.id}&amp;action=getProductEdit&amp;nstatus=paid">Paid</a>
                                          <a href="SalesmanController?id=${product.id}&amp;action=getProductEdit&amp;nstatus=cancel">Cancel</a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                </tbody>
        </table>
        <form action="SalesmanController" method="POST">
            <table>
                <tr>
                    <td>Product ID: </td>
                    <td><input type="text" name="idProductSearch" pattern="-?\d+" size="20"></td>
                </tr>
            </table>
            <input type="hidden" name="action" value="postProductSearch">
            <p><input type="submit" value="Search"></p>
        </form>
        <br><br>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>
    </body>
</html>
