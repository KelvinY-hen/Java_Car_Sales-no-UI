<%-- 
    Document   : productManage
    Created on : Feb 22, 2023, 10:33:13 AM
    Author     : yelia
--%>

<%@page import="javax.naming.InitialContext"%>
<%@page import="java.util.List"%>
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
        <a href="managerMenu.jsp">&laquo; Previous Page</a>
        <table id="table">
            <thead id="thead">
                <tr id="trow">
                    <th id="th">Id</th>
                    <th id="th">Price</th>
                    <th id="th">Status</th>
                    <th id="th">Salesman</th>
                    <th id="th">Class</th>
                    <th id="th"></th>
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
                                    <td id="tdata"><a href="ProductController?id=${product.id}&amp;action=getProductEdit">Edit</a></td>
                                    <td id="tdata"><a href="ProductController?id=${product.id}&amp;action=getProductDelete">Delete</a></td>
                                </tr>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                </tbody>
        </table>
        <form action="ProductController" method="POST">
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
        <td><a href="ProductController?action=getProductAdd">Register New Product</a></td>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>
        
        
        <br><br><br>
        <table id="table">
            <thead id="thead">
                <tr id="trow">
                    <th id="th">Id</th>
                    <th id="th">Name</th>
                    <th id="th"></th>
                    <th id="th"></th>
                </tr>
            </thead>
                <tbody id="tbody">
                    <c:choose>
                        <c:when test="${productClassList != null }"  >

                            <c:forEach items="${productClassList}" var="productClass">
                                <tr id="trow">
                                    <td id="tdata">${productClass.id}</td>
                                    <td id="tdata">${productClass.name}</td>
                                    <td id="tdata"><a href="ProductController?id=${productClass.id}&amp;action=getProductClassEdit">Edit</a></td>
                                    <td id="tdata"><a href="ProductController?id=${productClass.id}&amp;action=getProductClassDelete">Delete</a></td>
                                </tr>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                </tbody>
        </table>
                                
               <td><a href="productClassRegister.jsp">Register New Product Class</a></td>
               
               <form action="ProductController" method="POST">
                    <table>
                        <tr>
                            <td>Product Class ID: </td>
                            <td><input type="text" name="idProductClassSearch" size="20"></td>
                        </tr>
                    </table>
                    <input type="hidden" name="action" value="postProductClassSearch">
                    <p><input type="submit" value="Search"></p>
                </form>
    </body>
</html>
