<%-- 
    Document   : salesRecord.jsp
    Created on : Mar 2, 2023, 9:35:45 AM
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
        <title>Sales History</title>
    </head>
    <body>
        <a href="salesMenu.jsp">&laquo; Previous Page</a>
        <table id="table">
            <thead id="thead">
                <tr id="trow">
                    <th id="th">Id</th>
                    <th id="th">Category</th>
                    <th id="th">Date</th>
                    <th id="th">Time</th>
                    <th id="th">Price</th>
                </tr>
            </thead>
                <tbody id="tbody">
                    <c:choose>
                        <c:when test="${saleRecordsList != null }"  >

                            <c:forEach items="${saleRecordsList}" var="saleRecord">
                                <tr id="trow">
                                    <td id="tdata">${saleRecord.id.id}</td>
                                    <td id="tdata">${saleRecord.id.productClass.name}</td>
                                    <td id="tdata">${saleRecord.dt.toLocalDate()}</td>
                                    <td id="tdata">${saleRecord.dt.toLocalTime()}</td>
                                    <td id="tdata">${saleRecord.id.price}</td>
                                </tr>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                </tbody>
        </table>
    </body>
</html>
