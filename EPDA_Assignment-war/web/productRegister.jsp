<%-- 
    Document   : productRegister
    Created on : Feb 26, 2023, 6:39:14 PM
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
        <a href="<%= request.getHeader("Referer") %>">&laquo; Previous Page</a>
        <br><br><br>
        <table id="table">
            <thead id="thead">
                <tr id="trow">
                    <th id="th">Id</th>
                    <th id="th">Name</th>
                </tr>
            </thead>
                <tbody id="tbody">
                    <c:choose>
                        <c:when test="${productClassList != null }"  >

                            <c:forEach items="${productClassList}" var="productClass">
                                <tr id="trow">
                                    <td id="tdata">${productClass.id}</td>
                                    <td id="tdata">${productClass.name}</td>
                                </tr>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                </tbody>
        </table>
                                
               <td><a href="productClassRegister.jsp">Register New Product Class</a></td>                 
                                
        <table id="table">
            <thead id="thead">
                <tr id="trow">
                    <th id="th">Id</th>
                    <th id="th">Name</th>
                </tr>
            </thead>
                <tbody id="tbody">
                    <c:choose>
                        <c:when test="${salesmanList != null }"  >

                            <c:forEach items="${salesmanList}" var="salesman">
                                <tr id="trow">
                                    <td id="tdata">${salesman.id}</td>
                                    <td id="tdata">${salesman.name}</td>
                                </tr>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                </tbody>
        </table>
                          
                <td><a href="salesRegister.jsp">Register New Salesman</a></td>
                
                
        <form action="ProductController" method="POST">
            <table>
                <tr>
                    <tr>
                        <td>Id: </td>
                        <td><input type="text" name="idProd" pattern="-?\d+" size="20"></td>
                    </tr>
                    <td>Status:</td>
                    <td>
                      <select name="statusProd" id="statusProd">
                        <option value="available" >Available</option>
                        <option value="booked" >Booked</option>
                        <option value="paid">Paid</option>
                        <option value="cancel" >Cancel</option>
                      </select>
                    </td>
                 </tr>
                 <tr>
                    <td>Price: </td>
                    <td><input type="text" name="priceProd" size="20"></td>
                </tr>
                 <tr>
                    <td>Sales ID: </td>
                    <td><input type="text" name="idSales"  size="20"></td>
                </tr>
                 <tr>
                    <td>Product Class ID: </td>
                    <td><input type="text" name="idProductClass" size="20"></td>
                </tr>
            </table>
            <input type="hidden" name="action" value="postProductAdd">
            <p><input type="submit" value="Register"></p>
        </form>
    </body>
</html>
