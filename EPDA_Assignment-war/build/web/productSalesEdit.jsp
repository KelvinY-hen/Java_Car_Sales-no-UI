<%-- 
    Document   : productSalesEdit
    Created on : Mar 1, 2023, 9:29:36 AM
    Author     : yelia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="productSalesManage.jsp">&laquo; Previous Page</a>
        <form action="SalesmanController" method="POST">
            <table>
                <tr>
                    <td>ID: </td>
                    <td>${product.id}</td>
                    <input type="hidden" name="idProd" value="${product.id}" />
                </tr>
                <tr>
                    <td>Status:</td>
                    <td>
                        ${product.status}
                        <input type="hidden" name="statusProd" value="${product.status}" />
                    </td>
                 </tr>
                 <tr>
                    <td>Price: </td>
                    <td>
                        ${product.price}
                        <input type="hidden" name="priceProd" value="${product.price}" />
                    </td>
                </tr>
                <tr>
                    <td>Salesman ID: </td>
                    <td>
                        ${product.salesman.id}
                        <input type="hidden" name="idSales" value="${product.salesman.id}" />
                    </td>
                </tr>
                 <tr>
                    <td>Product Class ID: </td>
                    <td>
                        ${product.productClass.id}
                        <input type="hidden" name="idProductClass" value="${product.productClass.id}" />
                    </td>
                </tr>
                <tr>
                    <td>Product Class Name:</td>
                    <td>
                        ${product.productClass.name}
                    </td>
                </tr> 
                    <c:choose>
                    <c:when test="${product.status eq 'booked'}">
                        <tr>
                            <td>Customer ID: </td>
                            <td>
                                <select name="idCust">
                                    <c:forEach items="${customerList}" var="customer">
                                        <option value="${customer.id}">${customer.id}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                    </c:when>
                    <c:when test="${product.status eq 'paid'}">
                        <tr>
                            <td>Customer ID: </td>
                            <td>${customer.id}</td>
                            <input type="hidden" name="idCust" value="${customer.id}" />
                        </tr>
                        <tr>
                            <td>Customer Name: </td>
                            <td>${customer.name}</td>
                        </tr>
                        <tr>
                            <td>
                                <p>
                                    <label for="salesReview">Sales Review:</label>
                                    <input type="text" name="salesReview" id="salesReview" required>
                                </p>
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td>Customer ID: </td>
                            <td>${customer.id}</td>
                            <input type="hidden" name="idCust" value="${customer.id}" />
                        </tr>
                        <tr>
                            <td>Customer Name: </td>
                            <td>${customer.name}</td>
                        </tr>
                    </c:otherwise>
                    </c:choose>
                </tr>
            </table>
                <input type="hidden" name="selectedStatus" id="selectedCustomer" value="${customer.id}">
                <input type="hidden" name="action" value="postProductEdit">
            <p><input type="submit" value="Change Status"></p>
        </form>
                <script>
                    var salesReview = document.getElementById("salesReview").value.trim();
                    if (salesReview.length === 0) {
                        alert("Sales review cannot be empty");
                        return false; // or take any other appropriate action
                    }
                 </script>
    </body>
</html>
