<%-- 
    Document   : monthlySales
    Created on : Mar 6, 2023, 8:16:42 PM
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
        <form action="ManagerController" method="POST">
        <label for="date">Select a date:</label>
        <input type="month" id="date" name="date" min="2000-01" max="2099-12">
        <input type="hidden" name="action" value="postMonthlyAnalysis">
        <p><input type="submit" value="Submit Edit"></p>
</form>
    </body>
</html>
