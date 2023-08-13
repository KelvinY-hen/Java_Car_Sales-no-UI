<%-- 
    Document   : customerProductEdit
    Created on : Mar 2, 2023, 11:14:29 AM
    Author     : yelia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="customerPurchase.jsp">&laquo; Previous Page</a>
        <form action="CustomerController" method="POST">
            <table>
                <tr>
                    <td>ID: </td>
                    <td>${saleRecord.id.id}</td>
                    <input type="hidden" name="idProd" value="${saleRecord.id.id}" />
                </tr>
                <tr>
                    <td>Product Class Name:</td>
                    <td>
                        ${saleRecord.id.productClass.name}
                    </td>
                </tr>
                <tr>
                    <td>Price: </td>
                    <td>
                        ${saleRecord.id.price}
                    </td>
                </tr>
                <tr>
                    <td>Salesman ID: </td>
                    <td>
                        ${saleRecord.salesman.id}
                    </td>
                </tr><tr>
                    <td>Customer ID: </td>
                    <td>${saleRecord.customer.id}</td>
                </tr>
                <tr>
                    <td>Customer Name: </td>
                    <td>${saleRecord.customer.name}</td>
                </tr>
                <tr>
                    <td>Rating: </td>
                    <td>
                      <select name="customerRating" id="customerRating">
                        <option value="5" ${saleRecord.customerRating == '5' ? 'selected' : ''}>5</option>
                        <option value="4" ${saleRecord.customerRating == '4' ? 'selected' : ''}>4</option>
                        <option value="3" ${saleRecord.customerRating == '3' ? 'selected' : ''}>3</option>
                        <option value="2" ${saleRecord.customerRating == '2' ? 'selected' : ''}>2</option>
                        <option value="1" ${saleRecord.customerRating == '1' ? 'selected' : ''}>1</option>
                      </select>
                    </td>
                 </tr>
                <tr>
                    <td>
                        <p>
                            <label for="customerReview">Customer Review:</label>
                            <input type="text" name="customerReview" value="${saleRecord.customerFeedback}" id="customerReview">
                        </p>
                    </td>
                </tr>
            </table>
                <input type="hidden" name="action" value="postProductEdit">
            <p><input type="submit" value="Change Status"></p>
        </form>
    </body>
</html>
