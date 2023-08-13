<%-- 
    Document   : customerPurchase
    Created on : Mar 2, 2023, 10:33:54 AM
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
        <title>Your Purchase</title>
    </head>
    <body>
        <a href="customerMenu.jsp">&laquo; Previous Page</a>
        <table id="table">
            <thead id="thead">
                <tr id="trow">
                    <th id="th">Id</th>
                    <th id="th">Category</th>
                    <th id="th">Salesman</th>
                    <th id="th">Date</th>
                    <th id="th">Time</th>
                    <th id="th">Price</th>
                    <th id="th">Rating</th>
                    <th id="th">Your Review</th>
                    <th id="th"></th>
                    
                </tr>
            </thead>
                <tbody id="tbody">
                    <c:choose>
                        <c:when test="${customerPurchaseList != null }"  >

                            <c:forEach items="${customerPurchaseList}" var="purchase">
                                <tr id="trow">
                                    <td id="tdata">${purchase.id.id}</td>
                                    <td id="tdata">${purchase.id.productClass.name}</td>
                                    <td id="tdata">${purchase.id.salesman.name}</td>
                                    <td id="tdata">${purchase.dt.toLocalDate()}</td>
                                    <td id="tdata">${purchase.dt.toLocalTime()}</td>
                                    <td id="tdata">${purchase.id.price}</td>
                                    <td id="tdata">${purchase.customerRating}</td>
                                    <td id="tdata">${purchase.customerFeedback}</td>
                                    <td><a href="CustomerController?id=${purchase.id.id}&amp;action=getProductEdit">Edit Feedback & Review</a></td>
                                </tr>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                </tbody>
        </table>
    </body>
</html>
