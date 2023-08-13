<%-- 
    Document   : productEdit
    Created on : Feb 26, 2023, 5:32:58 PM
    Author     : yelia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>edit Profile</title>
    </head>
    <body>
        <a href="<%= request.getHeader("Referer") %>">&laquo; Previous Page</a>
        <br><br><br>
        
        <form action="ProductController" method="POST">
            <table>
                <tr>
                    <td>ID: </td>
                    <td><input type="text" name="idProd" value="${product.id}" size="20"></td>
                </tr>
                <tr>
                    <td>Status:</td>
                    <td>
                      <select name="statusProd" id="statusProd">
                        <option value="available" ${product.status == 'available' ? 'selected' : ''}>Available</option>
                        <option value="booked" ${product.status == 'booked' ? 'selected' : ''}>Booked</option>
                        <option value="paid" ${product.status == 'paid' ? 'selected' : ''}>Paid</option>
                        <option value="cancel" ${product.status == 'cancel' ? 'selected' : ''}>Cancel</option>
                      </select>
                    </td>
                 </tr>
                 <tr>
                    <td>Price: </td>
                    <td><input type="text" name="priceProd" value="${product.price}" size="20"></td>
                </tr>
                 <tr>
                    <td>Salesman ID: </td>
                    <td>
                        <select name="idSales" id="idSales">
                        <c:forEach items="${salesmanList}" var="salesman">
                            <option value="${salesman.id}">${salesman.name}</option>
                        </c:forEach>
                        </select>
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
                
            </table>
                <input type="hidden" name="selectedStatus" id="selectedStatus" value="${product.status}">
                <input type="hidden" name="action" value="postProductEdit">
            <p><input type="submit" value="Submit Edit"></p>
        </form>
    </body>
</html>
