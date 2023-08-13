<%-- 
    Document   : managerProductPayment
    Created on : Mar 2, 2023, 12:43:03 PM
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
        <title>JSP Page</title>
    </head>
    <body>
        <a href="managerMenu.jsp">&laquo; Previous Page</a>
    <break/><break/>PAID Records ${paidPercentage}%
        <table id="table">
            <thead id="thead">
                <tr id="trow">
                    <th id="th">Id</th>
                    <th id="th">Category ID</th>
                    <th id="th">Category Name</th>
                    <th id="th">Date</th>
                    <th id="th">Time</th>
                    <th id="th">Price</th>
                    <th id="th">Salesman ID</th>
                    <th id="th">Salesman Review</th>
                    <th id="th">Customer</th>
                    <th id="th">Rating</th>
                    <th id="th">Customer Review</th>
                    
                </tr>
            </thead>
                <tbody id="tbody">
                    <c:choose>
                        <c:when test="${paidRecords != null }"  >

                            <c:forEach items="${paidRecords}" var="paidRecord">
                                <tr id="trow">
                                    <td id="tdata">${paidRecord.id.id}</td>
                                    <td id="tdata">${paidRecord.id.productClass.id}</td>
                                    <td id="tdata">${paidRecord.id.productClass.name}</td>
                                    <td id="tdata">${paidRecord.dt.toLocalDate()}</td>
                                    <td id="tdata">${paidRecord.dt.toLocalTime()}</td>
                                    <td id="tdata">${paidRecord.id.price}</td>
                                    <td id="tdata">${paidRecord.id.salesman.id}</td>
                                    <td id="tdata">${paidRecord.salesReview}</td>
                                    <td id="tdata">${paidRecord.customer.id}</td>
                                    <td id="tdata">${paidRecord.customerRating}</td>
                                    <td id="tdata">${paidRecord.customerFeedback}</td>
                                </tr>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                </tbody>
        </table>
                                <br><br>
                                
        Booked Record    ${bookedPercentage}%                 
        <table id="table">
            <thead id="thead">
                <tr id="trow">
                    <th id="th">Id</th>
                    <th id="th">Category ID</th>
                    <th id="th">Category Name</th>
                    <th id="th">Date</th>
                    <th id="th">Time</th>
                    <th id="th">Price</th>
                    <th id="th">Salesman ID</th>
                    <th id="th">Customer</th>
                    
                </tr>
            </thead>
                <tbody id="tbody">
                    <c:choose>
                        <c:when test="${bookedRecords != null }"  >

                            <c:forEach items="${bookedRecords}" var="bookedRecord">
                                <tr id="trow">
                                    <td id="tdata">${bookedRecord.id.id}</td>
                                    <td id="tdata">${bookedRecord.id.productClass.id}</td>
                                    <td id="tdata">${bookedRecord.id.productClass.name}</td>
                                    <td id="tdata">${bookedRecord.dt.toLocalDate()}</td>
                                    <td id="tdata">${bookedRecord.dt.toLocalTime()}</td>
                                    <td id="tdata">${bookedRecord.id.price}</td>
                                    <td id="tdata">${bookedRecord.id.salesman.id}</td>
                                    <td id="tdata">${bookedRecord.customer.id}</td>
                                </tr>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                </tbody>
        </table>
    </body>
</html>
